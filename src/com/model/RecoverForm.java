package com.model;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

@Entity
@Table (name = "customers")
public class RecoverForm extends org.apache.struts.action.ActionForm {
	@Id
	int pk_id;
	private String name;
	private String question;
	private String pass;
	@Transient
	private String message;
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();

		if(name == null || name.length() < 1) {
			errors.add("name", new ActionMessage("error.name.required"));
		}
		if(pass == null || pass.length() < 1) {
			errors.add("pass", new ActionMessage("error.pass.required"));
		}
		if(question == null || question.length() < 1) {
			errors.add("question", new ActionMessage("error.question.required"));
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
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
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
