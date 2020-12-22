package com.pcorbett.littleBlackBook.exceptions;

public class UserNotFoundException extends AbstractApplicationException {

	private static final long serialVersionUID = 3971110153271780127L;

	public UserNotFoundException(String pMessage) {
		super(pMessage);
	}

}
