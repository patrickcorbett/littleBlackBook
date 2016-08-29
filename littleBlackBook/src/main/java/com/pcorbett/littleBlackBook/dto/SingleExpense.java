package com.pcorbett.littleBlackBook.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Patrick Corbett
 * @created 28.08.2016
 *
 */
@Entity
@Table
public class SingleExpense {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private String id;

	@Column(name = "COMPLETE")
	private Boolean complete;

}
