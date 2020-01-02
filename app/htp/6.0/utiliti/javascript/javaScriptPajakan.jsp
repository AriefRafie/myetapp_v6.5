<script>
	//javaScriptPajakan.jsp
	//Pagging
	/*function langkah1(){
		document.${formName}.actionPajakan.value = "papar";
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanSenaraiFailView";
		document.${formName}.mode.value = "view";
		document.${formName}.submit();
		
	}
	function langkah2(permohonan,idFail){
		url = "../servlet/ekptg.view.htp.FrmPajakanServlet?command=papar&idFail="+idFail;
		actionName = "setSessionIdFail";
		target = "setSessionIdFail_result";
		doAjaxUpdater(document.${formName}, url, target, actionName);
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanPendaftaranView&idPermohonan="+permohonan+"&actionPajakan=papar";
		document.${formName}.actionPajakan.value = "papardaftar";
		document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanPendaftaranView&idPermohonan="+permohonan+"&actionPajakan=papar";
		document.${formName}.mode.value = "view";
		document.${formName}.submit();
	}
	function langkah3(permohonan,idFail){
		url = "../servlet/ekptg.view.htp.FrmPajakanServlet?command=papar&idFail="+idFail;
		actionName = "setSessionIdFail";
		target = "setSessionIdFail_result";
		doAjaxUpdater(document.${formName}, url, target, actionName);
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanMJMView&idPermohonan="+permohonan+"&actionPajakan=papar";
		document.${formName}.actionPajakan.value = "paparmjm";
		document.${formName}.mode.value = "view";
		document.${formName}.submit();
		
	}
	function langkah4(permohonan,idFail){
		url = "../servlet/ekptg.view.htp.FrmPajakanServlet?command=papar&idFail="+idFail;
		actionName = "setSessionIdFail";
		target = "setSessionIdFail_result";
		doAjaxUpdater(document.${formName}, url, target, actionName);
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanPerjanjianView&idPermohonan="+permohonan+"&actionPajakan=papar";
		document.${formName}.actionPajakan.value = "paparperjanjian";
		document.${formName}.mode.value = "view";
		document.${formName}.submit();
	}
	function langkah5(permohonan,idFail){
		url = "../servlet/ekptg.view.htp.FrmPajakanServlet?command=papar&idFail="+idFail;
		actionName = "setSessionIdFail";
		target = "setSessionIdFail_result";
		doAjaxUpdater(document.${formName}, url, target, actionName);
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanBayaranView&idPermohonan="+permohonan+"&actionPajakan=BayaranPajakan";
		document.${formName}.actionPajakan.value = "BayaranPajakan";
		document.${formName}.mode.value = "view";
		document.${formName}.submit();
		
	} 
	
	//index 2017/05/18, Maklumat Tanah
	function refreshFromPilihTanah() {
		document.${formName}.actionPajakan.value = "papardaftar";
		document.${formName}.submit();
		refreshAgain()
	}
	function refreshAgain() {
		document.${formName}.submit();
	}*/

	//javaScriptPajakan.jsp
	/** Fungsi pilih sub tab [Tab Ulasan]
	Mukasurat 3 */
	function doChangeTabLower(tabId) {
		document.${formName}.selectedTabLower.value = tabId;
		document.${formName}.mode.value = "view";
		//document.${formName}.submit();
		//
		doAjaxCall${formName}("");
		
	}
	//javaScriptPajakan.jsp
	/** Fungsi pilih tab
		Mukasurat 3 */
	function doChangeTab(tabId) {
		//alert("doChangeTab:actionPajakan="+document.${formName}.actionPajakan.value);
		if(document.${formName}.idStatus.value == '6' && ('$!flagFail' == 'N' || '$!flagFail' == 'H')){
			alert('Sila Hantar Pengesahan/ Sahkan permohonan terlebih dahulu.');
			document.${formName}.selectedTab.value = 0;	
		}else
			document.${formName}.selectedTab.value = tabId;
		
		document.${formName}.mode.value = "view";
		//document.${formName}.submit();
		doAjaxCall${formName}("");
		
	}
	//Pendaftaran
	/*function doChangeTab(tabId) {
		document.${formName}.selectedTab.value = tabId;
		document.${formName}.actionPajakan.value = "papardaftar";
		document.${formName}.mode.value = "view";
		doAjaxCall${formName}("");
		
	}*/
	/** 2017/05/18
	//Skrin Senarai hakmilik. Butang [seterusnya]
	function seterusnya(){
		if ( !window.confirm("Adakah Anda Pasti ?") ){
			return;
		}		
		document.${formName}.actionPajakan.value = "paparmjm";
		//document.${formName}.hitButton.value = "seterusnya";
		document.${formName}.hitButton.value = "seterusnyadaftar";
		document.${formName}.selectedTab.value = 0;
		doAjaxCall${formName}("");
		
	}*/
