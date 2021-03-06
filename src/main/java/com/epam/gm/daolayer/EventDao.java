package com.epam.gm.daolayer;

import com.epam.gm.calculators.EventCalculator;
import com.epam.gm.model.City;
import com.epam.gm.model.Country;
import com.epam.gm.model.Event;
import com.epam.gm.model.User;
import com.epam.gm.model.UserInEvent;
import com.epam.gm.olgmaks.absractdao.dbcontrol.ConnectionManager;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;
import com.epam.gm.services.EventService;
import com.epam.gm.services.UserInEventService;

import org.apache.commons.collections.map.HashedMap;

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

public class EventDao extends AbstractDao<Event> {

	private static final String GET_ACTIVE_NOT_DELETED = "e WHERE e.deleted = 0 AND e.status = 'active'";

	private static final String GET_ACTIVE_NOT_DELETED_AND_USERS = "e JOIN user u ON e.moderator_id = u.id WHERE e.deleted = 0 AND e.status = 'active' AND u.user_type_id = 2";

	private static final String GET_ACTIVE_NOT_DELETED_AND_GUIDE = "e JOIN user u ON e.moderator_id = u.id WHERE e.deleted = 0 AND e.status = 'active' AND u.user_type_id = 3";

	private static final String GET_NOT_DELETED_BY_PATTERN = "e WHERE (e.name LIKE '%TEXT%' OR e.name RLIKE '%TEXT%' OR e.description LIKE '%TEXT%' OR e.description RLIKE '%TEXT%') AND e.deleted = FALSE";

	private static final String GET_NOT_DELETED_BY_PATTERN_EXTENDED = "e WHERE (_PATTERN_) AND e.deleted = FALSE";

	private static final String GET_NOT_DELETED_BY_MODERATOR_ID = "e WHERE e.deleted = 0 AND e.date_to>NOW() AND e.status = 'active' and moderator_id = '%S'";

	private static final String GET_NOT_DELETED_AND_OLD_BY_MODERATOR_ID = "e WHERE e.deleted = 0 AND moderator_id = %s AND (e.date_to<NOW() OR not e.status = 'active')";

	private static final String GET_TAGS_BY_EVENTS = "SELECT  e.id, e.name, et.tag_id, t.name AS 'tag_name' "
			+ "FROM event e JOIN event_tag et ON e.id = et.event_id "
			+ " JOIN tag t ON et.tag_id = t.id "
			+ " WHERE e.id IN (?) "
			+ " ORDER BY e.id, e.name, t.name ";

	private static final String ADDITIONAL_EVENTS_FILTER = " e  WHERE e.id IN "
			+ " (SELECT e1.id FROM event e1 WHERE e.status IN ?status) "
			+ " AND e.id IN "
			+ " ( "
			+ "  SELECT e2.id FROM EVENT e2 JOIN USER u ON e2.MODERATOR_ID = u.id "
			+ "  WHERE u.USER_TYPE_ID in ?moderator_type " + "  ) "
			+ " AND e.id IN ( "
			+ " SELECT e3.id FROM event e3 WHERE e3.id NOT IN ( "
			+ "    SELECT uie.event_id FROM user_in_event uie "
			+ "    WHERE uie.is_member = TRUE " + "    GROUP BY uie.event_id "
			+ "    HAVING COUNT(uie.event_id) > ?max_members " + ") " + " ) ";

	private static final String TAG_NAME_EVENTS_FILTER = " e JOIN event_tag et ON e.id = et.event_id "
			+ " JOIN tag t ON et.tag_id = t.id "
			+ " WHERE e.deleted = FALSE AND  e.status = 'active' AND  t.name = '?' ";

	private static final String GET_ALL_ACTIVE_EVENT_WHERE_USER_MODERATOR = " e WHERE e.moderator_id = _userId_ AND e.status = 'active' AND e.deleted = 0 ";

	private static final String GET_ALL_ACTIVE_EVENT_WHERE_USER_NOT_MODERATOR = " e WHERE e.moderator_id != _userId_ AND e.status = 'active' AND e.deleted = 0 AND e.id IN ( "
			+ " SELECT uie.event_id FROM user_in_event uie WHERE uie.user_id = _userId_ AND uie.is_member = 1) ";

