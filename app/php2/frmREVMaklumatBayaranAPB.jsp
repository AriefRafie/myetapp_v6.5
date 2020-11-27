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
  <input type="hidden" name="idFail"  id="idFail" value="$idFail"/>
  <input type="hidden" name="idJadualPertama" id="idJadualPertama" value="$!idJadualPertama">
  <input type="hidden" name="idAkaun" id="idAkaun" value="$idAkaun"/>
  <input type="hidden" name="idPemohon" id="idPemohon" value="$idPemohon">
  <input type="hidden" name="mode" id="mode" value="$mode"/>
  <input type="hidden" name="actionHasil" id="actionHasil" value="$actionHasil"/>
  <input type="hidden" name="hitButton" id="hitButton"/>
  <input type="hidden" name="selectedTabUpper" id="selectedTabUpper" value="$selectedTabUpper"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td>#parse("app/php2/frmAPBHeader.jsp") </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><div id="TabbedPanels1" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <li onClick="doChangeTabUpper(0);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT DEPOSIT PASIR</li>
          <li onClick="doChangeTabUpper(1);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT FI PERMOHONAN</li>
          <li onClick="doChangeTabUpper(2);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT FI LESEN</li>
          <li onClick="doChangeTabUpper(3);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT ROYALTI PASIR</li>
        </ul>
        <div class="TabbedPanelsContentGroup">
          <div class="TabbedPanelsContent">#parse("app/php2/frmREVMaklumatDepositAPB.jsp")</div>
          <div class="TabbedPanelsContent">#parse("app/php2/frmREVMaklumatFiPermohonan.jsp")</div>
          <div class="TabbedPanelsContent">#parse("app/php2/frmREVMaklumatFiLesen.jsp")</div>
          <div class="TabbedPanelsContent">#parse("app/php2/frmREVMaklumatRoyalti.jsp")</div>
        </div>
      </div></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>

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
function batal(){
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function doBatalKemaskini(){
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function doChangeJenisBayaran() {
	doAjaxCall${formName}("doChangeJenisBayaran");
}
function doChangeCaraBayar() {
	doAjaxCall${formName}("doChangeCaraBayar");
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

function janaPenyataAkaunDepositPasir(idHasil) {

	var url = "../servlet/ekptg.report.php2.REVPenyataAkaunDepositPasir?idJadualPertama="+idHasil;
    var hWnd = window.open(url,'printuser','width=1000,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function janaPenyataAkaunFiPermohonan(idHasil) {

	var url = "../servlet/ekptg.report.php2.REVPenyataAkaunFiPermohonan?idJadualPertama="+idHasil;
    var hWnd = window.open(url,'printuser','width=1000,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function janaPenyataAkaunFiLesen(idHasil) {

	var url = "../servlet/ekptg.report.php2.REVPenyataAkaunFiLesen?idJadualPertama="+idHasil;
    var hWnd = window.open(url,'printuser','width=1000,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function janaPenyataAkaunRoyaltiPasir(idHasil) {

	var url = "../servlet/ekptg.report.php2.REVPenyataAkaunRoyaltiPasir?idJadualPertama="+idHasil;
    var hWnd = window.open(url,'printuser','width=1000,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>
<!-- PEMBAYARAN DEPOSIT PASIR-->
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
	document.${formName}.hitButton.value = "hapusBayaran";
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

<!-- PEMBAYARAN FI PERMOHONAN-->
<script>
function paparBayaranFiPermohonan(idAkaun){
	document.${formName}.mode.value = "viewBayaranFiPermohonan";
	document.${formName}.idAkaun.value = idAkaun;
	doAjaxCall${formName}("");
}
function pemBayaranFiPermohonan(){
	document.${formName}.mode.value = "newBayaranFiPermohonan";
	doAjaxCall${formName}("");
}
function simpanBayaranFiPermohonan(){

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
		document.${formName}.mode.value = "newBayaranFiPermohonan";
		return;
	}

	document.${formName}.mode.value = "viewBayaranFiPermohonan";
	document.${formName}.hitButton.value = "simpanBayaranFiPermohonan";
	doAjaxCall${formName}("");
}
function paparBayaranFiPermohonan(idAkaun){
	document.${formName}.mode.value = "viewBayaranFiPermohonan";
	document.${formName}.idAkaun.value = idAkaun;
	doAjaxCall${formName}("");
}
function kemaskiniBayaranFiPermohonan(){
	document.${formName}.mode.value = "updateBayaranFiPermohonan";
	doAjaxCall${formName}("");
}
function hapusBayaranFiPermohonan(){

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "viewBayaranFiPermohonan";
		return;
	}

	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "hapusBayaran";
	doAjaxCall${formName}("");
}
function simpanKemaskiniBayaranFiPermohonan(){
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
		document.${formName}.mode.value = "updateBayaranFiPermohonan";
		return;
	}

	document.${formName}.mode.value = "viewBayaranFiPermohonan";
	document.${formName}.hitButton.value = "simpanKemaskiniBayaranFiPermohonan";
	doAjaxCall${formName}("");
}
function batalKemaskiniBayaranFiPermohonan(){
	document.${formName}.mode.value = "viewBayaranFiPermohonan";
	doAjaxCall${formName}("");
}
</script>

<!-- PEMBAYARAN FI LESEN-->
<script>
function paparBayaranFiLesen(idAkaun){
	document.${formName}.mode.value = "viewBayaranFiLesen";
	document.${formName}.idAkaun.value = idAkaun;
	doAjaxCall${formName}("");
}
function pemBayaranFiLesen(){
	document.${formName}.mode.value = "newBayaranFiLesen";
	doAjaxCall${formName}("");
}
function simpanBayaranFiLesen(){

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
		document.${formName}.mode.value = "newBayaranFiLesen";
		return;
	}

	document.${formName}.mode.value = "viewBayaranFiLesen";
	document.${formName}.hitButton.value = "simpanBayaranFiLesen";
	doAjaxCall${formName}("");
}
function paparBayaranFiLesen(idAkaun){
	document.${formName}.mode.value = "viewBayaranFiLesen";
	document.${formName}.idAkaun.value = idAkaun;
	doAjaxCall${formName}("");
}
function kemaskiniBayaranFiLesen(){
	document.${formName}.mode.value = "updateBayaranFiLesen";
	doAjaxCall${formName}("");
}
function hapusBayaranFiLesen(){

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "viewBayaranFiLesen";
		return;
	}

	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "hapusBayaran";
	doAjaxCall${formName}("");
}
function simpanKemaskiniBayaranFiLesen(){
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
		document.${formName}.mode.value = "updateBayaranFiLesen";
		return;
	}

	document.${formName}.mode.value = "viewBayaranFiLesen";
	document.${formName}.hitButton.value = "simpanKemaskiniBayaranFiLesen";
	doAjaxCall${formName}("");
}
function batalKemaskiniBayaranFiLesen(){
	document.${formName}.mode.value = "viewBayaranFiLesen";
	doAjaxCall${formName}("");
}
</script>

<!-- PEMBAYARAN ROYALTI-->
<script>
function paparBayaranRoyalti(idAkaun){
	document.${formName}.mode.value = "viewBayaranRoyalti";
	document.${formName}.idAkaun.value = idAkaun;
	doAjaxCall${formName}("");
}
function pemBayaranRoyalti(){
	document.${formName}.mode.value = "newBayaranRoyalti";
	doAjaxCall${formName}("");
}
function simpanBayaranRoyalti(){

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
		document.${formName}.mode.value = "newBayaranRoyalti";
		return;
	}

	document.${formName}.mode.value = "viewBayaranRoyalti";
	document.${formName}.hitButton.value = "simpanBayaranRoyalti";
	doAjaxCall${formName}("");
}
function paparBayaranRoyalti(idAkaun){
	document.${formName}.mode.value = "viewBayaranRoyalti";
	document.${formName}.idAkaun.value = idAkaun;
	doAjaxCall${formName}("");
}
function kemaskiniBayaranRoyalti(){
	document.${formName}.mode.value = "updateBayaranRoyalti";
	doAjaxCall${formName}("");
}
function hapusBayaranRoyalti(){

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "viewBayaranRoyalti";
		return;
	}

	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "hapusBayaran";
	doAjaxCall${formName}("");
}
function simpanKemaskiniBayaranRoyalti(){
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
		document.${formName}.mode.value = "updateBayaranRoyalti";
		return;
	}

	document.${formName}.mode.value = "viewBayaranRoyalti";
	document.${formName}.hitButton.value = "simpanKemaskiniBayaranRoyalti";
	doAjaxCall${formName}("");
}
function batalKemaskiniBayaranRoyalti(){
	document.${formName}.mode.value = "viewBayaranRoyalti";
	doAjaxCall${formName}("");
}
</script>

<script>
function janaBorangPenyerahanAPBPelesen(idJadualPertama) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmREVPopupCetakLaporanView?report=BorangPenyerahanAPBPelesen&idJadualPertama="+idJadualPertama;
    var hWnd = window.open(url,'printuser','width=1000,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>

