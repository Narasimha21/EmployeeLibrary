package com.imcs.project.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.imcs.project.dao.EmployeeDaoImpl;
import com.imcs.project.dao.EmployeeDaoInterface;
import com.imcs.project.entities.Department;
import com.imcs.project.entities.Employee;
import com.imcs.project.entities.User;

public class EmployeeService {
	EmployeeDaoInterface edi = new EmployeeDaoImpl();
	EmployeeDaoInterface edao = new EmployeeDaoImpl();

	List<Department> deptList = new ArrayList<>();
	List<User> userList = new ArrayList<>();

	public List<Department> getDeptList() {
		deptList.add(new Department(10, "Finance"));
		deptList.add(new Department(20, "Operations"));
		deptList.add(new Department(30, "Account"));
		return deptList;

	}

	public Department getDept(int deptNo) {
		deptList = getDeptList();
		for (Department d : deptList) {
			if (d.getDeptNo() == deptNo) {
				return d;
			}
		}

		return null;

	}

	public List<User> getUserList() {
		userList.add(new User("scott", "tiger"));
		userList.add(new User("root", "root"));
		userList.add(new User("admin", "admin"));
		return userList;

	}

	public boolean validateUser(User user) {
		userList = getUserList();
		if (userList.contains(user)) {
			return true;
		}
		return false;
	}

	public Employee getEmployee(int empNo, int deptNo) {
		return edi.getEmployee(empNo, deptNo);
	}

	public List<Employee> getEmployeeByDeptNo(int deptNo) {
		return edao.getEmployeeByDeptNo(deptNo);
	}

	public void deleteEmployee(int empNo) {

		edao.deleteEmployee(empNo);
	}

	public void updateEmployee(int empNo, int deptNo, float salary, Date doj, Date dob, int salaryGrade) {

		edao.updateEmployee(empNo, deptNo, salary, doj, dob, salaryGrade);
	}

	public Employee getEmployee(int empNo) {
		return edao.getEmployee(empNo);

	}

	public int addEmployee(int deptNo, float salary, Date doj, Date dob, int salaryGrade) {
		return edao.addEmployee(deptNo, salary, doj, dob, salaryGrade);
	}
}
