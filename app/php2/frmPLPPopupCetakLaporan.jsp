<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
-->
</style>
<p>
  <input name="idfail" type="hidden" id="idfail" value="$idfail"/>
  <input name="idUlasanTeknikal" type="hidden" id="idUlasanTeknikal" value="$idUlasanTeknikal"/>
  <input name="idHakmilikPermohonan" type="hidden" id="idHakmilikPermohonan" value="$idHakmilikPermohonan"/>
  <input name="idTanahGanti" type="hidden" id="idTanahGanti" value="$idTanahGanti"/>
  <input name="idMesyuarat" type="hidden" id="idMesyuarat" value="$idMesyuarat"/>
  <input name="report" type="hidden" id="report" value="$report"/>
</p>
<table width="100%" cellspacing="2" cellpadding="2" border="0">
  <tr>
    <td><fieldset>
      <legend><strong>CETAKAN SURAT / LAPORAN</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="29%">&nbsp;</td>
          <td width="70%">&nbsp;</td>
        </tr>
        <tr>
          <td >&nbsp;</td>
          <td>No Fail</td>
          <td>: <strong>$noFail</strong></td>
        </tr>
        <tr>
          <td><span class="style1">*</span></td>
          <td>Kandungan</td>
          <td>:
            <input name="txtKandungan" type="text" size="2" maxlength="2" onblur="validateNumber(this,this.value);"></td>
        </tr>
        <tr>
          <td ><span class="style1">*</span></td>
          <td>Pegawai yang Menandatangani</td>
          <td>: $selectPegawai</td>
        </tr>
        <!-- <tr>
          <td colspan="4"><fieldset>
          	<legend><strong>SALINAN KEPADA</strong></legend>
          	 <table width="100%" border="0" cellspacing="2" cellpadding="2">
        	  <tr>
			  	<td valign="top">&nbsp;</td>
				<td valign="top">JKPTG</td>
				<td>: $selectPejabatJKPTG</td>
			  </tr>
        	  <tr>
				<td valign="top">&nbsp;</td>
				<td valign="top">KJP</td>
				<td valign="top">: $selectKementerian 
					<input type="button" name="tambahKem" id="tambahKem" onclick="javascript:tambahKementerian()" value="Pilih">
					<div id="showKementerian" ></div>
				</td>
			  </tr>
        	 </table>
          </fieldset></td>
        </tr> -->
        <tr>
          <td >&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td >&nbsp;</td>
          <td>&nbsp;</td>
          <td>
            <!-- START CETAK PELEPASAN -->
            #if($report == 'suratAkuanTerima')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratAkuanTerima('$idFail')">
            #end
            #if($report == 'suratMUKJP')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratMUKJP('$idFail','$idUlasanTeknikal')">
            #end      
            #if($report == 'suratUlanganKJP')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakPLPSuratUlanganKJP('$idFail','$idUlasanTeknikal')">
            #end  
            #if($report == 'suratMULT')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakPLPSuratMULT('$idFail','$idUlasanTeknikal')">
            #end 
            #if($report == 'suratUlanganLT')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakPLPSuratUlanganLT('$idFail','$idUlasanTeknikal')">
            #end            
            #if($report == 'suratMUJPPHB')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratMUJPPHB('$idFail','$idUlasanTeknikal')">
            #end       
            #if($report == 'suratMUJPPHP')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratMUJPPHP('$idFail','$idUlasanTeknikal')">
            #end 
            #if($report == 'suratMUJPPHG')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratMUJPPHG('$idFail','$idUlasanTeknikal')">
            #end             
            #if($report == 'suratUlanganJPPHAsal')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakPLPSuratUlanganJPPHAsal('$idFail','$idUlasanTeknikal')">
            #end              
            #if($report == 'suratIringanKewangan')
			<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratIringanKewangan('$idFail')"/>
            #end  
            #if($report == 'suratTolak')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratTolak('$idFail')">
            #end
            #if($report == 'suratLulusSebahagian')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratKelulusanSebahagian('$idFail')">
            #end
            #if($report == 'suratLulusSeluruh')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratKelulusanSeluruh('$idFail')">
            #end
            #if($report == 'PLPSuratLulusBersyarat')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakPLPSuratLulusBersyarat('$idFail','$idPermohonan')">
            #end
            #if($report == 'suratIringan12A')
			<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratIringan12A('$idFail')"/>
            #end            
            #if($report == 'suratIringan12B')
			<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratIringan12B('$idFail')"/>
            #end               
            <!-- END CETAK PELEPASAN -->
            
            <!-- START CETAK PENAWARAN -->
            #if($report == 'PNWsuratAkuanTerima')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakPNWSuratAkuanTerima('$idFail')">
            #end
            #if($report == 'PNWSuratMULT')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakPNWSuratLawatanTapak('$idFail','$idUlasanTeknikal')">
            #end
            #if($report == 'PNWSuratUlanganLT')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakPNWSuratUlanganLT('$idFail','$idUlasanTeknikal')">
            #end            
            #if($report == 'PNWSuratTawaran')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakPNWSuratTawaran('$idFail')">
            #end
            #if($report == 'PNWSuratTolak')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakPNWSuratTolak('$idFail')">
            #end
            #if($report == 'PNWSuratLulus')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratLulus('$idFail')">
            #end
            #if($report == 'PNWSuratLulusPajakan')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratLulusPajakan('$idFail','$idTanah')">
            #end
            <!-- END CETAK PENAWARAN -->
            
            <!-- START CETAK TUKAR GUNA -->
			#if($report == 'TKRSuratAkuanTerima')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakTKRSuratAkuanTerima('$idFail')">
            #end    
            #if($report == 'TKRSuratMUKJP')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakTKRSuratMUKJP('$idFail')">
            #end        
            #if($report == 'suratLT')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratLT('$idFail','$idUlasanTeknikal')">
            #end 
            #if($report == 'TKRSuratUlanganKJP')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakTKRSuratUlanganKJP('$idFail')">
            #end  
            #if($report == 'TKRSuratMULT')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakTKRSuratMULT('$idFail','$idUlasanTeknikal')">
            #end 
            #if($report == 'TKRSuratUlanganLT')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakTKRSuratUlanganLT('$idFail','$idUlasanTeknikal')">
            #end            
            #if($report == 'TKRSuratMUJPPHB')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakTKRSuratMUJPPHB('$idFail','$idUlasanTeknikal')">
            #end 
            #if($report == 'TKRSuratMUJPPHP')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakTKRSuratMUJPPHP('$idFail','$idUlasanTeknikal')">
            #end
            #if($report == 'TKRSuratMUJPPHG')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakTKRSuratMUJPPHG('$idFail','$idUlasanTeknikal')">
            #end                                             
            #if($report == 'TKRSuratLulus')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakTKRSuratLulus('$idFail','$idPermohonan')">
            #end
            #if($report == 'TKRSuratLulusBersyarat')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakTKRSuratLulusBersyarat('$idFail','$idPermohonan')">
            #end
            #if($report == 'TKRSuratTolak')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakTKRSuratTolak('$idFail','$idPermohonan')">
            #end
            #if($report == 'TKRSuratPanggilMesyuarat')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakTKRSuratPanggilMesyuarat('$idFail','$idMesyuarat')">
            #end
            <!-- END CETAK TUKAR GUNA -->
          </td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
