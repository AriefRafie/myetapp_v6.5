<%
String randomNo = lebah.db.UniqueID.getUID();
boolean x = ekptg.helpers.Utils.isFromOutside(request.getServerName());
//response.sendRedirect("online/?rndId=" + randomNo); 
out.println(x);
/*
if (x) {
	response.sendRedirect("online/?rndId=" + randomNo); 
} else {
	response.sendRedirect("login.jsp"); 
}
*/
%>
