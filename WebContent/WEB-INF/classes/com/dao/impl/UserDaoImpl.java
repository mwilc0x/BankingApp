package com.dao.impl;
 
import java.util.Date;
import java.util.List;
 
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dao.UserDao;
import com.model.UserDetails;
 
public class UserDaoImpl extends 
       HibernateDaoSupport implements UserDao{
 
	public void addUser(UserDetails user)throws Exception
	{
		try{
 
		user.setCreatedDate(new Date());
		getHibernateTemplate().save(user);
		}catch(Exception e)
		{
			throw new Exception("Error in DAO: " + e.getMessage());
		}
 
	}
 
	public List<UserDetails> findAllUser(){
 
		return getHibernateTemplate().find("from UserDetails");
 
	}

	@Override
	public boolean checkUser(UserDetails user) throws Exception{
		boolean flag = false;
		List l = null;
		
		try{
			 
		String sql = "from UserDetails u where u.userName =? and u.password=?";
		String[] params = {user.getuserName(),user.getPassword()};
		
		
		System.out.println("Username inside DAO: "+user.getuserName());
		System.out.println("Password inside DAO: "+user.getPassword() );
	
		l = getHibernateTemplate().find(sql,params);
		
		if(l!= null)
		{
			System.out.println("Size of the list inside CheckUser: "+l.size());
			if(l.size() == 1)
				flag=true;
		}
		}catch(Exception e)
		{
			throw new Exception("Error in DAO: " + e.getMessage());
		}
 
		
		return flag;
	}
}

