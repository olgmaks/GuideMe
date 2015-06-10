package com.epam.gm.daolayer;

import com.epam.gm.model.Language;
import com.epam.gm.olgmaks.absractdao.dbcontrol.ConnectionManager;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class LanguageDao extends AbstractDao<Language> {

    public LanguageDao() {
	super(ConnectionManager.getConnection(), Language.class);
    }

}
