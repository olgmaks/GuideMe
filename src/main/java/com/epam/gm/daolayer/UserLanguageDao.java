package com.epam.gm.daolayer;

import java.sql.SQLException;
import java.util.List;

import com.epam.gm.model.Language;
import com.epam.gm.model.Tag;
import com.epam.gm.model.UserLanguage;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class UserLanguageDao extends AbstractDao<UserLanguage> {

    public UserLanguageDao() {
    	//gryn
    	//super(ConnectionManager.getConnection(), UserLanguage.class);
    	super(UserLanguage.class);
    }
    
    public void deleteAllUserLangs(Integer userId) throws IllegalAccessException, SQLException {
    	deleteByField("user_id", userId);
    	
    }
    
	

}
