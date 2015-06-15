package com.epam.gm.daolayer;

import com.epam.gm.model.EventTag;

import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class EventTagDao extends AbstractDao<EventTag> {

    public EventTagDao() {
    	//gryn
    	//super(ConnectionManager.getConnection(), EventTag.class);
    	super(EventTag.class);
    }

}
