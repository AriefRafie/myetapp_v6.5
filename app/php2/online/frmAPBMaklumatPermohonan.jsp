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
  <input name="idPemohon" type="hidden" id="idPemohon" value="$idPemohon"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="idPengarah" type="hidden" id="idPengarah" value="$idPengarah"/>
  <input name="idPembeliPasir" type="hidden" id="idPembeliPasir" value="$idPembeliPasir"/>
  <input name="idProjek" type="hidden" id="idProjek" value="$idProjek"/>
  <input name="idPakar" type="hidden" id="idPakar" value="$idPakar"/>
  <input name="idKoordinat" type="hidden" id="idKoordinat" value="$idKoordinat"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="modePopup" type="hidden" id="modePopup" value="$modePopup"/>
  <input name="flagPopup" type="hidden" id="flagPopup" value="$flagPopup"/>
  <input name="selectedTabUpper" type="hidden" id="selectedTabUpper" value="$selectedTabUpper"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
  <input name="actionOnline" type="hidden" id="actionOnline" value="$actionOnline"/>
  <input name="kategori" type="hidden" id="kategori" value="$!pemohon.get("kategoriPemohon")"/>
  <input name="idDokumen" type="hidden" id="idDokumen" value="$!idDokumen"/>
  <input name="namaPemohon" type="hidden" id="namaPemohon" value="$namaPemohon"/>
  <input name="noLesen" type="hidden" id="noLesen" value="$noLesen"/>
  <input name="idJadualKeduaLesen" type="hidden" id="idJadualKeduaLesen" value="$idJadualKeduaLesen"/>
  
</p>

<body onLoad = $onload >
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($idFail != '')
  <tr>
    <td> #parse("app/php2/online/frmAPBHeader.jsp") </td>
  </tr>
  #end
  #if ($idFail != '')
  <tr>
    <td><div id="TabbedPanels1" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <li onClick="doChangeTab(0);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT PERMOHONAN</li>
          <li onClick="doChangeTab(1);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT PEMBELI PASIR</li>
          <li onClick="doChangeTab(2);" class="TabbedPanelsTab" tabindex="0">SENARAI SEMAK APB</li>
<!--           <li onClick="doChangeTab(3);" class="TabbedPanelsTab" tabindex="0">LAMPIRAN</li> -->
          <li onClick="doChangeTab(4);" class="TabbedPanelsTab" tabindex="0">PENGESAHAN PERMOHONAN</li>
        </ul>
        <div class="TabbedPanelsContentGroup">
          <div class="TabbedPanelsContent"> #if ($selectedTabUpper == '0')
            #parse("app/php2/online/frmAPBMaklumatPermohonanPermohonan.jsp")
            #end </div>
          <div class="TabbedPanelsContent"> #if ($selectedTabUpper == '1')
            #parse("app/php2/online/frmAPBMaklumatPermohonanPembeliPasir.jsp")
            #end </div>
  				<div class="TabbedPanelsContent"> #if ($selectedTabUpper == '2')
						#parse("app/php2/online/frmAPBSenaraiSemakOnline.jsp") 
						#end</div>
