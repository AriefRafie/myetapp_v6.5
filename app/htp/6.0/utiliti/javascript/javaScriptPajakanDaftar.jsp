<script>
	//javaScriptPajakanDaftar
	//document.${formName}.actionPajakan.value = "papar";
	
	//2017/05/05 Skrin/page Senarai Fail || Pendaftaran - pilihan[Kementerian]
	function doChangeKementerian() {
		document.${formName}.actionPajakan.value = "daftarbaru";	
		document.${formName}.mode.value = "changeKementerian";
		//document.${formName}.submit();
		doAjaxCall${formName}("");

	}
	//alert('javaScriptPajakanDaftar:simpan');
	//function simpan() {	
	function simpanDaftar() {	
		if(document.${formName}.socNegeri.value == ""){
			alert('Sila pilih Negeri.');
	  		document.${formName}.socNegeri.focus(); 
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
		if(document.${formName}.socAgensi.value == ""){
			alert('Sila pilih Sub Urusan.');
	  		document.${formName}.socAgensi.focus(); 
			return; 
		}
		if(document.${formName}.socStatusTanah.value == ""){
			alert('Sila pilih Status Tanah.');
	  		document.${formName}.socStatusTanah.focus(); 
			return; 
		}
		if(document.${formName}.socJenisFail.value == ""){
			alert('Sila pilih Jenis Fail.');
	  		document.${formName}.socJenisFail.focus(); 
			return; 
		}
		/*if(document.${formName}.txtNoFailKJP.value == ""){
			alert('Sila masukkan No. Fail KJP.');
	  		document.${formName}.txtNoFailKJP.focus(); 
			return; 
		}
		if(document.${formName}.tarikhSuratKJP.value == ""){
			alert('Sila masukkan Tarikh Surat KJP.');
	  		document.${formName}.tarikhSuratKJP.focus(); 
			return; 
		}
		
		if(document.${formName}.txtNoFailLain.value == ""){
			alert('Sila masukkan No. Fail Lain.');
	  		document.${formName}.txtNoFailLain.focus(); 
			return; 
		}
		
		if(document.${formName}.tarikhAgihan.value == ""){
			alert('Sila masukkan Tarikh Agihan.');
	  		document.${formName}.tarikhAgihan.focus(); 
			return; 
		}*/
		if(document.${formName}.txtTajuk.value == ""){
			alert('Sila masukkan Tajuk.');
	  		document.${formName}.txtTajuk.focus(); 
			return; 
		}
		
		if ( !window.confirm("Adakah Anda Pasti ?") ){
			//document.${formName}.actionPajakan.value = "daftarBaru";
			return;
		}
		
		document.${formName}.actionPajakan.value = "papar";
		document.${formName}.hitButton.value = "simpan";
		document.${formName}.mode.value = "view";	
		//document.${formName}.submit();
		doAjaxCall${formName}("");
		//goToNext();
		
	}
	//alert('javaScriptPajakanDaftar:kembali');
	//function kembali() {	
	//	document.${formName}.actionPajakan.value = "";
	//	document.${formName}.submit();
	//}
 	//2017/04/17 Skrin Pendaftaran, butang [Batal] 
	function batalDaftar() {	
		document.${formName}.actionPajakan.value = "";
		//document.${formName}.submit();
		doAjaxCall${formName}("");
		
	}	
	//alert('javaScriptPajakanDaftar:goToNext');
	function goToNext(idFail){
		url = "../servlet/ekptg.view.htp.FrmPajakanServlet?command=papar&idFail="+idFail;
		actionName = "setSessionIdFail";
		target = "setSessionIdFail_result";
		doAjaxUpdater(document.${formName}, url, target, actionName);		
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanPendaftaranView";
		document.${formName}.submit();
		
	}
	//2017/04/17 Skrin Pendaftaran, butang [Seterusnya]
	function seterusDaftar(idFail){	
		langkah2(idFail,'$!idPermohonan');
	}
	//2017/04/05 Paging 2, Capaian selepas pendaftaran
	function langkah2_daftar(idFail){
		url = "../servlet/ekptg.view.htp.FrmPajakanServlet?command=papar&idFail="+idFail;
		actionName = "setSessionIdFail";
		target = "setSessionIdFail_result";
		doAjaxUpdater(document.${formName}, url, target, actionName);
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanPendaftaranView&idPermohonan="+permohonan+"&actionPajakan=papar";
		document.${formName}.actionPajakan.value = "papardaftar";
		document.${formName}.mode.value = "view";
		document.${formName}.submit();
		//doAjaxCall${formName}("");
		
	}
	//function doChangeKementerian() {
	//	doAjaxCall${formName}("doChangeKementerian");
	//}
		

</script>