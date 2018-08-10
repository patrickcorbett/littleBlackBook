package com.pcorbett.littleBlackBook.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pcorbett.littleBlackBook.BaseTest;
import com.pcorbett.littleBlackBook.domain.db.Household;
import com.pcorbett.littleBlackBook.domain.db.User;

@ContextConfiguration(locations = { "classpath:spring/test-applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest extends BaseTest {

	@Autowired
	private UserService userService;

	@Autowired
	private HouseholdService householdService;

	@Test
	public void testCreateUser() {
		User createdUser = userService.saveUser(getUser());

		assertNotNull("User Id should not be null", createdUser.getId());
		assertNull("Household should be null", createdUser.getHousehold());
	}

	@Test
	public void testCreateUserWithHousehold() {
		User createdUser = userService.saveUser(getUser());

		Household household = getTestHousehold(false, false);
		household.addUser(createdUser);

		householdService.saveHousehold(household);

		assertNotNull("User Id should not be null", createdUser.getId());
		assertNotNull("Household Id should not be null", createdUser.getHousehold().getId());
	}

	@Test
	public void testCreateUserWithHouseholdThenLeaveHousehold() {
		User createdUser = userService.saveUser(getUser());

		Household household = getTestHousehold(false, false);
		household.addUser(createdUser);

		householdService.saveHousehold(household);

		assertNotNull("User Id should not be null", createdUser.getId());
		assertNotNull("Household Id should not be null", createdUser.getHousehold().getId());

		// remember the household id
		Long householdId = createdUser.getHousehold().getId();
		// leave the household
		createdUser.leaveHousehold();

		userService.saveUser(createdUser);

		// reload the household
		Household loadedHousehold = householdService.getHouseholdById(householdId, true, true, true, true);

		assertEquals("Household should have no users", 0, loadedHousehold.getUsers().size());
	}
}
