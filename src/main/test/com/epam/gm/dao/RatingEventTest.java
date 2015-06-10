package com.epam.gm.dao;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.gm.daolayer.RatingEventDao;
import com.epam.gm.model.RatingEvent;

public class RatingEventTest {

    private static RatingEventDao ratingEventDao;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
	ratingEventDao = new RatingEventDao();
    }

    @Test
    public void test() {
	List<RatingEvent> ratingEvents = ratingEventDao.getAll();
	for (RatingEvent ratingEvent : ratingEvents) {
	    System.out.println(ratingEvent);
	}
    }

}
