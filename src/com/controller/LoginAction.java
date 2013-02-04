package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.model.*;

import com.crypto.BCrypt;

public class LoginAction extends LookupDispatchAction {
	private static HttpServletRequest request;
	private static HttpServletResponse response;

	protected Map getKeyMethodMap() {
		Map<String,String> map = new HashMap<String, String>();
		map.put("LoginForm.check_login", "Login");
		map.put("LoginForm.new_user", "Signup");

		return map;
	}

	public ActionForward Login(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginForm lf = (LoginForm) form;

		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.flush();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(LoginForm.class);

		criteria.add(Restrictions.eq("name", lf.getName()));

		List<LoginForm> users = (List<LoginForm>)criteria.list();

		for(LoginForm user:users) {
			if (BCrypt.checkpw(lf.getPass(), user.getPass())) {
				System.out.println("It matches");
				session.getTransaction().commit();
				session.close();

				ServletContext context = request.getServletContext();
				context.setAttribute("name2", lf.getName());

				return mapping.findForward("success");
			}
		}

		session.getTransaction().commit();
		session.close();
		return mapping.findForward("failure");
	}
}