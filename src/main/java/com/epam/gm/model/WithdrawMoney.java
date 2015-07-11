package com.epam.gm.model;

import java.util.Date;

import com.epam.gm.olgmaks.absractdao.annotation.Column;
import com.epam.gm.olgmaks.absractdao.annotation.Entity;

@Entity("withdrawmoney")
public class WithdrawMoney {
	@Column("id")
	Integer id;
	@Column("user_id")
	Integer userId;
	@Column("money_amount")
	Double moneyAmount;
	@Column("withdraw_date")
	Date withdrawDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Double getMoneyAmount() {
		return moneyAmount;
	}

	public void setMoneyAmount(Double moneyAmount) {
		this.moneyAmount = moneyAmount;
	}

	public Date getWithdrawDate() {
		return withdrawDate;
	}

	public void setWithdrawDate(Date withdrawDate) {
		this.withdrawDate = withdrawDate;
	}

	@Override
	public String toString() {
		return "WithdrawMoney [id=" + id + ", userId=" + userId
				+ ", moneyAmount=" + moneyAmount + ", withdrawDate="
				+ withdrawDate + "]";
	}

}
