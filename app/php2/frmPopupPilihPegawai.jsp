<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
-->
</style>
<input name="idFail" type="hidden" id="idFail" value="$idFail"/>
<input name="idUlasanTeknikal" type="hidden" id="idUlasanTeknikal" value="$idUlasanTeknikal"/>
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
    <td><input type="text" name="txtNamaPegawai" id="txtNamaPegawai" size="43" onBlur="this.value=this.value.toUpperCase();"/>
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
    <td><input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="cetak('$idFail','$idUlasanTeknikal','$report')"/>
    </td>
  </tr>
</table>
</fieldset>
<script>
function cetak(idFail,idUlasanTeknikal,namaRepot) {
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
	
	//surat akuan terima LKL DAN DKL	
	if(namaRepot == 'SuratAkuanTerima'){
		var url = "../../servlet/ekptg.report.php.LKLSuratAkuanPenerimaan?ID_FAIL="+idFail+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
	}
	// Surat Tolak Ada Penyewa LKL DAN DKL
	if(namaRepot == 'SuratTolakAdaPenyewa'){
		var url = "../../servlet/ekptg.report.php.LKLSuratPenolakanPermohonanSewaAdaPenyewa?ID_FAIL="+idFail+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
	}
	// Surat Tolak Tanah Bukan PTP LKL DAN DKL
	if(namaRepot == 'SuratTolakTanahBukanPTP'){
		var url = "../../servlet/ekptg.report.php.LKLSuratPenolakanPermohonanSewaBukanMilikPTP?ID_FAIL="+idFail+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
	}	
	//SuratTolakDBKL
	if(namaRepot == 'SuratTolakDBKL'){
		var url = "../../servlet/ekptg.report.php.LKLSuratPenolakanPermohonanSewaDBKL?ID_FAIL="+idFail+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
	}    
	//SuratTolakKJP
	if(namaRepot == 'SuratTolakKJP'){
		var url = "../../servlet/ekptg.report.php.LKLSuratPenolakanPermohonanSewaKJP?ID_FAIL="+idFail+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
	}  
	//SuratTolakMesyuarat
	if(namaRepot == 'SuratTolakMesyuarat'){
		var url = "../../servlet/ekptg.report.php.LKLSuratPenolakanPermohonanSewaMesyuaratTolak?ID_FAIL="+idFail+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
	} 	
	//derafPerjanjianSewaLKL
	if(namaRepot == 'derafPerjanjianSewaLKL'){
		var url = "../../servlet/ekptg.report.php.DerafPerjanjianSewa?ID_FAIL="+idFail+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
	}	
	//derafPerjanjianSewaDKL
	if(namaRepot == 'derafPerjanjianSewaDKL'){
		var url = "../../servlet/ekptg.report.php.DKLDerafPerjanjianSewa?ID_FAIL="+idFail+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
	}	
	//SuratTawaranPenyewaanLKL
	if(namaRepot == 'SuratTawaranPenyewaanLKL'){
		var url = "../../servlet/ekptg.report.php.LKLSuratTawaranPenyewaan?ID_FAIL="+idFail+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
	}	
	//SuratUJPPHNilaianSewa
	if(namaRepot == 'SuratUJPPHNilaianSewa'){
		var url = "../../servlet/ekptg.report.php.LKLSuratMintaUlasanJPPHNilaianSewa?ID_ULASANTEKNIKAL="+idUlasanTeknikal+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
	}	
	//SuratMintaUlasan
	if(namaRepot == 'SuratMintaUlasan'){
		var url = "../../servlet/ekptg.report.php.LKLSuratMintaUlasanKJPHasratPenyewaan?ID_ULASANTEKNIKAL="+idUlasanTeknikal+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
	}	
	//SuratMatiSetem
	if(namaRepot == 'SuratMatiSetem'){
		var url = "../../servlet/ekptg.report.php.SuratMatiSetem?ID_FAIL="+idFail+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
	}	
	//SuratPanggilPenyewaTandaTanganPerjanjian
	if(namaRepot == 'SuratPanggilPenyewaTandaTanganPerjanjian'){
		var url = "../../servlet/ekptg.report.php.LKLSuratPanggilPenyewaTandatangan?ID_FAIL="+idFail+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
	}
	//TarikbalikKelulusanKeranaTempohMaklumbalasBerakhir
	if(namaRepot == 'TarikbalikKelulusanKeranaTempohMaklumbalasBerakhir'){
		var url = "../../servlet/ekptg.report.php.LKLSuratPembatalanKelulusanSewaSelepasTarikh?ID_FAIL="+idFail+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
	}	
	//TarikbalikKelulusanKeranaTiadaMaklumbalas
	if(namaRepot == 'TarikbalikKelulusanKeranaTiadaMaklumbalas'){
		var url = "../../servlet/ekptg.report.php.LKLSuratPembatalanKelulusanSewa?ID_FAIL="+idFail+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
	}		
	//suratIringanLKL
	if(namaRepot == 'suratIringanLKL'){
		var url = "../../servlet/ekptg.report.php.SuratIringanKepadaPenyewaPerjanjianSewa?ID_FAIL="+idFail+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
	}	
	//suratPengarahLKL
	if(namaRepot == 'suratPengarahLKL'){
		var url = "../../servlet/ekptg.report.php.SuratMaklumanSewakKepadaKJP_Hasil_JKPTGN?ID_FAIL="+idFail+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
	}
	//suratTawaranPenyewaanLKL
	if(namaRepot == 'suratTawaranPenyewaanLKL'){
		var url = "../../servlet/ekptg.report.php.LKLSuratTawaranPenyewaan?ID_FAIL="+idFail+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
	}
	//suratTawaranPenyewaanDKL
	if(namaRepot == 'suratTawaranPenyewaanDKL'){
		var url = "../../servlet/ekptg.report.php.DKLSuratTawaranPenyewaan?ID_FAIL="+idFail+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
	}	
	//SuratMUDBKL
	if(namaRepot == 'SuratMUDBKL'){
		var url = "../../servlet/ekptg.report.php.DKLSuratMintaUlasanDBKL?ID_ULASANTEKNIKAL="+idUlasanTeknikal+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
	}	
	//LKLNotisTamatKJP
	if(namaRepot == 'LKLNotisTamatKJP'){
		var url = "../../servlet/ekptg.report.php.LKLNotisPenamatanKJP?ID_FAIL="+idFail+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
	}	
	//DKLNotisTamatKJP
	if(namaRepot == 'DKLNotisTamatKJP'){
		var url = "../../servlet/ekptg.report.php.DKLNotisPenamatanKJP?ID_FAIL="+idFail+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
	}		
	//LKLNotisTunggakanSewa
	if(namaRepot == 'LKLNotisTunggakanSewa'){
		var url = "../../servlet/ekptg.report.php.LKLNotisTunggakanSewa?ID_FAIL="+idFail+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
	}	
	//DKLNotisTamatKJP
	if(namaRepot == 'DKLNotisTamatKJP'){
		var url = "../../servlet/ekptg.report.php.SuratPenamatanSewaDanKosongTapak?ID_FAIL="+idFail+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
	}	
	//DKLNotisTunggakanSewa
	if(namaRepot == 'DKLNotisTunggakanSewa'){
		var url = "../../servlet/ekptg.report.php.LKLNotisTunggakanSewa?ID_FAIL="+idFail+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
	}		
	//SuratLawatanTapakKL
	if(namaRepot == 'SuratLawatanTapakKL'){
		var url = "../../servlet/ekptg.report.php.LKLSuratLawatanTapak?ID_ULASANTEKNIKAL="+idUlasanTeknikal+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
	}		
	//SuratMU1JPPH
	if(namaRepot == 'SuratMU1JPPH'){
		var url = "../../servlet/ekptg.report.php.LKLSuratUlanganJPPH?ID_ULASANTEKNIKAL="+idUlasanTeknikal+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
	}		
	//SuratMU1KJP
	if(namaRepot == 'SuratMU1KJP'){
		var url = "../../servlet/ekptg.report.php.LKLSuratUlanganKJPJABHUTAN?ID_ULASANTEKNIKAL="+idUlasanTeknikal+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
	}		
	//LaporanTanahLKL
	if(namaRepot == 'LaporanTanahLKL'){
		var url = "../../servlet/ekptg.report.php.LKLLaporanHakmilikTanah?ID_FAIL="+idFail+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
	}	
	//SuratMUJabHutan
	if(namaRepot == 'SuratMUJabHutan'){
		var url = "../../servlet/ekptg.report.php.LKLSuratMintaUlasanJabatanHutan?ID_ULASANTEKNIKAL="+idUlasanTeknikal+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
	}	
	//LKLSuratKawalTapak
	if(namaRepot == 'LKLSuratKawalTapak'){
		var url = "../../servlet/ekptg.report.php.SuratKepadaKJPBagiMengawalTapak?ID_FAIL="+idFail+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
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
