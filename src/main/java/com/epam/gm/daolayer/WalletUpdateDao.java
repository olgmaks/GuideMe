package com.epam.gm.daolayer;

import java.sql.SQLException;

import com.epam.gm.model.WalletUpdate;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class WalletUpdateDao extends AbstractDao<WalletUpdate> {

	public WalletUpdateDao() {
		super(WalletUpdate.class);

	}

	public void saveWalletUpdate(WalletUpdate walletUpdate)
			throws IllegalArgumentException, IllegalAccessException,
			SQLException {
		super.save(walletUpdate);
	}

}
