package com.epam.gm.dao;

import com.epam.gm.daolayer.UserInEventDao;
import com.epam.gm.model.UserInEvent;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class UserInEventDaoTest {

    private static UserInEventDao userInEventDao;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        userInEventDao = new UserInEventDao();

    }

    @Ignore
    @Test
    public void test() throws SQLException {
        System.out.println("user in event test");
        List<UserInEvent> usersInEvents = userInEventDao.getAllUsersInEvents();
        for (UserInEvent userInEvent : usersInEvents) {
            System.out.println(userInEvent);
        }

    }
    @Ignore
    @Test
    public void testGetEventsForUser() throws SQLException {
        List<UserInEvent> usersInEvents = userInEventDao.getEventsByUserId(8);
        for (UserInEvent userInEvent : usersInEvents) {
            System.out.println(userInEvent);
        }
    }
    @Test
    public void testIsMemberOfEvent(){
	try {
	    System.out.println("test is member of event");
	    System.out.println(userInEventDao.isMemberOfEvent(8, 5));
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    

    public void testGetUsersOnEvent() {
    }

}
