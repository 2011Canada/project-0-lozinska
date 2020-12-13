package com.revature.repositories;

import java.util.List;

import com.revature.exceptions.AccountDoesNotExistException;
import com.revature.models.Accounts;

public class BAccountDAODebugger {
public static void main(String[] args) throws AccountDoesNotExistException {
	int accountNumber=21;
	int customerID=1;
	BAccountDAO bankAccount=new BAccountPostgresDAO();
	
List<Accounts> userCatalgoue = bankAccount.findAllAccounts(2);
	
	for(Accounts uc : userCatalgoue) {
		System.out.println("Employee name " + uc.getAccountType());
	}
	System.out.println("Account test "+bankAccount.findOneAccount(accountNumber,customerID).display());
}

}
