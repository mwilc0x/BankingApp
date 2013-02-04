<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="java.util.List"%>
<%@ page import="com.model.CustomerForm"%>


<%
	List<CustomerForm> cf = (List<CustomerForm>) application.getAttribute("person");
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
				<li><a href="CustomerAction.do?method=DisplayInfoPage">My Info</a></li>
				<br>
				<li><a href="logout.jsp">Logout</a></li>
			</ul>
		</div>
	<!-- <form method="GET" action="CustomerServlet" id="cust_form">
			<fieldset style="float: left; display: inline-block;">
				<legend>Customer Info</legend>
				<span>Name:</span> <br> <input type="text" id="name"
					name="name"><br> <span>Address:</span><br> <input
					type="text" id="addr" name="address"><br> <span>City:</span>
				<br> <input type="text" id="city" name="city"><br>
				<span>State:</span><br> <input type="text" id="state"
					name="state">
			</fieldset>
			<fieldset
				style="float: right; display: inline-block; padding-top: 63px;">
				<span>Pin:</span><br> <input type="text" id="pin" name="pin"><br>
				<span>Phone:</span><br> <input type="text" id="phone"
					name="phone"><br> <span>Email:</span><br> <input
					type="text" id="email" name="email"><br> <span>Password:</span><br>
				<input type="password" id="password" name="pass"> <br>
				<span>Question:</span><br> <input type="text" id="question"
					name="question"> <span><input type="submit"
					value="Update" /></span>
			</fieldset>
		</form> -->


		<html:form action="CustomerAction"
			styleId="cust_form">
			<bean:write name="CustomerForm" property="message" />
			<fieldset style="float: left; display: inline-block;">
			<legend>Customer Info</legend>
				<span>Name:</span><br>
			<html:text name="CustomerForm" property="name" styleClass="input-xlarge"
				styleId="name" value="<%= cf.get(0).getName() %>"></html:text><br>
		
			<span>Address:</span><br>
			<html:text name="CustomerForm" property="address" styleClass="input-xlarge"
				styleId="addr" value="<%= cf.get(0).getAddress() %>"></html:text><br>

			
			<span>City:</span><br>
			<html:text name="CustomerForm" property="city" styleClass="input-xlarge"
				styleId="city" value="<%= cf.get(0).getCity() %>"></html:text><br>
				
			<span>State:</span><br>
			<html:text name="CustomerForm" property="state" styleClass="input-xlarge"
				styleId="state" value="<%= cf.get(0).getState() %>"></html:text><br>
				</fieldset>
				<fieldset style="float: right; display: inline-block; padding-top: 63px;">
				
			<span>Pin:</span><br>
			<html:text name="CustomerForm" property="pin" styleClass="input-xlarge"
				styleId="pin" value="<%= cf.get(0).getPin() %>"></html:text><br>
				
			<span>Phone:</span><br>
			<html:text name="CustomerForm" property="phone" styleClass="input-xlarge"
				styleId="phone" value="<%= cf.get(0).getPhone() %>"></html:text><br>
				
			<span>Email:</span><br>
			<html:text name="CustomerForm" property="email" styleClass="input-xlarge"
				styleId="email" value="<%= cf.get(0).getEmail() %>"></html:text><br>
				
			<span>Question:</span><br>
			<html:password name="CustomerForm" property="question" styleClass="input-xlarge"
				styleId="question" value="<%= cf.get(0).getQuestion() %>"></html:password><br>
				</fieldset>
			<span><html:submit property="method" styleClass="btn" value="UpdateUser">
				<bean:message key="CustomerForm.update_user" />
			</html:submit></span>
		</html:form>


	</div>
	<div id="footer">
		<p class="footer-text">All Rights Reserved by eTeam Bank</p>
	</div>
</body>
</html>