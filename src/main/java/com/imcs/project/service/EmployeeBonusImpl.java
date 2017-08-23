package com.imcs.project.service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.imcs.project.dao.BonusDao;
import com.imcs.project.dao.BonusDaoInterface;
import com.imcs.project.dao.EmployeeBonusDaoImpl;
import com.imcs.project.dao.EmployeeBonusDaoInterface;
import com.imcs.project.dao.EmployeeDaoImpl;
import com.imcs.project.dao.EmployeeDaoInterface;
import com.imcs.project.entities.Bonus;
import com.imcs.project.entities.Employee;
import com.imcs.project.entities.EmployeeBonus;

public class EmployeeBonusImpl implements EmployeeBonusInterface {

	EmployeeDaoInterface edao = new EmployeeDaoImpl();
	EmployeeBonusDaoInterface ebdi = new EmployeeBonusDaoImpl();
	BonusDaoInterface boi = new BonusDao();
	// EmployeeInterface ei = new EmployeeImpl();
	static Logger logger = Logger.getLogger(EmployeeBonusImpl.class.getName());

	@Override
	public void allocate() {

		List<Bonus> blist = boi.getBonusList();
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		for (Bonus b : blist) {
			Thread t = new Thread() {
				public void run() {
					List<Employee> elist = edao.getEmployeeByDeptNo(b.getDeptNo());
					for (Employee e : elist) {
						giveBonus(e);

					}
				}
			};
			executorService.execute(t);
			logger.info("executing threading is : " + t);
		}
		executorService.shutdown();
	}

	private void giveBonus(Employee e) {
		// get remaining amount frm bonus service
		float remAmount = boi.get(e.getDeptNo());

		// calculate bonus for emp
		EmployeeBonus eb = new EmployeeBonus();
		if (remAmount > 0) {
			Float tempBonus = 0.0f;
			switch (e.getSalaryGrade()) {
			case 1:

				tempBonus = (float) (e.getSalary() * 0.1);

				break;

			case 2:

				tempBonus = (float) (e.getSalary() * 0.15);

				break;
			case 3:

				tempBonus = (float) (e.getSalary() * 0.2);

				break;
			case 4:

				tempBonus = (float) (e.getSalary() * 0.25);

				break;
			default:

			}
			if (tempBonus <= remAmount) {
				remAmount = remAmount - tempBonus;
				eb.setAmount(tempBonus);
				eb.setStatus("CMP");

			} else {
				eb.setAmount(remAmount);
				remAmount = 0;
				eb.setStatus("PAR");

			}
			eb.setEmpNo(e.getEmpNo());
			eb.setAllocatedDate(new Date());
		} else {

			eb.setStatus("INC");
			eb.setEmpNo(e.getEmpNo());
			eb.setAmount(0);
			eb.setAllocatedDate(new Date());

		}
		addEmployeeBonus(eb);
		boi.updateBonus(remAmount, e.getDeptNo());
		// check for remaining amount -bonus
		// add emp bonus using empbonus service

	}

	@Override
	public void addEmployeeBonus(EmployeeBonus eb) {

		ebdi.addEmployeeBonus(eb);
	}
	// ebdi.saveEmployeeBonus(leb);

}
