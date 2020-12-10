package com.revature.services;

import java.util.List;

import com.revature.exceptions.NoUserFindException;
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
	@Override
	public List<BankUser> seeAllUsers() {
		return bankUser.findAll();
	}

	@Override
	public BankUser findUserByCredential(String username, String password) throws NoUserFindException {
		
		return bankUser.findUser(username, password);
	}

	@Override
	public Accounts findAllUserAccounts(BankUser customer) {
		
		return null;
	}

	@Override
	public Accounts findOneUserAccount(BankUser customer, int accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}
