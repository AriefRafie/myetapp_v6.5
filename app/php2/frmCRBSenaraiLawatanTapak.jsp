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
  <input name="idUlasanTeknikal" type="hidden" id="idUlasanTeknikal" value="$idUlasanTeknikal"/>
  <input name="idLaporanTanah" type="hidden" id="idLaporanTanah" value="$idLaporanTanah"/>
  <input name="idPenceroboh" type="hidden" id="idPenceroboh" value="$idPenceroboh"/>
  <input name="idPegawaiLaporanTanah" type="hidden" id="idPegawaiLaporanTanah" value="$idPegawaiLaporanTanah"/>
  <input name="idDokumen" type="hidden" id="idDokumen" value="$idDokumen"/>
  
  <input name="actionCRB" type="hidden" id="actionCRB" value="$actionCRB"/>
  <input name="flagPopup" type="hidden" id="flagPopup" value="$flagPopup"/>
  <input name="modePopup" type="hidden" id="modePopup" value="$modePopup"/>
  <input name="flagStatus" type="hidden" id="flagStatus" value="$flagStatus"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
  
  <input name="selectedTabUpper" type="hidden" id="selectedTabUpper" value="$selectedTabUpper"/>
  <input name="selectedTabLower" type="hidden" id="selectedTabLower" value="$selectedTabLower"/>
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
  #if ($idFail != '' && $flagOpenDetail)
  <tr>
    <td><div id="TabbedPanels1" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <li onClick="doChangeTabUpper(0);" class="TabbedPanelsTab" tabindex="0">SURAT MINTA LAPORAN TANAH</li>
          <li onClick="doChangeTabUpper(1);" class="TabbedPanelsTab" tabindex="0">LAWATAN TAPAK</li>
        </ul>
        <div class="TabbedPanelsContentGroup">
          <div class="TabbedPanelsContent"> #parse("app/php2/frmCRBSuratMintaLawatanTapak.jsp") </div>
          <div class="TabbedPanelsContent">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              #if ($actionCRB == "newLawatanTapak")
              <tr>
                <td>#parse("app/php2/frmCRBLawatanTapakDetail.jsp")</td>
              </tr>
              #end
              #if ($actionCRB == "viewLawatanTapak")
              <tr>
                <td>#parse("app/php2/frmCRBLawatanTapak.jsp")</td>
              </tr>
              #end
              #if ($actionCRB == "")
              <tr>
                <td><fieldset>
                  <legend><strong>SENARAI LAWATAN TAPAK</strong></legend>
                  <table align="center" width="100%">
                    <tr>
                      <td colspan="4" scope="row"><input name="cmdDaftar" type="button" value="Tambah" onClick="javascript:doDaftarBaruLT()"/></td>
                    </tr>
                    <tr class="table_header">
                      <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
                      <td width="15%" align="center"><strong>Tarikh Lawatan</strong></td>
                      <td width="70%" align="center"><strong>Tujuan Lawatan</strong></td>
                      <td width="10%" align="center"><strong>Hapus</strong></td>
                    </tr>
                    #set ($senaraiLawatanTapak = "")
                    #if ($SenaraiLawatanTapak.size() > 0)
                    #foreach ($senaraiLawatanTapak in $SenaraiLawatanTapak)
                    #if ($senaraiLawatanTapak.bil == '')
                    #set( $row = "row1" )
                    #elseif (($senaraiLawatanTapak.bil % 2) != 0)
                    #set( $row = "row1" )
                    #else 
                    #set( $row = "row2" )
                    #end
                    <tr>
                      <td class="$row" align="center">$senaraiLawatanTapak.bil</td>
                      <td class="$row" align="center"><a href="javascript:paparLawatanTapak('$senaraiLawatanTapak.idLaporanTanah')" class="style2">$senaraiLawatanTapak.tarikhLawatan</a></td>
                      <td class="$row">$senaraiLawatanTapak.tujuanLawatan</td>
                      <td class="$row" align="center"><input name="cmdHapusLT" id="cmdHapusLT" type="button" value="Hapus" onClick="hapusLT('$senaraiLawatanTapak.idLaporanTanah')" ></td>
                    </tr>
                    #end
                    #else
                    <tr>
                      <td class="row1" align="center">&nbsp;</td>
                      <td class="row1">Tiada Rekod</td>
                      <td class="row1">&nbsp;</td>
                      <td class="row1">&nbsp;</td>
                    </tr>
                    #end
                    <tr>
                      <td colspan="4">&nbsp;</td>
                    </tr>
                  </table>
                  </fieldset></td>
              </tr>
              #end
            </table>
          </div>
        </div>
      </div></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  #if ($idStatus == '1610200' && $actionCRB == '' && $flagPopup == '')
  <tr>
    <td align="center"><input type="button" name="cmdHantar" id="cmdHantar" value="Seterusnya" onClick="doSeterusnya()"/>
      <input type="button" name="cmdSelesaiPermohonan" id="cmdSelesaiPermohonan" value="Selesai Permohonan" onClick="gotoSelesaiPermohonan()"/>
      <input type="button" name="cmdBatalPermohonan" id="cmdBatalPermohonan" value="Batal Permohonan" onClick="gotoBatalPermohonan()"/>
    </td>
  </tr>
  #end
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
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmCRBLawatanTapakView";
	document.${formName}.method="POST";
	document.${formName}.selectedTabUpper.value = tabId;
	document.${formName}.actionCRB.value = "";
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.submit();
}
function doChangeTabLower(tabId) {
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmCRBLawatanTapakView";
	document.${formName}.method="POST";
	document.${formName}.selectedTabLower.value = tabId;
	document.${formName}.actionCRB.value = "viewLawatanTapak";
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "view";
	document.${formName}.submit();
}
</script>
<script>
function doDaftarBaruLT(){
	document.${formName}.actionCRB.value = "newLawatanTapak";
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "new";
	doAjaxCall${formName}("");
}
function batalLawatanTapak() {
	document.${formName}.actionCRB.value = "";
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	doAjaxCall${formName}("");
}
function simpanSiasatanLT() {
	
	if(document.${formName}.txtTarikhTerimaFail.value == ""){
		alert('Sila masukkan Tarikh Terima Fail.');
  		document.${formName}.txtTarikhTerimaFail.focus(); 
		return; 
	}
	if(document.${formName}.txtTarikhLawatan.value == ""){
		alert('Sila masukkan Tarikh Lawatan.');
  		document.${formName}.txtTarikhLawatan.focus(); 
		return; 
	}
	if(document.${formName}.txtTarikhLaporan.value == ""){
		alert('Sila masukkan Tarikh Laporan.');
  		document.${formName}.txtTarikhLaporan.focus(); 
		return; 
	}
	if(document.${formName}.txtTujuanLaporan.value == ""){
		alert('Sila masukkan Tujuan Laporan.');
  		document.${formName}.txtTujuanLaporan.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}
	document.${formName}.actionCRB.value = "viewLawatanTapak";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "doSimpanSiasatanLT";
	doAjaxCall${formName}("");
}
function paparLawatanTapak(idLaporanTanah){
	document.${formName}.actionCRB.value = "viewLawatanTapak";
	document.${formName}.modePopup.value = "view";
	document.${formName}.selectedTabLower.value = "";
	document.${formName}.idLaporanTanah.value = idLaporanTanah;
	doAjaxCall${formName}("");
}
function kemaskiniMaklumatSiasatanLT() {
	document.${formName}.modePopup.value = "update";
	doAjaxCall${formName}("");
}
function simpanKemaskiniMaklumatSiasatanLT() {
	if(document.${formName}.txtTarikhTerimaFail.value == ""){
		alert('Sila masukkan Tarikh Terima Fail.');
  		document.${formName}.txtTarikhTerimaFail.focus(); 
		return; 
	}
	if(document.${formName}.txtTarikhLawatan.value == ""){
		alert('Sila masukkan Tarikh Lawatan.');
  		document.${formName}.txtTarikhLawatan.focus(); 
		return; 
	}
	if(document.${formName}.txtTarikhLaporan.value == ""){
		alert('Sila masukkan Tarikh Laporan.');
  		document.${formName}.txtTarikhLaporan.focus(); 
		return; 
	}
	if(document.${formName}.txtTujuanLaporan.value == ""){
		alert('Sila masukkan Tujuan Laporan.');
  		document.${formName}.txtTujuanLaporan.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}
	document.${formName}.actionCRB.value = "viewLawatanTapak";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniMaklumatSiasatanLT";
	doAjaxCall${formName}("");
}
function hapusLT(idLaporanTanah){
		
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.actionCRB.value = "";
	document.${formName}.idLaporanTanah.value = idLaporanTanah;
	document.${formName}.hitButton.value = "doHapusMaklumatLT";
	doAjaxCall${formName}("");
}

