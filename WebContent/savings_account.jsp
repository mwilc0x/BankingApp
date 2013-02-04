<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="com.helper.*"%>

<%
	ServletContext context = request.getServletContext();
	if (context.getAttribute("name2") != null) {
		String name = context.getAttribute("name2").toString();
	}
%>

<html>
<head>
<title>Savings Account</title>
<link href='css/bootstrap.css' rel='stylesheet' type='text/css'>
<script src="http://code.jquery.com/jquery-latest.js"></script>

<script>
				$(document).ready(function() {
					$('input[type=text]').val("");

					$("#deposit").attr('placeholder', 'Deposit Amount');
					$("#withdrawal").attr('placeholder', 'Withdrawal Amount');
				});
			</script>
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
		<div id="accounts_table_">
			<table class="table table-bordered table-striped">
				<thead>
					<tr>
						<th>Balance</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<h2>
								<%
									String balance = request.getAttribute("balance").toString();
									out.println("$ " + eightFigures.moneyProper(balance));
								%>
							</h2>
						</td>
					</tr>
				</tbody>
			</table>
			<br> <br>
			<table class="table table-bordered table-striped"
				style="width: 50%; display: inline-block;">
				<thead>
					<tr>
						<th>Deposit</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><html:form action="DepositAction">
								<bean:write name="DepositForm" property="message" />
								<html:hidden name="DepositForm" property="name"
									value='<%=context.getAttribute("name2").toString()%>'></html:hidden>
								<html:text name="DepositForm" property="balance"
									styleId="deposit"></html:text>
								<html:hidden name="DepositForm" property="type" value="savings"></html:hidden>
								<html:submit property="method" styleClass="btn btn-primary"
									value="Deposit">
									<bean:message key="DepositForm.Deposit" />
								</html:submit>
							</html:form></td>
					</tr>
				</tbody>
			</table>

			<table class="table table-bordered table-striped"
				style="width: 48%; float: right; display: inline-block;">
				<thead>
					<tr>
						<th>Withdrawal</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><html:form action="WithdrawalAction">
								<bean:write name="WithdrawalForm" property="message" />
								<html:hidden name="WithdrawalForm" property="name"
									value='<%=context.getAttribute("name2").toString()%>'></html:hidden>
								<html:text name="WithdrawalForm" property="balance"
									styleId="withdrawal"></html:text>
								<html:hidden name="WithdrawalForm" property="type"
									value="savings"></html:hidden>
								<html:submit property="method" styleClass="btn btn-primary"
									value="Withdrawal">
									<bean:message key="WithdrawalForm.Withdrawal" />
								</html:submit>
							</html:form></td>
					</tr>
				</tbody>
			</table>
			<br>
			<br>
			<div>
				<html:form action="TransactionAction.do?method=GetTransaction">
					<bean:write name="TransactionBean" property="message" />
					<html:hidden name="TransactionBean" property="name"
						value='<%=context.getAttribute("name2").toString()%>'></html:hidden>
					<html:hidden name="TransactionBean" property="balance"
						value='<%=request.getAttribute("balance").toString()%>'></html:hidden>
					<html:hidden name="TransactionBean" property="acc_type"
						value="savings"></html:hidden>
					<html:submit styleClass="btn btn-primary"
						onclick="TransactionAction.do?method=GetTransaction"
						value="Transactions">
						<bean:message key="TransactionBean.GetTransaction" />
					</html:submit>
				</html:form>
			</div>
		</div>
	</div>
</body>
</html>