package com.pcorbett.littleBlackBook.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcorbett.littleBlackBook.dto.Income;

/**
 * Spring Data Repository for the {@link Income}
 * 
 * @author Patrick Corbett
 * @created 29 Aug 2016
 * 
 *
 */
@Repository
public interface IncomeDao extends JpaRepository<Income, Long> {

}
