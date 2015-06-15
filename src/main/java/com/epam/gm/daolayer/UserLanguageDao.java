package com.epam.gm.daolayer;

import com.epam.gm.model.UserLanguage;

import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class UserLanguageDao extends AbstractDao<UserLanguage> {

    public UserLanguageDao() {
    	//gryn
    	//super(ConnectionManager.getConnection(), UserLanguage.class);
    	super(UserLanguage.class);
    }

}
