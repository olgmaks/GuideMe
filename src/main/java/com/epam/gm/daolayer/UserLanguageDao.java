package com.epam.gm.daolayer;

import java.sql.SQLException;
import java.util.List;

import com.epam.gm.model.Language;
import com.epam.gm.model.Tag;
import com.epam.gm.model.UserLanguage;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class UserLanguageDao extends AbstractDao<UserLanguage> {

    private static final String DELETE_USER_LANGUAGE = "user_id=%s and lang_id=%s";
    private static final String IS_USER_LANGUAGE = "SELECT ('%s' IN (SELECT l.name FROM language l JOIN user_language ul ON l.id = ul.lang_id WHERE ul.user_id=%s))";

    public UserLanguageDao() {
    	//gryn
    	//super(ConnectionManager.getConnection(), UserLanguage.class);
    	super(UserLanguage.class);
    }
    
    public void deleteAllUserLangs(Integer userId) throws IllegalAccessException, SQLException {
    	deleteByField("user_id", userId);
    }

    public void deleteUserLanguage(Integer userId, Integer languageId) throws SQLException {
        super.deleteWithCustomQuery(String.format(DELETE_USER_LANGUAGE,userId,languageId));
    }

    public Boolean isUserLanguage(Integer userId, String language ) throws SQLException {
       return super.getBoolean(String.format(IS_USER_LANGUAGE,language,userId));
    }
}
