package com.pcorbett.littleBlackBook.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pcorbett.littleBlackBook.domain.db.Household;
import com.pcorbett.littleBlackBook.domain.db.Income;
import com.pcorbett.littleBlackBook.domain.dto.HouseholdDTO;
import com.pcorbett.littleBlackBook.domain.dto.IncomeDTO;
import com.pcorbett.littleBlackBook.service.HouseholdService;

/**
 * @author Patrick Corbett
 *
 * @created 21.07.2018
 */
@RestController
@RequestMapping("/rest/household")
public class HouseholdController {

//	@Inject
//	private HouseholdService householdService;
//
//	/*
//	 * create a modelmapper to automatically map between DTO and Domain objects,
//	 * more info at: http://modelmapper.org/getting-started/
//	 * http://www.baeldung.com/entity-to-and-from-dto-for-a-java-spring-application
//	 */
//	@Inject
//	private ModelMapper modelMapper;
//
//	/**
//	 * Create a new household
//	 * 
//	 * @return create a new household
//	 */
//	@RequestMapping(value = "/", produces = { "application/json" }, consumes = { "*" }, method = RequestMethod.POST)
//	public HouseholdDTO createHousehold(@RequestBody HouseholdDTO pHousehold) {
//		// convert the household DTO to the Domain object
//		Household householdToSave = modelMapper.map(pHousehold, Household.class);
//
//		// Validation ??
//
//		// Save the household
//		Household household = householdService.saveHousehold(householdToSave);
//
//		// convert the household Domain object to the Simpler DTO
//		HouseholdDTO householdDTO = modelMapper.map(household, HouseholdDTO.class);
//
//		return householdDTO;
//	}
//
//	/**
//	 * List all households
//	 * 
//	 * @return all households
//	 */
//	@RequestMapping(value = "/households", produces = { "application/json" })
//	public List<HouseholdDTO> getHouseholds() {
//		List<HouseholdDTO> allHouseholds = new ArrayList<>();
//		List<Household> households = householdService.getAllHouseholds();
//
//		// convert the households
//		for (Household household : households) {
//			allHouseholds.add(modelMapper.map(household, HouseholdDTO.class));
//		}
//		return allHouseholds;
//	}
//
//	/**
//	 * Create a new income for a specific household
//	 * 
//	 * @return create a new household income
//	 */
//	@RequestMapping(value = "/{householdId}/income", produces = { "application/json" }, consumes = {
//			"*" }, method = RequestMethod.POST)
//	public IncomeDTO createHouseholdIncome(@PathVariable("householdId") Long pHouseholdId,
//			@RequestBody IncomeDTO pIncome) {
//		// convert the household DTO to the Domain object
//		Income incomeToSave = modelMapper.map(pIncome, Income.class);
//
//		// Validation ??
//		incomeToSave.setValidFrom(new Date());
//
//		Household household = householdService.getHouseholdById(pHouseholdId);
//		household.getIncomes().add(incomeToSave);
//
//		// Save the household
//		household = householdService.saveHousehold(household);
//
//		// convert the Income Domain object to the Simpler DTO
//		IncomeDTO householdDTO = modelMapper.map(incomeToSave, IncomeDTO.class);
//
//		return householdDTO;
//	}
//
//	/**
//	 * List all household incomes for a specific household id
//	 * 
//	 * @return all household incomes
//	 */
//	@RequestMapping(value = "/{householdId}/incomes", produces = { "application/json" })
//	public List<IncomeDTO> getHouseholdIncomes(@PathVariable("householdId") Long pHouseholdId) {
//		List<IncomeDTO> allIncomes = new ArrayList<>();
//		Set<Income> incomes = householdService.getHouseholdById(pHouseholdId).getIncomes();
//
//		// convert the incomes
//		Iterator<Income> iterator = incomes.iterator();
//		while (iterator.hasNext()) {
//			allIncomes.add(modelMapper.map(iterator.next(), IncomeDTO.class));
//		}
//		return allIncomes;
//	}

}