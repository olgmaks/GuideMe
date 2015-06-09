package com.epam.gm.model;

import com.epam.gm.model.annotation.Column;
import com.epam.gm.model.annotation.Entity;
import com.epam.gm.model.annotation.ID;

@Entity("city")
public class City {
	@ID("id")
	private Integer id;
	@Column("name")
	private String name;
	@Column("country_id")
	private Integer countryId;
	
	private Country country;
	
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

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", countryId=" + countryId
				+ "]";
	}

}
