package com.epam.gm.daolayer;

import com.epam.gm.model.Country;
import com.epam.gm.olgmaks.absractdao.dbcontrol.ConnectionManager;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class CountryDao extends AbstractDao<Country> {

    public CountryDao() {
	super(ConnectionManager.getConnection(), Country.class);
    }

}
