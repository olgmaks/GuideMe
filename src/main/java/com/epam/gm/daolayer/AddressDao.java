package com.epam.gm.daolayer;

import com.epam.gm.model.Address;
import com.epam.gm.olgmaks.absractdao.dbcontrol.ConnectionManager;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class AddressDao extends AbstractDao<Address>{

    public AddressDao() {
	super(ConnectionManager.getConnection(), Address.class);
    }

}
