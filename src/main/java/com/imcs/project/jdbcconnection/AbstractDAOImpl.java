package com.imcs.project.jdbcconnection;

import java.sql.Connection;

public abstract class AbstractDAOImpl {
	Connection connection;

	public Connection getConnection() {

		try {
			connection = JdbcConnectionFactory.getConnection();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

}
