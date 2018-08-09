package com.pcorbett.littleBlackBook.service.impl;

import java.util.Optional;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.pcorbett.littleBlackBook.dao.MonthDao;
import com.pcorbett.littleBlackBook.domain.db.Month;
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
	public Month getMonthById(Long pId) {
		return getMonthById(pId, false);
	}

	@Override
	public Month getMonthById(Long pId, boolean pLoadExpenses) {
		Optional<Month> opMonth = monthDao.findById(pId);

		Month month = null;

		if (opMonth.isPresent()) {
			month = opMonth.get();

			if (pLoadExpenses) {
				month.getExpenses().size();
			}
		}

		return month;
	}

}
