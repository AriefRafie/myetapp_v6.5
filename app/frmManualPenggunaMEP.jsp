<style type="text/css">
<!--
.style1 {
	color: #0033FF;
	font-size: 12px;
//font-style: bold;
}

-->
</style>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>	
    	<td>&nbsp;</td>
	</tr>
	<tr>	
    	<td>
    		
		<fieldset>	
				<table width="100%">
					<tr>	
						<td width="5%">&nbsp;</td>
	    				<td width="95%" > <a href="javascript:openManualX('PanduanModulMEP')" class=style1><b>Manual Pengguna (Modul Maklumat Eksekutif Pengurusan)</b></a>	
						</td>
					</tr>
					<!-- 
					<tr>	
						<td>&nbsp;</td>
						<td> <a href="javascript:openManualX('Panduan Mengisi Permohonan Borang P Secara Online')" class=style1><b>Panduan Permohonan Secara Online - Borang P</b></a>	
						</td>
					</tr>	
					<tr>	
						<td>&nbsp;</td>
						<td> <a href="javascript:openManualX('panduanperintah')" class=style1><b>Panduan Menguruskan Perintah</b></a>	
						</td>
					</tr>
					-->
					<td>&nbsp;</td>
					<table>
							<tr>	
							<input type="reset" value="Kembali ke Menu Utama" onclick="javascript:menuUtama()"/>
							</tr>		
					</table> 
				</table>
		</fieldset>
		
		</td>
	</tr>
</table>

#set ($portal_role = "online")
<script>
	//Panduan Memohon Secara Online - Borang A
	//Panduan Memohon Secara Online - Borang P
	
	function openManual(pautan){
		//alert(pautan)
		var param = "";
	    var url = "../borang/"+pautan+".pdf";
	    var hWnd = window.open(url,"Manual Pengguna","width=800,height=500, resizable=yes,scrollbars=yes");
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
    }
    
	function openManualX(pautan){
    	var url = "../borang/"+pautan+".pdf";
	    
	    var hWnd = window.open(url,'printuser','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
	       hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();
    }
	
	function menuUtama(){
		document.${formName}.action = "$EkptgUtil.getTabID('Dashboard',$myrole)?_portal_module=ekptg.view.meps.FrmDashboard";
		document.${formName}.submit();
	}
</script>