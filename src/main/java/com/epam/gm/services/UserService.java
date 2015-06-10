package com.epam.gm.services;

import java.sql.SQLException;

import com.epam.gm.daolayer.UserDao;
import com.epam.gm.model.User;

public class UserService {

    private UserDao userDao;

    public UserService() {
	userDao = new UserDao();
    }
    
    public void saveUser(User user){
	userDao.saveUser(user);
    }
    
    public User getUserByEmail (String email) throws SQLException {
	return userDao.getUserByEmail(email);
    }

}
