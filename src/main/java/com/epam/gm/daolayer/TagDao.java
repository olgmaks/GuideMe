package com.epam.gm.daolayer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.epam.gm.model.EventTag;
import com.epam.gm.model.Tag;
import com.epam.gm.model.UserTag;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class TagDao extends AbstractDao<Tag> {

	public TagDao() {
		// gryn
		// super(ConnectionManager.getConnection(), Tag.class);
		super(Tag.class);
	}
	
	public Boolean isPresentName(String name) throws SQLException{
		String select = "SELECT ('%S' IN (SELECT t.name FROM tag t))";
		return super.getBoolean(String.format(select, name));
	}
	// gryn - all not deleted tags
	public List<Tag> getAllActiveTags() throws SQLException {
		List<Tag> result = getByField("deleted", false);
		Collections.sort(result);

		return result;
	}

	public Tag getTagById(Integer id) throws SQLException {
		List<Tag> result = getByField("id", id);
		if (result.isEmpty()) {
			return null;
		} else {
			return result.get(0);
		}
	}

	public Tag getTagByName(String name) throws SQLException {
		List<Tag> result = getByField("name", name);
		if (result.isEmpty()) {
			return null;
		} else {
			return result.get(0);
		}
	}

	public void save(Tag tag) throws IllegalArgumentException,
			IllegalAccessException, SQLException {
		super.save(tag);
	}

	public void update(int id, Map<String, Object> map) throws SQLException {
		super.updateById(id, map);
	}

	public List<Tag> getAllUserTags(Integer userId) throws SQLException {
		UserTagDao dao = new UserTagDao();

		List<UserTag> list = dao.getByField("user_id", userId);

		List<Tag> res = new ArrayList<>();
		List<Tag> all = getAllActiveTags();

		for (UserTag userTag : list) {
			Tag tag = getTagById(userTag.getTagId());

			if (tag == null)
				continue;

			if (all.contains(tag))
				res.add(tag);
		}

		return res;
	}
	
	public List<Tag> getAllEventTags(Integer eventId) throws SQLException {
		EventTagDao dao = new EventTagDao();
 
		List<EventTag> list = dao.getByField("event_id", eventId);

		List<Tag> res = new ArrayList<>();
		List<Tag> all = getAllActiveTags();

		for (EventTag eventTag : list) {
			Tag tag = getTagById(eventTag.getTagId());

			if (tag == null)
				continue;

			if (all.contains(tag))
				res.add(tag);
		}

		return res;
	}
}
