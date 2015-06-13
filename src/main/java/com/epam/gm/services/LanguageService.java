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
}
