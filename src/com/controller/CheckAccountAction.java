package com.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

public class CheckAccountAction extends LookupDispatchAction {

	@Override
	protected Map getKeyMethodMap() {
		Map<String,String> map = new HashMap<String, String>();
		map.put("CheckAccountForm.Check", "Check");

		return map;
	}

	public ActionForward Check(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CheckAccountForm cf = (CheckAccountForm) form;
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.flush();
		session.beginTransaction();
		
		Criteria getUserPK = session.createCriteria(CustomerForm.class);
		getUserPK.add(Restrictions.eq("name", cf.getName()));
		
		List<CustomerForm> userList = (List<CustomerForm>)getUserPK.list();
		
		
		// first check if the user has an account
        Criteria criteria = session.createCriteria(CheckAccountForm.class);
        criteria.add(Restrictions.eq("cust_id", userList.get(0).getPk_id()));
        criteria.add(Restrictions.eq("type", cf.getType()));
        
        List<CheckAccountForm> users = (List<CheckAccountForm>)criteria.list();
        System.out.println("Check list size is " + users.size() + " and type is " + cf.getType() + " with a name of " + cf.getName());
        
        
        if(users.size() > 0) {
        	// has an account, forward to appropriate account type page
        	System.out.println("HAS AN ACCOUNT OF TYPE " + cf.getType() + " with a balance of " + cf.getBalance());
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