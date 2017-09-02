package com.imcs.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.imcs.project.entities.Employee;
import com.imcs.project.jdbcconnection.AbstractDAOImpl;
import com.imcs.project.jdbcconnection.JdbcConnectionFactory;
import com.mysql.jdbc.Statement;

public class EmployeeDaoImpl extends AbstractDAOImpl implements EmployeeDaoInterface {

	public List<Employee> getEmployeeByDeptNo(int deptNo) {
		// TODO Auto-generated method stub

		if (deptNo % 2 == 0) {
			return getEmpByDeptNo(deptNo, "doB");

		}

		return getEmpByDeptNo(deptNo, "doJ");
	}

	@Override
	public List<Employee> getEmpByDeptNo(int deptNo, String orderby) {
		List<Employee> list = new ArrayList<>();
		ResultSet rs = null;
		try (Connection connection = getConnection();

				PreparedStatement statement = connection.prepareStatement(
						"select empNo,deptNo,salary,doJ,doB,salaryGrade,status from imcs.empl where deptNo=?  order by ?")) {

			statement.setInt(1, deptNo);
			statement.setString(2, orderby);
			rs = statement.executeQuery();
			while (rs.next()) {

				list.add(new Employee(rs.getInt("empNo"), rs.getInt("deptNo"), rs.getFloat("salary"), rs.getDate("doJ"),
						rs.getDate("doB"), rs.getInt("salaryGrade"), rs.getString("status")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;

	}

	@Override
	public Employee getEmployee(int empNo, int deptNo) {
		Employee emp = new Employee();
		ResultSet rs = null;
		try (Connection connection = getConnection();

				PreparedStatement statement = connection.prepareStatement(
						"select empNo,deptNo,salary,doJ,doB,salaryGrade,status from imcs.empl where empNo=? and deptNo=?")) {

			statement.setInt(1, empNo);
			statement.setInt(2, deptNo);

			rs = statement.executeQuery();
			while (rs.next()) {

				emp = new Employee(rs.getInt("empNo"), rs.getInt("deptNo"), rs.getFloat("salary"), rs.getDate("doJ"),
						rs.getDate("doB"), rs.getInt("salaryGrade"), rs.getString("status"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return emp;

	}

	@Override
	public int addEmployee(int deptNo, float salary, Date doj, Date dob, int salaryGrade) {
		int empId = -1;

		try (Connection connection = JdbcConnectionFactory.getConnection();

				PreparedStatement statement = connection

						.prepareStatement("INSERT INTO imcs.empl(deptNo,salary,doJ,doB,salaryGrade) VALUES(?,?,?,?,?)",
								Statement.RETURN_GENERATED_KEYS)) {
			Employee emp = new Employee();
			statement.setInt(1, deptNo);
			statement.setFloat(2, salary);
			statement.setDate(3, new java.sql.Date(doj.getTime()));
			statement.setDate(4, new java.sql.Date(dob.getTime()));
			statement.setInt(5, salaryGrade);
			statement.executeUpdate();
			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					empId = generatedKeys.getInt(1);
				} else {
					throw new SQLException("Creating user failed, no ID obtained.");
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return empId;
	}

	@Override
	public void deleteEmployee(int empNo) {
		try (Connection connection = JdbcConnectionFactory.getConnection();

				PreparedStatement statement = connection.prepareStatement("delete from imcs.empl where empNo=? ")) {
			statement.setInt(1, empNo);
			statement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void updateEmployee(int empNo, int deptNo, float salary, Date doj, Date dob, int salaryGrade) {
		try (Connection connection = JdbcConnectionFactory.getConnection();

				PreparedStatement statement = connection.prepareStatement(
						"update imcs.empl set deptNo=?,salary=?,doj=?,dob=?,salaryGrade=? where empNo=? ")) {

			statement.setInt(1, deptNo);
			statement.setFloat(2, salary);
			statement.setDate(3, new java.sql.Date(doj.getTime()));
			statement.setDate(4, new java.sql.Date(dob.getTime()));
			statement.setInt(5, salaryGrade);
			statement.setInt(6, empNo);
			statement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Employee getEmployee(int empNo) {
		Employee emp = new Employee();
		ResultSet rs = null;
		try (Connection connection = getConnection();

				PreparedStatement statement = connection.prepareStatement(
						"select empNo,deptNo,salary,doJ,doB,salaryGrade,status from imcs.empl where empNo=? ")) {

			statement.setInt(1, empNo);

			rs = statement.executeQuery();
			if (rs.next()) {

				emp = new Employee(rs.getInt("empNo"), rs.getInt("deptNo"), rs.getFloat("salary"), rs.getDate("doJ"),
						rs.getDate("doB"), rs.getInt("salaryGrade"), rs.getString("status"));
			} else {
				emp = null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return emp;
	}

}