</table>
<script>
function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	} else {
		if (content.length < 2){
			document.${formName}.txtKandungan.value = "0" + content;
		}
	}
}
</script>

<script>
document.${formName}.selected_id_KJP.value = "0";
function tambahKementerian(){	

	var value_selected = document.${formName}.socKementerian.value;
	
	if(value_selected=="")
	{
		alert("Sila Pilih KJP!");
		return false;
	}
	else
	{
		var idKJP_size = document.getElementsByName("idKJP").length;
		if(idKJP_size>0)
		{
		  for(var i = 0; i < idKJP_size; i++)
		  {
			var check_idKJP = document.getElementsByName("idKJP")[i].value;
			if(value_selected==check_idKJP)
			{
				alert("KJP Telah Dipilih!");
				return false;
			}
				
		  }
		}
		var element_socKementerian = document.getElementById("socKementerian");
		var selectedTextKJP = element_socKementerian.options[element_socKementerian.selectedIndex].text;
			
		var inner_div = document.getElementById("showKementerian").innerHTML;
		inner_div = inner_div + "<span id='spanSelKJP_"+element_socKementerian.value+"' ><input type='hidden' name='idKJP' value='"+element_socKementerian.value+"'><a href='javascript:deleteKJP("+element_socKementerian.value+")' ><font color='blue'>HAPUS</font></a> "+selectedTextKJP + "<br></span>";
		document.getElementById("showKementerian").innerHTML = inner_div;
		document.${formName}.socKementerian.value = "";
	}
}
function deleteKJP(id){
	document.getElementById("spanSelKJP_"+id).remove();
}
</script>

