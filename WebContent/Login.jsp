<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%
	ServletContext context = request.getServletContext();
	if (context.getAttribute("name2") != null) {
%>
<jsp:forward page="login_success.jsp"></jsp:forward>
<%
	}
%>
<html>
<head>
<title>eTeam Bank</title>
<link href='css/bootstrap.css' rel='stylesheet' type='text/css'>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>

<script>
	$(document).ready(function() {
		$('input[type=text]').val("");
		$('input[type=password]').val("");
		
		$("#login").attr('placeholder', 'Username');
		$("#pass").attr('placeholder', 'Password');
	});
</script>
</head>
<body>
	<%@ include file="header_nav.jsp"%>
	<div id="login_box">
		<h1 style="position: absolute; left: 5%; top: 20%; font-size: 50px;">Welcome
			to eTeam Bank</h1>
		<html:form action="LoginAction"
			styleClass="well form-search login-form">
			<bean:write name="LoginForm" property="message" />
			<html:text name="LoginForm" property="name"
				styleClass="input-xlarge" styleId="login"></html:text>
				<br><br>
			<html:password name="LoginForm" property="pass"
				styleClass="input-xlarge" styleId="pass"></html:password>
				<br><br>
			<html:submit property="method" styleClass="btn" value="Login">
				<bean:message key="LoginForm.check_login" />
			</html:submit>
		</html:form>

		<p style="position: absolute; top: 180%; left: 20%;">
			<a href="new_user.jsp">Register</a>
		</p>

		<p style="position: absolute; top: 200%; left: 18%;">
			<a href="reset.jsp">Recover Password</a>
		</p>
	</div>
</body>
</html>