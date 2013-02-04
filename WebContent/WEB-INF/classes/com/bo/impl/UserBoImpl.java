package com.bo.impl;
 
import java.util.List;

import com.bo.UserBo;
import com.dao.UserDao;
import com.model.UserDetails;
 
public class UserBoImpl implements UserBo{
 
	UserDao userDao;
 
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
 
	public void addUser(UserDetails user) throws Exception{
		try{
			userDao.addUser(user);
		}catch (Exception e)
		{
			throw new Exception("Error in Bo: "+ e.getMessage());
		}
 
	}
 
	public List<UserDetails> findAllUser()throws Exception{
 
		return userDao.findAllUser();
	}

	public boolean checkUser(UserDetails user)throws Exception {
		
		return userDao.checkUser(user);
	}

}