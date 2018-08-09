package com.pcorbett.littleBlackBook.service.impl;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.pcorbett.littleBlackBook.dao.HouseholdDao;
import com.pcorbett.littleBlackBook.dao.MonthDao;
import com.pcorbett.littleBlackBook.domain.db.Household;
import com.pcorbett.littleBlackBook.service.HouseholdService;

/**
 * @author Patrick Corbett
 *
 * @created 14.07.2018
 */
@Service
public class HouseholdServiceImpl implements HouseholdService {

	@Inject
	private HouseholdDao householdDao;

	@Inject
	private MonthDao monthDao;

	@Override
	public Household saveHousehold(Household pHousehold) {
		return householdDao.save(pHousehold);
	}

	@Override
	public List<Household> getAllHouseholds() {
		return householdDao.findAll();
	}

	@Override
	public Household getHouseholdById(Long pId) {
		return getHouseholdById(pId, false, false, false);
	}

	@Override
	public Household getHouseholdById(Long pId, boolean pLoadIncomes, boolean pLoadExpenses, boolean pLoadMonths) {
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
		}

		return household;
	}

	@Override
	public void deleteAllHouseholds() {
		householdDao.deleteAll();
	}

}