//NO.3 MAKLUMAT MJM
		//PEMOHON
		//ULASAN
	//DRAF PERJANJIAN
	// javaScriptPajakan.jsp
	function paparDraf(idDraf){
		document.${formName}.idDraf.value = idDraf;
		document.${formName}.mode.value = "viewDraf";
		//document.${formName}.submit();	
		//
		doAjaxCall${formName}("");
		
	}
	function KemaskiniDraf(){
		document.${formName}.actionPajakan.value = "paparmjm";
		document.${formName}.mode.value = "updateDraf";
		//document.${formName}.submit();	
		//
		doAjaxCall${formName}("");		
	}	
			//Skrin Senarai, Button [Daftar Baru]
	function daftarBaruDraf(){
		//document.${formName}.actionPajakan.value = "papar";
		//document.${formName}.actionPajakan.value = "paparperjanjian";
		document.${formName}.mode.value = "newDraf";
		doAjaxCall${formName}("");
		
	}		
	
	// javaScriptPajakan.jsp
	function SDraf_(id){	
		var today = new Date();
		var StarikhTerimaDraf = document.getElementById("txdTarikhTerimaDraf").value;
		var StarikhHantarDraf = document.getElementById("txdTarikhHantarDraf").value;
		if(document.${formName}.txdTarikhHantarDraf.value == ""){
			alert('Sila masukkan maklumat Tarikh Hantar .');
  			document.${formName}.txdTarikhHantarDraf.focus(); 
			return; 
		}

		if(document.${formName}.txdTarikhTerimaDraf.value == ""){
			alert('Sila masukkan maklumat tarikh terima');
  			document.${formName}.StarikhTerimaDraf.focus(); 
			return; 
		}	
	
		//for StarikhTerimaDraf
		var STTDate  = parseInt(StarikhTerimaDraf.substring(0,2),10);
	    var STTmon = parseInt(StarikhTerimaDraf.substring(3,5),10)-1;
	    var STTyr  = parseInt(StarikhTerimaDraf.substring(6,10),10); 
		
		//for StarikhHantarDraf
		var STHDate  = parseInt(StarikhHantarDraf.substring(0,2),10);
	    var STHmon = parseInt(StarikhHantarDraf.substring(3,5),10)-1;
	    var STHyr  = parseInt(StarikhHantarDraf.substring(6,10),10); 
		
		var tarikhTerimaDraf = new Date(STTyr,STTmon,STTDate);
		var tarikhHantarDraf = new Date(STHyr,STHmon,STHDate);

		if(tarikhHantarDraf > today){
			alert('Tarikh Hantar mestilah tidak melebihi dari Hari ini.');
	  		document.${formName}.txdTarikhHantarDraf.focus(); 
			return; 
		}
	
		if(tarikhTerimaDraf > today){
			alert('Tarikh Terima mestilah tidak melebihi dari Hari ini.');
	  		document.${formName}.StarikhTerimaDraf.focus(); 
			return; 
		}
		if ( !window.confirm("Adakah Anda Pasti?") ) return;	
		
		var x = create_request_string(document.${formName});
		
		document.${formName}.mode.value = "view";
		//document.${formName}.actionPajakan.value = "papar";
		document.${formName}.actionPajakan.value = "paparmjm";
		document.${formName}.hitButton.value = "saveDraf";
		document.${formName}.method="post";
		document.${formName}.enctype="multipart/form-data";
		document.${formName}.encoding="multipart/form-data";
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanMJMView&actionPajakan=papar&selectedTab=2&mode=view&hitButton=saveDraf&"+x;
		document.${formName}.submit();	
	
	}
	function SimpanUpdateDraf(){		
		if(document.${formName}.txdTarikhHantarDraf.value == ""){
			alert('Sila masukkan Tarikh Hantar.');
	  		document.${formName}.txdTarikhHantarDraf.focus(); 
			return; 
		}
/* 		if(document.${formName}.txdTarikhTerimaDraf.value == ""){
			alert('Sila masukkan Tarikh Terima.');
	  		document.${formName}.txdTarikhTerimaDraf.focus(); 
			return; 
		} */
		
		var StarikhTerimaDraf = document.getElementById("txdTarikhTerimaDraf").value;
		var StarikhHantarDraf = document.getElementById("txdTarikhHantarDraf").value;
		
		var today = new Date();		
		//for StarikhTerimaDraf
		var STTDate  = parseInt(StarikhTerimaDraf.substring(0,2),10);
	    var STTmon = parseInt(StarikhTerimaDraf.substring(3,5),10)-1;
	    var STTyr  = parseInt(StarikhTerimaDraf.substring(6,10),10); 
		
		//for StarikhHantarDraf
		var STHDate  = parseInt(StarikhHantarDraf.substring(0,2),10);
	    var STHmon = parseInt(StarikhHantarDraf.substring(3,5),10)-1;
	    var STHyr  = parseInt(StarikhHantarDraf.substring(6,10),10); 
		
		var tarikhTerimaDraf = new Date(STTyr,STTmon,STTDate);
		var tarikhHantarDraf = new Date(STHyr,STHmon,STHDate);
	
		if(tarikhHantarDraf > today){
			alert('Tarikh Hantar mestilah tidak melebihi dari Hari ini.');
	  		document.${formName}.txdTarikhHantarDraf.focus(); 
			return; 
		}
		
		if(tarikhTerimaDraf > today){
			alert('Tarikh Terima mestilah tidak melebihi dari Hari ini.');
	  		document.${formName}.txdTarikhTerimaDraf.focus(); 
			return; 
		}
	
		if ( !window.confirm("Adakah Anda Pasti ?") ){
			//document.${formName}.actionPajakan.value = "papar";
			return;
		}
		
		//document.${formName}.mode.value = "viewDraf";
		//document.${formName}.hitButton.value = "saveUpdateDraf";
		//doAjaxCall${formName}("");
		var x = create_request_string(document.${formName});
		alert('modul=${modul}');
		document.${formName}.action = "?_portal_module=${modul}&actionPajakan=paparmjm&selectedTab=2&mode=viewDraf&hitButton=saveUpdateDraf&"+x;
		//document.${formName}.method="post";
		document.${formName}.enctype="multipart/form-data";
		document.${formName}.encoding="multipart/form-data";
		document.${formName}.submit();	
		
	}

		// MJM
	
	//NO.4 PERJANJIAN

		// Skrin Maklumat Perjanjian, Button [Simpan]
	function simpanDraf(){
		if(document.${formName}.txdTarikhHantarDraf.value == ""){
			alert('Sila masukkan Tarikh Hantar Draf.');
	  		document.${formName}.txdTarikhHantarDraf.focus(); 
			return; 
		}
		/*	if(document.${formName}.txdTarikhTerimaDraf.value == ""){
			alert('Sila masukkan Tarikh Terima Draf.');
	  		document.${formName}.txdTarikhTerimaDraf.focus(); 
			return; 
		}
		if(document.${formName}.txdTarikhHantarPKP.value == ""){
			alert('Sila masukkan Tarikh Hantar PKP');
	  		document.${formName}.txdTarikhHantarPKP.focus(); 
			return; 
		}
		if(document.${formName}.txdTarikhTerimaPKP.value == ""){
			alert('Sila masukkan Tarikh Terima PKP.');
	  		document.${formName}.txdTarikhTerimaPKP.focus(); 
			return; 
		}*/

		var StarikhHantarDraf = document.getElementById("txdTarikhHantarDraf").value;
		var StarikhTerimaDraf = document.getElementById("txdTarikhTerimaDraf").value;
		var StarikhHantarPKP = document.getElementById("txdTarikhHantarPKP").value;
		var StarikhTerimaPKP = document.getElementById("txdTarikhTerimaPKP").value;
		
		var today = new Date();
		
		//for StarikhHantarDraf
		var STHDDate  = parseInt(StarikhHantarDraf.substring(0,2),10);
	    var STHDmon = parseInt(StarikhHantarDraf.substring(3,5),10)-1;
	    var STHDyr  = parseInt(StarikhHantarDraf.substring(6,10),10); 
		
		//for StarikhTerimaDraf
		var STTDDate  = parseInt(StarikhTerimaDraf.substring(0,2),10);
	    var STTDmon = parseInt(StarikhTerimaDraf.substring(3,5),10)-1;
	    var STTDyr  = parseInt(StarikhTerimaDraf.substring(6,10),10); 
		
		//for StarikhHantarPKP
		var STHPKPDate  = parseInt(StarikhHantarPKP.substring(0,2),10);
	    var STHPKPmon = parseInt(StarikhHantarPKP.substring(3,5),10)-1;
	    var STHPKPyr  = parseInt(StarikhHantarPKP.substring(6,10),10); 
		
		//for StarikhTerimaPKP
		var STTPKPDate  = parseInt(StarikhTerimaPKP.substring(0,2),10);
	    var STTPKPmon = parseInt(StarikhTerimaPKP.substring(3,5),10)-1;
	    var STTPKPyr  = parseInt(StarikhTerimaPKP.substring(6,10),10); 
	
		var tarikhHantarDraf = new Date(STHDyr,STHDmon,STHDDate);
		var tarikhTerimaDraf = new Date(STTDyr,STTDmon,STTDDate);
		var tarikhHantarPKP = new Date(STHPKPyr,STHPKPmon,STHPKPDate);
		var tarikhTerimaPKP = new Date(STTPKPyr,STTPKPmon,STTPKPDate);
	
		if(tarikhHantarDraf > today){
			alert('Tarikh Hantar Draf mestilah tidak melebihi dari Hari ini.');
	  		document.${formName}.txdTarikhHantarDraf.focus(); 
			return; 
		}
		
		if(tarikhTerimaDraf > today){
			alert('Tarikh Terima Draf mestilah tidak melebihi dari Hari ini.');
	  		document.${formName}.txdTarikhTerimaDraf.focus(); 
			return; 
		}
		if(tarikhHantarPKP > today){
			alert('Tarikh Hantar PKP mestilah tidak melebihi dari Hari ini.');
	  		document.${formName}.txdTarikhHantarPKP.focus(); 
			return; 
		}
		if(tarikhTerimaPKP > today){
			alert('Tarikh Terima PKP mestilah tidak melebihi dari Hari ini.');
	  		document.${formName}.txdTarikhTerimaPKP.focus(); 
			return; 
		}
	
		if ( !window.confirm("Adakah Anda Pasti ?") ){
			//document.${formName}.actionPajakan.value = "papar";
			//document.${formName}.actionPajakan.value = "paparperjanjian";
			return;
		}
	
		document.${formName}.mode.value = "viewDraf";
		//document.${formName}.hitButton.value = "saveDraf";
		document.${formName}.hitButton.value = "savedrafper";
		doAjaxCall${formName}("");
		
	}
	
	//NO.5 BAYARAN
	
	//NO.6 PENAMATAN
	
	//NO.7 PEMANTAUAN
</script>
