package com.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.crypto.BCrypt;

public class RecoverAction extends LookupDispatchAction  {

	@Override
	protected Map getKeyMethodMap() {
		Map<String,String> map = new HashMap<String, String>();
		map.put("RecoverForm.Recover", "Recover");

		return map;
	}

	public ActionForward Recover(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RecoverForm cf = (RecoverForm) form;
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
        Criteria criteria = session.createCriteria(RecoverForm.class);
        criteria.add(Restrictions.eq("name", cf.getName()));
        criteria.add(Restrictions.eq("question", cf.getQuestion()));
		
        List<RecoverForm> users = (List<RecoverForm>)criteria.list();
        
        if(users.size() > 0) {
        	// update user with new encrypted pw
        	
    		String pass = cf.getPass();
    		String hashed = BCrypt.hashpw(pass, BCrypt.gensalt());
    		RecoverForm user = users.get(0);
    		user.setPass(hashed);
    		session.update(user);
    		
    		ServletContext context = request.getServletContext();
    		context.setAttribute("name2", cf.getName());
    		session.getTransaction().commit();
    		session.close();
        	return mapping.findForward("success");
        }
		
		session.getTransaction().commit();
		session.close();
		return mapping.findForward("failure");
	}
}