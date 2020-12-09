package com.revature.models;

public class Accounts {
private int accountNumber;
private double accountBalance;
private String accountType;
private static int accountNumberGenerator=1000;

public Accounts(int accountNumber,double accountBalance) {
	if(accountBalance>=0) {
		this.accountBalance=accountBalance;
		this.accountNumber=accountNumberGenerator+1;
		accountNumberGenerator++;
	}
	
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
}
