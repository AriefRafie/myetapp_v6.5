<style type="text/css">
<!--
.style1 {
	color: #ff0000
}
.style2 {
	color: #0000FF
}
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input type="hidden" name="idHasil" id="idHasil" value="$idHasil"/>
  <input type="hidden" name="idFail"  id="idFail" value="$idFail"/>
  <input type="hidden" name="idAkaun" id="idAkaun" value="$idAkaun"/>
  <input type="hidden" name="idPemohon" id="idPemohon" value="$idPemohon">
  <input type="hidden" name="idNotis" id="idNotis" value="$!idNotis">
  <input type="hidden" name="mode" id="mode" value="$mode"/>
  <input type="hidden" name="actionHasil" id="actionHasil" value="$actionHasil"/>
  <input type="hidden" name="hitButton" id="hitButton"/>
  <input type="hidden" name="selectedTabUpper" id="selectedTabUpper" value="$selectedTabUpper"/>
  <input name="anchor" type="hidden" id="anchor"/>
  <input type="hidden" name="txtNamaPemohon" id="txtNamaPemohon" value="$txtNamaPemohon"/>
  <input type="hidden" name="txtNoFail" id="txtNoFail" value="$txtNoFail"/>

</p>

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td>#parse("app/php2/frmREVHeader.jsp") </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><div id="TabbedPanels1" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <li onClick="doChangeTabUpper(0);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT DEPOSIT</li>
          <li onClick="doChangeTabUpper(1);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT SEWA</li>
          <li onClick="doChangeTabUpper(2);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT BAYARAN LAIN LAIN</li>
          <li onClick="doChangeTabUpper(3);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT PERJANJIAN</li>
          <li onClick="doChangeTabUpper(4);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT PEMOHON</li>
          <li onClick="doChangeTabUpper(5);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT PERMOHONAN</li>
          <li onClick="doChangeTabUpper(6);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT TANAH</li>
          <li onClick="doChangeTabUpper(7);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT ABT</li>
          <li onClick="doChangeTabUpper(8);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT NOTIS</li>
          <li onClick="doChangeTabUpper(9);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT MEMO</li>
        </ul>
        <div class="TabbedPanelsContentGroup">
          <div class="TabbedPanelsContent">#parse("app/php2/frmREVMaklumatDeposit.jsp")</div>
          <div class="TabbedPanelsContent">#parse("app/php2/frmREVMaklumatSewa.jsp")</div>
          <div class="TabbedPanelsContent">#parse("app/php2/frmREVMaklumatBayaranLain.jsp")</div>
          <div class="TabbedPanelsContent">#parse("app/php2/frmREVMaklumatPerjanjian.jsp")</div>
          <div class="TabbedPanelsContent">#parse("app/php2/frmREVMaklumatPemohon.jsp")</div>
          <div class="TabbedPanelsContent">#parse("app/php2/frmREVMaklumatPermohonan.jsp")</div>
          <div class="TabbedPanelsContent">#parse("app/php2/frmREVMaklumatTanah.jsp")</div>
          <div class="TabbedPanelsContent">#parse("app/php2/frmREVMaklumatABT.jsp")</div>
          <div class="TabbedPanelsContent">#parse("app/php2/frmREVSenaraiNotis.jsp")</div>
          <div class="TabbedPanelsContent">#parse("app/php2/frmREVSenaraiMemo.jsp")</div>
        </div>
      </div></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr align="center">
  	<td><input type="button" name="cmdBackList" id="cmdBackList" value="Kembali" onClick="doBacklist()"/></td>
  </tr>
</table>

<div id="checkingExistNoFailUpdate_result"></div>
<script>
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabUpper});

function doChangeTabUpper(tabId) {
	document.${formName}.selectedTabUpper.value = tabId;
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("doChangeTabUpper");
}
function kembali(){
	document.${formName}.actionHasil.value = "";
	document.${formName}.submit();
}

