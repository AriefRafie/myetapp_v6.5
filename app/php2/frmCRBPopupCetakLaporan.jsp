<script type="text/javascript" src="../../library/js/report.js" ></script>
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
   <input name="idTanah" type="hidden" id="idTanah" value="$idTanah"/>
  <input name="report" type="hidden" id="report" value="$report"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idMesyuarat" type="hidden" id="idMesyuarat" value="$idMesyuarat"/>
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
          <td>&nbsp;</td>
          <td>Kandungan</td>
          <td>:
            <input name="txtKandungan" type="text" size="2" maxlength="2" onblur="validateNumber(this,this.value);"></td>
        </tr>
        <tr>
          <td ><span class="style1">*</span></td>
          <td>Pegawai yang Menandatangani</td>
          <td>: $selectPegawai</td>
        </tr>
        <tr>
<!--           <td ><span class="style1"></span></td> -->
<!--           <td>Salinan Kepada</td> -->
<!--           <td>: $selectNamaPejabat</td> -->
			<td colspan="4">
				<fieldset>
					<legend><strong>SALINAN KEPADA</strong></legend>
					<table width="100%" border="0" cellspacing="2" cellpadding="2">
						<tr>
				          <td>&nbsp;</td>
				          <td>1) PTG</td>
				          <td>: $selectPejabatPTG</td>
				        </tr>
						<tr>
				          <td>&nbsp;</td>
				          <td>2) PTD</td>
				          <td>: $selectPejabatPTD</td>
				        </tr>
						<tr>
				          <td>&nbsp;</td>
				          <td>3) PBT</td>
				          <td>: $selectPejabatPBT</td>
				        </tr>
						<tr>
				          <td>&nbsp;</td>
				          <td>4) JKPTG</td>
				          <td>: $selectPejabatJKPTG</td>
				        </tr>
						<tr>
				          <td>&nbsp;</td>
				          <td>5) KJP</td>
				          <td>: $selectKementerian</td>
				        </tr>
						<tr>
				          <td>&nbsp;</td>
				          <td>6) Lain-lain</td>
				          <td>: $selectAgensi</td>
				        </tr>
				        <tr>
				          <td>&nbsp;</td>
				          <td>7) Lain-lain</td>
				          <td>: $selectAgensi1</td>
				        </tr>
				        <tr>
				          <td>&nbsp;</td>
				          <td>8) Lain-lain</td>
				          <td>: $selectAgensi2</td>
				        </tr>			        				        				        				        				        
					</table>
				</fieldset>
			</td>
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
            #if($report == 'suratAkuanTerima')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratAkuanTerima('$idFail')">
            #end
            #if($report == 'suratMUKJP')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratMintaUlasanKJP('$idUlasanTeknikal')">
            #end            
            #if($report == 'suratUlanganKJP')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratUlanganKJP('$idUlasanTeknikal')">
            #end
            #if($report == 'suratMULT')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratMintaLaporanTanah('$idUlasanTeknikal')">
            #end     
            #if($report == 'suratUlanganLT')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratUlanganLT('$idUlasanTeknikal')">
            #end  
            #if($report == 'suratTindakanPBT')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratTindakanPBT('$idUlasanTeknikal')">
            #end    
            #if($report == 'suratUlanganPBT')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratUlanganPBT('$idUlasanTeknikal')">
            #end   
            #if($report == 'notis425')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakNotis425('$idUlasanTeknikal')">
            #end  
            #if($report == 'cetakSuratPanggilMesy')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratPanggilMesy('$idFail','$idMesyuarat')">
            #end  
            #if($report == 'notisKepadaPenceroboh')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakNotisKepadaPenceroboh('$idFail')">
            #end      
            #if($report == 'notisKepadaPentadbirTanah')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakNotisKepadaPentabirTanah('$idUlasanTeknikal')">
            #end  
            #if($report == 'suratTindakanPenguatkuasaan')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratTindakanPenguatkuasaan('$idUlasanTeknikal')">
            #end  
             #if($report == 'suratTapak')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratTapak2('$idUlasanTeknikal')">
            #end     
            #if($report == 'suratKJPPagarTanah')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratKJPPagarTanah('$idFail')">
            #end
            #if($report == 'suratPanggilanOperasi')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratPanggilanOperasi('$idFail')">
            #end
            #if($report == 'suratPenghargaan')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratPenghargaan('$idFail')">
            #end  
             #if($report == 'suratPanggilanMesyuarat')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakPanggilMesyuarat('$idFail')">
            #end               
          </td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
