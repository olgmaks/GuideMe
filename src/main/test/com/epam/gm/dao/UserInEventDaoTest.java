package com.epam.gm.dao;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.gm.model.UserInEvent;
import com.epam.gm.olgmaks.absractdao.dbcontrol.ConnectionManager;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;
import com.epam.gm.olgmaks.absractdao.general.IDao;

public class UserInEventDaoTest {
    
    private static IDao<UserInEvent> userInEventDao;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
	userInEventDao = new AbstractDao<UserInEvent>(ConnectionManager.getConnection(), UserInEvent.class);
	
    }


    @Test
    public void test() {
	System.out.println("user in event test");
	List<UserInEvent> usersInEvents = userInEventDao.getAll();
	for (UserInEvent userInEvent : usersInEvents) {
	    System.out.println(userInEvent);
	}
	
    }

}
