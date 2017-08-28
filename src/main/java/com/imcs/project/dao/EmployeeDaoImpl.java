package com.imcs.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.imcs.project.entities.Employee;
import com.imcs.project.jdbcconnection.AbstractDAOImpl;

public class EmployeeDaoImpl extends AbstractDAOImpl implements EmployeeDaoInterface {

	@Override
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

				emp =	new Employee(rs.getInt("empNo"), rs.getInt("deptNo"), rs.getFloat("salary"), rs.getDate("doJ"),
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

}
