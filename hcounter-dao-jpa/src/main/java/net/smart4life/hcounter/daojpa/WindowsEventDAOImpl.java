package net.smart4life.hcounter.daojpa;

import net.smart4life.hcounter.dao.BaseDAO;
import net.smart4life.hcounter.dao.UserDAO;
import net.smart4life.hcounter.dao.WindowsEventDAO;
import net.smart4life.hcounter.datamodel.entity.User;
import net.smart4life.hcounter.datamodel.entity.WorkEvent;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

/**
 * Created by roman on 21.09.2015.
 */
@Repository
public class WindowsEventDAOImpl extends BaseDAOImpl<WorkEvent> implements WindowsEventDAO {

    @Override
    public Class<WorkEvent> getEntityClass() {
        return WorkEvent.class;
    }

	@Override
	public List<WorkEvent> findByDateAndCode(Date from, Date to, Integer... eventCodes) {
		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder("select o from ");
		jpql.append(getEntityClass().getSimpleName());
		jpql.append(" o where 1 = 1 ");
		if(from != null){
			jpql.append("and o.eventDate > :from ");
			params.put("from", from);
		}
		if(to != null){
			jpql.append("and o.eventDate < :to ");
			params.put("to", to);
		}
		if(eventCodes != null && eventCodes.length > 0){
			jpql.append("and o.eventCode in :eventCodes ");
			params.put("eventCodes", Arrays.asList(eventCodes));
		}
		TypedQuery<WorkEvent> query = em.createQuery(jpql.toString(), getEntityClass());
		for(Entry<String, Object> entry : params.entrySet()){
			query.setParameter(entry.getKey(), entry.getValue());
		}
		return query.getResultList();
	}
}
