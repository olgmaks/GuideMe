package com.epam.gm.model;

import com.epam.gm.model.annotation.Column;
import com.epam.gm.model.annotation.Entity;
import com.epam.gm.model.annotation.ID;

@Entity("service")
public class Service {
	@ID("id")
	private Integer id;
	@Column("guide_id")
	private Integer guideId;
	@Column("name")
	private String name;
	@Column("price")
	private Double price;

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

	@Override
	public String toString() {
		return "Service [id=" + id + ", guideId=" + guideId + ", name=" + name
				+ ", price=" + price + "]";
	}

}
