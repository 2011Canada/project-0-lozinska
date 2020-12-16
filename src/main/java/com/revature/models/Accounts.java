package com.revature.models;

import com.revature.exceptions.InsuficientAmmountException;

public class Accounts {
	private int accountId;

private int accountNumber;
private double accountBalance;
private String accountType;
//private BankCustomer bankCustomer;
private static int accountNumberGenerator=1000;
public Accounts() {
	this.accountNumber=accountNumberGenerator;
	accountNumberGenerator++;
};

public Accounts(double accountBalance, String type) {
	if(accountBalance>=0) {
		this.accountBalance=accountBalance;
		this.accountType=type;
		this.accountNumber=accountNumberGenerator;
		//this.bankCustomer.setUserId(userId);	
		accountNumberGenerator++;
	}
	
}
public int getAccountId() {
	return accountId;
}
public void setAccountId(int accountId) {
	this.accountId = accountId;
}
public int getAccountNumber() {
	return accountNumber;
}
public void setAccountNumber(int accountNumber) {
	this.accountNumber = accountNumber;
}
public double getAccountBalance() {
	return accountBalance;
}
public void setAccountBalance(double accountBalance) {
	this.accountBalance = accountBalance;
}
public String getAccountType() {
	return accountType;
}
public void setAccountType(String accountType) {
	this.accountType = accountType;
}
public String display() {
	return this.getAccountType()+" has balance of "+this.getAccountBalance();
}
}
