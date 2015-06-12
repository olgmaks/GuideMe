package com.epam.gm.services;


import java.sql.SQLException;
import java.util.List;

import com.epam.gm.daolayer.UserTypeDao;
import com.epam.gm.model.UserType;

public class UserTypeService {
    private UserTypeDao userTypeDao;

    public UserTypeService() {
    	userTypeDao = new UserTypeDao();
    }
    
    public List<UserType> getAll() throws SQLException {
    	return userTypeDao.getAll();
    }
}