function doBacklist() {
	document.${formName}.actionHasil.value = "";
	document.${formName}.txtNamaPemohon.value = "$txtNamaPemohon";
	document.${formName}.txtNoFail.value = "$txtNoFail";
	document.${formName}.submit();
}
function batal(){
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function doBatalKemaskini(){
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function doChangeNegeri() {
	doAjaxCall${formName}("doChangeNegeri");
}
function doChangeKementerian() {
	doAjaxCall${formName}("doChangeKementerian");
}
function doChangeModBayaran() {
	doAjaxCall${formName}("doChangeModBayaran");
}
function doChangeCaraBayar() {
	doAjaxCall${formName}("doChangeCaraBayar");
}
function doChangeKategoriBayaran() {
	doAjaxCall${formName}("doChangeKategoriBayaran");
}
function janaPenyataAkaun(idHasil) {

	var url = "../servlet/ekptg.report.php2.REVPenyataAkaun?ID_HASIL="+idHasil;
    var hWnd = window.open(url,'printuser','width=1000,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function janaSuratIringanResit(idHasil) {

	var url = "../x/${securityToken}/ekptg.view.php2.FrmREVPopupCetakLaporanView?idHasil="+idHasil+"&report=suratIringanResit";
    var hWnd = window.open(url,'printuser','width=1000,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function janaSuratTuntutanDeposit(idHasil) {

	var url = "../x/${securityToken}/ekptg.view.php2.FrmREVPopupCetakLaporanView?idHasil="+idHasil+"&report=suratTuntutanDeposit";
    var hWnd = window.open(url,'printuser','width=1000,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function validateCurrency(elmnt,content,content2) {
	content = content.replace(/,/g,'');
	content2 = content2.replace(/,/g,'');
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
function checkingExistNoFailUpdate() {
	url = "../servlet/ekptg.view.php2.FrmREVServlet?command=checkingExistNoFailUpdate";
	actionName = "checkingExistNoFailUpdate";
	target = "checkingExistNoFailUpdate_result";
	doAjaxUpdater(document.${formName}, url, target, actionName);
}
function popupMsg(){
	alert('No. Fail telah wujud.');
	document.${formName}.noFail.value = "";
}
</script>
<!-- MAKLUMAT PEMOHON -->
<script>
function doKemaskiniPemohon(){
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}
function doSimpanKemaskiniPemohon() {
	if(document.${formName}.txtNama.value == ""){
			alert('Sila masukan Nama.');
  			document.${formName}.txtNama.focus();
			return;
		}

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}

	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSimpanKemaskiniPemohon";
	document.${formName}.submit();
}
</script>
<!-- MAKLUMAT PERMOHONAN -->
<script>
function doChangeUrusan() {
	doAjaxCall${formName}("doChangeUrusan");
}
function doKemaskiniPermohonan(){
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}
function doHapusPermohonan(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	document.${formName}.actionHasil.value = "";
	document.${formName}.hitButton.value = "hapusFail";
	document.${formName}.submit();
}
function doSimpanKemaskiniPermohonan() {
	if(document.${formName}.noFail.value == ''){
		alert('Sila masukkan No Fail.');
  		document.${formName}.noFail.focus();
		return;
	}
	if(document.${formName}.socUrusan.value == ""){
		alert('Sila pilih Jenis Urusan.');
  		document.${formName}.socUrusan.focus();
		return;
	}
	if(document.${formName}.socSuburusan.value == ""){
		alert('Sila pilih Jenis Suburusan.');
  		document.${formName}.socSuburusan.focus();
		return;
	}
	if(document.${formName}.txtPerkara.value == ""){
		alert('Sila masukkan Perkara.');
  		document.${formName}.txtPerkara.focus();
		return;
	}

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}

	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSimpanKemaskiniPermohonan";
	document.${formName}.submit();
}
</script>
<!-- MAKLUMAT TANAH -->
<script>
function doKemaskiniTanah(){
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}
function doSimpanKemaskiniTanah() {

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}

	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSimpanKemaskiniTanah";
	document.${formName}.submit();
}
</script>
<!-- PEMBAYARAN DEPOSIT -->
<script>
function paparBayaranD(idAkaun){
	document.${formName}.mode.value = "viewBayaranD";
	document.${formName}.idAkaun.value = idAkaun;
	doAjaxCall${formName}("");
}
function pembayaranD(){
	document.${formName}.mode.value = "newBayaranD";
	doAjaxCall${formName}("");
}

function capaianGfmas() {
	document.${formName}.mode.value = "capaianGfmas";
	doAjaxCall${formName}("");
}

function capaianGfmasSewa() {
	document.${formName}.mode.value = "capaianGfmasSewa";
	doAjaxCall${formName}("");
}

function capaianGfmasBayaran() {
	document.${formName}.mode.value = "capaianGfmasBayaran";
	doAjaxCall${formName}("");
}

function simpanBayaranD(){

	if(document.${formName}.txtTarikh.value == ""){
		alert('Sila masukkan Tarikh.');
  		document.${formName}.txtTarikh.focus();
		return;
	}

	if(document.${formName}.socModBayaran.value == ""){
		alert('Sila pilih Mod Bayaran.');
  		document.${formName}.socModBayaran.focus();
		return;
	}
	if(document.${formName}.socCaraBayaran.value == ""){
		alert('Sila pilih Cara Bayar.');
  		document.${formName}.socCaraBayaran.focus();
		return;
	}
	if(document.${formName}.txtAmaun.value == ""){
		alert('Sila masukkan Amaun.');
  		document.${formName}.txtAmaun.focus();
		return;
	}

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "newBayaranD";
		return;
	}

	document.${formName}.mode.value = "viewBayaranD";
	document.${formName}.hitButton.value = "simpanBayaranD";
	doAjaxCall${formName}("");
}

function sendNotisByEmail(idAkaun){
	document.${formName}.idAkaun.value = idAkaun;
	document.${formName}.hitButton.value = "sendNotisByEmail";
	doAjaxCall${formName}("");
}
function paparBayaranD(idAkaun){
	document.${formName}.mode.value = "viewBayaranD";
	document.${formName}.idAkaun.value = idAkaun;
	doAjaxCall${formName}("");
}
function kemaskiniBayaranD(){
	document.${formName}.mode.value = "updateBayaranD";
	doAjaxCall${formName}("");
}
function hapusBayaranD(){

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "viewBayaranD";
		return;
	}

	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "hapusBayaranD";
	doAjaxCall${formName}("");
}
function simpanKemaskiniBayaranD(){
	if(document.${formName}.txtTarikh.value == ""){
		alert('Sila masukkan Tarikh.');
  		document.${formName}.txtTarikh.focus();
		return;
	}
	if(document.${formName}.socCaraBayaran.value == ""){
		alert('Sila pilih Cara Bayar.');
  		document.${formName}.socCaraBayaran.focus();
		return;
	}
	if(document.${formName}.txtAmaun.value == ""){
		alert('Sila masukkan Amaun.');
  		document.${formName}.txtAmaun.focus();
		return;
	}

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "updateBayaranD";
		return;
	}

	document.${formName}.mode.value = "viewBayaranD";
	document.${formName}.hitButton.value = "simpanKemaskiniBayaranD";
	doAjaxCall${formName}("");
}
function batalKemaskiniBayaranD(){
	document.${formName}.mode.value = "viewBayaranD";
	doAjaxCall${formName}("");
}
</script>
<!-- PEMBAYARAN SEWA -->
<script>
function paparBayaran(idAkaun){
	document.${formName}.mode.value = "viewBayaran";
	document.${formName}.idAkaun.value = idAkaun;
	doAjaxCall${formName}("");
}
function pemBayaran(){
	document.${formName}.mode.value = "newBayaran";
	doAjaxCall${formName}("");
}
function simpanBayaran(){

	if(document.${formName}.txtTarikh.value == ""){
		alert('Sila masukkan Tarikh.');
  		document.${formName}.txtTarikh.focus();
		return;
	}
	if(document.${formName}.socModBayaran.value == ""){
		alert('Sila pilih Mod Bayaran.');
  		document.${formName}.socModBayaran.focus();
		return;
	}
	if(document.${formName}.socCaraBayaran.value == ""){
		alert('Sila pilih Cara Bayar.');
  		document.${formName}.socCaraBayaran.focus();
		return;
	}
	if(document.${formName}.txtAmaun.value == ""){
		alert('Sila masukkan Amaun.');
  		document.${formName}.txtAmaun.focus();
		return;
	}

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "newBayaran";
		return;
	}

	document.${formName}.mode.value = "viewBayaran";
	document.${formName}.hitButton.value = "simpanBayaran";
	doAjaxCall${formName}("");
}
function paparBayaran(idAkaun){
	document.${formName}.mode.value = "viewBayaran";
	document.${formName}.idAkaun.value = idAkaun;
	doAjaxCall${formName}("");
}
function kemaskiniBayaran(){
	document.${formName}.mode.value = "updateBayaran";
	doAjaxCall${formName}("");
}
function hapusBayaran(){

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "viewBayaran";
		return;
	}

	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "hapusBayaran";
	doAjaxCall${formName}("");
}
function simpanKemaskiniBayaran(){
	if(document.${formName}.txtTarikh.value == ""){
		alert('Sila masukkan Tarikh.');
  		document.${formName}.txtTarikh.focus();
		return;
	}
	if(document.${formName}.socCaraBayaran.value == ""){
		alert('Sila pilih Cara Bayar.');
  		document.${formName}.socCaraBayaran.focus();
		return;
	}
	if(document.${formName}.txtAmaun.value == ""){
		alert('Sila masukkan Amaun.');
  		document.${formName}.txtAmaun.focus();
		return;
	}

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "updateBayaran";
		return;
	}

	document.${formName}.mode.value = "viewBayaran";
	document.${formName}.hitButton.value = "simpanKemaskiniBayaran";
	doAjaxCall${formName}("");
}
function batalKemaskiniBayaran(){
	document.${formName}.mode.value = "viewBayaran";
	doAjaxCall${formName}("");
}
function janaBorangPenyerahanPenyewa(idHasil) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmREVPopupCetakLaporanView?report=BorangPenyerahanSewaPenyewa&idHasil="+idHasil;
    var hWnd = window.open(url,'printuser','width=1000,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>
<!-- PEMBAYARAN SEWA LAIN LAIN -->
<script>
function paparBayaranLL(idAkaun){
	document.${formName}.mode.value = "viewBayaranLL";
	document.${formName}.idAkaun.value = idAkaun;
	doAjaxCall${formName}("");
}
function pemBayaranLL(){
	document.${formName}.mode.value = "newBayaranLL";
	doAjaxCall${formName}("");
}
function simpanBayaranLL(){

	if(document.${formName}.txtTarikh.value == ""){
		alert('Sila masukkan Tarikh.');
  		document.${formName}.txtTarikh.focus();
		return;
	}
	if(document.${formName}.socModBayaran.value == ""){
		alert('Sila pilih Mod Bayaran.');
  		document.${formName}.socModBayaran.focus();
		return;
	}
	if(document.${formName}.socCaraBayaran.value == ""){
		alert('Sila pilih Cara Bayar.');
  		document.${formName}.socCaraBayaran.focus();
		return;
	}
	if(document.${formName}.txtAmaun.value == ""){
		alert('Sila masukkan Amaun.');
  		document.${formName}.txtAmaun.focus();
		return;
	}

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "newBayaranLL";
		return;
	}

	document.${formName}.mode.value = "viewBayaranLL";
	document.${formName}.hitButton.value = "simpanBayaranLL";
	doAjaxCall${formName}("");
}
function paparBayaranLL(idAkaun){
	document.${formName}.mode.value = "viewBayaranLL";
	document.${formName}.idAkaun.value = idAkaun;
	doAjaxCall${formName}("");
}
function kemaskiniBayaranLL(){
	document.${formName}.mode.value = "updateBayaranLL";
	doAjaxCall${formName}("");
}
function hapusBayaranLL(){

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "viewBayaranLL";
		return;
	}

	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "hapusBayaranLL";
	doAjaxCall${formName}("");
}
function simpanKemaskiniBayaranLL(){
	if(document.${formName}.txtTarikh.value == ""){
		alert('Sila masukkan Tarikh.');
  		document.${formName}.txtTarikh.focus();
		return;
	}
	if(document.${formName}.socCaraBayaran.value == ""){
		alert('Sila pilih Cara Bayar.');
  		document.${formName}.socCaraBayaran.focus();
		return;
	}
	if(document.${formName}.txtAmaun.value == ""){
		alert('Sila masukkan Amaun.');
  		document.${formName}.txtAmaun.focus();
		return;
	}

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "updateBayaranLL";
		return;
	}

	document.${formName}.mode.value = "viewBayaranLL";
	document.${formName}.hitButton.value = "simpanKemaskiniBayaranLL";
	doAjaxCall${formName}("");
}
function batalKemaskiniBayaranLL(){
	document.${formName}.mode.value = "viewBayaranLL";
	doAjaxCall${formName}("");
}


