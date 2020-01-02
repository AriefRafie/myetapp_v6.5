<script type="text/javascript" >

	function cetakSuratBayaran(idp){
		cetakSuratExt(idp, "HTPajakanSuratBayaran");
	}
	
	function daftarBaruBayaran(){
		document.${formName}.actionPajakan.value = "BayaranPajakan";
		document.${formName}.mode.value = "newBayaran";
		doAjaxCall${formName}("");
		
	}

	function cetakSuratExt_javaScriptPajakanBayaran(idPermohonan, laporan){
		var url = "../x/${securityToken}/ekptg.report.htp.utiliti.FrmPopupPilihPegawaiReportView?idpermohonan="+idPermohonan+"&report="+laporan;	
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
	
	}

	function simpanBayaran(){
		if(document.${formName}.socTujuan.value == ""){
			alert('Sila Pilih Tujuan.');
	  		document.${formName}.socTujuan.focus(); 
			return; 
		}
		if(document.${formName}.socCaraBayar.value == ""){
			alert('Sila Pilih Cara Bayar.');
	  		document.${formName}.socCaraBayar.focus(); 
			return; 
		}
		
		/*
		if(document.${formName}.txdTarikhTerima.value == ""){
			alert('Sila Masukkan Tarikh Terima.');
	  		document.${formName}.txdTarikhTerima.focus(); 
			return; 
		}
		
		*/
		if(document.${formName}.txdTarikhCek.value != "" ){
			//return checkDate(document.${formName}.txdTarikhCek);
			checkDateV01(document.${formName}.txdTarikhCek);
		}
		if(document.${formName}.txdTarikhTerima.value != "" ){
			//return checkDate(document.${formName}.txdTarikhTerima);
			//checkDateV01(document.${formName}.txdTarikhTerima);		
			checkDate(document.${formName}.txdTarikhTerima);		
		}	
		if(document.${formName}.txtJumlahBayaran.value == ""){
			alert('Sila Masukkan Jumlah Bayaran.');
	  		document.${formName}.txtJumlahBayaran.focus(); 
			return; 
		}
		/**
		Kemaskini pada 2017/12/13 - Peringkat awal tiada Maklumat Resit
		if(document.${formName}.txtNoResit.value == ""){
			alert('Sila Masukkan No Resit.');
	  		document.${formName}.txtNoResit.focus(); 
			return; 
		}
		if(document.${formName}.txdtarikhResit.value == ""){
			alert('Sila Masukkan Tarikh Resit.');
	  		document.${formName}.txdtarikhResit.focus(); 
			return; 
		}
		
		if(document.${formName}.txdtarikhResit.value != ''){
			checkDateV01(document.${formName}.txdtarikhResit);
		}
		
		if(document.${formName}.txdtarikhHantarResit.value == ''){
			alert('Sila Masukkan Tarikh Hantar Resit.');
	  		document.${formName}.txdtarikhHantarResit.focus(); 
			return; 
		}
		
		if(document.${formName}.txdtarikhHantarResit.value != ''){
			checkDateV01(document.${formName}.txdtarikhHantarResit);
		} */
		
		if ( !window.confirm("Adakah Anda Pasti ?") ){
			document.${formName}.actionPajakan.value = "BayaranPajakan";
			return;
		}
	
		document.${formName}.hitButton.value = "saveBayaran";
		document.${formName}.mode.value = "view";
		doAjaxCall${formName}("");
	}
	function batalBayaran(){
		document.${formName}.actionPajakan.value = "BayaranPajakan";
		document.${formName}.mode.value = "view";
		doAjaxCall${formName}("");
	}
	function kemaskiniBayaran(){
		document.${formName}.actionPajakan.value = "BayaranPajakan";
		document.${formName}.mode.value = "updateBayaran";
		doAjaxCall${formName}("");
	}
	
	function simpanUpdateBayaran(){
		
		if(document.${formName}.socTujuan.value == ""){
			alert('Sila pilih Tujuan.');
	  		document.${formName}.socTujuan.focus(); 
			return; 
		}
		if(document.${formName}.socCaraBayar.value == ""){
			alert('Sila pilih Cara Bayar.');
	  		document.${formName}.socCaraBayar.focus(); 
			return; 
		}
		
		/*
		if(document.${formName}.txdTarikhTerima.value == ""){
			alert('Sila Masukkan Tarikh Terima.');
	  		document.${formName}.txdTarikhTerima.focus(); 
			return; 
		}
		*/
		if(document.${formName}.txdTarikhCek.value != "" ){
			//return checkDate(document.${formName}.txdTarikhCek);
			//checkDateV01(document.${formName}.txdTarikhCek);
			checkDate(document.${formName}.txdTarikhCek);
		}
		if(document.${formName}.txdTarikhTerima.value != "" ){
			//return checkDate(document.${formName}.txdTarikhTerima);
			//checkDateV01(document.${formName}.txdTarikhTerima);
			checkDate(document.${formName}.txdTarikhTerima);
			
		}	
		if(document.${formName}.txtJumlahBayaran.value == ""){
			alert('Sila Masukkan Jumlah Bayaran.');
	  		document.${formName}.txtJumlahBayaran.focus(); 
			return; 
		}
		/*if(document.${formName}.txtNoResit.value == ""){
			alert('Sila Masukkan No Resit.');
	  		document.${formName}.txtNoResit.focus(); 
			return; 
		}
		if(document.${formName}.txdtarikhResit.value == ""){
			alert('Sila Masukkan Tarikh Resit.');
	  		document.${formName}.txdtarikhResit.focus(); 
			return; 
		}
		if(document.${formName}.txdtarikhResit.value != ''){
			//checkDateV01(document.${formName}.txdtarikhResit);
			checkDate(document.${formName}.txdtarikhResit);
		}
		
		if(document.${formName}.txdtarikhHantarResit.value == ''){
			alert('Sila Masukkan Tarikh Hantar Resit.');
	  		document.${formName}.txdtarikhHantarResit.focus(); 
			return; 
		}
		
		if(document.${formName}.txdtarikhHantarResit.value != ''){
			//checkDateV01(document.${formName}.txdtarikhHantarResit);
			checkDate(document.${formName}.txdtarikhHantarResit);
		}
		*/
		if ( !window.confirm("Adakah Anda Pasti ?") ){
			document.${formName}.actionPajakan.value = "BayaranPajakan";
			return;
		}
	
		document.${formName}.hitButton.value = "saveUpdateBayaran";
		document.${formName}.mode.value = "viewBayaran";
		doAjaxCall${formName}("");
		
	}
	function batalUpdateBayaran(){
		document.${formName}.actionPajakan.value = "BayaranPajakan";
		document.${formName}.mode.value = "viewDraf";
		doAjaxCall${formName}("");
	}
	function paparBayaran(idBayaran){
		document.${formName}.actionPajakan.value = "BayaranPajakan";
		document.${formName}.mode.value = "viewBayaran";
		document.${formName}.idBayaran.value = idBayaran;
		doAjaxCall${formName}("");
	}
	function hapusBayaran(){
		document.${formName}.actionPajakan.value = "BayaranPajakan";
		document.${formName}.hitButton.value = "hapusBayaran";
		document.${formName}.mode.value = "view";
		doAjaxCall${formName}("");
	}
	
	function batalUpdateBayaran(){
		document.${formName}.actionPajakan.value = "BayaranPajakan";
		document.${formName}.mode.value = "viewBayaran";
		doAjaxCall${formName}("");
	}
</script>