package com.pcorbett.littleBlackBook.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
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

}
