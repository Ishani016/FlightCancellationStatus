package com.example.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySQLConnection {
	private Logger logger = LoggerFactory.getLogger(Logger.class);
	
	public Connection conn = null;
    public Statement stmt = null;
    
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/flight", "root", "pass@word1");
        return conn;
    }
    
}
