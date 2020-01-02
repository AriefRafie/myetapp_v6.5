<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
-->
</style>
<input name="idFail" type="hidden" id="idFail" value="$idFail"/>
<input name="idUlasanTeknikal" type="hidden" id="idUlasanTeknikal" value="$idUlasanTeknikal"/>
<input name="idAduan" type="hidden" id="idAduan" value="$idAduan"/>
<input name="report" type="hidden" id="report" value="$report"/>
<input name="flagReport" type="hidden" id="flagReport" value="$flagReport"/>
<fieldset>
<legend><strong>CETAKAN SURAT/LAPORAN</strong></legend>
<table align="center" width="100%">
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="37%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
    <td width="61%">&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td valign="top">Kandungan</td>
    <td>:</td>
    <td><input type="text" name="txtBilDokumen" id="txtBilDokumen" size="3" onKeyUp="validateNumber(this,this.value);"/></td>
  </tr>
  <tr>
    <td width="1%"><span class="style1">*</span></td>
    <td valign="top">Nama Pegawai Yang Menandatangani</td>
    <td>:</td>
    <td><input type="text" name="txtNamaPegawai" id="txtNamaPegawai" size="43" />
      <div id="tarikhTerima_check" style="color:red" ></div></td>
  </tr>
  <tr>
    <td width="1%"><span class="style1">*</span></td>
    <td valign="top">No Telefon</td>
    <td>:</td>
    <td><input type="text" name="txtNoTelefon" id="txtNoTelefon" size="30" onKeyUp="validateNumber(this,this.value);"/></td>
  </tr>
  <tr>
    <td valign="top"><span class="style1">*</span></td>
    <td valign="top">No Faks</td>
    <td valign="top">:</td>
    <td><input type="text" name="txtNoFaks" id="txtNoFaks" size="30" onKeyUp="validateNumber(this,this.value);"/></td>
  </tr>
  <tr>
    <td valign="top">&nbsp;</td>
    <td valign="top">Email</td>
    <td valign="top">:</td>
    <td><input type="text" name="txtEmail" id="txtEmail" size="43"/>
      @kptg.gov.my</td>
  </tr>
  <tr>
    <td></td>
    <td></td>
    <td></td>
    <td><input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="cetak('$idFail','$idUlasanTeknikal','$report','$idAduan')"/>
    </td>
  </tr>
</table>
</fieldset>
<script>
function cetak(idFail,idUlasanTeknikal,namaRepot,idAduan) {
	var bil = document.${formName}.txtBilDokumen.value;
	var nama = document.${formName}.txtNamaPegawai.value;
	var noTelefon = document.${formName}.txtNoTelefon.value;
	var noFaks = document.${formName}.txtNoFaks.value;
	var email = document.${formName}.txtEmail.value;
	
	if(document.${formName}.txtNamaPegawai.value == ""){
		alert('Sila masukan Nama Pegawai.');
  		document.${formName}.txtNamaPegawai.focus(); 
		return; 
	}
	if(document.${formName}.txtNoTelefon.value == ""){
		alert('Sila masukan No Telefon.');
  		document.${formName}.txtNoTelefon.focus(); 
		return; 
	}
	if(document.${formName}.txtNoFaks.value == ""){
		alert('Sila masukan No Faks.');
  		document.${formName}.txtNoFaks.focus(); 
		return; 
	}	
	
	//surat akuan terima	
	if(namaRepot == 'SuratAkuanTerima'){
		var url = "../../servlet/ekptg.report.php.APBSuratAkuanPenerimaanPermohonanPasir?ID_FAIL="+idFail+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
	}
	//kertasRingkasan
	if(namaRepot == 'kertasRingkasan'){
		var url = "../../servlet/ekptg.report.php.APBKertasRingkasan?ID_FAIL="+idFail+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
	}
	//lulusDasar
	if(namaRepot == 'lulusDasar'){
		var url = "../../servlet/ekptg.report.php.APBLulusDasar?ID_FAIL="+idFail+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
	}
	//suratBayaranFeeLesen
	if(namaRepot == 'suratBayaranFeeLesen'){
		var url = "../../servlet/ekptg.report.php.APBMengemukakanBayaran?ID_FAIL="+idFail+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
	}
	//pengesahanPelanKawasan
	if(namaRepot == 'pengesahanPelanKawasan'){
		var url = "../../servlet/ekptg.report.php.APBPengesahanPelanKawasan?ID_ULASANTEKNIKAL="+idUlasanTeknikal+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
	}
	//suratJabatanTeknikal
	if(namaRepot == 'suratJabatanTeknikal'){
		var url = "../../servlet/ekptg.report.php.APBUlasanJT?ID_FAIL="+idFail+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
	}
	
	//syaratLesen
	if(namaRepot == 'syaratLesen'){
		var url = "../../servlet/ekptg.report.php.APBSyaratLesen?ID_FAIL="+idFail+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
	}
	//maklumanTindihKawasan
	if(namaRepot == 'maklumanTindihKawasan'){
		var url = "../../servlet/ekptg.report.php.APBTindihKawasan?ID_FAIL="+idFail+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
	}
	//suratPenolakan
	if(namaRepot == 'suratPenolakan'){
		var url = "../../servlet/ekptg.report.php.APBTolakPermohonan?ID_FAIL="+idFail+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
	}
	//memo
	if(namaRepot == 'memo'){
		var url = "../../servlet/ekptg.report.php.APBLulusMenteri?ID_FAIL="+idFail+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
	}
	//cetakSuratLulus
	if(namaRepot == 'cetakSuratLulus'){
		var url = "../../servlet/ekptg.report.php.APBLulusDasar?ID_FAIL="+idFail+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
	}	
	//cetakSuratPenamatanTamat
	if(namaRepot == 'cetakSuratPenamatan'){
		var url = "../../servlet/ekptg.report.php.APBSuratPenamatanLesen?ID_FAIL="+idFail+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
	}	
	//cetakSuratAmaran-- java x betol lg
	if(namaRepot == 'cetakSuratAmaran'){
		var url = "../../servlet/ekptg.report.php.APBSuratPenamatanLesen?ID_FAIL="+idFail+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
	}
	//SuratAkuanTerimaAduan
	if(namaRepot == 'SuratAkuanTerimaAduan'){
		var url = "../../servlet/ekptg.report.php.APBSuratAkuanPenerimaan_Aduan?ID_FAIL="+idFail+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
	}	
	//SuratAPMMAduan
	if(namaRepot == 'SuratAPMMAduan'){
		var url = "../../servlet/ekptg.report.php.APBSuratKepadaAPMM_Aduan?ID_FAIL="+idFail+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
	}
	//SuratKertasRingkasanAduan	
	if(namaRepot == 'SuratKertasRingkasanAduan'){
		var url = "../../servlet/ekptg.report.php.APBKertasRingkasan_Aduan?ID_FAIL="+idFail+"&ID_ADUAN="+idAduan+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
	}
	//SuratAmaranAduan	
	if(namaRepot == 'SuratAmaranAduan'){
		var url = "../../servlet/ekptg.report.php.APBSuratAmaran?ID_FAIL="+idFail+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
	}
	//SuratTamatLesenAduan	
	if(namaRepot == 'SuratTamatLesenAduan'){
		var url = "../../servlet/ekptg.report.php.APBSuratPenamatanLesen?ID_FAIL="+idFail+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
	}
	//SuratTunjukSebabAduan
	if(namaRepot == 'SuratTunjukSebabAduan'){
		var url = "../../servlet/ekptg.report.php.APBSuratTunjukSebab?ID_FAIL="+idFail+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
	}
	
	var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
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