<!-- 					<div class="TabbedPanelsContent"> #if ($selectedTabUpper == '3') -->
<!--   					#parse("app/php2/online/frmAPBMaklumatLampiranOnline.jsp") -->
<!--   					#end</div> -->
					<div class="TabbedPanelsContent">#if ($selectedTabUpper == '4')
					
	<fieldset>	
				<legend>
				PENGESAHAN PERMOHONAN
				</legend>
				<table width="100%" border="0">
				<tr>
					<td width="5%"></td>
					<td width="28%" >Negeri</td>
					<td width="1%">:</td>
					<td width="70%"><strong>$!maklumatPejabat.get("negeri")</strong></td>
	</tr>
	  <tr>
		<td></td>
		<td >Daerah</td>
		<td>:</td>
		<td><strong>$!maklumatPejabat.get("daerah")</strong></td>
	</tr>
	<tr>
		<td></td>
		<td >Pejabat</td>
		<td>:</td>
		<td><span style="font-weight: bold">$!maklumatPejabat.get("namaPejabat")</span></td>
	</tr>

	<tr>
		<td width="1%">&nbsp;</td>
		<td width="28%">Alamat</td>
		<td width="1%">:</td>
		<td width="70%"><strong>$!maklumatPejabat.get("alamat1")</strong></td>
	</tr>

	<tr>
		<td width="1%">&nbsp;</td>
		<td width="28%">&nbsp;</td>
		<td width="1%">&nbsp;</td>
		<td width="70%"><strong>$!maklumatPejabat.get("alamat2")</strong></td>
	</tr>
	
	<tr>
		<td width="1%">&nbsp;</td>
		<td width="28%">&nbsp;</td>
		<td width="1%">&nbsp;</td>
		<td width="70%"><strong>$!maklumatPejabat.get("alamat3")</strong></td>
	</tr>
	
	<tr>
		<td width="1%">&nbsp;</td>
		<td width="28%">Poskod</td>
		<td width="1%">:</td>
		<td width="70%"><strong>$!maklumatPejabat.get("poskod")</strong></td>
	</tr>
	
	<tr>
		<td width="1%">&nbsp;</td>
		<td width="28%">No. Telefon</td>
		<td width="1%">:</td>
		<td width="70%"><strong>$!maklumatPejabat.get("noTel")</strong></td>
	</tr>
	
	<tr>
		<td width="1%">&nbsp;</td>
		<td width="28%">No. Faks</td>
		<td width="1%">:</td>
		<td width="70%"><strong>$!maklumatPejabat.get("noFax")</strong></td>
  </tr>
  
	<tr>
		<td width="1%">&nbsp;</td>
		<td width="28%">Emel</td>
		<td width="1%">:</td>
		<td ><strong>$!maklumatPejabat.get("emel")</strong></td>
	</tr>
	
	<tr>
	<td></td>
		<td ></td>
		<td valign="top">
        #if ($idStatus == '')<input type="checkbox" name="pengesahan" id="pengesahan" >#end
        
        #if ($idStatus != '')
        <input type="checkbox" name="pengesahan" id="pengesahan" checked disabled>#end</td>
        <td>Kami $!pemohon.get("namaPemohon"), MyCOID $!pemohon.get("noPengenalan") dengan ini maklumat yang diberikan dalam borang ini adalah benar, betul dan lengkap.</td>
	</tr>
	
	<tr>
	<td></td>
  	<td></td>
		<td valign="top" colspan=2>
	#if ($idStatus == '')
    <input type="button" name="cdmCetak" id="cdmCetakBorang" value="Cetak Borang Permohonan" onClick="javascript:cetakBorangPermohonan('$idPermohonan')"/>
    <input type="button" name="cmdHantar" id="cmdHantar" value="Hantar &amp; Emel" onClick="doHantarEmel()"/>
    <input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onClick="doHapus()"/>
    #else
    #if($idStatus == '1610207') <!-- 1610207 -->
    <input type="button" name="cmdRenewLesen" id="cmdRenewLesen" value="Pembaharuan Lesen" onClick="javascript:daftarPembaharuan('$!idFail','$!idPermohonan','$!idStatus') "/>  
    <input type="button" name="cmdBorangA" id="cmdBorangA" value="Borang A" onClick="javascript:daftarPembaharuanBorangA('$!idFail','$!idPermohonan','$!idStatus','$!namaPemohon','$!idJadualKeduaLesen','$!noLesen') "/>   
    <input type="button" name="cmdBorangB" id="cmdBorangB" value="Borang B" onClick="javascript:daftarPembaharuanBorangB('$!idFail','$!idPermohonan','$!idStatus','$!namaPemohon','$!idJadualKeduaLesen','$!noLesen') "/>    	 
    #else
    #if ($idStatus !='' && $idStatus != '1610207')
    <input type="button" name="cdmCetak" id="cdmCetakBorang" value="Cetak Borang Permohonan" onClick="javascript:cetakBorangPermohonan('$idPermohonan')"/>
    <input type="button" name="cdmCetak" id="cdmCetakPengesahan" value="Cetak Pengesahan Permohonan" onClick="javascript:cetakPengesahanPermohonan('$idPermohonan')"/>
    #end
    #end
    #end
	</td>
	</tr>
				</table>
				</fieldset>
#end
</td></tr>#end</table>
</div>

<!--<div id="calculateTotalPercentPengarah_result"></div>
<fieldset id="tableReport" style="display:;"-->
<!--<legend><strong>SENARAI LAPORAN</strong></legend>  -->
<!--<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakPengesahanPermohonan('$idPermohonan')"> Pengesahan Permohonan </a></td>
  </tr>
</table>
</fieldset>-->

<script type="text/javascript">
#if ($idFail != '')
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabUpper});
#end
</script>

