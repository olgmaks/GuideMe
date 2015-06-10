package com.epam.gm.model;

import java.util.Date;

import com.epam.gm.olgmaks.absractdao.annotation.Column;
import com.epam.gm.olgmaks.absractdao.annotation.Entity;
import com.epam.gm.olgmaks.absractdao.annotation.ForeignKey;
import com.epam.gm.olgmaks.absractdao.annotation.OneToMany;

@Entity("comment_event")
public class CommentEvent {

    @Column("id")
    private Integer id;
    @Column("date")
    private Date date;
    @Column("commentator_id")
    private Integer comentatorId;

    @ForeignKey
    @OneToMany(field = "commentator_id", value = User.class)
    private User commentator;

    @Column("event_id")
    private Integer eventId;

    @ForeignKey
    @OneToMany(field = "event_id", value = Event.class)
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
	return "CommentEvent ["
		+ (id != null ? "id=" + id + ", " : "")
		+ (date != null ? "date=" + date + ", " : "")
		+ (comentatorId != null ? "comentatorId=" + comentatorId + ", "
			: "")
		+ (commentator != null ? "commentator=" + commentator + ", "
			: "")
		+ (eventId != null ? "eventId=" + eventId + ", " : "")
		+ (event != null ? "event=" + event + ", " : "")
		+ (comment != null ? "comment=" + comment : "") + "]";
    }



}
