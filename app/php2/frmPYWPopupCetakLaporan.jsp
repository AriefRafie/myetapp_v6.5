<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
-->
</style>
<p>
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idUlasanTeknikal" type="hidden" id="idUlasanTeknikal" value="$idUlasanTeknikal"/>
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
            <input name="txtKandungan" type="text" size="2" maxlength="2" onBlur="validateNumber(this,this.value);"></td>
        </tr>
        <tr>
          <td ><span class="style1">*</span></td>
          <td>Pegawai yang Menandatangani</td>
          <td>: $selectPegawai</td>
        </tr>
        <tr>
          <td colspan="3" >&nbsp;</td>
        </tr>
        <!-- <tr>
          <td colspan="4"><fieldset>
            <legend><strong>SALINAN KEPADA</strong></legend>
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td valign="top">&nbsp;</td>
                <td valign="top">Kementerian</td>
                <td valign="top">: $selectKementerian
                  <input type="button" name="tambahKem" id="tambahKem" onclick="javascript:tambahKementerian()" value="Pilih">
                  <div id="showKementerian" ></div></td>
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
          <td><!-- START CETAK PYW -->
            #if($report == 'PYWSuratAkuanTerima')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakPYWSuratAkuanTerima('$idFail')">
            #end
            #if($report == 'PYWSuratTolakAdaPenyewa')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakPYWSuratTolakAdaPenyewa('$idFail')">
            #end              
            #if($report == 'PYWSuratMintaUlasanKJP')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakPYWSuratMintaUlasanKJP('$idFail','$idUlasanTeknikal')">
            #end 
            #if($report == 'PYWSuratUlanganKJP')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakPYWSuratUlanganKJP('$idFail','$idUlasanTeknikal')">
            #end    
            #if($report == 'PYWSuratMintaUlasanJPPH')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakPYWSuratMintaUlasanJPPH('$idFail')">
            #end 
            #if($report == 'PYWSuratUlanganJPPH')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakPYWSuratUlanganJPPH('$idFail','$idUlasanTeknikal')">
            #end              
            #if($report == 'PYWSuratMintaUlasanJKT')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakPYWSuratMintaUlasanJKT('$idFail')">
            #end   
            #if($report == 'PYWSuratUlanganKWP')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakPYWSuratUlanganKWP('$idFail','$idUlasanTeknikal')">
            #end   
            #if($report == 'PYWSuratUlanganKKW')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakPYWSuratUlanganKKW('$idFail','$idUlasanTeknikal')">
            #end              
            #if($report == 'PYWSuratUlanganPTG')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakPYWSuratUlanganPTG('$idFail','$idUlasanTeknikal')">
            #end              
            #if($report == 'PYWSuratUlanganDBKL')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakPYWSuratUlanganDBKL('$idFail','$idUlasanTeknikal')">
            #end              
            #if($report == 'PYWSuratUlanganBPH')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakPYWSuratUlanganBPH('$idFail','$idUlasanTeknikal')">
            #end                                   
            #if($report == 'PYWSuratMintaUlasanJH')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakPYWSuratMintaUlasanJH('$idFail')">
            #end       
            #if($report == 'PYWSuratUlanganJH')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakPYWSuratUlanganJH('$idFail','$idUlasanTeknikal')">
            #end                               
            #if($report == 'PYWSuratMintaLaporanTanah')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakPYWSuratMintaLaporanTanah('$idFail','$idUlasanTeknikal')">
            #end
            #if($report == 'PYWSuratUlanganLT')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakPYWSuratUlanganLT('$idFail','$idUlasanTeknikal')">
            #end
            #if($report == 'PYWSuratTawaran')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakPYWSuratTawaran('$idFail','$idPermohonan')">
            #end       
            #if($report == 'PYWSuratTawaranDasarDKL')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakPYWSuratTawaranDasarDKL('$idFail','$idPermohonan')">
            #end  
            #if($report == 'PYWSuratTawaranDasarLKL')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakPYWSuratTawaranDasarLKL('$idFail','$idPermohonan')">
            #end       
            #if($report == 'PYWSuratTolakMesyuarat')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakPYWSuratTolakMesyuarat('$idFail','$idPermohonan')">
            #end  
            #if($report == 'PYWSuratTolakKJP')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakPYWSuratTolakKJP('$idFail')">
            #end    
            #if($report == 'PYWSuratTarikbalikTawaran')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakPYWSuratTarikbalikTawaran('$idFail')">
            #end                 
            #if($report == 'PYWSuratTandatanganPerjanjian')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakPYWSuratTandatanganPerjanjian('$idFail','$idPermohonan')">
            #end          
            #if($report == 'PYWSuratMatiSetem')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakPYWSuratMatiSetem('$idFail')">
            #end
            #if($report == 'PYWSenaraiSemakSyarikat')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakPYWSenaraiSemakSyarikat('$idFail')">
            #end
            #if($report == 'PYWSenaraiSemakIndividu')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakPYWSenaraiSemakIndividu('$idFail')">
            #end
            #if($report == 'PYWNotisTamatTempoh')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakPYWNotisTamatTempoh('$idFail')">
            #end
            <!-- END CETAK PYW -->
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
<!-- SALINAN KEPADA BY AIN (9/5/2017) -->
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
		//alert(" element_socKementerian : "+element_socKementerian);
		var selectedTextKJP = element_socKementerian.options[element_socKementerian.selectedIndex].text;
		//alert(" selectedTextKJP : "+selectedTextKJP);
			
		var inner_div = document.getElementById("showKementerian").innerHTML;
		//alert(" inner_div:"+inner_div);
		inner_div = inner_div + "<span id='spanSelKJP_"+element_socKementerian.value+"' ><input type='hidden' name='idKJP' value='"+element_socKementerian.value+"'><a href='javascript:deleteKJP("+element_socKementerian.value+")' ><font color='blue'>HAPUS</font></a> "+selectedTextKJP + "<br></span>";
		document.getElementById("showKementerian").innerHTML = inner_div;
		document.${formName}.socKementerian.value = "";
	}
}
function deleteKJP(id){
	document.getElementById("spanSelKJP_"+id).remove();
}
</script>
<!-- START CETAK PYW -->
<script>
function cetakPYWSuratAkuanTerima(idFail) {
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
		
	var url = "../../servlet/ekptg.report.php2.PYWSuratAkuanPenerimaan?ID_LISTKJP="+list_id+"&ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPYWSuratTolakAdaPenyewa(idFail) {
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
		
	var url = "../../servlet/ekptg.report.php2.PYWSuratTolakAdaPenyewa?ID_LISTKJP="+list_id+"&ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPYWSuratMintaUlasanKJP(idFail,idUlasanTeknikal) {
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
		
	var url = "../../servlet/ekptg.report.php2.PYWSuratMintaUlasanKJP?ID_LISTKJP="+list_id+"&ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+"&ID_ULASANTEKNIKAL="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPYWSuratUlanganKJP(idFail,idUlasanTeknikal) {
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
		
	var url = "../../servlet/ekptg.report.php2.PYWSuratUlanganKJP?ID_LISTKJP="+list_id+"&ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+"&ID_ULASANTEKNIKAL="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPYWSuratMintaUlasanJPPH(idFail) {
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
		
	var url = "../../servlet/ekptg.report.php2.PYWSuratMintaUlasanJPPH?ID_LISTKJP="+list_id+"&ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPYWSuratUlanganJPPH(idFail,idUlasanTeknikal) {
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
		
	var url = "../../servlet/ekptg.report.php2.PYWSuratUlanganJPPH?ID_LISTKJP="+list_id+"&ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+"&ID_ULASANTEKNIKAL="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPYWSuratMintaUlasanJKT(idFail) {
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
		
	var url = "../../servlet/ekptg.report.php2.PYWSuratMintaUlasanJKT?ID_LISTKJP="+list_id+"&ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPYWSuratUlanganKWP(idFail,idUlasanTeknikal) {
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
		
	var url = "../../servlet/ekptg.report.php2.PYWSuratUlanganKWP?ID_LISTKJP="+list_id+"&ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+"&ID_ULASANTEKNIKAL="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPYWSuratUlanganKKW(idFail,idUlasanTeknikal) {
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
		
	var url = "../../servlet/ekptg.report.php2.PYWSuratUlanganKKW?ID_LISTKJP="+list_id+"&ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+"&ID_ULASANTEKNIKAL="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPYWSuratUlanganPTG(idFail,idUlasanTeknikal) {
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
		
	var url = "../../servlet/ekptg.report.php2.PYWSuratUlanganPTG?ID_LISTKJP="+list_id+"&ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+"&ID_ULASANTEKNIKAL="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPYWSuratUlanganDBKL(idFail,idUlasanTeknikal) {
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
		
	var url = "../../servlet/ekptg.report.php2.PYWSuratUlanganDBKL?ID_LISTKJP="+list_id+"&ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+"&ID_ULASANTEKNIKAL="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPYWSuratUlanganBPH(idFail,idUlasanTeknikal) {
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
		
	var url = "../../servlet/ekptg.report.php2.PYWSuratUlanganBPH?ID_LISTKJP="+list_id+"&ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+"&ID_ULASANTEKNIKAL="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPYWSuratMintaUlasanJH(idFail) {
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
		
	var url = "../../servlet/ekptg.report.php2.PYWSuratMintaUlasanJH?ID_LISTKJP="+list_id+"&ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPYWSuratUlanganJH(idFail,idUlasanTeknikal) {
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
		
	var url = "../../servlet/ekptg.report.php2.PYWSuratUlanganJH?ID_LISTKJP="+list_id+"&ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+"&ID_ULASANTEKNIKAL="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPYWSuratMintaLaporanTanah(idFail,idUlasanTeknikal) {
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
		
	var url = "../../servlet/ekptg.report.php2.PYWSuratMintaLaporanTanah?ID_LISTKJP="+list_id+"&ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+"&ID_ULASANTEKNIKAL="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function cetakPYWSuratUlanganLT(idFail,idUlasanTeknikal) {
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
		
	var url = "../../servlet/ekptg.report.php2.PYWSuratUlanganLT?ID_LISTKJP="+list_id+"&ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+"&ID_ULASANTEKNIKAL="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function cetakPYWSuratTawaran(idFail,idPermohonan) {
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
		
	var url = "../../servlet/ekptg.report.php2.PYWSuratTawaran?ID_LISTKJP="+list_id+"&ID_FAIL="+idFail+"&ID_PERMOHONAN="+idPermohonan+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPYWSuratTawaranDasarDKL(idFail,idPermohonan) {
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
		
	var url = "../../servlet/ekptg.report.php2.PYWSuratTawaranDasarDKL?ID_LISTKJP="+list_id+"&ID_FAIL="+idFail+"&ID_PERMOHONAN="+idPermohonan+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPYWSuratTawaranDasarLKL(idFail,idPermohonan) {
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
		
	var url = "../../servlet/ekptg.report.php2.PYWSuratTawaranDasarLKL?ID_LISTKJP="+list_id+"&ID_FAIL="+idFail+"&ID_PERMOHONAN="+idPermohonan+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPYWSuratTolakMesyuarat(idFail,idPermohonan) {
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
		
	var url = "../../servlet/ekptg.report.php2.PYWSuratTolakMesyuarat?ID_LISTKJP="+list_id+"&ID_FAIL="+idFail+"&ID_PERMOHONAN="+idPermohonan+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPYWSuratTolakKJP(idFail) {
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
		
	var url = "../../servlet/ekptg.report.php2.PYWSuratTolakKJP?ID_LISTKJP="+list_id+"&ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPYWSuratTarikbalikTawaran(idFail) {
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
		
	var url = "../../servlet/ekptg.report.php2.PYWSuratTarikbalikTawaran?ID_LISTKJP="+list_id+"&ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPYWSuratTandatanganPerjanjian(idFail,idPermohonan) {
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
		
	var url = "../../servlet/ekptg.report.php2.PYWSuratTandatanganPerjanjian?ID_LISTKJP="+list_id+"&ID_FAIL="+idFail+"&ID_PERMOHONAN="+idPermohonan+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPYWSuratMatiSetem(idFail) {
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
		
	var url = "../../servlet/ekptg.report.php2.PYWSuratMatiSetem?ID_LISTKJP="+list_id+"&ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function cetakPYWNotisTamatTempoh(idFail) {
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
		
	var url = "../../servlet/ekptg.report.php2.PYWSuratMatiSetem?ID_LISTKJP="+list_id+"&ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function cetakPYWSenaraiSemakIndividu(idFail) {
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
		
	var reportfile = "PYWSenaraiSemakIndividu";
	var params = new Array();
	params[0] = "output="+(document.${formName}.rFormat.value).toLowerCase();
	params[1] = "ID_FAIL="+document.${formName}.idFail.value;
	
	var e = document.${formName}.socPegawai;
	var namaPegawai = e.options[e.selectedIndex].text;
	
	params[2] = "ID_PEGAWAI="+document.${formName}.socPegawai.value;
	params[3] = "BIL_DOKUMEN="+document.${formName}.txtKandungan.value;
	params[4] = "NAMA_PEGAWAI="+namaPegawai;
// 	params[2] = "id_bulan="+document.${formName}.id_bulan.value;
// 	params[3] = "tarikh_mula="+document.${formName}.txdTarikhMula.value;
// 	params[4] = "tarikh_akhir="+document.${formName}.txdTarikhAkhir.value;
	
	printReport(reportfile,params);
}

function cetakPYWSenaraiSemakSyarikat(idFail) {
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
		
	var reportfile = "PYWSenaraiSemakSyarikat";
	var params = new Array();
	params[0] = "output="+(document.${formName}.rFormat.value).toLowerCase();
	params[1] = "ID_FAIL="+document.${formName}.idFail.value;
	var e = document.${formName}.socPegawai;
	var namaPegawai = e.options[e.selectedIndex].text;
	params[2] = "ID_PEGAWAI="+document.${formName}.socPegawai.value;
	params[3] = "BIL_DOKUMEN="+document.${formName}.txtKandungan.value;
	params[3] = "NAMA_PEGAWAI="+namaPegawai;
// 	params[2] = "id_bulan="+document.${formName}.id_bulan.value;
// 	params[3] = "tarikh_mula="+document.${formName}.txdTarikhMula.value;
// 	params[4] = "tarikh_akhir="+document.${formName}.txdTarikhAkhir.value;
	
	printReport(reportfile,params);
}
</script>

