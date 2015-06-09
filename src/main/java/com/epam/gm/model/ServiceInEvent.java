package com.epam.gm.model;

import java.sql.Date;
import java.sql.Time;

import com.epam.gm.model.annotation.Column;
import com.epam.gm.model.annotation.Entity;
import com.epam.gm.model.annotation.ID;

@Entity("service_in_event")
public class ServiceInEvent {
	@ID("id")
	private Integer id;
	@Column("date")
	private Date date;
	@Column("time_from")
	private Time timeFrom;
	@Column("time_to")
	private Time timeTo;
	@Column("service_id")
	private Integer serviceId;
	@Column("event_id")
	private Integer eventId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getTimeFrom() {
		return timeFrom;
	}

	public void setTimeFrom(Time timeFrom) {
		this.timeFrom = timeFrom;
	}

	public Time getTimeTo() {
		return timeTo;
	}

	public void setTimeTo(Time timeTo) {
		this.timeTo = timeTo;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

}
