package com.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import com.appresource.*;
import com.db.*;
import com.model.*;

public class AccountAction extends LookupDispatchAction {

	@Override
	protected Map getKeyMethodMap() {
		Map<String,String> map = new HashMap<String, String>();
		map.put("Account.getCheckingInfo", "getCheckingInfo");
		map.put("Account.depositChecking", "depositChecking");
		map.put("Account.getSavingsInfo", "getSavingsInfo");
		map.put("Account.depositSavings", "depositSavings");
		map.put("Account.withdrawalChecking", "withdrawalChecking");
		map.put("Account.withdrawalSavings", "withdrawalSavings");
		map.put("Account.updateChecking", "updateChecking");
		map.put("Account.updateSavings", "updateSavings");
		map.put("Account.transfer", "transfer");
		
		return map;
	}
	
	public ActionForward getCheckingInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//String name = session.getAttribute("theName").toString();
		Connection conn = DriverManager.getConnection(EnvManager.getDbUrl());
		
		//Account cm = new Account();
		
		ServletContext context = request.getServletContext();
		String name = context.getAttribute("name2").toString();

		if(DB_Model.checkIfUserHasChecking(conn, name)) {
			System.out.println("User has a checking account");
			
			String balance = DB_Model.getCheckingBalance(conn, name);
			
			request.setAttribute("balance", balance);
			
			return mapping.findForward("success");
		}
		else {
			System.out.println("User doesn't have a checking account");
			return mapping.findForward("failure");
		}
	}

	public ActionForward depositChecking(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(true);
		//System.out.println("Got sess info for " + session.getAttribute("theName"));
		//String name = (String) session.getAttribute("theName");
		String deposit_amt = request.getParameter("deposit");
		Connection conn = DriverManager.getConnection(EnvManager.getDbUrl());

		Account cm = new Account();
		
		ServletContext context = request.getServletContext();
		String name = context.getAttribute("name2").toString();

		// create bean object with new user data
		cm.setBalance(deposit_amt);
		cm.setName(name);
		
		System.out.println("depositing for " + name);
		
		DB_Model.depositChecking(conn, cm);
		
		request.setAttribute("user", cm);

		return mapping.findForward("manage_accounts.jsp");
	}

	public ActionForward depositSavings(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(true);
		System.out.println("Got sess info for " + session.getAttribute("theName"));
		String name = (String) session.getAttribute("theName");
		String deposit_amt = request.getParameter("deposit");
		Connection conn = DriverManager.getConnection(EnvManager.getDbUrl());

		Account sm = new Account();

		// create bean object with new user data
		sm.setBalance(deposit_amt);
		sm.setName(name);
		sm.setType("savings");
		
		DB_Model.depositSavings(conn, sm);
		
		request.setAttribute("user", sm);
		
		return mapping.findForward("manage_accounts.jsp");
	}
	
	public ActionForward withdrawalChecking(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(true);
		System.out.println("Got sess info for " + session.getAttribute("theName"));
		//String name = (String) session.getAttribute("theName");
		Connection conn = DriverManager.getConnection(EnvManager.getDbUrl());

		Account sm = new Account();
		
		ServletContext context = request.getServletContext();
		String name = context.getAttribute("name2").toString();

		// create bean object with new user data
		sm.setName(name);
		
		sm.setWithdrawal(request.getParameter("withdrawal").toString());
		
		DB_Model.withdrawalChecking(conn, sm);
		
		request.setAttribute("user", sm);
		
		return mapping.findForward("manage_accounts.jsp");
	}
	
	public ActionForward withdrawalSavings(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(true);
		System.out.println("Got sess info for " + session.getAttribute("theName"));
		//String name = (String) session.getAttribute("theName");
		Connection conn = DriverManager.getConnection(EnvManager.getDbUrl());

		Account sm = new Account();
		
		ServletContext context = request.getServletContext();
		String name = context.getAttribute("name2").toString();

		// create bean object with new user data
		sm.setName(name);
		
		sm.setWithdrawal(request.getParameter("withdrawal").toString());
		sm.setType("savings");
		
		DB_Model.withdrawalSavings(conn, sm);
		
		request.setAttribute("user", sm);
		
		return mapping.findForward("manage_accounts.jsp");
	}
	
	public ActionForward updateSavings(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(true);
		System.out.println("Got sess info for " + session.getAttribute("theName"));
		//String name = (String) session.getAttribute("theName");
		Connection conn = DriverManager.getConnection(EnvManager.getDbUrl());

		Account sm = new Account();
		
		ServletContext context = request.getServletContext();
		String name = context.getAttribute("name2").toString();

		// create bean object with new user data
		sm.setName(name);
		sm.setDeposit(request.getParameter("deposit").toString());
		sm.setType("savings");
		
		DB_Model.updateSavings(conn, sm);
		
		request.setAttribute("user", sm);
		
		return mapping.findForward("manage_accounts.jsp");
	}
	
	public ActionForward updateChecking(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(true);
		System.out.println("Got sess info for " + session.getAttribute("theName"));
		//String name = (String) session.getAttribute("theName");
		Connection conn = DriverManager.getConnection(EnvManager.getDbUrl());

		Account sm = new Account();
		ServletContext context = request.getServletContext();
		String name = context.getAttribute("name2").toString();
		
		// create bean object with new user data
		sm.setName(name);
		sm.setBalance(sm.getBalance() + request.getParameter("deposit").toString());
		
		sm.setDeposit(request.getParameter("deposit").toString());
		
		DB_Model.updateChecking(conn, sm);
		
		request.setAttribute("user", sm);
		
		return mapping.findForward("manage_accounts.jsp");
	}
	
	public ActionForward transfer(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(true);
		System.out.println("Got sess info for " + session.getAttribute("theName"));
		//String name = (String) session.getAttribute("theName");
		Connection conn = DriverManager.getConnection(EnvManager.getDbUrl());

		Account sm = new Account();
		ServletContext context = request.getServletContext();
		String name = context.getAttribute("name2").toString();
		
		// create bean object with new user data
		sm.setName(name);
		
		DB_Model.updateChecking(conn, sm);
		
		request.setAttribute("user", sm);
		
		return mapping.findForward("transfer.jsp");
	}
	
	public Account fillBean(Account ab) {
		
		return ab;
	}
}