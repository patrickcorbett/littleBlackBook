package com.pcorbett.littleBlackBook.service.impl;

import java.util.Optional;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.pcorbett.littleBlackBook.dao.HouseholdDao;
import com.pcorbett.littleBlackBook.dao.UserDao;
import com.pcorbett.littleBlackBook.domain.db.User;
import com.pcorbett.littleBlackBook.service.UserService;

/**
 * @author Patrick Corbett
 *
 * @created 10.08.2018
 */
@Service
public class UserServiceImpl implements UserService {

	@Inject
	private UserDao userDao;
	@Inject
	private HouseholdDao householdDao;

	@Override
	public User saveUser(User pUser) {
		return userDao.save(pUser);
	}

	@Override
	public User login(String pUsernameOrEmailAddress, String pPassword) {
		// lookup the user
		Optional<User> user = userDao.findOneByUsernameOrEmailAddress(pUsernameOrEmailAddress, pUsernameOrEmailAddress);
		// TODO validation of username/password
		return user.isPresent() ? user.get() : null;
	}

}
