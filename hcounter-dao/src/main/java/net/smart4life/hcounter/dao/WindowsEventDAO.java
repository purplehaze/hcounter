package net.smart4life.hcounter.dao;

import java.util.Date;
import java.util.List;

import net.smart4life.hcounter.datamodel.entity.WorkEvent;

/**
 * Created by roman on 21.09.2015.
 */
public interface WindowsEventDAO extends BaseDAO<WorkEvent> {

	/**
	 * find WindowEvent's by given parameters
	 * 
	 * @param from - start date, if NULL won't be considered
	 * @param to - end date, if NULL won't be considered
	 * @param eventCodes - windows event codes, if NULL won't be considered
	 * 
	 * @return
	 */
	List<WorkEvent> findByDateAndCode(Date from, Date to, Integer... eventCodes);
}
