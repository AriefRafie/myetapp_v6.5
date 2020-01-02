Error Occured.
<%
if(session!=null) {
	out.print(session.getAttribute("error_msg"));
	out.print("<br>");
	out.print("<a href='"+session.getAttribute("AppsPath")+"'>Kembali</a>");
} else {
	out.print("HAHA");
}

%>