package com.imcs.project.jdbcconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class JdbcConnectionFactory {

	private static JdbcConnectionFactory connectionFactory;

	private Connection connection;

	private JdbcConnectionFactory() {

	}

	public static JdbcConnectionFactory createInstance() {
		if (connectionFactory == null) {
			connectionFactory = new JdbcConnectionFactory();
		}

		return connectionFactory;
	}

	public static Connection getConnection() {
		return createInstance().createConnection();
	}

	private Connection createConnection() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/imcs", "root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return connection;
	}

}
