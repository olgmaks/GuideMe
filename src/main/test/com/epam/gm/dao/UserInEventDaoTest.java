package com.epam.gm.dao;

import java.sql.SQLException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.gm.daolayer.UserInEventDao;
import com.epam.gm.model.UserInEvent;

public class UserInEventDaoTest {
    
    private static UserInEventDao userInEventDao;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
	userInEventDao = new UserInEventDao();
	
    }


    @Test
    public void test() throws SQLException {
	System.out.println("user in event test");
	List<UserInEvent> usersInEvents = userInEventDao.getAllUsersInEvents();
	for (UserInEvent userInEvent : usersInEvents) {
	    System.out.println(userInEvent);
	}
	
    }

}
