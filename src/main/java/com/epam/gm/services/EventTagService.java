package com.epam.gm.services;

import java.sql.SQLException;

import com.epam.gm.daolayer.EventTagDao;
import com.epam.gm.model.EventTag;


public class EventTagService {
	private EventTagDao dao = new EventTagDao();
	
	public void deleteAllEventTags(Integer eventId) throws IllegalAccessException, SQLException {
		dao.deleteAllEventTags(eventId);
	}
	
	public void save(EventTag eventTag) throws IllegalArgumentException, IllegalAccessException, SQLException {
		dao.save(eventTag);
	}
}
