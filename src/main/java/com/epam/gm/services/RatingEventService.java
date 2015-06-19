package com.epam.gm.services;

import java.sql.SQLException;
import java.util.List;

import com.epam.gm.daolayer.RatingEventDao;
import com.epam.gm.model.RatingEvent;

public class RatingEventService {
	private RatingEventDao dao = new RatingEventDao();
	
	public List<RatingEvent> getRatingByEvent(Integer eventId) throws SQLException {
		return dao.getRatingByEvent(eventId);
	}
	
}
