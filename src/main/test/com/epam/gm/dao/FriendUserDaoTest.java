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

    @Ignore
    @Test
    public void testGetUserFriends() throws SQLException {
        System.out.println("\nuser friends test");
        List<FriendUser> friendUsers = friendUserDao.getUserFriends(8);
        for (FriendUser friendUser : friendUsers) {
            System.out.println(friendUser);
        }
    }

    @Ignore
    @Test
    public void testOutcommingRequests() throws SQLException {
        System.out.println("\nout come appcications friends test");
        List<FriendUser> friendUsers = friendUserDao.getUserRequestsToFriends(8);
        for (FriendUser friendUser : friendUsers) {
            System.out.println(friendUser);
        }
    }

    @Ignore
    @Test
    public void testIncommingRequests() throws SQLException {
        System.out.println("\nin come appcications friends test");
        List<FriendUser> friendUsers = friendUserDao.getUserRequestsFromFriends(8);
        for (FriendUser friendUser : friendUsers) {
            System.out.println(friendUser);
        }
    }

    @Ignore
    @Test
    public void queryBuildingTest() {
        String GET_USER_FRIENDS_WITH_FILTER = "SELECT * FROM friend_user fu " +
                "JOIN user u ON fu.friend_id = u.id " +
                "JOIN address a ON u.address_id = a.id " +
                "JOIN city c ON a.city_id = c.id " +
                "WHERE fu.user_id = %1$s AND EXISTS (" +
                "SELECT * FROM friend_user fu1 WHERE fu.friend_id = fu1.user_id AND fu.user_id=fu1.friend_id " +
                "AND u.first_name RLIKE '.*%2$s.*' OR u.last_name RLIKE '.*%2$s.*' OR c.name RLIKE '.*%2$s.*');";
        System.out.println(String.format(GET_USER_FRIENDS_WITH_FILTER, 8, "w"));
    }

    @Test
    public void testFiter () throws SQLException {
        List <FriendUser> friendUsers = friendUserDao.filterUserFriends(8,"e");
        for (FriendUser friendUser : friendUsers) {
            System.out.println(friendUser);
        }
    }
}
