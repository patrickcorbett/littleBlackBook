package com.pcorbett.littleBlackBook;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.pcorbett.littleBlackBook.domain.db.Household;
import com.pcorbett.littleBlackBook.domain.db.Income;
import com.pcorbett.littleBlackBook.domain.db.Month;
import com.pcorbett.littleBlackBook.domain.db.RecurringExpense;
import com.pcorbett.littleBlackBook.domain.db.SingleExpense;

/**
 * JUnit test to populate and test the tables and their relationships and
 * constraints
 * 
 * @author Patrick Corbett
 * @created 29 Aug 2016
 * 
 *
 */
public abstract class BaseTest {

	public Household getTestHousehold(boolean pIncomes, boolean pExpenses) {
		// create a household
		Household household = new Household();
		household.setName("TEST HOUSEHOLD");
		if (pIncomes) {
			household.setIncomes(getTestIncomes());
		}
		if (pExpenses) {
			household.setExpenses(getTestExpenses());
		}

		return household;
	}

	public Income getTestIncome() {
		// create an income
		Income houseDadIncome = new Income();
		houseDadIncome.setName("DAD INCOME");
		houseDadIncome.setCurrency("EUR");
		houseDadIncome.setGross(new BigDecimal(3500.00));
		houseDadIncome.setNet(new BigDecimal(3000.00));
		houseDadIncome.setValidFrom(new Date());

		return houseDadIncome;
	}

	public Set<Income> getTestIncomes() {
		// create incomes
		Set<Income> incomes = new LinkedHashSet<Income>();

		// create and add an income
		incomes.add(getTestIncome());

		return incomes;
	}

	public Set<RecurringExpense> getTestExpenses() {
		// create expenses
		Set<RecurringExpense> expenses = new LinkedHashSet<RecurringExpense>();

		// RENT
		RecurringExpense rentExpense = new RecurringExpense();
		rentExpense.setAmount(new BigDecimal(1200.00));
		rentExpense.setDebitOnDOM(1);
		rentExpense.setDescription("Rent RECURRING");
		// exp1.setFormula(formula);
		rentExpense.setPriority(0);
		rentExpense.setValidFrom(new Date());

		expenses.add(rentExpense);

		// PETROL
		RecurringExpense petrolExpense = new RecurringExpense();
		petrolExpense.setAmount(new BigDecimal(100.00));
		petrolExpense.setDescription("Petrol RECURRING");
		petrolExpense.setValidFrom(new Date());

		expenses.add(petrolExpense);

		return expenses;
	}

	public Month getTestMonth(Household pHousehold, boolean pExpenses) {
		// create month
		Month month = new Month();

		if (null != pHousehold) {
			month.setHousehold(pHousehold);
		}

		month.setYear("2018");
		month.setMonth("08");
		month.setBonus(new BigDecimal(0.00));
		month.setProjectedNet(new BigDecimal(3000.00));

		if (pExpenses) {
			month.setExpenses(getSingleExpenses());
		}

		return month;
	}

	public Set<SingleExpense> getSingleExpenses() {
		// create expenses
		Set<SingleExpense> expenses = new LinkedHashSet<SingleExpense>();

		// RENT
		SingleExpense rentExpense = new SingleExpense();
		rentExpense.setAmount(new BigDecimal(1200.00));
		rentExpense.setDescription("Rent SINGLE");
		// exp1.setFormula(formula);

		expenses.add(rentExpense);

		// PETROL
		SingleExpense petrolExpense = new SingleExpense();
		petrolExpense.setAmount(new BigDecimal(100.00));
		petrolExpense.setDescription("Petrol SINGLE");

		expenses.add(petrolExpense);

		return expenses;
	}
}
