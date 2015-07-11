package com.epam.gm.daolayer;

import java.sql.SQLException;
import java.util.List;

import com.epam.gm.model.WithdrawMoney;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class WithdrawMoneyDao extends AbstractDao<WithdrawMoney> {

	public WithdrawMoneyDao() {
		super(WithdrawMoney.class);
	}

	public void saveWithDrawMoney(WithdrawMoney wm)
			throws IllegalArgumentException, IllegalAccessException,
			SQLException {
		super.save(wm);
	}

	public List<WithdrawMoney> getAllWithDrawMoneyByUserId(int id)
			throws SQLException {
		return super.getByField("user_id", id);
	}

}
