package com.pcorbett.littleBlackBook.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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

	@OneToMany
	@JoinTable(name = "HOUSEHOLD_INCOMES", joinColumns = @JoinColumn(name = "HOUSEHOLD_ID") , inverseJoinColumns = @JoinColumn(name = "INCOME_ID") )
	private List<Income> incomes;

	@OneToMany
	@JoinTable(name = "HOUSEHOLD_EXPENSES", joinColumns = @JoinColumn(name = "HOUSEHOLD_ID") , inverseJoinColumns = @JoinColumn(name = "EXPENSE_ID") )
	private List<RecurringExpense> expenses;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the incomes
	 */
	public List<Income> getIncomes() {
		return incomes;
	}

	/**
	 * @param incomes
	 *            the incomes to set
	 */
	public void setIncomes(List<Income> incomes) {
		this.incomes = incomes;
	}

	/**
	 * @return the expenses
	 */
	public List<RecurringExpense> getExpenses() {
		return expenses;
	}

	/**
	 * @param expenses
	 *            the expenses to set
	 */
	public void setExpenses(List<RecurringExpense> expenses) {
		this.expenses = expenses;
	}

}
