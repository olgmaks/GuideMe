package com.epam.gm.services;

import com.epam.gm.model.Language;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by OLEG on 05.07.2015.
 */
public class LanguageServiceTest {

    private static LanguageService languageService = new LanguageService();

    @Test@Ignore
    public void testGetAllUserLanguage() throws SQLException {
        List<Language> languages =  languageService.getAllUserLangs(8);
        for (Language language : languages) {
            System.out.println(language);
        }
    }

}
