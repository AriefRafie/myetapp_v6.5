<script>
function doChangeNegeri(){
	doDivAjaxCall$formname('divMainForm','carian','');
}
function doChangeDaerah(){
	doDivAjaxCall$formname('divMainForm','carian','');
}
function paparFail(idFail, idUlasanTeknikal){
	doDivAjaxCall$formname('divMainForm','paparFail','idFail='+ idFail +'&idUlasanTeknikal=' + idUlasanTeknikal);
}
function simpanUlasan(){	

	if(document.${formName}.txtTarikhSurat.value == ""){
		alert('Sila masukkan Tarikh Surat');
  		document.${formName}.txtTarikhSurat.focus(); 
		return; 
	}
	if(document.${formName}.txtNoRujukanSurat.value == ""){
		alert('Sila masukkan No Rujukan Surat');
  		document.${formName}.txtNoRujukanSurat.focus(); 
		return; 
	}
	if(document.${formName}.txtKeputusan.value == ""){
		alert('Sila masukkan Keputusan');
  		document.${formName}.txtKeputusan.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	doDivAjaxCall$formname('divUlasan','simpanUlasan','');
}
function hantarUlasan(){	

	if(document.${formName}.txtTarikhSurat.value == ""){
		alert('Sila masukkan Tarikh Surat');
  		document.${formName}.txtTarikhSurat.focus(); 
		return; 
	}
	if(document.${formName}.txtNoRujukanSurat.value == ""){
		alert('Sila masukkan No Rujukan Surat');
  		document.${formName}.txtNoRujukanSurat.focus(); 
		return; 
	}
	if(document.${formName}.txtKeputusan.value == ""){
		alert('Sila masukkan Keputusan');
  		document.${formName}.txtKeputusan.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	doDivAjaxCall$formname('divUlasan','hantarUlasan','');
}

function simpanDokumen() {
	
	$('err_dokumen').innerHTML = '';
	var idUlasanTeknikal = $('idUlasanTeknikal').value;
	
	if(document.${formName}.dokumen.value == ""){
		alert('Sila pilih Dokumen yang Ingin Dimuatnaik.');
  		document.${formName}.dokumen.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	$('dokumenMuatnaik').style.display = "";
	document.${formName}.enctype='multipart/form-data';
	document.${formName}.encoding ='multipart/form-data';
	document.${formName}.target='upload_dokumen';
	document.${formName}.action='?command=muatNaikDokumen&idUlasanTeknikal='+idUlasanTeknikal;
	document.${formName}.submit();
	$('dokumen').value = "";
}

function cetakDokumen(id){
	var url = "../servlet/ekptg.view.php2.FrmDisplayImage?id="+id;
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakPelan(idDokumen){
	var url = "../servlet/ekptg.view.php2.FrmDisplayImage?id="+idDokumen;
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function daftarBaru() {
	
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
	if(document.${formName}.socKementerian.value == ""){
		alert('Sila pilih Kementerian.');
		document.${formName}.socKementerian.focus(); 
		return; 
	}	
	if(document.${formName}.socAgensi.value == ""){
		alert('Sila pilih Agensi.');
		document.${formName}.socAgensi.focus(); 
		return; 
	}		
	if(document.${formName}.idHakmilikAgensi.value == "" && document.${formName}.idHakmilikSementara.value == ""){
		alert(document.${formName}.idHakmilikAgensi.value);
		alert(document.${formName}.idHakmilikSementara.value);
		alert('Sila pilih Pegangan Hakmilik.');
		return; 
	}
	if(document.${formName}.socLuasKegunaan.value == ""){
		alert('Sila masukkan Luas Kegunaan.');
  		document.${formName}.socLuasKegunaan.focus(); 
		return; 
	}
	if(document.${formName}.txtTujuanKegunaan.value == ""){
		alert('Sila masukkan Tujuan Kegunaan.');
  		document.${formName}.txtTujuanKegunaan.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.submit2.value = "daftarBaru";
		return;
	}
	
	document.${formName}.submit2.value = "papar";
	alert("test = "+document.${formName}.submit2.value);
	document.${formName}.hitButton.value = "daftarBaru";
	doAjaxCall${formName}("");
}
function doChangeKementerian() {
	doAjaxCall${formName}("doChangeKementerian");
}
function doChangeAgensi() {
	doAjaxCall${formName}("doChangeAgensi");
}
function doChangeJenisTanah() {
	doAjaxCall${formName}("doChangeJenisTanah");
}
function doChangePeganganHakmilik() {
	doAjaxCall${formName}("doChangePeganganHakmilik");
}
function doChangeKategori() {
	doAjaxCall${formName}("doChangeKategori");
}
function doChangeNegeri() {
	doAjaxCall${formName}("doChangeNegeri");
}
function doChangePejabat() {
	doAjaxCall${formName}("doChangePejabat");
}
function pilihTanah(idKategoriPemohon,idAgensiPemohon) {
	var url = "../x/${securityToken}/ekptg.view.php2.online.FrmTKROnlinePopupSenaraiTanahView?idKategoriPemohon="+idKategoriPemohon+"&idAgensiPemohon="+idAgensiPemohon;
    var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function refreshFromPilihTanah(idHakmilikAgensi) {
	
	document.${formName}.idHakmilikAgensi.value = idHakmilikAgensi;
	doAjaxCall${formName}("doChangeMaklumatTanah");
}
function janaTajuk(idKategoriPemohon) {
	
	if(document.${formName}.idHakmilikAgensi.value == "" && document.${formName}.idHakmilikSementara.value == ""){
		alert('Sila pilih Pegangan Hakmilik Sebelum Menjana Tajuk.');
		document.${formName}.idHakmilikAgensi.focus();
		return;
	}
	if(document.${formName}.socLuasKegunaan.value == ""){
		alert('Sila masukkan Luas Kegunaan.');
		document.${formName}.socLuasKegunaan.focus();
		return;
	}
		if(document.${formName}.txtTujuanKegunaan.value == ""){
		alert('Sila masukkan Tujuan Kegunaan.');
  		document.${formName}.txtTujuanKegunaan.focus(); 
		return; 
	}
	
	var strTajuk = " ";
	var luasKegunaan = " ";
	var milikOrRizab = " ";
	var pemohon = document.${formName}.namaAgensiKem.value;
	
	if(document.${formName}.socLuasKegunaan.value == "1") {
		luasKegunaan = "KESELURUHAN";
	}
	else if(document.${formName}.socLuasKegunaan.value == "2"){
		luasKegunaan = "SEBAHAGIAN";
	}
	
	var str1 = document.${formName}.noLotTanah.value;
	var str2 = document.${formName}.noMilikTanah.value;
	var str3 = document.${formName}.noWartaTanah.value;
	var str4 = document.${formName}.namaMukimTanah.value;
	var str5 = document.${formName}.namaDerahTanah.value;
	var str6 = document.${formName}.namaNegeriTanah.value;
	var kegunaanTanah = document.${formName}.kegunaanTanah.value;
	var statusRizabTnh = document.${formName}.statusRizab.value;
	var kjpTnh = document.${formName}.kementerian.value;
	var tujuanKegunaan = document.${formName}.txtTujuanKegunaan.value;
		
	if(statusRizabTnh == 'MILIK') {
		milikOrRizab = str2;
	} else if(statusRizabTnh == 'RIZAB') {
		milikOrRizab = str3;
	}
	strTajuk = "PERMOHONAN TUKARGUNA " + luasKegunaan +" TANAH " + statusRizabTnh + " PERSEKUTUAN " + str1 +", " + milikOrRizab + ", "+ str4 + ", " + str5+ ", " + str6 +" (" + kegunaanTanah + ")" + " DARIPADA " + kjpTnh + " KEPADA " + pemohon +" BAGI TUJUAN " + tujuanKegunaan;
	
	document.${formName}.txtPerkara.value = strTajuk;
}
function kembali() {
	document.${formName}.action = "?_portal_module=ekptg.view.php2.online.FrmTKROnlineKJPSenaraiFailView";
	document.${formName}.method="POST";	
	document.${formName}.submit2.value = "";
	document.${formName}.submit();
}
function seterusnya(){
	alert('BACA SETERUSNYAAA');
	document.${formName}.action = "?_portal_module=ekptg.view.php2.online.FrmTKROnlineKJPSenaraiFailView";
	document.${formName}.method="POST";	
	document.${formName}.submit2.value = "seterusnya";
	document.${formName}.submit();
}

</script>