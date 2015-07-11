package com.epam.gm.model;

import com.epam.gm.olgmaks.absractdao.annotation.Column;
import com.epam.gm.olgmaks.absractdao.annotation.Entity;
import com.epam.gm.olgmaks.absractdao.annotation.ForeignKey;
import com.epam.gm.olgmaks.absractdao.annotation.OneToMany;

@Entity("raiting_user")
public class RatingUser {
    @Column("id")
    private Integer id;
    
    @Column("estimator_id")
    private Integer estimatorId;

    @ForeignKey
    @OneToMany(field = "estimator_id", value = User.class)
    private User estimator;
    
    @Column("user_id")
    private Integer userId;

    @ForeignKey
    @OneToMany(field = "user_id", value = User.class)
    private User user;

    @Column("mark")
    private Integer mark;

    public RatingUser () {}

    public RatingUser(Integer estimatorId, Integer userId, Integer mark) {
        this.estimatorId = estimatorId;
        this.userId = userId;
        this.mark = mark;
    }

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

    @Override
    public String toString() {
	return "RatingUser [id=" + id + ", estimatorId=" + estimatorId
		+ ", estimator=" + estimator + ", userId=" + userId + ", user="
		+ user + ", mark=" + mark + "]";
    }

    
    
}
