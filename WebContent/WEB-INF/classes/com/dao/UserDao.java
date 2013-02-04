package com.dao;
 
import java.util.List;

import com.model.UserDetails;
 
public interface UserDao{
	
	public boolean checkUser(UserDetails user)throws Exception;
 
	void addUser(UserDetails user)throws Exception;
 
	List<UserDetails> findAllUser()throws Exception;
 
}