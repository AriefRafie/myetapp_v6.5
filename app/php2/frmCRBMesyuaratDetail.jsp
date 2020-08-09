<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
.style2 {
	color: #0000FF
}
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="idMesyuarat" type="hidden" id="idMesyuarat" value="$idMesyuarat"/>
  <input name="idKehadiran" type="hidden" id="idKehadiran" value="$idKehadiran"/>
  <input name="idDokumen" type="hidden" id="idDokumen" value="$idDokumen"/>
  
  <input name="actionCRB" type="hidden" id="actionCRB" value="$actionCRB"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="flagPopup" type="hidden" id="flagPopup" value="$flagPopup"/>
  <input name="modePopup" type="hidden" id="modePopup" value="$modePopup"/>
  <input name="selectedTabUpper" type="hidden" id="selectedTabUpper" value="$selectedTabUpper"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td>#parse("app/php2/frmCRBHeader.jsp")</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><div id="TabbedPanels" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <li onClick="doChangeTab(0);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT MESYUARAT</li>
          <li onClick="doChangeTab(1);" class="TabbedPanelsTab" tabindex="0">PANGGILAN MESYUARAT</li>
          <li onClick="doChangeTab(2);" class="TabbedPanelsTab" tabindex="0">KEHADIRAN</li>
          <li onClick="doChangeTab(3);" class="TabbedPanelsTab" tabindex="0">KEPUTUSAN</li>
          <li onClick="doChangeTab(4);" class="TabbedPanelsTab" tabindex="0">MINIT MESYUARAT</li>
        </ul>
        <div class="TabbedPanelsContentGroup">
          <div class="TabbedPanelsContent"> #parse("app/php2/frmCRBMaklumatMesyuarat.jsp") </div>
          <div class="TabbedPanelsContent"> #parse("app/php2/frmCRBPanggilanMesyuarat.jsp") </div>
          <div class="TabbedPanelsContent"> #parse("app/php2/frmCRBSenaraiKehadiran.jsp") </div>
          <div class="TabbedPanelsContent"> #parse("app/php2/frmCRBKeputusanMesyuarat.jsp") </div>
          <div class="TabbedPanelsContent"> #parse("app/php2/frmCRBSenaraiMinitMesyuarat.jsp") </div>
        </div>
      </div></td>
  </tr>
</table>
<fieldset id="tableReport" style="display:none;"-->
<legend><strong>SENARAI LAPORAN</strong></legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakSPM('$idFail','$idMesyuarat')"> Surat Panggil Mesyuarat</a></td>
  </tr>
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakSenaraiEdaran('$idFail')"> Senarai Edaran</a></td>
  </tr>
</table>
</fieldset>
<script type="text/javascript">
	var TabbedPanels = new Spry.Widget.TabbedPanels("TabbedPanels",{defaultTab:$selectedTabUpper});
</script>
<script>
function doChangeTab(tabId) {
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmCRBMesyuaratView";
	document.${formName}.method="POST";
	document.${formName}.selectedTabUpper.value = tabId;
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.mode.value = "view";
	document.${formName}.actionCRB.value = "papar";
	document.${formName}.submit();
}
function batalMesyuarat(){
	document.${formName}.actionCRB.value = "";
	document.${formName}.mode.value = "";
	document.${formName}.submit();
}

