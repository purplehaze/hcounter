package net.smart4life.hcounter.dataprovider;

import java.util.Iterator;

import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.IFilterStateLocator;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.springframework.stereotype.Service;

import net.smart4life.hcounter.datamodel.entity.WorkEvent;
import net.smart4life.hcounter.datamodel.search.EventFilter;

@Service
public class EventDataProvider extends SortableDataProvider<WorkEvent, String> implements IFilterStateLocator<EventFilter> {

	@Override
	public Iterator<? extends WorkEvent> iterator(long first, long count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IModel<WorkEvent> model(WorkEvent object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EventFilter getFilterState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFilterState(EventFilter state) {
		// TODO Auto-generated method stub
		
	}

}
