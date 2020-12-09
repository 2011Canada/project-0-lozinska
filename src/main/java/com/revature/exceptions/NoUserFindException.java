package com.revature.exceptions;

public class NoUserFindException extends Exception {
public NoUserFindException() {
	super("No user find with this username. Please try again");
}
}
