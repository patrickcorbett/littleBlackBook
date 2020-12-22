package com.pcorbett.littleBlackBook.service;

import java.util.List;

import com.pcorbett.littleBlackBook.domain.db.Household;
import com.pcorbett.littleBlackBook.domain.db.RecurringExpense;

/**
 * @author Patrick Corbett
 *
 * @created 14.07.2018
 */
public interface HouseholdService {

	// Households
	Household getHousehold(Long pUserId);

	Household createHousehold(Long pUserId, Household pHousehold);

	Household updateHouseholdName(Long pHouseholdId, String pHouseholdName);

	void deleteHousehold(Long pUserId);

	// Users
	Household addHouseholdUser(Long pHouseholdId, Long pUserId);
	
	Household removeHouseholdUser(Long pHouseholdId, Long pUserId);

	Household leaveHousehold(Long pOwnerId, Long pNewOwnerId);

	// Expenses
	List<RecurringExpense> getHouseholdExpenses(Household pHousehold);

	RecurringExpense getHouseholdExpense(Long pExpenseId);

	Household createHouseholdExpense(Household pHousehold, RecurringExpense pExpense);

	Household updateHouseholdExpense(Household pHousehold, RecurringExpense pExpense);

	Household deleteHouseholdExpense(Household pHousehold, RecurringExpense pExpense);

}
