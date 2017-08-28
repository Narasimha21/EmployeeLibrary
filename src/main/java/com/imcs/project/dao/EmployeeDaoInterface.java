package com.imcs.project.dao;

import java.util.List;

import com.imcs.project.entities.Employee;

public interface EmployeeDaoInterface {

	List<Employee> getEmpByDeptNo(int deptNo, String orderby);

	List<Employee> getEmployeeByDeptNo(int deptNo);
	
	Employee getEmployee(int empNo,int deptNo);
	
}
