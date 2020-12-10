package com.revature.repositories;

import java.util.ArrayList;
import java.util.List;

import com.revature.exceptions.AccountDoesNotExistException;
import com.revature.models.Accounts;
import com.revature.models.Checking;
import com.revature.models.Savings;


public class BAccountMemoryDAO implements BAccountDAO{
static List<Accounts>accountsCatalogue=new ArrayList<>();
static {
	Accounts checking1 = new Checking(456.98);
	Accounts checking2 = new Checking(8765.99);
	Accounts saving1 = new Savings(786.76);
	Accounts saving2 = new Savings(566.67);
	accountsCatalogue.add(checking1);
	accountsCatalogue.add(checking2);
	accountsCatalogue.add(saving1);
	accountsCatalogue.add(saving2);
}
@Override
public Accounts creteAccount() {
	// TODO Auto-generated method stub
	return null;
}
@Override
public Accounts findOneAccount(int accountNumber) throws AccountDoesNotExistException{
	for(Accounts account: accountsCatalogue) {
		if(accountNumber==account.getAccountNumber()) {
			return account;
		}
	}
	throw new AccountDoesNotExistException();
}
@Override
public List<Accounts> findAllAccounts() {

	return accountsCatalogue;
}
}
