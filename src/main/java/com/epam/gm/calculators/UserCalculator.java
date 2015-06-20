package com.epam.gm.calculators;

import java.sql.SQLException;
import java.util.List;

import com.epam.gm.model.FriendUser;
import com.epam.gm.model.RatingEvent;
import com.epam.gm.model.RatingUser;
import com.epam.gm.model.User;
import com.epam.gm.services.FriendUserService;
import com.epam.gm.services.RatingUserService;
import com.epam.gm.services.UserService;

public class UserCalculator {
	//Models
	private User user;
	private User client;
	private List<RatingUser> allRatingUser;
	
	//Dao
	private UserService userService = new UserService();
	private RatingUserService ratingUserService = new RatingUserService();
	
	public UserCalculator(Integer userId, Integer clientId) throws SQLException {
		user = userService.getUserById(userId);
		allRatingUser = ratingUserService.getRatingByUser(userId);
		
		if(clientId != null) {
			client = userService.getUserById(clientId);
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
	
	
	public static void main(String[] args) throws SQLException {
//		List<FriendUser> favorites = new FriendUserService().getUserFavorites(3);
//		
//		for(FriendUser f: favorites) {
//			System.out.println(f.getFriendId());
//		}
		
		UserCalculator c = new UserCalculator(2, 8);
		System.out.println(c.getSummaryRate());
		System.out.println(c.getAverageRate());
		System.out.println(c.getClientRate());
		
	}
}
