package com.epam.gm.dao;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.gm.model.User;
import com.epam.gm.olgmaks.absractdao.dbcontrol.ConnectionManager;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;
import com.epam.gm.olgmaks.absractdao.general.IDao;

public class UserDaoTest {

    private static IDao<User> userDao;

    @BeforeClass
    public static void bef() {
	userDao = new AbstractDao<User>(ConnectionManager.getConnection(),
		User.class);
    }
    
    @Test
    public void testGetUsers () {
	List<User> users = userDao.getAll();
	
	for (User user : users) {
	    System.out.println(user);
	}
    }

}
