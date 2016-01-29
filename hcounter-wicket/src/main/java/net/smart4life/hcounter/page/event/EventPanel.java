package net.smart4life.hcounter.page.event;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.value.ValueMap;

import net.smart4life.hcounter.datamodel.entity.WorkEvent;
import net.smart4life.hcounter.service.windowsevent.WindowsEventService;

public class EventPanel extends Panel {

	@SpringBean
	private WindowsEventService eventService;
	
	public EventPanel(String id) {
		super(id);
		

		// Add comment form
//        add(new UserForm("userForm"));
        // Add commentListView of existing comments
//        add(new PropertyListView<User>("users", userService.findAll()) {
//            @Override
//            public void populateItem(final ListItem<User> listItem) {
//                listItem.add(new Label("id"));
//                listItem.add(new MultiLineLabel("loginname"));
//            }
//        }).setVersioned(false);
		
        add(new PropertyListView<WorkEvent>("events", new EventListModel()) {
            @Override
            public void populateItem(final ListItem<WorkEvent> listItem) {
                listItem.add(new Label("id"));
                listItem.add(new Label("eventDate"));
                listItem.add(new Label("workEventType"));
                listItem.add(new Label("creaUser"));
            }       	
          }).setVersioned(false);
	}
	

	class EventListModel extends LoadableDetachableModel<List<WorkEvent>> {
		@Override
		protected List<WorkEvent> load() {
			return eventService.findAll();
		}
	}
	
}
