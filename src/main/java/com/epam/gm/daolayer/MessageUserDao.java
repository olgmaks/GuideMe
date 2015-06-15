package com.epam.gm.daolayer;

import com.epam.gm.model.MessageUser;

import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class MessageUserDao extends AbstractDao<MessageUser> {

    public MessageUserDao() {
    	//gryn
    	//super(ConnectionManager.getConnection(), MessageUser.class);
    	super(MessageUser.class);
    }

}
