package com.pcorbett.littleBlackBook.exceptions;

import com.pcorbett.littleBlackBook.domain.db.Household;

public class MissingHouseholdOwnerException extends IllegalArgumentException {

	private static final long serialVersionUID = 2021711632817778732L;

	private Household household;

	public MissingHouseholdOwnerException(Household pHousehold) {
		super();
		household = pHousehold;
	}

	@Override
	public String getMessage() {
		return String.format("An new owner for Household '%s' must be defined before the owner can leave.",
				household.getId());
	}

}
