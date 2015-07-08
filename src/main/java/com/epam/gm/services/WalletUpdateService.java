package com.epam.gm.services;

import java.sql.SQLException;
import java.util.List;

import com.epam.gm.daolayer.WalletDao;
import com.epam.gm.daolayer.WalletUpdateDao;
import com.epam.gm.model.Wallet;
import com.epam.gm.model.WalletUpdate;

public class WalletUpdateService {

	private WalletUpdateDao walletUpdateDao;

	public WalletUpdateService() {
		walletUpdateDao = new WalletUpdateDao();
	}

	public void saveWalletUpdate(WalletUpdate walletUpdate)
			throws IllegalArgumentException, IllegalAccessException,
			SQLException {
		walletUpdateDao.saveWalletUpdate(walletUpdate);
	}

	public WalletUpdate getByUserId(int userId) throws SQLException {
		List<WalletUpdate> list = walletUpdateDao.getByField("user_id", userId);
		if (list.size() == 1) {
			return walletUpdateDao.getByField("user_id", userId).get(0);
		} else
			return null;
	}

	public WalletUpdate getByWalletId(int walletId) throws SQLException {
		List<WalletUpdate> list = walletUpdateDao.getByField("wallet_id",
				walletId);
		if (list.size() == 1) {
			return walletUpdateDao.getByField("wallet_id", walletId).get(0);
		} else
			return null;
	}

}