<!-- START CETAK PELEPASAN -->
<script>

function cetakSuratAkuanTerima(idFail) {
	if(document.${formName}.txtKandungan.value == ""){
		alert('Sila masukkan Kandungan.');
  		document.${formName}.txtKandungan.focus();
		return; 
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	
	var list_id = "0";
	var idKJP_size = document.getElementsByName("idKJP").length;
	if(idKJP_size>0)
	{
		for(var i = 0; i < idKJP_size; i++)
		{
			var check_idKJP = document.getElementsByName("idKJP")[i].value;
			list_id = list_id + ","+check_idKJP;
		}
	}
		
	var url = "../../servlet/ekptg.report.php2.PLPSuratAkuanPenerimaan?ID_LISTKJP="+list_id+"&ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratMUKJP(idFail,idUlasanTeknikal) {
	if(document.${formName}.txtKandungan.value == ""){
		alert('Sila masukkan Kandungan.');
  		document.${formName}.txtKandungan.focus();
		return; 
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	
	var url = "../../servlet/ekptg.report.php2.PLPSuratMintaUlasanKJP?ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+"&ID_ULASANTEKNIKAL="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function cetakPLPSuratUlanganKJP(idFail,idUlasanTeknikal) {
	if(document.${formName}.txtKandungan.value == ""){
		alert('Sila masukkan Kandungan.');
  		document.${formName}.txtKandungan.focus();
		return; 
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	
	var list_id = "0";
	var idKJP_size = document.getElementsByName("idKJP").length;
	if(idKJP_size>0)
	{
		for(var i = 0; i < idKJP_size; i++)
		{
			var check_idKJP = document.getElementsByName("idKJP")[i].value;
			list_id = list_id + ","+check_idKJP;
		}
	}
		
	var url = "../../servlet/ekptg.report.php2.PLPSuratUlanganKJP?ID_LISTKJP="+list_id+"&ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+"&ID_ULASANTEKNIKAL="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function cetakPLPSuratMULT(idFail,idUlasanTeknikal) {
	if(document.${formName}.txtKandungan.value == ""){
		alert('Sila masukkan Kandungan.');
  		document.${formName}.txtKandungan.focus();
		return; 
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	
	var list_id = "0";
	var idKJP_size = document.getElementsByName("idKJP").length;
	if(idKJP_size>0)
	{
		for(var i = 0; i < idKJP_size; i++)
		{
			var check_idKJP = document.getElementsByName("idKJP")[i].value;
			list_id = list_id + ","+check_idKJP;
		}
	}
		
	var url = "../../servlet/ekptg.report.php2.PLPSuratMintaUlasanLT?ID_LISTKJP="+list_id+"&ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+"&ID_ULASANTEKNIKAL="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function cetakPLPSuratUlanganLT(idFail,idUlasanTeknikal) {
	if(document.${formName}.txtKandungan.value == ""){
		alert('Sila masukkan Kandungan.');
  		document.${formName}.txtKandungan.focus();
		return; 
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	
	var list_id = "0";
	var idKJP_size = document.getElementsByName("idKJP").length;
	if(idKJP_size>0)
	{
		for(var i = 0; i < idKJP_size; i++)
		{
			var check_idKJP = document.getElementsByName("idKJP")[i].value;
			list_id = list_id + ","+check_idKJP;
		}
	}
	
	var url = "../../servlet/ekptg.report.php2.PLPSuratUlanganLT?ID_LISTKJP="+list_id+"&ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+"&ID_ULASANTEKNIKAL="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function cetakSuratKelulusanSebahagian(idFail) {
	if(document.${formName}.txtKandungan.value == ""){
		alert('Sila masukkan Kandungan.');
  		document.${formName}.txtKandungan.focus();
		return; 
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	
	var list_id = "0";
	var idKJP_size = document.getElementsByName("idKJP").length;
	if(idKJP_size>0)
	{
		for(var i = 0; i < idKJP_size; i++)
		{
			var check_idKJP = document.getElementsByName("idKJP")[i].value;
			list_id = list_id + ","+check_idKJP;
		}
	}
		
	var url = "../../servlet/ekptg.report.php2.PLPSuratLulusSebahagian?ID_LISTKJP="+list_id+"&ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratKelulusanSeluruh(idFail) {
	if(document.${formName}.txtKandungan.value == ""){
		alert('Sila masukkan Kandungan.');
  		document.${formName}.txtKandungan.focus();
		return; 
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	
	var list_id = "0";
	var idKJP_size = document.getElementsByName("idKJP").length;
	if(idKJP_size>0)
	{
		for(var i = 0; i < idKJP_size; i++)
		{
			var check_idKJP = document.getElementsByName("idKJP")[i].value;
			list_id = list_id + ","+check_idKJP;
		}
	}
		
	var url = "../../servlet/ekptg.report.php2.PLPSuratLulusSeluruh?ID_LISTKJP="+list_id+"&ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function cetakPLPSuratLulusBersyarat(idFail,idPermohonan) { /* Surat Lulus Bersyarat by Ain */
	if(document.${formName}.txtKandungan.value == ""){
		alert('Sila masukkan Kandungan.');
  		document.${formName}.txtKandungan.focus();
		return; 
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	
	var list_id = "0";
	var idKJP_size = document.getElementsByName("idKJP").length;
	if(idKJP_size>0)
	{
		for(var i = 0; i < idKJP_size; i++)
		{
			var check_idKJP = document.getElementsByName("idKJP")[i].value;
			list_id = list_id + ","+check_idKJP;
		}
	}
		
	var url = "../../servlet/ekptg.report.php2.PLPSuratLulusBersyarat?ID_LISTKJP="+list_id+"&ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+"&ID_PERMOHONAN="+idPermohonan;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratTolak(idFail) {
	if(document.${formName}.txtKandungan.value == ""){
		alert('Sila masukkan Kandungan.');
  		document.${formName}.txtKandungan.focus();
		return; 
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	
	/* var idKJP_size = document.getElementsByName("idKJP").length;
	if(idKJP_size>0)
	{
		for(var i = 0; i < idKJP_size; i++)
		{
			var check_idKJP = document.getElementsByName("idKJP")[i].value;
			list_id = list_id + ","+check_idKJP; ID_LISTKJP="+list_id+"&
		}
	} */
		
	var url = "../../servlet/ekptg.report.php2.PLPSuratTolak?ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratMUJPPHB(idFail,idUlasanTeknikal) {
	if(document.${formName}.txtKandungan.value == ""){
		alert('Sila masukkan Kandungan.');
  		document.${formName}.txtKandungan.focus();
		return; 
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	
	var idKJP_size = document.getElementsByName("idKJP").length;
	if(idKJP_size>0)
	{
		for(var i = 0; i < idKJP_size; i++)
		{
			var check_idKJP = document.getElementsByName("idKJP")[i].value;
			list_id = list_id + ","+check_idKJP;
		}
	}
		
	var url = "../../servlet/ekptg.report.php2.PLPSuratMintaUlasanJPPHB?ID_LISTKJP="+list_id+"&ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+"&ID_ULASANTEKNIKAL="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratMUJPPHP(idFail,idUlasanTeknikal) {
	if(document.${formName}.txtKandungan.value == ""){
		alert('Sila masukkan Kandungan.');
  		document.${formName}.txtKandungan.focus();
		return; 
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	
	/* var idKJP_size = document.getElementsByName("idKJP").length;
	if(idKJP_size>0)
	{
		for(var i = 0; i < idKJP_size; i++)
		{
			var check_idKJP = document.getElementsByName("idKJP")[i].value;
			list_id = list_id + ","+check_idKJP;
		}
	} */
	/* ID_LISTKJP="+list_id+"&  */	
	var url = "../../servlet/ekptg.report.php2.PLPSuratMintaUlasanJPPHP?ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+"&ID_ULASANTEKNIKAL="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function cetakSuratMUJPPHG(idFail,idUlasanTeknikal) {
	if(document.${formName}.txtKandungan.value == ""){
		alert('Sila masukkan Kandungan.');
  		document.${formName}.txtKandungan.focus();
		return; 
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	
	var idKJP_size = document.getElementsByName("idKJP").length;
	if(idKJP_size>0)
	{
		for(var i = 0; i < idKJP_size; i++)
		{
			var check_idKJP = document.getElementsByName("idKJP")[i].value;
			list_id = list_id + ","+check_idKJP;
		}
	}
		
	var url = "../../servlet/ekptg.report.php2.PLPSuratMintaUlasanJPPHG?ID_LISTKJP="+list_id+"&ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+"&ID_ULASANTEKNIKAL="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function cetakPLPSuratUlanganJPPHAsal(idFail,idUlasanTeknikal) {
	if(document.${formName}.txtKandungan.value == ""){
		alert('Sila masukkan Kandungan.');
  		document.${formName}.txtKandungan.focus();
		return; 
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	
	var url = "../../servlet/ekptg.report.php2.PLPSuratUlanganJPPHAsal?ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+"&ID_ULASANTEKNIKAL="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratIringanKewangan(idFail) {
	if(document.${formName}.txtKandungan.value == ""){
		alert('Sila masukkan Kandungan.');
  		document.${formName}.txtKandungan.focus();
		return; 
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	
	var list_id = "0";
	var idKJP_size = document.getElementsByName("idKJP").length;
	if(idKJP_size>0)
	{
		for(var i = 0; i < idKJP_size; i++)
		{
			var check_idKJP = document.getElementsByName("idKJP")[i].value;
			list_id = list_id + ","+check_idKJP;
		}
	}
	
	var url = "../../servlet/ekptg.report.php2.PLPSuratIringanKewangan?ID_LISTKJP="+list_id+"&ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratIringan12A(idFail) {
	if(document.${formName}.txtKandungan.value == ""){
		alert('Sila masukkan Kandungan.');
  		document.${formName}.txtKandungan.focus();
		return; 
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	
	var list_id = "0";
	var idKJP_size = document.getElementsByName("idKJP").length;
	if(idKJP_size>0)
	{
		for(var i = 0; i < idKJP_size; i++)
		{
			var check_idKJP = document.getElementsByName("idKJP")[i].value;
			list_id = list_id + ","+check_idKJP;
		}
	}
		
	var url = "../../servlet/ekptg.report.php2.PLPSuratIringan12A?ID_LISTKJP="+list_id+"&ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratIringan12B(idFail) {
	if(document.${formName}.txtKandungan.value == ""){
		alert('Sila masukkan Kandungan.');
  		document.${formName}.txtKandungan.focus();
		return; 
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	
	var list_id = "0";
	var idKJP_size = document.getElementsByName("idKJP").length;
	if(idKJP_size>0)
	{
		for(var i = 0; i < idKJP_size; i++)
		{
			var check_idKJP = document.getElementsByName("idKJP")[i].value;
			list_id = list_id + ","+check_idKJP;
		}
	}
		
	var url = "../../servlet/ekptg.report.php2.PLPSuratIringan12B?ID_LISTKJP="+list_id+"&ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>
<!-- END CETAK PELEPASAN -->

<!-- START CETAK PENAWARAN -->
<script>
function cetakPNWSuratAkuanTerima(idFail) {
	if(document.${formName}.txtKandungan.value == ""){
		alert('Sila masukkan Kandungan.');
  		document.${formName}.txtKandungan.focus();
		return; 
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
		
	var url = "../../servlet/ekptg.report.php2.PNWSuratAkuanPenerimaan?ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPNWSuratLawatanTapak(idFail,idUlasanTeknikal) {
	if(document.${formName}.txtKandungan.value == ""){
		alert('Sila masukkan Kandungan.');
  		document.${formName}.txtKandungan.focus();
		return; 
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
		
	var url = "../../servlet/ekptg.report.php2.PNWSuratLawatanTapak?ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+"&ID_ULASANTEKNIKAL="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function cetakPNWSuratUlanganLT(idFail,idUlasanTeknikal) {
	if(document.${formName}.txtKandungan.value == ""){
		alert('Sila masukkan Kandungan.');
  		document.${formName}.txtKandungan.focus();
		return; 
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
		
	var url = "../../servlet/ekptg.report.php2.PNWSuratUlanganLT?ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+"&ID_ULASANTEKNIKAL="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function cetakPNWSuratTawaran(idFail) {
	if(document.${formName}.txtKandungan.value == ""){
		alert('Sila masukkan Kandungan.');
  		document.${formName}.txtKandungan.focus();
		return; 
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
		
	var url = "../../servlet/ekptg.report.php2.PNWSuratTawaran?ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPNWSuratTolak(idFail) {
	if(document.${formName}.txtKandungan.value == ""){
		alert('Sila masukkan Kandungan.');
  		document.${formName}.txtKandungan.focus();
		return; 
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
		
	var url = "../../servlet/ekptg.report.php2.PNWSuratTolak?ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratLulus(idFail,idTanah) {
	if(document.${formName}.txtKandungan.value == ""){
		alert('Sila masukkan Kandungan.');
  		document.${formName}.txtKandungan.focus();
		return; 
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
		
	var url = "../../servlet/ekptg.report.php2.PNWSuratLulus?ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+"&ID_TANAH="+idTanah;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratLulusPajakan(idFail) {
	if(document.${formName}.txtKandungan.value == ""){
		alert('Sila masukkan Kandungan.');
  		document.${formName}.txtKandungan.focus();
		return; 
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
		
	var url = "../../servlet/ekptg.report.php2.PNWSuratLulusPajakan?ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>
<!-- END CETAK PENAWARAN -->

<!-- START CETAK TUKAR GUNA -->
<script>
function cetakTKRSuratAkuanTerima(idFail) {
	if(document.${formName}.txtKandungan.value == ""){
		alert('Sila masukkan Kandungan.');
  		document.${formName}.txtKandungan.focus();
		return; 
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
		
	var url = "../../servlet/ekptg.report.php2.TKRSuratAkuanPenerimaan?ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakTKRSuratMUKJP(idFail) {
	if(document.${formName}.txtKandungan.value == ""){
		alert('Sila masukkan Kandungan.');
  		document.${formName}.txtKandungan.focus();
		return; 
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
		
	var url = "../../servlet/ekptg.report.php2.TKRSuratMUKJP?ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakTKRSuratUlanganKJP(idFail) {
	if(document.${formName}.txtKandungan.value == ""){
		alert('Sila masukkan Kandungan.');
  		document.${formName}.txtKandungan.focus();
		return; 
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
		
	var url = "../../servlet/ekptg.report.php2.TKRSuratUlanganKJP?ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakTKRSuratMULT(idFail,idUlasanTeknikal) {
	if(document.${formName}.txtKandungan.value == ""){
		alert('Sila masukkan Kandungan.');
  		document.${formName}.txtKandungan.focus();
		return; 
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	
	var list_id = "0";
	var idKJP_size = document.getElementsByName("idKJP").length;
	if(idKJP_size>0)
	{
		for(var i = 0; i < idKJP_size; i++)
		{
			var check_idKJP = document.getElementsByName("idKJP")[i].value;
			list_id = list_id + ","+check_idKJP;
		}
	}
		
	var url = "../../servlet/ekptg.report.php2.TKRSuratMintaUlasanLT?ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+"&ID_ULASANTEKNIKAL="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function cetakTKRSuratUlanganLT(idFail,idUlasanTeknikal) {
	if(document.${formName}.txtKandungan.value == ""){
		alert('Sila masukkan Kandungan.');
  		document.${formName}.txtKandungan.focus();
		return; 
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
		
	var url = "../../servlet/ekptg.report.php2.TKRSuratUlanganLT?ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+"&ID_ULASANTEKNIKAL="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakTKRSuratMUJPPHB(idFail,idUlasanTeknikal) {
	if(document.${formName}.txtKandungan.value == ""){
		alert('Sila masukkan Kandungan.');
  		document.${formName}.txtKandungan.focus();
		return; 
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
		
	var url = "../../servlet/ekptg.report.php2.TKRSuratMintaUlasanJPPHB?ID_FAIL="+idFail+"&ID_ULASANTEKNIKAL="+idUlasanTeknikal+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakTKRSuratMUJPPHG(idFail,idUlasanTeknikal) {
	if(document.${formName}.txtKandungan.value == ""){
		alert('Sila masukkan Kandungan.');
  		document.${formName}.txtKandungan.focus();
		return; 
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
		
	var url = "../../servlet/ekptg.report.php2.TKRSuratMintaUlasanJPPHG?ID_FAIL="+idFail+"&ID_ULASANTEKNIKAL="+idUlasanTeknikal+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakTKRSuratMUJPPHP(idFail,idUlasanTeknikal) {
	if(document.${formName}.txtKandungan.value == ""){
		alert('Sila masukkan Kandungan.');
  		document.${formName}.txtKandungan.focus();
		return; 
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
		
	var url = "../../servlet/ekptg.report.php2.TKRSuratMintaUlasanJPPHP?ID_FAIL="+idFail+"&ID_ULASANTEKNIKAL="+idUlasanTeknikal+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakTKRSuratLulus(idFail,idPermohonan) {
	if(document.${formName}.txtKandungan.value == ""){
		alert('Sila masukkan Kandungan.');
  		document.${formName}.txtKandungan.focus();
		return; 
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
		
	var url = "../../servlet/ekptg.report.php2.TKRSuratLulus?ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+"&ID_PERMOHONAN="+idPermohonan;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakTKRSuratLulusBersyarat(idFail,idPermohonan) { /* Surat Lulus Bersyarat by Ain */
	if(document.${formName}.txtKandungan.value == ""){
		alert('Sila masukkan Kandungan.');
  		document.${formName}.txtKandungan.focus();
		return; 
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
		
	var url = "../../servlet/ekptg.report.php2.TKRSuratLulusBersyarat?ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+"&ID_PERMOHONAN="+idPermohonan;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakTKRSuratTolak(idFail,idPermohonan) {
	if(document.${formName}.txtKandungan.value == ""){
		alert('Sila masukkan Kandungan.');
  		document.${formName}.txtKandungan.focus();
		return; 
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	
	var url = "../../servlet/ekptg.report.php2.TKRSuratTolak?ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+"&ID_PERMOHONAN="+idPermohonan;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratLT(idFail,idUlasanTeknikal) {
	if(document.${formName}.txtKandungan.value == ""){
		alert('Sila masukkan Kandungan.');
  		document.${formName}.txtKandungan.focus();
		return; 
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	
	var list_id = "0";
	var idKJP_size = document.getElementsByName("idKJP").length;
	if(idKJP_size>0)
	{
		for(var i = 0; i < idKJP_size; i++)
		{
			var check_idKJP = document.getElementsByName("idKJP")[i].value;
			list_id = list_id + ","+check_idKJP;
		}
	}
		
	var url = "../../servlet/ekptg.report.php2.PLPSuratLawatanTapak?ID_LISTKJP="+list_id+"&ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+"&ID_ULASANTEKNIKAL="+idUlasanTeknikal;	//+"&ID_JENISPEJABAT="+document.${formName}.socJenisPejabat.value;
	
	var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakTKRSuratPanggilMesyuarat(idFail,idMesyuarat) {
	if(document.${formName}.txtKandungan.value == ""){
		alert('Sila masukkan Kandungan.');
  		document.${formName}.txtKandungan.focus();
		return; 
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}

	var url = "../../servlet/ekptg.report.php2.TKRSuratPanggilMesyuarat?ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+"&ID_MESYUARAT="+idMesyuarat;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>
<!-- END CETAK TUKAR GUNA -->
<script>
function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}
function RemoveNonNumeric( strString ){
      var strValidCharacters = "1234567890";
      var strReturn = "";
      var strBuffer = "";
      var intIndex = 0;
      // Loop through the string
      for( intIndex = 0; intIndex < strString.length; intIndex++ )
      {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                  strReturn += strBuffer;
            }
      }
      return strReturn;
}
</script>