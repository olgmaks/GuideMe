package com.epam.gm.model;

import com.epam.gm.model.annotation.Column;
import com.epam.gm.model.annotation.Entity;
import com.epam.gm.model.annotation.ID;

@Entity("user_tag")
public class UserTag {
	@ID("id")
private Integer id;
	@Column("user_id")
private Integer userId;
	@Column("tag_id")
	private Integer tagId;
	@Override
	public String toString() {
		return "UserTag [id=" + id + ", tagId=" + tagId + ", userId=" + userId
				+ "]";
	}
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
	public Integer getTagId() {
		return tagId;
	}
	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	} 
	
}
