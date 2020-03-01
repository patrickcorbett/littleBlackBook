package com.pcorbett.littleBlackBook.service;

import java.util.List;

import com.pcorbett.littleBlackBook.domain.db.Household;
import com.pcorbett.littleBlackBook.domain.db.Income;
import com.pcorbett.littleBlackBook.domain.db.Month;
import com.pcorbett.littleBlackBook.domain.db.RecurringExpense;
import com.pcorbett.littleBlackBook.domain.db.SingleExpense;
import com.pcorbett.littleBlackBook.domain.db.User;
import com.pcorbett.littleBlackBook.exceptions.MissingHouseholdOwnerException;

/**
 * @author Patrick Corbett
 *
 * @created 14.07.2018
 */
public interface HouseholdService {

	// Households
	Household getHousehold(Long pUserId);

	Household createHousehold(Long pUserId, Household pHousehold);

	Household updateHousehold(Household pHousehold);

	Household addHouseholdUser(Long pHouseholdId, Long pUserId);

	Household removeHouseholdUser(Long pHouseholdId, Long pUserId);

	Household leaveHousehold(Long pOwnerId, Long pNewOwnerId);

	void deleteHousehold(Long pUserId);

	// Incomes
	List<Income> getHouseholdIncomes(Long pHouseholdId);

	Income getHouseholdIncome(Long pIncomeId);

	Household createHouseholdIncome(Long pHouseholdId, Income pIncome);

	Household updateHouseholdIncome(Long pHouseholdId, Income pIncome);

	Household deleteHouseholdIncome(Long pHouseholdId, Income pIncomeId);

	// Expenses
	List<RecurringExpense> getHouseholdExpenses(Household pHousehold);

	RecurringExpense getHouseholdExpense(Long pExpenseId);

	Household createHouseholdExpense(Household pHousehold, RecurringExpense pExpense);

	Household updateHouseholdExpense(Household pHousehold, RecurringExpense pExpense);

	Household deleteHouseholdExpense(Household pHousehold, RecurringExpense pExpense);

}
