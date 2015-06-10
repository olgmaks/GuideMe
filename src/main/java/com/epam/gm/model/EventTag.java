package com.epam.gm.model;

import com.epam.gm.olgmaks.absractdao.annotation.Column;
import com.epam.gm.olgmaks.absractdao.annotation.Entity;
import com.epam.gm.olgmaks.absractdao.annotation.ForeignKey;
import com.epam.gm.olgmaks.absractdao.annotation.OneToMany;

@Entity("event_tag")
public class EventTag {

    @Column("id")
    private Integer id;

    @ForeignKey
    @OneToMany(field = "event_id", value = Event.class)
    private Event event;
    @Column("event_id")
    private Integer eventId;
    @Column("tag_id")
    private Integer tagId;

    @ForeignKey
    @OneToMany(field = "tag_id", value = Tag.class)
    private Tag tag;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public Integer getEventId() {
	return eventId;
    }

    public void setEventId(Integer eventId) {
	this.eventId = eventId;
    }

    public Integer getTagId() {
	return tagId;
    }

    public void setTagId(Integer tagId) {
	this.tagId = tagId;
    }

    @Override
    public String toString() {
	return "EventTag [id=" + id + ", eventId=" + eventId + ", tagId="
		+ tagId + "]";
    }

}
