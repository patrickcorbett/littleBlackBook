package com.pcorbett.littleBlackBook.service;

import java.util.List;

import com.pcorbett.littleBlackBook.domain.db.Household;
import com.pcorbett.littleBlackBook.domain.db.Month;
import com.pcorbett.littleBlackBook.domain.db.SingleExpense;

public interface HouseholdMonthService {

	// MONTHS
	List<Month> getHouseholdMonths(Long pHouseholdId);

	Month getHouseholdMonth(Long pHouseholdId, Long pMonthId);

	Household createHouseholdMonth(Long pHouseholdId, Month pMonth);

	Household updateHouseholdMonth(Long pHouseholdId, Month pMonth);

	Household deleteHouseholdMonth(Long pHouseholdId, Long pMonthId);

	// MONTH EXPENSES
	List<SingleExpense> getHouseholdMonthSingleExpenses(Long pHouseholdId, Long pMonthId);

	Household createHouseholdSingleExpense(Long pHouseholdId, Long pMonthId, SingleExpense pSingleExpense);

	Household updateHouseholdSingleExpense(Long pHouseholdId, Long pMonthId, SingleExpense pSingleExpense);

	Household deleteHouseholdSingleExpense(Long pHouseholdId, Long pMonthId, Long pSingleExpenseId);

}
