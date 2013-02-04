
<%
	String name = request.getParameter("name");
	HttpSession sess = request.getSession();
	
	sess.setAttribute("theName", name);
	System.out.println(session.getAttribute("theName"));
%>

<html>
<head>
<title>eTeam Bank</title>
<link href='css/bootstrap.css' rel='stylesheet' type='text/css'>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>

<script type="text/javascript" 
   src="http://maps.googleapis.com/maps/api/js?sensor=false"></script>
   
<script type="text/javascript">
  function loadMap() {
    var latlng = new google.maps.LatLng(40.5532, -74.411259);
    var myOptions = {
      zoom: 9,
      center: latlng,
      mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    var map = new google.maps.Map(document.getElementById("map_container"),myOptions);
 
    var marker = new google.maps.Marker({
      position: latlng, 
      map: map, 
      title:"eTeam Inc. Headquarters"
    }); 
 
  }
</script>

</head>
<body onload="loadMap()">
	<%@ include file="header_nav.jsp"%>
	<h1>Welcome to eTeam Bank</h1>
	<div id="main">
		<h5>
			Welcome back to eTeam Bank,
			<%
			out.println(application.getAttribute("name2"));
		%>
		</h5>
		<br>
		<%@ include file="sidebar.jsp"%>
		<div id="login_text_block">
			<h1>At eTeam Bank, we value your business with us.</h1>
			<br>
			<p>That's why we are constantly striving to provide the best
				possible web experience for our clients.</p>
				<br><br>
				<div id="map_container" style="width: 60%; height: 350px;"></div>
				
		</div>
	</div>
	<div id="footer">
		<p class="footer-text">All Rights Reserved by eTeam Bank</p>
	</div>
</body>
</html>