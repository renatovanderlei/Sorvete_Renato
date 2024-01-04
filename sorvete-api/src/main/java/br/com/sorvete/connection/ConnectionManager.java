package br.com.sorvete.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class ConnectionManager {

	private static final String USER = "root";
	private static final String PASSWORD = "admin";
	
	public static Connection getConnection() throws SQLException {
		String url = "jdbc:mysql://127.0.0.1:3306/sorvete";
		
		Properties properties = new Properties();
		properties.setProperty("user", USER);
		properties.setProperty("password", PASSWORD);
		
		return DriverManager.getConnection(url, properties);
	}

	protected static Connection getNewConnection() throws SQLException {
		String url = "jdbc:mysql://127.0.0.1:3306/sorvete";
		return DriverManager.getConnection(url, USER, PASSWORD);
	}
}
