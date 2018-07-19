package com.pcorbett.littleBlackBook.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
		monthService.deleteAllMonths();
	}

	/**
	 * Fires after each test
	 */
	@After
	public void afterTest() {
		// clear the database
		monthService.deleteAllMonths();
	}

	/**
	 * Fires when all tests complete
	 */
	@AfterClass
	public static void done() {
		System.out.println("All Tests Completed");
	}

//		// define a household
//		Household household = getTestHousehold(true, true);
//
//		// save the household
//		Household createdHousehold = householdService.saveHousehold(household);

	@Test(expected = DataIntegrityViolationException.class)
	public void breakCreateMonth() {
		// define a month without a household
		Month month = getTestMonth(null, false);

		// save a month
		Month createdMonth = monthService.saveMonth(month);
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
}
