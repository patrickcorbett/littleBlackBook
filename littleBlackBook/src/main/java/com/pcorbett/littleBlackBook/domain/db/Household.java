package com.pcorbett.littleBlackBook.domain.db;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.JoinFormula;

/**
 * POJO Class for the description of a household and its income
 * 
 * @author Patrick Corbett
 * @created 29 Aug 2016
 * 
 *
 */
@Entity
@Table
public class Household {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME")
	private String name;

	@OneToMany(mappedBy = "household", cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REMOVE }, orphanRemoval = true, fetch = FetchType.LAZY)
	private Set<RecurringExpense> expenses = new LinkedHashSet<>();

	@OneToMany(mappedBy = "household", cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REMOVE }, orphanRemoval = true, fetch = FetchType.LAZY)
	private Set<Month> months = new LinkedHashSet<>();

	@OneToMany(mappedBy = "household", cascade = { CascadeType.MERGE,
			CascadeType.REMOVE }, orphanRemoval = false, fetch = FetchType.LAZY)
	private Set<User> users = new LinkedHashSet<>();

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_OWNER")
	private User owner;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long pId) {
		id = pId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the expenses
	 */
	public Set<RecurringExpense> getExpenses() {
		return expenses;
	}

	/**
	 * @param pExpense the expense to add
	 */
	public void addExpense(RecurringExpense pExpense) {
		expenses.add(pExpense);
		pExpense.setHousehold(this);
	}

	/**
	 * @return the months
	 */
	public Set<Month> getMonths() {
		return months;
	}

	/**
	 * @param pMonth the month to add
	 */
	public void addMonth(Month pMonth) {
		months.add(pMonth);
		pMonth.setHousehold(this);
	}

	/**
	 * @return the users
	 */
	public Set<User> getUsers() {
		return users;
	}

	/**
	 * @param pUser the user to add
	 */
	public void addUser(User pUser) {
		users.add(pUser);
		pUser.setHousehold(this);
	}

	/**
	 * @param pUser the user to remove
	 */
	public void removeUser(User pUser) {
		users.remove(pUser);
		pUser.setHousehold(null);
	}

	/**
	 * @return the owner
	 */
	public User getOwner() {
		return owner;
	}

	/**
	 * @param pOwner the owner to set
	 */
	public void setOwner(User pOwner) {
		owner = pOwner;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Household other = (Household) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
