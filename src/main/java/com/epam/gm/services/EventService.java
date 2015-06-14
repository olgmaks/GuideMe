package com.epam.gm.services;

import java.sql.SQLException;
import java.util.List;

import com.epam.gm.daolayer.EventDao;
import com.epam.gm.model.Event;

public class EventService {
	public List<Event> getAll() throws SQLException{
		return new EventDao().getAllEvents();
	}
}