function kemaskiniMesyuarat(){
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}
function batalKemaskiniMesyuarat(){
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function simpanKemaskiniMesyuarat(){
	if(document.${formName}.txtTarikhMesyuarat.value == ""){
		alert('Sila masukkan Tarikh Mesyuarat.');
  		document.${formName}.txtTarikhMesyuarat.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniMesyuarat";
	doAjaxCall${formName}("");
}

function trimUlasan(s) {
	var maxlength = 1500; // Change number to your max length.
	
	if (s.value.length > maxlength) {
		s.value = s.value.substring(0,maxlength);
		alert('Melebihi aksara yang dibenarkan.'); 
	}
}
function trimTindakan(s) {
	var maxlength = 4000; // Change number to your max length.
	
	if (s.value.length > maxlength) {
		s.value = s.value.substring(0,maxlength);
		alert('Melebihi aksara yang dibenarkan.'); 
	}
}
function kemaskiniKeputusan(){
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}
function batalKemaskiniKeputusan(){
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function simpanKemaskiniKeputusan(){	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniKeputusan";
	doAjaxCall${formName}("");
}

function daftarKehadiran(){
	document.${formName}.flagPopup.value = "openKehadiran";
	document.${formName}.modePopup.value = "new";
	doAjaxCall${formName}("");
}
function paparKehadiran(idKehadiran){
	document.${formName}.flagPopup.value = "openKehadiran";
	document.${formName}.modePopup.value = "view";
	document.${formName}.idKehadiran.value = idKehadiran;
	doAjaxCall${formName}("");
}
function hapusKehadiran(idKehadiran){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.idKehadiran.value = idKehadiran;
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.hitButton.value = "hapusKehadiran";
	doAjaxCall${formName}("");
}
function daftarNotifikasi(){
	document.${formName}.flagPopup.value = "openPanggilMesyuarat";
	document.${formName}.modePopup.value = "new";
	doAjaxCall${formName}("");
}
</script>
<script>
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function cetakSPM(idFail,idMesyuarat) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmCRBPopupCetakLaporanView?idFail="+idFail+"&idMesyuarat="+idMesyuarat+"&report=cetakSuratPanggilMesy";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSenaraiEdaran(idFail) {
	var url = "../servlet/ekptg.report.php2.CRBSenaraiEdaran?ID_FAIL="+idFail;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>

<script>
function daftarDokumen() {	
	document.${formName}.flagPopup.value = "openPopupDokumen";
	document.${formName}.modePopup.value = "new";
	doAjaxCall${formName}("");
}
function simpanDokumen(idMesyuarat,idPermohonan) {
	
	if(document.${formName}.txtNamaImej.value == ""){
		alert('Sila masukkan Nama Fail.');
  		document.${formName}.txtNamaImej.focus(); 
		return; 
	}
	if(document.${formName}.fileupload.value == ""){
		alert('Sila pilih Fail yang Ingin Dimuatnaik.');
  		document.${formName}.fileupload.focus(); 
		return; 
	}

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	var namaImej = document.${formName}.txtNamaImej.value;

 	var catatanImej = document.${formName}.txtCatatanImej.value ;
	var dp = document.${formName}.form_token.value ;
	var dopost = "&form_token="+dp;
	
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmCRBMesyuaratView&hitButton=simpanDokumen&namaImej="+namaImej+"&catatanImej="+catatanImej+"&idPermohonan="+idPermohonan+"&idMesyuarat="+idMesyuarat+"&selectedTabUpper=3&actionCRB=papar"+dopost;
	document.${formName}.method="post";
	document.${formName}.enctype="multipart/form-data";
    document.${formName}.encoding="multipart/form-data";
	document.${formName}.submit();
}
function afterSaveDokumen(){
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmCRBMesyuaratView";
	document.${formName}.method="POST";
	document.${formName}.actionCRB.value = "papar";
	document.${formName}.selectedTabUpper.value = "2";
	document.${formName}.selectedTabLower.value = "5";
	document.${formName}.submit();
}
function batalDokumen(){
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	doAjaxCall${formName}("");
}
function kemaskiniDokumen(){
	document.${formName}.flagPopup.value = "openPopupDokumen";
	document.${formName}.modePopup.value = "update";
	doAjaxCall${formName}("");
}
function simpanKemaskiniDokumen() {

	if(document.${formName}.txtNamaImej.value == ""){
		alert('Sila masukkan Nama Imej.');
  		document.${formName}.txtNamaImej.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}

	document.${formName}.flagPopup.value = "openPopupDokumen";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniDokumen";
	doAjaxCall${formName}("");
}
function paparDokumen(idDokumen){
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmCRBMesyuaratView";
	document.${formName}.method="POST";
	document.${formName}.idDokumen.value = idDokumen;
	document.${formName}.flagPopup.value = "openPopupDokumen";
	document.${formName}.modePopup.value = "view";
	document.${formName}.submit();
}
function hapusDokumen(){	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}	
	
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.hitButton.value = "hapusDokumen";
	document.${formName}.submit();
}
function cetakImej(id){
	var url = "../servlet/ekptg.view.php2.FrmDisplayImage?id="+id;
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}
</script>
