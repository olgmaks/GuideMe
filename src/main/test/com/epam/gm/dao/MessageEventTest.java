package com.epam.gm.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.gm.model.MessageEvent;
import com.epam.gm.olgmaks.absractdao.dbcontrol.ConnectionManager;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;
import com.epam.gm.olgmaks.absractdao.general.IDao;

public class MessageEventTest {



    @Test
    public void test() {
	IDao<MessageEvent> dao = new AbstractDao<MessageEvent>(ConnectionManager.getConnection(), MessageEvent.class);
	for (MessageEvent m : dao.getAll()) {
	    System.out.println(m);
	}
    }

}
