package com.epam.gm.services;

import java.sql.SQLException;
import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Set;

import com.epam.gm.model.User;

public class UserServiceSearchTest {

    private static UserService userService;

    @BeforeClass
    public static void b(){
        userService =new UserService();
    }


    @Test
    public void searchTest() throws SQLException {
	System.out.println();
        String tags = "'music','cinema','camping','hiking','sport','nature','tourism'";
        Set<User> users = userService.searchUsers(8,"","","",0, UserService.SearchRole.all);

        for (User u : users) {
            System.out.println(u);
        }

    }
}

