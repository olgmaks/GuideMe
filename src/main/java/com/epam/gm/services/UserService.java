package com.epam.gm.services;

import java.util.List;

import com.epam.gm.model.Event;
import com.epam.gm.olgmaks.absractdao.dbcontrol.ConnectionManager;
import com.epam.gm.olgmaks.absractdao.dbcontrol.MyConnection;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;
import com.epam.gm.olgmaks.absractdao.general.IDao;

public class UserService {
	public static void main(String[] args) {
		IDao<Event> abstractDao = new AbstractDao<Event>(
				MyConnection.getConnection(), Event.class);

		List<Event> list = abstractDao.getAll();
		for (Event e : list) {
			System.out.println(e);
		}

	}
}
