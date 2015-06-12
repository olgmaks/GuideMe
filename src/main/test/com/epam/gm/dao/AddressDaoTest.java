package com.epam.gm.dao;

import java.sql.SQLException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.gm.daolayer.AddressDao;
import com.epam.gm.model.Address;

public class AddressDaoTest {

    private static AddressDao addressDao;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
	addressDao = new AddressDao();
    }

    @Test
    public void test() throws SQLException {
	List<Address> addresses = addressDao.getAll();
	for (Address address : addresses) {
	    System.out.println(address);
	}
    }

}
