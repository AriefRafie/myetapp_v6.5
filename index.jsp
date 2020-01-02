<%
String randomNo = lebah.db.UniqueID.getUID();
boolean x = ekptg.helpers.Utils.isFromOutside(request.getServerName());
if (x) {	
	if(request.getHeader("User-Agent").indexOf("Mobile") != -1) {
	    response.sendRedirect("checkBrowser.jsp?rndId=" + randomNo); 
	  } else {
		  response.sendRedirect("online/?rndId=" + randomNo); 
	  }		
	
} else {
	if(request.getHeader("User-Agent").indexOf("Mobile") != -1) {
	    response.sendRedirect("checkBrowser.jsp?rndId=" + randomNo); 
	  } else {
		response.sendRedirect("login.jsp?rndId=" + randomNo); 
	  }	
}
%>
