package com.epam.gm.olgmaks.absractdao.dbcontrol;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.sql.DataSource;

public class ConnectionManager {
	// Database credentials
	public static final String DB_URL = "jdbc:mysql://localhost/guideme";
	public static final String USER = "root";
	public static final String PASS = "1234";
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final boolean USE_POOL = true;

	private static GuideMeConnectionPool pool;
	private static DataSource dataSource;

	private static Connection conn;

	private ConnectionManager() {
	}

	public static void closeConnection(Connection conn) throws SQLException {
		if (USE_POOL) {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public static Connection getConnection() {

		if (USE_POOL) {

			if (pool == null) {
				pool = new GuideMeConnectionPool();
				try {
					dataSource = pool.setUp();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			try {
				conn = dataSource.getConnection();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			// pool.printStatus();

		} else {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (conn == null) {
				try {
					conn = DriverManager.getConnection(DB_URL, USER, PASS);
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}

		// System.out.println("Connect: " + conn);
		return conn;
	}

}
