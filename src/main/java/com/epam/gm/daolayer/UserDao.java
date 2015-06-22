package com.epam.gm.daolayer;

import com.epam.gm.hashpassword.MD5HashPassword;
import com.epam.gm.model.FriendUser;
import com.epam.gm.model.User;
import com.epam.gm.model.UserActivity;
import com.epam.gm.olgmaks.absractdao.dbcontrol.ConnectionManager;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDao extends AbstractDao<User> {

    private static final String USER_EMAIL_FIELD = "email";
    private static final String USER_TYPE_FIELD = "user_type_id";
    private static final String USER_FACEBOOK_ID = "facebook_id";
    private static final String USER_VK_ID = "vk_id";
    private static final String GET_USER_BY_CITY_NAME_SQL = "JOIN address ON user.address_id = address.id "
            + "JOIN city ON address.city_id = city.id WHERE name = '%S'";

    private static final String SEARCH_USER_BY_NAME = "u " +
            "WHERE (u.First_name LIKE '%%%1$s%%' OR u.Last_name LIKE '%%%1$s%%')";

    private static final String SEARCH_USER_BY_CITY_NAME = "u " +
            "JOIN address a ON u.address_id = a.id JOIN city c ON a.city_id = c.pure_id " +
            "WHERE c.name LIKE '%s'";

    private static final String SEARCH_USER_BY_TAGS = "";


    public UserDao() {
        // gryn
        // super(ConnectionManager.getConnection(), User.class);
        super(User.class);
    }

    public void saveUser(User user) throws IllegalArgumentException,
            IllegalAccessException, SQLException {
        super.save(user);
    }

    public List<User> searchUserByName(String filterNameInput) throws SQLException {
        return super.getWithCustomQuery(String.format(SEARCH_USER_BY_NAME, filterNameInput));
    }

    public List<User> searchUserByCityName (String cityName) throws SQLException {
        return super.getWithCustomQuery(String.format(SEARCH_USER_BY_CITY_NAME, cityName));
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

    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        try {
            userDao.updateUserPasswordById(2, MD5HashPassword.getHashPassword(
                    "21212", "neutron80@list.ru"));
        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();
        }
    }
}