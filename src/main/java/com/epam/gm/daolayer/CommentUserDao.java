package com.epam.gm.daolayer;

import com.epam.gm.model.CommentUser;

import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class CommentUserDao extends AbstractDao<CommentUser> {

    public CommentUserDao() {
    	//gryn
    	//super(ConnectionManager.getConnection(), CommentUser.class);
    	super(CommentUser.class);
    }

}
