package com.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

@Entity
@Table (name = "accounts")
public class WithdrawalForm extends org.apache.struts.action.ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private int pk_id;
	private String name;
	private String balance;
	private String type;
	@Transient
	private String message;
	private int cust_id;
	

	public int getCust_id() {
		return cust_id;
	}

	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}

	public WithdrawalForm() {
		
	}
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();

		if(name == null || name.length() < 1) {
			errors.add("userName", new ActionMessage("error.name.required"));
		}
		if(balance == null || balance.length() < 1) {
			errors.add("password", new ActionMessage("error.amount.required"));
		}
		if(type == null || type.length() < 1) {
			errors.add("password", new ActionMessage("error.type.required"));
		}
		return errors;
	}

	public int getPk_id() {
		return pk_id;
	}

	public void setPk_id(int pk_id) {
		this.pk_id = pk_id;
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
	public void setBalance(String amount) {
		this.balance = amount;
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
	
	

}
