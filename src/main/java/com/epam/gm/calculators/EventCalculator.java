package com.epam.gm.calculators;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.epam.gm.daolayer.RatingEventDao;
import com.epam.gm.model.Event;
import com.epam.gm.model.User;
import com.epam.gm.model.UserInEvent;
import com.epam.gm.services.EventService;
import com.epam.gm.services.RatingEventService;
import com.epam.gm.services.UserInEventService;
import com.epam.gm.services.UserService;
import com.epam.gm.util.Constants;

public class EventCalculator {
	
	//Models
	private Event event;
	private UserInEvent userInEvent;
	private User moderator;
	private List<UserInEvent> allUserInEvent;
	
	//Dao
	private EventService eventService = new EventService();
	private UserInEventService userInEventService = new UserInEventService();
	private UserService userService = new UserService();
	private RatingEventService ratingEventService = new RatingEventService();
	
	public EventCalculator(Integer id) throws SQLException {
		event = eventService.getById(id);
		allUserInEvent = userInEventService.getUsersByEventId(id);
		moderator = userService.getUserById(event.getModeratorId());
	}
	
	public boolean isActiveAndNotOutOfDate() {
		if(event.getDateTo().compareTo(new Date()) > 0 && "active".equals(event.getStatus())) {
			return true;
		}
		
		return false;
	}
	
	public boolean isModeratorUser() {
		return moderator.getUserTypeId() == Constants.USER_TYPE_USER;
	}
	
	public boolean isModeratorGuide() {
		return moderator.getUserTypeId() == Constants.USER_TYPE_GUIDE;
	}
	
	public int countOfParticipants() {
		return allUserInEvent.size();
	}
	
	
}
