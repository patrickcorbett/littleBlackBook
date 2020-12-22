package com.pcorbett.littleBlackBook.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.pcorbett.littleBlackBook.dao.HouseholdDao;
import com.pcorbett.littleBlackBook.dao.MonthDao;
import com.pcorbett.littleBlackBook.domain.db.Household;
import com.pcorbett.littleBlackBook.domain.db.Month;
import com.pcorbett.littleBlackBook.domain.db.SingleExpense;
import com.pcorbett.littleBlackBook.service.HouseholdMonthService;

/**
 * @author Patrick Corbett
 *
 * @created 14.09.2019
 */
@Service
public class HouseholdMonthServiceImpl extends AbstractServiceImpl implements HouseholdMonthService {
	@Inject
	private HouseholdDao householdDao;

	@Inject
	private MonthDao monthDao;

	@Override
	public List<Month> getHouseholdMonths(Long pHouseholdId) {
		Optional<Household> opHousehold = householdDao.findById(pHouseholdId);

		if (opHousehold.isPresent()) {
			// load the months for the household
			return new ArrayList<Month>(opHousehold.get().getMonths());
		}

		return Collections.emptyList();
	}

	@Override
	public Month getHouseholdMonth(Long pHouseholdId, Long pMonthId) {
		Optional<Month> opMonth = monthDao.findById(pMonthId);

		Month month = null;

		if (opMonth.isPresent()) {
			month = opMonth.get();

			// load the month expenses!
			month.getExpenses().size();
		}

		return month;
	}

	@Override
	public Household createHouseholdMonth(Long pHouseholdId, Month pMonth) {
		Optional<Household> opHousehold = householdDao.findById(pHouseholdId);

		Household household = null;
		
		if (opHousehold.isPresent()) {
			household = opHousehold.get();
			
			// add the month
			household.addMonth(pMonth);

			// update household
			householdDao.save(household);
		}
		
		return household;
	}

	@Override
	public Household updateHouseholdMonth(Long pHouseholdId, Month pMonth) {
		Optional<Household> opHousehold = householdDao.findById(pHouseholdId);

		Household household = null;
		
		if (opHousehold.isPresent()) {
			household = opHousehold.get();
			
			// add the month
			household.addMonth(pMonth);

			// update household
			householdDao.save(household);
		}
		
		return household;
	}

	@Override
	public Household deleteHouseholdMonth(Long pHouseholdId, Long pMonthId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SingleExpense> getHouseholdMonthSingleExpenses(Long pHouseholdId, Long pMonthId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Household createHouseholdSingleExpense(Long pHouseholdId, Long pMonthId, SingleExpense pSingleExpense) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Household updateHouseholdSingleExpense(Long pHouseholdId, Long pMonthId, SingleExpense pSingleExpense) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Household deleteHouseholdSingleExpense(Long pHouseholdId, Long pMonthId, Long pSingleExpenseId) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