<script>
function doChangeTab(tabId) {
	//document.${formName}.actionOnline.value = "seterusnya";]
	document.${formName}.mode.value = "view";
	document.${formName}.selectedTabUpper.value = tabId;
	document.${formName}.submit();
// 	doAjaxCall${formName}("");
}
function doBacklist() {
	document.${formName}.actionOnline.value = "";
	document.${formName}.submit();
}
function doChangeNegeri() {
// 	document.${formName}.submit("doChangeNegeri");
 	doAjaxCall${formName}("doChangeNegeri");
}
//Baru Add 29092020
/*function doChangeWarganegara() {
	doAjaxCall${formName}("doChangeWarganegara");
}*/

function getLainlain(id) {

	if (document.${formName}.socWarganegara.value == "13")
	{

		document.getElementById(id).style.display="";
	}
	
	else
	{
		
		document.getElementById(id).style.display="none";
		
	}
}

function sembunyikanLainLainNegara(id,id2) {

	if (document.${formName}.socWarganegara.value == "13")
	{

		document.getElementById(id).style.display="";
	}
	
	else
	{
		
		document.getElementById(id).style.display="none";
		
	}
	
	if (document.${formName}.socBangsa.value == "7")
	{

		document.getElementById(id2).style.display="";
	}
	
	else
	{
		
		document.getElementById(id).style.display="none";
		
	}
	if (document.${formName}.socBangsa.value == "")
	{

		document.getElementById(id3).style.display="";
	}
	
	else
	{
		
		document.getElementById(id3).style.display="none";
		
	}
	
}

function getLainLainBangsa(id) {

	if (document.${formName}.socBangsa.value == "7")
	{

		document.getElementById(id).style.display="";
	}
	
	else
	{

		document.getElementById(id).style.display="none";
	}
}

/*function sembnyknLainLainBangsa(id) {

	if (document.${formName}.socBangsa.value == "7")
	{

		document.getElementById(id).style.display="";
	}
	
	else
	{
		
		document.getElementById(id).style.display="none";
		
	}
}*/
//END ADD

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
/*function seterusnya(){

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "view";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSeterusnya";
	document.${formName}.submit();
// 	doAjaxCall${formName}("");
}*/
</script>

<!-- PEMBELI PASIR -->
<script>
function tambahPembeliPasir() {
	document.${formName}.mode.value = "newPembeliPasir";
	document.${formName}.submit();
// 	doAjaxCall${formName}("");
}
function batalPembeliPasir() {
	document.${formName}.mode.value = "view";
	document.${formName}.submit();
// 	doAjaxCall${formName}("");
}

function simpanPembeliPasir(){
	
	if(document.${formName}.txtNamaPembeliPasir.value == ""){
		alert('Sila masukkan Nama.');
  		document.${formName}.txtNamaPembeliPasir.focus(); 
		return; 
	}
	if(document.${formName}.txtAlamat1PembeliPasir.value == ""){
		alert('Sila masukkan Alamat.');
  		document.${formName}.txtAlamat1PembeliPasir.focus(); 
		return; 
	}
	if(document.${formName}.txtPoskodPembeliPasir.value == ""){
		alert('Sila masukkan Poskod.');
  		document.${formName}.txtPoskodPembeliPasir.focus(); 
		return; 
	}
	if(document.${formName}.socNegeriPembeliPasir.value == ""){
		alert('Sila pilih Negeri.');
  		document.${formName}.socNegeriPembeliPasir.focus(); 
		return; 
	}
	if(document.${formName}.socBandarPembeliPasir.value == ""){
		alert('Sila pilih Bandar.');
  		document.${formName}.socBandarPembeliPasir.focus(); 
		return; 
	}
	if(document.${formName}.txtNoTelPembeliPasir.value == ""){
		alert('Sila masukkan No. Telefon.');
  		document.${formName}.txtNoTelPembeliPasir.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "newPembeliPasir";
		return;
	}
	
	document.${formName}.mode.value = "newPembeliPasir";
	document.${formName}.hitButton.value = "doSimpanPembeliPasir";
	document.${formName}.submit();
}
function batalPembeliPasir(){
	document.${formName}.mode.value = "view";
	document.${formName}.submit();
// 	doAjaxCall${formName}("");
}
function kemaskiniPembeliPasir(){
	document.${formName}.mode.value = "updatePembeliPasir";
	document.${formName}.submit();
// 	doAjaxCall${formName}("");
}

