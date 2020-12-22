package com.pcorbett.littleBlackBook.exceptions;

public class HouseholdNotFoundException extends AbstractApplicationException {

	private static final long serialVersionUID = 7190320138207945929L;

	public HouseholdNotFoundException(String pMessage) {
		super(pMessage);
	}

}
