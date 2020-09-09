<script type="text/javascript" >

	document.${formName}.actionPajakan.value = "paparpemohon";
	//MJM, TAB MAKLUMAT PEMOHON
	function SimpanPemohon(){

		if(document.${formName}.txtNama.value == ""){
			alert('Sila masukkan Nama.');
	  		document.${formName}.txtNama.focus();
			return;
		}
		if(document.${formName}.txtAlamat1.value == ""){
			alert('Sila masukkan Alamat.');
	  		document.${formName}.txtAlamat1.focus();
			return;
		}
		if(document.${formName}.txtPoskod.value == ""){
			alert('Sila masukkan Poskod');
	  		document.${formName}.txtPoskod.focus();
			return;
		}
		if(document.${formName}.socNegeri.value == ""){
			alert('Sila pilih Negeri.');
	  		document.${formName}.socNegeri.focus();
			return;
		}
		if(document.${formName}.socDaerah.value == ""){
			alert('Sila pilih Daerah.');
	  		document.${formName}.socDaerah.focus();
			return;
		}
		if ( !window.confirm("Adakah Anda Pasti ?") ){
			//document.${formName}.actionPajakan.value = "papar";
			document.${formName}.actionPajakan.value = "paparpemohon";
			return;
		}

		document.${formName}.mode.value = "view";
		document.${formName}.hitButton.value = "savePemohon";
		document.${formName}.submit();
		//doAjaxCall${formName}("");
	}

	function batalPemohon(){
		//document.${formName}.actionPajakan.value = "papar";
		document.${formName}.actionPajakan.value = "paparpemohon";
		document.${formName}.mode.value = "view";
		document.${formName}.submit();
		//doAjaxCall${formName}("");
	}

	function kemaskiniPemohon(){
		//document.${formName}.actionPajakan.value = "papar";
		//document.${formName}.actionPajakan.value = "paparmjm";
		document.${formName}.mode.value = "update";
		document.${formName}.submit();
		//doAjaxCall${formName}("");
	}

	function setTable(id){
		if(document.getElementById(id).style.display=="none"){
			document.getElementById(id).style.display="block";
		}else if(document.getElementById(id).style.display=="block"){
			document.getElementById(id).style.display="none";
		}
	}

	function cetakSuratPemohon(idpermohonan){
		/*var url = "../servlet/ekptg.report.htp.SuratKepadaPemohonPajakan?idpermohonan="+idpermohonan;
	    var hWnd = window.open(url,'Cetak','full=yes, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
	   	hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();*/
		var url = "../x/${securityToken}/ekptg.report.htp.utiliti.FrmPopupPilihPegawaiReportView?idpermohonan="+idpermohonan+"&report=SuratKepadaPemohonPajakan&selectNoFail=no";
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();

	}

	function cetakSuratJKPTGNegeri(idpermohonan){
		/*var url = "../x/${securityToken}/ekptg.report.htp.utiliti.FrmPopupPilihPegawaiReportView?idpermohonan="+idpermohonan+"&report=SuratKepadaJKPTGNegeri&selectNoFail=no";
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus(); */
	    cetakSuratExt(idpermohonan, 'HTPajakanSuratJKPTGNegeri');

	}

	function cetakSuratExt_javaScriptPajakanPermohonan(idPermohonan, laporan){
		var url = "../x/${securityToken}/ekptg.report.htp.utiliti.FrmPopupPilihPegawaiReportView?idpermohonan="+idPermohonan+"&report="+laporan;
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');

		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();

	}
</script>