function simpanKemaskiniPembeliPasir(){

	if(document.${formName}.txtNamaPembeliPasir.value == ""){
		alert('Sila masukkan Nama.');
  		document.${formName}.txtNamaPembeliPasir.focus(); 
		return; 
	}
	if(document.${formName}.txtAlamat1PembeliPasir.value == ""){
		alert('Sila masukkan Alamat.');
  		document.${formName}.txtAlamat1PembeliPasir.focus(); 
		return; 
	}
	if(document.${formName}.txtPoskodPembeliPasir.value == ""){
		alert('Sila masukkan Poskod.');
  		document.${formName}.txtPoskodPembeliPasir.focus(); 
		return; 
	}
	if(document.${formName}.socNegeriPembeliPasir.value == ""){
		alert('Sila pilih Negeri.');
  		document.${formName}.socNegeriPembeliPasir.focus(); 
		return; 
	}
	if(document.${formName}.socBandarPembeliPasir.value == ""){
		alert('Sila pilih Bandar.');
  		document.${formName}.socBandarPembeliPasir.focus(); 
		return; 
	}
	if(document.${formName}.txtNoTelPembeliPasir.value == ""){
		alert('Sila masukkan No. Telefon.');
  		document.${formName}.txtNoTelPembeliPasir.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "updatePembeliPasir";
		return;
	}
	
	document.${formName}.mode.value = "viewPembeliPasir";
	document.${formName}.hitButton.value = "doSimpanKemaskiniPembeliPasir";
	document.${formName}.submit();
// 	doAjaxCall${formName}("");
}

function batalKemaskiniPembeliPasir(){
	document.${formName}.mode.value = "viewPembeliPasir";
	document.${formName}.submit();
// 	doAjaxCall${formName}("");
}
function hapusPembeliPasir(){

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "viewPembeliPasir";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doHapusPembeliPasir";
	document.${formName}.submit();
// 	doAjaxCall${formName}("");
}
function paparPembeliPasir(idPembeliPasir){
	document.${formName}.idPembeliPasir.value = idPembeliPasir;
	document.${formName}.mode.value = "viewPembeliPasir";
	document.${formName}.submit();
// 	doAjaxCall${formName}("");
}
</script>

<!-- PEMOHON -->
<script>
function kemaskiniPemohon() {
	document.${formName}.mode.value = "update";
	document.${formName}.submit();
// 	doAjaxCall${formName}("");
}
function batalKemaskiniPemohon() {
	document.${formName}.mode.value = "view";
	document.${formName}.submit();
// 	doAjaxCall${formName}("");
}
</script>

<!-- PENGARAH -->
<script>
function tambahPengarah() {
	document.${formName}.mode.value = "newPengarah";
	document.${formName}.submit();
// 	doAjaxCall${formName}("");
}
function batalPengarah() {
	document.${formName}.mode.value = "view";
	document.${formName}.submit();
// 	doAjaxCall${formName}("");
}

function checkPercentage(){;
	if(parseInt(document.${formName}.txtSaham.value) > 100){
		alert('Sila masukkan nilai peratusan saham dengan betul.');
		document.${formName}.txtSaham.value = ""; 
  		document.${formName}.txtSaham.focus(); 
		return; 
	}
}

function simpanPengarah(){

	if(document.${formName}.socWarganegara.value == ""){
		alert('Sila pilih Warganegara.');
  		document.${formName}.socWarganegara.focus(); 
		return; 
	}
	if(document.${formName}.txtNamaPengarah.value == ""){
		alert('Sila masukkan Nama.');
  		document.${formName}.txtNamaPengarah.focus(); 
		return; 
	}
	if(document.${formName}.socJenisPengenalan.value == ""){
		alert('Sila pilih Jenis Pengenalan.');
  		document.${formName}.socJenisPengenalan.focus(); 
		return; 
	}
	if(document.${formName}.txtNoPengenalan.value == ""){
		alert('Sila masukkan No. Pengenalan.');
  		document.${formName}.txtNoPengenalan.focus(); 
		return; 
	}
	if(document.${formName}.socBangsa.value == ""){
		alert('Sila pilih Bangsa.');
  		document.${formName}.socBangsa.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "newPengarah";
		return;
	}
	
	document.${formName}.mode.value = "newPengarah";
	document.${formName}.hitButton.value = "doSimpanPengarah";
	document.${formName}.submit();
// 	doAjaxCall${formName}("");
}

