package com.revature.repositories;

import java.util.List;

import com.revature.exceptions.AccountDoesNotExistException;
import com.revature.models.Accounts;

public class BAccountDAODebugger {
public static void main(String[] args) throws AccountDoesNotExistException {
	int accountNumber=21;
	int customerID=1;
	BAccountDAO bankAccount=new BAccountPostgresDAO();
	
//List<Accounts> userCatalgoue = bankAccount.findAllAccounts(1);
	
	//for(Accounts uc : userCatalgoue) {
	//	System.out.println("Account type " + uc.getAccountType());
	//}
	Accounts account=bankAccount.findOneAccount(accountNumber,customerID);
	//System.out.println("Account before update "+account.getAccountBalance()+" accountId "+account.getAccountId()+" account number "+account.getAccountNumber());
	//account=bankAccount.updateAccount(account, 53.87);
	System.out.println("Account after update "+account.getAccountBalance()+" accountId "+account.getAccountId()+" account number "+account.getAccountNumber());
	/*	Accounts account=new Accounts(13.00,"checking");
	bankAccount.createAccount(account, 1);
	bankAccount.updateAccount(account,63.99);*/
}

}
