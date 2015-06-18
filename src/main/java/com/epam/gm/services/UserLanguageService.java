package com.epam.gm.services;

import java.sql.SQLException;

import com.epam.gm.daolayer.UserLanguageDao;
import com.epam.gm.model.UserLanguage;


public class UserLanguageService {
	private UserLanguageDao dao = new UserLanguageDao();
	
	public void deleteAllUserLangs(Integer userId) throws IllegalAccessException, SQLException {
		dao.deleteAllUserLangs(userId);
	}
	
	public void save(UserLanguage userLang) throws IllegalArgumentException, IllegalAccessException, SQLException {
		dao.save(userLang);
	}
}
