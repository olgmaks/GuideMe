package com.epam.gm.daolayer;

import com.epam.gm.model.MessageUser;
import com.epam.gm.olgmaks.absractdao.dbcontrol.ConnectionManager;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class MessageUserDao extends AbstractDao<MessageUser> {

    public MessageUserDao() {
	super(ConnectionManager.getConnection(), MessageUser.class);
    }

}
