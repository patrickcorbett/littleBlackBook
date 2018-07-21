package com.pcorbett.littleBlackBook.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcorbett.littleBlackBook.domain.db.Household;

/**
 * Spring Data Repository for the {@link Household}
 * 
 * @author Patrick Corbett
 *
 * @created 29.08.2016
 */
@Repository
public interface HouseholdDao extends JpaRepository<Household, Long> {

}