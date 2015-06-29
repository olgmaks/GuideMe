package com.epam.gm.daolayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.epam.gm.model.MessageUser;
import com.epam.gm.olgmaks.absractdao.dbcontrol.ConnectionManager;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class MessageUserDao extends AbstractDao<MessageUser> {
	private static final String GET_BY_USER = " mu WHERE (mu.sender_id = %S AND mu.user_id = %S) OR "
														+ " (mu.sender_id = %S AND mu.user_id = %S)";
	private static final String UPDATE_READ_MESSAGE = "WHERE sender_id = %S and user_id = %S and is_read = 0";																									
    
	private static final String GET_COUNT_UNREADED = "SELECT "
														+ "mu.sender_id as senderId, "
														+ "COUNT(*) as num "
													+ "FROM message_user mu "
													+ "WHERE mu.user_id = ? AND mu.is_read = 0 "
													+ "GROUP BY mu.sender_id";
	public MessageUserDao() {
    	//gryn
    	//super(ConnectionManager.getConnection(), MessageUser.class);
    	super(MessageUser.class);
    }
    public void save(MessageUser mu) throws IllegalArgumentException, IllegalAccessException, SQLException {
    	super.save(mu);
    }
    
    public List<MessageUser> getByUser(int userId, int senderId) throws SQLException{
    	List<MessageUser> list = super.getWithCustomQuery(String.format(
				GET_BY_USER, senderId, userId, userId, senderId));
    	return list;
    }
   
    public void updateRead (int senderId,int userId) throws SQLException{
    	Map<String, Object> updates = new HashMap<>();
    	updates.put("is_read", 1);
    	super.updateWithCustomQuery(updates, "",String.format(UPDATE_READ_MESSAGE, senderId, userId));
    }
    
    public Map<Integer, String> getCountUnreadeMessage(int userId) throws SQLException{
    	Map<Integer,String> map = new HashMap<>();
    	Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = connection.prepareStatement(GET_COUNT_UNREADED);
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
    public static void main(String[] args) throws SQLException {
		new MessageUserDao().updateRead(12,8);
	}
}
