package com.epam.gm.daolayer;

import com.epam.gm.model.Event;
import com.epam.gm.model.User;
import com.epam.gm.model.UserInEvent;
import com.epam.gm.olgmaks.absractdao.dbcontrol.ConnectionManager;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;
import com.epam.gm.services.UserService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class UserInEventDao extends AbstractDao<UserInEvent> {
	private static final String GET_BY_EVENT_AND_USER = " uie WHERE uie.event_id = ?eventId AND uie.user_id = ?userId";

	private static final String GET_BY_EVENT_ONLY_MEMBERS = " uie WHERE uie.is_member = TRUE AND uie.event_id = ? ";

	private static final String GET_BY_EVENT_ONLY_REQUESTERS = " uie WHERE uie.is_member = FALSE AND uie.event_id = ? ";

	private static final String DELETE_BY_EVENT_AND_USER = " event_id = ?eventId AND user_id = ?userId";

	private static final String GET_ACTUAL_EVENTS_BY_USER_ID_WHERE_USER_NOT_MODERATOR = "uie JOIN event e WHERE uie.event_id=e.id AND NOT uie.user_id=e.moderator_id AND e.date_to>NOW() AND uie.user_id=%s;";
	private static final String GET_OLD_EVENTS_BY_USER_ID_WHERE_USER_NOT_MODERATOR = "uie JOIN event e WHERE uie.event_id=e.id AND NOT uie.user_id=e.moderator_id AND e.date_to<NOW() AND uie.user_id=%s;";

	public UserInEventDao() {
		// gryn
		// super(ConnectionManager.getConnection(), UserInEvent.class);
		super(UserInEvent.class);
	}

	public List<UserInEvent> getAllActualUserInEventWhereUserNotModeratorByUserId(
			int id) throws SQLException {
		List<UserInEvent> list = super.getWithCustomQuery(String.format(
				GET_ACTUAL_EVENTS_BY_USER_ID_WHERE_USER_NOT_MODERATOR, id));
		Collections.sort(list, new Comparator<UserInEvent>() {

			@Override
			public int compare(UserInEvent uie1, UserInEvent uie2) {
				if (uie1.getEvent().getDateFrom()
						.before(uie2.getEvent().getDateFrom())) {
					return -1;
				} else if (uie2.getEvent().getDateFrom()
						.before(uie1.getEvent().getDateFrom())) {
					return 1;
				} else {
					return 0;
				}
			}
		});
		return list;
	}

	public List<UserInEvent> getAllOldUserInEventWhereUserNotModeratorByUserId(
			int id) throws SQLException {
		List<UserInEvent> list = super.getWithCustomQuery(String.format(
				GET_OLD_EVENTS_BY_USER_ID_WHERE_USER_NOT_MODERATOR, id));
		Collections.sort(list, new Comparator<UserInEvent>() {

			@Override
			public int compare(UserInEvent uie1, UserInEvent uie2) {
				if (uie1.getEvent().getDateFrom()
						.before(uie2.getEvent().getDateFrom())) {
					return -1;
				} else if (uie2.getEvent().getDateFrom()
						.before(uie1.getEvent().getDateFrom())) {
					return 1;
				} else {
					return 0;
				}
			}
		});
		return list;
	}

	public List<UserInEvent> getAllUsersInEvents() throws SQLException {
		return super.getAll();
	}

	public List<UserInEvent> getEventsByUserId(int userId) throws SQLException {
		return super.getByField("user_id", userId);
	}

	public List<UserInEvent> getUsersByEventId(int eventId) throws SQLException {
		return super.getByField("event_id", eventId);
	}

	public List<UserInEvent> getByEventAndUser(Integer eventId, Integer userId)
			throws SQLException {
		return getWithCustomQuery(GET_BY_EVENT_AND_USER.replace("?eventId",
				eventId.toString()).replace("?userId", userId.toString()));
	}

	public List<UserInEvent> getByEventOnlyMembers(Integer eventId)
			throws SQLException {
		return getWithCustomQuery(GET_BY_EVENT_ONLY_MEMBERS.replace("?",
				eventId.toString()));
	}

	public List<UserInEvent> getByEventOnlyRequesters(Integer eventId)
			throws SQLException {
		return getWithCustomQuery(GET_BY_EVENT_ONLY_REQUESTERS.replace("?",
				eventId.toString()));
	}

	public List<User> getByEventOnlyMembersToUsers(Integer eventId)
			throws SQLException {
		List<UserInEvent> userInEvent = getByEventOnlyMembers(eventId);

		List<User> res = new ArrayList<User>();

		UserService userService = new UserService();
		for (UserInEvent u : userInEvent) {
			User user = userService.getUserById(u.getUserId());
			res.add(user);
		}

		return res;
	}

	public void deleteUserFromEvent(Integer eventId, Integer userId)
			throws SQLException {
		deleteWithCustomQuery(DELETE_BY_EVENT_AND_USER.replace("?eventId",
				eventId.toString()).replace("?userId", userId.toString()));
	}

	public void joinToEvent(Integer eventId, Integer userId, Integer bedCount,
			String status) throws IllegalArgumentException,
			IllegalAccessException, SQLException {
		UserInEvent temp = new UserInEvent();
		temp.setBedCount(bedCount);
		temp.setCarplaceCount(0);
		temp.setEventId(eventId);
		temp.setFoodCount(0);
		temp.setIsMember(false);
		temp.setStatus(status);
		temp.setUserId(userId);

		super.save(temp);
	}

	public static void main(String[] args) throws SQLException,
			IllegalArgumentException, IllegalAccessException {
		for (UserInEvent uie : new UserInEventDao()
				.getAllOldUserInEventWhereUserNotModeratorByUserId(2)) {
			System.out.println(uie.getEvent());
		}

		// new
		// UserInEventDao().getByEventOnlyMembers(4).forEach(x->System.out.println(x));
		// new UserInEventDao().deleteUserFromEvent(4, 10);
		new UserInEventDao().joinToEvent(4, 5, -3, "guest");
	}

}
