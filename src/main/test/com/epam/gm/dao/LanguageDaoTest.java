package com.epam.gm.dao;

import java.sql.SQLException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.gm.daolayer.LanguageDao;
import com.epam.gm.model.Language;

public class LanguageDaoTest {

    private static LanguageDao languageDao;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
	languageDao = new LanguageDao();
    }

    @Test
    public void test() throws SQLException {
	List<Language> languages = languageDao.getAll();
	for (Language language : languages) {
	    System.out.println(language);
	}
    }

}
