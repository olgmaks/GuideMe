package com.epam.gm.daolayer;

import java.sql.SQLException;

import com.epam.gm.model.Event;
import com.epam.gm.model.Wallet;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class WalletDao extends AbstractDao<Wallet> {

	public WalletDao() {
		super(Wallet.class);

	}

	public void saveWallet(Wallet wallet) throws IllegalArgumentException,
			IllegalAccessException, SQLException {
		super.save(wallet);
	}

}
