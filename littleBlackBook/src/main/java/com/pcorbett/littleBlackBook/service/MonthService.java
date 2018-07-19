package com.pcorbett.littleBlackBook.service;

import com.pcorbett.littleBlackBook.dto.Month;

/**
 * @author Patrick Corbett
 *
 * @created 17.07.2018
 */
public interface MonthService {

	Month saveMonth(Month pMonth);
	
	void deleteAllMonths();
	
}
