package com.epam.gm.model;

import java.sql.Date;

import com.epam.gm.model.annotation.Column;
import com.epam.gm.model.annotation.Entity;
import com.epam.gm.model.annotation.ID;

@Entity("comment_user")
public class CommentUser {
	@ID("id")
	private Integer id;
	@Column("date")
	private Date date;
	@Column("commentator_id")
	private Integer commentatorId;
	
	private User Commentator;
	@Column("user_id")
	private Integer userId;
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
		return "CommentUser [id=" + id + ", date=" + date + ", comentatoId="
				+ userId + ", commentatorId=" + commentatorId
				+ ", comment=" + comment + "]";
	}

}
