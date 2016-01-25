package net.smart4life.hcounter.service.windowsevent;

import net.smart4life.hcounter.dao.UserDAO;
import net.smart4life.hcounter.dao.WindowsEventDAO;
import net.smart4life.hcounter.datamodel.entity.User;
import net.smart4life.hcounter.datamodel.entity.WorkEvent;
import net.smart4life.hcounter.datamodel.enumeration.WindowsEvent;

import org.hibernate.internal.util.xml.MappingReader.SupportedOrmXsdVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by roman on 21.09.2015.
 */
@Component
public class WindowsEventServiceImpl implements WindowsEventService {
	private static final Logger LOG = LoggerFactory.getLogger(WindowsEventServiceImpl.class);
	
	private static final String EVENTS_FILE = "C:/Temp/stopStartEvents.txt";
	
	public static final String WINDOWS_EVENT_USER = "WINDOWS_EVENT";
	
    @Autowired
    private WindowsEventDAO eventDAO;

    @Override
    public WorkEvent findById(long id) {
        return eventDAO.findById(id);
    }

    @Override
    public List<WorkEvent> findAll() {
        return eventDAO.findAll();
    }

    @Override
    @Transactional
    public WorkEvent insert(WorkEvent event) {
        return eventDAO.insert(event);
    }

    @Override
    @Transactional
    public WorkEvent update(WorkEvent event) {
        return eventDAO.update(event);
    }

    @Override
    @Transactional
    public void delete(WorkEvent event) {
    	eventDAO.delete(event);
    }

	@Override
	public List<WorkEvent> findByDateAndCode(Date from, Date to, Integer... eventCodes) {
		return eventDAO.findByDateAndCode(from, to, eventCodes);
	}

	@Override
	@Transactional
	public Integer loadEventsIntoDb(Date from) {
		System.out.println("Load events ...");
		loadEventsIntoFile();
		List<WorkEvent> events = loadEventsFromFile(EVENTS_FILE);
		LOG.debug(String.format("loaded %d events", events.size()));
		for(WorkEvent event : events){
			eventDAO.insert(event);
		}
		return 33;
	}
	
	private void loadEventsIntoFile(){
		try {
			ProcessBuilder pb = new ProcessBuilder("powershell", "Get-WinEvent -FilterHashtable @{Logname='System';ID=6005,6006} | Format-Table -AutoSize -HideTableHeaders TimeCreated, Id >> ", EVENTS_FILE);
//			Map<String, String> env = pb.environment();
//			env.put("VAR1", "myValue");
//			env.remove("OTHERVAR");
//			env.put("VAR2", env.get("VAR1") + "suffix");
			pb.directory(new File("c:/temp"));
			Process p = pb.start();
			for(int i = 0; p.isAlive() && i < 5; i++){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					LOG.error("can not sleep", e);
				}
			}
		} catch (IOException e) {
			LOG.error("can not write windows events to file", e);
		}
	}
	
	private List<WorkEvent> loadEventsFromFile(String eventsFile) {
		List<WorkEvent> events = new ArrayList<>();
		Path eventsPath = Paths.get(eventsFile);
		if (eventsFile != null && Files.exists(eventsPath)) {
			try (BufferedReader reader = Files.newBufferedReader(eventsPath, Charset.forName("x-UTF-16LE-BOM"))) {
				String l = "";
				SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
				while ((l = reader.readLine()) != null) {
					if(l.length() == 24){
						try {
							String dateString = l.substring(0, 19);
							String codeString = l.substring(20);
							Date d = sdf.parse(dateString);
							int i = Integer.parseInt(codeString);
							WindowsEvent windowsEvent = WindowsEvent.getByCode(i);
							if(windowsEvent == null){
								throw new RuntimeException(String.format("not supported event code %d", i));
							}
							WorkEvent event = new WorkEvent(d, windowsEvent.getWorkEventType(), WINDOWS_EVENT_USER);
							events.add(event);
						} catch (Exception ex) {
							LOG.error("can not parse event line '"+l+"'", ex);
						}
					}
					

				}

			} catch (IOException e) {
				LOG.error("can not read "+eventsFile+" file", e);
			}
		} 
		return events;
	}
}
