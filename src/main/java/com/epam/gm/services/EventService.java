package com.epam.gm.services;

import java.sql.SQLException;
import java.util.List;

import com.epam.gm.daolayer.EventDao;
import com.epam.gm.model.Event;
import com.epam.gm.model.User;

public class EventService {

	private EventDao eventDao;

	public EventService () {
		eventDao = new EventDao();
	}
	public void saveEvent(Event event) throws IllegalArgumentException, IllegalAccessException, SQLException{
		eventDao.saveEvent(event);
	    }

	public List<Event> getAll() throws SQLException{
		return new EventDao().getAllEvents();
	}

//	public List<Event> getUserEvents (int userId) {
//		return eventDao.getUserEvents(userId);
//	}
}
