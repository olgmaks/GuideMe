package com.epam.gm.daolayer;

import com.epam.gm.model.Event;
import com.epam.gm.olgmaks.absractdao.dbcontrol.ConnectionManager;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

import java.sql.SQLException;
import java.util.List;

public class EventDao extends AbstractDao<Event> {

    public EventDao() {
        super(ConnectionManager.getConnection(), Event.class);
    }


    public List<Event> getAllEvents() throws SQLException {
        return super.getAll();
    }
    public void deleteById(int eventId) throws IllegalAccessException, SQLException{
    	deleteByField("id", eventId);
    }

}
