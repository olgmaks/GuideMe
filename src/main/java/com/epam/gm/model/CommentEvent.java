package com.epam.gm.model;

import java.sql.Date;

import com.epam.gm.model.annotation.Column;
import com.epam.gm.model.annotation.Entity;
import com.epam.gm.model.annotation.ID;
@Entity("cooment_event")
public class CommentEvent {
	@ID("id")
	private Integer id;
	@Column("date")
	private Date date;
	@Column("commentator_id")
	private Integer comentatorId;
	
	private User commentator;
	
	@Column("event_id")
	private Integer eventId;
	
	private Event event;
	
	@Column("comment")
	private String comment;
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
	public Integer getComentatorId() {
		return comentatorId;
	}
	public void setComentatorId(Integer comentatorId) {
		this.comentatorId = comentatorId;
	}
	public Integer getEventId() {
		return eventId;
	}
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "CommentEvent [id=" + id + ", date=" + date + ", comentatorId="
				+ comentatorId + ", eventId=" + eventId + ", comment="
				+ comment + "]";
	}
	
}
