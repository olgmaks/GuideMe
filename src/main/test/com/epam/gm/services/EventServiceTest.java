package com.epam.gm.services;

import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by OLEG on 02.07.2015.
 */
public class EventServiceTest {

    private static final EventService eventService = new EventService();

    @Test
    public void testUpdateAvatar(){
        try {
            eventService.updateEventAvatar(5,80);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
