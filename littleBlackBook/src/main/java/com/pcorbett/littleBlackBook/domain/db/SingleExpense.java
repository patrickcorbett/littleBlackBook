package com.pcorbett.littleBlackBook.domain.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * @author Patrick Corbett
 * 
 * @created 28.08.2016
 */
@Entity
@Table(name = "SINGLE_EXPENSE")
@PrimaryKeyJoinColumn(name = "ID")
public class SingleExpense extends Expense {

	@Column(name = "COMPLETE")
	private Boolean complete;

	@ManyToOne
	@JoinColumn(name = "FK_MONTH")
	private Month month;

	/**
	 * @return the complete
	 */
	public Boolean getComplete() {
		return complete;
	}

	/**
	 * @param pComplete the complete to set
	 */
	public void setComplete(Boolean pComplete) {
		complete = pComplete;
	}

	/**
	 * @return the month
	 */
	public Month getMonth() {
		return month;
	}

	/**
	 * @param pMonth the month to set
	 */
	public void setMonth(Month pMonth) {
		month = pMonth;
	}

}
