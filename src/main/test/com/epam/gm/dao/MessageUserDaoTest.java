package com.epam.gm.dao;

import java.sql.SQLException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.gm.daolayer.MessageUserDao;
import com.epam.gm.model.MessageUser;

public class MessageUserDaoTest {
    
    private static MessageUserDao messageUserDao;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
	messageUserDao = new MessageUserDao();
    }

    @Test
    public void testGetAll() throws SQLException {
	List<MessageUser>  messageUsers = messageUserDao.getAll();
	for (MessageUser messageUser : messageUsers) {
	    System.out.println(messageUser);
	}
    }

}
