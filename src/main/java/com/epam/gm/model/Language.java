package com.epam.gm.model;

import com.epam.gm.model.annotation.Column;
import com.epam.gm.model.annotation.Entity;
import com.epam.gm.model.annotation.ID;

@Entity("language")
public class Language {
	@ID("id")
	private Integer id;
	@Column("key")
	private String key;
	@Column("name")
	private String name;
	@Column("localized")
	private Boolean localized;

	@Override
	public String toString() {
		return "Language [id=" + id + ", key=" + key + ", name=" + name
				+ ", localized=" + localized + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getLocalized() {
		return localized;
	}

	public void setLocalized(Boolean localized) {
		this.localized = localized;
	}

}
