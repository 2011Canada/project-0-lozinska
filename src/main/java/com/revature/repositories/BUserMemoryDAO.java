package com.revature.repositories;

import java.util.ArrayList;
import java.util.List;

import com.revature.exceptions.NoUserFindException;
import com.revature.models.BankCustomer;
import com.revature.models.BankEmployee;
import com.revature.models.BankUser;


public class BUserMemoryDAO implements BUserDAO{
	static List<BankUser> usersCatalogue = new ArrayList<>();

	// static code block is a block of code that will execute when the program
	// before the main method
	static {
		BankUser employee1 = new BankEmployee("Shawn","123456");
		BankUser employee2 = new BankEmployee("Anna","1111111");
		BankUser customer1 = new BankCustomer("Olaf","12333");
		BankUser customer2 = new BankCustomer("Elsa","00000");
		usersCatalogue.add(employee1);
		usersCatalogue.add(employee2);
		usersCatalogue.add(customer1);
		usersCatalogue.add(customer2);
	}
	public BankUser createOne() {
		// TODO Auto-generated method stub
		return null;
	}

	public BankUser findUser(String username, String password) throws NoUserFindException{
		for(BankUser user: usersCatalogue) {
			if(user.getUsername()==username&&user.getPassword()==password) {
				return user;
			}
		}
		throw new NoUserFindException();
	}
	
	public List<BankUser> findAll(){
		return usersCatalogue;
	}
}
