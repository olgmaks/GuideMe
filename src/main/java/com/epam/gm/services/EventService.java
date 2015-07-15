package com.epam.gm.services;

import com.epam.gm.daolayer.EventDao;
import com.epam.gm.model.Event;
import com.epam.gm.model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


public class EventService {

    private EventDao eventDao;

    public static EventService serve(){
        return new EventService();
    }

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

    public List<Event> getUserEvents(Integer moderatorId) throws SQLException {
        return eventDao.getByField("moderator_id", moderatorId);
    }

    public List<Event> getUserFriendEvents (Integer userId) throws SQLException {
        List<Event> results = eventDao.getUserFriendsEvents(userId);
        Collections.shuffle(results);
        return results;
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

    public List<Event> getAllNotDeletedEventsInTheCountry(Integer countryId) throws SQLException {
        return eventDao.getAllNotDeletedEventsInTheCountry(countryId);
    }

    public List<Event> getBySearchMap(Map<String, String> map, User user) throws SQLException {
        return eventDao.getBySearchMap(map, user);
    }

    public List<Event> getByTagName(String tagName) throws SQLException {
        return eventDao.getByTagName(tagName);
    }

    public void buildTagString(List<Event> list) throws SQLException {
        eventDao.buildTagString(list);
    }

    public void buildTagString(Event event) throws SQLException {
        List<Event> list = new ArrayList<Event>(1);
        list.add(event);

        buildTagString(list);
    }

    public void changeEventStatus(Integer id, String status) throws SQLException {
        eventDao.changeEventStatus(id, status);
    }

    public void updateEventAvatar(Integer eventId, Integer photoId) throws SQLException {
        eventDao.updateEventAvatar(eventId, photoId);
    }
    
    public void fixEventLimit(Integer id) throws SQLException {
    	eventDao.fixEventLimit(id);
    }
    
    public List<Event> getAllActiveEventsWhereUserModerator(Integer userId) throws SQLException {
    	return eventDao.getAllActiveEventsWhereUserModerator(userId);
    }
    
    public List<Event> getAllActiveEventsWhereUserNotModerator(Integer userId) throws SQLException {
    	return eventDao.getAllActiveEventsWhereUserNotModerator(userId);
    }

    public static void main(String[] args) throws SQLException {
        List<Event> topUserEvents = new EventService().getAllActiveNotDeletedUserEvents();
        //EventCalculator.sortEventsByPoints(topUserEvents, null);

//			if(topUserEvents.size() > Constants.TOP_NUMBER) {
//				topUserEvents = new ArrayList<Event>(topUserEvents.subList(0, Constants.TOP_NUMBER));
//			}

        for (Event e : topUserEvents) {
            System.out.println("" + e.getId() + " " + e.getName() + " : " + e.getPoints());
        }

    }
}
