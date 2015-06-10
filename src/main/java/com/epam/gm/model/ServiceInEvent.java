package com.epam.gm.model;

import java.sql.Time;
import java.util.Date;

import com.epam.gm.olgmaks.absractdao.annotation.Column;
import com.epam.gm.olgmaks.absractdao.annotation.Entity;
import com.epam.gm.olgmaks.absractdao.annotation.ForeignKey;
import com.epam.gm.olgmaks.absractdao.annotation.OneToMany;

@Entity("service_in_event")
public class ServiceInEvent {
    @Column("id")
    private Integer id;
    @Column("date")
    private Date date;
    @Column("time_from")
    private Time timeFrom;
    @Column("time_to")
    private Time timeTo;
    @Column("service_id")
    private Integer serviceId;

    @ForeignKey
    @OneToMany(field = "service_id", value = Service.class)
    private Service service;
    @Column("event_id")
    private Integer eventId;

    @ForeignKey
    @OneToMany(field = "event_id", value = Event.class)
    private Event event;

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

    @Override
    public String toString() {
	return "ServiceInEvent [id=" + id + ", date=" + date + ", timeFrom="
		+ timeFrom + ", timeTo=" + timeTo + ", serviceId=" + serviceId
		+ ", service=" + service + ", eventId=" + eventId + ", event="
		+ event + "]";
    }

    
    
}
