package com.epam.gm.model;

import com.epam.gm.model.annotation.Column;
import com.epam.gm.model.annotation.Entity;
import com.epam.gm.model.annotation.ID;

@Entity("rating_event")
public class RatingEvent {
	@ID("id")
	private Integer id;
	@Column("estimator_id")
	private Integer estimatorId;
	@Column("event_id")
	private Integer eventId;
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

}
