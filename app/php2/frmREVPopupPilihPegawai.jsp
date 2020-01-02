<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
-->
</style>
<input name="idHasil" type="hidden" id="idHasil" value="$idHasil"/>
<input name="idBayaran" type="hidden" id="idBayaran" value="$idBayaran"/>
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
    <td>#if($report=="SuratCekTidakSah")
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="cetakSuratCekTidakSah('$idBayaran')"/>
      #end
      #if($report=="SuratCekTamatTempoh")
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="cetakSuratCekTamatTempoh('$idBayaran')"/>
      #end
      #if($report=="SuratTunggakanSewa")
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="cetakSuratTunggakanSewa('$idHasil')"/>
      #end
      #if($report=="SuratTunggakanSewaBerjadual")
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="cetakSuratTunggakanSewaBerjadual('$idHasil')"/>
      #end </td>
  </tr>
</table>
</fieldset>
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
function cetakSuratCekTidakSah(idBayaran) {
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
	
	var url = "../../servlet/ekptg.report.php.REVSuratCekTidakLaku?ID_BAYARAN="+idBayaran+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratCekTamatTempoh(idBayaran) {
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
	
	var url = "../../servlet/ekptg.report.php.REVSuratCekTamatTempoh?ID_BAYARAN="+idBayaran+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratTunggakanSewa(idHasil) {
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
	var url = "../../servlet/ekptg.report.php.REVSuratPembayaranTunggakanSewa?ID_HASIL="+idHasil+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratTunggakanSewaBerjadual(idHasil) {
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
	var url = "../../servlet/ekptg.report.php.REVSuratTunggakanPeringatanPertama?ID_HASIL="+idHasil+"&BIL_DOKUMEN="+bil+"&NAMA_PEGAWAI="+nama+"&NO_TEL="+noTelefon+"&NO_FAKS="+noFaks+"&EMAIL="+email;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

</script>
