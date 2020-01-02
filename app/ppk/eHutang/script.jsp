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

function simpanKemaskiniPenghutang(){	
	
	if(document.${formName}.nama.value == ""){
		alert('Sila masukkan Nama.');
  		document.${formName}.nama.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	doDivAjaxCall$formname('divMainForm','kemaskiniPenghutang2','');
}

function kemaskiniPenghutang(){	
	
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

function simpanKemaskiniHutang(){	
	if(document.${formName}.nama.value == ""){
		alert('Sila masukkan Nama.');
  		document.${formName}.nama.focus(); 
		return; 
	}
	if(document.${formName}.alamat1.value == ""){
		alert('Sila masukkan Alamat Agensi.');
  		document.${formName}.alamat1.focus(); 
		return; 
	}	
	if(document.${formName}.socNegeri.value == ""){
		alert('Sila masukkan maklumat Negeri.');
  		document.${formName}.socNegeri.focus(); 
		return; 
	}
	if(document.${formName}.socBandar.value == ""){
		alert('Sila masukkan maklumat Bandar.');
  		document.${formName}.socBandar.focus(); 
		return; 
	}
	
	if(document.${formName}.poskod.value == ""){
		alert('Sila masukkan Poskod Agensi.');
  		document.${formName}.poskod.focus(); 
		return; 
	}
	if(document.${formName}.noTelefon.value == ""){
		alert('Sila masukkan No. Telefon Agensi.');
  		document.${formName}.noTelefon.focus(); 
		return; 
	}
	if(document.${formName}.jenisHutang.value == ""){
		alert('Sila masukkan Jenis Hutang.');
  		document.${formName}.jenisHutang.focus(); 
		return; 
	}	
	if(document.${formName}.noAkaun.value == ""){
		alert('Sila masukkan No. Akaun');
  		document.${formName}.noAkaun.focus(); 
		return; 
	}	
	if(document.${formName}.tarikhPerjanjian.value == ""){
		alert('Sila masukkan Tarikh Perjanjian.');
  		document.${formName}.tarikhPerjanjian.focus(); 
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
	if(document.${formName}.statusHutang.value == ""){
		alert('Sila masukkan Status Hutang sama ada Telah Selesai atau Belum Selesai.');
  		document.${formName}.statusHutang.focus(); 
		return; 
	}
	if((document.${formName}.statusHutang.value == "Y") && (document.${formName}.tarikhSelesaiHutang.value == ""))
	{
		alert('Sila masukkan Tarikh Hutang Diselesaikan.');
  		document.${formName}.statusHutang.focus(); 
		return; 
	}
	if(document.${formName}.insuransHutang.value == ""){
		alert('Sila masukkan maklumat berkaitan Insurans Hutang.');
  		document.${formName}.insuransHutang.focus(); 
		return; 
	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	doDivAjaxCall$formname('divMainForm','kemaskiniHutang2','');
}

function doChangeTempoh() {
	document.${formName}.sorTempoh2.value = document.${formName}.sorTempoh.value;
	var sorTempoh2 = document.${formName}.sorTempoh2.value;
	document.${formName}.jenisLaporan2.value = document.${formName}.jenisLaporan.value;
	var jenisLaporan2 = document.${formName}.jenisLaporan2.value;
	//alert ("sorTempoh2 dalam doChangeTempoh = "+sorTempoh2);
	doDivAjaxCall$formname('divMainForm','doChangeTempoh','');
}

function cetakLaporaneHutangAgensi() {
	var extend;
	if(document.${formName}.namaAgensi.value == ""){
		alert('Sila masukkan Nama Agensi.');
  		document.${formName}.namaAgensi.focus(); 
		return; 
	}	
	if(document.${formName}.sorTempoh.value == "1")
	{	if (document.${formName}.bulan1.value == "")
		{
			alert('Sila masukkan Bulan.');
  			document.${formName}.bulan1.focus(); 
			return; 
		}
		if (document.${formName}.tahun1.value == "")
		{
			alert('Sila masukkan Tahun.');
			document.${formName}.tahun1.focus(); 
			return; 
		}
		bulan1 = document.${formName}.bulan1.value;
		tahun1 = document.${formName}.tahun1.value;
		extend="&bulan1="+bulan1+"&tahun1="+tahun1;
		//alert (extend);
	}	
	if(document.${formName}.sorTempoh.value == "2")
	{	
		if (document.${formName}.tahun1.value == "")
		{
			alert('Sila masukkan Tahun.');
			document.${formName}.tahun1.focus(); 
			return; 
		}
		
		tahun1 = document.${formName}.tahun1.value;
		extend="&tahun1="+tahun1;
	}	
	if(document.${formName}.sorTempoh.value == "3")
	{	
		if (document.${formName}.bulan1.value == "")
		{
			alert('Sila masukkan Bulan Mula.');
  			document.${formName}.bulan1.focus(); 
			return; 
		}
		
		if (document.${formName}.tahun1.value == "")
		{
			alert('Sila masukkan Tahun Mula.');
			document.${formName}.tahun1.focus(); 
			return; 
		}
		
		if (document.${formName}.bulan2.value == "")
		{
			alert('Sila masukkan Bulan Akhir.');
  			document.${formName}.bulan2.focus(); 
			return; 
		}
		
		if (document.${formName}.tahun2.value == "")
		{
			alert('Sila masukkan Tahun Akhir.');
			document.${formName}.tahun2.focus(); 
			return; 
		}
		bulan1 = document.${formName}.bulan1.value;
		tahun1 = document.${formName}.tahun1.value;
		bulan2 = document.${formName}.bulan2.value;
		tahun2 = document.${formName}.tahun2.value;
		extend="&bulan1="+bulan1+"&tahun1="+tahun1+"&bulan2="+bulan2+"&tahun2="+tahun2;
		
		
	}	
	
	document.${formName}.sorTempoh2.value = document.${formName}.sorTempoh.value;
	var sorTempoh2 = document.${formName}.sorTempoh2.value;
	document.${formName}.jenisLaporan2.value = document.${formName}.jenisLaporan.value;
	var jenisLaporan2 = document.${formName}.jenisLaporan2.value;
	var namaAgensi = document.${formName}.namaAgensi.value;
	
	//alert ("sorTempoh2 dalam cetakLaporaneHutangAgensi = "+sorTempoh2);
	//doDivAjaxCall$formname('divMainForm','cetakLaporaneHutangAgensi','');
	
	
	var url = "../x/${securityToken}/ekptg.view.ppk.LaporaneHutang?command=cetakLaporaneHutangAgensi&namaAgensi="+namaAgensi+"&sorTempoh2="+sorTempoh2+"&jenisLaporan2="+jenisLaporan2+"&Cetak=cetak"+extend;
    var hWnd=window.open(url,'Cetak','width=800,height=300, resizable=yes,scrollbars=yes,menubar=1');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
    
}

function cetakLaporaneHutangAgensi2() {
	var extend;
	//alert("1");
	if(document.${formName}.sorTempoh.value == "1")
	{	if (document.${formName}.bulan1.value == "")
		{
			alert('Sila masukkan Bulan.');
  			document.${formName}.bulan1.focus(); 
			return; 
		}
		if (document.${formName}.tahun1.value == "")
		{
			alert('Sila masukkan Tahun.');
			document.${formName}.tahun1.focus(); 
			return; 
		}
		bulan1 = document.${formName}.bulan1.value;
		tahun1 = document.${formName}.tahun1.value;
		extend="&bulan1="+bulan1+"&tahun1="+tahun1;
		//alert (extend);
	}
	//alert("2");
	if(document.${formName}.sorTempoh.value == "2")
	{	
		if (document.${formName}.tahun1.value == "")
		{
			alert('Sila masukkan Tahun.');
			document.${formName}.tahun1.focus(); 
			return; 
		}
		
		tahun1 = document.${formName}.tahun1.value;
		extend="&tahun1="+tahun1;
	}
	//alert("3");
	if(document.${formName}.sorTempoh.value == "3")
	{	
		if (document.${formName}.bulan1.value == "")
		{
			alert('Sila masukkan Bulan Mula.');
  			document.${formName}.bulan1.focus(); 
			return; 
		}
		
		if (document.${formName}.tahun1.value == "")
		{
			alert('Sila masukkan Tahun Mula.');
			document.${formName}.tahun1.focus(); 
			return; 
		}
		
		if (document.${formName}.bulan2.value == "")
		{
			alert('Sila masukkan Bulan Akhir.');
  			document.${formName}.bulan2.focus(); 
			return; 
		}
		
		if (document.${formName}.tahun2.value == "")
		{
			alert('Sila masukkan Tahun Akhir.');
			document.${formName}.tahun2.focus(); 
			return; 
		}
		bulan1 = document.${formName}.bulan1.value;
		tahun1 = document.${formName}.tahun1.value;
		bulan2 = document.${formName}.bulan2.value;
		tahun2 = document.${formName}.tahun2.value;
		extend="&bulan1="+bulan1+"&tahun1="+tahun1+"&bulan2="+bulan2+"&tahun2="+tahun2;
		
		
	}	
	//alert("4");
	document.${formName}.sorTempoh2.value = document.${formName}.sorTempoh.value;
	var sorTempoh2 = document.${formName}.sorTempoh2.value;
	document.${formName}.jenisLaporan2.value = document.${formName}.jenisLaporan.value;
	var jenisLaporan2 = document.${formName}.jenisLaporan2.value;
	//alert("5");
	var namaAgensi = document.${formName}.vnamaAgensixx.value;
	//alert("Nama Agensi = "+namaAgensi);
	//alert ("sorTempoh2 dalam cetakLaporaneHutangAgensi = "+sorTempoh2);
	//doDivAjaxCall$formname('divMainForm','cetakLaporaneHutangAgensi','');
	
	
	var url = "../x/${securityToken}/ekptg.view.ppk.LaporaneHutang?command=cetakLaporaneHutangAgensi&namaAgensi="+namaAgensi+"&sorTempoh2="+sorTempoh2+"&jenisLaporan2="+jenisLaporan2+"&Cetak=cetak"+extend;
    var hWnd=window.open(url,'Cetak','width=800,height=300, resizable=yes,scrollbars=yes,menubar=1');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
    
}

function myFunction() {
    window.print();
}

function cetakLaporaneHutangAgensibyPPK_step1() {
	document.${formName}.jenisLaporan2.value = document.${formName}.jenisLaporan.value;
	var jenisLaporan2 = document.${formName}.jenisLaporan2.value;
	
	doDivAjaxCall$formname('divMainForm','cetakLaporaneHutangAgensibyPPK_step1','');
}

function simpanHutang(){	
	if(document.${formName}.nama.value == ""){
		alert('Sila masukkan Nama.');
  		document.${formName}.nama.focus(); 
		return; 
	}	
	if(document.${formName}.alamat1.value == ""){
		alert('Sila masukkan Alamat Agensi.');
  		document.${formName}.alamat1.focus(); 
		return; 
	}	
		
	if(document.${formName}.poskod.value == ""){
		alert('Sila masukkan Poskod Agensi.');
  		document.${formName}.poskod.focus(); 
		return; 
	}	
	if(document.${formName}.socNegeri.value == ""){
		alert('Sila masukkan maklumat Negeri.');
  		document.${formName}.socNegeri.focus(); 
		return; 
	}
	if(document.${formName}.socBandar.value == ""){
		alert('Sila masukkan maklumat Bandar.');
  		document.${formName}.socBandar.focus(); 
		return; 
	}
	if(document.${formName}.noTelefon.value == ""){
		alert('Sila masukkan No. Telefon Agensi.');
  		document.${formName}.noTelefon.focus(); 
		return; 
	}
	if(document.${formName}.jenisHutang.value == ""){
		alert('Sila masukkan Jenis Hutang.');
  		document.${formName}.jenisHutang.focus(); 
		return; 
	}
	if(document.${formName}.noAkaun.value == ""){
		alert('Sila masukkan No. Akaun');
  		document.${formName}.noAkaun.focus(); 
		return; 
	}	
	if(document.${formName}.tarikhPerjanjian.value == ""){
		alert('Sila masukkan Tarikh Perjanjian.');
  		document.${formName}.tarikhPerjanjian.focus(); 
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
	if(document.${formName}.insuransHutang.value == ""){
		alert('Sila masukkan maklumat berkaitan Insurans Hutang.');
  		document.${formName}.insuransHutang.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	doDivAjaxCall$formname('divMainForm','simpanHutang','');
}

function kemaskiniHutang(){	
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