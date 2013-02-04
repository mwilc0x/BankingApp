package com.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.model.*;

public class DepositAction extends LookupDispatchAction {

	@Override
	protected Map getKeyMethodMap() {
		Map<String,String> map = new HashMap<String, String>();
		map.put("DepositForm.Deposit", "Deposit");
		map.put("DepositForm.Check", "Check");
		
		return map;
	}
	
	public ActionForward Deposit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		DepositForm df = (DepositForm) form;
		
		Random rand = new Random();
		df.setPk_id(rand.nextInt(100000));
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.flush();
		session.beginTransaction();
		
		Criteria getUserPK = session.createCriteria(CustomerForm.class);
		getUserPK.add(Restrictions.eq("name", df.getName()));
		
		List<CustomerForm> userList = (List<CustomerForm>)getUserPK.list();
		
		// first check if the user has an account
        Criteria criteria = session.createCriteria(DepositForm.class);
        
        criteria.add(Restrictions.eq("cust_id", userList.get(0).getPk_id()));
        criteria.add(Restrictions.eq("type", df.getType()));
        
        List<DepositForm> users = (List<DepositForm>)criteria.list();
        
    	// update the transactions table
    	Criteria userID = session.createCriteria(CustomerForm.class);
    	userID.add(Restrictions.eq("name", df.getName()));
    	List<CustomerForm> customers = (List<CustomerForm>)userID.list();
    	int pk_id = customers.get(0).getPk_id();
    	// set account cust_id to track customers (foreign key)
    	df.setCust_id(pk_id);
    	java.util.Date dt = new java.util.Date();

    	java.text.SimpleDateFormat sdf = 
    	     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    	String currentTime = sdf.format(dt);
    	
        // add the transaction to transactions table
        TransactionBean trans = new TransactionBean();
        trans.setPk_id(rand.nextInt(100000));
        trans.setAcc_type(df.getType());
        trans.setAmount(df.getBalance());
        trans.setFk_cust_id(df.getCust_id());
        trans.setLogin_date(currentTime);
        trans.setTrans_type("deposit");
        
        if(users.size() > 0) {
        	// has account, update
            DepositForm user = users.get(0);
            System.out.println("User "+user.getName()+" currently has a balance of " + user.getBalance());
            
            double newBalance = Double.parseDouble(user.getBalance()) + Double.parseDouble(df.getBalance());
            String bal = String.valueOf(newBalance);
            
        	user.setBalance(bal);
        	session.update(user);

        	//grab the new balance
            request.setAttribute("balance", user.getBalance());	
   
            if(df.getType().equals("checking")) {
            	session.save(trans);
        		session.getTransaction().commit();
        		session.close();
            	return mapping.findForward("success_checking");
            }
            else if(df.getType().equals("savings")) {
            	session.save(trans);
        		session.getTransaction().commit();
        		session.close();
            	return mapping.findForward("success_savings");
            }
        }
        else {
        	// no account, just deposit
        	double depositAmount = Double.parseDouble(df.getBalance());
        	if(depositAmount < 200) {
                if(df.getType().equals("checking")) {
            		session.getTransaction().commit();
            		session.close();
                	return mapping.findForward("failure_checking");
                }
                else if(df.getType().equals("savings")) {	
            		session.getTransaction().commit();
            		session.close();
                	return mapping.findForward("failure_savings");
                }
        	}
        	
        	session.save(df);
        	
        	// balance is the current amount that user just deposited
        	request.setAttribute("balance", df.getBalance());
        	
            if(df.getType().equals("checking")) {
            	session.save(trans);
        		session.getTransaction().commit();
        		session.close();
            	return mapping.findForward("success_checking");
            }
            else if(df.getType().equals("savings")) {
            	session.save(trans);
        		session.getTransaction().commit();
        		session.close();
            	return mapping.findForward("success_savings");
            }
        }
		session.getTransaction().commit();
		session.close();
		return mapping.findForward("success_checking");
	}
	
	public ActionForward Check(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		DepositForm cf = (DepositForm) form;
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.flush();
		session.beginTransaction();
		
		// first check if the user has an account
        Criteria criteria = session.createCriteria(DepositForm.class);
        criteria.add(Restrictions.eq("name", cf.getName()));
        criteria.add(Restrictions.eq("type", cf.getType()));
        
        List<DepositForm> users = (List<DepositForm>)criteria.list();
        System.out.println("Check list size is " + users.size() + " and type is " + cf.getType() + " with a name of " + cf.getName());
        
        
        if(users.size() > 0) {
        	// has an account, forward to appropriate account type page
        	System.out.println("HAS AN ACCOUNT OF TYPE " + cf.getType());
        	if(cf.getType().equals("checking")) {
        		session.getTransaction().commit();
        		session.close();
        		request.setAttribute("balance", users.get(0).getBalance());	
        		return mapping.findForward("checking");
        	}
        	else if(cf.getType().equals("savings")) {
        		session.getTransaction().commit();
        		session.close();
        		request.setAttribute("balance", users.get(0).getBalance());
        		return mapping.findForward("savings");
        	}
        }
        else {
        	if(cf.getType().equals("checking")) {
        		session.getTransaction().commit();
        		session.close();
        		return mapping.findForward("failure_checking");
        	}
        }
		session.getTransaction().commit();
		session.close();
        return mapping.findForward("failure_savings");
	}
}