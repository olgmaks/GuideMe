package com.epam.gm.model;

import java.util.Date;

import com.epam.gm.olgmaks.absractdao.annotation.Column;
import com.epam.gm.olgmaks.absractdao.annotation.Entity;
import com.epam.gm.olgmaks.absractdao.annotation.ForeignKey;
import com.epam.gm.olgmaks.absractdao.annotation.OneToMany;

@Entity("comment_user")
public class CommentUser {
    @Column("id")
    private Integer id;
    @Column("date")
    private Date date;
    @Column("commentator_id")
    private Integer commentatorId;

    @ForeignKey
    @OneToMany(field="commentator_id",value= User.class)
    private User Commentator;
    @Column("user_id")
    private Integer userId;

    @ForeignKey
    @OneToMany(field="user_id",value=User.class)
    private User user;
    @Column("comment")
    private String comment;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public Date getDate() {
	return date;
    }

    public void setDate(Date date) {
	this.date = date;
    }

    public Integer getCommentatoId() {
	return userId;
    }

    public void setCommentatoId(Integer userId) {
	this.userId = userId;
    }

    public Integer getUserId() {
	return userId;
    }

    public void setCommentatorId(Integer commentatorId) {
	this.commentatorId = commentatorId;
    }

    public String getComment() {
	return comment;
    }

    public void setComment(String comment) {
	this.comment = comment;
    }

    @Override
    public String toString() {
	return "CommentUser [id=" + id + ", date=" + date + ", commentatorId="
		+ commentatorId + ", Commentator=" + Commentator + ", userId="
		+ userId + ", user=" + user + ", comment=" + comment + "]";
    }



}
