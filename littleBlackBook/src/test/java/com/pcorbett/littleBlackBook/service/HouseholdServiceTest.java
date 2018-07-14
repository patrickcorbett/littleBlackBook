package com.pcorbett.littleBlackBook.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pcorbett.littleBlackBook.BaseTest;
import com.pcorbett.littleBlackBook.dto.Household;
import com.pcorbett.littleBlackBook.dto.Income;
import com.pcorbett.littleBlackBook.dto.RecurringExpense;

/**
 * JUnit test to test the Household service
 *
 * @author Patrick Corbett
 *
 * @created 29.08.2016
 */
@ContextConfiguration(locations = { "classpath:spring/applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class HouseholdServiceTest extends BaseTest {

	@Autowired
	private HouseholdService householdService;

	/**
	 * Fires before each test
	 */
	@Before
	public void beforeTest() {
		// clear the database in case it wasn't cleared due to an error
		householdService.deleteAllHouseholds();
	}

	/**
	 * Fires after each test
	 */
	@After
	public void afterTest() {
		// clear the database
		householdService.deleteAllHouseholds();
	}

	/**
	 * Fires when all tests complete
	 */
	@AfterClass
	public static void done() {
		System.out.println("All Tests Completed");
	}

	/**
	 * Test to create a household with no incomes and no expenses
	 */
	@Test
	public void testCreateHousehold() {
		// define a household
		Household household = getTestHousehold(false, false);

		// save the household
		Household createdHousehold = householdService.saveHousehold(household);

		// ensure an ID Was provided
		assertNotNull("The createdHousehold ID should not be null", createdHousehold.getId());

		assertEquals("The household 'Name' should be the match the input", household.getName(),
				createdHousehold.getName());

		// ensure the collections are empty
		assertEquals("The createdHousehold Incomes collection should be empty", 0, createdHousehold.getExpenses().size());
		assertEquals("The createdHousehold Expenses collection should be empty", 0, createdHousehold.getExpenses().size());
	}

	/**
	 * Test to create a household with an income in a single operation
	 */
	@Test
	public void testCreateHouseholdWithIncome() {
		// define a household
		Household household = getTestHousehold(true, false);

		// save the household
		Household createdHousehold = householdService.saveHousehold(household);

		// ensure an ID Was provided
		assertNotNull("The createdHousehold ID should not be null", createdHousehold.getId());

		assertEquals("The household 'Name' should be the match the input", household.getName(),
				createdHousehold.getName());

		// ensure the expenses collection is empty
		assertEquals("The createdHousehold Income collection should be empty", 0, createdHousehold.getExpenses().size());

		// ensure that the income list is defined
		assertNotNull("The income list should not be null", createdHousehold.getIncomes());
		assertEquals("The household should have one income", getTestIncomes().size(),
				createdHousehold.getIncomes().size());
	}

	/**
	 * Test to create a household and then add an income at a later time
	 */
	@Test
	public void testCreateHouseholdWithIncomeAfterHousehold() {
		// define a household
		Household household = getTestHousehold(false, false);

		// save the household
		Household createdHousehold = householdService.saveHousehold(household);

		// add incomes to the household
		createdHousehold.setIncomes(getTestIncomes());

		// save the incomes
		Household updatedHousehold = householdService.saveHousehold(household);

		// ensure an ID Was provided
		assertNotNull("The updatedHousehold ID should not be null", updatedHousehold.getId());

		assertNotNull("The income list should not be null", updatedHousehold.getIncomes());
		assertEquals("The household should have one income", getTestIncomes().size(),
				updatedHousehold.getIncomes().size());
	}

	/**
	 * Test to create a household with incomes and expenses in a single operation
	 */
	@Test
	public void testCreateHouseholdWithIncomeAndRecurringExpenses() {
		// define a household
		Household household = getTestHousehold(true, true);

		// save the household
		Household createdHousehold = householdService.saveHousehold(household);

		// ensure an ID Was provided
		assertNotNull("The createdHousehold ID should not be null", createdHousehold.getId());

		assertNotNull("The income list should not be null", createdHousehold.getIncomes());
		assertEquals("The household should have one income", getTestIncomes().size(),
				createdHousehold.getIncomes().size());

		assertNotNull("The expenses list should not be null", createdHousehold.getExpenses());
		assertEquals("The household should have two expenses", getTestExpenses().size(),
				createdHousehold.getExpenses().size());
	}

	@Test
	public void testCreateHouseholdWithIncomeAndRecurringExpensesAfterHousehold() {
		// define a household
		Household household = getTestHousehold(false, false);

		// save the household
		Household createdHousehold = householdService.saveHousehold(household);

		// add incomes to the household
		createdHousehold.setIncomes(getTestIncomes());

		// update the household
		Household updatedHouseholdWithIncome = householdService.saveHousehold(createdHousehold);

		// ensure the ID Was not changed
		assertEquals("The updatedHousehold ID should not be changed", createdHousehold.getId(),
				updatedHouseholdWithIncome.getId());

		assertNotNull("The income list should not be null", updatedHouseholdWithIncome.getIncomes());
		assertEquals("The household should have one income", getTestIncomes().size(),
				updatedHouseholdWithIncome.getIncomes().size());

		// update the household again
		updatedHouseholdWithIncome.setExpenses(getTestExpenses());
		Household updatedHouseholdWithIncomeAndExpenses = householdService.saveHousehold(updatedHouseholdWithIncome);

		assertNotNull("The expenses list should not be null", updatedHouseholdWithIncomeAndExpenses.getExpenses());
		assertEquals("The household should have two expenses", getTestExpenses().size(),
				updatedHouseholdWithIncomeAndExpenses.getExpenses().size());

	}

	@Test
	public void testLoadHouseholdById() {
		// define a household
		Household household = getTestHousehold(true, true);

		// save the household
		Household createdHousehold = householdService.saveHousehold(household);

		// read all the household items
		List<Household> households = householdService.getAllHouseholds();

		assertEquals("The household list should contain one item", 1, households.size());

		// read by id
		Household loadedHousehold = householdService.getHouseholdById(createdHousehold.getId());

		assertNotNull("The income list should not be null", loadedHousehold.getIncomes());
		assertEquals("The household should have one income", getTestIncomes().size(),
				loadedHousehold.getIncomes().size());

		assertNotNull("The expenses list should not be null", loadedHousehold.getExpenses());
		assertEquals("The household should have two expenses", getTestExpenses().size(),
				loadedHousehold.getExpenses().size());
	}

	@Test
	public void testDeleteSingleExpense() {
		// define a household
		Household household = getTestHousehold(true, true);

		// save the household
		Household createdHousehold = householdService.saveHousehold(household);

		// read by id
		Household loadedHousehold = householdService.getHouseholdById(createdHousehold.getId());

		assertEquals("The household should have two expenses", getTestExpenses().size(),
				loadedHousehold.getExpenses().size());

		RecurringExpense firstExpense = loadedHousehold.getExpenses().iterator().next();

		// delete the expense
		loadedHousehold.getExpenses().remove(firstExpense);

		// update the household
		Household updatedHousehold = householdService.saveHousehold(loadedHousehold);

		assertEquals("The household should have one expense", 1, updatedHousehold.getExpenses().size());

		// read by id
		Household reloadedHousehold = householdService.getHouseholdById(updatedHousehold.getId());

		assertEquals("The household should have one expense", 1, reloadedHousehold.getExpenses().size());
	}

	@Test
	public void testDeleteSingleIncome() {
		// define a household
		Household household = getTestHousehold(true, true);

		// save the household
		Household createdHousehold = householdService.saveHousehold(household);

		// read by id
		Household loadedHousehold = householdService.getHouseholdById(createdHousehold.getId());

		assertEquals("The household should have only 1 income", getTestIncomes().size(),
				loadedHousehold.getIncomes().size());

		Income onlyIncome = loadedHousehold.getIncomes().iterator().next();

		// delete the income
		loadedHousehold.getIncomes().remove(onlyIncome);

		// update the household
		Household updatedHousehold = householdService.saveHousehold(loadedHousehold);

		assertEquals("The household should have no incomes", 0, updatedHousehold.getIncomes().size());

		// read by id
		Household reloadedHousehold = householdService.getHouseholdById(updatedHousehold.getId());

		assertEquals("The household should have no incomes", 0, reloadedHousehold.getIncomes().size());
	}

}