function batalPengarah(){
	document.${formName}.mode.value = "view";
	document.${formName}.submit();
// 	doAjaxCall${formName}("");
}

function kemaskiniPengarah(){
	document.${formName}.mode.value = "updatePengarah";
	document.${formName}.submit();
// 	doAjaxCall${formName}("");
}

function simpanKemaskiniPengarah(){

	if(document.${formName}.socWarganegara.value != "13"){
		document.${formName}.txtWarga.value="";
	}
	
	if(document.${formName}.socBangsa.value != "7"){
		document.${formName}.txtBangsa.value="";
	}

	if(document.${formName}.socWarganegara.value == ""){
		alert('Sila pilih Warganegara.');
  		document.${formName}.socWarganegara.focus(); 
		return; 
	}
	if(document.${formName}.txtNamaPengarah.value == ""){
		alert('Sila masukkan Nama.');
  		document.${formName}.txtNamaPengarah.focus(); 
		return; 
	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "updatePengarah";
		return;
	}
	
	document.${formName}.mode.value = "newPengarah";
	document.${formName}.hitButton.value = "doSimpanKemaskiniPengarah";
	document.${formName}.submit();
// 	doAjaxCall${formName}("");
}

function batalKemaskiniPengarah(){
	document.${formName}.mode.value = "viewPengarah";
	document.${formName}.submit();
// 	doAjaxCall${formName}("");
}

function hapusPengarah(){

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "viewPengarah";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doHapusPengarah";
	document.${formName}.submit();
// 	doAjaxCall${formName}("");
}

function paparPengarah(idPengarah){
	document.${formName}.idPengarah.value = idPengarah;
	document.${formName}.mode.value = "viewPengarah";
	document.${formName}.submit();
// 	doAjaxCall${formName}("");
}
function calculateTotalPercentPengarah() {
	url = "../servlet/ekptg.view.php2.FrmAPBServlet?command=calculateTotalPercentPengarah";
	actionName = "calculateTotalPercentPengarah";
	target = "calculateTotalPercentPengarah_result";
	doAjaxUpdater(document.${formName}, url, target, actionName);
}
function popupMsg(){
	alert('Jumlah peratusan saham yang dimasukkan telah melebihi 100%');
	document.${formName}.txtSaham.value = "";
}
</script>

<!-- PERMOHONAN -->
<script>
function kemaskiniPermohonan() {
	document.${formName}.actionOnline.value = "seterusnya";
	document.${formName}.mode.value = "update";
	document.${formName}.submit();	
 //	doAjaxCall${formName}("");//comment jap ye
}
function batalKemaskiniPermohonan() {
	document.${formName}.mode.value = "view";
	document.${formName}.submit();
// 	doAjaxCall${formName}("");
}
function simpanKemaskiniPermohonan() {
	
	/*if(document.${formName}.socJenisTujuan.value == ""){
		alert('Sila pilih Jenis Tujuan.');
  		document.${formName}.socJenisTujuan.focus(); 
		return; 
	}*/
	if(document.${formName}.socKaitanTujuan.value == ""){
		alert('Sila pilih Kaitan Tujuan.');
  		document.${formName}.socKaitanTujuan.focus(); 
		return; 
	}
	if(document.${formName}.txtTujuanPengambilan.value == ""){
		alert('Sila masukkan Tujuan.');
  		document.${formName}.txtTujuanPengambilan.focus(); 
		return; 
	}
	if(document.${formName}.socTempoh.value == "SILA PILIH"){
		alert('Sila pilih Tempoh Lesen Dipohon.');
  		document.${formName}.socTempoh.focus(); 
		return; 
	}
	if(document.${formName}.txtRingkasanPengalaman.value == ""){
		alert('Sila masukkan Ringkasan Pengalaman Pemohon.');
  		document.${formName}.txtRingkasanPengalaman.focus(); 
		return; 
	}
	if(document.${formName}.txtModalBenar.value == ""){
		alert('Sila masukkan Modal Dibenarkan.');
			document.${formName}.txtModalBenar.focus(); 
			return; 
		}
	if(document.${formName}.txtModalJelas.value == ""){
		alert('Sila masukkan Modal Jelas.');
		document.${formName}.txtModalJelas.focus(); 
		return; 
	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}

	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSimpanKemaskiniPermohonan";
	document.${formName}.submit();
	// 	doAjaxCall${formName}("");
}
function cekTarikhTerima(elmnt) {
//CHECK DATE   
	var str1  = document.${formName}.tarikhTerima.value;		   
	var dt1   = parseInt(str1.substring(0,2),10);
	var mon1  = parseInt(str1.substring(3,5),10)-1;
	var yr1   = parseInt(str1.substring(6,10),10);
	var tarikhTerima = new Date(yr1, mon1, dt1);
	
	var currentDate = new Date();
	
	if (tarikhTerima > currentDate){
		alert('Tarikh Terima tidak boleh melebihi dari tarikh hari ini.');
  		elmnt.value ="";
		document.${formName}.tarikhTerima.focus(); 
		return;
	}
}
function cekTarikhSurat(elmnt) {
//CHECK DATE   
	//CHECK DATE   
	var str1  = document.${formName}.tarikhTerima.value;		   
	var dt1   = parseInt(str1.substring(0,2),10);
	var mon1  = parseInt(str1.substring(3,5),10)-1;
	var yr1   = parseInt(str1.substring(6,10),10);
	var tarikhTerima = new Date(yr1, mon1, dt1);
	
	var str2  =  document.${formName}.tarikhSurat.value;		   
	var dt2   = parseInt(str2.substring(0,2),10);
	var mon2  = parseInt(str2.substring(3,5),10)-1;
	var yr2   = parseInt(str2.substring(6,10),10);
	var tarikhSurat = new Date(yr2, mon2, dt2);
	
	var currentDate = new Date();
	
	if (tarikhSurat > currentDate){
		alert('Tarikh Surat tidak boleh melebihi dari tarikh hari ini.');
		elmnt.value ="";
  		document.${formName}.tarikhSurat.focus(); 
		return;
	}
	if (tarikhSurat > tarikhTerima){
		alert('Tarikh Surat tidak boleh melebihi dari Tarikh Terima.');
		elmnt.value ="";
  		document.${formName}.tarikhSurat.focus(); 
		return;
	}
}
</script>

