package com.epam.gm.model;

import org.omg.CORBA.portable.ValueBase;

import com.epam.gm.olgmaks.absractdao.annotation.Column;
import com.epam.gm.olgmaks.absractdao.annotation.Entity;
import com.epam.gm.olgmaks.absractdao.annotation.ForeignKey;
import com.epam.gm.olgmaks.absractdao.annotation.OneToMany;



@Entity("user_in_event")
public class UserInEvent {

    	@Column("id")
	private Integer id;
    	
	@Column("user_id")
	private Integer userId;
	
	@ForeignKey("id")
	@OneToMany(field = "user_id",value= User.class)
	private User user;
	
	@Column("event_id")
	private Integer eventId;
	
	@ForeignKey("id")
	@OneToMany(field = "event_id",value= Event.class)
	private Event event;
	@Column("status")
	private String status;
	@Column("bed_count")
	private Integer bedCount;
	@Column("food_count")
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

	@Override
	public String toString() {
	    return "UserInEvent [id=" + id + ", userId=" + userId + ", user="
		    + user + ", eventId=" + eventId + ", event=" + event
		    + ", status=" + status + ", bedCount=" + bedCount
		    + ", foodCount=" + foodCount + ", carplaceCount="
		    + carplaceCount + ", isMember=" + isMember + "]";
	}
	
	

}
