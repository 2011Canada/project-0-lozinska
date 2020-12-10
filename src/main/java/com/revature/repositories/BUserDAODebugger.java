package com.revature.repositories;

import java.util.Collections;
import java.util.List;

import com.revature.models.BankUser;

public class BUserDAODebugger {
public static void main(String[] args) {
	BUserDAO bankUsers = new BUserMemoryDAO();
	
	List<BankUser> userCatalgoue = bankUsers.findAll();
	
	for(BankUser uc : userCatalgoue) {
		System.out.println("Employee name " + uc.getUsername()+"\t"+uc.getPassword());
	}

}
}