<!-- PROJEK -->
<script>
function tambahProjek() {
	document.${formName}.mode.value = "newProjek";
	document.${formName}.submit();
// 	doAjaxCall${formName}("");
}
function batalProjek() {
	document.${formName}.mode.value = "view";
	document.${formName}.submit();
// 	doAjaxCall${formName}("");
}
function simpanProjek(){
	
	if(document.${formName}.txtNamaProjek.value == ""){
		alert('Sila masukkan Nama.');
  		document.${formName}.txtNamaProjek.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "newProjek";
		return;
	}
	
	document.${formName}.mode.value = "newProjek";
	document.${formName}.hitButton.value = "doSimpanProjek";
	document.${formName}.submit();
// 	doAjaxCall${formName}("");
}
function kemaskiniProjek(){
	document.${formName}.mode.value = "updateProjek";
	document.${formName}.submit();
// 	doAjaxCall${formName}("");
}
function simpanKemaskiniProjek(){

	if(document.${formName}.txtNamaProjek.value == ""){
		alert('Sila masukkan Nama.');
  		document.${formName}.txtNamaProjek.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "updateProjek";
		return;
	}
	
	document.${formName}.mode.value = "viewProjek";
	document.${formName}.hitButton.value = "doSimpanKemaskiniProjek";
	document.${formName}.submit();
// 	doAjaxCall${formName}("");
}
function batalKemaskiniProjek(){
	document.${formName}.mode.value = "viewProjek";
	document.${formName}.submit();
// 	doAjaxCall${formName}("");
}
function hapusProjek(){

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "viewProjek";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doHapusProjek";
	doAjaxCall${formName}("");
}
function paparProjek(idProjek){
	document.${formName}.idProjek.value = idProjek;
	document.${formName}.mode.value = "viewProjek";
	document.${formName}.submit();
// 	doAjaxCall${formName}("");
}
</script>

