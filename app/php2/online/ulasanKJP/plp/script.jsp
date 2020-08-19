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
</script>