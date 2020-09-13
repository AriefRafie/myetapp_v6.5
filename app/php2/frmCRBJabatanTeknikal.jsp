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
  <input name="idUlasanTeknikal" type="hidden" id="idUlasanTeknikal" value="$idUlasanTeknikal"/>
  <input name="idKementerianTanah" type="hidden" id="idKementerianTanah" value="$idKementerianTanah"/>
  <input name="idAgensiTanah" type="hidden" id="idAgensiTanah" value="$idAgensiTanah"/>
  <input name="modePopup" type="hidden" id="modePopup" value="$modePopup"/>
  <input name="flagPopup" type="hidden" id="flagPopup" value="$flagPopup"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="selectedTabUpper" type="hidden" id="selectedTabUpper" value="$selectedTabUpper"/>
  <input name="flagStatus" type="hidden" id="flagStatus" value="$flagStatus"/>
  <input name="flagAktif" type="hidden" id="flagAktif" value="$flagAktif"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($idFail != '' && $idStatus != '1610198' && $idStatus != '1610200')
  <tr>
    <td> #parse("app/php2/frmCRBHeader.jsp") </td>
  </tr>
  #elseif ($idFail == '' )
  <tr>
    <td>&nbsp;
      <div class="warning">SILA PILIH FAIL DI SENARAI FAIL TERLEBIH DAHULU</div></td>
  </tr>
  #end
  
  #if ($idFail != '' && $flagOpenDetail && $idStatus != '1610198' && $idStatus != '1610200')
  <tr>
    <td><div id="TabbedPanels1" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <!-- <li onClick="doChangeTabUpper(0);" class="TabbedPanelsTab" tabindex="0">DOKUMEN ULASAN</li> -->
          <!-- <li onClick="doChangeTabUpper(1);" class="TabbedPanelsTab" tabindex="0">SURAT TINDAKAN PBT</li> -->
          <li onClick="doChangeTabUpper(0);" class="TabbedPanelsTab" tabindex="0">SURAT PEMBERITAHUAN PENGOSONGAN TAPAK</li>
          <li onClick="doChangeTabUpper(1);" class="TabbedPanelsTab" tabindex="0">SURAT POHON TINDAKAN DARIPADA PENTADBIR</li>
        </ul>
        <div class="TabbedPanelsContentGroup">
          <!-- <div class="TabbedPanelsContent">
            #parse("app/php2/frmCRBDokumenUlasan.jsp") </div> -->
          <!-- <div class="TabbedPanelsContent">
            #parse("app/php2/frmCRBDokumenPBT.jsp") 
             </div> -->
          <div class="TabbedPanelsContent"> #parse("app/php2/frmCRBNotisPengosongan.jsp") </div>
          <div class="TabbedPanelsContent"> #parse("app/php2/frmCRBDokumenNotis425.jsp") </div>
        </div>
      </div></td>
  </tr>
  <tr>
    <td align="center"> #if ($idStatus == '1610199' && $flagPopup == '')
      <input type="button" name="cmdHantar" id="cmdHantar" value="Seterusnya" onClick="doSeterusnya()"/>
      <input type="button" name="cmdSelesaiPermohonan" id="cmdSelesaiPermohonan" value="Selesai Permohonan" onClick="gotoSelesaiPermohonan()"/>
      <input type="button" name="cmdBatalPermohonan" id="cmdBatalPermohonan" value="Batal Permohonan" onClick="gotoBatalPermohonan()"/>
      #end</td>
  </tr>
  #elseif ($idFail != '')
  <tr>
    <td> 
      <div class="warning"><strong>STATUS FAIL MASIH DI PERINGKAT $!status</strong></div></td>
  </tr>
  #end
