<script>
function doChangeNegeri(){
	doDivAjaxCall$formname('divBandar','doChangeNegeri','');
}

function paparPenghutang(id){
	doDivAjaxCall$formname('divMainForm','paparPenghutang','id='+ id);
}

function simpanPenghutang(){	
	var resultSemakanNoPengenalanBaru = document.${formName}.resultSemakanNoPengenalanBaru.value;
	if (resultSemakanNoPengenalanBaru == 'Y') {
		alert('MyID Telah Wujud.');
  		document.${formName}.noPengenalanBaru.focus(); 
		return; 
	}
	
	if(document.${formName}.noPengenalanBaru.value == ""){
		alert('Sila masukkan MyID Baru.');
  		document.${formName}.noPengenalanBaru.focus(); 
		return; 
	}
	if(document.${formName}.nama.value == ""){
		alert('Sila masukkan Nama.');
  		document.${formName}.nama.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	doDivAjaxCall$formname('divMainForm','simpanPenghutang','');
}

function kemaskiniPenghutang(){	
	if(document.${formName}.nama.value == ""){
		alert('Sila masukkan Nama.');
  		document.${formName}.nama.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	doDivAjaxCall$formname('divMainForm','kemaskiniPenghutang','');
}

function hapusPenghutang(id){	
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	doDivAjaxCall$formname('divMainForm','hapusPenghutang','id='+id);
}

function paparHutang(id){
	doDivAjaxCall$formname('divMainForm','paparHutang','id='+ id);
}

function simpanHutang(){	
	if(document.${formName}.nama.value == ""){
		alert('Sila masukkan Nama.');
  		document.${formName}.nama.focus(); 
		return; 
	}	
	if(document.${formName}.jenisHutang.value == ""){
		alert('Sila masukkan Jenis Hutang.');
  		document.${formName}.jenisHutang.focus(); 
		return; 
	}	
	if(document.${formName}.nilaiHutang.value == ""){
		alert('Sila masukkan Nilai Hutang.');
  		document.${formName}.nilaiHutang.focus(); 
		return; 
	}
	if(document.${formName}.bakiHutang.value == ""){
		alert('Sila masukkan Baki Hutang.');
  		document.${formName}.bakiHutang.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	doDivAjaxCall$formname('divMainForm','simpanHutang','');
}

function kemaskiniHutang(){	
	if(document.${formName}.nama.value == ""){
		alert('Sila masukkan Nama.');
  		document.${formName}.nama.focus(); 
		return; 
	}	
	if(document.${formName}.jenisHutang.value == ""){
		alert('Sila masukkan Jenis Hutang.');
  		document.${formName}.jenisHutang.focus(); 
		return; 
	}	
	if(document.${formName}.nilaiHutang.value == ""){
		alert('Sila masukkan Nilai Hutang.');
  		document.${formName}.nilaiHutang.focus(); 
		return; 
	}
	if(document.${formName}.bakiHutang.value == ""){
		alert('Sila masukkan Baki Hutang.');
  		document.${formName}.bakiHutang.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	doDivAjaxCall$formname('divMainForm','kemaskiniHutang','');
}

function hapusHutang(id){	
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	doDivAjaxCall$formname('divMainForm','hapusHutang','id='+id);
}

function simpanDokumen(id) {
	$('err_dokumen').innerHTML = '';
	var idHutang = $('idHutang').value;
	var idSenaraiHutang = $('idSenaraiHutang').value;
	
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
		document.${formName}.action='?command=muatNaikDokumen&idHutang='+idHutang+'&idSenaraiHutang='+idSenaraiHutang+'&namaDokumen='+$('namaDokumen').value;
		document.${formName}.submit();
		$('namaDokumen').value = "";
		$('dokumen').value = "";
}

function cetakImej(id){
	var url = "../servlet/ekptg.view.ppk.FrmDisplayDokumenSenaraiHutang?id="+id;
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function deleteDokumen(id){	
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	doDivAjaxCall$formname('divDokumenSokongan','hapusDokumen','idDokumen='+id);
}
</script>