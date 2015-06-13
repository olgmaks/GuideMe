package com.epam.gm.model;

import com.epam.gm.olgmaks.absractdao.annotation.Column;
import com.epam.gm.olgmaks.absractdao.annotation.Entity;

@Entity("country")
public class Country {
    @Column("id")
    private Integer id;
    @Column("name")
    private String name;
    
	@Column("local_id")
	private Integer localId;
	
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
    
	public Integer getLocalId() {
		return localId;
	}

	public void setLocalId(Integer localId) {
		this.localId = localId;
	}
	
    @Override
    public String toString() {
	return "Country [" + (id != null ? "id=" + id + ", " : "")
		+ (name != null ? "name=" + name : "") + "]";
    }
    
    
}
