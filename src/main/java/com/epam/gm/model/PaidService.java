package com.epam.gm.model;

import com.epam.gm.olgmaks.absractdao.annotation.Column;
import com.epam.gm.olgmaks.absractdao.annotation.Entity;

@Entity("paid_service")
public class PaidService {
	@Column("id")
	private Integer id;
	@Column("user_id")
	private Integer userId;
	@Column("event_id")
	private Integer eventId;
	@Column("service_in_event_id")
	private Integer serviceInEventId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public Integer getServiceInEventId() {
		return serviceInEventId;
	}

	public void setServiceInEventId(Integer serviceInEventId) {
		this.serviceInEventId = serviceInEventId;
	}

}
