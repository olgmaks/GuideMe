package com.epam.gm.daolayer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.gm.model.Tag;
import com.epam.gm.model.UserTag;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class UserTagDao extends AbstractDao<UserTag>{

    public UserTagDao() {
    	//gryn
    	//super(ConnectionManager.getConnection(), UserTag.class);
    	super(UserTag.class);
    }
    
    public void deleteAllUserTags(Integer userId) throws IllegalAccessException, SQLException {
    	deleteByField("user_id", userId);
    	
    }
    
}
