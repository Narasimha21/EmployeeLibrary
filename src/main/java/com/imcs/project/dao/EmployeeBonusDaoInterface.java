package com.imcs.project.dao;

import java.util.List;

import com.imcs.project.entities.EmployeeBonus;

public interface EmployeeBonusDaoInterface {
	void saveEmployeeBonus(List<EmployeeBonus> leb);

	void addEmployeeBonus(EmployeeBonus eb);
}
