
<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>

 <input type="hidden" name="idFail" id="idFail" value="$idFail"> 
  <input type="hidden" name="userId" id="userId" value="$userId">
    <input type="hidden" name="otp" id="otp" value="$!otp">
<script type="text/javascript" src="../../library/js/SpryTabbedPanels.js"></script>
<script type="text/javascript" src="../../library/js/ekptgTools.js"></script>
<script type="text/javascript" src="../../img"></script>
<link rel="stylesheet" type="text/css" href="../../css/SpryTabbedPanels.css">
<style type="text/css">
#parse("css/eTapp_PPK.css")
</style>

<p> <textarea id="signedData" name="signedData" style="display:none;">$!signedData</textarea></p>
<!--  -->
<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td></td>
	</tr>
	<tr>
  		<td><marquee behavior="scroll" direction="left">Sebarang pertanyaan, sila hubungi Pusat Pertanyaan Pusaka di talian  <b>03-88712999</b></marquee></td>
  	</tr>
	<tr>
    	<td>
    		<fieldset>
    			<legend><strong>Pengesahan Permohonan menggunakan TAC</strong></legend>
	    		<table width="100%" cellspacing="6" cellpadding="1" border="0">
	   <tr>
    <td colspan="3">Untuk meneruskan sila klik pada butang<font color="blue" class="blink"><b> <a href="#" onClick="javascript:mohonTAC('$!idFail','$!id_Permohonan')"> Mohon TAC</a> </b></font> untuk pengesahan cetakan perintah</td>
    
    <!--  <b><a href="#" font color="blue" class="blink" onClick="javascript:cetakSuratPanduanBicara('$!idFail','$!id_Permohonan')">Mohon TAC</a></b> untuk pengesahan cetakan perintah</td>
    -->
  </tr>
  
  <tr>
    <td><div align="center">No.TAC</div></td>
    <td>:</td>
    <td><input type='text' name="notac"  value="$!notac"><font color="blue">6 digit TAC</font></td> 

  </tr>
  
  <tr>
  		<td> 
  		</td>
  			<td> 
  		</td>
  			<!--  	<td> <a href="#" onClick="javascript:cetakSuratPanduanBicara('$!idFail','$!id_Permohonan')">
		       	<input type="button" value="Hantar">
		    </a>
		      <a href="#"  onClick="javascript:clearData();">
		    </a>
		    
  		</td>
  		-->
  		<td>
            	<input name="hantar" value="Hantar" type="button" onclick="javascript:hantarTAC('$!idFail','$!id_Permohonan')" />
  				<input type=button value = "Reset" onClick="javascript:clearData();">
            </td>
  		
  	</tr>			 
	</table>
				
					
			
      		</fieldset>
    	</td>
    	<td></td>
  	</tr>
  	<tr>
 		<td>&nbsp;</td>
  	</tr>
</table>


<script>

function mohonTAC(idFail,id_Permohonan) {
 /*   var url = "../../servlet/ekptg.report.ppk.SuratPanduanBicara?idpermohonan="+idpermohonan+"&idfail="+idfail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();*/
    //alert(document.${formName}.action);
   // alert(idfail);
    //alert("id_Permohonan xxx"+id_Permohonan);
    
    //alert("id_FAIL CCC"+idFail);
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPopupTAC&command=simpan";
	//alert(document.${formName}.action);
	//if ( !window.confirm("No TAC telah dihantar") ){
		alert("No. TAC telah dihantar ke emel pengguna");
		document.${formName}.submit();		
	//	return;
	//}
	
}
function hantarTAC(idFail,id_Permohonan) {
	alert(idFail);
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPopupTAC&command=hantar";	
		document.${formName}.submit();		

		var url = "../servlet/ekptg.report.ppk.BorangF?idfail="+idFail;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		
		if ((document.window != null) && (!hWnd.opener))
			hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus(); 
	
	}

function cetakBorangD(noFail,idfail,idPerbicaraan) {
	var url = "../../servlet/ekptg.report.ppk.BorangD_ORI?nofail="+noFail+"&idfail="+idfail+"&idperbicaraan="+idPerbicaraan;
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	
	if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	if (hWnd.focus != null) hWnd.focus(); 
}

function cetakBorangS(noFail,idfail,id_perbicaraan,idpemohonsek8,idsimati,signedData) {
	var url = "../../servlet/ekptg.report.ppk.BorangS_ORI?nofail="+noFail+"&idfail="+idfail+"&idperbicaraan="+id_perbicaraan+"&idpemohons8="+idpemohonsek8+"&idsimati="+idsimati+"&signedData="+signedData;
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	
	if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	if (hWnd.focus != null) hWnd.focus(); 
}

function keluar() {
	window.close();
}

</script>


