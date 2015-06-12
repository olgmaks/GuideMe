package com.epam.gm.dao;

import java.sql.SQLException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.gm.daolayer.UserLanguageDao;
import com.epam.gm.model.UserLanguage;

public class UserLanguageDaoTest {

    private static UserLanguageDao userLanguageDao;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
	userLanguageDao = new UserLanguageDao();
    }

    @Test
    public void test() throws SQLException {
	List<UserLanguage> userLanguages = userLanguageDao.getAll();
	for (UserLanguage userLanguage : userLanguages) {
	    System.out.println(userLanguage);
	}
    }

}
