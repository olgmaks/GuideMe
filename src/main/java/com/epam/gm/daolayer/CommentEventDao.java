package com.epam.gm.daolayer;

import com.epam.gm.model.CommentEvent;
import com.epam.gm.olgmaks.absractdao.dbcontrol.ConnectionManager;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class CommentEventDao extends AbstractDao<CommentEvent> {

    public CommentEventDao() {
	super(ConnectionManager.getConnection(), CommentEvent.class);
    }

}
