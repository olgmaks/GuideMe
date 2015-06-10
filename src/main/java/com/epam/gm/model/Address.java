package com.epam.gm.model;

import com.epam.gm.olgmaks.absractdao.annotation.Column;
import com.epam.gm.olgmaks.absractdao.annotation.Entity;
import com.epam.gm.olgmaks.absractdao.annotation.ForeignKey;
import com.epam.gm.olgmaks.absractdao.annotation.OneToMany;

@Entity("address")
public class Address {

	@Column("id")
	private Integer id;
	@Column("address")
	private String address;
	@Column("city_id")
	private Integer cityId;

	@ForeignKey
	@OneToMany(field="city_id",value=City.class)
	private City city;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	@Override
	public String toString() {
	    return "Address [" + (id != null ? "id=" + id + ", " : "")
		    + (address != null ? "address=" + address + ", " : "")
		    + (cityId != null ? "cityId=" + cityId + ", " : "")
		    + (city != null ? "city=" + city : "") + "]";
	}



}
