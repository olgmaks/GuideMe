package com.epam.gm.daolayer;

import com.epam.gm.model.EventTag;
import com.epam.gm.olgmaks.absractdao.dbcontrol.ConnectionManager;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class EventTagDao extends AbstractDao<EventTag> {

    public EventTagDao() {
	super(ConnectionManager.getConnection(), EventTag.class);
    }

}
