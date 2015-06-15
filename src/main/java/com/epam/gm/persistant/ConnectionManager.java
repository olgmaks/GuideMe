package com.epam.gm.persistant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Deprecated
public class ConnectionManager {
		private static Connection conn;
	 	private static ConnectionManager db;
	    private ConnectionManager() {
	        String url= "----------";
	        String dbName = "-------";
	        //String driver = "com.mysql.jdbc.Driver";
	        String userName = "--------";
	        String password = "---------";
	        try {
	          conn = DriverManager.getConnection(url+dbName,userName,password);
	        }
	        catch (Exception sqle) {
	            sqle.printStackTrace();
	        }
	    }

	    public static synchronized ConnectionManager getInstanse() {
	        if ( db == null ) {
	            db = new ConnectionManager();
	        }
	        return db;	 
	    }
	    
	    public static Connection getConnection(){
	    	return conn;
	    }
	    public static void release(PreparedStatement ps,ResultSet rs) {  
	        try {  
	            if (conn != null) {  
	                conn.close();  
	                conn = null;  
	            }  
	            if (ps != null) {  
	                ps.close();  
	                ps = null;  
	            }  
	            if (rs != null) {  
	                rs.close();  
	                rs = null;  
	            }  
	        } catch (SQLException e) {  
	            e.printStackTrace();  
	        }  
	        
	        
	        
	    }  
}

