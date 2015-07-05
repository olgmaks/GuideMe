package com.epam.gm.services;

import java.sql.SQLException;

import com.epam.gm.daolayer.UserLanguageDao;
import com.epam.gm.model.UserLanguage;


public class UserLanguageService {

	private UserLanguageDao dao = new UserLanguageDao();
	private LanguageService languageService = new LanguageService();
	
	public void deleteAllUserLangs(Integer userId) throws IllegalAccessException, SQLException {
		dao.deleteAllUserLangs(userId);
	}
	
	public void save(UserLanguage userLang) throws IllegalArgumentException, IllegalAccessException, SQLException {
		dao.save(userLang);
	}

	public void deleteUserLanguage (Integer userId, Integer languageId) throws SQLException {
		dao.deleteUserLanguage(userId,languageId);
	}

	public void deleteUserLanguage (Integer userId, String languageName) throws SQLException {
		Integer languageId = languageService.getlangByName(languageName).getId();
		dao.deleteUserLanguage(userId,languageId);
	}

	public Boolean isUserLanguage(Integer userId, String language) throws SQLException {
		return  dao.isUserLanguage(userId, language);
	}
}