function kemaskiniMaklumatLain() {
	document.${formName}.modePopup.value = "update";
	doAjaxCall${formName}("");
}
function simpanKemaskiniMaklumatLain() {
	if(document.${formName}.txtJalanHubungan.value == ""){
		alert('Sila masukkan Jalan Hubungan');
  		document.${formName}.txtJalanHubungan.focus(); 
		return; 
	}
	if(document.${formName}.txtKawasanBerhampiran.value == ""){
		alert('Sila masukkan Kawasan Berhampiran.');
  		document.${formName}.txtKawasanBerhampiran.focus(); 
		return; 
	}
	if(document.${formName}.txtJarakDariBandar.value == ""){
		alert('Sila masukkan Anggaran Jarak Dari Bandar.');
  		document.${formName}.txtJarakDariBandar.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.modePopup.value = "update";
		return;
	}
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "doSimpanKemaskiniMaklumatLain";
	doAjaxCall${formName}("");
}
function batalKemaskiniMaklumatLain() {
	document.${formName}.modePopup.value = "view";
	doAjaxCall${formName}("");
}

function doChangeNegeriPenceroboh() {
	doAjaxCall${formName}("doChangeNegeriPenceroboh");
}
function doDaftarBaruPenceroboh() {
	document.${formName}.flagPopup.value = "openPopupPenceroboh";
	document.${formName}.modePopup.value = "new";
	doAjaxCall${formName}("");
}
function doPaparPenceroboh(idPenceroboh){
	document.${formName}.idPenceroboh.value = idPenceroboh;
	document.${formName}.flagPopup.value = "openPopupPenceroboh";
	document.${formName}.modePopup.value = "view";
	doAjaxCall${formName}("");
}
function simpanPenceroboh() {
	if(document.${formName}.txtNamaPenceroboh.value == ""){
		alert('Sila masukkan Nama.');
  		document.${formName}.txtNamaPenceroboh.focus(); 
		return; 
	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.flagPopup.value = "openPopupPenceroboh";
		document.${formName}.modePopup.value = "new";
		document.${formName}.mode.value = "view";
		return;
	}
	
	document.${formName}.flagPopup.value = "openPopupPenceroboh";
	document.${formName}.modePopup.value = "new";
	document.${formName}.hitButton.value = "doSimpanPenceroboh";
	doAjaxCall${formName}("");
}
function batalPenceroboh(){
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	doAjaxCall${formName}("");
}
function kemaskiniPenceroboh(){
	document.${formName}.flagPopup.value = "openPopupPenceroboh";
	document.${formName}.modePopup.value = "update";
	doAjaxCall${formName}("");
}
function batalKemaskiniPenceroboh(){
	document.${formName}.flagPopup.value = "openPopupPenceroboh";
	document.${formName}.modePopup.value = "view";
	doAjaxCall${formName}("");
}
function simpanKemaskiniPenceroboh() {
	if(document.${formName}.txtNamaPenceroboh.value == ""){
		alert('Sila masukkan Nama.');
  		document.${formName}.txtNamaPenceroboh.focus(); 
		return; 
	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.flagPopup.value = "openPopupPenceroboh";
		document.${formName}.modePopup.value = "new";
		return;
	}

	document.${formName}.flagPopup.value = "openPopupPenceroboh";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "doSimpanKemaskiniPenceroboh";
	doAjaxCall${formName}("");
}
function hapusMaklumatPenceroboh(){	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.hitButton.value = "doHapusMaklumatPenceroboh";
	doAjaxCall${formName}("");
}

