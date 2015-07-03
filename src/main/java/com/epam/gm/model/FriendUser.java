package com.epam.gm.model;

import java.util.Comparator;

import com.epam.gm.olgmaks.absractdao.annotation.*;

/**
 * Created by OLEG on 16.06.2015.
 */
@Entity("friend_user")
public class FriendUser {

    @AutoGenerated
    @Column("id")
    private Integer id;

    @Column("user_id")
    private Integer userId;

    @Column("friend_id")
    private Integer friendId;

    @ForeignKey
    @OneToMany(field = "user_id", value = User.class)
    private User user;

    @ForeignKey
    @OneToMany(field = "friend_id", value = User.class)
    private User friend;
    
    private Double points;
    
    private Double rate;

    public FriendUser() {
    }

    public FriendUser(Integer userId, Integer friendId) {
        this.userId = userId;
        this.friendId = friendId;
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

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }
    
	public Double getPoints() {
		return points;
	}

	public void setPoints(Double points) {
		this.points = points;
	}
	
	
    public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	@Override
    public String toString() {
        return "FriendUser{" +
                "id=" + id +
                ", userId=" + userId +
                ", friendId=" + friendId +
                ", user=" + user +
                ", friend=" + friend +
                '}';
    }
    
	public static final Comparator<FriendUser> BY_POINTS = new Comparator<FriendUser>() {
		@Override
		public int compare(FriendUser first, FriendUser second) {
			return second.points.compareTo(first.points);
		}
	};
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FriendUser that = (FriendUser) o;

        if (friendId != null ? !friendId.equals(that.friendId) : that.friendId != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (friendId != null ? friendId.hashCode() : 0);
        return result;
    }
    

}
