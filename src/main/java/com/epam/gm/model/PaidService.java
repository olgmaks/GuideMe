package com.epam.gm.model;

import com.epam.gm.olgmaks.absractdao.annotation.Column;
import com.epam.gm.olgmaks.absractdao.annotation.Entity;
import com.epam.gm.olgmaks.absractdao.annotation.ForeignKey;
import com.epam.gm.olgmaks.absractdao.annotation.OneToMany;

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
	@Column("accepted")
	private Integer accepted;
	@ForeignKey
	@OneToMany(field = "user_id", value = User.class)
	private User user;

	@ForeignKey
	@OneToMany(field = "service_in_event_id", value = ServiceInEvent.class)
	private ServiceInEvent serviceInEvent;

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

	public boolean isAccepted() {
		if (this.accepted == 1) {
			return true;
		} else {
			return false;
		}

	}

	public void setAccepted(boolean accepted) {
		if (accepted) {
			this.accepted = 1;
		} else {
			this.accepted = 0;
		}

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ServiceInEvent getServiceInEvent() {
		return serviceInEvent;
	}

	public void setServiceInEvent(ServiceInEvent serviceInEvent) {
		this.serviceInEvent = serviceInEvent;
	}

}
