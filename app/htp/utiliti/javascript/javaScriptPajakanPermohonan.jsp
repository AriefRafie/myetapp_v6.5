
<script>
//javaScriptPajakanPermohonan
//document.${formName}.actionPajakan.value = "papardaftar";

	//Tab Maklumat Permohonan
	//by Rosli 2012/08/14
	function tambahFailLain(id,command){
		var url = "../x/${securityToken}/ekptg.view.htp.FrmFailLainKemaskini?idFailLama="+id+"&command="+command;
	    var hWnd = window.open(url,'printuser','width=500,height=200, resizable=yes,scrollbars=yes,copyhistory=yes,location=no,directories=no,status=yes,toolbar=no,menubar=no');
	  	if ((document.window != null) && (!hWnd.opener))
	   		hWnd.opener = document.window;
	  	if (hWnd.focus != null) hWnd.focus();
	}
	function tambahFailLain(id,command,con){
		var url = "../x/${securityToken}/ekptg.view.htp.FrmFailLainKemaskini?idFailLama="+id+"&command="+command+"&con="+con;
	    var hWnd = window.open(url,'printuser','width=500,height=200, resizable=yes,scrollbars=yes,copyhistory=yes,location=no,directories=no,status=yes,toolbar=no,menubar=no');
	  	if ((document.window != null) && (!hWnd.opener))
	   		hWnd.opener = document.window;
	  	if (hWnd.focus != null) hWnd.focus();
	}

	function paparPautan(idFail){
		url = "../servlet/ekptg.view.htp.FrmPenswastaan2Servlet?command=papar&idFail="+idFail;
		actionName = "setSessionIdFail";
		target = "setSessionIdFail_result";
		doAjaxUpdater(document.${formName}, url, target, actionName);

		document.${formName}.action = "$EkptgUtil.getTabID('Penswastaan',$portal_role)?_portal_module=ekptg.view.htp.FrmPenswastaan2SyarikatView&actionPenswastaan=papar&mode=view&idFail="+idFail;
		//document.${formName}.submit();
		doAjaxCall${formName}("");

	}

	function kemaskiniFail(){
		document.${formName}.actionPajakan.value = "papardaftar";
		document.${formName}.mode.value = "update";
		//document.${formName}.submit();
		doAjaxCall${formName}("");
	}

	function batalKemaskiniFail(){
		document.${formName}.actionPajakan.value = "papardaftar";
		document.${formName}.mode.value = "view";
		//document.${formName}.submit();
		doAjaxCall${formName}("");

	}

	function simpanKemaskiniFail(){

	/*if(document.${formName}.txtNoFailKJP.value == ""){
		alert('Sila masukkan No Fail KJP.');
  		document.${formName}.txtNoFailKJP.focus();
		return;
	}
	if(document.${formName}.tarikhSuratKJP.value == ""){
		alert('Sila masukkan Tarikh Surat KJP.');
  		document.${formName}.tarikhSuratKJP.focus();
		return;
	}
	if(document.${formName}.txtNoFailLain.value == ""){
		alert('Sila masukkan No. Fail Lain');
  		document.${formName}.txtNoFailLain.focus();
		return;
	}
	if(document.${formName}.tarikhAgihan.value == ""){
		alert('Sila masukkan Tarikh Agihan.');
  		document.${formName}.tarikhAgihan.focus();
		return;
	}*/

		if(document.${formName}.tarikhSuratPemohon.value == ""){
			alert('Sila masukkan Tarikh Surat Permohonan.');
	  		document.${formName}.tarikhSuratPemohon.focus();
			return;
		}
		if(document.${formName}.txtTajuk.value == ""){
			alert('Sila masukkan Tajuk.');
	  		document.${formName}.txtTajuk.focus();
			return;
		}
		if ( !window.confirm("Adakah Anda Pasti ?") ){
			//document.${formName}.actionPajakan.value = "papar";
			//document.${formName}.actionPajakan.value = "papardaftar";
			return;
		}

		document.${formName}.actionPajakan.value = "papardaftar";
		document.${formName}.hitButton.value = "saveUpdateFail";
		document.${formName}.mode.value = "view";
		//document.${formName}.submit();
		doAjaxCall${formName}("");

	}

