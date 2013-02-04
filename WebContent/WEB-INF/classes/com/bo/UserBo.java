package com.bo;
 
import java.util.List;

import com.model.UserDetails;


 
public interface UserBo{
	
	public boolean checkUser(UserDetails user)throws Exception;
 
	void addUser(UserDetails user)throws Exception;
 
	List<UserDetails> findAllUser()throws Exception;
 
}