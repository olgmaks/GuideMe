package com.epam.gm.daolayer;

import java.sql.SQLException;

import com.epam.gm.model.EventTag;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class EventTagDao extends AbstractDao<EventTag> {

    public EventTagDao() {
    	//gryn
    	//super(ConnectionManager.getConnection(), EventTag.class);
    	super(EventTag.class);
    }
    
    public void deleteAllEventTags(Integer eventId) throws IllegalAccessException, SQLException {
    	deleteByField("event_id", eventId);
    	
    }

}
