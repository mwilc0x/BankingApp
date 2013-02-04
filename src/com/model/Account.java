package com.model;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class Account extends org.apache.struts.action.ActionForm {
	private static final long serialVersionUID = 1L;
	private String name;
	private String balance;
	private String withdrawal;
	private String deposit;
	private String message;
	private String type;

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();

		if(name == null || name.length() < 1) {
			errors.add("userName", new ActionMessage("error.name.required"));
		}
		if(balance == null || balance.length() < 1) {
			errors.add("password", new ActionMessage("error.password.required"));
		}
		if(withdrawal == null || withdrawal.length() < 1) {
			errors.add("userName", new ActionMessage("error.withdrawal.required"));
		}
		if(deposit == null || deposit.length() < 1) {
			errors.add("password", new ActionMessage("error.deposit.required"));
		}
		if(type == null || type.length() < 1) {
			errors.add("userName", new ActionMessage("error.type.required"));
		}
		return errors;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDeposit() {
		return deposit;
	}
	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}
	public String getWithdrawal() {
		return withdrawal;
	}
	public void setWithdrawal(String withdrawal) {
		this.withdrawal = withdrawal;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
}
