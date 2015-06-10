package com.epam.gm.model;

import com.epam.gm.olgmaks.absractdao.annotation.Column;
import com.epam.gm.olgmaks.absractdao.annotation.Entity;
import com.epam.gm.olgmaks.absractdao.annotation.ForeignKey;
import com.epam.gm.olgmaks.absractdao.annotation.OneToMany;

@Entity("user_language")
public class UserLanguage {

    @Column("id")
    private Integer id;

    @Column("user_id")
    private Integer userId;

    @ForeignKey
    @OneToMany(field = "user_id", value = User.class)
    private User user;

    @Column("lang_id")
    private Integer langId;

    @ForeignKey
    @OneToMany(field = "lang_id", value = Language.class)
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
	return "UserLanguage [" + (id != null ? "id=" + id + ", " : "")
		+ (userId != null ? "userId=" + userId + ", " : "")
		+ (user != null ? "user=" + user + ", " : "")
		+ (langId != null ? "langId=" + langId + ", " : "")
		+ (lang != null ? "lang=" + lang : "") + "]";
    }

}
