
package com.epam.gm.daolayer;

import java.sql.SQLException;
import java.util.List;

import com.epam.gm.model.UserInEvent;
import com.epam.gm.olgmaks.absractdao.dbcontrol.ConnectionManager;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class UserInEventDao extends AbstractDao<UserInEvent>{
    
    public UserInEventDao() {
	super(ConnectionManager.getConnection(), UserInEvent.class);
    }
    
    public List<UserInEvent> getAllUsersInEvents () throws SQLException {
	return super.getAll();
    }
    
}
