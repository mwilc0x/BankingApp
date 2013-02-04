
<div class="well sidebar-dash" style="padding: 8px 0; width: 20%;">
	<ul class="nav nav-list">
		<li class="nav-header">
			<%
						out.println(application.getAttribute("name2"));
					%>'s Dashboard
		</li>

		<% if((application.getAttribute("name2")) != null) {
							out.println("<li class='active'><a href='login_success.jsp'>Home</a></li>");
							//request.setAttribute("theName", request.getParameter("name"));
							
							
							//RequestDispatcher view = request.getRequestDispatcher("login_success.jsp");
							//view.forward(request, response);
					}
					else {
						out.println("<li class='active'><a href='Login.jsp'>Home</a></li>");
					}
				%>
		<li><a href="manage_accounts.jsp">Accounts</a></li>
		<li><a href="CustomerAction.do?method=DisplayInfoPage">My Info</a></li>
		<br>
		<li><a href="logout.jsp">Logout</a></li>
	</ul>
</div>