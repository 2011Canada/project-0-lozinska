package com.revature.models;

public class BankCustomer extends BankUser{

	public BankCustomer(String username, String password) {
		super(username, password);
		this.setRole("customer");
	}


}
