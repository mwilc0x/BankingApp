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
		
		$("#name").attr('placeholder', 'Name');
		$("#pass").attr('placeholder', 'Password');
		$("#addr").attr('placeholder', 'Address');
		$("#city").attr('placeholder', 'City');
		$("#state").attr('placeholder', 'State');
		$("#pin").attr('placeholder', 'Pin');
		$("#phone").attr('placeholder', 'Phone');
		$("#email").attr('placeholder', 'Email');
		$("#question").attr('placeholder', 'What city were you born in?');
	});
</script>
</head>
<body>
	<%@ include file="header_nav.jsp"%>
	<h1>Welcome to eTeam Bank</h1>
	<div id="login_box">
		<h3 style="position: fixed; top: 20%; left: 32%;">New here?
			Register a new account below:</h3>

		<html:form action="CustomerAction"
			styleClass="well form-search signup-form">
			<bean:write name="CustomerForm" property="message" />
			<div id="signup_left">
				<html:text name="CustomerForm" property="name"
					styleClass="input-xlarge" styleId="name"></html:text>
				<br> <br>
				<html:password name="CustomerForm" property="pass"
					styleClass="input-xlarge" styleId="pass"></html:password>
				<br> <br>
				<html:text name="CustomerForm" property="address"
					styleClass="input-xlarge" styleId="addr"></html:text>
				<br> <br>
				<html:text name="CustomerForm" property="city"
					styleClass="input-xlarge" styleId="city"></html:text>
				<br> <br>
				<html:text name="CustomerForm" property="state"
					styleClass="input-xlarge" styleId="state"></html:text>
				<br> <br>
				<html:submit property="method" styleClass="btn" value="Signup">
					<bean:message key="CustomerForm.new_user" />
				</html:submit>
			</div>
			<div id="signup_right">
				<html:text name="CustomerForm" property="pin"
					styleClass="input-xlarge" styleId="pin"></html:text>
				<br> <br>
				<html:text name="CustomerForm" property="phone"
					styleClass="input-xlarge" styleId="phone"></html:text>
				<br> <br>
				<html:text name="CustomerForm" property="email"
					styleClass="input-xlarge" styleId="email"></html:text>
				<br> <br>
				<html:text name="CustomerForm" property="question"
					styleClass="input-xlarge" styleId="question"></html:text>
				<br> <br>
			</div>

		</html:form>
				<div class='alert alert-error'
			style='position: fixed; top: 80%; left: 37.5%;'>
			<strong>Error</strong> Sorry, that user already exists.
		</div>
	</div>
</body>
</html>