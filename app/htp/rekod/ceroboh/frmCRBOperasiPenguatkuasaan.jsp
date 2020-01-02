<style type="text/css">
<!--
.style1 {color: #FF0000}
.style2 {color: #0000FF}
-->
</style>

<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="idLaporanTanah" type="hidden" id="idLaporanTanah" value="$idLaporanTanah"/>
  <input name="idPegawaiLaporanTanah" type="hidden" id="idPegawaiLaporanTanah" value="$idPegawaiLaporanTanah"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="flagPopup" type="hidden" id="flagPopup" value="$flagPopup"/>
  <input name="modePopup" type="hidden" id="modePopup" value="$modePopup"/>
  <input name="selectedTabUpper" type="hidden" id="selectedTabUpper" value="$selectedTabUpper"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
  <input name="idDokumen" type="hidden" id="idDokumen" value="$idDokumen"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($idFail != '')
  <tr>
    <td> #parse("app/php2/frmCRBHeader.jsp") </td>
  </tr>
  #else
  <tr>
    <td>&nbsp;
      <div class="warning">SILA PILIH FAIL DI SENARAI FAIL TERLEBIH DAHULU</div></td>
  </tr>
  #end
  #if ($idFail != '' && $idStatus != '1610198' && $idStatus != '1610199' && $idStatus !='1610200' && $idStatus !='1610201' && $idStatus !='1610215' && $idStatus != '1610216')
  <tr>
    <td><div id="TabbedPanels1" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <li onClick="doChangeTab(0);" class="TabbedPanelsTab" tabindex="0">LAPORAN OPERASI</li>
          <li onClick="doChangeTab(1);" class="TabbedPanelsTab" tabindex="0">KEHADIRAN</li>
          <li onClick="doChangeTab(2);" class="TabbedPanelsTab" tabindex="0">IMEJAN</li>
        </ul>
        <div class="TabbedPanelsContentGroup">
          <div class="TabbedPanelsContent"> <br>
            #parse("app/htp/rekod/ceroboh/frmCRBLaporanOperasi.jsp") </div>
          <div class="TabbedPanelsContent"> <br>
            #parse("app/htp/rekod/ceroboh/frmCRBLaporanOperasiKehadiran.jsp")</div>
          <div class="TabbedPanelsContent"> <br>
            #parse("app/htp/rekod/ceroboh/frmCRBLaporanOperasiImejan.jsp") </div>
        </div>
      </div></td>
  </tr>
  #end
  <tr>
  	<td align="center">   		
  		<input type="button" class="stylobutton100" name="cmdsebelum" value="Sebelumnya" onClick="cmdSebelumnya()"/>
  		<input type="button" class="stylobutton100" name="cmdkembali" value="Kembali" onClick="cmdKembali()"/>
	</td>
  </tr>
</table>
<fieldset id="tableReport" style="display:none;"-->
<legend><strong>SENARAI LAPORAN</strong></legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakLaporanOperasi('$idFail','$idLaporanTanah','$idPermohonan')"> Laporan Operasi </a></td>
  </tr>
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakCRBLampiranA('$idLaporanTanah')"> Lampiran A </a></td>
  </tr>
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakSuratPagarTanah('$idFail')"> Surat Pagar Tanah </a></td>
  </tr>
</table>
</fieldset>
<script type="text/javascript">
#if ($idFail != '' && $idStatus != '1610198' && $idStatus != '1610199' && $idStatus !='1610200' && $idStatus !='1610215' && $idStatus != '1610216')
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabUpper});
#end
</script>

<script>
function doChangeTab(tabId) {
	document.${formName}.mode.value = "view";
	document.${formName}.selectedTabUpper.value = tabId;
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmCRBOperasiPenguatkuasaanView";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function kemaskiniLaporanOperasi() {
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}
function validateCurrency(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
	
	if(content != ""){
		var num = content * 1;
		elmnt.value = num.toFixed(2);
		return;
	} else {
		elmnt.value ="";
		return;
	}
}
function batalLaporanOperasi() {
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function simpanKemaskiniLaporanOperasi() {
	if(document.${formName}.txtTarikhOperasi.value == ""){
		alert('Sila masukkan Tarikh Operasi.');
  		document.${formName}.txtTarikhOperasi.focus(); 
		return; 
	}
	if(document.${formName}.txtLaporan.value == ""){
		alert('Sila masukkan Laporan.');
  		document.${formName}.txtLaporan.focus(); 
		return; 
	}
	if(document.${formName}.txtNamaPelaporOperasi.value == ""){
		alert('Sila masukkan Nama Pegawai Pelapor.');
  		document.${formName}.txtNamaPelaporOperasi.focus(); 
		return; 
	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniLaporanOperasi";
	doAjaxCall${formName}("");
}

function daftarKehadiran(){
	document.${formName}.flagPopup.value = "openKehadiran";
	document.${formName}.modePopup.value = "new";
	doAjaxCall${formName}("");
}
function paparKehadiran(idPegawaiLaporanTanah){
	document.${formName}.flagPopup.value = "openKehadiran";
	document.${formName}.modePopup.value = "view";
	document.${formName}.idPegawaiLaporanTanah.value = idPegawaiLaporanTanah;
	doAjaxCall${formName}("");
}
function hapusKehadiran(idPegawaiLaporanTanah){

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.idPegawaiLaporanTanah.value = idPegawaiLaporanTanah;
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.hitButton.value = "hapusKehadiran";
	doAjaxCall${formName}("");
}
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function cetakLaporanOperasi(idFail,idLaporanTanah,idPermohonan) {
	var url = "../servlet/ekptg.report.php2.CBRLaporanOperasi?ID_FAIL="+idFail+"&ID_LAPORANTANAH="+idLaporanTanah+"&ID_PERMOHONAN="+idPermohonan;
    var hWnd = window.open(url,'printuser','width=700,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function cetakCRBLampiranA(idLaporanTanah) {
	var url = "../servlet/ekptg.report.php2.CRBLampiranA?ID_LAPORANTANAH="+idLaporanTanah;
    var hWnd = window.open(url,'printuser','width=700,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratPagarTanah(idFail) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmCRBPopupCetakLaporanView?idFail="+idFail+"&report=suratKJPPagarTanah";
    var hWnd = window.open(url,'printuser','width=700,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>