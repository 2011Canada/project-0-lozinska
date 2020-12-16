package com.revature.services;

import java.sql.SQLException;
import java.util.List;

import com.revature.exceptions.AccountDoesNotExistException;
import com.revature.exceptions.InternalErrorException;
import com.revature.exceptions.NoUserFindException;
import com.revature.launcher.BankUserLauncher;
import com.revature.models.Accounts;
import com.revature.models.BankUser;
import com.revature.repositories.BAccountDAO;
import com.revature.repositories.BUserDAO;


public class BUserServiceImplementation implements BUserService{
private BUserDAO bankUser;
private BAccountDAO bankAccount;

public BUserServiceImplementation(BUserDAO bankUser,BAccountDAO bankAccount) {
	this.bankUser = bankUser;
	this.bankAccount=bankAccount;
}

	public List<BankUser> seeAllUsers() {
		return bankUser.findAll();
	}

	public int getCustomerById(int id) {
		return bankUser.findCustomerByBankId(id);
	}
	public BankUser login(String username, String password) throws NoUserFindException, SQLException, InternalErrorException {
		BankUserLauncher.e720Logger.info("Customer "+username+" login to system");
		return bankUser.findUser(username, password);
	}


	public List<Accounts> findAllUserAccounts(int customerID) {
		return bankAccount.findAllAccounts(customerID);
	}


	public Accounts findOneUserAccount(int accountNumber,int customerID) throws AccountDoesNotExistException {
	Accounts ac;
		ac=bankAccount.findOneAccount(accountNumber,customerID);
		return ac;
	}

	public void depositMoney(Accounts ba,double ammount) {
		if(ammount>0) {
		bankAccount.updateAccount(ba, ba.getAccountBalance()+ammount);
		ba.setAccountBalance(ba.getAccountBalance()+ammount);
		BankUserLauncher.e720Logger.info("Deposit transaction on "+ba.getAccountNumber());
		}else {
			System.out.println("Amount should be greater then 0");
			BankUserLauncher.e720Logger.info("Attenpt to deposit negative ammount");
		}
	}

	public Accounts createBankAccount(Accounts ac,int userId) {
		return bankAccount.createAccount(ac, userId);
	}

	public BankUser createUserAccount(BankUser bu) {
		return bankUser.createOne(bu);
	}

	public void withdrawMoney(Accounts ba, double ammount) {
		if(ammount<ba.getAccountBalance()) {
		bankAccount.updateAccount(ba, ba.getAccountBalance()-ammount);
		ba.setAccountBalance(ba.getAccountBalance()-ammount);		
		BankUserLauncher.e720Logger.info("Withdraw transaction on"+ba.getAccountNumber());
		}
		else {
			System.out.println("Insuficient funds");
			BankUserLauncher.e720Logger.info("Insuficient funds to withdraw");
		}
	}
public void transferMoney(Accounts a1,Accounts a2, double ammount) {
	if(a1.getAccountBalance()>=ammount) {
		bankAccount.updateAccount(a2, a2.getAccountBalance()+ammount);
		bankAccount.updateAccount(a1, a1.getAccountBalance()-ammount);
		a1.setAccountBalance(a1.getAccountBalance()-ammount);
		a2.setAccountBalance(a2.getAccountBalance()+ammount);
		BankUserLauncher.e720Logger.info("Transfer transaction on occur on "+a1.getAccountNumber()+" and "+a2.getAccountNumber());
	}
	else {
		System.out.println("Insuficient funds");
		BankUserLauncher.e720Logger.info("Insuficient funds to transfer");
	}
}
}
