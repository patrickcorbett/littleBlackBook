package com.pcorbett.littleBlackBook.domain.dto;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.modelmapper.ModelMapper;

import com.pcorbett.littleBlackBook.domain.db.Household;

/**
 * @author Patrick Corbett
 *
 * @created 21.07.2018
 */
public class HouseholdDTOMappingTest {

	private ModelMapper modelMapper = new ModelMapper();

	@Test
	public void testEntityToDtoMapping() {
		Household household = new Household();
		household.setId(Long.valueOf(1));
		household.setName("The Flintstones");

		HouseholdDTO householdDTO = modelMapper.map(household, HouseholdDTO.class);
		assertEquals(household.getId(), householdDTO.getId());
		assertEquals(household.getName(), householdDTO.getName());
	}

	@Test
	public void testDtoToEntityMapping() {
		HouseholdDTO householdDto = new HouseholdDTO();
		householdDto.setId(Long.valueOf(2));
		householdDto.setName("The Rubbles");

		Household household = modelMapper.map(householdDto, Household.class);
		assertEquals(householdDto.getId(), household.getId());
		assertEquals(householdDto.getName(), household.getName());
	}

}
