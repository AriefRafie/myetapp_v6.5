f<link rel="stylesheet" type="text/css" href="../../css/eTapp_PPK.css" />

<style>
<!-- 
.success_detail {
	color: #4F8A10;
	background-color: #DFF2BF;
	border: 1px solid #4F8A10;
}
 -->
</style>
<body>
	<table width="95%" border="0" cellspacing="2" cellpadding="2" align="center">
		<input type="hidden" name="NO_FAIL" id="NO_FAIL" value='$!NO_FAIL'/> 
		<input type="hidden" name="id_perbicaraan" id="id_perbicaraan" value='$!id_perbicaraan'/> 
		<input type="hidden" name="id_fail" id="id_fail" value='$!id_fail'/> 
		<tr>
			<td colspan="3">&nbsp;</td>
		</tr>
	    <tr>
	   		<td colspan="3" class="success">
		   		<p><span>Tandatangan Berjaya Dikenalpasti</span></p>
	   		</td>
	    </tr>
	    <tr>
	    	<td align=center>	    	
	    	#if($!id_perbicaraan!="")
		    	<span id="span_PNB" >
		       	</span>
		    #end		    		    
	    		<input type="button" name="tutup" id="tutup" value="Tutup" onclick="tutupTetingkap()" />
	    	</td>
		</tr>
		
	</table>
</body>
<script>
	function openPopupPNB(NO_FAIL,id_perbicaraan,idfail){
		try {	
			window.opener.cetakBorangD_X(NO_FAIL,id_perbicaraan,idfail);
		}
		catch (err) {}
	   	window.close();	
	    return false;
	}
	function tutupTetingkap() {		
		window.close();
	}
	function cetakBorangD(NO_FAIL,id_perbicaraan,idfail) {	
		var url = "../x/${securityToken}/ekptg.report.ppk.BorangD?nofail="+NO_FAIL+"&idfail="+idfail+"&idperbicaraan="+id_perbicaraan+"&flagVersion=popupPNB";
	    //var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+NO_FAIL+"&idperbicaraan="+id_perbicaraan+"&report=BorangD&flagReport=B";
		var hWnd = window.open(url,'Cetak','width=1200,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();   
	}
	
</script>