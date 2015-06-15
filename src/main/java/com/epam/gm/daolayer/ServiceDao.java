package com.epam.gm.daolayer;

import com.epam.gm.model.Service;

import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class ServiceDao extends AbstractDao<Service>{

    public ServiceDao() {
    	//gryn
    	//super(ConnectionManager.getConnection(), Service.class);
    	super(Service.class);
    }

}
