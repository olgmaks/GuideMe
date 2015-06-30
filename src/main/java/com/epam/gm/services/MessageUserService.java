package com.epam.gm.services;

import java.sql.SQLException;

import com.epam.gm.daolayer.MessageUserDao;
import com.epam.gm.model.User;

public class MessageUserService {
 private MessageUserDao muDao;
 public MessageUserService(){
	 muDao = new MessageUserDao();
 }
 public User getLastMessanegr(int userId) throws SQLException{
	 return muDao.getLastMessanger(userId);
 }
}
