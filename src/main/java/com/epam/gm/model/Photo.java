package com.epam.gm.model;

import com.epam.gm.olgmaks.absractdao.annotation.Column;
import com.epam.gm.olgmaks.absractdao.annotation.Entity;

@Entity("photo")
public class Photo {
    @Column("id")
    private Integer id;
    @Column("path")
    private String path;
    @Column("owner_type")
    private String ownerType;
    @Column("owner_id")
    private Integer ownerId;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getPath() {
	return path;
    }

    public void setPath(String path) {
	this.path = path;
    }

    public String getOwnerType() {
	return ownerType;
    }

    public void setOwnerType(String ownerType) {
	this.ownerType = ownerType;
    }

    public Integer getOwnerId() {
	return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
	this.ownerId = ownerId;
    }

    @Override
    public String toString() {
	return "Photo [id=" + id + ", path=" + path + ", ownerType="
		+ ownerType + ", ownerId=" + ownerId + "]";
    }
    
    

}