</script>
<!-- PELARASAN DEPOSIT -->
<script>
function onChangeCek(str) {
	document.${formName}.cek.value = str.value;
	doAjaxCall${formName}("onChangeCek");
}
function paparPelarasanD(idAkaun){
	document.${formName}.mode.value = "viewPelarasanD";
	document.${formName}.idAkaun.value = idAkaun;
	doAjaxCall${formName}("");
}
function pelarasanD(){
	document.${formName}.mode.value = "newPelarasanD";
	doAjaxCall${formName}("");
}
function simpanPelarasanD(){

	if(document.${formName}.txtTarikh.value == ""){
		alert('Sila masukkan Tarikh.');
  		document.${formName}.txtTarikh.focus();
		return;
	}
	if(document.${formName}.socJenisPelarasan.value != "C" && document.${formName}.socJenisPelarasan.value != "D" && document.${formName}.socJenisPelarasan.value != "K" && document.${formName}.socJenisPelarasan.value != "R"){
		alert('Sila pilih Jenis Pelarasan.');
  		document.${formName}.socJenisPelarasan.focus();
		return;
	}
	if(document.${formName}.txtAmaun.value == ""){
		alert('Sila masukkan Amaun.');
  		document.${formName}.txtAmaun.focus();
		return;
	}

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "newPelarasanD";
		return;
	}

	document.${formName}.mode.value = "viewPelarasanD";
	document.${formName}.hitButton.value = "simpanPelarasanD";
	doAjaxCall${formName}("");
}
function paparPelarasanD(idAkaun){
	document.${formName}.mode.value = "viewPelarasanD";
	document.${formName}.idAkaun.value = idAkaun;
	doAjaxCall${formName}("");
}
function kemaskiniPelarasanD(){
	document.${formName}.mode.value = "updatePelarasanD";
	doAjaxCall${formName}("");
}
function hapusPelarasanD(){

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "viewPelarasanD";
		return;
	}

	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "hapusPelarasanD";
	doAjaxCall${formName}("");
}
function simpanKemaskiniPelarasanD(){
	if(document.${formName}.txtTarikh.value == ""){
		alert('Sila masukkan Tarikh.');
  		document.${formName}.txtTarikh.focus();
		return;
	}
	if(document.${formName}.socJenisPelarasan.value != "C" && document.${formName}.socJenisPelarasan.value != "D" && document.${formName}.socJenisPelarasan.value != "K" && document.${formName}.socJenisPelarasan.value != "R"){
		alert('Sila pilih Jenis Pelarasan.');
  		document.${formName}.socJenisPelarasan.focus();
		return;
	}
	if(document.${formName}.txtAmaun.value == ""){
		alert('Sila masukkan Amaun.');
  		document.${formName}.txtAmaun.focus();
		return;
	}

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "updatePelarasanD";
		return;
	}

	document.${formName}.mode.value = "viewPelarasanD";
	document.${formName}.hitButton.value = "simpanKemaskiniPelarasanD";
	doAjaxCall${formName}("");
}
function batalKemaskiniPelarasanD(){
	document.${formName}.mode.value = "viewPelarasanD";
	doAjaxCall${formName}("");
}
</script>
<!-- PELARASAN SEWA -->
<script>

