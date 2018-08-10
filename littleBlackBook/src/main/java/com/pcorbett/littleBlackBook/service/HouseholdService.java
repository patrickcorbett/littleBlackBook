package com.pcorbett.littleBlackBook.service;

import java.util.List;
import java.util.Set;

import com.pcorbett.littleBlackBook.domain.db.Household;
import com.pcorbett.littleBlackBook.domain.db.Income;

/**
 * @author Patrick Corbett
 *
 * @created 14.07.2018
 */
public interface HouseholdService {

	Household saveHousehold(Household pHousehold);

	List<Household> getAllHouseholds();
	
	Household getHouseholdById(Long pId);
	
	Household getHouseholdById(Long pId, boolean pLoadIncomes, boolean pLoadExpenses, boolean pLoadMonths, boolean pLoadUsers);

	void deleteAllHouseholds();

}
