package com.epam.gm.calculators;

import java.sql.SQLException;
import java.util.List;

import com.epam.gm.model.Event;
import com.epam.gm.model.FriendUser;
import com.epam.gm.model.Photo;
import com.epam.gm.model.RatingEvent;
import com.epam.gm.model.RatingUser;
import com.epam.gm.model.User;
import com.epam.gm.services.EventService;
import com.epam.gm.services.FriendUserService;
import com.epam.gm.services.PhotoService;
import com.epam.gm.services.RatingEventService;
import com.epam.gm.services.RatingUserService;
import com.epam.gm.services.UserService;

public class UserCalculator {
	//Models
	private User user;
	private User client;
	private List<RatingUser> allRatingUser;
	private List<FriendUser> favorites;
	private List<Event> allEvents;
	
	
	
	//Dao
	private UserService userService = new UserService();
	private RatingUserService ratingUserService = new RatingUserService();
	private FriendUserService friendService = new FriendUserService();
	private EventService eventService = new EventService();
	private RatingEventService ratingEventService = new RatingEventService();
	
	public UserCalculator(Integer userId, Integer clientId) throws SQLException {
		user = userService.getUserById(userId);
		allRatingUser = ratingUserService.getRatingByUser(userId);
		allEvents = eventService.getUserEvents(userId);
		
		
		if(clientId != null) {
			client = userService.getUserById(clientId);
			favorites = friendService.getUserFavorites(clientId);
		}
		
	}
	
	public int getSummaryRate() {
		int res = 0;
		
		for(RatingUser r: allRatingUser) {
			
			res += r.getMark();
		}
		
		return res;
	}

	public double getAverageRate() {
		double res = getSummaryRate();
		
		if(!allRatingUser.isEmpty()) {
			res = Math.round(res / allRatingUser.size());
		}
		
		return res;
	}
	
	public int getClientRate() {
		int res = 0;
		
		if(client == null) return res;

		for(RatingUser r: allRatingUser) {
			
			if(r.getEstimatorId() != client.getId()) continue; 
			
			res += r.getMark();
		}		
		return res;
	}
	
	public int countOfFriends() {
		try {
			return friendService.getUserFriends(user.getId()).size();
		} catch (SQLException e) {
			return 0;
		}
	}
	
	public boolean isClientFavorite() {
		boolean res = false;
		

		if(client != null) {
			for(FriendUser f: favorites) {
				
				if(f.getFriendId().equals(user.getId())) {
					res = true;
					break;
				}
			}
		}
		
		return res;
	}	
	
	private class RateResults {
		int summary = 0;
		double average = 0.0;
	}
	
	private RateResults getRates() {
		RateResults rateRes = new RateResults();
		int count = 0;
		
		for(Event e: allEvents) {
			try {
				List<RatingEvent> allRatingEvent = ratingEventService.getRatingByEvent(e.getId());
				
				for(RatingEvent r: allRatingEvent) {
					rateRes.summary += r.getMark();
					count++;
					
				}
				
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		}
		
		if(count > 0) rateRes.average =  (double) rateRes.summary / count;
 		
		
		return rateRes;
	}   
	
	public int getSummaryEventsRate() {
		return getRates().summary;
	}

	
	public double getAverageEventsRate() {
		return getRates().average;
	}
	
	public double calculate() {
		double res = (double) getAverageRate() * 200.0 + 
					 (double)getSummaryRate() * 5.0 + 
				     getSummaryEventsRate() + 
				     (double) getAverageEventsRate() * 10.0 + 
				     (double) getClientRate() * 20.0 + 
				     (double) countOfFriends() * 2.5
				     ;
		
		if(isClientFavorite()) {
			res *= 2.0;
		}
		
		return res;
	}
	
	
	
	public static void main(String[] args) throws SQLException {

		
		
//		List<FriendUser> favorites = new FriendUserService().getUserFavorites(3);
//		
//		for(FriendUser f: favorites) {
//			System.out.println(f.getFriendId());
//		}
		
		UserCalculator c = new UserCalculator(2, 8);
//		System.out.println(c.getSummaryRate());
//		System.out.println(c.getAverageRate());
//		System.out.println(c.getClientRate());
		//System.out.println(c.countOfFriends());
		//System.out.println(c.isClientFavorite());
		
		System.out.println("**********************");
		System.out.println("summary:" + c.getSummaryEventsRate());
		System.out.println("aver:" + c.getAverageEventsRate());
		System.out.println("final: " + c.calculate());
		
	}
}