function doChangeKementerian() {
	doAjaxCall${formName}("doChangeKementerian");
}
function simpanKehadiran() {
	if(document.${formName}.txtNama.value == ""){
		alert('Sila masukkan Nama.');
  		document.${formName}.txtNama.focus(); 
		return; 
	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.flagPopup.value = "openPopupKehadiran";
		document.${formName}.modePopup.value = "new";
		document.${formName}.mode.value = "view";
		return;
	}
	document.${formName}.flagPopup.value = "openPopupKehadiran";
	document.${formName}.modePopup.value = "new";	
	document.${formName}.hitButton.value = "doSimpanKehadiran";
	doAjaxCall${formName}("");
}
function kemaskiniKehadiran(){
	document.${formName}.flagPopup.value = "openPopupKehadiran";
	document.${formName}.modePopup.value = "update";
	doAjaxCall${formName}("");
}
function simpanKemaskiniKehadiran() {
	if(document.${formName}.txtNama.value == ""){
		alert('Sila masukkan Nama.');
  		document.${formName}.txtNama.focus(); 
		return; 
	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.flagPopup.value = "openPopupKehadiran";
		document.${formName}.modePopup.value = "new";
		return;
	}

	document.${formName}.flagPopup.value = "openPopupKehadiran";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "doSimpanKemaskiniKehadiran";
	doAjaxCall${formName}("");
}
function batalKehadiran(){
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	doAjaxCall${formName}("");
}
function batalKemaskiniKehadiran(){
	document.${formName}.flagPopup.value = "openPopupKehadiran";
	document.${formName}.modePopup.value = "view";
	doAjaxCall${formName}("");
}
function hapusMaklumatKehadiran(){	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.hitButton.value = "doHapusMaklumatKehadiran";
	doAjaxCall${formName}("");
}
function doDaftarBaruKehadiran() {
	document.${formName}.flagPopup.value = "openPopupKehadiran";
	document.${formName}.modePopup.value = "new";
	doAjaxCall${formName}("");
}
function doPaparKehadiran(idPegawaiLaporanTanah){
	document.${formName}.idPegawaiLaporanTanah.value = idPegawaiLaporanTanah;
	document.${formName}.flagPopup.value = "openPopupKehadiran";
	document.${formName}.modePopup.value = "view";
	doAjaxCall${formName}("");
}
function kemaskiniPegawaiPelapor(){
	document.${formName}.modePopup.value = "update";
	doAjaxCall${formName}("");
}
function simpanKemaskiniPegawaiPelapor() {
	if(document.${formName}.txtNamaPelapor.value == ""){
		alert('Sila masukkan Nama.');
  		document.${formName}.txtNamaPelapor.focus(); 
		return; 
	}
	if(document.${formName}.txtJawatanPelapor.value == ""){
		alert('Sila masukkan Jawatan.');
  		document.${formName}.txtJawatanPelapor.focus(); 
		return; 
	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.modePopup.value = "update";
		return;
	}

	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "doSimpanKemaskiniMaklumatPelapor";
	doAjaxCall${formName}("");
}
function batalPegawaiPelapor() {
	document.${formName}.modePopup.value = "view";
	doAjaxCall${formName}("");
}
</script>
<script>
function daftarDokumen() {	
	document.${formName}.flagPopup.value = "openPopupDokumen";
	document.${formName}.modePopup.value = "new";
	doAjaxCall${formName}("");
}
function simpanDokumen(idLaporanTanah,idPermohonan) {
	
	if(document.${formName}.txtNamaImej.value == ""){
		alert('Sila masukkan Nama Imej.');
  		document.${formName}.txtNamaImej.focus(); 
		return; 
	}
	if(document.${formName}.fileupload.value == ""){
		alert('Sila pilih Imej yang Ingin Dimuatnaik.');
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
	
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmCRBLawatanTapakView&hitButton=simpanDokumen&namaImej="+namaImej+"&catatanImej="+catatanImej+"&idPermohonan="+idPermohonan+"&idLaporanTanah="+idLaporanTanah+"&selectedTabUpper=1&selectedTabLower=5&actionCRB=viewLawatanTapak"+dopost;
	document.${formName}.method="post";
	document.${formName}.enctype="multipart/form-data";
    document.${formName}.encoding="multipart/form-data";
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
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmCRBLawatanTapakView";
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

function simpanLampiran(idLaporanTanah,idPermohonan) {
	
	if(document.${formName}.txtNamaLampiran.value == ""){
		alert('Sila masukkan Nama Lampiran.');
  		document.${formName}.txtNamaLampiran.focus(); 
		return; 
	}
	if(document.${formName}.fileuploadLampiran.value == ""){
		alert('Sila pilih Lampiran yang Ingin Dimuatnaik.');
  		document.${formName}.fileuploadLampiran.focus(); 
		return; 
	}

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	var namaLampiran = document.${formName}.txtNamaLampiran.value;

 	var catatanLampiran = document.${formName}.txtCatatanLampiran.value ;
	var dp = document.${formName}.form_token.value ;
	var dopost = "&form_token="+dp;
	
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmCRBLawatanTapakView&hitButton=simpanLampiran&namaLampiran="+namaLampiran+"&catatanLampiran="+catatanLampiran+"&idLaporanTanah="+idLaporanTanah+"&idPermohonan="+idPermohonan+"&selectedTabUpper=1&selectedTabLower=6&actionCRB=viewLawatanTapak"+dopost;
	document.${formName}.method="post";
	document.${formName}.enctype="multipart/form-data";
    document.${formName}.encoding="multipart/form-data";
	document.${formName}.submit();
}
function simpanKemaskiniLampiran() {

	if(document.${formName}.txtNamaLampiran.value == ""){
		alert('Sila masukkan Nama Lampiran.');
  		document.${formName}.txtNamaLampiran.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}

	document.${formName}.flagPopup.value = "openPopupDokumen";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniLampiran";
	doAjaxCall${formName}("");
}

function doSeterusnya(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.hitButton.value = "doSeterusnya";
	document.${formName}.submit();
}
</script>
<script>
function validateJarak(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
}
function textCounter(field, countfield, maxlimit) {
   if (field.value.length > maxlimit) // if too long...trim it!
		 field.value = field.value.substring(0, maxlimit);
    // otherwise, update 'Baki Aksara' counter
	else
	 countfield.value = maxlimit - field.value.length;
}
function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
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
function cetakCRBLaporanTanah(idFail,idLaporanTanah) {
	var url = "../servlet/ekptg.report.php2.CBRLaporanTanah?ID_FAIL="+idFail+"&ID_LAPORANTANAH="+idLaporanTanah;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakCRBLampiranA(idLaporanTanah) {
	var url = "../servlet/ekptg.report.php2.CRBLampiranA?ID_LAPORANTANAH="+idLaporanTanah;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
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
