package com.epam.gm.model;

import com.epam.gm.olgmaks.absractdao.annotation.Column;
import com.epam.gm.olgmaks.absractdao.annotation.Entity;
import com.epam.gm.olgmaks.absractdao.annotation.ForeignKey;

/**
 * @author user
 *
 */
@Entity("User")
public class User {

    @ForeignKey("user_id")
    @Column("id")
    private Integer Id;
    @Column("last_name")
    private String lastName;
    @Column("first_name")
    private String firstName;
    @Column("email")
    private String email;
    @Column("sex")
    private String sex;
    @Column("user_type_id")
    private Integer userTypeId;
    private UserType userType;
    @Column("lang_id")
    private Integer langId;
    private Language lang;
    @Column("cell_number")
    private String cellNumber;
    @Column("facebook_id")
    private Integer facebookId;
    @Column("vk_id")
    private Integer vkId;
    @Column("is_active")
    private Boolean isActive;
    @Column("address_id")
    private Integer addressId;
    private Address address;
    @Column("password")
    private String password;

    public Integer getId() {
	return Id;
    }

    public void setId(Integer id) {
	Id = id;
    }

    public String getSex() {
	return sex;
    }

    public void setSex(String sex) {
	this.sex = sex;
    }

    public Integer getLangId() {
	return langId;
    }

    public void setLangId(Integer langId) {
	this.langId = langId;
    }

    public String getCellNumber() {
	return cellNumber;
    }

    public void setCellNumber(String cellNumber) {
	this.cellNumber = cellNumber;
    }

    public Integer getFacebookId() {
	return facebookId;
    }

    public void setFacebookId(Integer facebookId) {
	this.facebookId = facebookId;
    }

    public Integer getVkId() {
	return vkId;
    }

    public void setVkId(Integer vkId) {
	this.vkId = vkId;
    }

    public Boolean getIsActive() {
	return isActive;
    }

    public void setIsActive(Boolean isActive) {
	this.isActive = isActive;
    }

    public Integer getAddressId() {
	return addressId;
    }

    public void setAddressId(Integer addressId) {
	this.addressId = addressId;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

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

    @Override
    public String toString() {
	return "User [Id=" + Id + ", lastName=" + lastName + ", firstName="
		+ firstName + ", email=" + email + ", sex=" + sex
		+ ", userTypeId=" + userTypeId + ", langId=" + langId
		+ ", cellNumber=" + cellNumber + ", facebookId=" + facebookId
		+ ", vkId=" + vkId + ", isActive=" + isActive + ", addressId="
		+ addressId + ", password=" + password + "]";
    }

}
