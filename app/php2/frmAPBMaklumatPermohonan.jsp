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
  <input name="selectedTabUpper" type="hidden" id="selectedTabUpper" value="$selectedTabUpper"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
</p>

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($idFail != '')
  <tr>
    <td> #parse("app/php2/frmAPBHeader.jsp") </td>
  </tr>
  #else
  <tr>
    <td><div class="warning">SILA PILIH FAIL DI SENARAI FAIL TERLEBIH DAHULU</div></td>
  </tr>
  #end
  #if ($idFail != '')
  <tr>
    <td><div id="TabbedPanels1" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <li onClick="doChangeTab(0);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT PERMOHONAN</li>
          <li onClick="doChangeTab(1);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT PEMOHON</li>
          <!-- <li onClick="doChangeTab(2);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT PEMBELI PASIR</li>-->
          <li onClick="doChangeTab(3);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT SENARAI SEMAK</li>
        </ul>
        <div class="TabbedPanelsContentGroup">
          <div class="TabbedPanelsContent"> 
          	#if ($selectedTabUpper == '0')
            	#parse("app/php2/frmAPBMaklumatPermohonanPermohonan.jsp")
            #end 
          </div>
          <div class="TabbedPanelsContent"> 
          	#if ($selectedTabUpper == '1')
            	#parse("app/php2/frmAPBMaklumatPermohonanPemohon.jsp")
            #end 
          </div>
          <!-- <div class="TabbedPanelsContent"> #if ($selectedTabUpper == '2')
            #parse("app/php2/frmAPBMaklumatPermohonanPembeliPasir.jsp")
            #end </div>-->
          <div class="TabbedPanelsContent"> 
          	#if ($selectedTabUpper == '3')
            	#parse("app/php2/frmAPBSenaraiSemak.jsp")
            #end 
          </div>
        </div>
    </div></td>
  </tr>
  #end
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>
<fieldset id="tableReport" style="display:none;"-->
	<legend><strong>SENARAI DOKUMEN</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
	  <tr>
	    <td ><a href="#" class="style2" onClick="javascript:cetakKulitFail('$idFail')"> Kulit Fail </a></td>
	  </tr>
	  <tr>
	    <td ><a href="#" class="style2" onClick="javascript:cetakSuratAkuanTerima('$idFail')"> Akuan Penerimaan Permohonan </a></td>
	  </tr>
	</table>
	<div id="calculateTotalPercentPengarah_result"></div>
