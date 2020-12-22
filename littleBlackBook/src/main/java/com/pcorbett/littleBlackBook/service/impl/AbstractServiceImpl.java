package com.pcorbett.littleBlackBook.service.impl;

import java.util.Optional;

import javax.inject.Inject;

import com.pcorbett.littleBlackBook.dao.HouseholdDao;
import com.pcorbett.littleBlackBook.dao.UserDao;
import com.pcorbett.littleBlackBook.domain.db.Household;
import com.pcorbett.littleBlackBook.domain.db.User;
import com.pcorbett.littleBlackBook.exceptions.HouseholdNotFoundException;
import com.pcorbett.littleBlackBook.exceptions.UserNotFoundException;

public class AbstractServiceImpl {

	@Inject
	protected UserDao userDao;

	@Inject
	protected HouseholdDao householdDao;

	protected User getUserById(Long pUserId) {
		Optional<User> user = userDao.findById(pUserId);
		if (user.isPresent()) {
			return user.get();
		}

		throw new UserNotFoundException(String.format("User '%s' not found!!", pUserId));
	}

	protected Household getHouseholdById(Long pHouseholdId) {
		Optional<Household> household = householdDao.findById(pHouseholdId);
		if (household.isPresent()) {
			return household.get();
		}

		throw new HouseholdNotFoundException(String.format("Household '%s' not found!!", pHouseholdId));
	}

}
