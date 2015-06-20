package com.epam.gm.calculators;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.epam.gm.daolayer.RatingEventDao;
import com.epam.gm.model.Event;
import com.epam.gm.model.FriendUser;
import com.epam.gm.model.Photo;
import com.epam.gm.model.RatingEvent;
import com.epam.gm.model.RatingUser;
import com.epam.gm.model.Tag;
import com.epam.gm.model.User;
import com.epam.gm.model.UserInEvent;
import com.epam.gm.services.EventService;
import com.epam.gm.services.FriendUserService;
import com.epam.gm.services.PhotoService;
import com.epam.gm.services.RatingEventService;
import com.epam.gm.services.RatingUserService;
import com.epam.gm.services.TagService;
import com.epam.gm.services.UserInEventService;
import com.epam.gm.services.UserService;
import com.epam.gm.util.Constants;

public class EventCalculator {
	
	//Models
	private Event event;
	private UserInEvent userInEvent;
	private User moderator;
	private List<UserInEvent> allUserInEvent;
	private List<RatingEvent> allRatingEvent;
	private List<Photo> allPhotos;
	private User user = null;
	private List<FriendUser> favorites;
	UserCalculator userCalculator;
	private List<Tag> allTags;
	
	
	//Dao
	private EventService eventService = new EventService();
	private UserInEventService userInEventService = new UserInEventService();
	private UserService userService = new UserService();
	private RatingEventService ratingEventService = new RatingEventService();
	private PhotoService photoService = new PhotoService();
	private FriendUserService friendService = new FriendUserService();
	private TagService tagService = new TagService();
	
	
	
	public EventCalculator(Integer id, Integer userId) throws SQLException {
		event = eventService.getById(id);
		allUserInEvent = userInEventService.getUsersByEventId(id);
		moderator = userService.getUserById(event.getModeratorId());
		allRatingEvent = ratingEventService.getRatingByEvent(id);
		allPhotos = photoService.getEventPhotos(id);
		allTags = tagService.getAllEventTags(id);
		
		userCalculator = new UserCalculator(moderator.getId(), userId);
		
		if(userId != null) {
			user = userService.getUserById(userId);
			favorites = friendService.getUserFavorites(userId);
			
			
		}
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
	
	public int countOfApprovedParticipants() {
		int res = 0;
		
		for(UserInEvent u: allUserInEvent) {
			if(u.getIsMember()) res++;
		}
		
		return res;
	}
	
	public int getSummaryRate() {
		int res = 0;
		
		for(RatingEvent r: allRatingEvent) {
			
			res += r.getMark();
		}
		
		return res;
	}

	public double getAverageRate() {
		double res = getSummaryRate();
		
		if(!allRatingEvent.isEmpty()) {
			res = Math.round(res / allRatingEvent.size());
		}
		
		return res;
	}
	
	public int countOfPhotos() {
		return allPhotos.size();
	}
	
	public int countOfModeratorFriends() {
		try {
			return friendService.getUserFriends(moderator.getId()).size();
		} catch (SQLException e) {
			return 0;
		}
	}
	
	public int getTags() {
		if(allTags.isEmpty()) return 0;
		
		return 1;
	}
	
	public double calculate() {
		double res = (double) getAverageRate() * 200.0 + 
				     getSummaryRate() * 5.0 + 
				     (double) countOfPhotos() * 20.0  +
				     (double) countOfParticipants() * 0.5 +
				     (double) countOfApprovedParticipants() * 0.2 +
				     (double) countOfModeratorFriends() * 2.5 + 
				     (double) userCalculator.getSummaryRate() * 2.5 +
				     (double) getTags() * 300 +
				     userCalculator.getAverageRate() * 100;
		
		if(isModeratorFavorite()) {
			res *= 2.0;
		}
		
		return res;
	}
	
	public boolean isModeratorFavorite() {
		boolean res = false;
		

		if(user != null) {
			for(FriendUser f: favorites) {
				
				if(f.getFriendId().equals(moderator.getId())) {
					res = true;
					break;
				}
			}
		}
		
		return res;
	}
	
	
	public static void main(String[] args) throws SQLException {
		//EventCalculator e = new EventCalculator(1, 8);
		EventCalculator e = new EventCalculator(1, null);
		System.out.println(e.isModeratorFavorite());
		
		
//		System.out.println(e.countOfParticipants());
//		System.out.println(e.countOfApprovedParticipants());
//		System.out.println(e.getSummaryRate());
//		System.out.println(e.getAverageRate());
		
		System.out.println(e.calculate());
		
//		List<Event> events = new EventService().getAll();
//		for(Event event: events) {
//			//System.out.println(event.getName() + ": " + new EventCalculator(event.getId(), 8).calculate() );
//		}
	}
	
}
