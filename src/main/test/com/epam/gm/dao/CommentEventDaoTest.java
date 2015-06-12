package com.epam.gm.dao;

import java.sql.SQLException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.gm.daolayer.CommentEventDao;
import com.epam.gm.model.CommentEvent;

public class CommentEventDaoTest {

    private static CommentEventDao commentEventDao;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
	commentEventDao = new CommentEventDao();
    }

    @Test
    public void test() throws SQLException {
	List<CommentEvent> commentEvents = commentEventDao.getAll();
	for (CommentEvent commentEvent : commentEvents) {
	    System.out.println(commentEvent);
	}
    }

}
