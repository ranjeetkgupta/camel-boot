package com.citi.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "account")
@XmlType(propOrder = {"balance","lastBill","currentBill"})
public class Account {
	
	public Account() {
		super();
	}
	@XmlElement
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	
	@XmlElement
	public String getLastBill() {
		return lastBill;
	}
	public void setLastBill(String lastBill) {
		this.lastBill = lastBill;
	}
	
	@XmlElement
	public String getCurrentBill() {
		return currentBill;
	}
	public void setCurrentBill(String currentBill) {
		this.currentBill = currentBill;
	}
	public Account(String balance, String lastBill, String currentBill) {
		super();
		this.balance = balance;
		this.lastBill = lastBill;
		this.currentBill = currentBill;
	}
	String balance;
	String lastBill;
	String currentBill;

}
