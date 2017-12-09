package com.pcorbett.littleBlackBook.db;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pcorbett.littleBlackBook.dao.ExpensesDao;
import com.pcorbett.littleBlackBook.dao.HouseholdDao;
import com.pcorbett.littleBlackBook.dao.IncomeDao;
import com.pcorbett.littleBlackBook.dto.Household;
import com.pcorbett.littleBlackBook.dto.Income;
import com.pcorbett.littleBlackBook.dto.RecurringExpense;

/**
 * JUnit test to populate and test the tables and their relationships and
 * constraints
 * 
 * @author Patrick Corbett
 * @created 29 Aug 2016
 * 
 *
 */
@ContextConfiguration(locations = { "classpath:spring/applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class DbTest {

	@Autowired
	private HouseholdDao householdDao;

	@Autowired
	private ExpensesDao expensesDao;

	@Autowired
	private IncomeDao IncomeDao;

	/**
	 * Fires when all tests complete
	 */
	@BeforeClass
	public void dbGui() {
		// org.hsqldb.util.DatabaseManagerSwing.main(new String[] { "--url", "jdbc:hsqldb:file:testdb", "--noexit" });
	}
	
	/**
	 * Fires when all tests complete
	 */
	@AfterClass
	public void done() {
		System.out.println("OK");
	}

	/**
	 * Create a new Income with the passed name
	 * 
	 * @param name
	 * @return
	 */
	private Income createIncome(String name) {
		// create an income
		Income house1DadIncome = new Income();
		house1DadIncome.setName(name);
		house1DadIncome.setCurrency("EUR");
		house1DadIncome.setGross(new BigDecimal(3500.00));
		house1DadIncome.setNet(new BigDecimal(3000.00));
		house1DadIncome.setValidFrom(new Date());

		// save the income
		return IncomeDao.save(house1DadIncome);
	}

	/**
	 * Create a new household with the passed name and income
	 * 
	 * @param name
	 * @param income
	 * @return
	 */
	private Household createHousehold(String name, Income income) {

		// get expenses
		List<RecurringExpense> expenses = createHouseholdExpenses(name);

		expensesDao.save(expenses);

		// assign income to a household
		// create a household
		Household house = new Household();
		house.setName(name);
		house.setIncomes(Arrays.asList(income));
		house.setExpenses(expenses);

		// save the household
		return householdDao.save(house);
	}

	/**
	 * Create the recurring monthly expense for a household
	 * 
	 * @param name
	 * @return
	 */
	private List<RecurringExpense> createHouseholdExpenses(String name) {

		List<RecurringExpense> expenses = new ArrayList<RecurringExpense>();

		// RENT
		RecurringExpense exp1 = new RecurringExpense();
		exp1.setAmount(new BigDecimal(1200.00));
		exp1.setDebitOnDOM(1);
		exp1.setDescription(name + " - Rent");
		// exp1.setFormula(formula);
		exp1.setPriority(0);
		exp1.setValidFrom(new Date());

		expenses.add(exp1);

		// PETROL
		RecurringExpense exp2 = new RecurringExpense();
		exp2.setAmount(new BigDecimal(100.00));
		exp2.setDescription(name + " - Petrol");
		exp2.setValidFrom(new Date());

		expenses.add(exp2);

		return expenses;
	}

	/**
	 * Test to create a income
	 */
	@Test
	public void testCreateIncome() {
		// create a new income
		Income createdIncome = createIncome("TEST DAD 1");

		// ensure an ID Was provided
		assertNotNull("The createdIncome ID should not be null", createdIncome.getId());
	}

	/**
	 * Test to create an income and a household
	 */
	@Test
	public void testCreateIncomeAndHousehold() {
		// create a new income
		Income createdIncome = createIncome("TEST DAD 2");

		// assign income to a household
		// create a household
		Household createdHousehold = createHousehold("TEST HOUSE 2", createdIncome);

		// ensure an ID Was provided
		assertNotNull("The createdIncome ID should not be null", createdIncome.getId());
		assertNotNull("The createdHousehold ID should not be null", createdHousehold.getId());
	}

	/**
	 * A test to ensure an Income can only exist in one household, attempting to
	 * add an existing income to an new household should result in a database
	 * error
	 */
	@Test(expected = DataIntegrityViolationException.class)
	public void breakIncomeHouseholdOneToOne() {
		// create a new income
		Income createdIncome = createIncome("TEST DAD 3");

		// assign income to a household
		// create a household
		createHousehold("TEST HOUSE 3", createdIncome);

		// assign income to another household - should fail!
		createHousehold("TEST HOUSE 4", createdIncome);
	}
}
