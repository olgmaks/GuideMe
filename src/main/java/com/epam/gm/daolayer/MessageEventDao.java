package com.epam.gm.daolayer;

import java.util.List;

import com.epam.gm.model.MessageEvent;
import com.epam.gm.olgmaks.absractdao.dbcontrol.ConnectionManager;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class MessageEventDao extends AbstractDao<MessageEvent> {

    public MessageEventDao() {
	super(ConnectionManager.getConnection(), MessageEvent.class);
    }

    public List<MessageEvent> getAllMessagesEvents() {
	return super.getAll();
    }

}
