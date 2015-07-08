package com.epam.gm.daolayer;

import java.sql.SQLException;

import com.epam.gm.model.PaidService;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class PaidServiceDao extends AbstractDao<PaidService> {

	public PaidServiceDao() {
		super(PaidService.class);
	}

	public void savePaidService(PaidService paidService)
			throws IllegalArgumentException, IllegalAccessException,
			SQLException {
		super.save(paidService);
	}

}
