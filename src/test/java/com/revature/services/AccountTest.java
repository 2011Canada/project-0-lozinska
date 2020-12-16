package com.revature.services;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import com.revature.exceptions.AccountDoesNotExistException;
import com.revature.repositories.BAccountPostgresDAO;
import com.revature.repositories.BUserPostgresDAO;
public class AccountTest {
	BAccountPostgresDAO dao=new BAccountPostgresDAO();
@Test
public void Test() throws AccountDoesNotExistException {
int accountNumber=11;
	assertEquals(accountNumber,dao.getAccountById(1));
}
}
