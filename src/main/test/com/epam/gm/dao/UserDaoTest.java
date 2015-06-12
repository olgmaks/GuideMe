package com.epam.gm.dao;

import java.sql.SQLException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.epam.gm.daolayer.UserDao;
import com.epam.gm.daolayer.UserTypeDao;
import com.epam.gm.model.User;
import com.epam.gm.model.UserType;

public class UserDaoTest {

    private static UserDao userDao;
    private static UserType roleUser;

    @BeforeClass
    public static void bef() throws SQLException {
	userDao = new UserDao();
	roleUser = new UserTypeDao().getByField("name", "user").get(0);
    }

    @Ignore
    @Test
    public void testGetUsers() throws SQLException {
	System.out.println("userRole = " + roleUser);
	List<User> users = userDao.getAllUsers();
	for (User user : users) {
	    System.out.println(user);
	}
    }

    @Ignore
    @Test
    public void saveUser() throws IllegalArgumentException, IllegalAccessException, SQLException {
	User user = new User();
	user.setFirstName("Elon");
	user.setLastName("Mask");
	user.setEmail("elon.mask@gmail.com");
	user.setUserTypeId(roleUser.getId());
	user.setSex("male");
	user.setLangId(3);
	user.setCellNumber("+380635560235");
	user.setIsActive(true);
	user.setAddressId(1);
	user.setPassword("qwerty");

	userDao.save(user);
    }
    
    @Test
    public void testCustomQuery() throws SQLException{
	List<User> users= userDao.getUsersByCityName("kyiv");
	for (User user : users) {
	    System.out.println(user);
	}
    }

}
