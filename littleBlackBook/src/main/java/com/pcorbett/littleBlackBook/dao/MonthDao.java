package com.pcorbett.littleBlackBook.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcorbett.littleBlackBook.dto.Month;

/**
 * Spring Data Repository for the {@link Month}
 * 
 * @author Patrick Corbett
 *
 * @created 17.07.2018
 */
@Repository
public interface MonthDao extends JpaRepository<Month, Long> {

}
