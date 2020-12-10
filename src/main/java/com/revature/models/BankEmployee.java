package com.revature.models;

public class BankEmployee extends BankUser {
public BankEmployee(String username, String password) {
	super(username,password);
	this.setRole("employee");
}
}
