package com.bank.mts.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNTS", schema = "mts")
public class Account {

	@Id
	private String num;
	private double balance;
	private String name;
	private AccountType acc_type;

	@Override
	public String toString() {
		return "Account [num=" + num + ", balance=" + balance + ", name=" + name + ", acc_type=" + acc_type + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AccountType getAcc_type() {
		return acc_type;
	}

	public void setAcc_type(AccountType acc_type) {
		this.acc_type = acc_type;
	}

	public Account() {
	}

	public Account(String num, double balance) {
		super();
		this.num = num;
		this.balance = balance;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

}
