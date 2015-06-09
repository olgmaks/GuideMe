package com.epam.gm.model;

import java.sql.Date;

import com.epam.gm.model.annotation.Entity;

@Entity("User")
public class User {
private String lastName;
private String firstName;
private Date birtDate;
private String email;
private Integer userTypeId;
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public Date getBirtDate() {
	return birtDate;
}
public void setBirtDate(Date birtDate) {
	this.birtDate = birtDate;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public Integer getUserTypeId() {
	return userTypeId;
}
public void setUserTypeId(Integer userTypeId) {
	this.userTypeId = userTypeId;
}
}
