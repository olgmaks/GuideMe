package com.epam.gm.daolayer;

import java.sql.SQLException;
import java.util.List;

import com.epam.gm.model.User;
import com.epam.gm.olgmaks.absractdao.dbcontrol.ConnectionManager;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class UserDao extends AbstractDao<User> {

    private static final String USER_EMAIL_FIELD = "email";

    public UserDao() {
	super(ConnectionManager.getConnection(), User.class);
    }

    public void saveUser(User user) {
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

    public List<User> getAllUsers() {
	return getAll();
    }

}
