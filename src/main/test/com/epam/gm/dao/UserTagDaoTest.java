package com.epam.gm.dao;

import java.sql.SQLException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.gm.daolayer.UserTagDao;
import com.epam.gm.model.UserTag;

public class UserTagDaoTest {

    private static UserTagDao userTagDao;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
	userTagDao = new UserTagDao();
    }

    @Test
    public void test() throws SQLException {
	List<UserTag> userTags = userTagDao.getAll();
	for (UserTag userTag : userTags) {
	    System.out.println(userTag);
	}
    }

}
