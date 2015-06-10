package com.epam.gm.daolayer;

import com.epam.gm.model.ServiceInEvent;
import com.epam.gm.olgmaks.absractdao.dbcontrol.ConnectionManager;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class ServiceInEventDao extends AbstractDao<ServiceInEvent> {

    public ServiceInEventDao() {
	super(ConnectionManager.getConnection(), ServiceInEvent.class);
    }

}
