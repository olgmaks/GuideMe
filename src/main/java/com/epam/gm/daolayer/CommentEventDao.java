package com.epam.gm.daolayer;

import com.epam.gm.model.CommentEvent;

import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class CommentEventDao extends AbstractDao<CommentEvent> {

    public CommentEventDao() {
    	//gryn
    	//super(ConnectionManager.getConnection(), CommentEvent.class);
    	super(CommentEvent.class);
    }

}
