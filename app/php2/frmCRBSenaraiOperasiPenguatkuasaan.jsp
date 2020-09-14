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
	<input name="idFail" type="hidden" id="idFail" value="$idFail" /> 
	<input name="idStatus" type="hidden" id="idStatus" value="$idStatus" /> 
	<input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan" /> 
	<input name="idUlasanTeknikal" type="hidden" id="idUlasanTeknikal" value="$idUlasanTeknikal" /> 
	<input name="idLaporanTanah" type="hidden" id="idLaporanTanah" value="$idLaporanTanah" /> 
	<input name="idPenceroboh" type="hidden" id="idPenceroboh" value="$idPenceroboh" /> 
	<input name="idPegawaiLaporanTanah" type="hidden" id="idPegawaiLaporanTanah" value="$idPegawaiLaporanTanah" /> 
	<input name="idKementerianTanah" type="hidden" id="idKementerianTanah" value="$idKementerianTanah" /> 
	<input name="idAgensiTanah" type="hidden" id="idAgensiTanah" value="$idAgensiTanah" /> 
	<input name="idDokumen" type="hidden" id="idDokumen" value="$idDokumen" /> 
	<input name="actionCRB" type="hidden" id="actionCRB" value="$actionCRB" /> 
	<input name="flagPopup" type="hidden" id="flagPopup" value="$flagPopup" /> 
	<input name="modePopup" type="hidden" id="modePopup" value="$modePopup" /> 
	<input name="flagStatus" type="hidden" id="flagStatus" value="$flagStatus" />
	<input name="hitButton" type="hidden" id="hitButton" /> 
	<input name="selectedTabUpper" type="hidden" id="selectedTabUpper" value="$selectedTabUpper" /> 
	<input name="selectedTabLower" type="hidden" id="selectedTabLower" value="$selectedTabLower" />
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
	#if ($idFail != '' && $idStatus != '1610198' && $idStatus != '1610199' && $idStatus != '1610200' && $idStatus != '1610201' && $idStatus != '1610216')
	<tr>
		<td>#parse("app/php2/frmCRBHeader.jsp")</td>
	</tr>
	#elseif ($idFail == '' )
	<tr>
		<td>&nbsp;
			<div class="warning">SILA PILIH FAIL DI SENARAI FAIL TERLEBIH
				DAHULU</div>
		</td>
	</tr>
	#end 
	
	#if ($idFail != '' && $flagOpenDetail && $idStatus != '1610198' && $idStatus != '1610199' && $idStatus != '1610200' && $idStatus != '1610201' && $idStatus != '1610216')
	<tr>
		<td><div id="TabbedPanels1" class="TabbedPanels">
				<ul class="TabbedPanelsTabGroup">
					<li onClick="doChangeTabUpper(0);" class="TabbedPanelsTab"
						tabindex="0">LAPORAN OPERASI</li>
					<li onClick="doChangeTabUpper(1);" class="TabbedPanelsTab"
						tabindex="0">SURAT PENGHARGAAN</li>
				</ul>
				<div class="TabbedPanelsContentGroup">
					<div class="TabbedPanelsContent">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							#if ($actionCRB == "newLaporanOperasi")
							<tr>
								<td>#parse("app/php2/frmCRBLaporanOperasi.jsp")</td>
							</tr>
							#end #if ($actionCRB == "viewLaporanOperasi")
							<tr>
								<td>#parse("app/php2/frmCRBOperasiPenguatkuasaan.jsp")</td>
							</tr>
							#end #if ($actionCRB == "")
							<tr>
								<td><fieldset>
										<legend>
											<strong>SENARAI LAPORAN OPERASI</strong>
										</legend>
										<table align="center" width="100%">
											<tr>
												#if ($!{session.getAttribute("FLAG_FROM")} == 'failTugasan')
												<td colspan="4" scope="row">
													<input name="cmdDaftar" type="button" value="Tambah" onClick="javascript:doDaftarBaruLO()" />
												</td>
												#end
											</tr>
											<tr class="table_header">
												<td scope="row" width="5%" align="center"><strong>Bil</strong></td>
												<td width="15%" align="center"><strong>Tarikh
														Mula Operasi</strong></td>
												<td width="15%" align="center"><strong>Tarikh
														Tamat Operasi</strong></td>
												<td width="55%" align="center"><strong>Pegawai
														Pelapor</strong></td>
												<td width="10%" align="center"><strong>Hapus</strong></td>
											</tr>
											#set ($senaraiLaporanOperasi = "") #if
											($SenaraiLaporanOperasi.size() > 0) #foreach
											($senaraiLaporanOperasi in $SenaraiLaporanOperasi) #if
											($senaraiLaporanOperasi.bil == '') #set( $row = "row1" )
											#elseif (($senaraiLaporanOperasi.bil % 2) != 0) #set( $row =
											"row1" ) #else #set( $row = "row2" ) #end
											<tr>
												<td class="$row" align="center">$senaraiLaporanOperasi.bil</td>
												<td class="$row" align="center"><a
													href="javascript:paparLO('$senaraiLaporanOperasi.idLaporanTanah')"
													class="style2">$senaraiLaporanOperasi.tarikhMulaOperasi</a></td>
												<td class="$row" align="center">$senaraiLaporanOperasi.tarikhTamatOperasi</td>
												<td class="$row">$senaraiLaporanOperasi.pegawaiPelapor</td>
												<td class="$row" align="center"><input
													name="cmdHapusLO" id="cmdHapusLO" type="button"
													value="Hapus"
													onClick="hapusLO('$senaraiLaporanOperasi.idLaporanTanah')"></td>
											</tr>
											#end #else
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
					<div class="TabbedPanelsContent">
						<br> #parse("app/php2/frmCRBSuratPenghargaan.jsp")
					</div>
				</div>
			</div></td>
	</tr>
	#if ($!{session.getAttribute("FLAG_FROM")} == 'failTugasan')
	#if ($idStatus == '1610217' && $actionCRB == '' && $flagPopup == '')
	<tr>
		<td align="center">
			<input type="button" name="cmdSelesaiPermohonan" id="cmdSelesaiPermohonan" value="Selesai Permohonan" onClick="gotoSelesaiPermohonan()" /> 
			<input type="button" name="cmdBatalPermohonan" id="cmdBatalPermohonan" value="Batal Permohonan" onClick="gotoBatalPermohonan()" />
		</td>
	</tr>
	#end
	#end
    #if ($!{session.getAttribute("FLAG_FROM")} == 'failKeseluruhan')
    <tr>
    	<td align="center">
    		<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="gotoSenaraiFailKeseluruhan()"/>
    	</td>
    </tr>
    #end
	 
	#elseif ($idFail != '')
	<tr>
		<td> 
			<div class="warning">
				<strong>STATUS FAIL MASIH DI PERINGKAT $!status</strong>
			</div>
		</td>
	</tr>
	#end
