package com.pcorbett.littleBlackBook.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.pcorbett.littleBlackBook.dao.HouseholdDao;
import com.pcorbett.littleBlackBook.dto.Household;
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
		return householdDao.findOne(pId);
	}

	@Override
	public void deleteAllHouseholds() {
		householdDao.deleteAll();
	}

}
