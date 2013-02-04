<html>
<head>
<title>Transfer Funds</title>
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
				<li><a href="cust_info.jsp">Update Info</a></li>
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
						<td>
							<form method="GET" action="LoginServlet">
								<input type="submit" value="Checking" class="btn btn-primary" /><input
									type="hidden" name="form_type" value="checking" />
							</form>
						</td>
					</tr>
					<tr>
						<td>
							<form method="GET" action="LoginServlet">
								<input type="submit" value="Savings" class="btn btn-primary" /><input
									type="hidden" name="form_type" value="savings" />
							</form>
						</td>
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
					<td>
						<form method="GET" action="LoginServlet">
							<input type="submit" value="Transfer" class="btn btn-primary" /><input
								type="hidden" name="form_type" value="transfer" />
						</form>
					</td>
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