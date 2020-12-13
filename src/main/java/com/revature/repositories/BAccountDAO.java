package com.revature.repositories;

import java.util.List;

import com.revature.exceptions.AccountDoesNotExistException;
import com.revature.models.Accounts;

public interface BAccountDAO {
public Accounts creteAccount();
public Accounts findOneAccount(int accountNumber,int customerID) throws AccountDoesNotExistException;
public List<Accounts> findAllAccounts(int customerID);
}
