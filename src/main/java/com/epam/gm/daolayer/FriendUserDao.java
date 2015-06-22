package com.epam.gm.daolayer;

import com.epam.gm.model.FriendUser;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by OLEG on 16.06.2015.
 */

public class FriendUserDao extends AbstractDao<FriendUser> {

    //fu is FriendUser pseudomane
    private static final String GET_USER_FRIENDS_SQL = "fu " +
            "WHERE fu.user_id = %S AND EXISTS (" +
            "SELECT * FROM friend_user fu1 WHERE fu.friend_id = fu1.user_id AND fu.user_id=fu1.friend_id)";

    private static final String GET_USER_TO_FRIEND_REQUESTS_SQL = "fu " +
            "WHERE fu.user_id = %S AND NOT EXISTS (" +
            "SELECT * FROM friend_user fu1 WHERE fu.friend_id = fu1.user_id AND fu.user_id=fu1.friend_id)";

    private static final String GET_USER_FROM_FRIEND_REQUESTS_SQL = "fu " +
            "WHERE fu.friend_id = %S AND NOT EXISTS (" +
            "SELECT * FROM friend_user fu1 WHERE fu.friend_id = fu1.user_id AND fu.user_id=fu1.friend_id)";

    String GET_USER_FRIENDS_WITH_FILTER = "fu " +
            "JOIN user u ON fu.friend_id = u.id " +
            "JOIN address a ON u.address_id = a.id " +
            "JOIN city c ON a.city_id = c.id " +
            "WHERE fu.user_id = %1$s AND EXISTS (" +
            "SELECT * FROM friend_user fu1 WHERE fu.friend_id = fu1.user_id AND fu.user_id=fu1.friend_id " +
//            "AND (u.first_name RLIKE '.*%2$s.*' OR u.last_name RLIKE '.*%2$s.*' OR c.name RLIKE '.*%2$s.*'));";
            "AND (u.First_name LIKE '%%%2$s%%' OR u.Last_name LIKE '%%%2$s%%' OR c.name LIKE '%%%2$s%%'));";

    public FriendUserDao() {
        super(FriendUser.class);
    }

    public List<FriendUser> getUserFriends(int userId) throws SQLException {
        return super.getWithCustomQuery(String.format(GET_USER_FRIENDS_SQL, userId));
    }

    public List<FriendUser> getUserRequestsToFriends(int userId) throws SQLException {
        return super.getWithCustomQuery(String.format(GET_USER_TO_FRIEND_REQUESTS_SQL, userId));
    }

    public List<FriendUser> getUserRequestsFromFriends(int userId) throws SQLException {
        return super.getWithCustomQuery(String.format(GET_USER_FROM_FRIEND_REQUESTS_SQL, userId));
    }

    public void sendFriendRequest(int userId, int friendId) throws SQLException, IllegalAccessException {
        super.save(new FriendUser(userId, friendId));
    }

    public void sendFriendRequest(FriendUser friendUser) throws SQLException, IllegalAccessException {
        super.save(friendUser);
    }

    public void acceptFriendRequest(FriendUser friendUser) throws SQLException, IllegalAccessException {
        System.out.println("acceptFriendRequest(FriendUser friendUser) ");
        int userId = friendUser.getUserId();
        int friendId = friendUser.getFriendId();
        FriendUser fu = new FriendUser(friendId, userId);
        System.out.println("prepare saving : " + fu);
        super.save(fu);
    }

    public void acceptFriendRequest(int requestId) throws SQLException, IllegalAccessException {
        System.out.println("acceptFriendRequest(int requestId) ");
        FriendUser friendUser = super.getByField("id", requestId).get(0);
        acceptFriendRequest(friendUser);
    }

    public void declineFriendRequest(int requestId) throws SQLException, IllegalAccessException {
        super.deleteByField("id", requestId);
    }

    public void callBackFriendUserRequest(int friendUserRequestId) throws SQLException, IllegalAccessException {
        super.deleteByField("id", friendUserRequestId);
    }

    public void removeFriend(int friendId) throws SQLException, IllegalAccessException {
        super.deleteByField("id", friendId);
    }
    
    //gryn
    public List<FriendUser> getUserFavorites(Integer userId) throws SQLException {
    	return getByField("user_id", userId);
    }
    //filtering producing by f-name, l-name, city-name input matching
    public List<FriendUser> filterUserFriends(int userId, String filterInput) throws SQLException {
        List<FriendUser> results = new ArrayList<>();
        String sql = String.format(GET_USER_FRIENDS_WITH_FILTER, userId, filterInput);
        results = super.getWithCustomQuery(sql);
        return results;
    }

}
