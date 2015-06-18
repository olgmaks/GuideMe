package com.epam.gm.daolayer;

import java.sql.SQLException;
import java.util.List;

import com.epam.gm.model.CommentEvent;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class CommentEventDao extends AbstractDao<CommentEvent> {

	public CommentEventDao() {
		// gryn
		// super(ConnectionManager.getConnection(), CommentEvent.class);
		super(CommentEvent.class);
	}

	public List<CommentEvent> getByEventId(int eventId) throws SQLException {
		return getByField("event_id", eventId);
	}

	public void saveCommentEvent(CommentEvent comment)
			throws IllegalArgumentException, IllegalAccessException,
			SQLException {
		super.save(comment);
	}
}
