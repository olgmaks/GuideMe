package com.epam.gm.services;

import java.sql.SQLException;
import java.util.List;

import com.epam.gm.daolayer.TagDao;
import com.epam.gm.model.Tag;

public class TagService {
	private TagDao dao = new TagDao();
	
	public List<Tag> getAllUserTags(Integer userId) throws SQLException {
		return dao.getAllUserTags(userId);
	}
	
	public List<Tag> getAllActiveTags() throws SQLException {
		return dao.getAllActiveTags();
	}
	
	public Tag getTagByName(String name) throws SQLException {
		return dao.getTagByName(name);
	}	
	
	public void save(Tag tag) throws IllegalArgumentException, IllegalAccessException, SQLException {
		dao.save(tag);
	}
}