</table>
<script type="text/javascript">
#if ($idFail != '')
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabUpper});
	var TabbedPanels2 = new Spry.Widget.TabbedPanels("TabbedPanels2",{defaultTab:$selectedTabLower});
#end
</script>
<script>
function doChangeTabUpper(tabId) {
	document.${formName}.selectedTabUpper.value = tabId;
	document.${formName}.actionCRB.value = "";
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmCRBOperasiPenguatkuasaanView";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function doChangeTabLower(tabId) {
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmCRBOperasiPenguatkuasaanView";
	document.${formName}.method="POST";
	document.${formName}.selectedTabLower.value = tabId;
	document.${formName}.actionCRB.value = "viewLaporanOperasi";
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "view";
	document.${formName}.submit();
}
</script>
<script>
function doChangeSuratKe() {
	doAjaxCall${formName}("doChangeSuratKe");
}
function doChangeNegeri() {
	doAjaxCall${formName}("doChangeNegeri");
}
function doChangePejabat() {
	doAjaxCall${formName}("doChangePejabat");
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
function paparMaklumatSuratPenghargaan(idUlasanTeknikal){
	document.${formName}.flagPopup.value = "openSuratPenghargaan";
	document.${formName}.modePopup.value = "view";
	document.${formName}.idUlasanTeknikal.value = idUlasanTeknikal;
	doAjaxCall${formName}("");
}
function doDaftarMaklumatSuratPenghargaan(){
	document.${formName}.flagPopup.value = "openSuratPenghargaan";
	document.${formName}.modePopup.value = "new";
	document.${formName}.flagStatus.value = "1";
    doAjaxCall${formName}("");
}
function doSimpanMaklumatSuratPenghargaan(){
	
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
	
	document.${formName}.flagPopup.value = "openSuratPenghargaan";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanMaklumatSuratPenghargaan";
	doAjaxCall${formName}("");
}
function doSimpanMaklumatUlanganSuratPenghargaan(){	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "openSuratPenghargaan";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanMaklumatUlanganSuratPenghargaan";
	doAjaxCall${formName}("");
}
function doBatalMaklumatSuratPenghargaan(){
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	doAjaxCall${formName}("");
}
function doKemaskiniMaklumatSuratPenghargaan(){
	document.${formName}.flagPopup.value = "openSuratPenghargaan";
	document.${formName}.modePopup.value = "update";
	doAjaxCall${formName}("");
}
function doBatalKemaskiniMaklumatSuratPenghargaan(){
	document.${formName}.flagPopup.value = "openSuratPenghargaan";
	document.${formName}.modePopup.value = "view";
	doAjaxCall${formName}("");
}
function doSimpanKemaskiniMaklumatSuratPenghargaan(){

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
	
	document.${formName}.flagPopup.value = "openSuratPenghargaan";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniMaklumatSuratPenghargaan";
	doAjaxCall${formName}("");
}
function doHapusMaklumatSuratPenghargaan(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.hitButton.value = "hapusMaklumatKJPKJT";
	doAjaxCall${formName}("");
}
function doTerimaSuratPenghargaan(){
	document.${formName}.flagPopup.value = "openSuratPenghargaan";
	document.${formName}.modePopup.value = "update";
	document.${formName}.flagStatus.value = "2";
	doAjaxCall${formName}("");
}
function doUlanganSuratPenghargaan(){
	document.${formName}.flagPopup.value = "openSuratPenghargaan";
	document.${formName}.modePopup.value = "newUlangan";
	document.${formName}.flagStatus.value = "1";
	doAjaxCall${formName}("");
}
</script>

<script>
function doDaftarBaruLO(){
	document.${formName}.actionCRB.value = "newLaporanOperasi";
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "new";
	doAjaxCall${formName}("");
}
function batalLO() {
	document.${formName}.actionCRB.value = "";
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	doAjaxCall${formName}("");
}
function simpanLO() {
	if(document.${formName}.txtTarikhOperasi.value == ""){
		alert('Sila masukkan Tarikh Operasi.');
  		document.${formName}.txtTarikhOperasi.focus(); 
		return; 
	}
	if(document.${formName}.txtMasaDari.value == ""){
		alert('Sila masukkan Masa Mula Operasi.');
  		document.${formName}.txtMasaDari.focus(); 
		return; 
	}
	if(document.${formName}.txtMasaHingga.value == ""){
		alert('Sila masukkan Masa Tamat Operasi.');
  		document.${formName}.txtMasaHingga.focus(); 
		return; 
	}
	if(document.${formName}.txtTempat.value == ""){
		alert('Sila masukkan Tempat Operasi.');
  		document.${formName}.txtTempat.focus(); 
		return; 
	}
	if(document.${formName}.txtPenceroboh.value == ""){
		alert('Sila masukkan maklumat Penceroboh.');
  		document.${formName}.txtPenceroboh.focus(); 
		return; 
	}
	if(document.${formName}.txtLaporan.value == ""){
		alert('Sila masukkan Pendahuluan.');
  		document.${formName}.txtLaporan.focus(); 
		return; 
	}
	if(document.${formName}.txtLaporanSemasa.value == ""){
		alert('Sila masukkan maklumat Semasa Operasi.');
  		document.${formName}.txtLaporanSemasa.focus(); 
		return; 
	}
	if(document.${formName}.txtLaporanSelepas.value == ""){
		alert('Sila masukkan maklumat Selepas Operasi.');
  		document.${formName}.txtLaporanSelepas.focus(); 
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
	document.${formName}.actionCRB.value = "viewLaporanOperasi";
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanLO";
	doAjaxCall${formName}("");
}
function simpanKemaskiniLO() {
	if(document.${formName}.txtTarikhOperasi.value == ""){
		alert('Sila masukkan Tarikh Operasi.');
  		document.${formName}.txtTarikhOperasi.focus(); 
		return; 
	}
	if(document.${formName}.txtMasaDari.value == ""){
		alert('Sila masukkan Masa Mula Operasi.');
  		document.${formName}.txtMasaDari.focus(); 
		return; 
	}
	if(document.${formName}.txtMasaHingga.value == ""){
		alert('Sila masukkan Masa Tamat Operasi.');
  		document.${formName}.txtMasaHingga.focus(); 
		return; 
	}
	if(document.${formName}.txtTempat.value == ""){
		alert('Sila masukkan Tempat Operasi.');
  		document.${formName}.txtTempat.focus(); 
		return; 
	}
	if(document.${formName}.txtPenceroboh.value == ""){
		alert('Sila masukkan maklumat Penceroboh.');
  		document.${formName}.txtPenceroboh.focus(); 
		return; 
	}
	if(document.${formName}.txtLaporan.value == ""){
		alert('Sila masukkan Pendahuluan.');
  		document.${formName}.txtLaporan.focus(); 
		return; 
	}
	if(document.${formName}.txtLaporanSemasa.value == ""){
		alert('Sila masukkan maklumat Semasa Operasi.');
  		document.${formName}.txtLaporanSemasa.focus(); 
		return; 
	}
	if(document.${formName}.txtLaporanSelepas.value == ""){
		alert('Sila masukkan maklumat Selepas Operasi.');
  		document.${formName}.txtLaporanSelepas.focus(); 
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
	document.${formName}.actionCRB.value = "viewLaporanOperasi";
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniLO";
	doAjaxCall${formName}("");
}
function paparLO(idLaporanTanah){
	document.${formName}.actionCRB.value = "viewLaporanOperasi";
	document.${formName}.modePopup.value = "view";
	document.${formName}.selectedTabLower.value = "0";
	document.${formName}.idLaporanTanah.value = idLaporanTanah;
	doAjaxCall${formName}("");
}
function kemaskiniLO() {
	document.${formName}.modePopup.value = "update";
	doAjaxCall${formName}("");
}
function batalKemaskiniLO() {
	document.${formName}.modePopup.value = "view";
	doAjaxCall${formName}("");
}
function hapusLO(idLaporanTanah){
		
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.actionCRB.value = "";
	document.${formName}.idLaporanTanah.value = idLaporanTanah;
	document.${formName}.hitButton.value = "hapusLO";
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
</script>

<script>
function daftarDokumen() {
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmCRBOperasiPenguatkuasaanView";
	document.${formName}.method="POST";	
	document.${formName}.flagPopup.value = "openPopupDokumen";
	document.${formName}.modePopup.value = "new";
	document.${formName}.submit();
}
function simpanDokumen(idLaporanTanah,idPermohonan) {
	
	if(document.${formName}.socJenisImej.value == ""){
		alert('Sila pilih Jenis Imej.');
  		document.${formName}.socJenisImej.focus(); 
		return; 
	}
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
	
	var jenisImej = document.${formName}.socJenisImej.value;
	var namaImej = document.${formName}.txtNamaImej.value;
 	var catatanImej = document.${formName}.txtCatatanImej.value ;
	var dp = document.${formName}.form_token.value ;
	var dopost = "&form_token="+dp;
	
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmCRBOperasiPenguatkuasaanView&hitButton=simpanDokumen&namaImej="+namaImej+"&catatanImej="+catatanImej+"&jenisImej="+jenisImej+"&idLaporanTanah="+idLaporanTanah+"&idPermohonan="+idPermohonan+"&selectedTabUpper=0&selectedTabLower=2&actionCRB=viewLaporanOperasi"+dopost;
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
function batalKemaskiniDokumen(){
	document.${formName}.flagPopup.value = "openPopupDokumen";
	document.${formName}.modePopup.value = "view";
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
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmCRBOperasiPenguatkuasaanView";
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
function cetakCRBSuratPenghargaan(idFail,idUlasanTeknikal) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmCRBPopupCetakLaporanView?idUlasanTeknikal="+idUlasanTeknikal+"&idFail="+idFail+"&report=suratPenghargaan";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakLaporanOperasi(idFail,idLaporanTanah,idPermohonan) {
	var url = "../servlet/ekptg.report.php2.CBRLaporanOperasi?ID_FAIL="+idFail+"&ID_LAPORANTANAH="+idLaporanTanah+"&ID_PERMOHONAN="+idPermohonan;
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
function cetakSuratPagarTanah(idFail) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmCRBPopupCetakLaporanView?idFail="+idFail+"&report=suratKJPPagarTanah";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>

<input name="step" type="hidden" id="step" />
<script>
function gotoSelesaiPermohonan(){	
	document.${formName}.step.value = "selesaiPermohonan";
	document.${formName}.submit();
}
function gotoBatalPermohonan(){	
	document.${formName}.step.value = "batalPermohonan";
	document.${formName}.submit();
}
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
</script>
