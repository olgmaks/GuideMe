package com.epam.gm.dao;

import com.epam.gm.daolayer.FriendUserDao;
import com.epam.gm.model.FriendUser;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by OLEG on 16.06.2015.
 */
public class FriendUserDaoTest {

    private static FriendUserDao friendUserDao;

    @BeforeClass
    public static void b() {
        friendUserDao = new FriendUserDao();
    }

    @Test
    public void testGetUserFriends() throws SQLException {
        System.out.println("\nuser friends test");
        List<FriendUser> friendUsers = friendUserDao.getUserFriends(8);
        for (FriendUser friendUser : friendUsers) {
            System.out.println(friendUser);
        }
    }

    @Test
    public void testOutcommingRequests() throws SQLException {
        System.out.println("\nout come appcications friends test");
        List<FriendUser> friendUsers = friendUserDao.getUserRequestsToFriends(8);
        for (FriendUser friendUser : friendUsers) {
            System.out.println(friendUser);
        }
    }

    @Test
    public void testIncommingRequests() throws SQLException {
        System.out.println("\nin come appcications friends test");
        List<FriendUser> friendUsers = friendUserDao.getUserRequestsFromFriends(8);
        for (FriendUser friendUser : friendUsers) {
            System.out.println(friendUser);
        }
    }
}
