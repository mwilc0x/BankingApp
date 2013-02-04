<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%
	ServletContext context = request.getServletContext();
	if (context.getAttribute("name2") != null) {
		String name = context.getAttribute("name2").toString();
		context.setAttribute("name2", context.getAttribute("name2").toString());
	}
%>
<!DOCTYPE html>
<html>
<head>
<title>Manage Accounts</title>
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
						application.setAttribute("name2", context.getAttribute("name2").toString());

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
		<div id="accounts_table_">
			<table class="table table-bordered table-striped">
				<thead>
					<tr>
						<th>Accounts</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><html:form action="CheckAccountAction.do?method=Check">
								<bean:write name="CheckAccountForm" property="message" />
								<html:hidden name="CheckAccountForm" property="name"
									value='<%= context.getAttribute("name2").toString() %>'></html:hidden>
								<html:hidden name="CheckAccountForm" property="type" value="checking"></html:hidden>
								<html:submit styleClass="btn btn-primary" onclick="CheckAccountAction.do?method=Check" value="Checking">
									<bean:message key="CheckAccountForm.Check" />
								</html:submit>
							</html:form></td>
					</tr>
					<tr>
						<td><html:form action="CheckAccountAction.do?method=Check">
								<bean:write name="CheckAccountForm" property="message" />
								<html:hidden name="CheckAccountForm" property="name"
									value='<%= context.getAttribute("name2").toString()%>'></html:hidden>
								<html:hidden name="CheckAccountForm" property="type" value="savings"></html:hidden>
								<html:submit styleClass="btn btn-primary" onclick="CheckAccountAction.do?method=Check" value="Savings"></html:submit>
							</html:form></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div id="accounts_table_transfer">
		<table class="table table-bordered table-striped">
			<thead>
				<tr>
					<th>Transfer Funds</th>
				</tr>
			</thead>
			<tbody>
				<tr>
				</tr>
			</tbody>
		</table>
	</div>
	</div>
	<div id="footer">
		<p class="footer-text">All Rights Reserved by eTeam Bank</p>
	</div>
</body>
</html>