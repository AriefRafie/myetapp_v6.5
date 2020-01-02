<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
-->
</style>
<p>
  <input name="idfail" type="hidden" id="idfail" value="$idfail"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idUlasanTeknikal" type="hidden" id="idUlasanTeknikal" value="$idUlasanTeknikal"/>
  <input name="idTanah" type="hidden" id="idTanah" value="$idTanah"/>
  <input name="idAduan" type="hidden" id="idAduan" value="$idAduan"/>
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
          <td >&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td >&nbsp;</td>
          <td>&nbsp;</td>
          <td><!-- START CETAK APB -->
            #if($report == 'suratAkuanTerima')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakSuratAkuanTerima('$idFail')">
            #end            
            #if($report == 'pengesahanPelanKawasan')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakPengesahanPelanKawasan('$idFail','$idUlasanTeknikal')">
            #end
            #if($report == 'ulanganPengesahanPelanKawasan')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakUlanganPengesahanPelanKawasan('$idFail','$idUlasanTeknikal')">
            #end
            #if($report == 'suratMintaUlasanJabatanTeknikal')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakSuratMintaUlasanJabatanTeknikal('$idFail','$idUlasanTeknikal')">
            #end
            #if($report == 'ulanganSuratMintaUlasanJAS')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakUlanganSuratMintaUlasanJAS('$idFail','$idUlasanTeknikal')">
            #end
            #if($report == 'ulanganSuratMintaUlasanJMG')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakUlanganSuratMintaUlasanJMG('$idFail','$idUlasanTeknikal')">
            #end
            #if($report == 'ulanganSuratMintaUlasanDOF')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakUlanganSuratMintaUlasanDOF('$idFail','$idUlasanTeknikal')">
            #end
            #if($report == 'ulanganSuratMintaUlasanJLM')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakUlanganSuratMintaUlasanJLM('$idFail','$idUlasanTeknikal')">
            #end
            #if($report == 'ulanganSuratMintaUlasanPHN')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakUlanganSuratMintaUlasanPHN('$idFail','$idUlasanTeknikal')">
            #end
            #if($report == 'ulanganSuratMintaUlasanJPS')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakUlanganSuratMintaUlasanJPS('$idFail','$idUlasanTeknikal')">
            #end
            #if($report == 'ulanganSuratMintaUlasanPTG')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakUlanganSuratMintaUlasanPTG('$idFail','$idUlasanTeknikal')">
            #end
            #if($report == 'suratKelulusanDasar')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakSuratKelulusanDasar('$idFail','$idPermohonan')">
            #end
     
            
            #if($report == 'maklumanTindihKawasan')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakMaklumanTindihKawasan('$idFail')">
            #end
            #if($report == 'suratPenolakan')
            <input type="button" name="cmdCetak2" id="cmdCetak2" value="Cetak" onClick="javascript:cetakSuratPenolakan('$idFail')" />
            #end
            #if($report == 'memo')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakMemo('$idFail')"/>
            #end
            
            #if($report == 'cetakSuratHantarLesen')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakSuratHantarLesen('$idFail')">
            #end
            #if($report == 'cetakSuratPenamatan')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakSuratPenamatan('$idFail')">
            #end
            #if($report == 'SuratAkuanTerimaAduan')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakAkuanTerimaAduan('$idFail')">
            #end
            #if($report == 'SuratAPMMAduan')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakSuratAPMMAduan('$idFail')">
            #end
            #if($report == 'SuratKertasRingkasanAduan')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakKertasRingkasanAduan('$idFail')">
            #end
            #if($report == 'SuratAmaranAduan')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakSuratAmaranAduan('$idFail')">
            #end
            #if($report == 'SuratTamatLesenAduan')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakSuratTamatLesenAduan('$idFail')">
            #end
            #if($report == 'SuratTunjukSebabAduan')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakSuratTunjukSebabAduan('$idFail')">
            #end            
            #if($report == 'suratBayaranFeeLesen')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakSuratBayaranFeeLesen('$idFail','$idPermohonan')">
            #end
            <!-- END CETAK APB -->
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
<!-- START CETAK APB -->
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
		
	var url = "../../servlet/ekptg.report.php2.APBSuratAkuanPenerimaan?ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function cetakPengesahanPelanKawasan(idFail,idUlasanTeknikal) {
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
		
	var url = "../../servlet/ekptg.report.php2.APBPengesahanPelanKawasan?ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+"&ID_ULASANTEKNIKAL="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function cetakUlanganPengesahanPelanKawasan(idFail,idUlasanTeknikal) {
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
		
	var url = "../../servlet/ekptg.report.php2.APBUlanganPengesahanPelanKawasan?ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+"&ID_ULASANTEKNIKAL="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function cetakSuratMintaUlasanJabatanTeknikal(idFail,idUlasanTeknikal) {
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
		
	var url = "../../servlet/ekptg.report.php2.APBUlasanJT?ID_FAIL="+idFail+"&ID_ULASANTEKNIKAL="+idUlasanTeknikal+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function cetakUlanganSuratMintaUlasanJAS(idFail,idUlasanTeknikal) {
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
		
	var url = "../../servlet/ekptg.report.php2.APBUlanganSuratMintaUlasanJAS?ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+"&ID_ULASANTEKNIKAL="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function cetakUlanganSuratMintaUlasanJMG(idFail,idUlasanTeknikal) {
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
		
	var url = "../../servlet/ekptg.report.php2.APBUlanganSuratMintaUlasanJMG?ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+"&ID_ULASANTEKNIKAL="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function cetakUlanganSuratMintaUlasanDOF(idFail,idUlasanTeknikal) {
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
		
	var url = "../../servlet/ekptg.report.php2.APBUlanganSuratMintaUlasanDOF?ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+"&ID_ULASANTEKNIKAL="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function cetakUlanganSuratMintaUlasanJLM(idFail,idUlasanTeknikal) {
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
		
	var url = "../../servlet/ekptg.report.php2.APBUlanganSuratMintaUlasanJLM?ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+"&ID_ULASANTEKNIKAL="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function cetakUlanganSuratMintaUlasanPHN(idFail,idUlasanTeknikal) {
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
		
	var url = "../../servlet/ekptg.report.php2.APBUlanganSuratMintaUlasanPHN?ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+"&ID_ULASANTEKNIKAL="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function cetakUlanganSuratMintaUlasanJPS(idFail,idUlasanTeknikal) {
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
		
	var url = "../../servlet/ekptg.report.php2.APBUlanganSuratMintaUlasanJPS?ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+"&ID_ULASANTEKNIKAL="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function cetakUlanganSuratMintaUlasanPTG(idFail,idUlasanTeknikal) {
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
		
	var url = "../../servlet/ekptg.report.php2.APBUlanganSuratMintaUlasanPTG?ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+"&ID_ULASANTEKNIKAL="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function cetakSuratKelulusanDasar(idFail,idPermohonan) {
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
		
	var url = "../../servlet/ekptg.report.php2.APBSuratKelulusanDasar?ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+"&ID_PERMOHONAN="+idPermohonan;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}




function cetakMaklumanTindihKawasan(idFail) {
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
		
	var url = "../../servlet/ekptg.report.php2.APBTindihKawasan?ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratPenolakan(idFail) {
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
		
	var url = "../../servlet/ekptg.report.php2.APBTolakPermohonan?ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakMemo(idFail) {
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
		
	var url = "../../servlet/ekptg.report.php.APBLulusMenteri?ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratHantarLesen(idFail) {
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
		
	var url = "../../servlet/ekptg.report.php2.APBSuratHantarLesen?ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratPenamatan(idFail) {
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
		
	var url = "../../servlet/ekptg.report.php.APBSuratPenamatanLesen?ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakAkuanTerimaAduan(idFail) {
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
		
	var url = "../../servlet/ekptg.report.php.APBSuratAkuanPenerimaan_Aduan?ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratAPMMAduan(idFail) {
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
		
	var url = "../../servlet/ekptg.report.php.APBSuratKepadaAPMM_Aduan?ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakKertasRingkasanAduan(idFail) {
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
		
	var url = "../../servlet/ekptg.report.php.APBKertasRingkasan_Aduan?ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratAmaranAduan(idFail) {
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
		
	var url = "../../servlet/ekptg.report.php.APBSuratAmaran?ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratTamatLesenAduan(idFail) {
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
		
	var url = "../../servlet/ekptg.report.php.APBSuratPenamatanLesen?ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratTunjukSebabAduan(idFail) {
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
		
	var url = "../../servlet/ekptg.report.php.APBSuratTunjukSebab?ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function cetakSuratBayaranFeeLesen(idFail,idPermohonan) {
 
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
		
	var url = "../../servlet/ekptg.report.php2.APBMengemukakanBayaran?ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+"&ID_PERMOHONAN="+idPermohonan;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>
<!-- END CETAK APB-->
