package com.epam.gm.services;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.epam.gm.daolayer.CityDao;
import com.epam.gm.model.City;

public class CityService {
	private CityDao cityDao = new CityDao();
	
	//gryn
	public List<City> getCitiesByCountryId(Integer country_id) throws SQLException {
		return cityDao.getCitiesByCountryId(country_id);
	}
	
	//gryn
	public Map<Integer, String> getMapOfCitiesByCountryId(Integer country_id) throws SQLException {
		Map<Integer, String> map = new HashMap<Integer, String>();
		
		List<City> cities = cityDao.getCitiesByCountryId(country_id);
		for(City city: cities) {
			map.put(city.getId(), city.getName());
		}
		
		return map;
	}
}