</table>

<input type="hidden" id="paramUserLogin" name="paramUserLogin" value='$!{session.getAttribute("_portal_login")}' >
<input type="hidden" id="rFormat" name="rFormat" value='$!{session.getAttribute("rFormat")}'>

<script>

function cetakSuratTapak2(idUlasanTeknikal) {

	var reportfile = "CRBSuratMemohonKJPKawalTapak";
	var params = new Array();
	params[0] = "output="+(document.${formName}.rFormat.value).toLowerCase();
	params[1] = "ID_ULASANTEKNIKAL="+idUlasanTeknikal;
	params[2] = "ID_PEGAWAI="+document.${formName}.socPegawai.value;
	params[3] = "ID_PTG="+document.${formName}.socPegawaiPTG.value;
	params[4] = "ID_PTD="+document.${formName}.socPegawaiPTD.value;
	params[5] = "ID_PBT="+document.${formName}.socPegawaiPBT.value;
	params[6] = "ID_PEJABATJKPTG="+document.${formName}.socPegawaiJKPTG.value;
	params[7] = "ID_AGENSI="+document.${formName}.socPegawaiKJP.value;
	params[8] = "ID_LAINLAIN="+document.${formName}.socPegawaiLainlain.value;
	params[9] = "ID_AGENSI1="+document.${formName}.socAgensi1.value;
	params[10] = "ID_AGENSI2="+document.${formName}.socAgensi2.value;
	params[11] = "BIL_DOKUMEN="+document.${formName}.txtKandungan.value;
	
	printReport(reportfile,params);
}

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

<!-- START CETAK PELEPASAN -->
<script>

function cetakPanggilMesyuarat(idFail) {

	var reportfile = "CRBSuratPohonPanggilMesyuaratJKPTGNegeri";
	var params = new Array();
	params[0] = "output="+(document.${formName}.rFormat.value).toLowerCase();
	params[1] = "ID_FAIL="+idFail;
	params[2] = "ID_MESYUARAT="+document.${formName}.idMesyuarat.value;
	params[3] = "ID_PTG="+document.${formName}.socPejabatPTG.value;
	params[4] = "ID_PTD="+document.${formName}.socPejabatPTD.value;
	params[5] = "ID_PBT="+document.${formName}.socPejabatPBT.value;
	params[6] = "ID_JKPTG="+document.${formName}.socPejabatJKPTG.value;
	params[7] = "ID_KEMENTERIAN="+document.${formName}.socKementerian.value;
	params[8] = "ID_AGENSI="+document.${formName}.socAgensi.value;
	params[9] = "ID_AGENSI1="+document.${formName}.socAgensi1.value;
	params[10] = "ID_AGENSI2="+document.${formName}.socAgensi2.value;
	params[11] = "BIL_DOKUMEN="+document.${formName}.txtKandungan.value;
	
	printReport(reportfile,params);
	
}

