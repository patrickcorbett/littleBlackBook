package com.pcorbett.littleBlackBook.domain.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Patrick Corbett
 *
 * @created 23.07.2018
 */
public class IncomeDTO {

	private Long id;
	private String name;
	private BigDecimal gross;
	private BigDecimal net;
	private String currency;

	public Long getId() {
		return id;
	}

	public void setId(Long pId) {
		id = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String pName) {
		name = pName;
	}

	public BigDecimal getGross() {
		return gross;
	}

	public void setGross(BigDecimal pGross) {
		gross = pGross;
	}

	public BigDecimal getNet() {
		return net;
	}

	public void setNet(BigDecimal pNet) {
		net = pNet;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String pCurrency) {
		currency = pCurrency;
	}

}