	private static final String GET_USER_FRIENDS_EVENTS = "e JOIN user_in_event uie ON e.id = uie.event_id " +
			"  WHERE uie.user_id IN (" +
				" SELECT u.id FROM user u JOIN friend_user  fu2 ON u.id = fu2.friend_id " +
					" WHERE  fu2.user_id=%1$s  " +
							" AND EXISTS (" +
				" SELECT * FROM friend_user fu3 WHERE fu3.user_id=fu2.friend_id " +
							" AND EXISTS (" +
					" SELECT * FROM friend_user WHERE fu2.user_id=fu3.friend_id" +
				" ))" +
			" ) AND e.id NOT IN (" +
				" SELECT e1.id FROM event e1 JOIN user_in_event uie1 ON e1.id = uie1.event_id WHERE uie1.user_id = %1$s)" +
			" AND e.deleted=false" +
			" GROUP BY e.id";

	public EventDao() {
		// gryn
		// super(ConnectionManager.getConnection(), Event.class);
		super(Event.class);
	}

	public List<Event> getUserFriendsEvents (Integer userId) throws SQLException {
		return  super.getWithCustomQuery(String.format(GET_USER_FRIENDS_EVENTS, userId));
	}

	public List<Event> getActiveAndNotDeletedEventsByModeratorId(int id)
			throws SQLException {
		List<Event> newList = super.getWithCustomQuery(String.format(
				GET_NOT_DELETED_BY_MODERATOR_ID, id));
		Collections.sort(newList, new Comparator<Event>() {

			@Override
			public int compare(Event e1, Event e2) {
				if (e1.getDateFrom().before(e2.getDateFrom())) {
					return -1;
				} else if (e2.getDateFrom().before(e1.getDateFrom())) {
					return 1;
				} else {
					return 0;
				}
			}
		});

		return newList;
	}

	public List<Event> getOldAndNotDeletedEventsByModeratorId(int id)
			throws SQLException {
		List<Event> newList = super.getWithCustomQuery(String.format(
				GET_NOT_DELETED_AND_OLD_BY_MODERATOR_ID, id));
		Collections.sort(newList, new Comparator<Event>() {

			@Override
			public int compare(Event e1, Event e2) {
				if (e1.getDateFrom().before(e2.getDateFrom())) {
					return -1;
				} else if (e2.getDateFrom().before(e1.getDateFrom())) {
					return 1;
				} else {
					return 0;
				}
			}
		});

		return newList;
	}

	public void saveEvent(Event event) throws IllegalArgumentException,
			IllegalAccessException, SQLException {
		super.save(event);
	}

	public List<Event> getAllEvents() throws SQLException {
		return super.getAll();
	}

	public void deleteById(int eventId) throws IllegalAccessException,
			SQLException {
		deleteByField("id", eventId);
	}

	// gryn
	public List<Event> getAllActiveNotDeletedEvents() throws SQLException {

		List<Event> results = new ArrayList<>();
		String sql = GET_ACTIVE_NOT_DELETED;
		results = getWithCustomQuery(sql);

		return results;
	}

	// gryn
	public List<Event> getAllActiveNotDeletedUserEvents() throws SQLException {

		List<Event> results = new ArrayList<>();
		String sql = GET_ACTIVE_NOT_DELETED_AND_USERS;
		results = getWithCustomQuery(sql);

		return results;
	}

	// gryn
	public List<Event> getAllActiveNotDeletedGuideEvents() throws SQLException {

		List<Event> results = new ArrayList<>();
		String sql = GET_ACTIVE_NOT_DELETED_AND_GUIDE;
		results = getWithCustomQuery(sql);

		return results;
	}

	public List<Event> getAllActiveNotDeletedGuideEventsInTheCity(Integer cityId)
			throws SQLException {
		CityDao cityDao = new CityDao();
		City city = cityDao.getCityById(cityId);
		List<City> cities = cityDao.getCitiesByPureId(city.getPureId());

		// StringJoiner join = new StringJoiner(",");

		List<Integer> cityIdList = new ArrayList<>();
		for (City c : cities) {
			// join.add(c.getId().toString());
			cityIdList.add(c.getId());
		}

		String sql = GET_ACTIVE_NOT_DELETED_AND_GUIDE;
		List<Event> results = getWithCustomQuery(sql);

		List<Event> events = new ArrayList<Event>();

		for (Event r : results) {
			if (r.getAddress() != null && r.getAddress().getCity() != null)
				if (cityIdList.contains(r.getAddress().getCity().getId()))
					events.add(r);

		}

		return events;
	}

