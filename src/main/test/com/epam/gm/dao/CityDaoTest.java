package com.epam.gm.dao;

import java.sql.SQLException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.epam.gm.daolayer.CityDao;
import com.epam.gm.model.City;
import com.epam.gm.services.CityService;

public class CityDaoTest {

	private static CityDao cityDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cityDao = new CityDao();
	}

	
	@Test
	public void test() throws SQLException {
		List<City> cities = cityDao.getAll();
		for (City city : cities) {
			System.out.println(city);
		}
	}

	@Ignore
	@Test
	public void test2() throws SQLException {
		List<City> cities = cityDao.getCitiesByCountryId(5);
		for (City city : cities) {
			System.out.println(city);
		}
	}
	
	@Ignore
	@Test
	public void test3() throws SQLException {
		System.out.println(new CityService().getCitiesByCountryId(5));

	}	
	
	@Ignore
	@Test
	public void test4() throws SQLException {
		System.out.println(cityDao.getCityById(5));

	}	
	
	@Ignore
	@Test
	public void test5() throws SQLException {
		List<City> cities = cityDao.getCitiesByPureId(1);
		for (City city : cities) {
			System.out.println(city);
		}
	}	
	
	@Ignore
	@Test
	public void test6() throws SQLException {
		System.out.println("Last pure:");
		System.out.println(cityDao.getLastPureId());
	}	

}
