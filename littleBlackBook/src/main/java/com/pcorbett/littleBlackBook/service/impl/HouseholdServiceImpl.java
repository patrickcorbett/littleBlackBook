package com.pcorbett.littleBlackBook.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.pcorbett.littleBlackBook.dao.HouseholdDao;
import com.pcorbett.littleBlackBook.dao.UserDao;
import com.pcorbett.littleBlackBook.domain.db.Household;
import com.pcorbett.littleBlackBook.domain.db.User;
import com.pcorbett.littleBlackBook.service.HouseholdService;

/**
 * @author Patrick Corbett
 *
 * @created 14.07.2018
 */
@Service
public class HouseholdServiceImpl implements HouseholdService {

	@Inject
	private UserDao userDao;

	@Inject
	private HouseholdDao householdDao;

	@Override
	public Household saveHousehold(Household pHousehold) {
		Household createdHousehold = householdDao.save(pHousehold);

		Iterator<User> users = createdHousehold.getUsers().iterator();
		while (users.hasNext()) {

			userDao.save(users.next());
		}
		return createdHousehold;
	}

	@Override
	public List<Household> getAllHouseholds() {
		return householdDao.findAll();
	}

	@Override
	public Household getHouseholdById(Long pId) {
		return getHouseholdById(pId, false, false, false, false);
	}

	@Override
	public Household getHouseholdById(Long pId, boolean pLoadIncomes, boolean pLoadExpenses, boolean pLoadMonths,
			boolean pLoadUsers) {
		Optional<Household> opHousehold = householdDao.findById(pId);

		Household household = null;

		if (opHousehold.isPresent()) {
			household = opHousehold.get();

			if (pLoadIncomes) {
				household.getIncomes().size();
			}
			if (pLoadExpenses) {
				household.getExpenses().size();
			}
			if (pLoadMonths) {
				household.getMonths().size();
			}
			if (pLoadUsers) {
				household.getUsers().size();
			}
		}

		return household;
	}

	@Override
	public void deleteAllHouseholds() {
		householdDao.deleteAll();
	}

}
