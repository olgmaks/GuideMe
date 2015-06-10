package com.epam.gm.dao;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.gm.daolayer.UserTypeDao;
import com.epam.gm.model.UserType;

public class UserTypeTest {

    private static UserTypeDao userTypeDao;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
	userTypeDao = new UserTypeDao();
    }

    @Test
    public void test() {
	List<UserType> userTypes = userTypeDao.getAll();
	for (UserType userType : userTypes) {
	    System.out.println(userType);
	}
    }

}
