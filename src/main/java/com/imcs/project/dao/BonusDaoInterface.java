package com.imcs.project.dao;

import java.util.List;

import com.imcs.project.entities.Bonus;

public interface BonusDaoInterface {

	void save(List<Bonus> list);

	Float get(int deptNo);

	List<Bonus> getBonusList();

	void updateBonus(float remainingAmount, int DeptNo);

	void addBonus(Bonus b);

	void saveBonus();
}
