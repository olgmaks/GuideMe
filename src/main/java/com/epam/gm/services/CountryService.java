package com.epam.gm.services;

import java.sql.SQLException;
import java.util.List;

import com.epam.gm.daolayer.CountryDao;
import com.epam.gm.model.Country;

public class CountryService {
	private CountryDao countryDao = new CountryDao();
	
	public List<Country> getAll() throws SQLException {
		return countryDao.getAll();
	}
}
