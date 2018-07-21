package com.pcorbett.littleBlackBook.domain.db;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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

	@Fetch(FetchMode.JOIN)
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "HOUSEHOLD_INCOMES", joinColumns = @JoinColumn(name = "HOUSEHOLD_ID"), inverseJoinColumns = @JoinColumn(name = "INCOME_ID"))
	private Set<Income> incomes = new LinkedHashSet<>();

	@Fetch(FetchMode.JOIN)
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "HOUSEHOLD_EXPENSES", joinColumns = @JoinColumn(name = "HOUSEHOLD_ID"), inverseJoinColumns = @JoinColumn(name = "EXPENSE_ID"))
	private Set<RecurringExpense> expenses = new LinkedHashSet<>();

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
	 * @param incomes the incomes to set
	 */
	public void setIncomes(Set<Income> incomes) {
		this.incomes.clear();
		this.incomes.addAll(incomes);
	}

	/**
	 * @return the expenses
	 */
	public Set<RecurringExpense> getExpenses() {
		return expenses;
	}

	/**
	 * @param expenses the expenses to set
	 */
	public void setExpenses(Set<RecurringExpense> expenses) {
		this.expenses.clear();
		this.expenses.addAll(expenses);
	}

}
