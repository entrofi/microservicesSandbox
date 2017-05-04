/* *****************************************************************************
 * State.java
 *
 * Copyright (c) 2012 TAV Bilisim Hizmetleri AS, TURKIYE
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * TAV Bilisim Hizmetleri AS, TURKIYE
 *
 * 
 ******************************************************************************/
package net.entrofi.microservices.sandbox.kbms.domain.model;

import net.entrofi.microservices.sandbox.kbms.domain.model.base.BaseInfoEntity;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/*******************************************************************************
 * State
 *
 *
 *
 * @author hcomak
 * @created Jan 5, 2015
 * @version Jan 5, 2015
 * @see <Add inherited classes>
 ******************************************************************************/
@Entity
@Audited
@Table(name = "KBMS_STATE")
public class State extends BaseInfoEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2203420470441380287L;

	@Column(nullable=false, unique=true)
	private String name;
	
	@ManyToOne
	private Country country;


	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}


	public Country getCountry() {
		return country;
	}


	public void setCountry(Country country) {
		this.country = country;
	}
	
	
}
