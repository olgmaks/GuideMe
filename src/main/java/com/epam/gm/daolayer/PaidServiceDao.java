package com.epam.gm.daolayer;

import groovy.lang.Newify;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.epam.gm.model.PaidService;
import com.epam.gm.model.User;
import com.epam.gm.model.UserInEvent;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class PaidServiceDao extends AbstractDao<PaidService> {
	public static final String ALL_PAID_BY_USER_AND_EVENT_ID = "ps WHERE ps.user_id=%s AND ps.event_id= %s;";

	public PaidServiceDao() {
		super(PaidService.class);
	}

	public void savePaidService(PaidService paidService)
			throws IllegalArgumentException, IllegalAccessException,
			SQLException {
		super.save(paidService);
	}

	public List<PaidService> getPaidServiceByUserIdAndEventId(int userId,
			int eventId) throws SQLException {
		return super.getWithCustomQuery(String.format(
				ALL_PAID_BY_USER_AND_EVENT_ID, userId, eventId));
	}

	public Map<User, List<PaidService>> getAllUsersAndOrdersByEventId(
			int eventId) throws SQLException {
		List<UserInEvent> list = new UserInEventDao()
				.getAllUserInEventByEventId(eventId);
		Map<User, List<PaidService>> map = new HashMap<User, List<PaidService>>();

		if (list.isEmpty()) {
			return null;
		} else {
			for (UserInEvent uie : list) {
				List<PaidService> paidService = new PaidServiceDao()
						.getByField("user_id", uie.getUser().getId());
				map.put(uie.getUser(), paidService);

			}
			return map;
		}

	}

	public static void main(String[] args) throws SQLException {
		Map<User, List<PaidService>> map = new PaidServiceDao()
				.getAllUsersAndOrdersByEventId(29);
		for (Map.Entry<User, List<PaidService>> entry : map.entrySet()) {
			System.out.println(entry.getKey());
			for (PaidService p : entry.getValue()) {
				System.out.println(p);
			}

		}

	}

}
