package com.epam.gm.dao;

import java.sql.SQLException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.gm.daolayer.EventTagDao;
import com.epam.gm.model.EventTag;

public class EventTagDaoTest {

    private static EventTagDao eventTagDao;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
	eventTagDao = new EventTagDao();
    }

    @Test
    public void testEventTagDao() throws SQLException {
	List<EventTag> eventTags = eventTagDao.getAll();
	for (EventTag eventTag : eventTags) {
	    System.out.println(eventTag);
	}
    }

}
