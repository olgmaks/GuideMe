package com.epam.gm.daolayer;

import java.sql.SQLException;
import java.util.List;

import com.epam.gm.model.Event;
import com.epam.gm.olgmaks.absractdao.dbcontrol.ConnectionManager;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class EventDao extends AbstractDao<Event>{

    public EventDao() {
	super(ConnectionManager.getConnection(), Event.class);
    }
    
    
    public List<Event> getAllEvents() throws SQLException {
	return super.getAll();
    }
    

}
