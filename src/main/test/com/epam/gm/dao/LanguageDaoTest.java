package com.epam.gm.dao;

import java.sql.SQLException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.epam.gm.daolayer.LanguageDao;
import com.epam.gm.model.Language;
import com.epam.gm.model.UserLanguage;

public class LanguageDaoTest {

	private static LanguageDao languageDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		languageDao = new LanguageDao();
	}

	@Ignore
	@Test
	public void test() throws SQLException {
		List<Language> languages = languageDao.getAll();
		for (Language language : languages) {
			System.out.println(language);
		}
	}

	@Ignore
	@Test
	public void test2() throws SQLException {
		List<Language> languages = languageDao.getLocalizedLangs();
		for (Language language : languages) {
			System.out.println(language);
		}
	}
	
	@Test
	public void test3() throws SQLException {
		List<Language> languages = languageDao.getAllUserLangs(14);
		for (Language language : languages) {
			System.out.println(language);
		}
		
		
	}	

}
