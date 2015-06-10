package com.epam.gm.dao;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.gm.daolayer.CityDao;
import com.epam.gm.model.City;

public class CityDaoTest {

    private static CityDao cityDao;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
	cityDao = new CityDao();
    }

    @Test
    public void test() {
	List<City> cities = cityDao.getAll();
	for (City city : cities) {
	    System.out.println(city);
	}
    }

}
