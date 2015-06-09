package com.epam.gm.model;

import com.epam.gm.model.annotation.Column;
import com.epam.gm.model.annotation.Entity;
import com.epam.gm.model.annotation.ID;

@Entity("event_tag")
public class EventTag {
	@ID("id")
	private Integer id;
	private Event event;
	@Column("event_id")
	private Integer eventId;
	@Column("tag_id")
	private Integer tagId;
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
