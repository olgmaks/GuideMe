package com.epam.gm.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.epam.gm.daolayer.LanguageDao;
import com.epam.gm.model.Language;
import com.epam.gm.model.User;
import com.epam.gm.utf8uncoder.StringHelper;

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
	
	public List<Language> getUserLangsForLocal(User user) throws SQLException {
		return languageDao.getUserLangsForLocal(user);
	}
	
	public void save(Language lang) throws IllegalArgumentException, IllegalAccessException, SQLException{
		languageDao.save(lang);
	}
	
	public void updateById(int id, Map<String, Object> updates) throws SQLException{
		languageDao.updateById(id, updates);
	}
	public Boolean isPresentName(String name) throws SQLException{
		return languageDao.isPresentName(name);
	}
	
	public Boolean isPresentShortName(String shortName) throws SQLException{
		return languageDao.isPresentShortName(shortName);
	}
	public static void main(String[] args) throws SQLException {
		String s = "Українська";
		s = StringHelper.convertFromUTF8(s);
		System.out.println(new LanguageService().isPresentName(s));
	}
}
