package com.revature.models;

public class BankCustomer extends BankUser{
public BankCustomer() {}
	public BankCustomer(String username, String password) {
		super(username, password);
		this.setRole("customer");
	}
	public String display() {
		return "Hello "+this.getUsername();
	}

}
