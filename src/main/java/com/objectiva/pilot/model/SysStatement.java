package com.objectiva.pilot.model;

import java.time.LocalDate;

public class SysStatement {
	private int id;
	private int accountId;
	private LocalDate dateField;
	private float amount;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public LocalDate getDateField() {
		return dateField;
	}
	public void setDateField(LocalDate dateField) {
		this.dateField = dateField;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
}
