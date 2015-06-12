package com.epam.gm.dao;

import java.sql.SQLException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.gm.daolayer.RatingUserDao;
import com.epam.gm.model.RatingUser;

public class RatingUserTest {

    private static RatingUserDao ratingUserDao;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
	ratingUserDao = new RatingUserDao();
    }

    @Test
    public void test() throws SQLException {
	List<RatingUser> ratingUsers = ratingUserDao.getAll();
	for (RatingUser ratingUser : ratingUsers) {
	    System.out.println(ratingUser);
	}
    }

}
