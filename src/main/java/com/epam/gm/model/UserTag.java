package com.epam.gm.model;

import com.epam.gm.olgmaks.absractdao.annotation.Column;
import com.epam.gm.olgmaks.absractdao.annotation.Entity;
import com.epam.gm.olgmaks.absractdao.annotation.ForeignKey;
import com.epam.gm.olgmaks.absractdao.annotation.OneToMany;

@Entity("user_tag")
public class UserTag {

    @Column("id")
    private Integer id;

    @Column("user_id")
    private Integer userId;

    @ForeignKey
    @OneToMany(field = "user_id", value = User.class)
    private User user;

    @Column("tag_id")
    private Integer tagId;

    @ForeignKey
    @OneToMany(field = "tag_id", value = Tag.class)
    private Tag tag;



    @Override
    public String toString() {
	return "UserTag [" + (id != null ? "id=" + id + ", " : "")
		+ (userId != null ? "userId=" + userId + ", " : "")
		+ (tagId != null ? "tagId=" + tagId + ", " : "")
		+ (tag != null ? "tag=" + tag : "") + "]";
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
