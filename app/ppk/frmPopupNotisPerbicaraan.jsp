<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>


<script type="text/javascript" src="../../library/js/SpryTabbedPanels.js"></script>
<script type="text/javascript" src="../../library/js/ekptgTools.js"></script>
<script type="text/javascript" src="../../img"></script>
<link rel="stylesheet" type="text/css" href="../../css/SpryTabbedPanels.css">
<style type="text/css">
#parse("css/eTapp_PPK.css")
</style>

<p> <textarea id="signedData" name="signedData" style="display:none;">$!signedData</textarea></p>

<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr><td></td></tr>
	<tr>
  		<td><marquee behavior="scroll" direction="left">Sebarang pertanyaan, sila hubungi Pusat Pertanyaan Pusaka di talian  <b>03-88712999</b></marquee></td>
  	</tr>
	<tr>
    	<td>
    		<fieldset>
    			<legend><strong>Maklumat Notis Perbicaraan</strong></legend>
	    		<table align="left" width="100%" cellspacing="2" cellpadding="1" border="0">
	    			<tr>
				  		<td width='20%'>Tarikh Notis</td>
				  		<td> : </td>
				  		<td><b>$tarikh_notis</b></td>
				  	</tr>
				  	<tr>
				  		<td>Tarikh Bicara</td>
				  		<td> : </td>
				  		<td><b>$tarikh_bicara</b></td>
				  	</tr>
				  	<tr>
				  		<td>Masa Bicara</td>
				  		<td> : </td>
				  		<td><b>$masa_bicara</b></td>
				  	</tr>
				  	<tr>
					  	<td>Tempat Bicara</td>
					  	<td> : </td>
					  	<td>
						  	<b>$tempat_bicara
						  	<br>
						  	$alamat1
						  	<br>
						  	$alamat2
						  	$alamat3
						  	<br>
						  	$poskod
						  	$bandar
						  	<br>
						  	$nama_negeri</b>
					  	</td>
				  	</tr>
				  	<tr>
				 		<td>&nbsp;</td>
				  	</tr>
				  	<tr><td colspan="3"><font color="blue" class="blink"><b>PEMOHON ADALAH DIWAJIBKAN HADIR PADA HARI PERBICARAAN BERSAMA WARIS</b></font></td></tr>
				</table>
      		</fieldset>
    	</td>
  	</tr>
  	<tr>
  		<td>
  			<a href="#" onClick="javascript:cetakSuratPanduanBicara('$!idFail','$!id_Permohonan')">
		       	<input type="button" value="Panduan Perbicaraan">
		    </a>
  		</td>
  	</tr>
<!--   	<tr> -->
<!--   		<td> -->
<!-- 			#if($seksyen == "17") -->
<!-- 			<a href="#" onClick="javascript:cetakBorangS('$!noFail.toUpperCase()','$!idFail','$!idPerbicaraan','$!id_Permohonan','$!icSimati','$signedData')"> -->
<!-- 		       	<input type="button" value="Borang S"> -->
<!-- 		    </a> -->
<!-- 			#else    -->
<!-- 			<a href="#" onClick="javascript:cetakBorangD('$!noFail.toUpperCase()','$!idFail','$!idPerbicaraan')"> -->
<!-- 		 		<input type="button" value="Borang D"> -->
<!-- 		 	</a>    			 -->
<!-- 			#end -->
<!-- 		</td> -->
<!-- 	</tr> -->
  	<tr>
 		<td>&nbsp;</td>
  	</tr>
</table>


<script>

function cetakSuratPanduanBicara(idfail,idpermohonan) {
    var url = "../../servlet/ekptg.report.ppk.SuratPanduanBicara?idpermohonan="+idpermohonan+"&idfail="+idfail;
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


