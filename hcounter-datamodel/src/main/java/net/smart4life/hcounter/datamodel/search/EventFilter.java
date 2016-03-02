package net.smart4life.hcounter.datamodel.search;

import java.util.Date;

public class EventFilter extends BaseFilter {
	private Date dateFrom;
	private Date dateTo;
	
	public Date getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}
	public Date getDateTo() {
		return dateTo;
	}
	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}
	
	@Override
	public String toString() {
		return "EventFilter [dateFrom=" + dateFrom + ", dateTo=" + dateTo + "]";
	}
}
