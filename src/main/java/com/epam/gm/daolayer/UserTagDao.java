package com.epam.gm.daolayer;

import com.epam.gm.model.UserTag;

import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class UserTagDao extends AbstractDao<UserTag>{

    public UserTagDao() {
    	//gryn
    	//super(ConnectionManager.getConnection(), UserTag.class);
    	super(UserTag.class);
    }
    
}
