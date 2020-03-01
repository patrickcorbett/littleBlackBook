package com.pcorbett.littleBlackBook.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.LazyInitializationException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pcorbett.littleBlackBook.BaseTest;
import com.pcorbett.littleBlackBook.dao.HouseholdDao;
import com.pcorbett.littleBlackBook.domain.db.Household;
import com.pcorbett.littleBlackBook.domain.db.Income;
import com.pcorbett.littleBlackBook.domain.db.RecurringExpense;
import com.pcorbett.littleBlackBook.domain.db.User;

/**
 * JUnit test to test the Household service
 *
 * @author Patrick Corbett
 *
 * @created 29.08.2016
 */
@ContextConfiguration(locations = { "classpath:spring/test-applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class HouseholdServiceTest extends BaseTest {

	@Autowired
	private UserService userService;

	@Autowired
	private HouseholdService householdService;

	@Autowired
	private HouseholdDao householdDao;

	/**
	 * Fires before each test
	 */
	@Before
	public void beforeTest() {
		// clear the database in case it wasn't cleared due to an error
//		householdDao.deleteAll();
	}

	/**
	 * Fires after each test
	 */
	@After
	public void afterTest() {
		// clear the database
//		householdDao.deleteAll();
	}

	/**
	 * Fires when all tests complete
	 */
	@AfterClass
	public static void done() {
		System.out.println("All Tests Completed");
	}

	@Test
	public void testGetHousehold() {
		// create user
		User createdUser = userService.createUser(getUser());

		// create household
		Household householdToCreate = getTestHousehold(false);
		Household createdHousehold = householdService.createHousehold(createdUser.getId(), householdToCreate);

		// lookup the household of the user
		Household loadedHousehold = householdService.getHousehold(createdUser.getId());

		assertEquals("The createdHousehold and loadedHousehold should have the same id", createdHousehold.getId(), loadedHousehold.getId());

		assertEquals("The createdHousehold and loadedHousehold should be the equal", createdHousehold, loadedHousehold);
	}

	/**
	 * Test to create a household with no incomes and no expenses
	 */
	@Test
	public void testCreateHousehold() {
		// create user
		User createdUser = userService.createUser(getUser());

		// create household
		Household householdToCreate = getTestHousehold(false);
		Household createdHousehold = householdService.createHousehold(createdUser.getId(), householdToCreate);
		// ensure an ID was provided
		assertNotNull("The createdHousehold ID should not be null", createdHousehold.getId());

		assertEquals("The household 'Name' should be the match the input", householdToCreate.getName(),
				createdHousehold.getName());

		// ensure the collections are empty
		assertEquals("The createdHousehold Incomes collection should be empty", 0,
				createdHousehold.getExpenses().size());
		assertEquals("The createdHousehold Expenses collection should be empty", 0,
				createdHousehold.getExpenses().size());
		assertEquals("The createdHousehold Months collection should be empty", 0, createdHousehold.getMonths().size());
		assertEquals("The createdHousehold Users collection should have one entry", 1,
				createdHousehold.getUsers().size());

		// ensure the creator was set
		assertEquals("Household creator should be the userId", createdHousehold.getOwner().getId(),
				createdUser.getId());
	}

	public void updateHousehold() {
		// TODO
	}

	public void addHouseholdUser() {
		// TODO
	}

	public void removeHouseholdUser() {
		// TODO
	}

	@Test(expected = IllegalArgumentException.class)
	public void failLeaveHouseholdNullCurrentOwner() {
		householdService.leaveHousehold(null, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void failLeaveHouseholdNullNewOwner() {
		householdService.leaveHousehold(-1L, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void failLeaveHouseholdInvalidCurrentOwner() {
		// create user
		User createdUser = userService.createUser(getUser());

		// create household
		Household household = getTestHousehold(false);
		householdService.createHousehold(createdUser.getId(), household);

		assertNotNull("Household Id should not be null", household.getId());

		householdService.leaveHousehold(-1L, createdUser.getId());
	}

	@Test(expected = IllegalArgumentException.class)
	public void failLeaveHouseholdInvalidNewOwner() {
		// create user
		User createdUser = userService.createUser(getUser());

		// create household
		Household household = getTestHousehold(false);
		householdService.createHousehold(createdUser.getId(), household);

		assertNotNull("Household Id should not be null", household.getId());

		householdService.leaveHousehold(createdUser.getId(), -1L);
	}

	@Test(expected = IllegalArgumentException.class)
	public void failLeaveHouseholdSameUserIdOldAndNew() {
		// create user
		User createdUser = userService.createUser(getUser());

		// create household
		Household household = getTestHousehold(false);
		householdService.createHousehold(createdUser.getId(), household);

		assertNotNull("Household Id should not be null", household.getId());

		householdService.leaveHousehold(createdUser.getId(), createdUser.getId());
	}

	@Test(expected = IllegalArgumentException.class)
	public void failLeaveHouseholdCurrentUserHasNoHousehold() {
		// create user
		User createdUser1 = userService.createUser(getUser());
		User createdUser2 = userService.createUser(getUser());

		assertNotNull("User Id should not be null", createdUser1.getId());
		assertNotNull("User Id should not be null", createdUser2.getId());

		// create household
		Household household = getTestHousehold(false);
		householdService.createHousehold(createdUser1.getId(), household);

		assertNotNull("Household Id should not be null", household.getId());

		householdService.leaveHousehold(createdUser2.getId(), createdUser1.getId());
	}

	@Test(expected = IllegalArgumentException.class)
	public void failLeaveHouseholdNewUserIsNotHouseholdUser() {
		// create user
		User createdUser1 = userService.createUser(getUser());
		User createdUser2 = userService.createUser(getUser());

		assertNotNull("User Id should not be null", createdUser1.getId());
		assertNotNull("User Id should not be null", createdUser2.getId());

		// create household
		Household household = getTestHousehold(false);
		householdService.createHousehold(createdUser1.getId(), household);

		assertNotNull("Household Id should not be null", household.getId());

		householdService.leaveHousehold(createdUser1.getId(), createdUser2.getId());
	}

	@Test
	public void testLeaveHousehold() {
		// create user
		User createdUser1 = userService.createUser(getUser());
		User createdUser2 = userService.createUser(getUser());

		assertNotNull("User Id should not be null", createdUser1.getId());

		// create household
		Household household = getTestHousehold(false);
		householdService.createHousehold(createdUser1.getId(), household);

		assertNotNull("Household Id should not be null", household.getId());
		assertEquals("Household creator should equal createdUser1", household.getOwner().getId(), createdUser1.getId());

		// add a new user to the household
		householdService.addHouseholdUser(household.getId(), createdUser2.getId());

		// user1 leaves the household to user2
		Household updatedHousehold = householdService.leaveHousehold(createdUser1.getId(), createdUser2.getId());

		assertNotNull("Household Id should not be null", updatedHousehold.getId());
		assertEquals("Household creator should equal createdUser1", updatedHousehold.getOwner().getId(),
				createdUser2.getId());

		// reload the user
		User userWithoutHousehold = userService.login(createdUser1.getUsername(), createdUser1.getPassword());

		assertNull("User Household should be empty", userWithoutHousehold.getHousehold());
	}

	public void deleteHousehold() {
		// TODO
	}

	// Incomes
	public void getHouseholdIncomes() {
		// TODO
	}

	public void getHouseholdIncome() {
		// TODO
	}

	public void updateHouseholdIncome() {
		// TODO
	}

	public void deleteHouseholdIncome() {
		// TODO
	}

	// Expenses
	public void getHouseholdExpenses() {
		// TODO
	}

	public void getHouseholdExpense() {
		// TODO
	}

	public void createHouseholdExpense() {
		// TODO
	}

	public void updateHouseholdExpense() {
		// TODO
	}

	public void deleteHouseholdExpense() {
		// create user
		User createdUser = userService.createUser(getUser());

		// create household
		Household createdHousehold = householdService.createHousehold(createdUser.getId(),
				getTestHousehold(true));

		// read by id
		Household loadedHousehold = householdService.getHousehold(createdHousehold.getId());

		assertEquals("The household should have two expenses", getTestExpenses().size(),
				loadedHousehold.getExpenses().size());

		// TODO 
	}

	// OLD ---------------------------

//	/**
//	 * Test to create a household and then add an income at a later time
//	 */
//	@Test
//	public void testCreateHouseholdWithIncomeAfterHousehold() {
//		// define a household
//		Household household = getTestHousehold(false, false);
//
//		// save the household
//		Household createdHousehold = householdService.saveHousehold(household);
//
//		// add incomes to the household
//		for (Income income : getTestIncomes()) {
//			createdHousehold.addIncome(income);
//		}
//
//		// save the incomes
//		Household updatedHousehold = householdService.saveHousehold(household);
//
//		// ensure an ID Was provided
//		assertNotNull("The updatedHousehold ID should not be null", updatedHousehold.getId());
//
//		assertNotNull("The income list should not be null", updatedHousehold.getIncomes());
//		assertEquals("The household should have one income", getTestIncomes().size(),
//				updatedHousehold.getIncomes().size());
//	}
//
//	/**
//	 * Test to create a household with incomes and expenses in a single operation
//	 */
//	@Test
//	public void testCreateHouseholdWithIncomeAndRecurringExpenses() {
//		// define a household
//		Household household = getTestHousehold(true, true);
//
//		// save the household
//		Household createdHousehold = householdService.saveHousehold(household);
//
//		// ensure an ID Was provided
//		assertNotNull("The createdHousehold ID should not be null", createdHousehold.getId());
//
//		assertNotNull("The income list should not be null", createdHousehold.getIncomes());
//		assertEquals("The household should have one income", getTestIncomes().size(),
//				createdHousehold.getIncomes().size());
//
//		assertNotNull("The expenses list should not be null", createdHousehold.getExpenses());
//		assertEquals("The household should have two expenses", getTestExpenses().size(),
//				createdHousehold.getExpenses().size());
//
//		// get first income
//		RecurringExpense firstExpense = createdHousehold.getExpenses().iterator().next();
//		RecurringExpense firstTestExpense = getTestExpenses().iterator().next();
//
//		assertNotNull("The Expense ID should not be null", firstExpense.getId());
//
//		assertEquals("The Expense 'Description' should match", firstExpense.getDescription(),
//				firstTestExpense.getDescription());
//		assertEquals("The Expense 'Amount' should match", firstExpense.getAmount(), firstTestExpense.getAmount());
//		assertEquals("The Expense 'Priority' should match", firstExpense.getPriority(), firstTestExpense.getPriority());
//		assertEquals("The Expense 'DebitOnDOM' should match", firstExpense.getDebitOnDOM(),
//				firstTestExpense.getDebitOnDOM());
//	}
//
//	@Test
//	public void testCreateHouseholdWithIncomeAndRecurringExpensesAfterHousehold() {
//		// define a household
//		Household household = getTestHousehold(false, false);
//
//		// save the household
//		Household createdHousehold = householdService.saveHousehold(household);
//
//		// add incomes to the household
//		for (Income income : getTestIncomes()) {
//			createdHousehold.addIncome(income);
//		}
//
//		// update the household
//		Household updatedHouseholdWithIncome = householdService.saveHousehold(createdHousehold);
//
//		// ensure the ID Was not changed
//		assertEquals("The updatedHousehold ID should not be changed", createdHousehold.getId(),
//				updatedHouseholdWithIncome.getId());
//
//		assertNotNull("The income list should not be null", updatedHouseholdWithIncome.getIncomes());
//		assertEquals("The household should have one income", getTestIncomes().size(),
//				updatedHouseholdWithIncome.getIncomes().size());
//
//		// update the household again
//		for (RecurringExpense expense : getTestExpenses()) {
//			updatedHouseholdWithIncome.addExpense(expense);
//		}
//
//		Household updatedHouseholdWithIncomeAndExpenses = householdService.saveHousehold(updatedHouseholdWithIncome);
//
//		assertNotNull("The expenses list should not be null", updatedHouseholdWithIncomeAndExpenses.getExpenses());
//		assertEquals("The household should have two expenses", getTestExpenses().size(),
//				updatedHouseholdWithIncomeAndExpenses.getExpenses().size());
//
//	}
//
//	@Test
//	public void testLoadHouseholdById() {
//		// define a household
//		Household household = getTestHousehold(true, true);
//
//		// save the household
//		Household createdHousehold = householdService.saveHousehold(household);
//
//		// read all the household items
//		List<Household> households = householdService.getAllHouseholds();
//
//		assertEquals("The household list should contain one item", 1, households.size());
//
//		// read by id
//		Household loadedHousehold = householdService.getHouseholdById(createdHousehold.getId(), true, true, false,
//				false);
//
//		assertNotNull("The income list should not be null", loadedHousehold.getIncomes());
//		assertEquals("The household should have one income", getTestIncomes().size(),
//				loadedHousehold.getIncomes().size());
//
//		assertNotNull("The expenses list should not be null", loadedHousehold.getExpenses());
//		assertEquals("The household should have two expenses", getTestExpenses().size(),
//				loadedHousehold.getExpenses().size());
//	}
//
//	@Test
//	public void testDeleteSingleExpense() {
//
//	}
//
//	@Test
//	public void testDeleteSingleIncome() {
//		// define a household
//		Household household = getTestHousehold(true, true);
//
//		// save the household
//		Household createdHousehold = householdService.saveHousehold(household);
//
//		// read by id
//		Household loadedHousehold = householdService.getHouseholdById(createdHousehold.getId(), true, false, false,
//				false);
//
//		assertEquals("The household should have only 1 income", getTestIncomes().size(),
//				loadedHousehold.getIncomes().size());
//
//		Income onlyIncome = loadedHousehold.getIncomes().iterator().next();
//
//		// delete the income
//		loadedHousehold.getIncomes().remove(onlyIncome);
//
//		// update the household
//		Household updatedHousehold = householdService.saveHousehold(loadedHousehold);
//
//		assertEquals("The household should have no incomes", 0, updatedHousehold.getIncomes().size());
//
//		// read by id
//		Household reloadedHousehold = householdService.getHouseholdById(updatedHousehold.getId(), true, false, false,
//				false);
//
//		assertEquals("The household should have no incomes", 0, reloadedHousehold.getIncomes().size());
//	}
//
//	@Test(expected = LazyInitializationException.class)
//	public void testLoadHouseholdByIdLazyIncomes() {
//		// define a household
//		Household household = getTestHousehold(true, true);
//
//		// save the household
//		Household createdHousehold = householdService.saveHousehold(household);
//
//		// read by id
//		Household loadedHousehold = householdService.getHouseholdById(createdHousehold.getId());
//
//		loadedHousehold.getIncomes().size();
//	}
//
//	@Test(expected = LazyInitializationException.class)
//	public void testLoadHouseholdByIdLazyExpenses() {
//		// define a household
//		Household household = getTestHousehold(true, true);
//
//		// save the household
//		Household createdHousehold = householdService.saveHousehold(household);
//
//		// read by id
//		Household loadedHousehold = householdService.getHouseholdById(createdHousehold.getId());
//
//		loadedHousehold.getExpenses().size();
//	}
//
//	@Test(expected = LazyInitializationException.class)
//	public void testLoadHouseholdByIdLazyMonths() {
//		// define a household
//		Household household = getTestHousehold(true, true);
//
//		// save the household
//		Household createdHousehold = householdService.saveHousehold(household);
//
//		// read by id
//		Household loadedHousehold = householdService.getHouseholdById(createdHousehold.getId());
//
//		loadedHousehold.getMonths().size();
//	}
//
//	@Test
//	public void testLoadHouseholdByIdEagerIncomes() {
//		// define a household
//		Household household = getTestHousehold(true, true);
//
//		// save the household
//		Household createdHousehold = householdService.saveHousehold(household);
//
//		// read by id
//		Household loadedHousehold = householdService.getHouseholdById(createdHousehold.getId(), true, false, false,
//				false);
//		assertEquals("The household should have only 1 income", getTestIncomes().size(),
//				loadedHousehold.getIncomes().size());
//	}
//
//	@Test
//	public void testLoadHouseholdByIdEagerExpenses() {
//		// define a household
//		Household household = getTestHousehold(true, true);
//
//		// save the household
//		Household createdHousehold = householdService.saveHousehold(household);
//
//		// read by id
//		Household loadedHousehold = householdService.getHouseholdById(createdHousehold.getId(), true, true, false,
//				false);
//
//		assertEquals("The household should have only 1 income", getTestIncomes().size(),
//				loadedHousehold.getIncomes().size());
//
//		assertEquals("The household should have only 2 expenses", getTestExpenses().size(),
//				loadedHousehold.getExpenses().size());
//	}
//
//	@Test
//	public void testLoadHouseholdByIdEagerMonths() {
//		// define a household
//		Household household = getTestHousehold(true, true);
//
//		// save the household
//		Household createdHousehold = householdService.saveHousehold(household);
//
//		// read by id
//		Household loadedHousehold = householdService.getHouseholdById(createdHousehold.getId(), true, true, true,
//				false);
//
//		assertEquals("The household should have only 1 income", getTestIncomes().size(),
//				loadedHousehold.getIncomes().size());
//
//		assertEquals("The household should have only 2 expenses", getTestExpenses().size(),
//				loadedHousehold.getExpenses().size());
//
//		assertEquals("The household should have only no months", 0, loadedHousehold.getMonths().size());
//	}

}
