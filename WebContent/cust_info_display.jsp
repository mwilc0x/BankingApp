<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="java.util.List"%>
<%@ page import="com.model.CustomerForm"%>
<%@ page import="com.helper.*"%>
<%
	List<CustomerForm> cf = (List<CustomerForm>) application
			.getAttribute("person");
%>

<html>
<head>
<title>Customer Info</title>
<link href='css/bootstrap.css' rel='stylesheet' type='text/css'>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>

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
				<li><a href="manage_accounts.jsp">Accounts</a></li>
				<li class='active'><a
					href="CustomerAction.do?method=DisplayInfoPage">My Info</a></li>
				<br>
				<li><a href="logout.jsp">Logout</a></li>
			</ul>
		</div>
		<div id="accounts_table_">
			<h1><%=cf.get(0).getName()%></h1>
			<br>
			<div id="left_display_info" style="width:28%; display:inline-block; float:left;">
				<div class="info"
					style="display: block;">
					<strong style="font-size: 20px;">Address</strong>
					<div class="well" style="margin-top: 10px;">
						<h4><%=cf.get(0).getAddress()%></h4>
						<h4><%=cf.get(0).getCity()%>,
							<%=cf.get(0).getState()%></h4>
					</div>
				</div>
				<div class="info"
					style="display: block;">
					<strong style="font-size: 20px;">Phone</strong>
					<div class="well" style="margin-top: 10px;">
						<h4><%=eightFigures.phoneProper(cf.get(0).getPhone())%></h4>
					</div>
				</div>
				
			<strong><a href="cust_info.jsp">Update Info</a></strong>
			</div>
			<div id="right_display_info" style="width:30%; display:inline-block; float:left; margin-left: 100px;">
				<div class="info"
					style="display: block;">
					<strong style="font-size: 20px;">Email</strong>
					<div class="well" style="margin-top: 10px;">
						<h4><%=cf.get(0).getEmail()%></h4>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="footer">
		<p class="footer-text">All Rights Reserved by eTeam Bank</p>
	</div>
</body>
</html>