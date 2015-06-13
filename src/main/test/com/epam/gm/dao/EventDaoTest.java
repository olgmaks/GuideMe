package com.epam.gm.dao;

import com.epam.gm.daolayer.EventDao;
import com.epam.gm.model.Event;
import com.epam.gm.sessionrepository.FormatUtils;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by OLEG on 13.06.2015.
 */
public class EventDaoTest {


    private static EventDao eventDao;

    @BeforeClass
    public static void before() {
        eventDao = new EventDao();
    }

    @Ignore
    @Test
    public void testGetAllEvents() throws SQLException {
        List<Event> events = eventDao.getAllEvents();
        for (Event event : events) {
            System.out.println(event);
        }
    }

    @Test//Passed
    public void testSaveEvent() throws ParseException, SQLException, IllegalAccessException {
        Event event = new Event();
        event.setName("Захід Фесливаль");
        event.setDescription("Zaxid festival near Ivano Frankovo");

        DateFormat dateFormat = FormatUtils.dateFormat;

        event.setDateFrom(dateFormat.parse("2015-06-18"));
        event.setDateTo(dateFormat.parse("2015-08-18"));
        event.setAddressId(9);
        event.setModeratorId(1);
        event.setStatus("active");

        eventDao.save(event);
    }

}
