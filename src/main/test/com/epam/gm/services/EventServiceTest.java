package com.epam.gm.services;

import com.epam.gm.model.Event;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by OLEG on 02.07.2015.
 */
public class EventServiceTest {

    private static final EventService eventService = new EventService();

    @Test
    public void getUserEvents () {
        try {
            List<Event> events = eventService.getUserFriendEvents(8);
            for (Event event : events) {
                System.out.println(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test@Ignore
    public void testUpdateAvatar(){
        try {
            eventService.updateEventAvatar(5,80);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