	public List<Event> getAllNotDeletedEventsInTheCity(Integer cityId,
			List<Event> entry) throws SQLException {
		CityDao cityDao = new CityDao();
		City city = cityDao.getCityById(cityId);
		List<City> cities = cityDao.getCitiesByPureId(city.getPureId());

		List<Integer> cityIdList = new ArrayList<>();
		for (City c : cities) {
			// join.add(c.getId().toString());
			cityIdList.add(c.getId());
		}

		List<Event> results = null;
		if (entry == null)
			results = getAllNotDeletedEvents();
		else
			results = new ArrayList<>(entry);

		List<Event> events = new ArrayList<Event>();

		for (Event r : results) {
			if (r.getAddress() != null && r.getAddress().getCity() != null)
				if (cityIdList.contains(r.getAddress().getCity().getId()))
					events.add(r);

		}

		return events;
	}

	public List<Event> getAllNotDeletedEventsInTheCity(Integer cityId)
			throws SQLException {
		return getAllNotDeletedEventsInTheCity(cityId, null);
	}

	public List<Event> getAllNotDeletedEventsInTheCountry(Integer countryId,
			List<Event> entry) throws SQLException {
		CountryDao countryDao = new CountryDao();
		Country country = countryDao.getCountryById(countryId);
		List<Country> countries = countryDao.getCountriesByPureId(country
				.getPureId());

		List<Integer> countryIdList = new ArrayList<>();
		for (Country c : countries) {
			// join.add(c.getId().toString());
			countryIdList.add(c.getId());
		}

		List<Event> results = null;

		if (entry == null)
			results = getAllNotDeletedEvents();
		else
			results = new ArrayList<Event>(entry);

		List<Event> events = new ArrayList<Event>();

		for (Event r : results) {
			if (r.getAddress() != null && r.getAddress().getCity() != null
					&& r.getAddress().getCity().getCountryId() != null)
				if (countryIdList.contains(r.getAddress().getCity()
						.getCountryId()))
					events.add(r);

		}

		return events;
	}

	public List<Event> getAllNotDeletedEventsInTheCountry(Integer countryId)
			throws SQLException {
		return getAllNotDeletedEventsInTheCountry(countryId, null);
	}

	public List<Event> getAllNotDeletedEvents() throws SQLException {
		List<Event> res = getByField("deleted", false);
		return res;
	}

	public String getStringOfIds(List<Event> list) {
		StringJoiner joiner = new StringJoiner(",", "(", ")");
		joiner.setEmptyValue("");

		for (Event e : list)
			joiner.add(e.getId().toString());

		return joiner.toString();
	}

	public List<Event> excludeByAdditionalFilter(Map<String, String> map,
			List<Event> list) throws SQLException {
		if (list == null || list.isEmpty())
			return new ArrayList<Event>();

		String sql = ADDITIONAL_EVENTS_FILTER
				.replace("?status", map.get("status"))
				.replace("?max_members", map.get("max_members"))
				.replace("?moderator_type", map.get("moderator_type"));

		String tail = getStringOfIds(list);
		if (tail.length() > 0) {
			sql = sql + " AND e.id IN " + tail;
		}

		List<Event> result = new ArrayList<Event>();

		System.out.println("Query :");
		System.out.println(sql);

		List<Event> filter = getWithCustomQuery(sql);

		for (Event e : list) {
			if (filter.contains(e)) {
				result.add(e);
			}
		}

		return result;
	}

	public List<Event> getBySearchMap(Map<String, String> map, User user)
			throws SQLException {
		List<Event> res = null;

		if (map.isEmpty()) {
			res = getAllNotDeletedEvents();
		} else {
			if (map.keySet().contains("text")) {
				res = getAllNotDeletedEventsByPattern(map.get("text"));
			} else {
				res = getAllNotDeletedEvents();
			}

			if (map.keySet().contains("cityId")) {
				res = getAllNotDeletedEventsInTheCity(
						Integer.parseInt(map.get("cityId")), res);
			} else if (map.keySet().contains("countryId")) {
				res = getAllNotDeletedEventsInTheCountry(
						Integer.parseInt(map.get("countryId")), res);
			}
		}

		res = excludeByAdditionalFilter(map, res);

		EventCalculator.sortEventsByPoints(res,
				user == null ? null : user.getId());

		return res;
	}

	public List<Event> getByTagName(String tagName) throws SQLException {
		List<Event> res = null;

		res = getWithCustomQuery(TAG_NAME_EVENTS_FILTER.replace("?", tagName));

		return res;
	}

