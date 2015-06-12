package com.epam.gm.dao;

import java.sql.SQLException;
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
    public void test() throws SQLException {
	List<CommentUser> commentUsers = commentUserDao.getAll();
    
	for (CommentUser commentUser : commentUsers) {
	    System.out.println(commentUser);
	}
    }

}
