package com.revature.launcher;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.exceptions.AccountDoesNotExistException;
import com.revature.exceptions.InternalErrorException;
import com.revature.exceptions.NoUserFindException;
import com.revature.menus.BankUserMenu;
import com.revature.models.Displayable;
import com.revature.repositories.BAccountDAO;
import com.revature.repositories.BAccountPostgresDAO;
import com.revature.repositories.BUserDAO;
import com.revature.repositories.BUserPostgresDAO;
import com.revature.services.BUserService;
import com.revature.services.BUserServiceImplementation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class BankUserLauncher {

	public static Logger e720Logger=LogManager.getLogger("com.revature.e720");
	public static void main(String[] args) throws SQLException, InternalErrorException, NoUserFindException, AccountDoesNotExistException {
		BUserDAO emd = new BUserPostgresDAO();
		BAccountDAO acc=new BAccountPostgresDAO();
		BUserService ems = new BUserServiceImplementation(emd, acc);
		BankUserMenu emm = new BankUserMenu(ems);
		
		while(true) {
			emm.mainMenu();
			emm.manageUserInput();
		
		}
		
		
	}
	
}
