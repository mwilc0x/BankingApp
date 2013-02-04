package com.controller;

import com.crypto.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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

public class CustomerAction extends LookupDispatchAction {

	@Override
	protected Map getKeyMethodMap() {
		Map<String,String> map = new HashMap<String, String>();
		map.put("CustomerForm.new_user", "Signup");
		map.put("CustomerForm.getInfo", "DisplayInfo");
		map.put("CustomerForm.update_user", "UpdateUser");
		map.put("CustomerForm.displayInfo", "DisplayInfoPage");
		
		return map;
	}
	

	public ActionForward Signup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CustomerForm cf = (CustomerForm) form;	
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		//first check if the user is in the database already, if he/she is then return mapping forward for fail
		
        Criteria criteria = session.createCriteria(LoginForm.class);
        criteria.add(Restrictions.eq("name", cf.getName()));
        
        List<LoginForm> users = (List<LoginForm>)criteria.list();
        
        if(users.size() > 0) {
        	return mapping.findForward("failure");
        }
        
		
		// Insert the user into the database

		// create a random id for the new user
		Random rand = new Random();
		cf.setPk_id(rand.nextInt(1000000));
		
		// do crypto on new password
		String pass = cf.getPass();
		String hashed = BCrypt.hashpw(pass, BCrypt.gensalt());
		cf.setPass(hashed);

        session.save(cf);
		session.getTransaction().commit();
		session.close();
		ServletContext context = request.getServletContext();
		context.setAttribute("name2", cf.getName());

		return mapping.findForward("success");
	}
	
	public ActionForward DisplayInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CustomerForm cf = (CustomerForm) form;	
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		ServletContext context = request.getServletContext();
		String name = context.getAttribute("name2").toString();
		
        Criteria criteria = session.createCriteria(CustomerForm.class);
        criteria.add(Restrictions.eq("name", name));
        HttpSession sess = request.getSession(true);
        
        List<CustomerForm> users = (List<CustomerForm>)criteria.list();
        
        context.setAttribute("person", users);
        
		session.getTransaction().commit();
		session.close();
		context = request.getServletContext();
		context.setAttribute("name2", name);

		return mapping.findForward("show_user_form");
	}
	
	public ActionForward DisplayInfoPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CustomerForm cf = (CustomerForm) form;	
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		ServletContext context = request.getServletContext();
		String name = context.getAttribute("name2").toString();
		
        Criteria criteria = session.createCriteria(CustomerForm.class);
        criteria.add(Restrictions.eq("name", name));
        HttpSession sess = request.getSession(true);
        
        List<CustomerForm> users = (List<CustomerForm>)criteria.list();
        
        context.setAttribute("person", users);
        
		session.getTransaction().commit();
		session.close();
		context = request.getServletContext();
		context.setAttribute("name2", name);

		return mapping.findForward("display_user_info_page");
	}
	
	
	public ActionForward UpdateUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CustomerForm cf = (CustomerForm) form;	
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		ServletContext context = request.getServletContext();
		String name = context.getAttribute("name2").toString();
		
        Criteria criteria = session.createCriteria(CustomerForm.class);
        criteria.add(Restrictions.eq("name", name));
        HttpSession sess = request.getSession(true);
        
        List<CustomerForm> users = (List<CustomerForm>)criteria.list();
        
        CustomerForm cfUser = users.get(0);
        cfUser.setName(cf.getName());
        cfUser.setAddress(cf.getAddress());
        cfUser.setCity(cf.getCity());
        cfUser.setState(cf.getState());
        cfUser.setPin(cf.getPin());
        cfUser.setPhone(cf.getPhone());
        cfUser.setEmail(cf.getEmail());
        cfUser.setQuestion(cf.getQuestion());
        
		//String pass = cf.getPass();
		//String hashed = BCrypt.hashpw(pass, BCrypt.gensalt());
		//cfUser.setPass(hashed);
        
		session.flush();
        session.update(cfUser);
        
		session.getTransaction().commit();
		session.close();
		context = request.getServletContext();
		context.setAttribute("name2", cf.getName());

		return mapping.findForward("updated_user");
	}
}