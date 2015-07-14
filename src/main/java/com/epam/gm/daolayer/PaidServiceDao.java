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

	public Map<User, List<Integer>> getAllUserAndOrdersByEventId(int eventId)
			throws SQLException {
		Map<User, List<Integer>> map = new HashMap<User, List<Integer>>();
		List<UserInEvent> list = new UserInEventDao()
				.getAllUserInEventByEventId(eventId);
		if (list.isEmpty()) {
			return null;
		} else {
			for (UserInEvent userInEvent : list) {
				List<Integer> orders = new ArrayList<Integer>();
				User u = userInEvent.getUser();
				orders.add(getAmountUserOrdersByUserId(u.getId()));
				orders.add(getAmountUserAcceptedOrdersByUserId(u.getId()));
				map.put(u, orders);
			}
		}

		return map;

	}

	public Integer getAmountUserOrdersByUserId(int idUser) throws SQLException {
		List<PaidService> list = getByField("user_id", idUser);
		int orders = 0;
		if (list.isEmpty()) {
			return 0;
		} else {
			for (PaidService paidService : list) {
				if (paidService.isAccepted()) {
					++orders;
				}

			}
		}
		return orders;

	}

	public Integer getAmountUserAcceptedOrdersByUserId(int idUser)
			throws SQLException {
		List<PaidService> list = getByField("user_id", idUser);
		int accepted = 0;
		if (list.isEmpty()) {
			return 0;
		} else {
			for (PaidService paidService : list) {
				if (!paidService.isAccepted()) {
					++accepted;
				}

			}
		}
		return accepted;
	}

	public static void main(String[] args) throws SQLException {
		Map<User, List<Integer>> map = new PaidServiceDao()
				.getAllUserAndOrdersByEventId(42);

		for (Map.Entry<User, List<Integer>> entry : map.entrySet()) {
			System.out.println(entry.getKey().getFirstName());
			List<Integer> list = entry.getValue();
			for (Integer i : list) {
				System.out.println(i);
			}
		}
	}
}
