package com.epam.gm.olgmaks.absractdao.dbcontrol;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;





public class GuideMeConnectionPool {

	private static int MAX_ACTIVE = 100;
	
	private GenericObjectPool connectionPool = null;
	
    public DataSource setUp() throws Exception {
        //
        // Load JDBC Driver class.
        //
        Class.forName(ConnectionManager.DRIVER).newInstance();

        //
        // Creates an instance of GenericObjectPool that holds our
        // pool of connections object.
        //
        connectionPool = new GenericObjectPool();
        connectionPool.setMaxActive(GuideMeConnectionPool.MAX_ACTIVE);

        //
        // Creates a connection factory object which will be use by
        // the pool to create the connection object. We passes the
        // JDBC url info, username and password.
        //
        ConnectionFactory cf = new DriverManagerConnectionFactory(
                ConnectionManager.DB_URL,
                ConnectionManager.USER,
                ConnectionManager.PASS);

        //
        // Creates a PoolableConnectionFactory that will wraps the
        // connection object created by the ConnectionFactory to add
        // object pooling functionality.
        //
        PoolableConnectionFactory pcf =
                new PoolableConnectionFactory(cf, connectionPool,
                        null, null, false, true);
        return new PoolingDataSource(connectionPool);
    }
    
    public GenericObjectPool getConnectionPool() {
        return connectionPool;
    }

    public static void main(String[] args) throws Exception {
    	GuideMeConnectionPool demo = new GuideMeConnectionPool();
        DataSource dataSource = demo.setUp();
        demo.printStatus();

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = dataSource.getConnection();
            demo.printStatus();

            stmt = conn.prepareStatement("SELECT * FROM user");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("login: " + rs.getString("email"));
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        demo.printStatus();
    }

    /**
     * Prints connection pool status.
     */
    public void printStatus() {
        System.out.println("Max   : " + getConnectionPool().getMaxActive() + "; " +
            "Active: " + getConnectionPool().getNumActive() + "; " +
            "Idle  : " + getConnectionPool().getNumIdle());
    }
}
