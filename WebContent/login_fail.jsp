<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html>
<head>
<title>eTeam Bank</title>
<link href='css/bootstrap.css' rel='stylesheet' type='text/css'>
</head>
<body>
	<%@ include file="header_nav.jsp"%>
	<h1>Welcome to eTeam Bank</h1>
	<div id="login_box">
		<h1 style="position: fixed; left: 29%; top: 18%; font-size: 50px;">Welcome
			to eTeam Bank</h1>
		<!--  <form method="GET" action="LoginAction" class="well form-search"
			style="position: fixed; top: 40%; left: 40%;"> -->
		<html:form action="LoginAction"
			styleClass="well form-search login-form">
			<!--<bean:write name="LoginForm" property="message" />-->
			<html:text name="LoginForm" property="name"
				styleClass="input-xlarge"></html:text>
			<br>
			<br>
			<html:password name="LoginForm" property="pass"
				styleClass="input-xlarge"></html:password>
			<br>
			<br>
			<html:submit property="method" styleClass="btn">
				<bean:message key="LoginForm.check_login" />
			</html:submit>
		</html:form>

		<!--  </form>-->
		<!-- 			<input type="text" id="name" name="name" placeholder="login"
				class="input-large"><br> <br> <input id="pass"
				name="pass" type="password" placeholder="password"
				class="input-large"><br> <br> <input type="submit"
				value="Login" class="btn" /> <input type="hidden" name="form_type"
				value="login" /> -->


		<div class='alert alert-error'
			style='position: fixed; top: 65%; left: 39%;'>
			<strong>Error</strong> username or password incorrect.
		</div>
		<p style="position: fixed; top: 72%; left: 45%;">
			<a href="new_user.jsp">New User?</a>
		</p>
		<p style="position: fixed; top: 76.5%; left: 43.4%;">
			<a href="reset.jsp">Recover Password</a>
		</p>
	</div>
</body>
</html>