package com.epam.gm.services;

import java.sql.SQLException;

import com.epam.gm.daolayer.UserTagDao;
import com.epam.gm.model.UserTag;

public class UserTagService {
	private UserTagDao dao = new UserTagDao();
	
	public void deleteAllUserTags(Integer userId) throws IllegalAccessException, SQLException {
		dao.deleteAllUserTags(userId);
	}
	
	public void save(UserTag userTag) throws IllegalArgumentException, IllegalAccessException, SQLException {
		dao.save(userTag);
	}
}
