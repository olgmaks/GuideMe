package com.epam.gm.model;

import java.io.Serializable;
import java.util.Date;

import com.epam.gm.olgmaks.absractdao.annotation.Column;
import com.epam.gm.olgmaks.absractdao.annotation.Entity;
import com.epam.gm.olgmaks.absractdao.annotation.ForeignKey;
import com.epam.gm.olgmaks.absractdao.annotation.OneToMany;

@Entity("service_in_event")
public class ServiceInEvent implements Serializable {
	@Column("id")
	private Integer id;
	@Column("date_from")
	private Date dateFrom;
	@Column("date_to")
	private Date dateTo;
	@Column("service_id")
	private Integer serviceId;
	@Column("available_amount_of_positions")
	private Integer availableAmountOfPositions;
	@ForeignKey
	@OneToMany(field = "service_id", value = Service.class)
	private Service service;
	@Column("event_id")
	private Integer eventId;

	@ForeignKey
	@OneToMany(field = "event_id", value = Event.class)
	private Event event;
	@Column("is_necessary_to_pay")
	private Integer isNecessaryToPay;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Integer getAvailableAmountOfPositions() {
		return availableAmountOfPositions;
	}

	public void setAvailableAmountOfPositions(Integer availableAmountOfPositions) {
		this.availableAmountOfPositions = availableAmountOfPositions;
	}

	
	public Integer getIsNecessaryToPay() {
		return isNecessaryToPay;
	}

	public void setIsNecessaryToPay(Integer isNecessaryToPay) {
		this.isNecessaryToPay = isNecessaryToPay;
	}

	@Override
	public String toString() {
		return "ServiceInEvent [id=" + id + ", dateFrom=" + dateFrom
				+ ", dateTo=" + dateTo + ", serviceId=" + serviceId
				+ ", availableAmountOfPositions=" + availableAmountOfPositions
				+ ", service=" + service + ", eventId=" + eventId + ", event="
				+ event + "]";
	}

}
