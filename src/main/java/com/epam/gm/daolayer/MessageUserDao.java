package com.epam.gm.daolayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.epam.gm.model.MessageUser;
import com.epam.gm.model.User;
import com.epam.gm.olgmaks.absractdao.dbcontrol.ConnectionManager;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;
import com.epam.gm.services.UserService;

public class MessageUserDao extends AbstractDao<MessageUser> {
	private static final String GET_BY_USER = " mu WHERE (mu.sender_id = %S AND mu.user_id = %S) OR "
			+ " (mu.sender_id = %S AND mu.user_id = %S)";
	private static final String UPDATE_READ_MESSAGE = "WHERE sender_id = %S and user_id = %S and is_read = 0";

	private static final String GET_COUNT_UNREADED = "SELECT "
			+ "mu.sender_id as senderId, " + "COUNT(*) as num "
			+ "FROM message_user mu "
			+ "WHERE mu.user_id = ? AND mu.is_read = 0 "
			+ "GROUP BY mu.sender_id";
	private String GET_LAST_MESSANGER = "SELECT " + "CASE sender_id "
			+ "WHEN ? THEN user_id " + "ELSE sender_id END  AS user  "
			+ "FROM message_user mu  " + "WHERE mu.user_id = ? OR "
			+ "mu.sender_id = ?  " + "ORDER BY mu.created_on DESC LIMIT 1";
	private String GET_NEW_NUMBER_MESSAGE = "SELECT COUNT(*) AS num "
			+ "FROM message_user mu " + "WHERE mu.user_id = %S  "
			+ "GROUP BY mu.sender_id ";
	private String GET_MESSAGE_TO_ADMIN = "mu JOIN  user u ON mu.user_id WHERE u.user_type_id = 1";
	private String SEND_MESSAGE_TO_ADMIN = "insert into message_user(sender_id, user_id, message) select ?, id, ? FROM user u WHERE u.user_type_id = 1";

	public MessageUserDao() {
		// gryn
		// super(ConnectionManager.getConnection(), MessageUser.class);
		super(MessageUser.class);
	}

	public void save(MessageUser mu) throws IllegalArgumentException,
			IllegalAccessException, SQLException {
		super.save(mu);
	}

	public List<MessageUser> getByUser(int userId, int senderId)
			throws SQLException {
		List<MessageUser> list = super.getWithCustomQuery(String.format(
				GET_BY_USER, senderId, userId, userId, senderId));
		return list;
	}

	public void updateRead(int senderId, int userId) throws SQLException {
		Map<String, Object> updates = new HashMap<>();
		updates.put("is_read", 1);
		super.updateWithCustomQuery(updates, "",
				String.format(UPDATE_READ_MESSAGE, senderId, userId));
	}

	public Map<Integer, String> getCountUnreadeMessage(int userId)
			throws SQLException {
		Map<Integer, String> map = new HashMap<>();
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = connection
				.prepareStatement(GET_COUNT_UNREADED);
		stmt.setInt(1, userId);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			map.put(rs.getInt("senderId"), String.valueOf(rs.getInt("num")));
		}
		rs.close();
		stmt.close();
		ConnectionManager.closeConnection(connection);
		return map;
	}

	public User getLastMessanger(int userId) throws SQLException {
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = connection
				.prepareStatement(GET_LAST_MESSANGER);
		stmt.setInt(1, userId);
		stmt.setInt(2, userId);
		stmt.setInt(3, userId);
		ResultSet rs = stmt.executeQuery();
		int friendId = -1;
		while (rs.next()) {
			friendId = rs.getInt(1);
			System.out.println(friendId);
		}
		rs.close();
		stmt.close();
		ConnectionManager.closeConnection(connection);
		return new UserService().getUserById(friendId);
	}

	public void deleteById(int id) throws IllegalAccessException, SQLException {
		super.deleteByField("id", id);
	}

	public Integer getNewMessageUser(int userId) throws SQLException {
		return super.getInteger(String.format(GET_NEW_NUMBER_MESSAGE, userId));

	}

	public static void main(String[] args) throws SQLException {
		System.out.println(new MessageUserDao().getMessageToAdmin());
	}

	public List<MessageUser> getMessageToAdmin() throws SQLException {
		return super.getWithCustomQuery(GET_MESSAGE_TO_ADMIN);
	}

	public void sendMessageToAdmin(int userId, String message)
			throws SQLException {
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = connection
				.prepareStatement(SEND_MESSAGE_TO_ADMIN);
		stmt.setInt(1, userId);
		stmt.setString(2, message);
		stmt.executeUpdate();
		stmt.close();
		ConnectionManager.closeConnection(connection);
	}
}
