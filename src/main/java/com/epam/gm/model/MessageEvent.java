package com.epam.gm.model;

import com.epam.gm.olgmaks.absractdao.annotation.Column;
import com.epam.gm.olgmaks.absractdao.annotation.Entity;
import com.epam.gm.olgmaks.absractdao.annotation.ForeignKey;
import com.epam.gm.olgmaks.absractdao.annotation.OneToMany;

@Entity("message_event")
public class MessageEvent {

    @Column("id")
    private Integer id;

    @Column("sender_id")
    private Integer senderId;

    @ForeignKey("id")
    @OneToMany(field = "sender_id", value = User.class)
    private User sender;

    @Column("event_id")
    private Integer eventId;

    @ForeignKey("id")
    @OneToMany(field = "event_id", value = Event.class)
    private Event event;

    @Column("message")
    private String message;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public Integer getSenderId() {
	return senderId;
    }

    public void setSenderId(Integer senderId) {
	this.senderId = senderId;
    }

    public Integer getEventId() {
	return eventId;
    }

    public void setEventId(Integer eventId) {
	this.eventId = eventId;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	@Override
    public String toString() {
	return "MessageEvent [id=" + id + ", senderId=" + senderId
		+ ", sender=" + sender + ", eventId=" + eventId + ", event="
		+ event + ", message=" + message + "]";
    }

    
    
}
