package com.pcorbett.littleBlackBook.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pcorbett.littleBlackBook.BaseTest;
import com.pcorbett.littleBlackBook.domain.db.User;

@ContextConfiguration(locations = { "classpath:spring/test-applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest extends BaseTest {

	@Autowired
	private UserService userService;

	@Test
	public void testCreateUser() {
		User createdUser = userService.createUser(getUser());

		assertNotNull("User Id should not be null", createdUser.getId());
		assertNull("Household should be null", createdUser.getHousehold());
	}

}
