package com.epam.gm.services;

import java.sql.SQLException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.gm.daolayer.UserService;
import com.epam.gm.daolayer.UserTypeDao;
import com.epam.gm.model.User;
import com.epam.gm.model.UserType;

public class UserServiceTest {

    private static UserService userService;
    private static UserType roleUser;

    @BeforeClass
    public static void bef() throws SQLException {
	userService = new UserService();
	
	//Returns list with UserTypes where field"name" = user
	//in this case we have only one result 
	roleUser = new UserTypeDao().getByField("name", "user").get(0);
    }

    @Test
    public void testGetUsers() {
	System.out.println("userRole = " + roleUser);
	List<User> users = userService.getAllUsers();
	for (User user : users) {
	    System.out.println(user);
	}
    }

    @Test
    public void saveUser() {
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

	userService.save(user);
    }
}
