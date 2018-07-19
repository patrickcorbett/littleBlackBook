package com.pcorbett.littleBlackBook.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.pcorbett.littleBlackBook.dao.MonthDao;
import com.pcorbett.littleBlackBook.dto.Month;
import com.pcorbett.littleBlackBook.service.MonthService;

/**
 * @author Patrick Corbett
 *
 * @created 17.07.2018
 */
@Service
public class MonthServiceImpl implements MonthService {

	@Inject
	private MonthDao monthDao;

	@Override
	public Month saveMonth(Month pMonth) {
		return monthDao.save(pMonth);
	}

	@Override
	public void deleteAllMonths() {
		monthDao.deleteAll();
	}

}
