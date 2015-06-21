package com.epam.gm.services;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import com.epam.gm.calculators.EventCalculator;
import com.epam.gm.daolayer.EventDao;
import com.epam.gm.model.Event;


public class EventService {

	private EventDao eventDao;

	public EventService() {
		eventDao = new EventDao();
	}

	public void saveEvent(Event event) throws IllegalArgumentException,
			IllegalAccessException, SQLException {
		eventDao.saveEvent(event);
	}

	public List<Event> getAll() throws SQLException {
		return new EventDao().getAllEvents();
	}

	public Event getById(int eventId) throws SQLException {
		List<Event> list = eventDao.getByField("id", eventId);
		if (list.size() == 1) {
			return eventDao.getByField("id", eventId).get(0);
		} else
			return null;
	}

	 public List<Event> getUserEvents (Integer moderatorId) throws SQLException {
		  return eventDao.getByField("moderator_id", moderatorId);	 
	 }
	 
	 public List<Event> getAllActiveNotDeletedEvents() throws SQLException {
		return eventDao.getAllActiveNotDeletedEvents(); 
	 }
	 
	 public List<Event> getAllActiveNotDeletedUserEvents() throws SQLException {
		 return eventDao.getAllActiveNotDeletedUserEvents();
	 }
	 
	 public List<Event> getAllActiveNotDeletedGuideEvents() throws SQLException {
		 return eventDao.getAllActiveNotDeletedGuideEvents();
	 }
	 
	 public List<Event> getAllActiveNotDeletedGuideEventsInTheCity(Integer cityId) throws SQLException {
		 return eventDao.getAllActiveNotDeletedGuideEventsInTheCity(cityId);
	 }
	 
	  
	 
	 public static void main(String[] args) throws SQLException {
		// new EventService().getUserEvents(2).forEach(System.out::println);
		 
		 List<Event> events =  new EventService().getAllActiveNotDeletedGuideEventsInTheCity(9);
//		 System.out.println("before sort:");
//		 events.forEach(System.out::println);
//		 
//		 System.out.println("After:");
//		 
//		 //EventCalculator.sortEventsByPoints(events, null);
//		 EventCalculator.sortEventsByPoints(events, 1);
		 
		 for(Event e: events) {
			 System.out.println("" + e.getId() + " " + e.getName() + " : " + e.getPoints());
		 }
		 
	 }
}
