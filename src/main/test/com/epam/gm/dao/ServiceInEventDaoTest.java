package com.epam.gm.dao;

import java.sql.SQLException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.gm.daolayer.ServiceInEventDao;
import com.epam.gm.model.ServiceInEvent;

public class ServiceInEventDaoTest {

    private static ServiceInEventDao serviceInEventDao;

    @BeforeClass
    public static void bef() {
	serviceInEventDao = new ServiceInEventDao();
    }

    @Test
    public void test() throws SQLException {
	List<ServiceInEvent> serviceInEvents = serviceInEventDao.getAll();
	for (ServiceInEvent serviceInEvent : serviceInEvents) {
	    System.out.println(serviceInEvent);
	}
    }

}