function paparPelarasan(idAkaun){
	document.${formName}.mode.value = "viewPelarasan";
	document.${formName}.idAkaun.value = idAkaun;
	doAjaxCall${formName}("");
}
function pelarasan(){
	document.${formName}.mode.value = "newPelarasan";
	doAjaxCall${formName}("");
}
function simpanPelarasan(){

	if(document.${formName}.txtTarikh.value == ""){
		alert('Sila masukkan Tarikh.');
  		document.${formName}.txtTarikh.focus();
		return;
	}
	if(document.${formName}.socJenisPelarasan.value != "C" && document.${formName}.socJenisPelarasan.value != "D" && document.${formName}.socJenisPelarasan.value != "K" && document.${formName}.socJenisPelarasan.value != "R"){
		alert('Sila pilih Jenis Pelarasan.');
  		document.${formName}.socJenisPelarasan.focus();
		return;
	}
	if(document.${formName}.txtAmaun.value == ""){
		alert('Sila masukkan Amaun.');
  		document.${formName}.txtAmaun.focus();
		return;
	}

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "newPelarasan";
		return;
	}

	document.${formName}.mode.value = "viewPelarasan";
	document.${formName}.hitButton.value = "simpanPelarasan";
	doAjaxCall${formName}("");
}
function paparPelarasan(idAkaun){
	document.${formName}.mode.value = "viewPelarasan";
	document.${formName}.idAkaun.value = idAkaun;
	doAjaxCall${formName}("");
}
function kemaskiniPelarasan(){
	document.${formName}.mode.value = "updatePelarasan";
	doAjaxCall${formName}("");
}
function hapusPelarasan(){

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "viewPelarasan";
		return;
	}

	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "hapusPelarasan";
	doAjaxCall${formName}("");
}
function simpanKemaskiniPelarasan(){
	if(document.${formName}.txtTarikh.value == ""){
		alert('Sila masukkan Tarikh.');
  		document.${formName}.txtTarikh.focus();
		return;
	}
	if(document.${formName}.socJenisPelarasan.value != "C" && document.${formName}.socJenisPelarasan.value != "D" && document.${formName}.socJenisPelarasan.value != "K" && document.${formName}.socJenisPelarasan.value != "R"){
		alert('Sila pilih Jenis Pelarasan.');
  		document.${formName}.socJenisPelarasan.focus();
		return;
	}
	if(document.${formName}.txtAmaun.value == ""){
		alert('Sila masukkan Amaun.');
  		document.${formName}.txtAmaun.focus();
		return;
	}

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "updatePelarasan";
		return;
	}

	document.${formName}.mode.value = "viewPelarasan";
	document.${formName}.hitButton.value = "simpanKemaskiniPelarasan";
	doAjaxCall${formName}("");
}
function batalKemaskiniPelarasan(){
	document.${formName}.mode.value = "viewPelarasan";
	doAjaxCall${formName}("");
}

