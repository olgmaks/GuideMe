package com.epam.gm.dao;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.gm.daolayer.CommentUserDao;
import com.epam.gm.model.CommentUser;

public class CommentUserDaoTest {

    private static CommentUserDao commentUserDao;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
	commentUserDao = new CommentUserDao();
    }

    @Test
    public void test() {
	List<CommentUser> commentUsers = commentUserDao.getAll();
    
	for (CommentUser commentUser : commentUsers) {
	    System.out.println(commentUser);
	}
    }

}
