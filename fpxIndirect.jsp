<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" type="text/css" href="css/online.css" />



<%
	String status = (String)request.getAttribute("STATUS");
	if(status == null)
		status ="";
	String recordId = (String)request.getAttribute("IDFPX");
		
	String nilaiWord = (String)request.getAttribute("NILAI_WORD");
	if(nilaiWord == null)
		nilaiWord ="";
	
	
	String noTransaksi = (String)request.getAttribute("NO_TRANSAKSI");
	String amount = (String)request.getAttribute("AMOUNT");
	String jenisBayaran = (String)request.getAttribute("JENIS_BAYARAN");
	String tarikh = (String)request.getAttribute("TARIKH_TRANSAKSI");
%>


<script type="text/javascript">
function cetakResit() {
	
 	var url = "servlet/ekptg.report.online.ResitBayaran?id_fpx=<%=recordId%>&nilai=<%=nilaiWord%>";
    var hWnd = window.open(url,'printuser','width=700,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function mainPage(){
	window.location = 'online/';
}
function nextPay(){
	window.location = 'online/1288069120507?_portal_module=ekptg.fpx.FrmFPXView';
}
</script>
<title>JKPTG Online</title>
</head>
<body class="apps-container">

<div class="welcome">
<b>FPX - Bayaran Online</b>
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
									<div class="page_header_10">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<table class="tab" width="100%"  cellspacing="0" cellpadding="0" border="0">
	<tr>
		<td valign="bottom">&nbsp;
		</td>
	</tr>
</table>

<fieldset style="padding: 5px 5px 10px 5px; margin: 0px 15px 15px 15px;"><legend><strong>MAKLUMAT BAYARAN</strong></legend>
	<table width="95%" border="0" cellspacing="0" cellpadding="5" align="center">
		<tr>
			<td colspan="2">
				<p align="left">Terima kasih kerana menggunakan Sistem JKPTG <i>Online.</i></p>
				<%-- <% if(status.equals("SUCCESSFUL")){ %> --%>
				<p align="left">Transaksi bayaran anda telah berjaya, sila rujuk jadual di bawah :-
					<table width="70%" border="0" cellspacing="1" cellpadding="3">
						<tr class="table_header">
							<th>No Transaksi</th>
							<th>Tarikh & Masa</th>
							<th>Jenis Bayaran</th>
							<th>Amaun (RM)</th>
							<th>&nbsp;</th>
						</tr>
						<tr class="row2">
							<td><%=noTransaksi %></td>
							<td><%=tarikh %></td>
							<td><%=jenisBayaran %></td>
							<td><%=amount %></td>
							<td><input type="button" style="cursor:pointer" value="Papar Resit" onclick=" cetakResit()"></td>
						</tr>
					</table>
				</p>
				<%-- <%} else {%> --%>
				<p align="left">Transaksi bayaran anda telah gagal. Sila hubungi Seksyen Pentadbiran & Kewangan di talian 03-8871 2908 jika ada sebarang pertanyaan</p>
				<%-- <%}%> --%>
				<p align="left">
					<input type="button" value="Kembali ke Menu Utama" onclick="mainPage()">
					<input type="button" value="Transaksi Seterusnya" onclick="nextPay()">
				</p>
			</td>
		</tr>
	</table>
</fieldset>

</body>
</html>