package com.pcorbett.littleBlackBook.domain.db;

import java.math.BigDecimal;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

	@Column(name = "YEAR")
	private String year;

	@Column(name = "MONTH")
	private String month;

	@Column(name = "BONUS")
	private BigDecimal bonus;

	@Column(name = "PROJECTED_NET")
	private BigDecimal projectedNet;

	@OneToMany(mappedBy = "month", cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REMOVE }, orphanRemoval = true, fetch = FetchType.LAZY)
	private Set<SingleExpense> expenses = new LinkedHashSet<>();

	@ManyToOne
	@JoinColumn(name = "FK_HOUSEHOLD", nullable = false)
	private Household household;

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

	public void addExpense(SingleExpense pExpense) {
		this.expenses.add(pExpense);
		pExpense.setMonth(this);
	}

}
