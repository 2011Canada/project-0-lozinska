package com.revature.services;

import java.util.List;

import com.revature.models.BankUser;
import com.revature.repositories.*;

public class BUserDebugger {
public static void main(String[]args) {
	BUserService bus = new BUserServiceImplementation(new BUserMemoryDAO(),new BAccountMemoryDAO());
	
	List<BankUser> userCatalogue = bus.seeAllUsers();
	
	for(BankUser bu : userCatalogue) {
		System.out.println(bu);
	}
}
}
