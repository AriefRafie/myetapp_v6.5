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
  <input name="idBorangA" type="hidden" id="idBorangA" value="$idBorangA"/>
  <input name="actionOnline" type="hidden" id="actionOnline" value="$actionOnline"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="hitButton" type="hidden" id="hitButton" value="$hitButton"/>
  <input name="idfail" type="hidden" id="idfail" value="$idFail"/>
   
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idJadualKeduaLesen" type="hidden" id="idJadualKeduaLesen" value="$idJadualKeduaLesen"/>
  
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  
  <tr>
    <td> #parse("app/php2/online/frmAPBBorangAOnlineHeader.jsp") </td>
  </tr>
  
  <tr>
    <td>&nbsp;</td>
  </tr>
  #if (($mode != 'newBarge') && ($mode != 'viewBarge')&& ($mode != 'updateBarge'))
  <tr>
    <td><fieldset>
      <legend><strong>MAKLUMAT PENGAMBILAN PASIR</strong></legend>
      #parse("app/php2/online/frmAPBBorangAOnlineAmbilPasirDetail.jsp")
      </fieldset></td>
  </tr>
  #end
  #if(($mode == 'view')||($mode == 'newBarge') || ($mode == 'viewBarge') || ($mode == 'updateBarge'))
  
  <tr>
    <td>
      #parse("app/php2/online/frmAPBBorangAOnlineMaklumatBarge.jsp")
      </td>
  </tr>
  #end
  <tr>
    <td>&nbsp;</td>
  </tr>
   
</table>
<fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
       <tr>
        <td><a href="#" class="style2" onClick="javascript:cetakAPBLaporanBorangA('$idBorangA')"><font color="blue"> Borang A </font></a></td>
      </tr>           
    </table>
</fieldset>

