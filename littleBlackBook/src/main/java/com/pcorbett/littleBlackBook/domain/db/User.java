package com.pcorbett.littleBlackBook.domain.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * POJO Class for the description of a user
 * 
 * @author Patrick Corbett
 *
 * @created 09.08.2018
 */
@Entity
@Table
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "USERNAME", unique = true)
	private String username;

	@Column(name = "FIRSTNAME")
	private String firstName;

	@Column(name = "SURNAME")
	private String surname;

	@Column(name = "EMAIL_ADDRESS", unique = true)
	private String emailAddress;

	@Column(name = "PASSWORD")
	private String password;

	@OneToOne
	@JoinColumn(name = "FK_INCOME")
	private Income income;

	@OneToOne
	@JoinColumn(name = "FK_HOUSEHOLD")
	private Household household;

	@OneToOne(mappedBy = "owner", fetch = FetchType.LAZY)
	private Household ownedHousehold;

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

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param pPassword the password to set
	 */
	public void setPassword(String pPassword) {
		password = pPassword;
	}

	/**
	 * @return the income
	 */
	public Income getIncome() {
		return income;
	}

	/**
	 * @param pIncome the income to set
	 */
	public void setIncome(Income pIncome) {
		income = pIncome;
	}

	/**
	 * @return the household
	 */
	public Household getHousehold() {
		return household;
	}

	/**
	 * @param pHousehold the household to set
	 */
	public void setHousehold(Household pHousehold) {
		household = pHousehold;
	}

	/**
	 * @return the ownedHousehold
	 */
	public Household getOwnedHousehold() {
		return ownedHousehold;
	}

}
