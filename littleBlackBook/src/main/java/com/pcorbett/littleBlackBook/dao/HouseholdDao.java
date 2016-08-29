package com.pcorbett.littleBlackBook.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcorbett.littleBlackBook.dto.Household;

/**
 * Spring Data Repository for the {@link Household}
 * 
 * @author Patrick Corbett
 * @created 29 Aug 2016
 * 
 *
 */
@Repository
public interface HouseholdDao extends JpaRepository<Household, Long> {

}
