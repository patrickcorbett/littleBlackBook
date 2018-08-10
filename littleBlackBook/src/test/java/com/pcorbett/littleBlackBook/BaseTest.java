package com.pcorbett.littleBlackBook;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.pcorbett.littleBlackBook.domain.db.Household;
import com.pcorbett.littleBlackBook.domain.db.Income;
import com.pcorbett.littleBlackBook.domain.db.Month;
import com.pcorbett.littleBlackBook.domain.db.RecurringExpense;
import com.pcorbett.littleBlackBook.domain.db.SingleExpense;
import com.pcorbett.littleBlackBook.domain.db.User;

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

	private static int count = 1;

	public User getUser() {
		int uniqueCount = BaseTest.count++;
		User user = new User();
		user.setUsername("testuser" + uniqueCount);
		user.setFirstName("Test");
		user.setSurname("User");
		user.setEmailAddress("test" + uniqueCount + "@Test.com");
		user.setPassword("1234");
		return user;
	}

	public Household getTestHousehold(boolean pIncomes, boolean pExpenses) {
		// create a household
		Household household = new Household();
		household.setName("TEST HOUSEHOLD");
		if (pIncomes) {
			for (Income income : getTestIncomes()) {
				household.addIncome(income);
			}
		}
		if (pExpenses) {
			for (RecurringExpense expense : getTestExpenses()) {
				household.addExpense(expense);
			}
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

	public List<Income> getTestIncomes() {
		// create incomes
		List<Income> incomes = new LinkedList<Income>();

		// create and add an income
		incomes.add(getTestIncome());

		return incomes;
	}

	public List<RecurringExpense> getTestExpenses() {
		// create expenses
		List<RecurringExpense> expenses = new LinkedList<RecurringExpense>();

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
			for (SingleExpense expense : getSingleExpenses()) {
				month.addExpense(expense);
			}
		}

		return month;
	}

	public List<SingleExpense> getSingleExpenses() {
		// create expenses
		List<SingleExpense> expenses = new LinkedList<SingleExpense>();

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