<!-- MAKLUMAT PERJANJIAN -->
function daftarPerjanjian(idFail, idHasil, flagSkrin) {

	var url = "../x/${securityToken}/ekptg.view.php2.FrmREVPopupPerjanjianSewaView?actionPopup=new&idFail="+idFail+"&idHasil="+idHasil+"&flagSkrin="+flagSkrin;
    var hWnd = window.open(url,'printuser','width=1000,height=400, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function paparPerjanjian(idPerjanjian,idHasil, idFail, flagSkrin) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmREVPopupPerjanjianSewaView?idPerjanjian="+idPerjanjian+"&idHasil="+idHasil+"&idFail="+idFail+"&flagSkrin="+flagSkrin;
    var hWnd = window.open(url,'printuser','width=1000,height=400, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function doRefreshScreen() {
	doAjaxCall${formName}("");
}
</script>
<!-- MAKLUMAT ABT -->
<script>
function doSimpanKemaskiniABT() {

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}

	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSimpanKemaskiniABT";
	document.${formName}.submit();
}
</script>

<script>
function cetakSuratTuntutanTunggakanSewa(idNotis) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmREVPopupCetakLaporanView?idNotis="+idNotis+"&report=SuratTuntutanTunggakanSewa";
    var hWnd = window.open(url,'printuser','width=1000,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratRampasanDeposit(idNotis) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmREVPopupCetakLaporanView?idNotis="+idNotis+"&report=SuratRampasanDeposit";
    var hWnd = window.open(url,'printuser','width=1000,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratCekTidakSah(idBayaran) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmREVPopupPilihPegawaiView?idBayaran="+idBayaran+"&report=SuratCekTidakSah";
    var hWnd = window.open(url,'printuser','width=1000,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratCekTamatTempoh(idBayaran) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmREVPopupPilihPegawaiView?idBayaran="+idBayaran+"&report=SuratCekTamatTempoh";
    var hWnd = window.open(url,'printuser','width=1000,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function janaSuratPemulanganCek() {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmREVPopupCetakLaporanView?idAkaun="+document.${formName}.idAkaun.value+"&report=SuratPemulanganSemula";
    var hWnd = window.open(url,'printuser','width=1000,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakInvois(idAkaun) {

	var url = "../servlet/ekptg.report.php2.REVInvoisSewa?ID_AKAUN="+idAkaun;
    var hWnd = window.open(url,'printuser','width=1000,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>

<script>
function hapusNotis(idNotis){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	document.${formName}.idNotis.value = idNotis;
	document.${formName}.hitButton.value = "hapusNotis";
	doAjaxCall${formName}("");
}
function doRefreshScreen() {
	doAjaxCall${formName}("");
}
function goUpperScreen() {
	window.scrollTo(0, 0);
}

function janaMaklumatLot() {
	var strTajuk = " ";
	var milikOrRizab = " ";
	var str1  = document.${formName}.noLotTanah.value;
	var str2  = document.${formName}.noMilikTanah.value;
	var str3  = document.${formName}.namaMukimTanah.value;
	var str4  = document.${formName}.namaDerahTanah.value;
	var str5  = document.${formName}.namaNegeriTanah.value;
	var str6 = document.${formName}.noWartaTanah.value;
	var strTujuan = document.${formName}.namatujuan.value;
	var statusRizabTnh = document.${formName}.status.value;
	var namaPemohon = document.${formName}.txtNama.value;

	if(statusRizabTnh == 'MILIK') {
		milikOrRizab = str2;
	} else if(statusRizabTnh == 'RIZAB') {
		milikOrRizab = str6;
	}

	strTajuk = str1 +", " + milikOrRizab +", " + str3 + ", "+ str4 + ", " + str5  + " OLEH " + namaPemohon + " UNTUK TUJUAN " + strTujuan ;
	document.${formName}.txtMaklumatLot.value = strTajuk;
	}
</script>
