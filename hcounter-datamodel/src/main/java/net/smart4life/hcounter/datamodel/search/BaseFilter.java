package net.smart4life.hcounter.datamodel.search;

import java.io.Serializable;

/**
 * Created by ILIN02 on 09.03.2015.
 */
public class BaseFilter implements Serializable {
	private SortMeta sortMeta;
	private long first;
	private long maxResults;

	public String getSortField(){
		return sortMeta != null ? sortMeta.getSortField() : null;
	}

	public String getSortOrder(){
		return sortMeta != null ? sortMeta.getSortDirection().name() : SortDirection.DESCENDING.name();
	}

	public SortMeta getSortMeta() {
		return sortMeta;
	}

	public void setSortMeta(SortMeta sortMeta) {
		this.sortMeta = sortMeta;
	}

	public long getFirst() {
		return first;
	}

	public void setFirst(long first) {
		this.first = first;
	}

	public long getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(long maxResults) {
		this.maxResults = maxResults;
	}

}
