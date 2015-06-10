package com.epam.gm.daolayer;

import com.epam.gm.model.RatingUser;
import com.epam.gm.olgmaks.absractdao.dbcontrol.ConnectionManager;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class RatingUserDao extends AbstractDao<RatingUser>{

    public RatingUserDao() {
	super(ConnectionManager.getConnection(), RatingUser.class);
    }

}
