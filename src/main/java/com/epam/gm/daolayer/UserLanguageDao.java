package com.epam.gm.daolayer;

import com.epam.gm.model.UserLanguage;
import com.epam.gm.olgmaks.absractdao.dbcontrol.ConnectionManager;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class UserLanguageDao extends AbstractDao<UserLanguage> {

    public UserLanguageDao() {
	super(ConnectionManager.getConnection(), UserLanguage.class);
    }

}
