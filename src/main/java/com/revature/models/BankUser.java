package com.revature.models;

import java.util.List;

import com.revature.exceptions.NoUserFindException;

public class BankUser implements Displayable{
private String username;
private String password;
private String role;
//private List<Accounts> accounts;

public BankUser() {}
public BankUser(String username,String password) {
	if(username!=null&&password!=null) {
		this.username=username;
		this.password=password;
	}
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
/*public List<Accounts> getAccounts() {
	return accounts;
}
public void setAccounts(List<Accounts> accounts) {
	this.accounts = accounts;
}
public void viewAccountBalance(int accountNumber, String username) throws NoUserFindException{
	
		if(username==this.username) {
			for(Accounts account: accounts) {
				if(accountNumber==account.getAccountNumber())
			System.out.println("Account balance is "+account.getAccountBalance());
		}
	}
		throw new NoUserFindException();
}*/
@Override
public String display() {
	return "I am a bank user"+this.getUsername();
}

}
