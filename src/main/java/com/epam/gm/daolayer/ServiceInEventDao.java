package com.epam.gm.daolayer;

import com.epam.gm.model.ServiceInEvent;

import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class ServiceInEventDao extends AbstractDao<ServiceInEvent> {

    public ServiceInEventDao() {
    	//gryn
    	//super(ConnectionManager.getConnection(), ServiceInEvent.class);
    	super(ServiceInEvent.class);
    }

}
