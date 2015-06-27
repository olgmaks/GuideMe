package com.epam.gm.services;

import java.sql.SQLException;
import java.util.List;

import com.epam.gm.daolayer.CommentEventDao;
import com.epam.gm.model.CommentEvent;

public class CommentEventService {
	private CommentEventDao ceDao ;
	
	public CommentEventService(){
		ceDao = new CommentEventDao();
	}
	public List<CommentEvent> getByEventId(int eventId) throws SQLException{
		return ceDao.getByEventId(eventId);
	}
	public void save(CommentEvent comment) throws IllegalArgumentException, IllegalAccessException, SQLException{
		ceDao.save(comment);
	}
	
	public void deleteById(Integer id) throws IllegalAccessException, SQLException {
		ceDao.deleteByField("id", id);
	}
}
