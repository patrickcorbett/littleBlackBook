package com.pcorbett.littleBlackBook.dto;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * POJO Class for the description of a month and its expenses
 * 
 * @author Patrick Corbett
 * @created 29 Aug 2016
 * 
 *
 */
@Entity
@Table
public class Month {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@JoinColumn(name = "HOUSEHOLD_ID", nullable = false)
	@OneToOne
	private Household household;

	@Column(name = "YEAR")
	private String year;

	@Column(name = "MONTH")
	private String month;

	@Column(name = "BONUS")
	private BigDecimal bonus;

	@Column(name = "PROJECTED_NET")
	private BigDecimal projectedNet;

	@OneToMany
	@JoinTable(name = "MONTH_EXPENSES", joinColumns = @JoinColumn(name = "MONTH_ID"), inverseJoinColumns = @JoinColumn(name = "EXPENSE_ID"))
	private Set<SingleExpense> expenses = new LinkedHashSet<>();

	public Long getId() {
		return id;
	}

	public Household getHousehold() {
		return household;
	}

	public void setHousehold(Household pHousehold) {
		household = pHousehold;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String pYear) {
		year = pYear;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String pMonth) {
		month = pMonth;
	}

	public BigDecimal getBonus() {
		return bonus;
	}

	public void setBonus(BigDecimal pBonus) {
		bonus = pBonus;
	}

	public BigDecimal getProjectedNet() {
		return projectedNet;
	}

	public void setProjectedNet(BigDecimal pProjectedNet) {
		projectedNet = pProjectedNet;
	}

	public Set<SingleExpense> getExpenses() {
		return expenses;
	}

	public void setExpenses(Set<SingleExpense> pExpenses) {
		this.expenses.clear();
		this.expenses.addAll(pExpenses);
	}

}
