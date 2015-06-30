package com.epam.gm.daolayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.epam.gm.olgmaks.absractdao.dbcontrol.ConnectionManager;

public class UserLoginingDao {
	private String INSERT = "insert into user_logining (user_id) values(?)";
	public void save(int userId) throws SQLException{
    	Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = connection.prepareStatement(INSERT);
		stmt.setInt(1, userId);
		stmt.executeUpdate();
		stmt.close();
		ConnectionManager.closeConnection(connection);

    }
}