</fieldset>
<script type="text/javascript">
#if ($idFail != '')
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabUpper});
#end
</script>
<script>
function doChangeTab(tabId) {
	document.${formName}.mode.value = "view";
	document.${formName}.selectedTabUpper.value = tabId;
	document.${formName}.submit();
}
function doChangeKategoriPemohon() {
	doAjaxCall${formName}("doChangeKategoriPemohon");
}
function doChangeNegeri() {
	doAjaxCall${formName}("doChangeNegeri");
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
function seterusnya(){

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "view";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSeterusnya";
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

<!-- PEMBELI PASIR -->
<script>
function tambahPembeliPasir() {
	document.${formName}.mode.value = "newPembeliPasir";
	doAjaxCall${formName}("");
}
function batalPembeliPasir() {
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
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
	doAjaxCall${formName}("");
}
function kemaskiniPembeliPasir(){
	document.${formName}.mode.value = "updatePembeliPasir";
	doAjaxCall${formName}("");
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
	doAjaxCall${formName}("");
}
function batalKemaskiniPembeliPasir(){
	document.${formName}.mode.value = "viewPembeliPasir";
	doAjaxCall${formName}("");
}
function hapusPembeliPasir(){

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "viewPembeliPasir";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doHapusPembeliPasir";
	doAjaxCall${formName}("");
}
function paparPembeliPasir(idPembeliPasir){
	document.${formName}.idPembeliPasir.value = idPembeliPasir;
	document.${formName}.mode.value = "viewPembeliPasir";
	doAjaxCall${formName}("");
}
</script>
<!-- PEMOHON -->
<script>
function kemaskiniPemohon() {
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}
function batalKemaskiniPemohon() {
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function simpanKemaskiniPemohon() {
	
	if(document.${formName}.socKategoriPemohon.value == ""){
		alert('Sila pilih Jenis Kategori Pemohon.');
  		document.${formName}.socKategoriPemohon.focus(); 
		return; 
	}
	
	
	if (document.${formName}.socKategoriPemohon.value == '1'){
		//INDIVIDU
		if(document.${formName}.txtNama.value == ""){
			alert('Sila masukkan Nama Pemohon.');
			document.${formName}.txtNama.focus(); 
			return; 
		}
		if(document.${formName}.socJenisPengenalanIndividu.value == ""){
			alert('Sila pilih Jenis Pengenalan.');
			document.${formName}.socJenisPengenalanIndividu.focus(); 
			return; 
		}
		if(document.${formName}.txtNoPengenalan.value == ""){
			alert('Sila masukkan No. Pengenalan.');
			document.${formName}.txtNoPengenalan.focus(); 
			return; 
		}
		if(document.${formName}.txtPekerjaan.value == ""){
			alert('Sila masukkan Pekerjaan.');
			document.${formName}.txtPekerjaan.focus(); 
			return; 
		}
		if(document.${formName}.socJantina.value == ""){
			alert('Sila pilih Jantina.');
			document.${formName}.socJantina.focus(); 
			return; 
		}
		if(document.${formName}.socBangsa.value == ""){
			alert('Sila pilih Bangsa.');
			document.${formName}.socBangsa.focus(); 
			return; 
		}
		if(document.${formName}.txtAlamat1.value == ""){
			alert('Sila masukkan Alamat.');
			document.${formName}.txtAlamat1.focus(); 
			return; 
		}
		if(document.${formName}.txtPoskod.value == ""){
			alert('Sila masukkan Poskod.');
			document.${formName}.txtPoskod.focus(); 
			return; 
		}
		if(document.${formName}.socNegeri.value == ""){
			alert('Sila pilih Negeri.');
			document.${formName}.socNegeri.focus(); 
			return; 
		}
		if(document.${formName}.socBandar.value == ""){
			alert('Sila pilih Bandar.');
			document.${formName}.socBandar.focus(); 
			return; 
		}
	
	} else if (document.${formName}.socKategoriPemohon.value == '2'){
		//SYARIKAT
		if(document.${formName}.txtNamaSykt.value == ""){
			alert('Sila masukkan Nama Syarikat.');
			document.${formName}.txtNamaSykt.focus(); 
			return; 
		}
		if(document.${formName}.txtNoPengenalanSykt.value == ""){
			alert('Sila masukkan No. Pendaftaran Syarikat.');
			document.${formName}.txtNoPengenalanSykt.focus(); 
			return; 
		}
		if(document.${formName}.txtPekerjaanSykt.value == ""){
			alert('Sila masukkan Jenis Perniagaan.');
			document.${formName}.txtPekerjaanSykt.focus(); 
			return; 
		}
		if(document.${formName}.txtAlamat1Sykt.value == ""){
			alert('Sila masukkan Alamat.');
			document.${formName}.txtAlamat1Sykt.focus(); 
			return; 
		}
		if(document.${formName}.txtPoskodSykt.value == ""){
			alert('Sila masukkan Poskod.');
			document.${formName}.txtPoskodSykt.focus(); 
			return; 
		}
		if(document.${formName}.socNegeriSykt.value == ""){
			alert('Sila pilih Negeri.');
			document.${formName}.socNegeriSykt.focus(); 
			return; 
		}
		if(document.${formName}.socBandarSykt.value == ""){
			alert('Sila pilih Bandar.');
			document.${formName}.socBandarSykt.focus(); 
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
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}

	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSimpanKemaskiniPemohon";
	doAjaxCall${formName}("");
}
</script>
<!-- PENGARAH -->
<script>
function tambahPengarah() {
	document.${formName}.mode.value = "newPengarah";
	doAjaxCall${formName}("");
}
function batalPengarah() {
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
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
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "newPengarah";
		return;
	}
	
	document.${formName}.mode.value = "newPengarah";
	document.${formName}.hitButton.value = "doSimpanPengarah";
	doAjaxCall${formName}("");
}
function batalPengarah(){
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function kemaskiniPengarah(){
	document.${formName}.mode.value = "updatePengarah";
	doAjaxCall${formName}("");
}
function simpanKemaskiniPengarah(){

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
	doAjaxCall${formName}("");
}
function batalKemaskiniPengarah(){
	document.${formName}.mode.value = "viewPengarah";
	doAjaxCall${formName}("");
}
function hapusPengarah(){

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "viewPengarah";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doHapusPengarah";
	doAjaxCall${formName}("");
}
function paparPengarah(idPengarah){
	document.${formName}.idPengarah.value = idPengarah;
	document.${formName}.mode.value = "viewPengarah";
	doAjaxCall${formName}("");
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
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}
function batalKemaskiniPermohonan() {
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function simpanKemaskiniPermohonan() {
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
	
	if (tarikhTerima > currentDate){
		alert('Tarikh Terima tidak boleh melebihi dari tarikh hari ini.');
  		document.${formName}.tarikhTerima.focus(); 
		return;
	}
	if (tarikhSurat > currentDate){
		alert('Tarikh Surat tidak boleh melebihi dari tarikh hari ini.');
  		document.${formName}.tarikhSurat.focus(); 
		return;
	}
	if (tarikhSurat > tarikhTerima){
		alert('Tarikh Surat tidak boleh melebihi dari Tarikh Terima.');
  		document.${formName}.tarikhSurat.focus(); 
		return;
	}
	
	
	if(document.${formName}.tarikhTerima.value == ""){
		alert('Sila masukkan Tarikh Terima.');
  		document.${formName}.tarikhTerima.focus(); 
		return; 
	}
	if(document.${formName}.tarikhSurat.value == ""){
		alert('Sila masukkan Tarikh Surat.');
  		document.${formName}.tarikhSurat.focus(); 
		return; 
	}
	if(document.${formName}.txtNoRujukanSurat.value == ""){
		alert('Sila masukkan No. Rujukan Surat.');
  		document.${formName}.txtNoRujukanSurat.focus(); 
		return; 
	}
	if(document.${formName}.txtPerkara.value == ""){
		alert('Sila masukkan Perkara.');
  		document.${formName}.txtPerkara.focus(); 
		return; 
	}	
	if(document.${formName}.socKaitanTujuan.value == ""){
		alert('Sila pilih Kaitan Tujuan.');
  		document.${formName}.socKaitanTujuan.focus(); 
		return; 
	}
	if(document.${formName}.txtTujuanPengambilan.value == ""){
		alert('Sila masukkan Tujuan Pengambilan.');
  		document.${formName}.txtTujuanPengambilan.focus(); 
		return; 
	}
	if(document.${formName}.txtTempoh.value == ""){
		alert('Sila masukkan Tempoh.');
  		document.${formName}.txtTempoh.focus(); 
		return; 
	}	
	if(document.${formName}.socTempoh.value == ""){
		alert('Sila pilih Jenis Tempoh.');
  		document.${formName}.socTempoh.focus(); 
		return; 
	}	
	if(document.${formName}.socFlagLuar.value == ""){
		alert('Sila pilih Luar Perairan Negeri.');
  		document.${formName}.socFlagLuar.focus(); 
		return; 
	}
	if(document.${formName}.socNegeri.value == ""){
		alert('Sila pilih Negeri Perairan.');
  		document.${formName}.socNegeri.focus(); 
		return; 
	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}

	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSimpanKemaskiniPermohonan";
	doAjaxCall${formName}("");
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
	doAjaxCall${formName}("");
}
function batalProjek() {
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
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
	doAjaxCall${formName}("");
}
function kemaskiniProjek(){
	document.${formName}.mode.value = "updateProjek";
	doAjaxCall${formName}("");
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
	doAjaxCall${formName}("");
}
function batalKemaskiniProjek(){
	document.${formName}.mode.value = "viewProjek";
	doAjaxCall${formName}("");
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
	doAjaxCall${formName}("");
}
</script>
<!-- PAKAR -->
<script>
function tambahPakar() {
	document.${formName}.mode.value = "newPakar";
	doAjaxCall${formName}("");
}
function batalPakar() {
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
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
	doAjaxCall${formName}("");
}
function kemaskiniPakar(){
	document.${formName}.mode.value = "updatePakar";
	doAjaxCall${formName}("");
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
	doAjaxCall${formName}("");
}
function batalKemaskiniPakar(){
	document.${formName}.mode.value = "viewPakar";
	doAjaxCall${formName}("");
}
function hapusPakar(){

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "viewPakar";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doHapusPakar";
	doAjaxCall${formName}("");
}
function paparPakar(idPakar){
	document.${formName}.idPakar.value = idPakar;
	document.${formName}.mode.value = "viewPakar";
	doAjaxCall${formName}("");
}
</script>
<!-- KOORDINAT -->
<script>
function tambahKoordinat() {
	document.${formName}.mode.value = "newKoordinat";
	doAjaxCall${formName}("");
}
function batalKoordinat() {
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
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
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "newKoordinat";
		return;
	}
	
	document.${formName}.mode.value = "newKoordinat";
	document.${formName}.hitButton.value = "doSimpanKoordinat";
	doAjaxCall${formName}("");
}
function kemaskiniKoordinat(){
	document.${formName}.mode.value = "updateKoordinat";
	doAjaxCall${formName}("");
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
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "updateKoordinat";
		return;
	}
	
	document.${formName}.mode.value = "viewKoordinat";
	//document.${formName}.hitButton.value = "doSimpanKemaskiniKoordinat";
	document.${formName}.hitButton.value = "doSimpanKoordinat";
	doAjaxCall${formName}("");
}
function batalKemaskiniKoordinat(){
	document.${formName}.mode.value = "viewKoordinat";
	doAjaxCall${formName}("");
}
function hapusKoordinat(){

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "viewKoordinat";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doHapusKoordinat";
	doAjaxCall${formName}("");
}
function paparKoordinat(idKoordinat){
	document.${formName}.idKoordinat.value = idKoordinat;
	document.${formName}.mode.value = "viewKoordinat";
	doAjaxCall${formName}("");
}
function textCounter(field, countfield, maxlimit) {
   if (field.value.length > maxlimit) // if too long...trim it!
		 field.value = field.value.substring(0, maxlimit);
    // otherwise, update 'Baki Aksara' counter
	else
	 countfield.value = maxlimit - field.value.length;
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
function cetakKulitFail(idFail) {
	var url = "../servlet/ekptg.report.php2.APBKulitFail?ID_FAIL="+idFail;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratAkuanTerima(idFail) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmAPBPopupCetakLaporanView?idFail="+idFail+"&report=suratAkuanTerima";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>
$javascriptLampiran