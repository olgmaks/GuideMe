package com.epam.gm.daolayer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.gm.model.Tag;
import com.epam.gm.model.UserTag;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class UserTagDao extends AbstractDao<UserTag>{

    private static final String DELETE_USER_TAG= "user_id=%s and tag_id=%s";
    private static final String IS_USER_HAVE_TAG = "SELECT ('%s' in (select t.name from user_tag ut JOIN tag t on t.id = ut.tag_id where ut.user_id=%s)) ";

    public UserTagDao() {
    	//gryn
    	//super(ConnectionManager.getConnection(), UserTag.class);
    	super(UserTag.class);
    }

    public Boolean isUserHaveTag (String tagLabel, Integer userId) throws SQLException {
        return super.getBoolean(String.format(IS_USER_HAVE_TAG,tagLabel,userId));
    }
    
    public void deleteAllUserTags(Integer userId) throws IllegalAccessException, SQLException {
    	deleteByField("user_id", userId);
    	
    }

    public void deleteUserTag (Integer userId, Integer tagId) throws SQLException {
        super.deleteWithCustomQuery(String.format(DELETE_USER_TAG,userId,tagId));
    }
    
}
