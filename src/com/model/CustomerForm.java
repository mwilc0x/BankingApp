package com.model;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

@Entity
@Table (name = "customers")
public class CustomerForm extends org.apache.struts.action.ActionForm {
	@Id
	private int pk_id;
	private String name;
	private String address;
	private String city;
	private String state;
	private String pin;
	private String phone;
	private String email;
	private String pass;
	private String question;
	@Transient
	private String message;
	
	/*public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();

		if(name == null || name.length() < 1) {
			errors.add("name", new ActionMessage("error.name.required"));
		}
		if(address == null || address.length() < 1) {
			errors.add("address", new ActionMessage("error.address.required"));
		}
		if(city == null || city.length() < 1) {
			errors.add("city", new ActionMessage("error.city.required"));
		}
		if(state == null || state.length() < 1) {
			errors.add("state", new ActionMessage("error.state.required"));
		}
		if(pin == null || pin.length() < 1) {
			errors.add("pin", new ActionMessage("error.pin.required"));
		}
		if(phone == null || phone.length() < 1) {
			errors.add("phone", new ActionMessage("error.phone.required"));
		}
		if(email == null || email.length() < 1) {
			errors.add("email", new ActionMessage("error.email.required"));
		}
		if(pass == null || pass.length() < 1) {
			errors.add("pass", new ActionMessage("error.pass.required"));
		}
		if(question == null || question.length() < 1) {
			errors.add("question", new ActionMessage("error.question.required"));
		}
		return errors;
	}*/
	
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
