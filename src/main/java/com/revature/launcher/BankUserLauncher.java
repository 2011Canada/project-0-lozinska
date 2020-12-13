package com.revature.launcher;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.exceptions.NoUserFindException;
import com.revature.menus.BankUserMenu;
import com.revature.models.Displayable;
import com.revature.repositories.BUserDAO;
import com.revature.repositories.BUserMemoryDAO;
import com.revature.services.BUserService;
import com.revature.services.BUserServiceImplementation;

public class BankUserLauncher {
	
	public static void main(String[] args) {
		BUserDAO emd = new BUserMemoryDAO();
		BUserService ems = new BUserServiceImplementation(emd, null);
		BankUserMenu emm = new BankUserMenu(ems);
		
		while(true) {
			//the server is running
		
			//System.out.println(emm.display());
		System.out.println("1 Login"+"\n"+"2 Register");
		Scanner userIn=new Scanner(System.in);
		String number=userIn.nextLine();
		System.out.println(number);
		if(number.equals("1")) {
			System.out.println("Who you are ");
			System.out.println("1 Customer"+"\n"+"2 Employee");
			number=userIn.nextLine();
			System.out.println(number);
			
			if(number.equals("1")) {
				System.out.println(emm.display());
			}
			
		}
		else if(number.equals("2")) {
			System.out.println("Im login");
		}
		else {
			System.out.println("Please make a valid selection");
		}
		
		}
		
		
	}
	
}
