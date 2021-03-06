package com.epam.gm.model;

import com.epam.gm.olgmaks.absractdao.annotation.Column;
import com.epam.gm.olgmaks.absractdao.annotation.Entity;
import com.epam.gm.olgmaks.absractdao.annotation.ForeignKey;
import com.epam.gm.olgmaks.absractdao.annotation.OneToMany;

@Entity("city")
public class City {
    
    @Column("id")
    private Integer id;
    
    @Column("name")
    private String name;
    
    @Column("country_id")
    private Integer countryId;
    
	@Column("local_id")
	private Integer localId;
	
	@Column("pure_id")
	private Integer pureId;

	@Column("deleted")
	private boolean deleted;
	
	@Column("lattitude")
	private Double lattitude;
	
	@Column("longitude")
	private Double longitude;
	
    public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@ForeignKey
    @OneToMany(field="country_id", value=Country.class)
    private Country country;

    public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
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

    public Integer getCountryId() {
	return countryId;
    }

    public void setCountryId(Integer countryId) {
	this.countryId = countryId;
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
		return "City [id=" + id + ", name=" + name + ", lattitude=" + lattitude + ", longitude=" + longitude + "]";
	}



 

}
