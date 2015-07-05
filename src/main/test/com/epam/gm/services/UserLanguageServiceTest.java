package com.epam.gm.services;

import com.epam.gm.model.UserLanguage;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by OLEG on 05.07.2015.
 */
public class UserLanguageServiceTest {

    private static UserLanguageService  userLanguageService = new UserLanguageService();

    @Test@Ignore
    public void testDelete () {
        try {
            userLanguageService.deleteUserLanguage(8,"русский");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test@Ignore
    public void testAdd(){
        UserLanguage userLanguage = new UserLanguage();
        userLanguage.setUserId(8);
        userLanguage.setLangId(4);
        try {
            userLanguageService.save(userLanguage);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testIsUserLang() {
        try {
           Boolean is = userLanguageService.isUserLanguage(8, "русский");
            System.out.println(is);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
