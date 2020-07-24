<script>
function pilihTanah(idKementerian,idAgensi) {
	var url = "../x/${securityToken}/ekptg.view.php2.online.FrmPNWPopupSenaraiTanahOnlineView?idKementerian="+idKementerian+"&idAgensi="+idAgensi;
	
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
function doChangePeganganHakmilik() {
	doAjaxCall${formName}("doChangePeganganHakmilik");
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
// 	if(document.${formName}.socJenisTanah.value == "3"){	
		if(document.${formName}.idHakmilikAgensi.value == ""){
			alert('Sila pilih Pegangan Hakmilik.');
			return; 
		}
// 	}	
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionOnline.value = "daftarBaru";
		return;
	}
	
	document.${formName}.actionOnline.value = "daftarBaru";
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doDaftarBaru";
	document.${formName}.submit();
}
function kembali() {	
	document.${formName}.actionOnline.value = "";
	document.${formName}.submit();
}
function janaTajuk(idKategoriPemohon) {
	
	if(document.${formName}.idHakmilikAgensi.value == ""){
		alert('Sila pilih Pegangan Hakmilik Sebelum Menjana Tajuk.');
		document.${formName}.idHakmilikAgensi.focus();
		return;
	}
		
	var strTujuan = " ";
	var strTajuk = " ";
	var luasKegunaan = " ";
	var milikOrRizab = " ";
	var pemohon = " ";
	
		
	var str1 = document.${formName}.noLotTanah.value;
	var str2 = document.${formName}.noMilikTanah.value;
	var str3 = document.${formName}.noWartaTanah.value;
	var str4 = document.${formName}.namaMukimTanah.value;
	var str5 = document.${formName}.namaDerahTanah.value;
	var str6 = document.${formName}.namaNegeriTanah.value;
	var kegunaanTanah = document.${formName}.kegunaanTanah.value;
	var statusRizabTnh = document.${formName}.statusRizab.value;
	var kjpTnh = document.${formName}.namaKementerianTanah.value;
		
	if(statusRizabTnh == 'MILIK') {
		milikOrRizab = str2;
	} else if(statusRizabTnh == 'RIZAB') {
		milikOrRizab = str3;
	}
	strTajuk = "PENAWARAN TANAH " + statusRizabTnh + " PERSEKUTUAN DI ATAS " + str1 +", " + milikOrRizab + ", "+ str4 + ", " + str5+ ", " + str6 +" ( " + kegunaanTanah + " )" + " KEGUNAAN  " + kjpTnh;
	
	document.${formName}.txtPerkara.value = strTajuk;
}
</script>
