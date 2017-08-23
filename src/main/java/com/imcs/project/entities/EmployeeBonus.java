package com.imcs.project.entities;

import java.util.Date;

public class EmployeeBonus {
	private int empNo;
	private float amount;
	private String status;
	private Date allocatedDate;

	public EmployeeBonus() {
		super();
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public Date getAllocatedDate() {
		return allocatedDate;
	}

	public void setAllocatedDate(Date allocatedDate) {
		this.allocatedDate = allocatedDate;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public EmployeeBonus(int empNo, float amount, String status, Date allocatedDate) {
		super();
		this.empNo = empNo;
		this.amount = amount;
		this.status = status;
		this.allocatedDate = allocatedDate;
	}

}
