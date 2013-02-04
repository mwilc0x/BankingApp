package com.controller;

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
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.model.*;

import com.crypto.BCrypt;

public class TransactionAction extends LookupDispatchAction {
	private static HttpServletRequest request;
	private static HttpServletResponse response;

	protected Map getKeyMethodMap() {
		Map<String,String> map = new HashMap<String, String>();
		map.put("TransactionBean.GetTransaction", "GetTransaction");

		return map;
	}
	
	public ActionForward GetTransaction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TransactionBean tb = (TransactionBean) form;

		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.flush();
		session.beginTransaction();
		
		// we need to get the user cust_id from customers table
		Criteria userCriteria = session.createCriteria(CustomerForm.class);
		userCriteria.add(Restrictions.eq("name", tb.getName()));
		
		List<CustomerForm> customers = (List<CustomerForm>)userCriteria.list();
		
		int cust_id = customers.get(0).getPk_id();
		
		System.out.println("Trying to get user ====== " + tb.getName() + " and " + cust_id);
		
		Criteria criteria = session.createCriteria(TransactionBean.class);

		criteria.add(Restrictions.eq("fk_cust_id", cust_id));
		criteria.add(Restrictions.eq("acc_type", tb.getAcc_type()));
		criteria.addOrder(Order.desc("login_date"));

		List<TransactionBean> users = (List<TransactionBean>)criteria.list();
		
		request.setAttribute("balance", tb.getBalance());
		request.setAttribute("users", users);

		session.getTransaction().commit();
		session.close();
		
		if(tb.getAcc_type().equals("checking")) {
			return mapping.findForward("success_checking");
		}
		return mapping.findForward("success_savings");
	}

}
