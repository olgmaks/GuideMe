package com.epam.gm.model;

import java.io.Serializable;

import com.epam.gm.olgmaks.absractdao.annotation.Column;
import com.epam.gm.olgmaks.absractdao.annotation.Entity;
import com.epam.gm.olgmaks.absractdao.annotation.ForeignKey;
import com.epam.gm.olgmaks.absractdao.annotation.OneToMany;

@Entity("service")
public class Service implements Serializable {
	@Column("id")
	private Integer id;
	@Column("guide_id")
	private Integer guideId;

	@ForeignKey
	@OneToMany(field = "guide_id", value = User.class)
	private User guide;
	@Column("name")
	private String name;
	@Column("price")
	private Double price;
	@Column("description")
	private String description;
	@Column("deleted")
	private int deleted;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGuideId() {
		return guideId;
	}

	public void setGuideId(Integer guideId) {
		this.guideId = guideId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isDeleted() {
		boolean newBoolean = false;
		if (deleted == 1) {
			newBoolean = true;
		}
		return newBoolean;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = 0;
		if (deleted == true) {
			this.deleted = 1;
		}
	}

	@Override
	public String toString() {
		return "Service [id=" + id + ", guideId=" + guideId + ", guide="
				+ guide + ", name=" + name + ", price=" + price
				+ ", description=" + description + ", deleted=" + deleted + "]";
	}

}
