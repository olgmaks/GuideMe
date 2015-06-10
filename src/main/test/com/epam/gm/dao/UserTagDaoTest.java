package com.epam.gm.dao;

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
    public void test() {
	List<UserTag> userTags = userTagDao.getAll();
	for (UserTag userTag : userTags) {
	    System.out.println(userTag);
	}
    }

}
