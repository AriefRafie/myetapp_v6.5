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
  <input name="actionTamat" type="hidden" id="actionTamat" value="$actionTamat"/>
  <input name="idMohonTamat" type="hidden" id="idMohonTamat" value="$idMohonTamat"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idUlasanTeknikal" type="hidden" id="idUlasanTeknikal" value="$idUlasanTeknikal"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="flagPopup" type="hidden" id="flagPopup" value="$flagPopup"/>
  <input name="modePopup" type="hidden" id="modePopup" value="$modePopup"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
  <input name="selectedTabUpper" type="hidden" id="selectedTabUpper" value="$selectedTabUpper"/>  
</p>
<body>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($idFail != '')
  <tr>
    <td> #parse("app/php2/frmAPBHeader.jsp") </td>
  </tr>
  #elseif ($idFail == '' )
  <tr>
    <td><div class="warning">SILA PILIH FAIL DI SENARAI FAIL TERLEBIH DAHULU</div></td>
  </tr>
  #end
  <tr>
    <td><div id="TabbedPanels1" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <li onClick="doChangeTab(0);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT PERMOHONAN PENAMATAN LESEN</li>
          <li onClick="doChangeTab(1);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT NOTIS / SURAT</li>
          <li onClick="doChangeTab(2);" class="TabbedPanelsTab" tabindex="0">ULASAN JKPTG</li>
          <li onClick="doChangeTab(3);" class="TabbedPanelsTab" tabindex="0">MESYUARAT</li>
          <li onClick="doChangeTab(4);" class="TabbedPanelsTab" tabindex="0">KEPUTUSAN</li>
        </ul>
        <div class="TabbedPanelsContentGroup">
          <div class="TabbedPanelsContent"> <br>
            #parse("app/php2/frmAPBPTamatLesenView.jsp") </div>
          <div class="TabbedPanelsContent"> <br>
            #parse("app/php2/frmAPBPTamatLesenMaklumatNotis.jsp") </div>
          <div class="TabbedPanelsContent"> <br>
            #parse("app/php2/frmAPBPTamatLesenUlasanJKPTG.jsp") </div>
  		  <div class="TabbedPanelsContent"> <br>
            #parse("app/php2/frmAPBPTamatLesenMesyuarat.jsp") </div>              
 		  <div class="TabbedPanelsContent"> <br>
            #parse("app/php2/frmAPBPTamatLesenKeputusan.jsp") </div>           
        </div>
      </div></td>
  </tr>
