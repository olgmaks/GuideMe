package com.epam.gm.services;

import java.sql.SQLException;

import com.epam.gm.daolayer.UserTagDao;
import com.epam.gm.model.Tag;
import com.epam.gm.model.UserTag;

public class UserTagService {

	private UserTagDao dao = new UserTagDao();

	public Boolean isUserHaveTag(String tagLabel, Integer userId) throws SQLException {
		return dao.isUserHaveTag(tagLabel,userId);
	}
	
	public void deleteAllUserTags(Integer userId) throws IllegalAccessException, SQLException {
		dao.deleteAllUserTags(userId);
	}
	
	public void save(UserTag userTag) throws IllegalArgumentException, IllegalAccessException, SQLException {
		dao.save(userTag);
	}

	public void deleteUserTag (Integer userId, Integer tagId) throws SQLException {
		dao.deleteUserTag(userId,tagId);
	}

}
