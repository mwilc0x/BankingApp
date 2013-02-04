<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

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
		$("#question").attr('placeholder', 'What city were you born in?');
		$("#pass").attr('placeholder', 'New Password');
	});
</script>
</head>
<body>
	<%@ include file="header_nav.jsp"%>
	<h1>Welcome to eTeam Bank</h1>
	<div id="reset_box">
		<h1 style="position: absolute; left: 5%; top: 20%; font-size: 50px;">Welcome
			to eTeam Bank</h1>
		<!--  <form method="GET" action="LoginAction" class="well form-search"
			style="position: fixed; top: 40%; left: 40%;"> -->
		<html:form action="RecoverAction"
			styleClass="well form-search login-form">
			<bean:write name="RecoverForm" property="message" />
			<html:text name="RecoverForm" property="name"
				styleClass="input-xlarge" styleId="login"></html:text>
			<br>
			<br>
			<html:text name="RecoverForm" property="question"
				styleClass="input-xlarge" styleId="question"></html:text>
			<br>
			<br>
			<html:password name="RecoverForm" property="pass"
				styleClass="input-xlarge" styleId="pass"></html:password>
			<br>
			<br>
			<html:submit property="method" styleClass="btn" value="Recover">
				<bean:message key="RecoverForm.Recover" />
			</html:submit>
		</html:form>
	</div>
	<div class='alert alert-error'
		style='position: fixed; top: 69%; left: 37.9%;'>
		<strong>Error</strong> username or question incorrect.
	</div>
</body>
</html>