package com.revature.services;

import java.util.List;
import java.util.Scanner;

import com.revature.exceptions.AccountDoesNotExistException;
import com.revature.exceptions.NoUserFindException;
import com.revature.models.BankUser;
import com.revature.repositories.*;

public class BUserDebugger {
public static void main(String[]args) throws NoUserFindException, AccountDoesNotExistException {
	BUserService bus = new BUserServiceImplementation(new BUserPostgresDAO(),new BAccountPostgresDAO());
	Scanner userIn = new Scanner(System.in);
	List<BankUser> userCatalogue = bus.seeAllUsers();
	
	for(BankUser bu : userCatalogue) {
		System.out.println(bu);
	}
	System.out.println("enter username ");
	
	String username=userIn.nextLine();
	
System.out.println("enter password ");
	
	String password=userIn.nextLine();
	
	System.out.println(bus.login(username, password).display());
	
	
System.out.println("user id ");
	
	String user=userIn.nextLine();
	int customerID=Integer.parseInt(user);
	
	//System.out.println("account number ");
	//String account=userIn.nextLine();
	//int ac=Integer.parseInt(account);
	System.out.println(bus.findAllUserAccounts(customerID));
	
System.out.println("user id ");
	
	user=userIn.nextLine();
	customerID=Integer.parseInt(user);
	
	System.out.println("account number ");
	String account=userIn.nextLine();
	int ac=Integer.parseInt(account);
	System.out.println(bus.findOneUserAccount(ac,customerID).display());
}
}
