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
  <input name="idHasil" type="hidden" id="idHasil" value="$idHasil"/>
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idAkaun" type="hidden" id="idAkaun" value="$idAkaun"/>
  <input type="hidden" name="idPemohon" id="idPemohon" value="$idPemohon">
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="actionHasil" type="hidden" id="actionHasil" value="$actionHasil"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
  <input name="selectedTabUpper" type="hidden" id="selectedTabUpper" value="$selectedTabUpper"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td>#parse("app/php2/frmREVHeaderPLP.jsp") </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><div id="TabbedPanels1" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <li onClick="doChangeTabUpper(0);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT PAMPASAN</li>
          <li onClick="doChangeTabUpper(1);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT PEMOHON</li>
        </ul>
        <div class="TabbedPanelsContentGroup">
          <div class="TabbedPanelsContent">#parse("app/php2/frmREVMaklumatPampasan.jsp")</div>
          <div class="TabbedPanelsContent">#parse("app/php2/frmREVMaklumatPemohon.jsp")</div>
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
	doAjaxCall${formName}("");
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
function doChangeNegeri() {
	doAjaxCall${formName}("doChangeNegeri");
}
function doChangeCaraBayar() {
	doAjaxCall${formName}("doChangeCaraBayar");
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
		if(document.${formName}.socNegeri.value == ""){
			alert('Sila masukan Negeri.');
  			document.${formName}.socNegeri.focus();
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

<!-- MAKLUMAT TINDAKAN MAHKAMAH -->
<script>
function doKemaskiniTindakanMahkamah(){
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}
function doSimpanKemaskiniTindakanMahkamah() {
	if(document.${formName}.tarikh_notis_tuntutan.value == ""){
			alert('Sila masukan Tarikh Notis Tuntutan.');
  			document.${formName}.tarikh_notis_tuntutan.focus();
			return;
		}
		if(document.${formName}.tarikh_notis_rampasan.value == ""){
			alert('Sila masukan Tarikh Notis Rampasan.');
  			document.${formName}.tarikh_notis_rampasan.focus();
			return;
		}

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}

	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSimpanKemaskiniTindakanMahkamah";
	document.${formName}.submit();
}
</script>

<!-- PEMBAYARAN PAMPASAN -->
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
</script>

<!-- PELARASAN PAMPASAN -->
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
	if(document.${formName}.socJenisPelarasan.value == ""){
		alert('Sila pilih Debit / Kredit.');
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
	if(document.${formName}.socJenisPelarasan.value == ""){
		alert('Sila pilih Debit / Kredit.');
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
</script>
