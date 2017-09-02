package com.imcs.project.dao;

import java.util.Date;
import java.util.List;

import com.imcs.project.entities.Employee;

public interface EmployeeDaoInterface {

	List<Employee> getEmpByDeptNo(int deptNo, String orderby);

	List<Employee> getEmployeeByDeptNo(int deptNo);

	Employee getEmployee(int empNo, int deptNo);

	int addEmployee(int deptNo, float salary, Date doj, Date dob, int salaryGrade);

	void deleteEmployee(int empNo);

	void updateEmployee(int empNo, int deptNo, float salary, Date doj, Date dob, int salaryGrade);

	Employee getEmployee(int empNo);
}
