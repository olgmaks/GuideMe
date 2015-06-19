package com.epam.gm.model;

import java.sql.Timestamp;

import com.epam.gm.olgmaks.absractdao.annotation.Column;
import com.epam.gm.olgmaks.absractdao.annotation.Entity;

@Entity("user_forgot_password")
public class ForgotPassword {

	@Column("id")
	int id;
	@Column("email")
	String email;
	@Column("code")
	String code;
	@Column("date")
	Timestamp timestamp;
	@Column("is_available")
	int isAvailable;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public boolean getIsAvailable() {
		boolean newBoolean = false;
		if (isAvailable == 1) {
			newBoolean = true;
		}
		return newBoolean;
	}

	public void setIsAvailable(boolean isAvailable) {
		int newInt = 0;
		if (isAvailable == true) {
			newInt = 1;
		}
		this.isAvailable = newInt;
	}

	@Override
	public String toString() {
		return "ForgotPassword [id=" + id + ", email=" + email + ", code="
				+ code + ", timestamp=" + timestamp + "]";
	}

}
