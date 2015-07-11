package com.epam.gm.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

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
        @SuppressWarnings("unused")
        long t1 = System.currentTimeMillis();
	    String tags = "'music','cinema','camping','hiking','sport','nature','tourism'";

        List<User> users = userService.searchUsers(8,"","","","",tags, UserService.SearchRole.all);
        long t2 = System.currentTimeMillis();

        System.out.println("time = " + (t2-t1));

        for (User u : users) {
            System.out.println(u);
        }

    }
}

