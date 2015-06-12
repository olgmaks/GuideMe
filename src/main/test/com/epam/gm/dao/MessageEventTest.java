package com.epam.gm.dao;

import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.gm.daolayer.MessageEventDao;
import com.epam.gm.model.MessageEvent;

public class MessageEventTest {
    
    
    private static MessageEventDao messageEventDao;

    @BeforeClass
    public static void bef() {
	messageEventDao = new MessageEventDao();
    }


    @Test
    public void test() throws SQLException {
	
	for (MessageEvent m : messageEventDao.getAllMessagesEvents()) {
	    System.out.println(m);
	}
    }

}
