package com.epam.gm.model;

import com.epam.gm.model.annotation.Column;
import com.epam.gm.model.annotation.Entity;
import com.epam.gm.model.annotation.ID;
@Entity("rating_user")
public class RatingUser {
	@ID("id")
	private Integer id;
	@Column("estimator_id")
	private Integer estimatorId;
	private User estimator;
	@Column("user_id")
	private Integer userId;
	private User user; 
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
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getMark() {
		return mark;
	}
	public void setMark(Integer mark) {
		this.mark = mark;
	}
	
}
