package com.pcorbett.littleBlackBook.service;

import com.pcorbett.littleBlackBook.domain.db.User;

/**
 * @author Patrick Corbett
 *
 * @created 17.07.2018
 */
public interface UserService {

	User createUser(User pUser);

	User updateUser(User pUser);

	void deleteUser(Long pUserId);

	User login(String pUsernameOrEmailAddress, String pPassword);

	void forgottenPassword(String pUsernameOrEmailAddress);
}
