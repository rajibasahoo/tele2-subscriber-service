package com.tele2.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 
 * @author rajiba sahoo
 * This class is model for Subscription where id is the primary key in the table in DB.
 * 
 */

@Entity
public class Subscription implements Serializable {
	@Id	
	private Integer id;
	private String name;
	private BigDecimal monthlyPrice;
	private Date lastUpdate;	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public BigDecimal getMonthlyPrice() {
		return monthlyPrice;
	}
	public void setMonthlyPrice(BigDecimal monthlyPrice) {
		this.monthlyPrice = monthlyPrice;
	}
	public Subscription(Integer id, String name,BigDecimal monthlyPrice, Date lastUpdate) {
		super();
		this.id = id;
		this.name = name;
		this.lastUpdate = lastUpdate;
		this.monthlyPrice= monthlyPrice;
	}
	
	public Subscription() {

		
	}
	

}
