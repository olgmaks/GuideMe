package com.epam.gm.daolayer;

import com.epam.gm.model.FriendUser;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by OLEG on 16.06.2015.
 */

public class FriendUserDao extends AbstractDao<FriendUser> {

    //fu is FriendUser pseudomane
    private static final String GET_USER_FRIENDS_SQL = "fu " +
            "WHERE fu.user_id = %S AND EXISTS (" +
            "SELECT * FROM friend_user fu1 WHERE fu.friend_id = fu1.user_id AND fu.user_id=fu1.friend_id)";

    private static  final String GET_USER_TO_FRIEND_REQUESTS_SQL = "fu " +
            "WHERE fu.user_id = %S AND NOT EXISTS (" +
            "SELECT * FROM friend_user fu1 WHERE fu.friend_id = fu1.user_id AND fu.user_id=fu1.friend_id)";

    private static final String GET_USER_FROM_FRIEND_REQUESTS_SQL = "fu " +
            "WHERE fu.friend_id = %S AND NOT EXISTS (" +
            "SELECT * FROM friend_user fu1 WHERE fu.friend_id = fu1.user_id AND fu.user_id=fu1.friend_id)";

    public FriendUserDao() {
        super(FriendUser.class);
    }

    public List<FriendUser> getUserFriends(int userId) throws SQLException {
        return super.getWithCustomQuery(String.format(GET_USER_FRIENDS_SQL, userId));
    }

    public List<FriendUser> getUserRequestsToFriends (int userId) throws SQLException {
        return super.getWithCustomQuery(String.format(GET_USER_TO_FRIEND_REQUESTS_SQL,userId));
    }

    public List<FriendUser> getUserRequestsFromFriends (int userId) throws SQLException {
        return super.getWithCustomQuery(String.format(GET_USER_FROM_FRIEND_REQUESTS_SQL,userId));
    }

    public void sendFriendRequest(int userId, int friendId) throws SQLException, IllegalAccessException {
        super.save(new FriendUser(userId, friendId));
    }

    public void sendFriendRequest(FriendUser friendUser) throws SQLException, IllegalAccessException {
        super.save(friendUser);
    }

    public void submitFriendRequest(FriendUser friendUser) throws SQLException, IllegalAccessException {
        int userId = friendUser.getUserId();
        int friendId = friendUser.getFriendId();
        super.save(new FriendUser(friendId, userId));
    }
}
