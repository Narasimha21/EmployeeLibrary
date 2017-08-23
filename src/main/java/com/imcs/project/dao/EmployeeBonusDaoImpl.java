package com.imcs.project.dao;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.imcs.project.entities.EmployeeBonus;
import com.imcs.project.jdbcconnection.JdbcConnectionFactory;

public class EmployeeBonusDaoImpl implements EmployeeBonusDaoInterface {
	static Logger logger = Logger.getLogger(EmployeeBonusDaoImpl.class.getName());

	@Override
	public void saveEmployeeBonus(List<EmployeeBonus> leb) {
		try (Connection connection = JdbcConnectionFactory.getConnection();

				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO imcs.employee_bonus VALUES(?,?,?,?)")) {
			connection.setAutoCommit(false);

			for (EmployeeBonus eb : leb) {
				statement.setInt(1, eb.getEmpNo());
				statement.setFloat(2, eb.getAmount());
				statement.setString(3, eb.getStatus());
				statement.setDate(4, new java.sql.Date(eb.getAllocatedDate().getTime()));

				statement.addBatch();
			}

			statement.executeBatch();

			logger.info("Executing batch for employee bonus ");

			connection.commit();
		} catch (BatchUpdateException b) {
			System.out.println(b.getMessage());
			b.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void addEmployeeBonus(EmployeeBonus eb) {
		try (Connection connection = JdbcConnectionFactory.getConnection();

				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO imcs.employee_bonus VALUES(?,?,?,?)");) {

			statement.setInt(1, eb.getEmpNo());
			statement.setFloat(2, eb.getAmount());
			statement.setString(3, eb.getStatus());
			statement.setDate(4, new java.sql.Date(eb.getAllocatedDate().getTime()));
			statement.execute();
			logger.info("Executing addEmployee bonus ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
