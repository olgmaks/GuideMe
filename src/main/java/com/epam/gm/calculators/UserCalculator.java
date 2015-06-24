package com.epam.gm.calculators;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.epam.gm.model.Address;
import com.epam.gm.model.Event;
import com.epam.gm.model.FriendUser;
import com.epam.gm.model.Language;
import com.epam.gm.model.Photo;
import com.epam.gm.model.RatingEvent;
import com.epam.gm.model.RatingUser;
import com.epam.gm.model.Tag;
import com.epam.gm.model.User;
import com.epam.gm.services.AddressService;
import com.epam.gm.services.EventService;
import com.epam.gm.services.FriendUserService;
import com.epam.gm.services.LanguageService;
import com.epam.gm.services.PhotoService;
import com.epam.gm.services.RatingEventService;
import com.epam.gm.services.RatingUserService;
import com.epam.gm.services.TagService;
import com.epam.gm.services.UserService;

public class UserCalculator {
	//Models
	private User user;
	private User client;
	private List<RatingUser> allRatingUser;
	private List<FriendUser> favorites;
	private List<Event> allEvents;
	private List<Tag> allTags;
	private List<Language> allLangs;
	private List<Photo> allPhotos;
	private List<Address> allAddress;
	
	//Dao
	private UserService userService = new UserService();
	private RatingUserService ratingUserService = new RatingUserService();
	private FriendUserService friendService = new FriendUserService();
	private EventService eventService = new EventService();
	private RatingEventService ratingEventService = new RatingEventService();
	private TagService tagService = new TagService();
	private LanguageService langService = new LanguageService();
	private PhotoService photoService = new PhotoService();
	private AddressService addressService = new AddressService();
	
	public UserCalculator(Integer userId, Integer clientId) throws SQLException {
		user = userService.getUserById(userId);
		allRatingUser = ratingUserService.getRatingByUser(userId);
		allEvents = eventService.getUserEvents(userId);
		allTags = tagService.getAllUserTags(userId);
		allLangs = langService.getAllUserLangs(userId);
		allPhotos = photoService.getUserPhotos(userId);
		allAddress = new ArrayList<Address>();
		if(user.getAddress() != null)
			if(user.getAddress().getId() != null)
					allAddress = addressService.getAddressByPureId(user.getAddress().getPureId());
		
		
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
	
	public int getTags() {
		if(allTags.isEmpty()) return 0;
		
		return 1;
	}
	
	public int getLangs() {
		if(allLangs.isEmpty()) return 0;
		
		return 1;
	}	
	
	public int countOfPhotos() {
		return allPhotos.size();
	}
	
	public int getLastName() {
		String lastname = user.getLastName();
		
		if(lastname != null && lastname.trim().length() > 0) 
			return 1;
		
		return 0;
	}
	
	public int getCellNumber() {
		String cellNumber = user.getCellNumber();
		
		if(cellNumber != null && cellNumber.trim().length() > 0) 
			return 1;
		
		return 0;
		
	}
	
	public int getAddress() {
		int res = 0;
		
		
		for(Address a: allAddress) {
			if(a.getAddress() != null && a.getAddress().trim().length() > 0)
				res++;
		}
		
		return res;
	}
	
	public double calculate() {
		double res = (double) getAverageRate() * 200.0 + 
					 (double)getSummaryRate() * 5.0 + 
				     getSummaryEventsRate() + 
				     (double) getAverageEventsRate() * 10.0 + 
				     (double) getClientRate() * 20.0 + 
				     (double) countOfFriends() * 2.5 +
				     (double) getTags() * 300.0 + 
				     (double) getLangs() * 300.0 +
				     (double) getLastName() * 350.0 +
				     (double) getCellNumber() * 400.0 +
				     (double) countOfPhotos() * 20.0 + 
					 (double) getAddress() * 150.0;
		
		if(isClientFavorite()) {
			res *= 2.0;
		}
		
		return res;
	}
	

	
	
	public static void sortUsersByPoints(List<User> users, Integer userid) throws SQLException {
		
		for(User e: users) {
			 e.setPoints(new UserCalculator(e.getId(), userid).calculate());
		}
		
		Collections.sort(users, User.BY_POINTS);
		
	}
	
	public static void main(String[] args) throws SQLException {

		
		
//		List<FriendUser> favorites = new FriendUserService().getUserFavorites(3);
//		
//		for(FriendUser f: favorites) {
//			System.out.println(f.getFriendId());
//		}
		
		//UserCalculator c = new UserCalculator(2, 8);
		//UserCalculator c = new UserCalculator(2, null);
		
//		System.out.println(c.getSummaryRate());
//		System.out.println(c.getAverageRate());
//		System.out.println(c.getClientRate());
		//System.out.println(c.countOfFriends());
		//System.out.println(c.isClientFavorite());
		
//		System.out.println("**********************");
//		System.out.println("summary:" + c.getSummaryEventsRate());
//		System.out.println("aver:" + c.getAverageEventsRate());
//		System.out.println("final: " + c.calculate());
		
		
		
		//get list
		List<User> users = new UserService().getAll();
		
		//sort by points for anonymous user
		UserCalculator.sortUsersByPoints(users, null);
		
		//sort by points for authorized user with id=8
		//UserCalculator.sortUsersByPoints(users, 8);
		
		for(User u: users) {
			System.out.println(u.getEmail() + ": " + u.getPoints());
		}
		
	}
	
	
	
}
