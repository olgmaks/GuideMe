package com.epam.gm.model;

import com.epam.gm.olgmaks.absractdao.annotation.Column;
import com.epam.gm.olgmaks.absractdao.annotation.Entity;
import com.epam.gm.olgmaks.absractdao.annotation.ForeignKey;
import com.epam.gm.olgmaks.absractdao.annotation.OneToMany;

@Entity("rating_event")
public class RatingEvent {
	@Column("id")
	private Integer id;
	@Column("estimator_id")
	private Integer estimatorId;
	@Column("event_id")
	private Integer eventId;
	
	@ForeignKey
	@OneToMany(field="event_id", value = Event.class)
	private Event event;
	@Column("mark")
	private Integer mark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEstimatorId() {
		return estimatorId;
	}

	public void setEstimatorId(Integer estimatorId) {
		this.estimatorId = estimatorId;
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public Integer getMark() {
		return mark;
	}

	public void setMark(Integer mark) {
		this.mark = mark;
	}

	@Override
	public String toString() {
	    return "RatingEvent [id=" + id + ", estimatorId=" + estimatorId
		    + ", eventId=" + eventId + ", event=" + event + ", mark="
		    + mark + "]";
	}
	
	

}
