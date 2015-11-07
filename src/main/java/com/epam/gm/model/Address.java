package com.epam.gm.model;

import java.util.StringJoiner;

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
	@Column("local_id")
	private Integer localId;
	@Column("pure_id")
	private Integer pureId;
	
	@Column("lattitude")
	private Double lattitude;
	
	@Column("longitude")
	private Double longitude;	

	@ForeignKey
	@OneToMany(field = "city_id", value = City.class)
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

	public Integer getLocalId() {
		return localId;
	}

	public void setLocalId(Integer localId) {
		this.localId = localId;
	}

	public Integer getPureId() {
		return pureId;
	}

	public void setPureId(Integer pureId) {
		this.pureId = pureId;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	
	public Double getLattitude() {
		return lattitude;
	}

	public void setLattitude(Double lattitude) {
		this.lattitude = lattitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		StringJoiner res = new StringJoiner(", ");
		if(city != null) {
			res.add(city.getName());
		}
		if(address != null && address.trim().length() > 0)
			res.add(address);
		
		return res.toString() + " lat: " + lattitude + " long: " + longitude;			
	}

	
}
