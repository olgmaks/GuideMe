package com.epam.gm.daolayer;

import com.epam.gm.model.RatingEvent;
import com.epam.gm.olgmaks.absractdao.dbcontrol.ConnectionManager;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class RatingEventDao extends AbstractDao<RatingEvent>{

    public RatingEventDao() {
	super(ConnectionManager.getConnection(), RatingEvent.class);
    }

}
