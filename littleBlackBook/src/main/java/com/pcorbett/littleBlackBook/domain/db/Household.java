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

	@OneToMany(mappedBy = "household", cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REMOVE }, orphanRemoval = true, fetch = FetchType.LAZY)
	private Set<Income> incomes = new LinkedHashSet<>();

	@OneToMany(mappedBy = "household", cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REMOVE }, orphanRemoval = true, fetch = FetchType.LAZY)
	private Set<RecurringExpense> expenses = new LinkedHashSet<>();

	@OneToMany(mappedBy = "household", cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REMOVE }, orphanRemoval = true, fetch = FetchType.LAZY)
	private Set<Month> months = new LinkedHashSet<>();

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
	 * @return the incomes
	 */
	public Set<Income> getIncomes() {
		return incomes;
	}

	/**
	 * @param pIncome the income to add
	 */
	public void addIncome(Income pIncome) {
		incomes.add(pIncome);
		pIncome.setHousehold(this);
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

}
