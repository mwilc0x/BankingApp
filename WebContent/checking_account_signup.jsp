<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%
	ServletContext context = request.getServletContext();
	if (context.getAttribute("name2") != null) {
		String name = context.getAttribute("name2").toString();
	}
%>

<html>
<head>
<title>Checking Account Signup</title>
<link href='css/bootstrap.css' rel='stylesheet' type='text/css'>
<script src="http://code.jquery.com/jquery-latest.js"></script>
</head>
<body>
	<%@ include file="header_nav.jsp"%>
	<div id="sub_page_layout">
		<br>
		<div class="well sidebar-dash" style="padding: 8px 0; width: 20%;">
			<ul class="nav nav-list">
				<li class="nav-header">
					<%
						out.println(application.getAttribute("name2"));
					%>'s Dashboard
				</li>

				<%
					if ((application.getAttribute("name2")) != null) {
						out.println("<li><a href='login_success.jsp'>Home</a></li>");
						//request.setAttribute("theName", request.getParameter("name"));

						//RequestDispatcher view = request.getRequestDispatcher("login_success.jsp");
						//view.forward(request, response);
					} else {
						out.println("<li><a href='Login.jsp'>Home</a></li>");
					}
				%>
				<li class='active'><a href="manage_accounts.jsp">Accounts</a></li>
				<li><a href="CustomerAction.do?method=DisplayInfoPage">My Info</a></li>
				<br>
				<li><a href="logout.jsp">Logout</a></li>
			</ul>
		</div>
		<div id="accounts_table">
			<html:form action="DepositAction" styleClass="well form-search deposit">
					<bean:write name="DepositForm" property="message" />
					<html:hidden name="DepositForm" property="name"
						value='<%=context.getAttribute("name2").toString()%>'></html:hidden>
					<html:text name="DepositForm" property="balance" styleId="deposit"></html:text>
					<br><br>
					<html:hidden name="DepositForm" property="type" value="checking"></html:hidden>
					<html:submit property="method" styleClass="btn btn-primary"
						value="Deposit">
						<bean:message key="DepositForm.Deposit" />
					</html:submit>
				</html:form>
		</div>
	</div>
	<div id="footer">
		<p class="footer-text">All Rights Reserved by eTeam Bank</p>
	</div>
</body>
</html>