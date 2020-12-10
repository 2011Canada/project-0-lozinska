package com.revature.repositories;

import java.util.List;

import com.revature.exceptions.AccountDoesNotExistException;
import com.revature.models.Accounts;

public class BAccountDAODebugger {
public static void main(String[] args) {
	int accountNumber=1003;
	BAccountDAO bankAccount=new BAccountMemoryDAO();
	
	List<Accounts> accountsCatalogue=bankAccount.findAllAccounts();
	try {
		Accounts newAccount=bankAccount.findOneAccount(accountNumber);
		System.out.println("Account type "+ newAccount.getAccountType()+" balance "+newAccount.getAccountBalance());
	} catch (AccountDoesNotExistException e) {
		
		e.printStackTrace();
	}
	for (Accounts account: accountsCatalogue) {
		System.out.println(account+" "+account.getAccountNumber());
	}
	
}

}
