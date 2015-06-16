package com.epam.gm.daolayer;

import com.epam.gm.model.User;
import com.epam.gm.model.UserType;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class UserDao extends AbstractDao<User> {

	private static final String USER_EMAIL_FIELD = "email";
	private static final String USER_TYPE_FIELD = "user_type_id";
	private static final String USER_FB_ID = "facebook_id";
	private static final String GET_USER_BY_CITY_NAME_SQL = "JOIN address ON user.address_id = address.id "
			+ "JOIN city ON address.city_id = city.id WHERE name = '%S'";
	@SuppressWarnings("unused")
	private static final String UPDATE = "";

	public UserDao() {
		// gryn
		// super(ConnectionManager.getConnection(), User.class);
		super(User.class);
	}

	public void saveUser(User user) throws IllegalArgumentException,
			IllegalAccessException, SQLException {
		super.save(user);
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
		List<User> result = getByField(USER_FB_ID, id);
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

	public static void main(String[] args) {
		UserDao userDao;
		UserType roleUser;

		try {

			userDao = new UserDao();
			roleUser = new UserTypeDao().getByField("name", "user").get(0);

			System.out.println("userRole = " + roleUser);
			List<User> users = userDao.getAllUsers();
			for (User user : users) {
				System.out.println(user);
			}

	

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
