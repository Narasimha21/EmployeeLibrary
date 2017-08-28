package com.imcs.project.service;

import com.imcs.project.dao.EmployeeDaoImpl;
import com.imcs.project.dao.EmployeeDaoInterface;
import com.imcs.project.entities.Employee;

public class Service {
	EmployeeDaoInterface edi = new EmployeeDaoImpl();
	public Employee getEmployee(int empNo,int deptNo){
		
		return edi.getEmployee(empNo, deptNo);
	}
	
}
