package com.epam.gm.model;


import com.epam.gm.olgmaks.absractdao.annotation.Column;
import com.epam.gm.olgmaks.absractdao.annotation.Entity;

@Entity("language")
public class Language implements Comparable<Language> {

    @Column("id")
	private Integer id;
	@Column("short_name")
	private String key;
	@Column("name")
	private String name;
	@Column("localized")
	private Boolean localized;
	
	@Column("deleted")
	private Boolean deleted;
	
	@Column("locale")
	private String locale;

     

	@Override
	public String toString() {
		return "Language [id=" + id + ", key=" + key + ", name=" + name
				+ ", localized=" + localized + ", deleted=" + deleted
				+ ", locale=" + locale + "]";
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

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
	
	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	//gryn
	@Override
	public boolean equals(Object obj) {
		if(obj == null) 
			return false;
		
		if(obj instanceof Language) {
			return ((Language) obj).getName().equals(name);
		}
		
		return false;
	}
    
	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public int compareTo(Language other) {
		return name.compareTo(other.name);
	}
	

}