<script>
function cekTarikh(elmnt) {
//CHECK DATE   
	//CHECK DATE 
	var str1  = document.${formName}.txtTarikhMula.value;		   
	var dt1   = parseInt(str1.substring(0,2),10);
	var mon1  = parseInt(str1.substring(3,5),10)-1;
	var yr1   = parseInt(str1.substring(6,10),10);
	var tarikhMula = new Date(yr1, mon1, dt1);
	
	var str2  =  document.${formName}.txtTarikhTamat.value;		   
	var dt2   = parseInt(str2.substring(0,2),10);
	var mon2  = parseInt(str2.substring(3,5),10)-1;
	var yr2   = parseInt(str2.substring(6,10),10);
	var tarikhTamat = new Date(yr2, mon2, dt2);
	
	var currentDate = new Date();
	
	if (tarikhMula > tarikhTamat){
		alert('Tarikh Mula Operasi tidak boleh melebihi dari Tarikh Tamat Operasi.');
		elmnt.value ="";
  		document.${formName}.txtTarikhTamat.focus(); 
		return;
	}
}
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function cetakAPBLaporanBorangA(id_borangA) {

	//var url = "../servlet/ekptg.report.php2.APBLaporanBorangA?idBorangA="+id_borangA;
	var url = "../servlet/ekptg.report.php2.online.PengesahanOnline?template=APBLaporanBorangA&folder=ONLINE&idBorangA="+id_borangA;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function doSimpanMaklumatPasir(idFail,idJadualKeduaLesen){
	if(document.${formName}.socBulan.value == ""){
		alert('Sila pilih bulan.');
  		document.${formName}.socBulan.focus(); 
		return; 
	}
	if(document.${formName}.txtTahun.value == ""){
		alert('Sila masukkan Tahun.');
  		document.${formName}.txtTahun.focus(); 
		return; 
	}
	if(document.${formName}.txtAnggaranPasir.value == ""){
		alert('Sila masukkan Anggaran Pasir.');
  		document.${formName}.txtAnggaranPasir.focus(); 
		return; 
	}
	if(document.${formName}.txtKontraktor.value == ""){
		alert('Sila masukkan maklumat Kontraktor.');
  		document.${formName}.txtKontraktor.focus(); 
		return; 
	}
	if(document.${formName}.txtTujuanAmbil.value == ""){
		alert('Sila masukkan Tujuan Pengambilan.');
  		document.${formName}.txtTujuanAmbil.focus(); 
		return; 
	}
	if(document.${formName}.txtDestinasiDihantar.value == ""){
		alert('Sila masukkan Destinasi Pasir Dihantar.');
  		document.${formName}.txtDestinasiDihantar.focus(); 
		return; 
	}
	if(document.${formName}.txtPembeli.value == ""){
		alert('Sila masukkan Nama Syarikat Pembeli.');
  		document.${formName}.txtPembeli.focus(); 
		return; 
	}
	if(document.${formName}.txtTarikhMula.value == ""){
		alert('Sila masukkan Tarikh Mula Operasi.');
  		document.${formName}.txtTarikhMula.focus(); 
		return; 
	}
	if(document.${formName}.txtTarikhTamat.value == ""){
		alert('Sila masukkan Tarikh Tamat Operasi.');
  		document.${formName}.txtTarikhTamat.focus(); 
		return; 
	}
	
	if(document.${formName}.txtLabelTitik.value == ""){
		alert('Sila masukkan Label Titik.');
  		document.${formName}.txtLabelTitik.focus(); 
		return; 
	}
	if(document.${formName}.txtDarjahT.value == ""){
		alert('Sila masukkan Darjah T.');
  		document.${formName}.txtDarjahT.focus(); 
		return; 
	}
	if(document.${formName}.txtDarjahU.value == ""){
		alert('Sila masukkan Darjah U.');
  		document.${formName}.txtDarjahU.focus(); 
		return; 
	}
	if(document.${formName}.txtMinitT.value == ""){
		alert('Sila masukkan Minit T.');
  		document.${formName}.txtMinitT.focus(); 
		return; 
	}
	if(document.${formName}.txtMinitU.value == ""){
		alert('Sila masukkan Minit U.');
  		document.${formName}.txtMinitU.focus(); 
		return; 
	}
	
	if(document.${formName}.txtLaluan.value == ""){
		alert('Sila masukkan Laluan.');
  		document.${formName}.txtLaluan.focus(); 
		return; 
	}
	if(document.${formName}.txtKaedah.value == ""){
		alert('Sila masukkan Kaedah Pengambilan Pasir.');
  		document.${formName}.txtKaedah.focus(); 
		return; 
	}
	if(document.${formName}.txtKawasan.value == ""){
		alert('Sila masukkan Kawasan Pelupusan.');
  		document.${formName}.txtKawasan.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.actionOnline.value = "daftarBaruBorangA";
	document.${formName}.hitButton.value = "simpanMaklumatAmbilPasir";
	// document.${formName}.actionOnline.value = "doMaklumatPasir";
	// document.${formName}.mode.value = "view";
	//document.${formName}.submit();
	document.${formName}.idFail.value = idFail;
	document.${formName}.idJadualKeduaLesen.value = idJadualKeduaLesen;
	document.${formName}.submit();
	// doAjaxCall${formName}("");
}
function doSimpanKemasMaklumatPasir(){
	
	if(document.${formName}.socBulan.value == ""){
		alert('Sila pilih Bulan.');
  		document.${formName}.socBulan.focus(); 
		return; 
	}
	if(document.${formName}.txtTahun.value == ""){
		alert('Sila masukkan Tahun.');
  		document.${formName}.txtTahun.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}

	document.${formName}.actionOnline.value = "doMaklumatPasir";
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniMaklumatPasir";
	// document.${formName}.submit();
	doAjaxCall${formName}("");
}
function doKemaskiniMaklumatPasir(){

	document.${formName}.actionOnline.value = "doMaklumatPasir";
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}
function doBatalKemaskiniMaklumatPasir(){
	
	document.${formName}.actionOnline.value = "doMaklumatPasir";
	document.${formName}.mode.value = "view";
	// document.${formName}.mode.value = "new";
	doAjaxCall${formName}("");
}
function doKembali(idFail,idJadualKeduaLesen){
	
	document.${formName}.actionOnline.value = "daftarBaruBorangA";
	document.${formName}.idFail.value = idFail;
	document.${formName}.idJadualKeduaLesen.value = idJadualKeduaLesen;
	document.${formName}.submit();
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
function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}
</script>
