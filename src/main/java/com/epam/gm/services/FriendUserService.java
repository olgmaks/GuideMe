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
        friendUserDao = new FriendUserDao();
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

    public void acceptFriendRequest(FriendUser friendUser) throws SQLException, IllegalAccessException {
        friendUserDao.acceptFriendRequest(friendUser);
    }

    public void acceptFriendRequest(int requestId) throws SQLException, IllegalAccessException {
        System.out.println("accept friend request service");
        friendUserDao.acceptFriendRequest(requestId);
    }

    public void declineFriendRequest (int requestId) throws SQLException, IllegalAccessException {
        friendUserDao.declineFriendRequest(requestId);
    }

    public void callBackFriendUserRequest(int friendUserRequestId) throws SQLException, IllegalAccessException {
        friendUserDao.callBackFriendUserRequest(friendUserRequestId);
    }
}
