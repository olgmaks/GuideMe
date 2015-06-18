package com.epam.gm.model;

import java.util.Date;

import com.epam.gm.olgmaks.absractdao.annotation.*;

@Entity("event")
public class Event {
	@AutoGenerated
	@Column("id")
	private Integer id;
	@Column("name")
	private String name;
	@Column("description")
	private String description;
	@Column("date_from")
	private Date dateFrom;
	@Column("date_to")
	private Date dateTo;
	@Column("address_id")
	private Integer addressId;
	@Column("moderator_id")
	private Integer moderatorId;
	@Column("participants_limit")
	private Integer participants_limit;

	@ForeignKey("id")
	@OneToMany(field = "moderator_id", value = User.class)
	private User moderator;
	@Column("status")
	private String status;

	@ForeignKey("id")
	@OneToMany(field = "address_id", value = Address.class)
	private Address address;

	@Column("avatar_id")
	private Integer avatarId;

	@ForeignKey
	@OneToMany(field = "avatar_id", value = Photo.class)
	private Photo avatar;

	public Integer getAvatarId() {
		return avatarId;
	}

	public void setAvatarId(Integer avatarId) {
		this.avatarId = avatarId;
	}

	public Photo getAvatar() {
		return avatar;
	}

	public void setAvatar(Photo avatar) {
		this.avatar = avatar;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public Integer getModeratorId() {
		return moderatorId;
	}

	public void setModeratorId(Integer moderatorId) {
		this.moderatorId = moderatorId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getParticipants_limit() {
		return participants_limit;
	}

	public void setParticipants_limit(Integer participants_limit) {
		this.participants_limit = participants_limit;
	}

	public User getModerator() {
		return moderator;
	}

	public void setModerator(User moderator) {
		this.moderator = moderator;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", name=" + name + ", description="
				+ description + ", dateFrom=" + dateFrom + ", dateTo=" + dateTo
				+ ", addressId=" + addressId + ", moderatorId=" + moderatorId
				+ ", participants_limit=" + participants_limit + ", moderator="
				+ moderator + ", status=" + status + ", address=" + address
				+ "]";
	}

}
