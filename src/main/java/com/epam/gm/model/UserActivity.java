package com.epam.gm.model;

import java.util.Date;

public class UserActivity {
	private String activity;
	private String name;
	private Date date;
	private String act;
	private Integer idAct;
	
	
	public Integer getIdAct() {
		return idAct;
	}
	public void setIdAct(Integer idAct) {
		this.idAct = idAct;
	}
	public String getAct() {
		return act;
	}
	public void setAct(String act) {
		this.act = act;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
