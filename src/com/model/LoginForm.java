package com.model;

import javax.servlet.http.HttpServletRequest;
import javax.persistence.*;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

@Entity
@Table (name = "customers")
public class LoginForm extends org.apache.struts.action.ActionForm {
	@Id @GeneratedValue
	private int pk_id;
	private String name;
	private String pass;
	@Transient
	private String message;

	public LoginForm() {

	}

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();

		if(name == null || name.length() < 1) {
			errors.add("name", new ActionMessage("error.userName.required"));
		}
		if(pass == null || pass.length() < 1) {
			errors.add("pass", new ActionMessage("error.password.required"));
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

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}