package com.epam.gm.services;

import java.sql.SQLException;
import java.util.List;

import com.epam.gm.daolayer.RatingUserDao;
import com.epam.gm.model.RatingUser;

public class RatingUserService {
	private RatingUserDao dao = new RatingUserDao();
	
	public List<RatingUser> getRatingByUser(Integer userId) throws SQLException {
		return dao.getRatingByUser(userId);
	}

	public void saveRatingUser (Integer estimatorId, Integer userId, Integer mark) throws SQLException, IllegalAccessException {
		dao.save(new RatingUser(estimatorId,userId,mark));
	}

	public Boolean verifyMarkExisting (Integer estimatorId, Integer userId) throws SQLException {
		return  dao.verifyMarkExisting(estimatorId,userId);
	}
	
//	public static void main(String[] args) throws SQLException {
//		new RatingUserService().getRatingByUser(2).forEach(System.out::println);
//	}
}
