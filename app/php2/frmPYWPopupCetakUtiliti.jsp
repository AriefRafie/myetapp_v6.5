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
          <td ><span class="style1">*</span></td>
          <td>No Fail SPHP</td>
          <td>:
          <input name="txtNoFailSPHP" type="text" id="txtNoFailSPHP" value="$txtNoFailSPHP" size="40" maxlength="40"/></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>No Fail Pemohon</td>
          <td>:
          <input name="txtNoFailPemohon" type="text" id="txtNoFailPemohon" value="$txtNoFailPemohon" size="40" maxlength="40"/></td>
        </tr>
        <tr>
          <td><span class="style1">*</span></td>
          <td>Kandungan</td>
          <td>:
            <input name="txtKandungan" type="text" onBlur="validateNumber(this,this.value);" value="$txtKandungan" size="2" maxlength="2"></td>
        </tr>
        <tr>
          <td ><span class="style1">*</span></td>
          <td>Nama Pemohon</td>
          <td>:
          <input name="txtNamaPemohon" type="text" id="txtNamaPemohon" value="$txtNamaPemohon" size="40" maxlength="40"/></td>
        </tr>
        <tr>
          <td ><span class="style1">*</span></td>
          <td>Alamat Pemohon</td>
          <td>:
          <input name="txtAlamat1" type="text" id="txtAlamat1" value="$txtAlamat1" size="40" maxlength="40"/></td>
        </tr>
        <tr>
          <td >&nbsp;</td>
          <td>&nbsp;</td>
          <td>:
          <input name="txtAlamat2" type="text" id="txtAlamat2" value="$txtAlamat2" size="40" maxlength="40"/></td>
        </tr>
        <tr>
          <td >&nbsp;</td>
          <td>&nbsp;</td>
          <td>:
          <input name="txtAlamat3" type="text" id="txtAlamat3" value="$txtAlamat3" size="40" maxlength="40"/></td>
        </tr>
        <tr>
          <td ><span class="style1">*</span></td>
          <td>Poskod</td>
          <td>:
          <input name="txtPoskod" type="text" id="txtPoskod" value="$txtPoskod" size="5" maxlength="5"/></td>
        </tr>
        <tr>
          <td ><span class="style1">*</span></td>
          <td>Negeri</td>
          <td>: $selectNegeri</td>
        </tr>
        
        <tr>
          <td ><span class="style1">*</span></td>
          <td>Bandar</td>
          <td>: $selectBandar</td>
        </tr>
        <tr>
          <td valign="top"><span class="style1">*</span></td>
          <td valign="top">Tajuk Surat</td>
          <td valign="top">:          
          <textarea name="txtTajukSurat" cols="50" rows="4" id="txtTajukSurat" >$txtTajukSurat</textarea></td>
        </tr>
        <tr>
          <td ><span class="style1">*</span></td>
          <td>Pegawai yang Menandatangani</td>
          <td>: $selectPegawai</td>
        </tr>
        <tr>
          <td >&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td >&nbsp;</td>
          <td>&nbsp;</td>
          <td>
            #if($report == 'PYWSuratTolakTanahBukanMilikPTP')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakPYWSuratTolakTanahBukanMilikPTP()">
            #end		  </td>
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
function doChangeNegeri() {
	doAjaxCall${formName}("doChangeNegeri");
}
</script>
<script>
function cetakPYWSuratTolakTanahBukanMilikPTP() {
	if(document.${formName}.txtNoFailSPHP.value == ""){
		alert('Sila masukkan No Fail SPHP.');
  		document.${formName}.txtNoFailSPHP.focus();
		return; 
	}	
	if(document.${formName}.txtKandungan.value == ""){
		alert('Sila masukkan Kandungan.');
  		document.${formName}.txtKandungan.focus();
		return; 
	}
	if(document.${formName}.txtNamaPemohon.value == ""){
		alert('Sila masukkan Nama Pemohon.');
  		document.${formName}.txtNamaPemohon.focus();
		return; 
	}	
	if(document.${formName}.txtAlamat1.value == ""){
		alert('Sila masukkan Alamat Pemohon.');
  		document.${formName}.txtAlamat1.focus();
		return; 
	}
	if(document.${formName}.txtPoskod.value == ""){
		alert('Sila masukkan Poskod Pemohon.');
  		document.${formName}.txtPoskod.focus();
		return; 
	}	
	if(document.${formName}.socNegeri.value == ""){
		alert('Sila pilih Negeri Pemohon.');
  		document.${formName}.socNegeri.focus(); 
		return; 
	}
	if(document.${formName}.socBandar.value == ""){
		alert('Sila pilih Bandar Pemohon.');
  		document.${formName}.socBandar.focus(); 
		return; 
	}
	if(document.${formName}.txtTajukSurat.value == ""){
		alert('Sila masukkan Tajuk Surat.');
  		document.${formName}.txtTajukSurat.focus();
		return; 
	}	
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
		
	var url = "../../servlet/ekptg.report.php2.PYWSuratTolakTanahBukanMilikPTP?ID_PEGAWAI="+document.${formName}.socPegawai.value+"&ID_NEGERI="+document.${formName}.socNegeri.value+"&ID_BANDAR="+document.${formName}.socBandar.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+"&NO_FAIL_SPHP="+document.${formName}.txtNoFailSPHP.value+"&NO_FAIL_RUJUKAN="+document.${formName}.txtNoFailPemohon.value+"&NAMA_PEMOHON="+document.${formName}.txtNamaPemohon.value+"&ALAMAT1_PEMOHON="+document.${formName}.txtAlamat1.value+"&ALAMAT2_PEMOHON="+document.${formName}.txtAlamat2.value+"&ALAMAT3_PEMOHON="+document.${formName}.txtAlamat3.value+"&POSKOD_PEMOHON="+document.${formName}.txtPoskod.value+"&TAJUK_SURAT="+document.${formName}.txtTajukSurat.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>