<!-- PAKAR -->
<script>
function tambahPakar() {
	document.${formName}.mode.value = "newPakar";
	document.${formName}.submit();
// 	doAjaxCall${formName}("");
}
function batalPakar() {
	document.${formName}.mode.value = "view";
	document.${formName}.submit();
// 	doAjaxCall${formName}("");
}
function simpanPakar(){
	
	if(document.${formName}.txtNamaPakar.value == ""){
		alert('Sila masukkan Nama.');
  		document.${formName}.txtNamaPakar.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "newPakar";
		return;
	}
	
	document.${formName}.mode.value = "newPakar";
	document.${formName}.hitButton.value = "doSimpanPakar";
	document.${formName}.submit();
// 	doAjaxCall${formName}("");
}
function kemaskiniPakar(){
	document.${formName}.mode.value = "updatePakar";
	document.${formName}.submit();
// 	doAjaxCall${formName}("");
}
function simpanKemaskiniPakar(){

	if(document.${formName}.txtNamaPakar.value == ""){
		alert('Sila masukkan Nama.');
  		document.${formName}.txtNamaPakar.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "updatePakar";
		return;
	}
	
	document.${formName}.mode.value = "viewPakar";
	document.${formName}.hitButton.value = "doSimpanKemaskiniPakar";
	document.${formName}.submit();
// 	doAjaxCall${formName}("");
}
function batalKemaskiniPakar(){
	document.${formName}.mode.value = "viewPakar";
	document.${formName}.submit();
// 	doAjaxCall${formName}("");
}
function hapusPakar(){

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "viewPakar";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doHapusPakar";
	document.${formName}.submit();
// 	doAjaxCall${formName}("");
}
function paparPakar(idPakar){
	document.${formName}.idPakar.value = idPakar;
	document.${formName}.mode.value = "viewPakar";
	document.${formName}.submit();
// 	doAjaxCall${formName}("");
}
</script>

<!-- KOORDINAT -->
<script>
function tambahKoordinat() {
	document.${formName}.mode.value = "newKoordinat";
	document.${formName}.submit();
// 	doAjaxCall${formName}("");
}
function batalKoordinat() {
	document.${formName}.mode.value = "view";
	document.${formName}.submit();
// 	doAjaxCall${formName}("");
}
function simpanKoordinat(){
	
	if(document.${formName}.txtLabelTitik.value == ""){
		alert('Sila masukkan Label Koordinat.');
  		document.${formName}.txtLabelTitik.focus(); 
		return; 
	}
	if(document.${formName}.txtDarjahU.value == ""){
		alert('Sila masukkan Darjah U.');
  		document.${formName}.txtDarjahU.focus(); 
		return; 
	}
	if(document.${formName}.txtMinitU.value == ""){
		alert('Sila masukkan Minit U.');
  		document.${formName}.txtMinitU.focus(); 
		return; 
	}
	/*if(document.${formName}.txtSaatU.value == ""){
		alert('Sila masukkan Saat U.');
  		document.${formName}.txtSaatU.focus(); 
		return; 
	}*/
	if(document.${formName}.txtDarjahT.value == ""){
		alert('Sila masukkan Darjah T.');
  		document.${formName}.txtDarjahT.focus(); 
		return; 
	}
	if(document.${formName}.txtMinitT.value == ""){
		alert('Sila masukkan Minit T.');
  		document.${formName}.txtMinitT.focus(); 
		return; 
	}
	/*if(document.${formName}.txtSaatT.value == ""){
		alert('Sila masukkan Saat T.');
  		document.${formName}.txtSaatT.focus(); 
		return; 
	}*/
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "newKoordinat";
		return;
	}
	
	document.${formName}.mode.value = "newKoordinat";
	document.${formName}.hitButton.value = "doSimpanKoordinat";
	document.${formName}.submit();
