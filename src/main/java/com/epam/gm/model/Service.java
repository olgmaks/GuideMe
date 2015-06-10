package com.epam.gm.model;

import com.epam.gm.olgmaks.absractdao.annotation.Column;
import com.epam.gm.olgmaks.absractdao.annotation.Entity;
import com.epam.gm.olgmaks.absractdao.annotation.ForeignKey;
import com.epam.gm.olgmaks.absractdao.annotation.OneToMany;

@Entity("service")
public class Service {
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
