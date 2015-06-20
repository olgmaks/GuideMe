package com.epam.gm.daolayer;

import java.sql.SQLException;
import java.util.List;

import com.epam.gm.model.RatingEvent;
import com.epam.gm.model.RatingUser;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class RatingUserDao extends AbstractDao<RatingUser>{

    public RatingUserDao() {
    	//gryn
    	//super(ConnectionManager.getConnection(), RatingUser.class);
    	super(RatingUser.class);
    }
    
    public List<RatingUser> getRatingByUser(Integer userId) throws SQLException {
    	return getByField("user_id", userId);
    }

}
