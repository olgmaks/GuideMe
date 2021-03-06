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
	
	public static UserInEventService serve(){
	    return new UserInEventService();
	}

	public List<UserInEvent> getEventsByUserId(int userId) throws SQLException {
		return userInEventDao.getEventsByUserId(userId);
	}

	public List<UserInEvent> getUsersByEventId(int eventId) throws SQLException {
		return userInEventDao.getUsersByEventId(eventId);
	}
    
    public List<UserInEvent> getByEventAndUser(Integer eventId, Integer userId) throws SQLException {
    	return userInEventDao.getByEventAndUser(eventId, userId);
    }
    
    public List<UserInEvent> getByEventOnlyMembers(Integer eventId) throws SQLException {
    	return userInEventDao.getByEventOnlyMembers(eventId);
    }
    
    public List<UserInEvent> getByEventOnlyRequesters(Integer eventId) throws SQLException {
    	return userInEventDao.getByEventOnlyRequesters(eventId);
    }
    
    public List<User> getByEventOnlyMembersToUsers(Integer eventId) throws SQLException {
    	return  userInEventDao.getByEventOnlyMembersToUsers(eventId);
    }


	public void saveUserInEvent(UserInEvent userInEvent) throws IllegalArgumentException,
			IllegalAccessException, SQLException {
		userInEventDao.saveUserInEvent(userInEvent);
	}


    public void deleteUserFromEvent(Integer eventId, Integer userId) throws SQLException{
    	userInEventDao.deleteUserFromEvent(eventId, userId);
    }
    
 	public void joinToEvent(Integer eventId, Integer userId, Integer bedCount, String status) throws IllegalArgumentException, IllegalAccessException, SQLException {
 		userInEventDao.joinToEvent(eventId, userId, bedCount, status);
 	}	
 	
 	public void acceptToEvent(Integer eventId, Integer userId) throws IllegalArgumentException, IllegalAccessException, SQLException {
 		userInEventDao.acceptToEvent(eventId, userId);
 	}

 	public Boolean isMemberOfEvent (Integer userId, Integer eventId) throws SQLException {
 	    return userInEventDao.isMemberOfEvent(userId, eventId);
 	}
}