function cetakSuratAkuanTerima(idFail) {
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	var item = "&ID_PTG="+document.${formName}.socPejabatPTG.value+"&ID_PTD="+document.${formName}.socPejabatPTD.value+"&ID_PBT="+document.${formName}.socPejabatPBT.value+"&ID_JKPTG="+document.${formName}.socPejabatJKPTG.value+"&ID_KEMENTERIAN="+document.${formName}.socKementerian.value+"&ID_AGENSI="+document.${formName}.socAgensi.value+"&ID_AGENSI1="+document.${formName}.socAgensi1.value+"&ID_AGENSI2="+document.${formName}.socAgensi2.value;	
	var url = "../../servlet/ekptg.report.php2.CBRSuratAkuanTerima?ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+item;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratMintaUlasanKJP(idUlasanTeknikal) {
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	var item = "&ID_PTG="+document.${formName}.socPejabatPTG.value+"&ID_PTD="+document.${formName}.socPejabatPTD.value+"&ID_PBT="+document.${formName}.socPejabatPBT.value+"&ID_JKPTG="+document.${formName}.socPejabatJKPTG.value+"&ID_KEMENTERIAN="+document.${formName}.socKementerian.value+"&ID_AGENSI="+document.${formName}.socAgensi.value+"&ID_AGENSI1="+document.${formName}.socAgensi1.value+"&ID_AGENSI2="+document.${formName}.socAgensi2.value;	
	var url = "../../servlet/ekptg.report.php2.CRBSuratMintaUlasanKJP?ID_ULASANTEKNIKAL="+idUlasanTeknikal+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+item;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratUlanganKJP(idUlasanTeknikal) {
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	var item = "&ID_PTG="+document.${formName}.socPejabatPTG.value+"&ID_PTD="+document.${formName}.socPejabatPTD.value+"&ID_PBT="+document.${formName}.socPejabatPBT.value+"&ID_JKPTG="+document.${formName}.socPejabatJKPTG.value+"&ID_KEMENTERIAN="+document.${formName}.socKementerian.value+"&ID_AGENSI="+document.${formName}.socAgensi.value+"&ID_AGENSI1="+document.${formName}.socAgensi1.value+"&ID_AGENSI2="+document.${formName}.socAgensi2.value;	
	var url = "../../servlet/ekptg.report.php2.CRBSuratUlanganKJP?ID_ULASANTEKNIKAL="+idUlasanTeknikal+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+item;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratMintaLaporanTanah(idUlasanTeknikal) {
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	var item = "&ID_PTG="+document.${formName}.socPejabatPTG.value+"&ID_PTD="+document.${formName}.socPejabatPTD.value+"&ID_PBT="+document.${formName}.socPejabatPBT.value+"&ID_JKPTG="+document.${formName}.socPejabatJKPTG.value+"&ID_KEMENTERIAN="+document.${formName}.socKementerian.value+"&ID_AGENSI="+document.${formName}.socAgensi.value+"&ID_AGENSI1="+document.${formName}.socAgensi1.value+"&ID_AGENSI2="+document.${formName}.socAgensi2.value;	
	var url = "../../servlet/ekptg.report.php2.CRBSuratMintaLaporanTanah?ID_ULASANTEKNIKAL="+idUlasanTeknikal+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+item;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratUlanganLT(idUlasanTeknikal) {
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	var item = "&ID_PTG="+document.${formName}.socPejabatPTG.value+"&ID_PTD="+document.${formName}.socPejabatPTD.value+"&ID_PBT="+document.${formName}.socPejabatPBT.value+"&ID_JKPTG="+document.${formName}.socPejabatJKPTG.value+"&ID_KEMENTERIAN="+document.${formName}.socKementerian.value+"&ID_AGENSI="+document.${formName}.socAgensi.value+"&ID_AGENSI1="+document.${formName}.socAgensi1.value+"&ID_AGENSI2="+document.${formName}.socAgensi2.value;	
	var url = "../../servlet/ekptg.report.php2.CRBSuratUlanganLT?ID_ULASANTEKNIKAL="+idUlasanTeknikal+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+item;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratTindakanPBT(idUlasanTeknikal) {
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	var item = "&ID_PTG="+document.${formName}.socPejabatPTG.value+"&ID_PTD="+document.${formName}.socPejabatPTD.value+"&ID_PBT="+document.${formName}.socPejabatPBT.value+"&ID_JKPTG="+document.${formName}.socPejabatJKPTG.value+"&ID_KEMENTERIAN="+document.${formName}.socKementerian.value+"&ID_AGENSI="+document.${formName}.socAgensi.value+"&ID_AGENSI1="+document.${formName}.socAgensi1.value+"&ID_AGENSI2="+document.${formName}.socAgensi2.value;	
	var url = "../../servlet/ekptg.report.php2.CRBSuratTindakanPBT?ID_ULASANTEKNIKAL="+idUlasanTeknikal+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+item;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratUlanganPBT(idUlasanTeknikal) {
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	var item = "&ID_PTG="+document.${formName}.socPejabatPTG.value+"&ID_PTD="+document.${formName}.socPejabatPTD.value+"&ID_PBT="+document.${formName}.socPejabatPBT.value+"&ID_JKPTG="+document.${formName}.socPejabatJKPTG.value+"&ID_KEMENTERIAN="+document.${formName}.socKementerian.value+"&ID_AGENSI="+document.${formName}.socAgensi.value+"&ID_AGENSI1="+document.${formName}.socAgensi1.value+"&ID_AGENSI2="+document.${formName}.socAgensi2.value;	
	var url = "../../servlet/ekptg.report.php2.CRBSuratUlanganPBT?ID_ULASANTEKNIKAL="+idUlasanTeknikal+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+item;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakNotis425(idUlasanTeknikal) {
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	var item = "&ID_PTG="+document.${formName}.socPejabatPTG.value+"&ID_PTD="+document.${formName}.socPejabatPTD.value+"&ID_PBT="+document.${formName}.socPejabatPBT.value+"&ID_JKPTG="+document.${formName}.socPejabatJKPTG.value+"&ID_KEMENTERIAN="+document.${formName}.socKementerian.value+"&ID_AGENSI="+document.${formName}.socAgensi.value+"&ID_AGENSI1="+document.${formName}.socAgensi1.value+"&ID_AGENSI2="+document.${formName}.socAgensi2.value;	
	var url = "../../servlet/ekptg.report.php2.CRBNotis425?ID_ULASANTEKNIKAL="+idUlasanTeknikal+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+item;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakNotisKepadaPenceroboh(idFail) {
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	
	var item = "&ID_PTG="+document.${formName}.socPejabatPTG.value+"&ID_PTD="+document.${formName}.socPejabatPTD.value+"&ID_PBT="+document.${formName}.socPejabatPBT.value+"&ID_JKPTG="+document.${formName}.socPejabatJKPTG.value+"&ID_KEMENTERIAN="+document.${formName}.socKementerian.value+"&ID_AGENSI="+document.${formName}.socAgensi.value+"&ID_AGENSI1="+document.${formName}.socAgensi1.value+"&ID_AGENSI2="+document.${formName}.socAgensi2.value;	
	var url = "../../servlet/ekptg.report.php2.CRBNotisKepadaPenceroboh?ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+item; 
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakNotisKepadaPentabirTanah(idUlasanTeknikal) {
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	alert(2);
	var item = "&ID_PTG="+document.${formName}.socPejabatPTG.value+"&ID_PTD="+document.${formName}.socPejabatPTD.value+"&ID_PBT="+document.${formName}.socPejabatPBT.value+"&ID_JKPTG="+document.${formName}.socPejabatJKPTG.value+"&ID_KEMENTERIAN="+document.${formName}.socKementerian.value+"&ID_AGENSI="+document.${formName}.socAgensi.value+"&ID_AGENSI1="+document.${formName}.socAgensi1.value+"&ID_AGENSI2="+document.${formName}.socAgensi2.value;	
	alert(3);
	var url = "../../servlet/ekptg.report.php2.CRBNotisKepadaPentadbirTanah?ID_ULASANTEKNIKAL="+idUlasanTeknikal+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+"&NAMA_AGENSI3="+document.${formName}.txtAgensi.value+item; /*AIN*/
	alert(4);
	var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
	alert(5);
	if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratTindakanPenguatkuasaan(idUlasanTeknikal) {
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	var item = "&ID_PTG="+document.${formName}.socPejabatPTG.value+"&ID_PTD="+document.${formName}.socPejabatPTD.value+"&ID_PBT="+document.${formName}.socPejabatPBT.value+"&ID_JKPTG="+document.${formName}.socPejabatJKPTG.value+"&ID_KEMENTERIAN="+document.${formName}.socKementerian.value+"&ID_AGENSI="+document.${formName}.socAgensi.value+"&ID_AGENSI1="+document.${formName}.socAgensi1.value+"&ID_AGENSI2="+document.${formName}.socAgensi2.value;	
	var url = "../../servlet/ekptg.report.php2.CRBSuratTindakanPenguatkuasaan?ID_ULASANTEKNIKAL="+idUlasanTeknikal+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+item;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function cetakSuratTapak(idUlasanTeknikal) {
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	var item = "&ID_PTG="+document.${formName}.socPejabatPTG.value+"&ID_PTD="+document.${formName}.socPejabatPTD.value+"&ID_PBT="+document.${formName}.socPejabatPBT.value+"&ID_JKPTG="+document.${formName}.socPejabatJKPTG.value+"&ID_KEMENTERIAN="+document.${formName}.socKementerian.value+"&ID_AGENSI="+document.${formName}.socAgensi.value+"&ID_AGENSI1="+document.${formName}.socAgensi1.value+"&ID_AGENSI2="+document.${formName}.socAgensi2.value;	
	var url = "../../servlet/ekptg.report.php2.CRBSuratMemohonKJPKawalTapak?ID_ULASANTEKNIKAL="+idUlasanTeknikal+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+item;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
	

function cetakSuratKJPPagarTanah(idFail) {
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	var item = "&ID_PTG="+document.${formName}.socPejabatPTG.value+"&ID_PTD="+document.${formName}.socPejabatPTD.value+"&ID_PBT="+document.${formName}.socPejabatPBT.value+"&ID_JKPTG="+document.${formName}.socPejabatJKPTG.value+"&ID_KEMENTERIAN="+document.${formName}.socKementerian.value+"&ID_AGENSI="+document.${formName}.socAgensi.value+"&ID_AGENSI1="+document.${formName}.socAgensi1.value+"&ID_AGENSI2="+document.${formName}.socAgensi2.value;	
	var url = "../../servlet/ekptg.report.php2.CRBSuratPagarTanah?ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+item;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratPanggilanOperasi(idFail) {
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	var item = "&ID_PTG="+document.${formName}.socPejabatPTG.value+"&ID_PTD="+document.${formName}.socPejabatPTD.value+"&ID_PBT="+document.${formName}.socPejabatPBT.value+"&ID_JKPTG="+document.${formName}.socPejabatJKPTG.value+"&ID_KEMENTERIAN="+document.${formName}.socKementerian.value+"&ID_AGENSI="+document.${formName}.socAgensi.value+"&ID_AGENSI1="+document.${formName}.socAgensi1.value+"&ID_AGENSI2="+document.${formName}.socAgensi2.value;	
	var url = "../../servlet/ekptg.report.php2.CBRSuratPanggilOperasi?ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+item;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratPenghargaan(idFail) {
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	var item = "&ID_PTG="+document.${formName}.socPejabatPTG.value+"&ID_PTD="+document.${formName}.socPejabatPTD.value+"&ID_PBT="+document.${formName}.socPejabatPBT.value+"&ID_JKPTG="+document.${formName}.socPejabatJKPTG.value+"&ID_KEMENTERIAN="+document.${formName}.socKementerian.value+"&ID_AGENSI="+document.${formName}.socAgensi.value+"&ID_AGENSI1="+document.${formName}.socAgensi1.value+"&ID_AGENSI2="+document.${formName}.socAgensi2.value;	
	var url = "../../servlet/ekptg.report.php2.CBRSuratPenghargaan?ID_FAIL="+idFail+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+item;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratPanggilMesy(idFail,idMesyuarat) {
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	var item = "&ID_PTG="+document.${formName}.socPejabatPTG.value+"&ID_PTD="+document.${formName}.socPejabatPTD.value+"&ID_PBT="+document.${formName}.socPejabatPBT.value+"&ID_JKPTG="+document.${formName}.socPejabatJKPTG.value+"&ID_KEMENTERIAN="+document.${formName}.socKementerian.value+"&ID_AGENSI="+document.${formName}.socAgensi.value+"&ID_AGENSI1="+document.${formName}.socAgensi1.value+"&ID_AGENSI2="+document.${formName}.socAgensi2.value;	
	var url = "../../servlet/ekptg.report.php2.CRBSuratPanggilMesyuarat?ID_FAIL="+idFail+"&ID_MESYUARAT="+idMesyuarat+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&BIL_DOKUMEN="+document.${formName}.txtKandungan.value+item;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>
