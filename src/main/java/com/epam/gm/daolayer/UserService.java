package com.epam.gm.daolayer;

import java.sql.SQLException;
import java.util.List;

import com.epam.gm.model.User;
import com.epam.gm.olgmaks.absractdao.dbcontrol.ConnectionManager;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class UserService extends AbstractDao<User>{

 
    
    public UserService() {
	super(ConnectionManager.getConnection(), User.class);
    }

    public List<User> getUserByField(String fieldName, Object fieldValue) throws SQLException{
	return  getByField(fieldName, fieldValue);
    }
    
    public List<User> getAllUsers () throws SQLException {
	return getAll();
    }

}
