package com.epam.gm.services;

import com.epam.gm.model.User;

public class UserService {

    private UserService userDao;

    public UserService() {
	userDao = new UserService();
    }
    
    public void saveUser(User user){
	userDao.saveUser(user);
    }

}
