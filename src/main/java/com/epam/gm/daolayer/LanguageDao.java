package com.epam.gm.daolayer;

import java.sql.SQLException;
import java.util.List;

import com.epam.gm.model.*;

import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class LanguageDao extends AbstractDao<Language> {

    public LanguageDao() {
    	//gryn
    	//super(ConnectionManager.getConnection(), Language.class);
    	super(Language.class);
    }
    
	//gryn
	public List<Language> getLocalizedLangs() throws SQLException {
		List<Language> result = getByField("localized", true);
		return result;      
	}

}
