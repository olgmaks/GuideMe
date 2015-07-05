package com.epam.gm.daolayer;

import java.sql.SQLException;
import java.util.List;

import com.epam.gm.model.CommentUser;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class CommentUserDao extends AbstractDao<CommentUser> {

	public CommentUserDao() {
		// gryn
		// super(ConnectionManager.getConnection(), CommentUser.class);
		super(CommentUser.class);
	}

	public List<CommentUser> getByUserId(int userId) throws SQLException {
		return super.getByField("user_id", userId);
	}

	public void saveCommentUser(CommentUser comment)
			throws IllegalArgumentException, IllegalAccessException,
			SQLException {
		super.save(comment);
	}



}
