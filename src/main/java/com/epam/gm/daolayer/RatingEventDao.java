package com.epam.gm.daolayer;

import java.sql.SQLException;
import java.util.List;

import com.epam.gm.model.RatingEvent;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class RatingEventDao extends AbstractDao<RatingEvent>{

    public RatingEventDao() {
    	//gryn
    	//super(ConnectionManager.getConnection(), RatingEvent.class);
    	super(RatingEvent.class);
    }
    
    public List<RatingEvent> getRatingByEvent(Integer eventId) throws SQLException {
    	return getByField("event_id", eventId);
    }

}
