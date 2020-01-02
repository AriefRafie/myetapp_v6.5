<script type="text/javascript" src="../../../library/js/report.js" ></script>
<link rel="stylesheet" type="text/css" href="../../css/Integrasi.css" />
<strong><center></center></strong>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input type="hidden" name="hitButton">
  <input type="hidden" name="id" value="$!id"/>

</p>
<fieldset>
<legend>
<strong>Senarai Laporan Harta Tanah Persekutuan</strong></legend>
    <table width="100%" cellspacing="2" cellpadding="1" border="0">
				  <tr class="table_header">
				  	<td width="10%" align="center">No.</td>
				  	<td width="80%"align="left">Nama Laporan</td>	
				  	<td width="80%"align="center">History</td>
				  </tr>
				  #if($SenaraiFail.size() > 0)
					#foreach ($result in $SenaraiFail )
					#set( $counter = $velocityCount )
					#if ( ($counter % 2) == 0 )
						#set( $row = "row2" )
					#else
						#set( $row = "row1" )
                  #end
                  
				<tr>
					  <td class="$row" align="center">$result.No</td>
                      #if($current_role == '(INTEGRASI)UsersMACGDI')
					  <td class="$row" align="left" style="text-transform:uppercase;"><a href="javascript:cetakLaporan('$result.id')" class="style1"><font color='blue'>$result.NamaLaporan</font></a></td>
                      #else
                      <td class="$row" align="left" style="text-transform:uppercase;">$result.NamaLaporan</font></td>
                      #end
					  <td class="$row" style="text-transform:uppercase;"><a href="javascript:papar('$result.id')" class="style1"><font color='blue'>Lihat</font></a></td>
              </tr>
              #end
				#else
					<tr>
						<td colspan="6">Rekod Tidak Dijumpai</td>
					</tr>
				#end
			</table>
<input type="hidden" id="paramUserLogin" name="paramUserLogin" value='$!{session.getAttribute("_portal_login")}' >
<input type="hidden" id="rFormat" name="rFormat" value='$!{session.getAttribute("rFormat")}'>
	
</fieldset>

<script type="text/javascript">


function kosongkan() {
	document.${formName}.reset();
	document.${formName}.paramNoLot.value = "";
	document.${formName}.paramNoCF.value = "";
	document.${formName}.paramNamaPemaju.value = "";
	document.${formName}.paramNamaSkim.value = "";
	document.${formName}.submit();
}

function cetakLaporan(id) {
	
	//alert(id);
	if(id == '1' )
	{ //alert("mASUK");
		var url = "../x/${securityToken}/ekptg.view.integrasi.macgdi.FrmPopupLaporanHTP?id="+id;
    	var hWnd = window.open(url,'printuser','width=600,height=300, resizable=yes,scrollbars=yes');
    	if ((document.window != null) && (!hWnd.opener))
       		hWnd.opener = document.window;
    	if (hWnd.focus != null) hWnd.focus();
			hWnd.focus();
	}
	else if(id==2)
	{
		var url = "../x/${securityToken}/ekptg.view.integrasi.macgdi.FrmPopupLaporanTanahMilikMengikutNegeri?id="+id;
    	var hWnd = window.open(url,'printuser','width=600,height=300, resizable=yes,scrollbars=yes');
    	if ((document.window != null) && (!hWnd.opener))
       		hWnd.opener = document.window;
    	if (hWnd.focus != null) hWnd.focus();
			hWnd.focus();
	}
	else if(id==3)
	{
		var url = "../x/${securityToken}/ekptg.view.integrasi.macgdi.FrmPopupLaporanRizabNegeri?id="+id;
    	var hWnd = window.open(url,'printuser','width=600,height=300, resizable=yes,scrollbars=yes');
    	if ((document.window != null) && (!hWnd.opener))
      	 hWnd.opener = document.window;
   	 	if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();
	}
	else 
	{	
		
		var url = "../x/${securityToken}/ekptg.view.integrasi.macgdi.FrmPopupLaporanRizabKementerian?id="+id;
    	var hWnd = window.open(url,'printuser','width=600,height=300, resizable=yes,scrollbars=yes');
    	if ((document.window != null) && (!hWnd.opener))
       		hWnd.opener = document.window;
   	 	if (hWnd.focus != null) hWnd.focus();
			hWnd.focus();
	}
}

function papar(id) {
	var url = "../x/${securityToken}/ekptg.view.integrasi.macgdi.FrmSenaraiHistory?id="+id;
    	var hWnd = window.open(url,'printuser','width=600,height=300, resizable=yes,scrollbars=yes');
    	if ((document.window != null) && (!hWnd.opener))
       		hWnd.opener = document.window;
    	if (hWnd.focus != null) hWnd.focus();
			hWnd.focus();
}

</script>