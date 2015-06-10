package com.epam.gm.daolayer;

import com.epam.gm.model.CommentUser;
import com.epam.gm.olgmaks.absractdao.dbcontrol.ConnectionManager;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class CommentUserDao extends AbstractDao<CommentUser> {

    public CommentUserDao() {
	super(ConnectionManager.getConnection(), CommentUser.class);
    }

}
