package com.epam.gm.services;

import com.epam.gm.daolayer.FriendUserDao;
import com.epam.gm.model.FriendUser;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by OLEG on 16.06.2015.
 */
public class FriendUserService {

    private FriendUserDao friendUserDao;

    public FriendUserService() {
    }

    public List<FriendUser> getUserFriends(int userId) throws SQLException {
        return friendUserDao.getUserFriends(userId);
    }

    public List<FriendUser> getUserRequestsToFriends(int userId) throws SQLException {
        return friendUserDao.getUserRequestsToFriends(userId);
    }

    public List<FriendUser> getUserRequestsFromFriends(int userId) throws SQLException {
        return friendUserDao.getUserRequestsFromFriends(userId);
    }

    public void sendFriendRequest(int userId, int friendId) throws SQLException, IllegalAccessException {
        friendUserDao.sendFriendRequest(userId, friendId);
    }

    public void sendFriendRequest(FriendUser friendUser) throws SQLException, IllegalAccessException {
        friendUserDao.sendFriendRequest(friendUser);
    }

    public void submitFriendRequest(FriendUser friendUser) throws SQLException, IllegalAccessException {
        friendUserDao.submitFriendRequest(friendUser);
    }
}
