package com.pcorbett.littleBlackBook.service.impl;

import java.util.Optional;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

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

	@Override
	public User createUser(User pUser) {
		// TODO validation
		return userDao.save(pUser);
	}

	@Override
	public User updateUser(User pUser) {
		return userDao.save(pUser);
	}

	@Override
	public User login(String pUsernameOrEmailAddress, String pPassword) {
		// lookup the user
		Optional<User> user = userDao.findOneByUsernameOrEmailAddress(pUsernameOrEmailAddress, pUsernameOrEmailAddress);
		// TODO validation of username/password
		return user.isPresent() ? user.get() : null;
	}

	@Override
	public void deleteUser(Long pUserId) {
		userDao.deleteById(pUserId);
	}

	@Override
	public void forgottenPassword(String pUsernameOrEmailAddress) {
		// TODO Auto-generated method stub
		
	}

}
