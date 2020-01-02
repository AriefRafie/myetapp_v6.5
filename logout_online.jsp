<%

String visitor = session.getAttribute("_portal_visitor") != null ? (String) session.getAttribute("_portal_visitor") : "anon";
session.invalidate();
String randomNo = lebah.db.UniqueID.getUID();
response.sendRedirect("online/?logoutrndId=" + randomNo); 
//response.sendRedirect("http://www.kptg.gov.my"); 
%>
