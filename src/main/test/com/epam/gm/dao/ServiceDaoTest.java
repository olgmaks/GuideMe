package com.epam.gm.dao;

import java.sql.SQLException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.gm.daolayer.ServiceDao;
import com.epam.gm.model.Service;

public class ServiceDaoTest {

    private static ServiceDao serviceDao;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
	serviceDao = new ServiceDao();
    }

    @Test
    public void test() throws SQLException {
	List<Service> services = serviceDao.getAll();
	for (Service service : services) {
	    System.out.println(service);
	}
    }

}
