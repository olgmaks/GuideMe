package com.epam.gm.model;

import java.util.Date;

import com.epam.gm.olgmaks.absractdao.annotation.Column;
import com.epam.gm.olgmaks.absractdao.annotation.Entity;
import com.epam.gm.olgmaks.absractdao.annotation.ForeignKey;
import com.epam.gm.olgmaks.absractdao.annotation.OneToMany;

@Entity("message_user")
public class MessageUser {

    @Column("id")
    private Integer id;
    @Column("sender_id")
    private Integer senderId;
    @Column("created_on")
    private Date createdOn;
    public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ForeignKey("id")
    @OneToMany(field = "sender_id", value = User.class)
    private User sender;
    @Column("user_id")
    private Integer userId;

    @ForeignKey("id")
    @OneToMany(field = "user_id", value = User.class)
    private User user;
    @Column("message")
    private String message;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public Integer getSenderId() {
	return senderId;
    }

    public void setSenderId(Integer senderId) {
	this.senderId = senderId;
    }

    public Integer getUserId() {
	return userId;
    }

    public void setUserId(Integer userId) {
	this.userId = userId;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    @Override
    public String toString() {
	return "MessageUser [id=" + id + ", senderId=" + senderId + ", sender="
		+ sender + ", userId=" + userId + ", user=" + user
		+ ", message=" + message + "]";
    }

    
    
}
