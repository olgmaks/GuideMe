package com.epam.gm.model;

import java.util.List;

import com.epam.gm.olgmaks.absractdao.annotation.Column;
import com.epam.gm.olgmaks.absractdao.annotation.Entity;
import com.epam.gm.olgmaks.absractdao.annotation.ForeignKey;
import com.epam.gm.olgmaks.absractdao.annotation.OneToMany;

@Entity("country")
public class Country {
    @Column("id")
    private Integer id;
    @Column("name")
    private String name;
    
	@Column("local_id")
	private Integer localId;
	
	@Column("pure_id")
	private Integer pureId;
	
	 @ForeignKey
	    @OneToMany(field="local_id", value=Language.class)
	private Language language;
	 
	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
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

	@Override
	public String toString() {
		return "Country [id=" + id + ", name=" + name + ", localId=" + localId
				+ ", pureId=" + pureId + "]";
	}

	
    
}
