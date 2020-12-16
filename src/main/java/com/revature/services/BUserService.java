package com.revature.services;

import java.sql.SQLException;
import java.util.List;

import com.revature.exceptions.AccountDoesNotExistException;
import com.revature.exceptions.InternalErrorException;
import com.revature.exceptions.NoUserFindException;
import com.revature.models.*;

public interface BUserService {
public List<BankUser> seeAllUsers();
public BankUser login(String username, String password) throws NoUserFindException, SQLException, InternalErrorException;
public List<Accounts> findAllUserAccounts(int customerID);
public Accounts findOneUserAccount(int accountNumber,int customerID) throws AccountDoesNotExistException;
public void depositMoney(Accounts ba,double ammount);
public void withdrawMoney(Accounts ba, double ammount);
public void transferMoney(Accounts ba1,Accounts ba2,double ammount);
public Accounts createBankAccount(Accounts ac,int userId);
public BankUser createUserAccount(BankUser bu);
public int getCustomerById(int id);
}
