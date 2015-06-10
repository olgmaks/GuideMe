package com.epam.gm.daolayer;

import com.epam.gm.model.Photo;
import com.epam.gm.olgmaks.absractdao.dbcontrol.ConnectionManager;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class PhotoDao extends AbstractDao<Photo> {
    
    public PhotoDao() {
	super(ConnectionManager.getConnection(), Photo.class);
    }
    
}
