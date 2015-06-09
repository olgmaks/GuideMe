package com.epam.gm.model;

import com.epam.gm.model.annotation.Column;
import com.epam.gm.model.annotation.Entity;
import com.epam.gm.model.annotation.ID;

@Entity("user_in_event")
public class UserInEvent {
	@ID("id")
	private Integer id;
	@Column("user_id")
	private Integer userId;
	@Column("event_id")
	private Integer eventId;
	@Column("status")
	private String status;
	@Column("bed_count")
	private Integer bedCount;
	@Column("feed_count")
	private Integer foodCount;
	@Column("carplace_count")
	private Integer carplaceCount;
	@Column("is_member")
	private Boolean isMember;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getBedCount() {
		return bedCount;
	}

	public void setBedCount(Integer bedCount) {
		this.bedCount = bedCount;
	}

	public Integer getFoodCount() {
		return foodCount;
	}

	public void setFoodCount(Integer foodCount) {
		this.foodCount = foodCount;
	}

	public Integer getCarplaceCount() {
		return carplaceCount;
	}

	public void setCarplaceCount(Integer carplaceCount) {
		this.carplaceCount = carplaceCount;
	}

	public Boolean getIsMember() {
		return isMember;
	}

	public void setIsMember(Boolean isMember) {
		this.isMember = isMember;
	}

}
