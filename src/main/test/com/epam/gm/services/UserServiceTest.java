package com.epam.gm.services;

import com.epam.gm.daolayer.UserTypeDao;
import com.epam.gm.model.User;
import com.epam.gm.model.UserType;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class UserServiceTest {

    private static UserService userService;
    private static UserType roleUser;

    @BeforeClass
    public static void bef() throws SQLException {
        userService = new UserService();

        // Returns list with UserTypes where field"name" = user
        // in this case we have only one result
        roleUser = new UserTypeDao().getByField("name", "user").get(0);
    }

    @Test
    public void getAvgMark () {
        try {
            System.out.println("avg mark : " + userService.getUserAvgMark(8));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Ignore
    @Test
    public void testGetUsers() throws SQLException {
        System.out.println("userRole = " + roleUser);
        
        List<User> users = userService.getAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Ignore
    @Test
    public void getUserByEmailTest() {
        try {
            System.out.println(userService.getUserByEmail("elon.mask@gmail.com"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Ignore
    @Test
    public void testUpdateAvatar() {
        System.out.println("update avatar test ...");
        try {
            userService.updateUserAvatar(8, 2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test@Ignore
    public void testIsUser() {
        try {
            Boolean b = userService.isUserPresent(8);
            System.out.println(b);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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

//	userService.save(user);
    }
}
