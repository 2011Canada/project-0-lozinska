package com.revature.repositories;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.revature.exceptions.NoUserFindException;
import com.revature.models.BankCustomer;
import com.revature.models.BankEmployee;
import com.revature.models.BankUser;

public class BUserDAODebugger {
public static void main(String[] args) throws NoUserFindException {
	BUserDAO bankUsers = new BUserPostgresDAO();
	
/*	
	List<BankUser> userCatalgoue = bankUsers.findAll();
	
	for(BankUser uc : userCatalgoue) {
		System.out.println("Employee name " + uc.getUsername());
	}
	*/
	BankUser bu=new BankCustomer("test","test");
	BankUser be=new BankEmployee("employee","test");
	bankUsers.createOne(bu);
	bankUsers.createOne(be);
	/*Scanner userIn=new Scanner(System.in);
	System.out.println("Enter username ");
	String username=userIn.nextLine();
	System.out.println(username);
	System.out.println("Enter password ");
	String password=userIn.nextLine();
	System.out.println(password);
	BankUser bankUser=bankUsers.findUser(username, password);
	System.out.println(bankUser.display());*/
}
}
