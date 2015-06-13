package com.epam.gm.daolayer;

import java.sql.SQLException;
import java.util.List;

import com.epam.gm.model.*;

import com.epam.gm.olgmaks.absractdao.dbcontrol.ConnectionManager;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class CityDao extends AbstractDao<City> {

	public CityDao() {
		super(ConnectionManager.getConnection(), City.class);
	}

	// gryn
	public List<City> getCitiesByCountryId(Integer country_id) throws SQLException {
		List<City> result = getByField("country_id", country_id);
		return result;
	}
}
