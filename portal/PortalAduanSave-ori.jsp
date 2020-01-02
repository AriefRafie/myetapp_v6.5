<%@ page import="lebah.template.DbPersistence" %>
<%@ page import="etapp.data.*" %>
<%@ page import="com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException" %>
<%@ page import="org.apache.velocity.Template" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@ page import="org.apache.commons.fileupload.FileItem" %>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory" %>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload" %>

<!DOCTYPE html>
<html>
<head>
<title>My Aduan</title>
<script>var $jquery = jQuery.noConflict();</script>
<link rel="stylesheet" type="text/css" href="../css/online.css" />

<link rel="shortcut icon" href="../favicon.ico" />
</head>

<body onmousemove="reset_interval()" onclick="reset_interval()" onkeypress="reset_interval()" onscroll="reset_interval()">

<div class="apps-container">
<div class="apps-body">
<table class="container" cellpadding="0" cellspacing="0" border="0">
<tr><td>
<div class="welcome">
<b>Online Aduan myeTaPP</b>
</div>

<div class="page_header_1">
<div class="page_header_2">
<div class="page_header_3">
<div class="page_header_4">
<div class="page_header_5">
<div class="page_header_6">
<div class="page_header_7">
<div class="page_header_8">
<div class="page_header_9">
</div>
</div>
</div>
</div>
</div>
</div>
</div>
</div>
</div>

</td></tr>
<tr><td>
<table class="tab" width="100%"  cellspacing="0" cellpadding="0" border="0">
	<tr>
		<td valign="bottom">&nbsp;
		</td>
	</tr>
</table>
</td></tr>
<tr><td>
<form id="f" name="f" method="post">

<%! DbPersistence db = new DbPersistence(); %>

<%
Date now = new Date();
long idPengadu = 12345l;
String namaPengadu = request.getParameter("name");
String emailPengadu = request.getParameter("email");
String phonePengadu = request.getParameter("phone");
String status = "DALAM PROSES";

Long idJenisAduan = Long.parseLong(request.getParameter("idJenisAduan"));
RujJenisAduanMobile jenisAduan = db.find(RujJenisAduanMobile.class, idJenisAduan);
String catatan = request.getParameter("catatan");

Long idSumber = Long.parseLong("161231");
RujSumberAduanMobile sumberAduan = db.find(RujSumberAduanMobile.class, idSumber);

try {
	//db = new DbPersistence();
	db.begin();
	OnlineEAduanMobile aduan = new OnlineEAduanMobile();
	aduan.setIdPengadu(idPengadu);
	aduan.setNamaPengadu(namaPengadu);
	aduan.setEmailPengadu(emailPengadu);
	aduan.setPhonePengadu(phonePengadu);
	aduan.setJenisAduan(jenisAduan);
	aduan.setCatatan(catatan.toUpperCase());
	aduan.setStatus(status.toUpperCase());
	aduan.setSumberAduan(sumberAduan);
	aduan.setTarikhMasuk(now);
	db.persist(aduan);
	db.commit();
	
    DiskFileItemFactory factory = new DiskFileItemFactory();
    ServletFileUpload upload = new ServletFileUpload(factory);
    boolean isMultipart = ServletFileUpload.isMultipartContent(request);
    if(!isMultipart) {
%>
BUKAN MULTIPART
<%
    } else {
%>
MULTIPART
<%			    	
    }
    List items = upload.parseRequest(request);
    Iterator itr = items.iterator();
    while (itr.hasNext()) {
		FileItem item = (FileItem)itr.next();
		if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
%>    	
SIZE DOC :" <% item.getSize(); %>
<%    	  
			//if(item.getSize()>2097152)
			//{
			 //context.put("alert_saiz","yes");
			//}
			//else
			//{
			//saveData(item);			    	  
			//}
		}
	}
%>
<table width="95%" border="0" cellspacing="2" cellpadding="2" align="center">
	<tr>
		<td colspan="2"></td>
	</tr>
    <tr>
      <td colspan="2" class="success"><p><span>Terima kasih di atas aduan anda. 
        </span></p>
        <p><span>Sila simpan nombor rujukan aduan di bawah untuk semakan anda.</span></p>
        <p><span>No. Rujukan Aduan : <strong><%=aduan.getId()%></strong> </span></p>
        <p><span>Pihak kami akan memproses aduan/cadangan anda secepat mungkin.</span>           
            </p></td>
    </tr>
    <tr>
    <td width="30%">&nbsp;</td>
    <td width="70%"><input type="button" name="cmdAduanBaru" id="cmdAduanBaru" value="Tambah Aduan" onclick="javascript:aduanBaru()" /> 
    </td>
  </tr>

</table>

<%	
} catch(Exception e){
%>	

<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td colspan="2"></td>
	</tr>
    <tr>
      <td colspan="2" class="success"><p><span>Aduan anda gagal dihantar. 
        </span></p>
        <p><span>Sila cuba sebentar lagi.</span></p>
        <p><span>Mesej Gagal : <strong><%=e.getMessage() %></strong> </span></p>
      </td>
    </tr>
    <tr>
    <td width="30%">&nbsp;</td>
    <td width="70%"><input type="button" name="cmdAduanBaru" id="cmdAduanBaru" value="Tambah Aduan" onclick="javascript:aduanBaru()" /> 
    </td>
  </tr>
</table>

<%
}
%>

</form>
</td></tr>
<tr>
	<td>&nbsp;</td>
</tr>
<tr>
	<td>&nbsp;</td>
</tr>
</table>

<div class="page_footer_body">
<div class="page_footer">
<div class="page_footer_1">
</div>
</div>
</div>

<script type="text/javascript">
	aduanBaru = function() {
		document.f.action = "PortalAduan.jsp";
		document.f.submit();
	}
</script>

</div>
</div>
</body>
</html>