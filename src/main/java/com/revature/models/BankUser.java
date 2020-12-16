package com.revature.models;

import java.util.List;

import com.revature.exceptions.NoUserFindException;

public class BankUser implements Displayable{
	private int userId;

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

public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}

public String display() {
	return "I am a bank user"+this.getUsername();
}

}
