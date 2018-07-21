package com.pcorbett.littleBlackBook.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pcorbett.littleBlackBook.domain.db.Household;
import com.pcorbett.littleBlackBook.service.HouseholdService;

/**
 * @author Patrick Corbett
 *
 * @created 21.07.2018
 */
@RestController
@RequestMapping("household")
public class HouseholdController {

	@Inject
	private HouseholdService householdService;

	/**
	 * Create a new household
	 * 
	 * @return create a new household
	 */
	@RequestMapping(value = "/", produces = { "application/json" }, consumes = { "*" }, method = RequestMethod.POST)
	public Household createHouseholds(@RequestBody Household pHousehold) {

		Household household = householdService.saveHousehold(pHousehold);

		// convert the household

		return household;
	}

	/**
	 * List all households
	 * 
	 * @return all households
	 */
	@RequestMapping(value = "/households", produces = { "application/json" })
	public List<Household> getHouseholds() {

		List<Household> households = new ArrayList<>();
		households = householdService.getAllHouseholds();

		// convert the household

		return households;
	}

}