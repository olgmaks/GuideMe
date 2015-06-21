package com.epam.gm.daolayer;

import com.epam.gm.model.Event;
import com.epam.gm.model.FriendUser;
import com.epam.gm.model.User;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventDao extends AbstractDao<Event> {
	
    private static final String GET_ACTIVE_NOT_DELETED = 
            "e WHERE e.deleted = 0 AND e.status = 'active'";
    
	public EventDao() {
		// gryn
		// super(ConnectionManager.getConnection(), Event.class);
		super(Event.class);
	}

	public void saveEvent(Event event) throws IllegalArgumentException,
			IllegalAccessException, SQLException {
		super.save(event);
	}

	public List<Event> getAllEvents() throws SQLException {
		return super.getAll();
	}

	public void deleteById(int eventId) throws IllegalAccessException,
			SQLException {
		deleteByField("id", eventId);
	}
	
	public List<Event> getAllActiveNotDeletedEvents() throws SQLException {
		
        List<Event> results = new ArrayList<>();
        String sql = GET_ACTIVE_NOT_DELETED;
        results = getWithCustomQuery(sql);
        
        return results;
	}
	
//	public static void main(String[] args) throws SQLException {
//		new EventDao().getAllActiveNotDeletedEvents().forEach(System.out::println);
//	}

}
