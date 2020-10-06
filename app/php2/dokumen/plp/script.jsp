<script>
function paparLaporan(idLaporan){
	doDivAjaxCall$formname('divMainForm','paparLaporan','idLaporan='+ idLaporan);
}

function simpanDokumen() {
	$('err_dokumen').innerHTML = '';
	var idLaporan = $('id').value;
	
	if(document.${formName}.namaDokumen.value == ""){
		alert('Sila masukkan Nama Dokumen.');
  		document.${formName}.namaDokumen.focus(); 
		return; 
	}
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
		document.${formName}.action='?command=muatNaikDokumen&idLaporan='+idLaporan+'&namaDokumen='+$('namaDokumen').value+'&catatan='+$('catatan').value;
		document.${formName}.submit();
		$('namaDokumen').value = "";
		$('dokumen').value = "";
		$('catatan').value = "";
}

function muatTurun(id){
	var url = "../servlet/ekptg.view.php2.FrmDisplayDokumen?id="+id;
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function deleteDokumen(id){	
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	doDivAjaxCall$formname('divMainForm','hapusDokumen','idDokumen='+id);
}

function lihatHistory(id){
	var url = "../x/${securityToken}/ekptg.view.integrasi.macgdi.FrmSenaraiHistoryAPB?id="+id;
    	var hWnd = window.open(url,'printuser','width=600,height=300, resizable=yes,scrollbars=yes');
    	if ((document.window != null) && (!hWnd.opener))
       		hWnd.opener = document.window;
    	if (hWnd.focus != null) hWnd.focus();
			hWnd.focus();
}
</script>