package com.revature.models;

import com.revature.exceptions.InsuficientAmmountException;

public class Checking extends Accounts{

	public Checking(int accountNumber, double accountBalance) {
		super(accountNumber, accountBalance);
		this.setAccountType("checking");
	}
	public void withdrawMoney(double ammountToWithdraw) throws InsuficientAmmountException {
		if(this.getAccountBalance()>=ammountToWithdraw&& ammountToWithdraw>0) {
			this.setAccountBalance(this.getAccountBalance()-ammountToWithdraw);
		}
		else if(ammountToWithdraw<this.getAccountBalance()) {
			throw new InsuficientAmmountException();
		}
	}
	
	public void depositMoney(double ammountToDeposit){
		if(this.getAccountBalance()>=ammountToDeposit&& ammountToDeposit>0) {
			this.setAccountBalance(this.getAccountBalance()+ammountToDeposit);
		}
	}

}
