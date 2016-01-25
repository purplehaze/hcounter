package net.smart4life.hcounter.datamodel.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import net.smart4life.hcounter.datamodel.enumeration.WorkEventType;

/**
 * Created by roman on 16.02.2015.
 */
@Entity
public class WorkEvent extends BaseEntity {

	@NotNull
	@Column
	private Date eventDate;
	
	@NotNull
	@Enumerated(value=EnumType.STRING)
	@Column
	private WorkEventType workEventType;
	
	public WorkEvent() {

	}
	
	public WorkEvent(Date eventDate, WorkEventType workEventType, String creaUser){
		this.eventDate = eventDate;
		this.workEventType = workEventType;
		setCreaUser(creaUser);
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public WorkEventType getWorkEventType() {
		return workEventType;
	}

	public void setWorkEventType(WorkEventType workEventType) {
		this.workEventType = workEventType;
	}

}
