package com.epam.gm.daolayer;

import java.sql.SQLException;
import java.util.List;

import com.epam.gm.model.RatingEvent;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class RatingEventDao extends AbstractDao<RatingEvent>{
	private static final String GET_RATE_BY_USER = "where event_id = %S and estimator_id = %S";
    public RatingEventDao() {
    	//gryn
    	//super(ConnectionManager.getConnection(), RatingEvent.class);
    	super(RatingEvent.class);
    }
    
    public List<RatingEvent> getRatingByEvent(Integer eventId) throws SQLException {
    	return getByField("event_id", eventId);
    }
    public void save(RatingEvent re) throws IllegalArgumentException, IllegalAccessException, SQLException{
    	super.save(re);
    }
    
    public RatingEvent getEventPhoto(int eventId, int userId) throws SQLException {
        RatingEvent result = super.getWithCustomQuery(String.format(GET_RATE_BY_USER,eventId,userId)).get(0);
        return result;
    }
}
