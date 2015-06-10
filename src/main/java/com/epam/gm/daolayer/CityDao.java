package com.epam.gm.daolayer;

import com.epam.gm.model.City;
import com.epam.gm.olgmaks.absractdao.dbcontrol.ConnectionManager;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class CityDao extends AbstractDao<City> {

    public CityDao() {
	super(ConnectionManager.getConnection(), City.class);
    }

}
