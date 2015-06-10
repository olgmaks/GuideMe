package com.epam.gm.daolayer;

import com.epam.gm.model.Tag;
import com.epam.gm.olgmaks.absractdao.dbcontrol.ConnectionManager;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class TagDao extends AbstractDao<Tag> {

    public TagDao() {
	super(ConnectionManager.getConnection(), Tag.class);
    }

}