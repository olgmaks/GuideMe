
package com.epam.gm.daolayer;

import com.epam.gm.model.UserInEvent;

import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

import java.sql.SQLException;
import java.util.List;

public class UserInEventDao extends AbstractDao<UserInEvent> {

    public UserInEventDao() {
        //gryn
    	//super(ConnectionManager.getConnection(), UserInEvent.class);
    	super(UserInEvent.class);
    }

    public List<UserInEvent> getAllUsersInEvents() throws SQLException {
        return super.getAll();
    }

    public List<UserInEvent> getEventsByUserId(int userId) throws SQLException {
        return super.getByField("user_id", userId);
    }

    public List<UserInEvent> getUsersByEventId(int eventId) throws SQLException {
        return super.getByField("event_id", eventId);
    }
    
    

}
