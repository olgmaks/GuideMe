package com.epam.gm.services;


import java.util.List;

import com.epam.gm.daolayer.UserTypeDao;

import com.epam.gm.model.UserType;

public class UserTypeService {
    private UserTypeDao userTypeDao;

    public UserTypeService() {
    	userTypeDao = new UserTypeDao();
    }
    
    public List<UserType> getAll() {
    	return userTypeDao.getAll();
    }
}
