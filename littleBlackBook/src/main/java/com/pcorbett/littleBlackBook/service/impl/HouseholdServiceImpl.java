package com.pcorbett.littleBlackBook.service.impl;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.pcorbett.littleBlackBook.dao.HouseholdDao;
import com.pcorbett.littleBlackBook.dao.UserDao;
import com.pcorbett.littleBlackBook.domain.db.Household;
import com.pcorbett.littleBlackBook.domain.db.Income;
import com.pcorbett.littleBlackBook.domain.db.RecurringExpense;
import com.pcorbett.littleBlackBook.domain.db.User;
import com.pcorbett.littleBlackBook.exceptions.HouseholdNotFoundException;
import com.pcorbett.littleBlackBook.exceptions.UserNotFoundException;
import com.pcorbett.littleBlackBook.service.HouseholdService;

/**
 * @author Patrick Corbett
 *
 * @created 14.07.2018
 */
@Service
public class HouseholdServiceImpl extends AbstractServiceImpl implements HouseholdService {

	// TODO validate that the user is allowed to perform actions for the household

	@Override
	public Household getHousehold(Long pUserId) {
		// Load user
		User user = getUserById(pUserId);

		Household household = getHouseholdById(user.getHousehold().getId());

		//throw new HouseholdNotFoundException(String.format("Household for user '%s' not found!!", pUserId));
		
		// include the months
		household.getMonths().size();

		return household;
	}

	@Override
	public Household createHousehold(Long pUserId, Household pHousehold) {
		// Load user
		User user = getUserById(pUserId);

		// validate that the user doesn't have a household
		if (user.getHousehold() != null) {
			// Household is already assigned
			return null;
		}

		// add user to household
		pHousehold.addUser(user);

		// set the owner for this household
		pHousehold.setOwner(user);

		// save the household
		Household createdHousehold = householdDao.save(pHousehold);

		return createdHousehold;
	}

	@Override
	public Household updateHouseholdName(Long pHouseholdId, String pHouseholdName) {
		Household household = getHouseholdById(pHouseholdId);
		household.setName(pHouseholdName);
		return householdDao.save(household);
	}

	@Override
	public void deleteHousehold(Long pUserId) {
		// TODO Auto-generated method stub
	}

	@Override
	public Household addHouseholdUser(Long pHouseholdId, Long pUserId) {
		Household household = getHouseholdById(pHouseholdId);
		User user = getUserById(pUserId);
		
		// validate that the user doesn't have a household
		if (user.getHousehold() != null) {
			// Household is already assigned
			return null;
		}
		
		household.addUser(user);
		return householdDao.save(household);
	}

	@Override
	public Household removeHouseholdUser(Long pHouseholdId, Long pUserId) {
		Household household = getHouseholdById(pHouseholdId);
		User user = getUserById(pUserId);
		
		// validate that the user has a household
		if (user.getHousehold() == null) {
			// Household is not assigned
			throw new IllegalArgumentException(String.format("User %s has no assigned Household!!", pUserId));
		}
		
		// Ensure the user is in the household
		if (!household.getUsers().contains(user)) {
			//user not in household
			throw new IllegalArgumentException(String.format("User %s is not in Household %s!!", pUserId, pHouseholdId));
		}
		
		household.removeUser(user);
		return householdDao.save(household);
	}

	@Override
	public Household leaveHousehold(Long pOwnerId, Long pNewOwnerId) {
		// check that both users have been found
		if (pOwnerId == null) {
			throw new UserNotFoundException("The current household owner cannot be null");
		}
		if (pNewOwnerId == null) {
			throw new UserNotFoundException("The new household owner cannot be null");
		}

		// lookup the users by id
		Optional<User> opCurrentOwner = userDao.findById(pOwnerId);
		Optional<User> opNewOwner = userDao.findById(pNewOwnerId);

		// check that both users have been found
		if (!opCurrentOwner.isPresent()) {
			throw new UserNotFoundException(
					String.format("The current household owner with id '%s' could not be found", pOwnerId));
		}
		if (!opNewOwner.isPresent()) {
			throw new UserNotFoundException(
					String.format("The new household owner with id '%s' could not be found", pNewOwnerId));
		}

		// get the users
		User currentOwner = opCurrentOwner.get();
		User newOwner = opNewOwner.get();

		// ensure the userids are not the same
		if (currentOwner.getId().equals(newOwner.getId())) {
			throw new IllegalArgumentException(String.format(
					"The owner id '%s' cannot be used as both current and new household owner", currentOwner.getId()));
		}

		// ensure the user is the owner of a household
		if (currentOwner.getHousehold() == null) {
			throw new IllegalArgumentException(String
					.format("The current owner id '%s' has no assigned household to leave", currentOwner.getId()));
		}

		// load the owners household
		Household household = householdDao.findById(currentOwner.getHousehold().getId()).get();

		// ensure the new owner is defined as a user
		if (!household.getUsers().contains(newOwner)) {
			throw new IllegalArgumentException(String.format("The new owner id '%s' is not a user in household '%s'",
					currentOwner.getId(), household.getId()));
		}

		// remove current owner
		household.removeUser(currentOwner);

		// update household owner
		household.setOwner(newOwner);

		// update the household
		return householdDao.save(household);
	}

	@Override
	public List<RecurringExpense> getHouseholdExpenses(Household pHousehold) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecurringExpense getHouseholdExpense(Long pExpenseId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Household createHouseholdExpense(Household pHousehold, RecurringExpense pExpense) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Household updateHouseholdExpense(Household pHousehold, RecurringExpense pExpense) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Household deleteHouseholdExpense(Household pHousehold, RecurringExpense pExpense) {
		// TODO Auto-generated method stub
		return null;
	}

}
