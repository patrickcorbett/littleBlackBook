package com.pcorbett.littleBlackBook.service;

import com.pcorbett.littleBlackBook.domain.db.User;

/**
 * @author Patrick Corbett
 *
 * @created 17.07.2018
 */
public interface UserService {

	User saveUser(User pUser);

	User login(String pUsernameOrEmailAddress, String pPassword);

}
