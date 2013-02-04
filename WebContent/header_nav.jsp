
<div class="navbar navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<a class="btn btn-navbar" data-toggle="collapse"
				data-target=".nav-collapse"> <span class="icon-bar"></span> <span
				class="icon-bar"></span> <span class="icon-bar"></span>
				
				<% if((application.getAttribute("name2")) != null) {
							out.println("</a> <a class='brand' href='login_success.jsp'>eTeam Bank</a>");
							//request.setAttribute("theName", request.getParameter("name"));
							
							
							//RequestDispatcher view = request.getRequestDispatcher("login_success.jsp");
							//view.forward(request, response);
					}
					else {
						out.println("</a> <a class='brand' href='Login.jsp'>eTeam Bank</a>");
					}
				%>
			<div class="nav-collapse" id="main-menu">
				<ul class="nav" id="main-menu-left">
				</ul>
				<ul class="nav pull-right" id="main-menu-right">
				</ul>
			</div>
		</div>
	</div>
</div>