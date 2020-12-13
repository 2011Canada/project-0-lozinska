package com.revature.services;

import java.util.List;

import com.revature.exceptions.AccountDoesNotExistException;
import com.revature.exceptions.NoUserFindException;
import com.revature.models.*;

public interface BUserService {
public List<BankUser> seeAllUsers();
public BankUser login(String username, String password) throws NoUserFindException;
public List<Accounts> findAllUserAccounts(int customerID);
public Accounts findOneUserAccount(int accountNumber,int customerID) throws AccountDoesNotExistException;
}
