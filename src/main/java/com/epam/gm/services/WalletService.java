package com.epam.gm.services;

import java.sql.SQLException;
import java.util.List;

import com.epam.gm.daolayer.WalletDao;
import com.epam.gm.model.Wallet;

public class WalletService {
	private WalletDao walletDao;

	public WalletService() {
		walletDao = new WalletDao();
	}

	public void saveWallet(Wallet wallet) throws IllegalArgumentException,
			IllegalAccessException, SQLException {
		walletDao.saveWallet(wallet);
	}

	public Wallet getByUserId(int userId) throws SQLException {
		List<Wallet> list = walletDao.getByField("user_id", userId);
		if (list.size() == 1) {
			return walletDao.getByField("user_id", userId).get(0);
		} else
			return null;
	}

}
