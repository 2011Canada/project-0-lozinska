package com.revature.menus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.exceptions.NoUserFindException;
import com.revature.models.BankUser;
import com.revature.models.Displayable;
import com.revature.services.BUserService;

public class BankUserMenu implements Displayable{
	
	BUserService bus;
	
	List<Displayable> lines;
	
	Scanner userIn;
	List<BankUser> bankUsers;
	
	public BankUserMenu(BUserService bus ) {
		this.bus = bus;
		lines = new ArrayList<Displayable>(this.bus.seeAllUsers());
		bankUsers=new ArrayList<>(this.bus.seeAllUsers());
		userIn = new Scanner(System.in);
	}

	
	public String display() {
		String display="";
		for(int i =0; i<lines.size(); i++) {
			
			display += (i+1) + ": " + lines.get(i).display() + "\n"; 
		}
		return display;
	}
/*	public BankUser displayOne(String username, String password) throws NoUserFindException {
		for(BankUser bankUser:bankUsers) {
			if(bankUser.getUsername()==username&&bankUser.getPassword()==password) {
			 bankUser.findUserByCredential(username, password);
			return bankUser;
			}
		}
				throw new NoUserFindException();
			}
	public String displayOne(String username, String password) {
for(int i=0;i<bankUser.size();i++) {
	if(bankUser.get(i).getUsername()==username&&bankUser.get(i).getPassword()==password) {
		this.bankUser.findUserByCredential(username, password);
	
	}
}
		return null;
	}*/

}
