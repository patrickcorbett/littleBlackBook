package com.pcorbett.littleBlackBook.controllers;

import javax.inject.Inject;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pcorbett.littleBlackBook.domain.db.User;
import com.pcorbett.littleBlackBook.domain.dto.UserDTO;
import com.pcorbett.littleBlackBook.service.UserService;

/**
 * @author Patrick Corbett
 *
 * @created 06.03.2020
 */
@RestController
@RequestMapping("/rest/user")
public class UserController {

	@Inject
	private UserService userService;

	/*
	 * create a modelmapper to automatically map between DTO and Domain objects,
	 * more info at: http://modelmapper.org/getting-started/
	 * http://www.baeldung.com/entity-to-and-from-dto-for-a-java-spring-application
	 */
	@Inject
	private ModelMapper modelMapper;

	/**
	 * Returns a blank user object
	 * 
	 * @return create a new user
	 */
	@RequestMapping(value = "/", produces = { "application/json" }, consumes = { "*" }, method = RequestMethod.GET)
	public UserDTO blankUser() {
		return modelMapper.map(new User(), UserDTO.class);
	}
	
	/**
	 * Create a new user
	 * 
	 * @return the created user
	 */
	@RequestMapping(value = "/", produces = { "application/json" }, consumes = { "*" }, method = RequestMethod.PUT)
	public UserDTO createUser(@RequestBody UserDTO pUser) {
		// convert the user DTO to the Domain object
		User userToSave = modelMapper.map(pUser, User.class);

		// Validation ??

		// Save the user
		User user = userService.createUser(userToSave);

		// convert the user Domain object to the Simpler DTO
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);

		return userDTO;
	}

	/**
	 * Update a user
	 * 
	 * @return the updated user
	 */
	@RequestMapping(value = "/{userId}", produces = { "application/json" }, consumes = { "*" }, method = RequestMethod.POST)
	public UserDTO updateUser(@PathVariable("userId") Long pUserId, @RequestBody UserDTO pUser) {
		// convert the user DTO to the Domain object
		User userToUpdate = modelMapper.map(pUser, User.class);
		
		// Validation ??
		
		// Save the user
		User user = userService.updateUser(userToUpdate);
		
		// convert the user Domain object to the Simpler DTO
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		
		return userDTO;
	}
	
	/**
	 * Delete a user
	 * 
	 * @return the updated user
	 */
	@RequestMapping(value = "/{userId}", produces = { "application/json" }, consumes = { "*" }, method = RequestMethod.DELETE)
	public ResponseEntity updateUser(@PathVariable("userId") Long pUserId) {
		// Delete the user
		userService.deleteUser(pUserId);
		
		
	}
	
	
	
	
//	-- update user / change password
//	POST /user/{0}

//	-- LOGIN
//	POST user/login/
//	-- forgot password
//	POST /user/forgot

}