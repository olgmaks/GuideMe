package com.epam.gm.daolayer;

import java.sql.SQLException;
import java.util.List;

import com.epam.gm.model.RatingEvent;
import com.epam.gm.model.RatingUser;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class RatingUserDao extends AbstractDao<RatingUser>{
	private static final String GET_RATE_BY_USER = "where user_id = %S and estimator_id = %S";
    public RatingUserDao() {
    	//gryn
    	//super(ConnectionManager.getConnection(), RatingUser.class);
    	super(RatingUser.class);
    }
    
    public List<RatingUser> getRatingByUser(Integer userId) throws SQLException {
    	return getByField("user_id", userId);
    }

	public void save(RatingUser ru) throws IllegalArgumentException,
			IllegalAccessException, SQLException {
		super.save(ru);
	}

	public RatingUser getMarkByEvent(int estimatorId, int userId)
			throws SQLException {

		List<RatingUser> list = super.getWithCustomQuery(String.format(
				GET_RATE_BY_USER, estimatorId, userId));
		if (list.size() != 0)
			return list.get(0);
		else
			return null;
	}
}
