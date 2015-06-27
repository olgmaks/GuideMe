package com.epam.gm.services;

import com.epam.gm.daolayer.UserInEventDao;
import com.epam.gm.model.User;
import com.epam.gm.model.UserInEvent;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by OLEG on 15.06.2015.
 */
public class UserInEventService {

    private UserInEventDao userInEventDao;

    public UserInEventService() {
        userInEventDao = new UserInEventDao();
    }

    public List<UserInEvent> getEventsByUserId (int userId) throws SQLException {
        return userInEventDao.getEventsByUserId(userId);
    }

    public List<UserInEvent> getUsersByEventId(int eventId) throws SQLException {
        return  userInEventDao.getUsersByEventId(eventId);
    }
    
    public List<UserInEvent> getByEventAndUser(Integer eventId, Integer userId) throws SQLException {
    	return userInEventDao.getByEventAndUser(eventId, userId);
    }
    
    public List<UserInEvent> getByEventOnlyMembers(Integer eventId) throws SQLException {
    	return userInEventDao.getByEventOnlyMembers(eventId);
    }
    
    public List<User> getByEventOnlyMembersToUsers(Integer eventId) throws SQLException {
    	return  userInEventDao.getByEventOnlyMembersToUsers(eventId);
    }

    
    
}
