package com.epam.gm.daolayer;

import java.sql.SQLException;
import java.util.List;

import com.epam.gm.model.User;
import com.epam.gm.olgmaks.absractdao.dbcontrol.ConnectionManager;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class UserDao extends AbstractDao<User> {

    private static final String USER_EMAIL_FIELD = "email";
    private static final String GET_USER_BY_CITY_NAME_SQL = "JOIN address ON user.address_id = address.id "
    	+ "JOIN city ON address.city_id = city.id WHERE name = '%S'";
    private static final String UPDATE ="";

    public UserDao() {
	super(ConnectionManager.getConnection(), User.class);
    }

    public void saveUser(User user) throws IllegalArgumentException, IllegalAccessException, SQLException {
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
    
    public List<User> getUsersByCityName (String cityName) throws SQLException {
	return customQuery(String.format(GET_USER_BY_CITY_NAME_SQL, cityName));
    }

    public List<User> getAllUsers() throws SQLException {
	return getAll();
    }


	public void activeUser(int userId) throws SQLException, IllegalAccessException {
		callStoredProcedure("{call confirmUnconfirmUser(?)}", String.valueOf(userId));

	}
}
