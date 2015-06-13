package com.epam.gm.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.epam.gm.daolayer.UserDao;
import com.epam.gm.model.User;

public class UserService {

    private UserDao userDao;

    public UserService() {
	userDao = new UserDao();
    }
    
    public void saveUser(User user) throws IllegalArgumentException, IllegalAccessException, SQLException{
	userDao.saveUser(user);
    }
    
    public User getUserByEmail (String email) throws SQLException {
	return userDao.getUserByEmail(email);
    }

    public List<User> getUsersByCityName(String cityName) throws SQLException {
	return userDao.getUsersByCityName(cityName);
	
    }
    public List<User> getAll() throws SQLException{
    	return userDao.getAll();
    }
    public List<User> getByUserType(String userTypeId) throws SQLException{
    	return userDao.getUsersByUserType(userTypeId);
    }

    public void  updateWithCustomQuery (Map<String, Object> updates, String joined, String where) throws SQLException {
        userDao.updateWithCustomQuery(updates,joined,where);
    }

}
