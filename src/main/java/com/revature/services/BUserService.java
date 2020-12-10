package com.revature.services;

import java.util.List;

import com.revature.exceptions.NoUserFindException;
import com.revature.models.*;

public interface BUserService {
public List<BankUser> seeAllUsers();
public BankUser findUserByCredential(String username, String password) throws NoUserFindException;
public Accounts findAllUserAccounts(BankUser customer);
public Accounts findOneUserAccount(BankUser customer, int accountNumber);
}
