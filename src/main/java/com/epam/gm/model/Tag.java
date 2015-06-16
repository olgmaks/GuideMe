package com.epam.gm.model;

import com.epam.gm.olgmaks.absractdao.annotation.Column;
import com.epam.gm.olgmaks.absractdao.annotation.Entity;

@Entity("tag")
public class Tag implements Comparable<Tag>{

    @Column("id")
    private Integer id;
    @Column("name")
    private String name;
    
	@Column("deleted")
	private Boolean deleted;
	
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
    
    

    public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	@Override
    public String toString() {
	return "Tag [id=" + id + ", name=" + name + "]";
    }
    
	//gryn
	@Override
	public boolean equals(Object obj) {
		if(obj == null) 
			return false;
		
		if(obj instanceof Tag) {
			return ((Tag) obj).getName().equals(name);
		}
		
		return false;
	}
    
	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public int compareTo(Tag other) {
		return name.compareTo(other.name);
	}
}