// 19/08/2010 -
	function senaraiDokumenSurat(id){
		if(document.getElementById(id).style.display=="none"){
			document.getElementById(id).style.display="block";
		}
		else if(document.getElementById(id).style.display=="block"){
			document.getElementById(id).style.display="none";
		}
	}

//19/08/2010 bertujuan mencetak doket atau Fail
// Dibuat oleh  : Rosli
// Dimodifikasi oleh :
	function cetakFailDoket(id,temp,urlserv) {
		var param ="";
	    var url = "../servlet/"+urlserv+"?"+id+temp;
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
	}
	/**Penambahbaikan. Syaz. 01122014. Untuk function pengesahan (2 layer)
	 * N = BARU SAVE
	 * H = HANTAR
	 * S = SAH (TELAH DISAHKAN)
	 * note : Hanya fail selepas penambahbaikan sahaja akan ada flag ini. Yang lama dikira telah disahkan dan boleh proceed seperti biasa.
	 */
	 function doHantarPengesahan(){
		 if ( !window.confirm("Adakah Anda Pasti ?") ){
			return;

		 }
		document.${formName}.actionPajakan.value = "paparpemohon";
		document.${formName}.hitButton.value = "hantarpengesahandaftar";
		document.${formName}.mode.value = "view";
		//document.${formName}.submit();
		doAjaxCall${formName}("");

	 }

	 function doHantarPenyemak(){
		 if ( !window.confirm("Adakah Anda Pasti ?") ){
			return;

		 }
		document.${formName}.actionPajakan.value = "paparpemohon";
		document.${formName}.hitButton.value = "hantarpenyemakdaftar";
		document.${formName}.mode.value = "view";
		//document.${formName}.submit();
		doAjaxCall${formName}("");

	 }

	 function doSahkan() {
		if ( !window.confirm("Adakah Anda Pasti ?") ){
				return;

		}
		document.${formName}.actionPajakan.value = "paparpemohon";
		document.${formName}.hitButton.value = "sahkanpermohonandaftar";
		document.${formName}.mode.value = "view";
		//document.${formName}.submit();
		doAjaxCall${formName}("");

	}

	//Tab Maklumat Tanah
	function pilihTanah(idPermohonan) {
		var url = "../x/${securityToken}/ekptg.view.htp.FrmPajakanPopupSenaraiTanahView?idPermohonan="+idPermohonan;
	    var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
	       hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();

	}

	function kemaskiniTanah(idPermohonan,idHakmilikUrusan,lot) {
		var params="idPermohonan="+idPermohonan+"&idhakmilikurusan="+idHakmilikUrusan+"&actionPopup=kemaskini&lot="+lot;
		var url = "../x/${securityToken}/ekptg.view.htp.FrmPajakanPopupSenaraiTanahView?"+params;
	    var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
	       hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();

	}

	function doDeleteHakmilik(idHakmilikUrusan){
		if ( !window.confirm("Adakah Anda Pasti ?") ){
			//document.${formName}.actionPajakan.value = "papar";
			return;

		}
		//
		document.${formName}.actionPajakan.value = "papardaftar";
		document.${formName}.hitButton.value = "deletehakmilik";
		document.${formName}.idHakmilikUrusan.value = idHakmilikUrusan;
		//document.${formName}.submit();
		doAjaxCall${formName}("");

	}
	//Skrin Senarai hakmilik. Butang [Seterusnya] sini yg betul
	function seterusnya(){
		if ( !window.confirm("Adakah Anda Pasti ?") ){
			return;
		}
		document.${formName}.actionPajakan.value = "paparpemohon";
		document.${formName}.hitButton.value = "seterusnyadaftar";
		document.${formName}.selectedTab.value = 0;
		//document.${formName}.submit();
		doAjaxCall${formName}("");

	}

	function refreshFromPilihTanah() {
		document.${formName}.actionPajakan.value = "papardaftar";
		document.${formName}.mode.value = "view";
		//document.${formName}.submit();
		//doAjaxCall${formName}("");
		refreshAgain()

	}

	function refreshAgain() {
		//document.${formName}.submit();
		doAjaxCall${formName}("");
	}

</script>
