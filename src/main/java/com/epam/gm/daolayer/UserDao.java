package com.epam.gm.daolayer;

import com.epam.gm.hashpassword.MD5HashPassword;
import com.epam.gm.model.Country;
import com.epam.gm.model.Event;
import com.epam.gm.model.FriendUser;
import com.epam.gm.model.User;
import com.epam.gm.model.UserActivity;
import com.epam.gm.olgmaks.absractdao.dbcontrol.ConnectionManager;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class UserDao extends AbstractDao<User> {

	private static final String USER_EMAIL_FIELD = "email";
	private static final String USER_TYPE_FIELD = "user_type_id";
	private static final String USER_FACEBOOK_ID = "facebook_id";
	private static final String USER_VK_ID = "vk_id";

	private static final String GET_USER_BY_CITY_NAME_SQL = "JOIN address ON user.address_id = address.id "
			+ "JOIN city ON address.city_id = city.id WHERE name = '%S' AND user.is_active=true";

	private static final String SEARCH_USER_BY_NAME = "u "
			+ "WHERE (u.First_name LIKE '%%%1$s%%' OR u.Last_name LIKE '%%%1$s%%') AND u.is_active=true";

	private static final String SEARCH_USER_BY_CITY_NAME = "u"
			+ "  JOIN address a ON u.address_id = a.id"
			+ "  JOIN city c ON a.city_id = c.id  WHERE c.id in ("
			+ "    SELECT c1.id FROM city c1 WHERE c1.pure_id in ("
			+ "    SELECT c2.pure_id FROM city c2 WHERE c2.name LIKE '%%%s%%' GROUP BY a.pure_id)"
			+ "  ) AND u.is_active=true GROUP BY u.id ;";

	private static final String SEARCH_USER_BY_TAGS = "currentUser WHERE EXISTS("
			+ "SELECT ut.user_id , COUNT(*) AS tag_count"
			+ "  FROM user_tag ut"
			+ "  JOIN user u ON ut.user_id = u.id"
			+ "  JOIN tag t ON ut.tag_id = t.id"
			+ "  WHERE currentUser.id = ut.user_id and t.name  IN (%s)"
			+ "  GROUP BY currentUser.id"
			+ "  HAVING tag_count >= %s"
			+ ") AND currentUser.is_active=true";

	private static final String SEARCH_USER_NON_FRIEND = "u WHERE (not u.id=%1$s)"
			+ "  AND (u.is_active=true)  AND (u.id not in ("
			+ "select fu.friend_id from friend_user fu WHERE fu.user_id =%1$s AND NOT EXISTS ("
			+ "SELECT fu1.friend_id FROM friend_user fu1 WHERE fu.friend_id = fu1.user_id AND fu.user_id=fu1.friend_id)"
			+ "  union  SELECT fu.user_id from friend_user fu WHERE fu.friend_id = %1$s AND NOT EXISTS ("
			+ "SELECT * FROM friend_user fu1 WHERE fu.friend_id = fu1.user_id AND fu.user_id=fu1.friend_id)"
			+ "    union  SELECT fu.friend_id from friend_user  fu WHERE fu.user_id = %1$s AND EXISTS ("
			+ "SELECT * FROM friend_user fu1 WHERE fu.friend_id = fu1.user_id AND fu.user_id=fu1.friend_id)))";

	private static final String SEARCH_USER_GUIDE = "u JOIN user_type ut ON u.user_type_id = ut.id "
			+ "WHERE ut.name = 'guide' AND u.is_active=true";

	private static final String SEARCH_USER_NON_GUIDE = "u JOIN user_type ut ON u.user_type_id = ut.id "
			+ "WHERE ut.name = 'user' AND u.is_active=true ";

	private static final String SEARCH_USER_AGE_RANGE = "";

	// gryn
	private static final String ACTIVE_USERS_AND_GUIDES_IN_COUNTRY = "u JOIN address a ON u.address_id = a.id JOIN city c ON a.city_id = c.id JOIN country c1 ON c.country_id = c1.id "
			+ "WHERE u.is_active = TRUE AND u.user_type_id IN (2, 3) AND c1.pure_id = ? ";

	public static final String IS_USER = "SELECT (%s in( select u.id from user u where is_active=true)) ";

	// gryn
	public static final String GET_TAGS_BY_USERS = " SELECT  e.id, et.tag_id, t.name AS 'tag_name' "
			+ " FROM user e JOIN user_tag et ON e.id = et.user_id "
			+ " JOIN tag t ON et.tag_id = t.id "
			+ " WHERE e.id IN (?) "
			+ " ORDER BY e.id, t.name ";

	public UserDao() {
		// gryn
		// super(ConnectionManager.getConnection(), User.class);
		super(User.class);
	}

	public void saveUser(User user) throws IllegalArgumentException,
			IllegalAccessException, SQLException {
		super.save(user);
	}

	public Boolean isUserPresent(Integer id) throws SQLException {
		return super.getBoolean(String.format(IS_USER, id));
	}

	/*
	 * Search user methods for future intersection (Maksymuk)
	 */

	public List<User> searchUserByName(String filterNameInput)
			throws SQLException {
		return super.getWithCustomQuery(String.format(SEARCH_USER_BY_NAME,
				filterNameInput));
	}

	public List<User> searchUserByCityName(String cityName) throws SQLException {
		return super.getWithCustomQuery(String.format(SEARCH_USER_BY_CITY_NAME,
				cityName));
	}

	// tags example : String : ['music','photo','camping']
	public List<User> searchUserByTags(String tags, int matches)
			throws SQLException {
		return super.getWithCustomQuery(String.format(SEARCH_USER_BY_TAGS,
				tags, matches));
	}

	public List<User> searchNonFriendsUsers(Integer searcherId)
			throws SQLException {
		String sql = String.format(SEARCH_USER_NON_FRIEND, searcherId);
		System.out.println(sql);
		return super.getWithCustomQuery(sql);
	}

	public List<User> searchUserGuide() throws SQLException {
		return super.getWithCustomQuery(SEARCH_USER_GUIDE);
	}

	public List<User> searchUserNonGuide() throws SQLException {
		return super.getWithCustomQuery(SEARCH_USER_NON_GUIDE);
	}

	public User getUserById(Integer id) throws SQLException {
		List<User> result = getByField("id", id);
		if (result.isEmpty()) {
			return null;
		} else {
			return result.get(0);
		}

	}

	public User getUserByEmail(String email) throws SQLException {
		List<User> result = getByField(USER_EMAIL_FIELD, email);
		if (result.isEmpty()) {
			return null;
		} else {
			return result.get(0);
		}
	}

	public User getUserByFacebookId(String id) throws SQLException {
		List<User> result = getByField(USER_FACEBOOK_ID, id);
		if (result.isEmpty()) {
			return null;
		} else {
			return result.get(0);
		}
	}

	public User getUserByVkId(String Id) throws SQLException {
		List<User> result = getByField(USER_VK_ID, Id);
		if (result.isEmpty()) {
			return null;
		} else {
			return result.get(0);
		}
	}

	public List<User> getUsersByCityName(String cityName) throws SQLException {
		return getWithCustomQuery(String.format(GET_USER_BY_CITY_NAME_SQL,
				cityName));
	}

	public List<User> getUsersByUserType(String userTypeId) throws SQLException {
		List<User> result = getByField(USER_TYPE_FIELD, userTypeId);
		System.out.println(userTypeId);
		return result;
	}

	public List<User> getAllUsers() throws SQLException {
		return getAll();
	}

	public User getUserById(int id) throws SQLException {
		List<User> result = super.getByField("id", id);
		if (result.isEmpty()) {
			return null;
		} else {
			return result.get(0);
		}
	}

	public void deleteById(int userId) throws IllegalAccessException,
			SQLException {
		deleteByField("id", userId);
	}

	public void updateWithCustomQuery(Map<String, Object> updates,
			String joined, String where) throws SQLException {
		super.updateWithCustomQuery(updates, joined, where);
	}

	public void activeUser(int userId) throws SQLException,
			IllegalAccessException {
		callStoredProcedure("{call confirmUnconfirmUser(?)}",
				String.valueOf(userId));
	}

	public List<FriendUser> getFriends(int userId) throws SQLException {
		FriendUserDao fuDao = new FriendUserDao();
		return fuDao.getUserFriends(userId);
	}

	public List<UserActivity> userActivity(int userId) throws SQLException,
			IllegalAccessException {
		List<UserActivity> listActivity = new ArrayList<UserActivity>();
		Connection connection = ConnectionManager.getConnection();
		CallableStatement cs = connection.prepareCall("{call userActivity(?)}");
		cs.setInt(1, userId);
		boolean hadResult = cs.execute();
		ResultSet rs = cs.getResultSet();
		while (rs.next()) {
			UserActivity ua = new UserActivity();
			ua.setActivity(rs.getString("activity"));
			ua.setName(rs.getString("name"));
			ua.setAct(rs.getString("act"));
			ua.setIdAct(rs.getInt("idAct"));
			listActivity.add(ua);
		}
		rs.close();
		cs.close();
		ConnectionManager.closeConnection(connection);
		return listActivity;
	}

	public void updateUserPasswordById(int id, String password) {
		Map<String, Object> updates = new HashMap<String, Object>();
		updates.put("password", password);
		try {
			updateById(id, updates);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// gryn
	public List<User> getActiveUsersAndGuidesInTheCountry(Integer countryId)
			throws SQLException {
		CountryDao countryDao = new CountryDao();
		Country country = countryDao.getCountryById(countryId);
		Integer pureId = country.getPureId();

		return getWithCustomQuery(ACTIVE_USERS_AND_GUIDES_IN_COUNTRY.replace(
				"?", pureId.toString()));
	}

	// gryn
	public void buildTagString(List<User> list) throws SQLException {
		if (list == null || list.isEmpty())
			return;

		Map<Integer, User> map = new HashMap<Integer, User>();

		StringJoiner join = new StringJoiner(",");
		for (User e : list) {
			join.add(e.getId().toString());

			map.put(e.getId(), e);

			e.setTagString("");

			e.setTagList(new ArrayList<String>());
		}

		Connection connection = ConnectionManager.getConnection();

		StringBuilder sb = new StringBuilder();

		System.out.println(GET_TAGS_BY_USERS.replace("?", join.toString()));

		PreparedStatement stmt = connection.prepareStatement(GET_TAGS_BY_USERS
				.replace("?", join.toString()));
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			User user = map.get(rs.getInt("id"));

			user.setTagString(sb.append(user.getTagString()).append("#")
					.append(rs.getString("tag_name")).append(" ").toString());

			user.getTagList().add(rs.getString("tag_name"));

			sb.setLength(0);
		}
		rs.close();
		stmt.close();
		ConnectionManager.closeConnection(connection);

	}

	public void saveUserLang(int userId, int langId) throws SQLException {
		Map<String, Object> updates = new HashMap<String, Object>();
		updates.put("lang_id", langId);
		try {
			updateById(userId, updates);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws SQLException {

		UserDao userDao = new UserDao();
		try {
			System.out.println(userDao.userActivity(2));
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// userDao.getActiveUsersAndGuidesInTheCountry(9).forEach(x ->
		// System.out.println(x.getFirstName()));
		//

		// try {
		// userDao.updateUserPasswordById(2, MD5HashPassword.getHashPassword(
		// "21212", "neutron80@list.ru"));
		// } catch (NoSuchAlgorithmException e) {
		//
		// e.printStackTrace();
		// }
	}

	public Integer saveUserAndRerutnId(User user)
			throws IllegalArgumentException, IllegalAccessException,
			SQLException {
		return super.saveAndReturnId(user);
	}
}