</table>
<script type="text/javascript">
#if ($idFail != '')
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabUpper});
#end
</script>
<script>
function doChangeTabUpper(tabId) {
	document.${formName}.mode.value = "view";
	document.${formName}.selectedTabUpper.value = tabId;
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.submit();
}
function doSeterusnya(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}

	document.${formName}.hitButton.value = "doSeterusnya";
	document.${formName}.submit();
}
function doChangeSuratKe() {
	doAjaxCall${formName}("doChangeSuratKe");
}
</script>
<script>
function cetakCRBSMUKJP(idFail,idUlasanTeknikal) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmCRBPopupCetakLaporanView?idFail="+idFail+"&report=suratMUKJP&idUlasanTeknikal="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakCRBSuratUlanganKJP(idFail,idUlasanTeknikal,bilUlangan) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmCRBPopupCetakLaporanView?idFail="+idFail+"&report=suratUlanganKJP&idUlasanTeknikal="+idUlasanTeknikal+"&bilUlangan="+bilUlangan;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakCRBSuratMULT(idFail,idUlasanTeknikal) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmCRBPopupCetakLaporanView?idFail="+idFail+"&report=suratMULT&idUlasanTeknikal="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakCRBSuratUlanganLT(idFail,idUlasanTeknikal) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmCRBPopupCetakLaporanView?idFail="+idFail+"&report=suratUlanganLT&idUlasanTeknikal="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakCRBSuratTindakanPBT(idFail,idUlasanTeknikal) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmCRBPopupCetakLaporanView?idFail="+idFail+"&report=suratTindakanPBT&idUlasanTeknikal="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakCRBSuratUlanganPBT(idFail,idUlasanTeknikal) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmCRBPopupCetakLaporanView?idFail="+idFail+"&report=suratUlanganPBT&idUlasanTeknikal="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakCRBNotis425(idFail,idUlasanTeknikal) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmCRBPopupCetakLaporanView?idFail="+idFail+"&report=notis425&idUlasanTeknikal="+idUlasanTeknikal+"&idSuratKe="+document.${formName}.idSuratKe.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakCRBUlanganNotis425(idFail,idUlasanTeknikal) {
	alert("Sila hubungi Administrator anda.");
}
</script>
<script>
function doChangeJenisDokumen() {
	doAjaxCall${formName}("doChangeJenisDokumen");
}
function cetakCRBNotisKepadaPenceroboh(idFail,idUlasanTeknikal) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmCRBPopupCetakLaporanView?idFail="+idFail+"&report=notisKepadaPenceroboh&idUlasanTeknikal="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakCRBNotisKepadaPentadbir(idFail,idUlasanTeknikal) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmCRBPopupCetakLaporanView?idFail="+idFail+"&report=notisKepadaPentadbirTanah&idUlasanTeknikal="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function paparMaklumatNotis(idUlasanTeknikal){
	document.${formName}.flagPopup.value = "openNotis";
	document.${formName}.modePopup.value = "view";
	document.${formName}.idUlasanTeknikal.value = idUlasanTeknikal;
	doAjaxCall${formName}("");
}
function doDaftarMaklumatNotis(){
	document.${formName}.flagPopup.value = "openNotis";
	document.${formName}.modePopup.value = "new";
	document.${formName}.flagStatus.value = "1";
	doAjaxCall${formName}("");
}
function doSimpanMaklumatNotis(){
	if(document.${formName}.socDokumen.value == ""){
		alert('Sila pilih Nama Dokumen.');
  		document.${formName}.socDokumen.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "openNotis";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanMaklumatNotis";
	doAjaxCall${formName}("");
}
function doBatalMaklumatNotis(){
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	doAjaxCall${formName}("");
}
function doKemaskiniMaklumatNotis(){
	document.${formName}.flagPopup.value = "openNotis";
	document.${formName}.modePopup.value = "update";
	doAjaxCall${formName}("");
}
function doBatalKemaskiniMaklumatNotis(){
	document.${formName}.flagPopup.value = "openNotis";
	document.${formName}.modePopup.value = "view";
	doAjaxCall${formName}("");
}
function doSimpanKemaskiniMaklumatNotis(){
	if(document.${formName}.socDokumen.value == ""){
		alert('Sila pilih Nama Dokumen.');
  		document.${formName}.socDokumen.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "openNotis";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniMaklumatNotis";
	doAjaxCall${formName}("");
}
function doHapusMaklumatNotis(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.hitButton.value = "hapusMaklumatNotis";
	doAjaxCall${formName}("");
}
function batalDokumen(){
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	doAjaxCall${formName}("");
}
</script>
<script>
function doChangeNegeri() {
	doAjaxCall${formName}("doChangeNegeri");
}
function doChangePejabat() {
	doAjaxCall${formName}("doChangePejabat");
}
function calcDate(){
	if (document.${formName}.txtTarikhHantar.value != "" && document.${formName}.txtJangkaMasa.value != ""){
		
		var tarikhHantar  = document.${formName}.txtTarikhHantar.value;
		var days  = parseInt(document.${formName}.txtJangkaMasa.value);
		
		var dt1   = parseInt(tarikhHantar.substring(0,2),10) + days;
		var mon1  = parseInt(tarikhHantar.substring(3,5),10)-1;
		var yr1   = parseInt(tarikhHantar.substring(6,10),10);
	 
		var myDate = new Date(yr1, mon1, dt1);
		
		var day = myDate.getDate();
		var month = myDate.getMonth()+1;
		var year = myDate.getFullYear();
		
		var tarikhJangkaTerima = "";
		if(month>=10){
			if(day>=10){
				tarikhJangkaTerima = day + "/" + month + "/" + year;	
			} else {
				tarikhJangkaTerima = "0"+ day + "/" + month + "/" + year;	
			}				
		} else {
			if(day>=10){
				tarikhJangkaTerima = day + "/0" + month + "/" + year;	
			} else {
				tarikhJangkaTerima = "0"+ day + "/0" + month + "/" + year;	
			}
		}
		document.${formName}.txtTarikhJangkaTerima.value = tarikhJangkaTerima;
	
	} else {
		document.${formName}.txtTarikhJangkaTerima.value = "";
	}
}
function paparMaklumatNotis425(idUlasanTeknikal){
	document.${formName}.flagPopup.value = "openNotis425";
	document.${formName}.modePopup.value = "view";
	document.${formName}.idUlasanTeknikal.value = idUlasanTeknikal;
	doAjaxCall${formName}("");
}
function doDaftarMaklumatNotis425(){
	document.${formName}.flagPopup.value = "openNotis425";
	document.${formName}.modePopup.value = "new";
	document.${formName}.flagStatus.value = "1";
    doAjaxCall${formName}("");
}
function doSimpanMaklumatNotis425(){
	
	if(document.${formName}.idSuratKe.value == ""){
		alert('Sila pilih Surat ke .');
  		document.${formName}.idSuratKe.focus(); 
		return; 
	}
	if(document.${formName}.socNegeri.value == ""){
		alert('Sila pilih Negeri.');
		document.${formName}.socNegeri.focus(); 
		return; 
	}
	if(document.${formName}.socPejabat.value == ""){
		alert('Sila pilih Pejabat.');
		document.${formName}.socPejabat.focus(); 
		return; 
	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "openNotis425";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanMaklumatNotis425";
	doAjaxCall${formName}("");
}
function doSimpanMaklumatUlanganNotis425(){	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "openNotis425";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanMaklumatUlanganNotis425";
	doAjaxCall${formName}("");
}
function doBatalMaklumatNotis425(){
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	doAjaxCall${formName}("");
}
function doKemaskiniMaklumatNotis425(){
	document.${formName}.flagPopup.value = "openNotis425";
	document.${formName}.modePopup.value = "update";
	doAjaxCall${formName}("");
}
function doBatalKemaskiniMaklumatNotis425(){
	document.${formName}.flagPopup.value = "openNotis425";
	document.${formName}.modePopup.value = "view";
	doAjaxCall${formName}("");
}
function doSimpanKemaskiniMaklumatNotis425(){

	if(document.${formName}.idSuratKe.value == ""){
		alert('Sila pilih Surat ke .');
  		document.${formName}.idSuratKe.focus(); 
		return; 
	}
	if(document.${formName}.socNegeri.value == ""){
		alert('Sila pilih Negeri.');
		document.${formName}.socNegeri.focus(); 
		return; 
	}
	if(document.${formName}.socPejabat.value == ""){
		alert('Sila pilih Pejabat.');
		document.${formName}.socPejabat.focus(); 
		return; 
	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "openNotis425";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniMaklumatNotis425";
	doAjaxCall${formName}("");
}
function doHapusMaklumatNotis425(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.hitButton.value = "hapusMaklumatKJPKJT";
	doAjaxCall${formName}("");
}
function doTerimaNotis425(){
	document.${formName}.flagPopup.value = "openNotis425";
	document.${formName}.modePopup.value = "update";
	document.${formName}.flagStatus.value = "2";
	doAjaxCall${formName}("");
}
function doUlanganNotis425(){
	document.${formName}.flagPopup.value = "openNotis425";
	document.${formName}.modePopup.value = "newUlangan";
	document.${formName}.flagStatus.value = "1";
	doAjaxCall${formName}("");
}
</script>
<input name="step" type="hidden" id="step"/>
<script>
function gotoSelesaiPermohonan(){	
	document.${formName}.step.value = "selesaiPermohonan";
	document.${formName}.submit();
}
function gotoBatalPermohonan(){	
	document.${formName}.step.value = "batalPermohonan";
	document.${formName}.submit();
}
</script>