	public List<Event> getAllNotDeletedEventsByPattern(String text)
			throws SQLException {

		String txt = "e.name LIKE '%TEXT%' OR e.name RLIKE '%TEXT%' OR e.description LIKE '%TEXT%' OR e.description RLIKE '%TEXT%'";

		List<Event> results = new ArrayList<>();
		// String sql = GET_NOT_DELETED_BY_PATTERN.replaceAll("TEXT", text);

		StringJoiner sqlJoiner = new StringJoiner(" OR ");
		String[] tokens = text.split(" ");
		for (String t : tokens) {
			String word = t.trim();

			if (word.length() == 0)
				continue;

			sqlJoiner.add(txt.replaceAll("TEXT", word));

		}

		String sql = GET_NOT_DELETED_BY_PATTERN_EXTENDED.replace("_PATTERN_",
				sqlJoiner.toString());
		System.out
				.println("******************************************************************************************");
		System.out.println(sql);

		results = getWithCustomQuery(sql);

		return results;
	}

	public void buildTagString(List<Event> list) throws SQLException {
		if (list == null || list.isEmpty())
			return;

		Map<Integer, Event> map = new HashMap<Integer, Event>();

		StringJoiner join = new StringJoiner(",");
		for (Event e : list) {
			join.add(e.getId().toString());

			map.put(e.getId(), e);

			e.setTagString("");

			e.setTagList(new ArrayList<String>());
		}

		Connection connection = ConnectionManager.getConnection();

		StringBuilder sb = new StringBuilder();

		System.out.println(GET_TAGS_BY_EVENTS.replace("?", join.toString()));

		PreparedStatement stmt = connection.prepareStatement(GET_TAGS_BY_EVENTS
				.replace("?", join.toString()));
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Event event = map.get(rs.getInt("id"));

			event.setTagString(sb.append(event.getTagString()).append("#")
					.append(rs.getString("tag_name")).append(" ").toString());

			event.getTagList().add(rs.getString("tag_name"));

			sb.setLength(0);
		}
		rs.close();
		stmt.close();
		ConnectionManager.closeConnection(connection);

	}

	public void changeEventStatus(Integer id, String status)
			throws SQLException {
		Map<String, Object> updates = new HashMap<String, Object>();
		updates.put("status", status);
		updateById(id, updates);

	}

	public void fixEventLimit(Integer id) throws SQLException {
		Event event = new EventService().getById(id);
		if (event.getParticipants_limit() == null
				|| event.getParticipants_limit().equals(0)) {
			return;
		}

		UserInEventService userInEventService = new UserInEventService();
		List<UserInEvent> members = userInEventService
				.getByEventOnlyMembers(event.getId());

		if (members == null)
			return;

		if (members.size() > event.getParticipants_limit()) {
			// event.setParticipants_limit(members.size());

			Map<String, Object> updateConditions = new HashMap<String, Object>();
			updateConditions.put("participants_limit", members.size());

			super.updateById(id, updateConditions);

		}
	}

	public List<Event> getAllActiveEventsWhereUserModerator(Integer userId)
			throws SQLException {

		List<Event> results = new ArrayList<>();
		String sql = GET_ALL_ACTIVE_EVENT_WHERE_USER_MODERATOR.replaceAll(
				"_userId_", userId.toString());

		results = getWithCustomQuery(sql);

		return results;
	}

	public List<Event> getAllActiveEventsWhereUserNotModerator(Integer userId)
			throws SQLException {

		List<Event> results = new ArrayList<>();
		String sql = GET_ALL_ACTIVE_EVENT_WHERE_USER_NOT_MODERATOR.replaceAll(
				"_userId_", userId.toString());

		results = getWithCustomQuery(sql);

		return results;
	}

	public void updateEventAvatar(Integer eventId, Integer photoId) throws SQLException {
		  Map<String, Object> updates = new HashMap<>();
		  updates.put("avatar_id",photoId);
		  super.updateById(eventId,updates);
		 }

	public Event getEventById(int id) throws SQLException {
		List<Event> list = super.getByField("id", id);
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}

	}

	public static void main(String[] args) throws SQLException {
		List<Event> list = new EventDao()
				.getAllNotDeletedEventsByPattern("yoga");
		list.forEach(x -> System.out.println(x.getName()));

		// "e.name LIKE '%TEXT%' OR e.name RLIKE '%TEXT%' OR e.description LIKE '%TEXT%' OR e.description RLIKE '%TEXT%'";

	}
}