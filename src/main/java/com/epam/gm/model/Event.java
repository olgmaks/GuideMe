package com.epam.gm.model;

import java.sql.Date;

import com.epam.gm.model.annotation.Column;
import com.epam.gm.model.annotation.Entity;
import com.epam.gm.model.annotation.ID;

@Entity("event")
public class Event {
	@ID("id")
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
	@Column("status")
	private String status;
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
	@Override
	public String toString() {
		return "Event [id=" + id + ", name=" + name + ", description="
				+ description + ", dateFrom=" + dateFrom + ", dateTo=" + dateTo
				+ ", addressId=" + addressId + ", moderatorId=" + moderatorId
				+ ", status=" + status + "]";
	}
	

}
