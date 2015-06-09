package com.epam.gm.model;

import com.epam.gm.model.annotation.Column;
import com.epam.gm.model.annotation.Entity;
import com.epam.gm.model.annotation.ID;

@Entity("user_language")
public class UserLanguage {
	@ID("id")
	private Integer id;
	@Column("user_id")
	private Integer userId;
	private User user;
	@Column("lang_id")
	private Integer langId;
	private Language lang;
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
	public Integer getLangId() {
		return langId;
	}
	public void setLangId(Integer langId) {
		this.langId = langId;
	}
	@Override
	public String toString() {
		return "UserLanguage [id=" + id + ", userId=" + userId + ", langId="
				+ langId + "]";
	}

}
