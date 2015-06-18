package com.epam.gm.services;

import java.sql.SQLException;
import java.util.List;

import com.epam.gm.daolayer.LanguageDao;
import com.epam.gm.model.Language;

public class LanguageService {
	private LanguageDao languageDao;
	
	public LanguageService() {
		languageDao = new LanguageDao();
	}
	
	public List<Language> getLocalizedLangs() throws SQLException {
		return languageDao.getLocalizedLangs();
	}
	
	public List<Language> getAllActiveLangs() throws SQLException {
		return languageDao.getAllActiveLangs();
	}
	
	public Language getLangById(Integer id) throws SQLException {
		return languageDao.getLangById(id);
	}
	
	public List<Language> getAllUserLangs(Integer userId) throws SQLException {
		return languageDao.getAllUserLangs(userId);
	}
	
	public Language getlangByName(String name) throws SQLException {
		return languageDao.getlangByName(name);
	}
}
