package com.revature.models;

import com.revature.exceptions.InsuficientAmmountException;

public class Savings extends Accounts{

	public Savings(double accountBalance,String username) {
		super(accountBalance,username);
		this.setAccountType("saving");
	}
public void transferMoneyTo(double ammountToTransfer, int accountNumber, Accounts account) throws InsuficientAmmountException{
	if(ammountToTransfer>0&&this.getAccountBalance()>=ammountToTransfer) {
	this.setAccountBalance(this.getAccountBalance()-ammountToTransfer);
	account.setAccountBalance(account.getAccountBalance()+ammountToTransfer);
	}
	else if(this.getAccountBalance()<ammountToTransfer) {
		throw new InsuficientAmmountException();
	}
}

public void receiveMoney(double ammountToReceive, Accounts account) throws InsuficientAmmountException{
	if(ammountToReceive>0&&account.getAccountBalance()>=ammountToReceive) {
	this.setAccountBalance(this.getAccountBalance()+ammountToReceive);
	account.setAccountBalance(account.getAccountBalance()-ammountToReceive);
	}
	else if(account.getAccountBalance()<ammountToReceive) {
		throw new InsuficientAmmountException();
	}
}
}
