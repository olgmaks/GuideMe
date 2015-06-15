package com.epam.gm.olgmaks.absractdao.dbcontrol;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

public class ConnectionManager {
	private static Connection conn = null;

	private ConnectionManager() {

	}

	private static Connection getInstance() {
		if (conn == null) {
//			Properties prop = new Properties();
//			String propFileName = "src/main/resources/dbConnection.properties";
//			InputStream inputStream = null;
//			try {
//				inputStream = new FileInputStream(propFileName);
//				prop.load(inputStream);
//				BasicDataSource dataSource = new BasicDataSource();
//				String driver = prop.getProperty("driver");
//				String url = prop.getProperty("url");
//				String login = prop.getProperty("name");
//				String password = prop.getProperty("pass");
//
//				dataSource.setDriverClassName(driver);
//				dataSource.setUrl(url);
//				dataSource.setUsername(login);
//				dataSource.setPassword(password);
//
//				conn = dataSource.getConnection();
//			} catch (IOException | SQLException e) {
//				e.printStackTrace();
//			}
		
		
			BasicDataSource dataSource = new BasicDataSource();
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost/guideme";
			String login = "taras";
			String password = "1";

			dataSource.setDriverClassName(driver);
			dataSource.setUrl(url);
			dataSource.setUsername(login);
			dataSource.setPassword(password);

			try {
				conn = dataSource.getConnection();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}		
		
		}
		
		
		
		return conn;
	}

	public static Connection getConnection() {
		return getInstance();
	}

	public static void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