</table>
<script type="text/javascript">
#if ($idFail != '')
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabUpper});
#end
</script>
<script>
function doChangeTab(tabId) {
	document.${formName}.actionTamat.value = "papar";
	document.${formName}.mode.value = "view";
	document.${formName}.selectedTabUpper.value = tabId;
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmAPBTamatLesen";
	document.${formName}.method="POST";
	document.${formName}.flagPopup.value = "";
	document.${formName}.submit();
}
function doKemaskiniTamatLesen(){
	document.${formName}.actionTamat.value = "papar";
	document.${formName}.mode.value = "update";
	document.${formName}.selectedTabUpper.value = 0;		
	document.${formName}.submit();
}
function doSimpanKemaskiniTamatLesen(){
	if(document.${formName}.tarikhSurat.value == ""){
		alert('Sila masukkan Tarikh Surat.');
  		document.${formName}.tarikhSurat.focus(); 
		return; 
	}
	if(document.${formName}.tarikhTerima.value == ""){
		alert('Sila masukkan Tarikh Terima.');
  		document.${formName}.tarikhTerima.focus(); 
		return; 
	}
/*	if(document.${formName}.rujukan.value == ""){
		alert('Sila masukkan No Rujukan.');
  		document.${formName}.rujukan.focus(); 
		return; 
	}*/
	if(document.${formName}.sebabTamat.value == ""){
		alert('Sila masukkan Sebab Penamatan.');
  		document.${formName}.sebabTamat.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionTamat.value = "papar";
		document.${formName}.mode.value = "view";
		return;
	}
	document.${formName}.actionTamat.value = "papar";
	document.${formName}.mode.value = "view";
	document.${formName}.selectedTabUpper.value = 0;	
	document.${formName}.hitButton.value = "simpanKemaskiniMaklumatMohonTamat";
	document.${formName}.submit();
}
function validateTarikh(){
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
}
function doBatalMohonTamat(){
	document.${formName}.actionTamat.value = "papar";
	document.${formName}.mode.value = "view";
	document.${formName}.selectedTabUpper.value = 0;
	document.${formName}.submit();
}
function doBatalJKPTG(){
	document.${formName}.actionTamat.value = "papar";
	document.${formName}.mode.value = "view";
	document.${formName}.selectedTabUpper.value = 1;
	document.${formName}.submit();
}
function doBatalMesyuarat(){
	document.${formName}.actionTamat.value = "papar";
	document.${formName}.mode.value = "view";
	document.${formName}.selectedTabUpper.value = 2;
	document.${formName}.submit();
}
function doBatalKeputusan(){
	document.${formName}.actionTamat.value = "papar";
	document.${formName}.mode.value = "view";
	document.${formName}.selectedTabUpper.value = 3;
	document.${formName}.submit();
}
function doKemaskiniJKPTG(){
	document.${formName}.actionTamat.value = "papar";
	document.${formName}.mode.value = "update";
	document.${formName}.selectedTabUpper.value = 1;		
	document.${formName}.submit();
}
function doSimpanKemaskiniJKPTG(){
	if(document.${formName}.ulasanJKPTG.value == ""){
		alert('Sila masukkan Ulasan JKPTG.');
  		document.${formName}.ulasanJKPTG.focus(); 
		return; 
	}

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionTamat.value = "papar";
		document.${formName}.mode.value = "view";
		return;
	}
	document.${formName}.actionTamat.value = "papar";
	document.${formName}.mode.value = "view";
	document.${formName}.selectedTabUpper.value = 1;	
	document.${formName}.hitButton.value = "simpanKemaskiniMaklumatJKPTG";
	document.${formName}.submit();
}
function doKemaskiniKeputusan(){
	document.${formName}.actionTamat.value = "papar";
	document.${formName}.mode.value = "update";
	document.${formName}.selectedTabUpper.value = 3;		
	document.${formName}.submit();
}
function simpanKemaskiniMaklumatKeputusan(){
	if(document.${formName}.tarikhKeputusan.value == ""){
		alert('Sila masukkan Tarikh Keputusan.');
  		document.${formName}.tarikhKeputusan.focus(); 
		return; 
	}
	if(document.${formName}.socKeputusan.value == ""){
		alert('Sila masukkan Keputusan.');
  		document.${formName}.socKeputusan.focus(); 
		return; 
	}
/*	if(document.${formName}.ulasanMenteri.value == ""){
		alert('Sila masukkan Ulasan Menteri.');
  		document.${formName}.ulasanMenteri.focus(); 
		return; 
	}*/	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionTamat.value = "papar";
		document.${formName}.mode.value = "view";
		return;
	}
	document.${formName}.actionTamat.value = "papar";
	document.${formName}.mode.value = "view";
	document.${formName}.selectedTabUpper.value = 3;	
	document.${formName}.hitButton.value = "simpanKemaskiniMaklumatKeputusan";
	document.${formName}.submit();
}
function doKemaskiniMesyuarat(){
	document.${formName}.actionTamat.value = "papar";
	document.${formName}.mode.value = "update";
	document.${formName}.selectedTabUpper.value = 2;		
	document.${formName}.submit();
}
function simpanKemaskiniMaklumatMesyuarat(){
	if(document.${formName}.tarikhMesyuarat.value == ""){
		alert('Sila masukkan Tarikh Mesyuarat.');
  		document.${formName}.tarikhMesyuarat.focus(); 
		return; 
	}
	if(document.${formName}.txtBilMesyuarat.value == ""){
		alert('Sila masukkan Bil Mesyuarat.');
  		document.${formName}.txtBilMesyuarat.focus(); 
		return; 
	}	
	if(document.${formName}.syorMesyuarat.value == ""){
		alert('Sila masukkan Syor Mesyuarat.');
  		document.${formName}.syorMesyuarat.focus(); 
		return; 
	}	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionTamat.value = "papar";
		document.${formName}.mode.value = "view";
		return;
	}
	document.${formName}.actionTamat.value = "papar";
	document.${formName}.mode.value = "view";
	document.${formName}.selectedTabUpper.value = 2;	
	document.${formName}.hitButton.value = "simpanKemaskiniMaklumatMesyuarat";
	document.${formName}.submit();
}
function doBackToList(){
	document.${formName}.actionTamat.value = "";
	document.${formName}.mode.value = "";
	document.${formName}.submit();
}
function seterusnya(){	

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmAPBTamatLesen";
	document.${formName}.method="POST";
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSeterusnya";
	document.${formName}.submit();
}
function textCounter(field, countfield, maxlimit) {
   if (field.value.length > maxlimit) // if too long...trim it!
		 field.value = field.value.substring(0, maxlimit);
    // otherwise, update 'Baki Aksara' counter
	else
	 countfield.value = maxlimit - field.value.length;
}
function validateLength(){
 var str = document.${formName}.txtWaktuDari.value;
 var lengthDari = str.length;
 if(lengthDari < 4 && lengthDari > 0){
 	alert(" Sila Masukan Digit Masa Yang Betul. ");
	document.${formName}.txtWaktuDari.focus(); 
 	return; 
 }
}
function validateLength2(){
 var str = document.${formName}.txtWaktuHingga.value.length;
 if(str < 4 && str > 0){
 	alert(" Sila Masukan Digit Masa Yang Betul. ");
	document.${formName}.txtWaktuHingga.focus(); 
 	return; 
 }
}
function checkAMPM(){
 var mula = document.${formName}.socMasaDari.value;
 var start = document.${formName}.txtWaktuDari.value;
 var tamat = document.${formName}.socMasaHingga.value;
 var end = document.${formName}.txtWaktuHingga.value;  
  
 //check masa mula terlebih dahulu
 if(mula ==''){
 	alert(" Sila Masukan Masa Mula Terlebih dahulu. ");
	document.${formName}.txtWaktuHingga.value=" ";
	document.${formName}.txtWaktuDari.focus(); 
	return; 
 }
 if(start ==''){
 	alert(" Sila Pilih Masa Mula Terlebih dahulu. ");
	document.${formName}.socMasaHingga.value = " ";
	document.${formName}.socMasaDari.focus(); 
	return; 
 }
 
 // validate mase
 if(mula=='PM'){
 	if( parseInt(start) > 1730 ){
	 	alert(" Sila Masukan Masa Yang Betul. ");
	 	document.${formName}.txtWaktuDari.focus(); 
	 	return; 
	 }
	else
 	if( parseInt(start) < 1200){
	 	alert(" Sila Masukan Masa Yang Betul. ");
	 	document.${formName}.txtWaktuDari.focus(); 
	 	return; 
	 }	 
 }
 else
 if(mula=='AM'){
	if(start.charAt(0)==0)
	   start = start.substring(1,4);
 	if( parseInt(start) > 1200 ){
	 	alert(" Sila Masukan Masa Yang Betul. ");
	 	document.${formName}.txtWaktuDari.focus(); 
	 	return; 
	 }
	else
 	if( parseInt(start) < 800 ){
	 	alert(" Sila Masukan Masa Yang Betul. ");
	 	document.${formName}.txtWaktuDari.focus(); 
	 	return; 
	 }
 }
}
function checkAMPM2(){

 var mula = document.${formName}.socMasaDari.value;
 var start = document.${formName}.txtWaktuDari.value;
 var tamat = document.${formName}.socMasaHingga.value;
 var end = document.${formName}.txtWaktuHingga.value;  
 
 //check masa mula terlebih dahulu
 if(mula ==''){
 	alert(" Sila Masukan Masa Mula Terlebih dahulu. ");
	document.${formName}.txtWaktuDari.focus(); 
	return; 
 }
 if(start ==''){
 	alert(" Sila Pilih Masa Mula Terlebih dahulu. ");
	document.${formName}.socMasaDari.focus(); 
	return; 
 }
 
 // validate mase
 if(tamat=='PM'){
 	if( parseInt(end) > 1730 ){
	 	alert(" Sila Masukan Masa Yang Betul. ");
	 	document.${formName}.txtWaktuHingga.focus(); 
	 	return; 
	 }
 	if( parseInt(end) < 1200){
	 	alert(" Sila Masukan Masa Yang Betul. ");
	 	document.${formName}.txtWaktuHingga.focus(); 
	 	return; 
	 }	 
 }
 else
 if(tamat=='AM'){
	if(end.charAt(0)==0)
	   end = end.substring(1,4);
 	if( parseInt(end) > 1200 ){
	 	alert(" Sila Masukan Masa Yang Betul. ");
	 	document.${formName}.txtWaktuHingga.focus(); 
	 	return; 
	 }
 	if( parseInt(end) < 800 ){
	 	alert(" Sila Masukan Masa Yang Betul. ");
	 	document.${formName}.txtWaktuHingga.focus(); 
	 	return; 
	 }
 } 

 //check if mase tamat tidak melebihi mase mula
 if(tamat == 'AM' && mula == 'PM'){
	 alert("  Sila Pilih Masa yang Betul. ");
	 document.${formName}.txtWaktuHingga.focus(); 
	return;  
 }
 if(tamat == 'PM' && mula == 'PM'){
	if( parseInt(end) < parseInt(start)){
	 	alert(" Sila Pilih Masa yang Betul. ");
	 	document.${formName}.txtWaktuHingga.focus(); 
		return;
	}  
 }
 if(tamat == 'AM' && mula == 'AM'){
	if( parseInt(end) < parseInt(start)){
	 	alert(" Sila Pilih Masa yang Betul. ");
	 	document.${formName}.txtWaktuHingga.focus(); 
		return;
	}  
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
function cetakKertasKerjaPenamatan(idFail) {
	var url = "../servlet/ekptg.report.php.APBLaporanMesyuarat_Penamatan?ID_FAIL="+idFail;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakKertasRingkasan(idFail,idMohonTamat) {
	var url = "../servlet/ekptg.report.php.APBKertasRingkasan_Penamatan?ID_FAIL="+idFail+"&ID_MOHONTAMAT="+idMohonTamat;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratPenamatan(idFail) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmAPBPopupCetakLaporanView?idFail="+idFail+"&report=cetakSuratPenamatan";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function doDaftarMaklumatNotis(){

	document.${formName}.actionTamat.value = "papar";
	document.${formName}.flagPopup.value = "openMaklumatNotis";
	document.${formName}.modePopup.value = "new";
	document.${formName}.mode.value = "add";
	document.${formName}.selectedTabUpper.value = 1;		
	document.${formName}.submit();
}

function doBatalMaklumatNotis(){
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.selectedTabUpper.value = 1;		
	document.${formName}.submit();
}

function doChangeNegeri() {
	doAjaxCall${formName}("doChangeNegeri");
}
function doChangePejabat() {
	doAjaxCall${formName}("doChangePejabat");
}
function doChangeJenisDokumen() {
	doAjaxCall${formName}("doChangeJenisDokumen");
}
function doChangeSuratKe() {
	doAjaxCall${formName}("doChangeSuratKe");
}
function doSimpanMaklumatNotis(){
	if(document.${formName}.jenisDokumen.value == ""){
		alert('Sila pilih Jenis Dokumen.');
  		document.${formName}.jenisDokumen.focus(); 
		return; 
	}
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
	
	document.${formName}.flagPopup.value = "openMaklumatNotis";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanMaklumatNotis";
	doAjaxCall${formName}("");
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

function paparMaklumatNotis(idUlasanTeknikal){
	document.${formName}.actionTamat.value = "papar";
	document.${formName}.flagPopup.value = "openMaklumatNotis";
	document.${formName}.modePopup.value = "view";
	document.${formName}.idUlasanTeknikal.value = idUlasanTeknikal;
	document.${formName}.selectedTabUpper.value= 1;
	document.${formName}.submit();
}

function doSimpanMaklumatUlanganNotis(){	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "openMaklumatNotis";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanMaklumatUlanganNotis";
	doAjaxCall${formName}("");
}

function doBatalMaklumatNotis(){
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	doAjaxCall${formName}("");
}
function doKemaskiniMaklumatNotis(idUlasanTeknikal){
	document.${formName}.actionTamat.value = "papar";
	document.${formName}.flagPopup.value = "openMaklumatNotis";
	document.${formName}.modePopup.value = "update";
	document.${formName}.idUlasanTeknikal.value = idUlasanTeknikal;
	document.${formName}.selectedTabUpper.value= 1;	
	document.${formName}.submit();
}

function doBatalKemaskiniMaklumatNotis(){
	//document.${formName}.flagPopup.value = "openMaklumatNotis";
	//document.${formName}.modePopup.value = "view";
	//doAjaxCall${formName}("");
	
	//document.${formName}.actionTamat.value = "papar";
	//document.${formName}.flagPopup.value = "openMaklumatNotis";
	//document.${formName}.modePopup.value = "view";
	//document.${formName}.idUlasanTeknikal.value = idUlasanTeknikal;
	//document.${formName}.selectedTabUpper.value= 1;	
	//document.${formName}.submit();
	//doAjaxCall${formName}("");
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.selectedTabUpper.value = 1;		
	document.${formName}.submit();
}

function doSimpanKemaskiniMaklumatNotis(){
	
	if(document.${formName}.jenisDokumen.value == ""){
		alert('Sila pilih Jenis Dokumen.');
  		document.${formName}.jenisDokumen.focus(); 
		return; 
	}
	
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
	
	document.${formName}.flagPopup.value = "openMaklumatNotis";
	document.${formName}.modePopup.value = "view";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniMaklumatNotis";
	doAjaxCall${formName}("");
}

function doHapusMaklumatNotis(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	var idUlasanTeknikal = document.getElementById('idUlasanTeknikalDokumen').value
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.idUlasanTeknikal.value = idUlasanTeknikal;
	document.${formName}.hitButton.value = "hapusMaklumatKJPKJT";
	doAjaxCall${formName}("");
}

function doTerimaNotis(){
	document.${formName}.flagPopup.value = "openMaklumatNotis";
	document.${formName}.modePopup.value = "update";
	document.${formName}.flagStatus.value = "2";
	doAjaxCall${formName}("");
}

function doUlanganNotis(){
	document.${formName}.flagPopup.value = "openMaklumatNotis";
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
