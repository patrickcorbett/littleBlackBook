package com.pcorbett.littleBlackBook.dto;

import java.math.BigDecimal;
import java.util.List;

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
	private String id;

	@JoinColumn(name = "INCOME_ID")
	@OneToOne
	private Income income;

	@Column(name = "YEAR")
	private String year;

	@Column(name = "MONTH")
	private String month;

	@Column(name = "BONUS")
	private BigDecimal bonus;

	@Column(name = "PROJECTED_NET")
	private BigDecimal projectedNet;

	@OneToMany
	@JoinTable(name = "MONTH_EXPENSES", joinColumns = @JoinColumn(name = "MONTH_ID") , inverseJoinColumns = @JoinColumn(name = "EXPENSE_ID") )
	private List<SingleExpense> expenses;

}
