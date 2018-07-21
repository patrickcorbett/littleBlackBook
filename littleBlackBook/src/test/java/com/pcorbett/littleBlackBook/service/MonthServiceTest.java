package com.pcorbett.littleBlackBook.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.Iterator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pcorbett.littleBlackBook.BaseTest;
import com.pcorbett.littleBlackBook.dto.Household;
import com.pcorbett.littleBlackBook.dto.Month;
import com.pcorbett.littleBlackBook.dto.SingleExpense;

@ContextConfiguration(locations = { "classpath:spring/test-applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class MonthServiceTest extends BaseTest {

	@Autowired
	private HouseholdService householdService;

	@Autowired
	private MonthService monthService;

	/**
	 * Fires before each test
	 */
	@Before
	public void beforeTest() {
		// clear the database in case it wasn't cleared due to an error
		// The order of the delete is important, FK constraint between Household and
		// Month
		monthService.deleteAllMonths();
		householdService.deleteAllHouseholds();
	}

	/**
	 * Fires after each test
	 */
	@After
	public void afterTest() {
		// clear the database, the order of the delete is important, FK constraint
		// between Household and Month
		monthService.deleteAllMonths();
		householdService.deleteAllHouseholds();
	}

	/**
	 * Fires when all tests complete
	 */
	@AfterClass
	public static void done() {
		System.out.println("All Tests Completed");
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void testCreateMonthWithoutHousehold() {
		// define a month without a household
		Month month = getTestMonth(null, false);

		// save a month, results in error as Income cannot be null
		monthService.saveMonth(month);
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void testMonthHouseholdConstaint() {
		// define a household
		Household household = getTestHousehold(true, false);

		// save the household
		Household createdHousehold = householdService.saveHousehold(household);

		// define a month with the income
		Month month = getTestMonth(createdHousehold, false);

		// save a month
		monthService.saveMonth(month);

		// Delete the household, foreign key constraint
		householdService.deleteAllHouseholds();
	}

	@Test
	public void testCreateMonthWithIncome() {
		// define a household
		Household household = getTestHousehold(true, false);

		// save the household
		Household createdHousehold = householdService.saveHousehold(household);

		// define a month with the income
		Month month = getTestMonth(createdHousehold, false);

		// save a month
		Month createdMonth = monthService.saveMonth(month);

		// ensure an ID Was provided
		assertNotNull("The createdMonth ID should not be null", createdMonth.getId());
		assertNotNull("The createdMonth 'Household' should not be null", createdMonth.getHousehold());
		assertEquals("The createdMonth 'Income ID' should match", month.getHousehold().getId(),
				createdMonth.getHousehold().getId());
		assertEquals("The createdMonth 'Year' should match", month.getYear(), createdMonth.getYear());
		assertEquals("The createdMonth 'Month' should match", month.getMonth(), createdMonth.getMonth());
		assertEquals("The createdMonth 'Bonus' should match", month.getBonus(), createdMonth.getBonus());
		assertEquals("The createdMonth 'Projected Net' should match", month.getProjectedNet(),
				createdMonth.getProjectedNet());

		// ensure the createdMonth collection are empty
		assertEquals("The createdMonth 'Expenses' collection should be empty", 0, createdMonth.getExpenses().size());
	}

	@Test
	public void testCreateMonthWithIncomeAndExpensesAfter() {
		// define a household
		Household household = getTestHousehold(true, false);

		// save the household
		Household createdHousehold = householdService.saveHousehold(household);

		// define a month with the income
		Month month = getTestMonth(createdHousehold, false);

		// save a month
		Month createdMonth = monthService.saveMonth(month);

		// ensure the createdMonth collection are empty
		assertEquals("The createdMonth 'Expenses' collection should be empty", 0, createdMonth.getExpenses().size());

		// add the expenses
		createdMonth.setExpenses(getSingleExpenses());

		// update the month
		Month updatedMonth = monthService.saveMonth(createdMonth);

		// ensure the createdMonth collection are empty
		assertEquals("The createdMonth 'Expenses' collection should have two expenses", 2,
				updatedMonth.getExpenses().size());
	}

	@Test
	public void testCreateMonthWithIncomeAndExpenses() {
		// define a household
		Household household = getTestHousehold(true, false);

		// save the household
		Household createdHousehold = householdService.saveHousehold(household);

		// define a month with the income
		Month month = getTestMonth(createdHousehold, true);

		// save a month
		Month createdMonth = monthService.saveMonth(month);

		// ensure the createdMonth collection are empty
		assertEquals("The createdMonth 'Expenses' collection should have two expenses", 2,
				createdMonth.getExpenses().size());
	}

	@Test
	public void testCreateMonthWithIncomeAndExpensesThenDeleteExpense() {
		// define a household
		Household household = getTestHousehold(true, false);

		// save the household
		Household createdHousehold = householdService.saveHousehold(household);

		// define a month with the income
		Month month = getTestMonth(createdHousehold, true);

		// save a month
		Month createdMonth = monthService.saveMonth(month);

		// ensure the createdMonth collection are empty
		assertEquals("The createdMonth 'Expenses' collection should have two expenses", 2,
				createdMonth.getExpenses().size());

		// delete
		SingleExpense firstExpense = createdMonth.getExpenses().iterator().next();

		// delete the expense
		createdMonth.getExpenses().remove(firstExpense);

		// update the month
		Month updatedMonth = monthService.saveMonth(createdMonth);

		// ensure the createdMonth collection are empty
		assertEquals("The updatedMonth 'Expenses' collection should only have one item", 1,
				updatedMonth.getExpenses().size());
	}

	@Test
	public void testCreateMonthWithIncomeAndExpensesThenCompleteExpense() {
		// define a household
		Household household = getTestHousehold(true, false);

		// save the household
		Household createdHousehold = householdService.saveHousehold(household);

		// define a month with the income
		Month month = getTestMonth(createdHousehold, true);

		// save a month
		Month createdMonth = monthService.saveMonth(month);

		// ensure the createdMonth collection are empty
		assertEquals("The createdMonth 'Expenses' collection should have two expenses", 2,
				createdMonth.getExpenses().size());

		// update the expense
		SingleExpense firstExpense = createdMonth.getExpenses().iterator().next();
		firstExpense.setComplete(true);

		// update the month
		Month updatedMonth = monthService.saveMonth(createdMonth);

		// ensure the updatedMonth collection still has two expenses
		assertEquals("The updatedMonth 'Expenses' collection should have two expenses", 2,
				updatedMonth.getExpenses().size());

		// find the updated expense
		SingleExpense firstExpenseUpdated = null;
		Iterator<SingleExpense> iterator = updatedMonth.getExpenses().iterator();
		while (iterator.hasNext()) {
			// get the next expense
			SingleExpense currentExpense = iterator.next();

			// match by ID
			if (firstExpense.getId().equals(currentExpense.getId())) {
				firstExpenseUpdated = currentExpense;
				break;
			}
		}

		assertNotNull("The Updated Expense was not found", firstExpenseUpdated);
		assertEquals("The updatedMonth should have one completed expense", true, firstExpenseUpdated.getComplete());
	}

	@Test
	public void testCreateMonthWithIncomeAndExpensesThenAddExpense() {
		// define a household
		Household household = getTestHousehold(true, false);

		// save the household
		Household createdHousehold = householdService.saveHousehold(household);

		// define a month with the income
		Month month = getTestMonth(createdHousehold, true);

		// save a month
		Month createdMonth = monthService.saveMonth(month);

		// ensure the createdMonth collection are empty
		assertEquals("The createdMonth 'Expenses' collection should have two expenses", 2,
				createdMonth.getExpenses().size());

		// Add an expense
		SingleExpense newExpense = new SingleExpense();
		newExpense.setAmount(new BigDecimal(150.00));
		newExpense.setDescription("MediaMarkt - USB Drive");

		createdMonth.getExpenses().add(newExpense);

		// update the month
		Month updatedMonth = monthService.saveMonth(createdMonth);

		// ensure the updatedMonth collection should have three expenses
		assertEquals("The updatedMonth 'Expenses' collection should have two expenses", 3,
				updatedMonth.getExpenses().size());
	}
}
