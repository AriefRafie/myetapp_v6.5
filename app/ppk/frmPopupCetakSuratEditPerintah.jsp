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

<p>
   <input name="report" type="hidden" id="report" value="$report"/>
   <input name="flagReport" type="hidden" id="flagReport" value="$flagReport"/>
   <input name="idperbicaraan" type="hidden" id="idperbicaraan" value="$id_perbicaraan"/>
   <input name="idhta" type="hidden" id="idhta" value="$idhta"/>
   <input name="daerahrayuan" type="hidden" id="daerahrayuan" value="$daerahrayuan"/>
   <input name="negerirayuan" type="hidden" id="negerirayuan" value="$negerirayuan"/>
   <input name="idobminor" type="hidden" id="idobminor" value="$idobminor"/>
   <input name="idfail" type="hidden" id="idfail" value="$idfail"/> 
   <input name="idsimati" type="hidden" id="idsimati" value="$idsimati"/> 
   <input name="idpejabatjkptg" type="hidden" id="idpejabatjkptg" value="$idpejabatjkptg"/> 
   <input name="idPegawai" type="hidden" id="idPegawai" value="$id_Pegawai"/>  
   <input name="noKP" type="hidden" id="noKP" value="$noKP" />
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td>
    	<fieldset><legend><strong>Cetakan Laporan</strong></legend>
        	<table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="30%">&nbsp;</td>
                <td width="70%">&nbsp;</td>
              </tr>
             
              <tr>
                <td width="30%">&nbsp;&nbsp; No. Fail</td>
                <td width="70%">: $noFail.toUpperCase()</td>
              </tr>
         
             <tr>
                <td><span class="$style style1">*</span> Nama Pegawai</td>
                <td>: $selectedNamaPegawai</td>
              </tr> 
               <tr>
				<td>
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSurat('$noFail','$idfail','$flagReport')">
                	<input type="button" name="cmdKeluar" id="cmdKeluar" value="Keluar" onclick="javascript:keluar()">             
             	</td>
              </tr>
            </table>
      </fieldset>
    </td>
  </tr>
    <tr>
 	<td>&nbsp;</td>
  </tr>

</table>


<script>

function keluar() {
	window.close();
}


function cetakSurat(noFail,idfail,flagReport) {
	
	 if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	} 
	
	if(flagReport==1){

    	var url = "../../servlet/ekptg.report.ppk.SuratPembetulanPerintah?nofail="+noFail+"&idfail="+idfail+"&idPegawai="+document.${formName}.socPegawai.value+"&flagReport="+flagReport;
    
	}else{
		var url = "../../servlet/ekptg.report.ppk.SuratPembetulanPerintah2?nofail="+noFail+"&idfail="+idfail+"&idPegawai="+document.${formName}.socPegawai.value+"&flagReport="+flagReport;
	}
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

</script>


