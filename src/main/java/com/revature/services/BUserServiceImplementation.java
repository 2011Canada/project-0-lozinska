package com.revature.services;

import java.sql.SQLException;
import java.util.List;

import com.revature.exceptions.AccountDoesNotExistException;
import com.revature.exceptions.InternalErrorException;
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
	public BankUser login(String username, String password) throws NoUserFindException {
		BankUser bu;
		try {
			bu = bankUser.findUser(username, password);
			return bu;
		} catch (NoUserFindException|InternalErrorException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new NoUserFindException();
		
	}

	@Override
	public List<Accounts> findAllUserAccounts(int customerID) {
		return bankAccount.findAllAccounts(customerID);
	}

	@Override
	public Accounts findOneUserAccount(int accountNumber,int customerID) throws AccountDoesNotExistException {
	Accounts ac;
		ac=bankAccount.findOneAccount(accountNumber,customerID);
		return ac;
	}
	


}
