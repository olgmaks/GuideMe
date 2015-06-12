package com.epam.gm.dao;

import java.sql.SQLException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.gm.daolayer.CountryDao;
import com.epam.gm.model.Country;

public class CountryDaoTest {

    private static CountryDao countryDao;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
	countryDao = new CountryDao();
    }

    @Test
    public void testGetAll() throws SQLException {
	List<Country> countries = countryDao.getAll();
	for (Country country : countries) {
	    System.out.println(country);
	}
    }

}
