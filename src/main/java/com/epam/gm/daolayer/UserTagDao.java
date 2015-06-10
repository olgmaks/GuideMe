package com.epam.gm.daolayer;

import com.epam.gm.model.UserTag;
import com.epam.gm.olgmaks.absractdao.dbcontrol.ConnectionManager;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class UserTagDao extends AbstractDao<UserTag>{

    public UserTagDao() {
	super(ConnectionManager.getConnection(), UserTag.class);
    }
    
}
