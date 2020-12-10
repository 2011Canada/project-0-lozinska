package com.revature.repositories;

import java.util.List;

import com.revature.exceptions.NoUserFindException;
import com.revature.models.BankUser;

public interface BUserDAO {
	
public BankUser createOne();
public BankUser findUser(String username, String password) throws NoUserFindException;
public List<BankUser> findAll();
}
