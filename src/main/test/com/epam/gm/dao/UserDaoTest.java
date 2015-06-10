package com.epam.gm.dao;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.gm.daolayer.UserDao;
import com.epam.gm.model.User;

public class UserDaoTest {

    private static UserDao userDao;

    @BeforeClass
    public static void bef() {
	userDao = new UserDao();
    }

    @Test
    public void testGetUsers() {
	List<User> users = userDao.getAllUsers();

	for (User user : users) {
	    System.out.println(user);
	}
    }

}
