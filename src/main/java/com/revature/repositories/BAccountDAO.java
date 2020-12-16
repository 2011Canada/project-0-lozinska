package com.revature.repositories;

import java.util.List;

import com.revature.exceptions.AccountDoesNotExistException;
import com.revature.models.Accounts;

public interface BAccountDAO {
public Accounts createAccount(Accounts a,int userId);
public Accounts findOneAccount(int accountNumber,int customerID) throws AccountDoesNotExistException;
public List<Accounts> findAllAccounts(int customerID);
public Accounts updateAccount(Accounts a, double ammount);
public int getAccountById(int accountId);
}
