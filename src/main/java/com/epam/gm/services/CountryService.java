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
	
    //gryn
    public Country getCountryById(Integer id) throws SQLException {
        return countryDao.getCountryById(id);
    }
    
    //gryn
    //Получити країни аналоги в різних мовах:
    //Ukraine - Україна - Украина
    public List<Country> getCountriesByPureId(Integer pureId) throws SQLException {
    	return countryDao.getCountriesByPureId(pureId);
    }	
}