// 	doAjaxCall${formName}("");
}
function kemaskiniKoordinat(){
	document.${formName}.mode.value = "updateKoordinat";
	document.${formName}.submit();
// 	doAjaxCall${formName}("");
}
function simpanKemaskiniKoordinat(){

	if(document.${formName}.txtLabelTitik.value == ""){
		alert('Sila masukkan Label Koordinat.');
  		document.${formName}.txtLabelTitik.focus(); 
		return; 
	}
	if(document.${formName}.txtDarjahU.value == ""){
		alert('Sila masukkan Darjah U.');
  		document.${formName}.txtDarjahU.focus(); 
		return; 
	}
	if(document.${formName}.txtMinitU.value == ""){
		alert('Sila masukkan Minit U.');
  		document.${formName}.txtMinitU.focus(); 
		return; 
	}
	if(document.${formName}.txtSaatU.value == ""){
		alert('Sila masukkan Saat U.');
  		document.${formName}.txtSaatU.focus(); 
		return; 
	}
	if(document.${formName}.txtDarjahT.value == ""){
		alert('Sila masukkan Darjah T.');
  		document.${formName}.txtDarjahT.focus(); 
		return; 
	}
	if(document.${formName}.txtMinitT.value == ""){
		alert('Sila masukkan Minit T.');
  		document.${formName}.txtMinitT.focus(); 
		return; 
	}
	if(document.${formName}.txtSaatT.value == ""){
		alert('Sila masukkan Saat T.');
  		document.${formName}.txtSaatT.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "updateKoordinat";
		return;
	}
	
	document.${formName}.mode.value = "viewKoordinat";
	document.${formName}.hitButton.value = "doSimpanKemaskiniKoordinat";
	document.${formName}.submit();
// 	doAjaxCall${formName}("");
}
function batalKemaskiniKoordinat(){
	document.${formName}.mode.value = "viewKoordinat";
	document.${formName}.submit();
// 	doAjaxCall${formName}("");
}
function hapusKoordinat(){

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "viewKoordinat";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doHapusKoordinat";
	document.${formName}.submit();
// 	doAjaxCall${formName}("");
}
function paparKoordinat(idKoordinat){
	document.${formName}.idKoordinat.value = idKoordinat;
	document.${formName}.mode.value = "viewKoordinat";
	document.${formName}.submit();
// 	doAjaxCall${formName}("");
}
function textCounter(field, countfield, maxlimit) {
   if (field.value.length > maxlimit) // if too long...trim it!
		 field.value = field.value.substring(0, maxlimit);
    // otherwise, update 'Baki Aksara' counter
	else
	 countfield.value = maxlimit - field.value.length;
}
function doHantarEmel(){
	if(pengesahan.checked != true){
		alert('Sila tanda pada checkbox untuk teruskan permohonan. ');
		return; 
	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doHantarEmel";
	document.${formName}.submit();
}
function doHapus(){
		
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "view";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doHapus";
	document.${formName}.actionOnline.value = "";
	document.${formName}.submit();
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
//Add pada 24/08/2020
function cetakBorangPermohonan(idPermohonan) {
	var url = "../servlet/ekptg.report.php2.online.PengesahanOnline?template=APBBorangPermohonan&folder=ONLINE&idPermohonan="+idPermohonan;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

//YG ASAL
	function cetakPengesahanPermohonan(idPermohonan) {
		var url = "../servlet/ekptg.report.php2.online.PengesahanOnline?template=APBPengesahanPermohonanOnline&folder=ONLINE&ID_PERMOHONAN="+idPermohonan;
	    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
	       hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();
	}

</script>

<script>
<!-- MAKLUMAT LAMPIRAN 
function daftarLampiran() {
	document.${formName}.action = "?_portal_module=ekptg.view.php2.online.FrmAPBOnlineSenaraiFailView";
	document.${formName}.method="POST";
	//document.${formName}.actionOnline = "seterusnya";
	document.${formName}.mode.value = "view";	
	document.${formName}.flagPopup.value = "openPopupLampiran";
	document.${formName}.mode.value = "new";
	document.${formName}.submit();
}-->
</script>

<!-- SENARAI SEMAK -->
<script>
function doSimpanKemaskiniSenaraiSemak() {
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "view";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSimpanKemaskiniSenaraiSemak";
	document.${formName}.submit();
}
function doBatalKemaskini() {
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}

function daftarPembaharuan(idFail,idPermohonan,idStatus){
	document.${formName}.actionOnline.value = "daftarBaruLesen";
	document.${formName}.idFail.value = idFail;
	document.${formName}.idPermohonan.value = idPermohonan;
	document.${formName}.idStatus.value = idStatus;
	document.${formName}.submit();
}
function daftarPembaharuanBorangA(idFail,idPermohonan,idStatus,namaPemohon,idJadualKeduaLesen,noLesen){

	document.${formName}.actionOnline.value = "daftarBaruBorangA";
	document.${formName}.idFail.value = idFail;
	document.${formName}.idPermohonan.value = idPermohonan;
	document.${formName}.idStatus.value = idStatus;
	document.${formName}.submit();
  
}
function daftarPembaharuanBorangB(idFail,idPermohonan,idStatus,namaPemohon,idJadualKeduaLesen,noLesen){
	
	//alert(idJadualKeduaLesen);
	document.${formName}.actionOnline.value = "daftarBaruBorangB";
	document.${formName}.idFail.value = idFail;
	document.${formName}.idPermohonan.value = idPermohonan;
	document.${formName}.idStatus.value = idStatus;
	document.${formName}.namaPemohon.value = namaPemohon;
	document.${formName}.submit();
}

</script>
$javascriptLampiran