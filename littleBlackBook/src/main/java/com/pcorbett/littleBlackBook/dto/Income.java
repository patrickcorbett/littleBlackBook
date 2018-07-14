package com.pcorbett.littleBlackBook.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * POJO Class for the description of an income
 * 
 * @author Patrick Corbett
 * @created 29 Aug 2016
 * 
 *
 */
@Entity
@Table
public class Income {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "GROSS")
	private BigDecimal gross;

	@Column(name = "NET")
	private BigDecimal net;

	@Column(name = "VALID_FROM")
	private Date validFrom;

	@Column(name = "VALID_TO")
	private Date validTo;

	@Column(name = "CURRENCY")
	private String currency;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
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
	 * @return the gross
	 */
	public BigDecimal getGross() {
		return gross;
	}

	/**
	 * @param gross
	 *            the gross to set
	 */
	public void setGross(BigDecimal gross) {
		this.gross = gross;
	}

	/**
	 * @return the net
	 */
	public BigDecimal getNet() {
		return net;
	}

	/**
	 * @param net
	 *            the net to set
	 */
	public void setNet(BigDecimal net) {
		this.net = net;
	}

	/**
	 * @return the validFrom
	 */
	public Date getValidFrom() {
		return validFrom;
	}

	/**
	 * @param validFrom
	 *            the validFrom to set
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
	 * @param validTo
	 *            the validTo to set
	 */
	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency
	 *            the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
