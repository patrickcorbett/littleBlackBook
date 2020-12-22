package com.pcorbett.littleBlackBook.domain.dto;

public class UserDTO {

	private Long id;
	private String username;
	private String firstName;
	private String surname;
	private String emailAddress;
	private String password;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param pId the id to set
	 */
	public void setId(Long pId) {
		id = pId;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param pUsername the username to set
	 */
	public void setUsername(String pUsername) {
		username = pUsername;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param pFirstName the firstName to set
	 */
	public void setFirstName(String pFirstName) {
		firstName = pFirstName;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param pSurname the surname to set
	 */
	public void setSurname(String pSurname) {
		surname = pSurname;
	}

	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @param pEmailAddress the emailAddress to set
	 */
	public void setEmailAddress(String pEmailAddress) {
		emailAddress = pEmailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String pPassword) {
		password = pPassword;
	}

}
