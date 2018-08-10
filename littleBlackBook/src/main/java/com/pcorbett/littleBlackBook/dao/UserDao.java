package com.pcorbett.littleBlackBook.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcorbett.littleBlackBook.domain.db.User;

/**
 * Spring Data Repository for the {@link User}
 * 
 * @author Patrick Corbett
 *
 * @created 09.08.2018
 */
@Repository
public interface UserDao extends JpaRepository<User, Long> {

	Optional<User> findOneByUsernameOrEmailAddress(String pUsername, String pEmailAddress);

}
