package com.revature.repositories;

import java.sql.SQLException;
import java.util.List;

import com.revature.exceptions.InternalErrorException;
import com.revature.exceptions.NoUserFindException;
import com.revature.models.BankUser;

public interface BUserDAO {
	
public BankUser createOne(BankUser bu);
public BankUser findUser(String username, String password) throws NoUserFindException, SQLException, InternalErrorException;
public List<BankUser> findAll();
public int findCustomerByBankId(int id);
}
