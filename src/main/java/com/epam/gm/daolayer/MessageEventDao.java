package com.epam.gm.daolayer;

import java.sql.SQLException;
import java.util.List;

import com.epam.gm.model.MessageEvent;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class MessageEventDao extends AbstractDao<MessageEvent> {

    public MessageEventDao() {
    	//gryn
    	//super(ConnectionManager.getConnection(), MessageEvent.class);
    	super(MessageEvent.class);
    }

    public List<MessageEvent> getAllMessagesEvents() throws SQLException {
	return super.getAll();
    }

    public void save(MessageEvent me) throws IllegalArgumentException, IllegalAccessException, SQLException {
    	super.save(me);
    }
    
    public List<MessageEvent> getByEvent(int eventId) throws SQLException{
    	return super.getByField("event_id", eventId);
    }
}
