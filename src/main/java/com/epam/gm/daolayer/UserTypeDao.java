package com.epam.gm.daolayer;

import com.epam.gm.model.UserType;
import com.epam.gm.olgmaks.absractdao.dbcontrol.ConnectionManager;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class UserTypeDao extends AbstractDao<UserType> {

    public UserTypeDao() {
	super(ConnectionManager.getConnection(), UserType.class);
    }

}
