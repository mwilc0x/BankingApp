package com.model;

public class NewUserModel extends org.apache.struts.action.ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
	private String message;
	
	// empty constructor
	public NewUserModel(){
			
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

	
	public String getuserName() {
		return userName;
	}

	public void setuserName(String username) {
		this.userName = username;
	}

	public String getpassword() {
		return password;
	}

	public void setpassword(String password) {
		this.password = password;
	}
}