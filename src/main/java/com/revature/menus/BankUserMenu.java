package com.revature.menus;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.exceptions.AccountDoesNotExistException;
import com.revature.exceptions.InternalErrorException;
import com.revature.exceptions.NoUserFindException;
import com.revature.models.Accounts;
import com.revature.models.BankCustomer;
import com.revature.models.BankUser;
import com.revature.services.BUserService;
import com.revature.services.BUserServiceImplementation;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class BankUserMenu {
	static double ammount;
	BUserService bus;
	static String yesno="";
	static boolean isEmployee;
	static boolean backMenu;
	static String status="N";
	Accounts myAccount;
	BankUser myUser;
	
	Scanner userIn;
	List<BankUser> bankUsers;
	List<Accounts> accountList;
	
	public BankUserMenu(BUserService bus ) {
		this.bus = bus;
	//	lines = new ArrayList<Displayable>(this.bus.seeAllUsers());
	//	bankUsers=new ArrayList<>(this.bus.seeAllUsers());
		userIn = new Scanner(System.in);
	}
public void mainMenu() {
	boolean check=false;
	while(!check) {
		System.out.println("Make selection\n"+"1. Customer\n"+"2. Employee\n");
		int selection;
		if(userIn.hasNextInt()) {
			selection=userIn.nextInt();
			if(selection==1) {
				isEmployee=false;
				check=true;
			}
			else if(selection==2) {
				isEmployee=true;
				check=true;
			}
			else {
				System.out.println("Please make valid selection");
			}
		}
	}
}
/*public void display() {
	if(!isEmployee) {
		if(status.equals("N")) {
			System.out.println(menu());
		}
		else if(status.equals("L")) {
			System.out.println(menuAfterLogin());
		}else {
			System.out.println(menu());
		}
	}
}
*/
public String menu() {
	String string="";
	if(!isEmployee) {
		string+="1.Rgister\n"+"2.Login\n"+"3.Return to main menu";
	}else {
		string+="1.Login\n"+"2.Back\n";
	}
	return string;
}

public void subDisplay() {
	int selection=0;
	boolean b=false;
	while(!b) {
	if(!isEmployee) {
		System.out.println("1.Login\n"+"2.Return to main menu");
		 selection=userIn.nextInt();
		 if(selection==1||selection==2) {
			 b=true;
		 }
		 else {
			 System.out.println("Made valid selection");
			
		 }
	}
	}
	//mainMenu();
}

public void manageUserInput() throws SQLException, InternalErrorException, NoUserFindException, AccountDoesNotExistException {
	int input;
	
	if(!isEmployee) {
		
		System.out.println("1.Login\n"+"2.Register\n");
		input=userIn.nextInt();
		if(input==1) {
			String name,password;
			System.out.println("Enter username\n");
			name=userIn.next();
			System.out.println("Enter password\n");
			password=userIn.next();
			myUser=bus.login(name, password);
			
			int id=myUser.getUserId();
			int accountNumber=0;
			System.out.println("Welcome "+(myUser.getUsername())+" chose operation you would like to perform\n");
			boolean check=false;
			while(!check) {
				System.out.println("\n");
			System.out.println("1\t Check account balance");
			System.out.println("2\t Deposit money");
			System.out.println("3\t Withdraw money");
			System.out.println("4\t Transfer money");
			System.out.println("5\t Back to main menu");
			//check if working
			int actualID=bus.getCustomerById(myUser.getUserId());
			//System.out.println("Userid "+myUser.getUserId());
			accountList=bus.findAllUserAccounts(actualID);
			input=userIn.nextInt();
			switch(input) {
			case 1:
				System.out.println("List of accounts with balances");
				int i=1;
					for(Accounts a: accountList) {
					System.out.println(i+". "+a.getAccountNumber()+" has balance of "+a.getAccountBalance());
				i++;
					}
				break;
			case 2:
				for(Accounts a: accountList) {
					System.out.println(a.getAccountNumber()+" has balance of "+a.getAccountBalance());
				}
				System.out.println("Select account you want to perform transaction");
				//accountList=bus.findAllUserAccounts(myUser.getUserId());
				accountNumber=userIn.nextInt();
				System.out.println("Enter amount you want to deposit");
				double deposit=userIn.nextDouble();
				myAccount=bus.findOneUserAccount(accountNumber,actualID);
				bus.depositMoney(myAccount, deposit);
				System.out.println("Account balance of account "+myAccount.getAccountNumber()+" is "+myAccount.getAccountBalance());
				break;
			case 3:
				for(Accounts a: accountList) {
					System.out.println(a.getAccountNumber()+" has balance of "+a.getAccountBalance());
				}
				System.out.println("Select the account you want to perform transaction");
				accountNumber=userIn.nextInt();
				System.out.println("Enter amount you want to withdraw");
				ammount=userIn.nextDouble();
				myAccount=bus.findOneUserAccount(accountNumber,actualID);
				bus.withdrawMoney(myAccount, ammount);
				DecimalFormat df = new DecimalFormat("#.##");
				String formated=df.format(myAccount.getAccountBalance());
				System.out.println("Account balance of account "+myAccount.getAccountNumber()+" is "+formated);
				ammount=0;
				break;
			case 4:
				Accounts accountsTo, accountsFrom;
				for(Accounts a: accountList) {
					System.out.println(a.getAccountNumber()+" has balance of "+a.getAccountBalance());
				}
					System.out.println("Select the account you want to perform transfer");
					int accountFrom=userIn.nextInt();
					accountsFrom=bus.findOneUserAccount(accountFrom, myUser.getUserId());
					System.out.println("Select the account where you want to transfer money");
					int accountTo=userIn.nextInt();
					accountsTo=bus.findOneUserAccount(accountTo, myUser.getUserId());
					System.out.println("Enter amount you want to transfer");
					ammount=userIn.nextDouble();
					bus.transferMoney(accountsFrom, accountsTo, ammount);
					System.out.println("Account FROM balance "+accountsFrom.getAccountBalance()+" account number is "+accountsFrom.getAccountNumber()+"\n");
					System.out.println("Account TO balance "+accountsTo.getAccountBalance()+" account number is "+accountsTo.getAccountNumber()+"\n");
				ammount=0; 
				break;
			case 5: mainMenu();
			check=true;
			}
			}
				
		} else if (input==2) {
			System.out.println("Enter username\n");
			String username=userIn.next();
			System.out.println("Enter password\n");
			String password=userIn.next();
			BankUser newUser=new BankCustomer(username,password);
			bus.createUserAccount(newUser);
			System.out.println("User "+newUser.getUsername()+" is created!");
			subDisplay();
		}
		
		
	}
	else {
		System.out.println("Enter username\n");
		String username=userIn.next();
		System.out.println("Enter password\n");
		String password=userIn.next();
		myUser=bus.login(username, password);
		int custId=0;
		bankUsers=bus.seeAllUsers();
		System.out.println("Welcome "+myUser.getUsername());
		boolean checkE=false;
		while(!checkE) {
		System.out.println("1.\t Check account balance");
		System.out.println("2.\t Check logs");
		System.out.println("3.\t Create account for customer");
		System.out.println("4.\t Back to main menu");
		int inputE=userIn.nextInt();
		switch(inputE) {
		case 1:
			for(BankUser bu:bankUsers) {
				System.out.println("Customer "+bu.getUsername()+" customer ID is "+bu.getUserId());
			}
			System.out.println("Enter customer ID to display accounts with their balances");
			custId=userIn.nextInt();
			accountList=bus.findAllUserAccounts(custId);
			for(Accounts a:accountList) {
			System.out.println("Account name "+a.getAccountType()+" account balance "+a.getAccountBalance());
			}
			break;
		case 2:
			System.out.println("Reading loggs");
			try {
				BufferedReader reader=new BufferedReader(new FileReader("C:\\Users\\Krystyna\\Documents\\Revature\\Java\\project0\\project-0-lozinska\\logs\\trace.log"));
			String fileContent="";
			String currentReadingLine=reader.readLine();
			while(currentReadingLine!=null) {
				fileContent+=currentReadingLine+System.lineSeparator();
				System.out.println(currentReadingLine);
				currentReadingLine=reader.readLine();
				
			}
			reader.close();
			}catch(FileNotFoundException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}
			break;
		case 3:
			for(BankUser bu:bankUsers) {
				System.out.println("Customer "+bu.getUsername()+" customer ID is "+bu.getUserId());
			}
			System.out.println("Enter customer ID to create new account");
			custId=userIn.nextInt();
			int actualID=bus.getCustomerById(custId);
			System.out.println("Enter amount for initial deposit or 0");
			ammount=userIn.nextDouble();
			System.out.println("Which account to create\n");
			System.out.println("1.Checking\n"+"2.Saving\n");
			int accountType=userIn.nextInt();
			System.out.println("");
			if(accountType==1) {
				Accounts ac=new Accounts(ammount,"checking");
				bus.createBankAccount(ac, actualID);
			}
			else if(accountType==2) {
				Accounts ac=new Accounts(ammount,"saving");
				bus.createBankAccount(ac, custId);
			}
			else {
				System.out.println("Make valid selection please");
			}
			break;
		case 4:
			mainMenu();
			checkE=true;
		}
		}
	}
}
}
