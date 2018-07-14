package com.pcorbett.littleBlackBook.service;

import java.util.List;

import com.pcorbett.littleBlackBook.dto.Household;

/**
 * @author Patrick Corbett
 *
 * @created 14.07.2018
 */
public interface HouseholdService {

	Household saveHousehold(Household pHousehold);

	List<Household> getAllHouseholds();

	Household getHouseholdById(Long pId);

	void deleteAllHouseholds();

}
