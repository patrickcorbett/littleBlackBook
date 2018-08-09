package com.pcorbett.littleBlackBook.domain.db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * POJO Class for the description of a particular expense
 * 
 * @author Patrick Corbett
 *
 * @created 14.07.2018
 */
@Entity
@Table(name = "RECURRING_EXPENSE")
@PrimaryKeyJoinColumn(name = "ID")
public class RecurringExpense extends Expense {

	@Column(name = "PRIORITY")
	private Integer priority;

	@Column(name = "DEBIT_ON_DOM")
	private Integer debitOnDOM;

	@Column(name = "VALID_FROM")
	private Date validFrom;

	@Column(name = "VALID_TO")
	private Date validTo;

	@ManyToOne
	@JoinColumn(name = "FK_HOUSEHOLD")
	private Household household;

	/**
	 * @return the priority
	 */
	public Integer getPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	/**
	 * @return the debitOnDOM
	 */
	public Integer getDebitOnDOM() {
		return debitOnDOM;
	}

	/**
	 * @param debitOnDOM the debitOnDOM to set
	 */
	public void setDebitOnDOM(Integer debitOnDOM) {
		this.debitOnDOM = debitOnDOM;
	}

	/**
	 * @return the validFrom
	 */
	public Date getValidFrom() {
		return validFrom;
	}

	/**
	 * @param validFrom the validFrom to set
	 */
	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	/**
	 * @return the validTo
	 */
	public Date getValidTo() {
		return validTo;
	}

	/**
	 * @param validTo the validTo to set
	 */
	public void setValidTo(Date validTo) {
		this.validTo = validTo;
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

}
