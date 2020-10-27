<script>
	//document.${formName}.actionPajakan.value = "paparmjm";
// TAB MEMORANDUM JEMAAH MENTERI

	//javaScriptPajakanMJM.jsp
	function cetakImej(id){
	    var url = "../servlet/ekptg.view.pfd.DisplayBlob?id="+id;
	    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener=document.window;
	    if (hWnd.focus != null) hWnd.focus();

	}
	//javaScriptPajakanMJM.jsp
	function deleteDetailImej(id_,id1){
		if ( !window.confirm('Adakah Anda Pasti?')) return;
			//document.${formName}.command.value = "";
		document.${formName}.actionPajakan.value = 'paparmjm';
		document.${formName}.mode.value = 'view';
		document.${formName}.hitButton.value = 'hapuslampiran';
		//doAjaxCall${formName}("");
		doAjaxCall${formName}('','iDokumen='+id_+'&idLampiran='+id1);

	}
	//javaScriptPajakanMJM.jsp
	function seterusnyaSelesaiMJM(){
		document.${formName}.actionPajakan.value = "paparperjanjian";
		document.${formName}.hitButton.value = "seterusnyamjm";
		document.${formName}.selectedTab.value = "0";
		//document.${formName}.submit();
		//alert('seterusnyaSelesaiMJM:'+document.${formName}.selectedTab.value)
		doAjaxCall${formName}("");

	}
	//javaScriptPajakanMJM.jsp
	function cetakPemajak(idpermohonan) {
	    var url = "../servlet/ekptg.report.htp.NoFailTajukFail?template=MaklumatPemajak&idpermohonan="+idpermohonan;
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();

	}
	function cetakSuratSetujuTerima(idpermohonan) {
	    var url = "../servlet/ekptg.report.htp.NoFailTajukFail?template=HTPajakanSuratTawaran&idpermohonan="+idpermohonan;
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();

	}
	function cetakSuratTawaran(idpermohonan) {
	    var url = "../servlet/ekptg.report.htp.NoFailTajukFail?template=HTPajakanSuratTawaran&idpermohonan="+idpermohonan;
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();

	}
	//javaScriptPajakanMJM.jsp
	function KemaskiniMemo(){
		document.${formName}.actionPajakan.value = "paparmjm";
		document.${formName}.mode.value = "update";
		//document.${formName}.submit();
		doAjaxCall${formName}("");
	}
	//javaScriptPajakanMJM.jsp
	function hapusMJM(){
		document.${formName}.actionPajakan.value = "paparmjm";
		document.${formName}.mode.value = "view";
		document.${formName}.hitButton.value = "hapusmemo";

		if ( !window.confirm("Adakah Anda Pasti ?") ){
			return;
		}
		doAjaxCall${formName}("");

	}
	//javaScriptPajakanMJM.jsp
	function batalMemo(){
		document.${formName}.actionPajakan.value = "paparmjm";
		document.${formName}.mode.value = "view";
		//document.${formName}.submit();
		doAjaxCall${formName}("");
	}
	//javaScriptPajakanMJM.jsp
	function SimpanMemo(){
		if(document.${formName}.subUrusan.value != "7"
			&& document.${formName}.subUrusan.value != "17"
			&& document.${formName}.subUrusan.value != "18"){
			alert('Sila kemaskini "Sub Urusan" terlebih dahulu.');
			return;

		}
		/*if(document.${formName}.txdTHPTP.value == ""){
			alert('Sila masukkan Tarikh Hantar PTP.');
	  		document.${formName}.txdTHPTP.focus();
			return;
		}
		if(document.${formName}.txdTTPTP.value == ""){
			alert('Sila masukkan Tarikh Terima PTP.');
	  		document.${formName}.txdTTPTP.focus();
			return;
		}
		if(document.${formName}.txdTHTUP.value == ""){
			alert('Sila masukkan Tarikh Hantar TUP');
	  		document.${formName}.txdTHTUP.focus();
			return;
		}*/

		if(document.${formName}.txdTMJM.value == ""){
			alert('Sila masukkan Tarikh Mesyuarat Jemaah Menteri.');
	  		document.${formName}.txdTMJM.focus();
			return;
		}
		if(document.${formName}.txdTTK.value == ""){
			alert('Sila masukkan Tarikh Terima Keputusan.');
	  		document.${formName}.txdTTK.focus();
			return;
		}
		if(document.${formName}.txtNoMemorandum.value == ""){
			alert('Sila masukkan No. Memorandum.');
	  		document.${formName}.txtNoMemorandum.focus();
			return;
		}
		if(document.${formName}.txtKeputusan.value == ""){
			alert('Sila masukkan Keputusan.');
	  		document.${formName}.txtKeputusan.focus();
			return;
		}
		if(document.${formName}.txtKeterangan.value == ""){
			alert('Sila masukkan Keterangan Kelulusan.');
	  		document.${formName}.txtKeterangan.focus();
			return;
		}

		var StarikhTerimaPTP = document.getElementById("txdTTPTP").value;
		var StarikhHantarPTP = document.getElementById("txdTHPTP").value;
		var StarikhHantarTUP = document.getElementById("txdTHTUP").value;
		var StarikhMesyuaratJM = document.getElementById("txdTMJM").value;
		var StarikhTerimaKeputusan = document.getElementById("txdTTK").value;

		var today = new Date();
		//for StarikhTerimaPTP
		var STTDate  = parseInt(StarikhTerimaPTP.substring(0,2),10);
	    var STTmon = parseInt(StarikhTerimaPTP.substring(3,5),10)-1;
	    var STTyr  = parseInt(StarikhTerimaPTP.substring(6,10),10);

		//for StarikhHantarPTP
		var STHDate  = parseInt(StarikhHantarPTP.substring(0,2),10);
	    var STHmon = parseInt(StarikhHantarPTP.substring(3,5),10)-1;
	    var STHyr  = parseInt(StarikhHantarPTP.substring(6,10),10);

		//for StarikhHantarTUP
		var STTUPDate  = parseInt(StarikhHantarTUP.substring(0,2),10);
	    var STTUPmon = parseInt(StarikhHantarTUP.substring(3,5),10)-1;
	    var STTUPyr  = parseInt(StarikhHantarTUP.substring(6,10),10);

		//for StarikhMesyuaratJM
		var STMJMDate  = parseInt(StarikhMesyuaratJM.substring(0,2),10);
	    var STMJMmon = parseInt(StarikhMesyuaratJM.substring(3,5),10)-1;
	    var STMJMyr  = parseInt(StarikhMesyuaratJM.substring(6,10),10);

		//for StarikhTerimaKeputusan
		var STTKDate  = parseInt(StarikhTerimaKeputusan.substring(0,2),10);
	    var STTKmon = parseInt(StarikhTerimaKeputusan.substring(3,5),10)-1;
	    var STTKyr  = parseInt(StarikhTerimaKeputusan.substring(6,10),10);

		var tarikhTerimaPTP = new Date(STTyr,STTmon,STTDate);
		var tarikhHantarPTP = new Date(STHyr,STHmon,STHDate);
		var tarikhHantarTUP = new Date(STTUPyr,STTUPmon,STTUPDate);
		var tarikhMesyuaratJM = new Date(STMJMyr,STMJMmon,STMJMDate);
		var tarikhTerimaKeputusan = new Date(STTKyr,STTKmon,STTKDate);

		if(tarikhHantarPTP > today){
			alert('Tarikh Hantar PTP mestilah tidak melebihi dari Hari ini.');
	  		document.${formName}.txdTHPTP.focus();
			return;
		}
		if(tarikhTerimaPTP > today){
			alert('Tarikh Terima PTP mestilah tidak melebihi dari Hari ini.');
	  		document.${formName}.txdTTPTP.focus();
			return;
		}
		if(tarikhHantarTUP > today){
			alert('Tarikh Hantar TUP mestilah tidak melebihi dari Hari ini.');
	  		document.${formName}.txdTHTUP.focus();
			return;
		}
		if(tarikhMesyuaratJM > today){
			alert('Tarikh Mesuarat Jemaah Menteri mestilah tidak melebihi dari Hari ini.');
	  		document.${formName}.txdTMJM.focus();
			return;
		}
		if(tarikhTerimaKeputusan > today){
			alert('Tarikh Terima Keputusan mestilah tidak melebihi dari Hari ini.');
	  		document.${formName}.txdTTK.focus();
			return;
		}

		if ( !window.confirm("Adakah Anda Pasti ?") ){
			document.${formName}.actionPajakan.value = "paparmjm";
			return;
		}

		var crs = create_request_string(document.${formName});
			//document.${formName}.method="post";
			//document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanMJMView&actionPajakan=papar&selectedTab=3&mode=view&hitButton=saveMemo&"+x;
		var params = "&actionPajakan=paparmjm";
		params += "&selectedTab=1";
		params += "&mode=view";
		params += "&hitButton=saveMemo";
			//document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanMJMView"+params+crs;
		//alert(params+crs);
		document.${formName}.enctype="multipart/form-data";
		document.${formName}.encoding="multipart/form-data";
		document.${formName}.action = "?_portal_module=${modul}"+params+"&"+crs;
		document.${formName}.submit();

/*  		document.${formName}.actionPajakan.value = "paparmjm";
		document.${formName}.mode.value = "view";
		document.${formName}.hitButton.value = "saveMemo";
		document.${formName}.method="post";
		document.${formName}.enctype="multipart/form-data";
		document.${formName}.encoding="multipart/form-data";
		document.${formName}.action = "?_portal_module=${modul}&actionPajakan=paparmjm&selectedTab=2&mode=view&hitButton=saveMemo&"+crs;
		document.${formName}.submit();	 */


	}

	function SimpanPerakuan(){

		if(document.${formName}.txtKeputusan.value == ""){
			alert('Sila masukkan Keputusan.');
	  		document.${formName}.txtKeputusan.focus();
			return;
		}
		if(document.${formName}.txtKeterangan.value == ""){
			alert('Sila masukkan Keterangan Kelulusan.');
	  		document.${formName}.txtKeterangan.focus();
			return;
		}

		var StarikhTerimaKeputusan = document.getElementById("txdTPerakuan").value;

		var today = new Date();
		//for StarikhTerimaKeputusan
		var STTKDate  = parseInt(StarikhTerimaKeputusan.substring(0,2),10);
	    var STTKmon = parseInt(StarikhTerimaKeputusan.substring(3,5),10)-1;
	    var STTKyr  = parseInt(StarikhTerimaKeputusan.substring(6,10),10);

		var tarikhTerimaKeputusan = new Date(STTKyr,STTKmon,STTKDate);

		if(tarikhTerimaKeputusan > today){
			alert('Tarikh Terima Keputusan mestilah tidak melebihi dari Hari ini.');
	  		document.${formName}.txdTTK.focus();
			return;
		}

		if ( !window.confirm("Adakah Anda Pasti ?") ){
			document.${formName}.actionPajakan.value = "paparmjm";
			return;
		}

		var crs = create_request_string(document.${formName});
			//document.${formName}.method="post";
			//document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanMJMView&actionPajakan=papar&selectedTab=3&mode=view&hitButton=saveMemo&"+x;
		var params = "&actionPajakan=paparmjm";
		params += "&selectedTab=0";
		params += "&mode=view";
		params += "&hitButton=saveMaklumatJawatankuasa";
		document.${formName}.enctype="multipart/form-data";
		document.${formName}.encoding="multipart/form-data";
		document.${formName}.action = "?_portal_module=${modul}"+params+"&"+crs;
		document.${formName}.submit();


	}

	function batalPerakuan(){
		document.${formName}.actionPajakan.value = "paparmjm";
		document.${formName}.mode.value = "view";
		document.${formName}.selectedTab.value = "0";
		//document.${formName}.submit();
		doAjaxCall${formName}("");
	}

	//javaScriptPajakanMJM.jsp
	function doChangeJumlahLampiran(tabId,j,a) {
		if (j.value < 1) {
			alert("Sila masukkan nombor yang sah");
			j.value = 1;
			return;
		}
		document.${formName}.action.value = "changelampiran";
		document.${formName}.actionPajakan.value = "paparmjm";
		//document.${formName}.mode.value = "view";
		doAjaxCall${formName}("");

		//document.${formName}.submit();
	 	//doAjaxCall${formName}("imej","firstAction=PendaftaranImej&X="+a+"&action=changeLampiran&IDJadualLampiran="+IDJadualLampiran);

	}

	function kemaskiniMJM(){
		//
		document.${formName}.actionPajakan.value = "paparmjm";
		document.${formName}.mode.value = "update";
		//document.${formName}.submit();
		//
		doAjaxCall${formName}("");

	}



	function KemaskiniPerakuan(){
		//
		document.${formName}.actionPajakan.value = "paparmjm";
		document.${formName}.mode.value = "update";
		//document.${formName}.submit();
		//
		doAjaxCall${formName}("");

	}

//TAB DRAF PERJANJIAN I
	function daftarBaruDraf(){
		//
		document.${formName}.actionPajakan.value = "paparderaf";
		document.${formName}.mode.value = "newDraf";
		document.${formName}.submit();
		//
		doAjaxCall${formName}("");

	}
	function SDraf(id){
		var today = new Date();
		var StarikhTerimaDraf = document.getElementById("txdTarikhTerimaDraf").value;
		var StarikhHantarDraf = document.getElementById("txdTarikhHantarDraf").value;
		if(document.${formName}.txdTarikhHantarDraf.value == ""){
			alert('Sila masukkan maklumat Tarikh Hantar .');
  			document.${formName}.txdTarikhHantarDraf.focus();
			return;
		}

/* 		if(document.${formName}.txdTarikhTerimaDraf.value == ""){
			alert('Sila masukkan maklumat tarikh terima');
  			document.${formName}.StarikhTerimaDraf.focus();
			return;
		} */

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

		//
		document.${formName}.actionPajakan.value = "paparderaf";
		document.${formName}.mode.value = "view";
		document.${formName}.hitButton.value = "saveDraf";
		document.${formName}.method="post";
		document.${formName}.enctype="multipart/form-data";
		document.${formName}.encoding="multipart/form-data";
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanMJMView&actionPajakan=papar&selectedTab=2&mode=view&hitButton=saveDraf&"+x;
		document.${formName}.action = "?_portal_module=${modul}&actionPajakan=paparmjm&selectedTab=2&mode=view&hitButton=saveDraf&"+x;
		document.${formName}.submit();

	}

	function batalDraf(){
		//
		document.${formName}.actionPajakan.value = "paparderaf";
		document.${formName}.mode.value = "view";
		//document.${formName}.submit();
		//
		doAjaxCall${formName}("");

	}

	function paparDraf(idDraf){
		//
		document.${formName}.actionPajakan.value = "paparderaf";
		document.${formName}.idDraf.value = idDraf;
		document.${formName}.mode.value = "viewDraf";
		//document.${formName}.submit();
		//
		doAjaxCall${formName}("");

	}

	function downloadPerjanjian(idPermohonan,idDeraf){
		var url = "../servlet/ekptg.view.htp.pajakan.PajakanDisplayBlob?id="+idPermohonan+"&idderaf="+idDeraf;
	    var hWnd = window.open(url,'displayfile','width=800,height=600, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
	    hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();

	}

	function downloadPerakuan(idPermohonan,idDeraf){
		var url = "../servlet/ekptg.view.htp.pajakan.PerakuanDisplayBlob?id="+idPermohonan+"&idderaf="+idDeraf;
	    var hWnd = window.open(url,'displayfile','width=800,height=600, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
	    hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();

	}

	function KemaskiniDraf(){
		document.${formName}.actionPajakan.value = "paparderaf";
		document.${formName}.mode.value = "updateDraf";
		//document.${formName}.submit();
		//
		doAjaxCall${formName}("");

	}

	function HapusDraf(id){
		//
		document.${formName}.actionPajakan.value = "paparderaf";
		document.${formName}.idDraf.value = id;
		document.${formName}.mode.value = "view";
		document.${formName}.hitButton.value = "hapusDraf";
		//document.${formName}.submit();
		//
		doAjaxCall${formName}("");

	}

	function batalUpdateDraf(){
		//
		document.${formName}.actionPajakan.value = "paparderaf";
		document.${formName}.mode.value = "viewDraf";
		//document.${formName}.submit();
		//
		doAjaxCall${formName}("");

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
			document.${formName}.actionPajakan.value = "paparderaf";
			return;
		}

		//document.${formName}.mode.value = "viewDraf";
		//document.${formName}.hitButton.value = "saveUpdateDraf";
		//doAjaxCall${formName}("");
		var x = create_request_string(document.${formName});
		//alert('modul=${modul}');
		document.${formName}.action = "?_portal_module=${modul}&actionPajakan=paparmjm&selectedTab=2&mode=viewDraf&hitButton=saveUpdateDraf&"+x;
		//document.${formName}.method="post";
		document.${formName}.enctype="multipart/form-data";
		document.${formName}.encoding="multipart/form-data";
		document.${formName}.submit();

	}

//TAB ULASAN
	//ULASAN BPHP - javaScriptPajakanMJM.jsp
	function daftarBaruSPHP(){
		document.${formName}.actionPajakan.value = "paparulasan";
		document.${formName}.mode.value = "newSPHP";
		//document.${formName}.submit();
	//
		doAjaxCall${formName}("");
	}

	function KemaskiniUlasanSPHP(){
		document.${formName}.actionPajakan.value = "paparulasan";
		document.${formName}.mode.value = "updateSPHP";
		//document.${formName}.submit();
	//
		doAjaxCall${formName}("");
	}

	function batalUlasanSPHP(){
		document.${formName}.actionPajakan.value = "paparulasan";
		document.${formName}.mode.value = "view";
		//document.${formName}.submit();
	//
		doAjaxCall${formName}("");
	}

	function SimpanUlasanSPHP(){
		/*
		if(document.${formName}.txtNoRujukanSPHP.value == ""){
			alert('Sila masukkan No.Rujukan SPHP.');
	  		document.${formName}.txtNoRujukanSPHP.focus();
			return;
		} */
		if(document.${formName}.txdTarikhHantarSPHP.value == ""){
			alert('Sila masukkan Tarikh Hantar.');
	  		document.${formName}.txdTarikhHantarSPHP.focus();
			return;
		}
		/*if(document.${formName}.txdTarikhTerimaSPHP.value == ""){
			alert('Sila masukkan Tarikh Terima.');
	  		document.${formName}.txdTarikhTerimaSPHP.focus();
			return;
		}*/

		var StarikhHantarSPHP = document.getElementById("txdTarikhHantarSPHP").value;
		var today = new Date();

		//for StarikhHantarSPHP
		var STHDate  = parseInt(StarikhHantarSPHP.substring(0,2),10);
	    var STHmon = parseInt(StarikhHantarSPHP.substring(3,5),10)-1;
	    var STHyr  = parseInt(StarikhHantarSPHP.substring(6,10),10);
		var tarikhHantarSPHP = new Date(STHyr,STHmon,STHDate);

		if(tarikhHantarSPHP > today){
			alert('Tarikh Hantar mestilah tidak melebihi dari Hari ini.');
	  		document.${formName}.txdTarikhHantarSPHP.focus();
			return;
		}

		if(document.${formName}.txdTarikhTerimaSPHP.value == ""){
			var StarikhTerimaSPHP = document.getElementById("txdTarikhTerimaSPHP").value;

		    //for StarikhTerimaSPHP
			var STTDate  = parseInt(StarikhTerimaSPHP.substring(0,2),10);
		    var STTmon = parseInt(StarikhTerimaSPHP.substring(3,5),10)-1;
		    var STTyr  = parseInt(StarikhTerimaSPHP.substring(6,10),10);

			var tarikhTerimaSPHP = new Date(STTyr,STTmon,STTDate);

			if(tarikhTerimaSPHP > today){
				alert('Tarikh Terima mestilah tidak melebihi dari Hari ini.');
		  		document.${formName}.txdTarikhTerimaSPHP.focus();
				return;
			}
		}
		if ( !window.confirm("Adakah Anda Pasti ?") ){
			//document.${formName}.actionPajakan.value = "paparmjm";
			return;
		}

		document.${formName}.actionPajakan.value = 'paparulasan';
		document.${formName}.mode.value = "view";
		document.${formName}.hitButton.value = "saveUlasanSPHP";
		//document.${formName}.submit();
	//
		doAjaxCall${formName}("");
	}

	function SimpanUpdateSPHP(){
		/*
		if(document.${formName}.txtNoRujukanSPHP.value == ""){
			alert('Sila masukkan No.Rujukan SPHP.');
	  		document.${formName}.txtNoRujukanSPHP.focus();
			return;
		}*/
		if(document.${formName}.txdTarikhHantarSPHP.value == ""){
			alert('Sila masukkan Tarikh Hantar.');
	  		document.${formName}.txdTarikhHantarSPHP.focus();
			return;
		}/*
		if(document.${formName}.txdTarikhTerimaSPHP.value == ""){
			alert('Sila masukkan Tarikh Terima.');
	  		document.${formName}.txdTarikhTerimaSPHP.focus();
			return;
		} */

		var StarikhHantarSPHP = document.getElementById("txdTarikhHantarSPHP").value;
		var today = new Date();

		//for StarikhHantarSPHP
		var STHDate  = parseInt(StarikhHantarSPHP.substring(0,2),10);
	    var STHmon = parseInt(StarikhHantarSPHP.substring(3,5),10)-1;
	    var STHyr  = parseInt(StarikhHantarSPHP.substring(6,10),10);

		var tarikhHantarSPHP = new Date(STHyr,STHmon,STHDate);

		if(tarikhHantarSPHP > today){
			alert('Tarikh Hantar mestilah tidak melebihi dari Hari ini.');
	  		document.${formName}.txdTarikhHantarSPHP.focus();
			return;
		}

		if(document.${formName}.txdTarikhTerimaSPHP.value == ""){
			var StarikhTerimaSPHP = document.getElementById("txdTarikhTerimaSPHP").value;

		    //for StarikhTerimaSPHP
			var STTDate  = parseInt(StarikhTerimaSPHP.substring(0,2),10);
		    var STTmon = parseInt(StarikhTerimaSPHP.substring(3,5),10)-1;
		    var STTyr  = parseInt(StarikhTerimaSPHP.substring(6,10),10);

			var tarikhTerimaSPHP = new Date(STTyr,STTmon,STTDate);

			if(tarikhTerimaSPHP > today){
				alert('Tarikh Terima mestilah tidak melebihi dari Hari ini.');
		  		document.${formName}.txdTarikhTerimaSPHP.focus();
				return;
			}
		}

		if ( !window.confirm("Adakah Anda Pasti ?") ){
			//document.${formName}.actionPajakan.value = "paparmjm";
			return;
		}

		document.${formName}.actionPajakan.value = 'paparulasan';
		document.${formName}.mode.value = "viewSPHP";
		document.${formName}.hitButton.value = "saveUpdateSPHP";
		//document.${formName}.submit();
	//
		doAjaxCall${formName}("");
	}

	function batalUpdateSPHP(){
		document.${formName}.mode.value = "viewSPHP";
		//document.${formName}.submit();
	//
		doAjaxCall${formName}("");
	}

	function HapusSPHP(){
		//
		document.${formName}.actionPajakan.value = "paparulasan";
		document.${formName}.hitButton.value = "hapusSPHP";
		document.${formName}.mode.value = "view";
		//document.${formName}.submit();
	//
		doAjaxCall${formName}("");
	}

	function paparSPHP(idUlasanSPHP){
		//
		document.${formName}.actionPajakan.value = "paparulasan";
		document.${formName}.mode.value = "viewSPHP";
		document.${formName}.idUlasanSPHP.value = idUlasanSPHP;
		//document.${formName}.submit();
	//
		doAjaxCall${formName}("");
	}

	// 2018/02/22 - Paparan Surat
	function senaraiDokumenSPHP(id){
		if(document.getElementById(id).style.display=="none"){
			document.getElementById(id).style.display="block";
		}
		else if(document.getElementById(id).style.display=="block"){
			document.getElementById(id).style.display="none";
		}
	}
	// 2018/02/22 -Pilih Pegawai untuk tandatangan surat
	function openSuratPegawai(urli,param,laporan,tem){
		var url = "../x/${securityToken}/ekptg.view.htp.FrmSenaraiSuratHTP?command=pegawai&urli="+urli+"&"+param+tem+"&flagReport=S";
	    var hWnd = window.open(url,'Cetak','status=1,width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
	}
	//ULASAN JPPH - javaScriptPajakanMJM.jsp
	function daftarBaruJPPH(){
		document.${formName}.actionPajakan.value = "paparulasan";
		document.${formName}.mode.value = "newJPPH";
		//document.${formName}.submit();
		doAjaxCall${formName}("");

	}

	function SimpanJPPH(){
		/*
		if(document.${formName}.txtNoRujJPPH.value == ""){
			alert('Sila masukkan No Ruj. JPPH');
	  		document.${formName}.txtNoRujJPPH.focus();
			return;
		}	*/
		if(document.${formName}.txdTarikhHantarJPPH.value == ""){
			alert('Sila masukkan Tarikh Hantar');
	  		document.${formName}.txdTarikhHantarJPPH.focus();
			return;
		} /*
		if(document.${formName}.txdTarikhTerimaJPPH.value == ""){
			alert('Sila masukkan Tarikh Terima');
	  		document.${formName}.txdTarikhTerimaJPPH.focus();
			return;
		}*/
		if(document.${formName}.txtTahunNilaian.value == ""){
			alert('Sila masukkan Tahun Nilaian.');
	  		document.${formName}.txtTahunNilaian.focus();
			return;
		}
		/*
		if(document.${formName}.txtNilaiTanah.value == ""){
			alert('Sila masukkan Nilaian Tanah.');
	  		document.${formName}.txtNilaiTanah.focus();
			return;
		}
		if(document.${formName}.txtSyorBayaran.value == ""){
			alert('Sila masukkan Syor Bayaran.');
	  		document.${formName}.txtSyorBayaran.focus();
			return;
		}
		*/

		var StarikhHantarJPPH = document.getElementById("txdTarikhHantarJPPH").value;
		var today = new Date();

		//for StarikhHantarJPPH
		var STHDate  = parseInt(StarikhHantarJPPH.substring(0,2),10);
	    var STHmon = parseInt(StarikhHantarJPPH.substring(3,5),10)-1;
	    var STHyr  = parseInt(StarikhHantarJPPH.substring(6,10),10);

		var tarikhHantarJPPH = new Date(STHyr,STHmon,STHDate);

		if(tarikhHantarJPPH > today){
			alert('Tarikh Hantar mestilah tidak melebihi dari Hari ini.');
	  		document.${formName}.txdTarikhHantarJPPH.focus();
			return;
		}

		if(document.${formName}.txdTarikhTerimaJPPH.value != ""){
			//for StarikhTerimaJPPH
			var StarikhTerimaJPPH = document.getElementById("txdTarikhTerimaJPPH").value;

			var STTDate  = parseInt(StarikhTerimaJPPH.substring(0,2),10);
	    	var STTmon = parseInt(StarikhTerimaJPPH.substring(3,5),10)-1;
	    	var STTyr  = parseInt(StarikhTerimaJPPH.substring(6,10),10);

			var tarikhTerimaJPPH = new Date(STTyr,STTmon,STTDate);

			if(tarikhTerimaJPPH > today){
				alert('Tarikh Terima mestilah tidak melebihi dari Hari ini.');
		  		document.${formName}.txdTarikhTerimaJPPH.focus();
				return;
			}

		}

		if ( !window.confirm("Adakah Anda Pasti ?") ){
			return;
		}

		//
		document.${formName}.actionPajakan.value = "paparulasan";
		document.${formName}.hitButton.value = "saveJPPH";
		document.${formName}.mode.value = "view";
		//document.${formName}.submit();
		//
		doAjaxCall${formName}("");

	}

	function batalJPPH(){
		//
		document.${formName}.actionPajakan.value = "paparulasan";
		document.${formName}.mode.value = "view";
		//document.${formName}.submit();
		//
		doAjaxCall${formName}("");
	}

	function KemaskiniJPPH(){
		//
		document.${formName}.actionPajakan.value = "paparulasan";
		document.${formName}.mode.value = "updateJPPH";
		//document.${formName}.submit();
		//
		doAjaxCall${formName}("");
	}

	function SimpanUpdateJPPH(){
		/*if(document.${formName}.txtNoRujJPPH.value == ""){
			alert('Sila masukkan No Ruj. JPPH');
	  		document.${formName}.txtNoRujJPPH.focus();
			return;
		}*/
		if(document.${formName}.txdTarikhHantarJPPH.value == ""){
			alert('Sila masukkan Tarikh Hantar');
	  		document.${formName}.txdTarikhHantarJPPH.focus();
			return;
		} /*
		if(document.${formName}.txdTarikhTerimaJPPH.value == ""){
			alert('Sila masukkan Tarikh Terima');
	  		document.${formName}.txdTarikhTerimaJPPH.focus();
			return;
		}*/
		if(document.${formName}.txtTahunNilaian.value == ""){
			alert('Sila masukkan Tahun Nilaian.');
	  		document.${formName}.txtTahunNilaian.focus();
			return;
		}
		/*
		if(document.${formName}.txtNilaiTanah.value == ""){
			alert('Sila masukkan Nilaian Tanah.');
	  		document.${formName}.txtNilaiTanah.focus();
			return;
		}
		if(document.${formName}.txtSyorBayaran.value == ""){
			alert('Sila masukkan Syor Bayaran.');
	  		document.${formName}.txtSyorBayaran.focus();
			return;
		}*/

		var StarikhHantarJPPH = document.getElementById("txdTarikhTerimaJPPH").value;
		var today = new Date();

		//for StarikhHantarJPPH
		var STHDate  = parseInt(StarikhHantarJPPH.substring(0,2),10);
	    var STHmon = parseInt(StarikhHantarJPPH.substring(3,5),10)-1;
	    var STHyr  = parseInt(StarikhHantarJPPH.substring(6,10),10);

		var tarikhHantarJPPH = new Date(STHyr,STHmon,STHDate);

		if(tarikhHantarJPPH > today){
			alert('Tarikh Hantar mestilah tidak melebihi dari Hari ini.');
	  		document.${formName}.txdTarikhHantarJPPH.focus();
			return;
		}

		if(document.${formName}.txdTarikhTerimaJPPH.value != ""){
			//for StarikhTerimaJPPH
			var StarikhTerimaJPPH = document.getElementById("txdTarikhTerimaJPPH").value;

			var STTDate  = parseInt(StarikhTerimaJPPH.substring(0,2),10);
	    	var STTmon = parseInt(StarikhTerimaJPPH.substring(3,5),10)-1;
	    	var STTyr  = parseInt(StarikhTerimaJPPH.substring(6,10),10);

			var tarikhTerimaJPPH = new Date(STTyr,STTmon,STTDate);

			if(tarikhTerimaJPPH > today){
				alert('Tarikh Terima mestilah tidak melebihi dari Hari ini.');
		  		document.${formName}.txdTarikhTerimaJPPH.focus();
				return;
			}

		}

		if ( !window.confirm("Adakah Anda Pasti ?") ){
			return;
		}

		//
		document.${formName}.actionPajakan.value = "paparulasan";
		document.${formName}.hitButton.value = "saveUpdateJPPH";
		document.${formName}.mode.value = "viewJPPH";
		//document.${formName}.submit();
		//
		doAjaxCall${formName}("");

	}

	function batalUpdateJPPH(){
		//
		document.${formName}.actionPajakan.value = "paparulasan";
		document.${formName}.mode.value = "viewJPPH";
		//document.${formName}.submit();
		//
		doAjaxCall${formName}("");
	}

	function paparJPPH(idUlasanTeknikal,idUlasanNilai){
		//
		document.${formName}.actionPajakan.value = "paparulasan";
		document.${formName}.mode.value = "viewJPPH";
		document.${formName}.idUlasanTeknikal.value = idUlasanTeknikal;
		document.${formName}.idUlasanNilai.value = idUlasanNilai;
		//document.${formName}.submit();
		//
		doAjaxCall${formName}("");
	}

	function HapusJPPH(){
		//
		document.${formName}.actionPajakan.value = "paparulasan";
		document.${formName}.hitButton.value = "hapusJPPH";
		document.${formName}.mode.value = "view";
		//document.${formName}.submit();
		//
		doAjaxCall${formName}("");
	}

	//ULASAN KJP - javaScriptPajakanMJM.jsp
	function daftarBaruKJP(){
		document.${formName}.actionPajakan.value = "paparulasan";
		document.${formName}.mode.value = "newKJP";
		//document.${formName}.submit();
		//
		doAjaxCall${formName}("");
	}

	function KemaskiniUlasanKJP(){
		//
		document.${formName}.actionPajakan.value = "paparulasan";
		document.${formName}.mode.value = "updateKJP";
		//document.${formName}.submit();
		//
		doAjaxCall${formName}("");
	}

	function batalUlasanKJP(){
		//
		document.${formName}.actionPajakan.value = "paparulasan";
		document.${formName}.mode.value = "view";
		//document.${formName}.submit();
	//
	doAjaxCall${formName}("");
	}

	//javaScriptPajakanMJM.jsp
	function SimpanUlasanKJP(){
		/*
		if(document.${formName}.txtNoRujukanKJP.value == ""){
			alert('Sila masukkan No.Rujukan KJP.');
	  		document.${formName}.txtNoRujukanKJP.focus();
			return;
		} */
		if(document.${formName}.txdTarikhHantarKJP.value == ""){
			alert('Sila masukkan Tarikh Hantar.');
	  		document.${formName}.txdTarikhHantarKJP.focus();
			return;
		}/*
		if(document.${formName}.txdTarikhTerimaKJP.value == ""){
			alert('Sila masukkan Tarikh Terima.');
	  		document.${formName}.txdTarikhTerimaKJP.focus();
			return;
		}*/

		//var StarikhTerima = document.getElementById("txdTarikhTerimaKJP").value;
		//var StarikhHantar = document.getElementById("txdTarikhHantarKJP").value;
		var StarikhHantar = document.${formName}.txdTarikhHantarKJP.value;

		var today = new Date();

		//for StarikhHantar
		var STHDate  = parseInt(StarikhHantar.substring(0,2),10);
	    var STHmon = parseInt(StarikhHantar.substring(3,5),10)-1;
	    var STHyr  = parseInt(StarikhHantar.substring(6,10),10);

		var tarikhHantar = new Date(STHyr,STHmon,STHDate);

		if(tarikhHantar > today){
			alert('Tarikh Hantar mestilah tidak melebihi dari Hari ini.');
	  		document.${formName}.txdTarikhHantarKJP.focus();
			return;
		}

		//for StarikhTerima
		if(document.${formName}.txdTarikhTerimaKJP.value != ""){
			var StarikhTerima = document.${formName}.txdTarikhHantarKJP.value;

			var STTDate  = parseInt(StarikhTerima.substring(0,2),10);
	    	var STTmon = parseInt(StarikhTerima.substring(3,5),10)-1;
	    	var STTyr  = parseInt(StarikhTerima.substring(6,10),10);

			var tarikhTerima = new Date(STTyr,STTmon,STTDate);
			if(tarikhTerima > today){
				alert('Tarikh Terima mestilah tidak melebihi dari Hari ini.');
		  		document.${formName}.txdTarikhTerimaKJP.focus();
				return;
			}

	    }

		if ( !window.confirm("Adakah Anda Pasti ?") ){
			return;
		}

		//
		document.${formName}.actionPajakan.value = "paparulasan";
		document.${formName}.hitButton.value = "saveUlasanKJP";
		document.${formName}.mode.value = "view";
		//document.${formName}.submit();
		//
		doAjaxCall${formName}("");

	}

	function SimpanUpdateKJP(){
		/*
		if(document.${formName}.txtNoRujukanKJP.value == ""){
			alert('Sila masukkan No.Rujukan KJP.');
	  		document.${formName}.txtNoRujukanKJP.focus();
			return;
		} */

		if(document.${formName}.txdTarikhHantarKJP.value == ""){
			alert('Sila masukkan Tarikh Hantar.');
	  		document.${formName}.txdTarikhHantarKJP.focus();
			return;
		}	/*
		if(document.${formName}.txdTarikhTerimaKJP.value == ""){
			alert('Sila masukkan Tarikh Terima.');
	  		document.${formName}.txdTarikhTerimaKJP.focus();
			return;
		} */
		//alert(1);
		//var StarikhHantar = document.getElementById("txdTarikhHantarKJP").value;
		var StarikhHantar = document.${formName}.txdTarikhHantarKJP.value;
		//alert(2);

		var today = new Date();
		//alert(3);

		//for StarikhHantar
		var STHDate  = parseInt(StarikhHantar.substring(0,2),10);
	    var STHmon = parseInt(StarikhHantar.substring(3,5),10)-1;
	    var STHyr  = parseInt(StarikhHantar.substring(6,10),10);
		//alert(4);

		var tarikhHantar = new Date(STHyr,STHmon,STHDate);

		if(tarikhHantar > today){
			alert('Tarikh Hantar mestilah tidak melebihi dari Hari ini.');
	  		document.${formName}.txdTarikhHantarKJP.focus();
			return;
		}

	    //for StarikhTerima
		if(document.${formName}.txdTarikhTerimaKJP.value != ""){
			//var StarikhTerima = document.getElementById("txdTarikhTerimaKJP").value;
			var StarikhTerima = document.${formName}.txdTarikhHantarKJP.value;

			var STTDate  = parseInt(StarikhTerima.substring(0,2),10);
		    var STTmon = parseInt(StarikhTerima.substring(3,5),10)-1;
	    	var STTyr  = parseInt(StarikhTerima.substring(6,10),10);

			var tarikhTerima = new Date(STTyr,STTmon,STTDate);

			if(tarikhTerima > today){
				alert('Tarikh Terima mestilah tidak melebihi dari Hari ini.');
		  		document.${formName}.txdTarikhTerimaKJP.focus();
				return;
			}
		}

		if ( !window.confirm("Adakah Anda Pasti ?") ){
			return;
		}

		//
		document.${formName}.actionPajakan.value = "paparulasan";
		document.${formName}.hitButton.value = "saveUpdateKJP";
		document.${formName}.mode.value = "viewKJP";
		//document.${formName}.submit();
	//
		doAjaxCall${formName}("");

	}

	function batalUpdateKJP(){
		//
		document.${formName}.actionPajakan.value = "paparulasan";
		document.${formName}.mode.value = "viewKJP";
		//document.${formName}.submit();
	//
		doAjaxCall${formName}("");
	}

	function HapusKJP(idUlasanKJP){
		//
		document.${formName}.actionPajakan.value = "paparulasan";
		document.${formName}.mode.value = "view";
		document.${formName}.hitButton.value = "hapusKJP";
		document.${formName}.idUlasanKJP.value = idUlasanKJP;
		if ( !window.confirm("Adakah Anda Pasti ?") ){
			return;
		}
		//document.${formName}.submit();
		//
		doAjaxCall${formName}("");
	}

	function HapusKJP(){
		//
		document.${formName}.actionPajakan.value = "paparulasan";
		document.${formName}.hitButton.value = "hapusKJP";
		document.${formName}.mode.value = "view";
		if ( !window.confirm("Adakah Anda Pasti ?") ){
			//document.${formName}.actionPajakan.value = "papar";
			return;
		}
		//document.${formName}.submit();
		//
		doAjaxCall${formName}("");

	}

	function paparKJP(idUlasanKJP){
		document.${formName}.actionPajakan.value = "paparulasan";
		document.${formName}.mode.value = "viewKJP";
		document.${formName}.idUlasanKJP.value = idUlasanKJP;
		//document.${formName}.submit();
		//
		doAjaxCall${formName}("");

	}
	//MJM, TAB ULASAN, Sub Tab Ulasan KJP
	function cetakSuratUlasanKJP(idpermohonan){
		var url = "../x/${securityToken}/ekptg.report.htp.utiliti.FrmPopupPilihPegawaiReportView?idpermohonan="+idpermohonan+"&report=SuratKepadaKJPPajakan&selectNoFail=no";
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();

	}

	//MJM, TAB ULASAN, Sub Tab Ulasan JPPH
	function cetakSuratUlasanJPPH(idpermohonan){
		var url = "../x/${securityToken}/ekptg.report.htp.utiliti.FrmPopupPilihPegawaiReportView?idpermohonan="+idpermohonan+"&report=SuratKepadaKJPPH&selectNoFail=no";
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();

	}

	//MJM, TAB MAKLUMAT PEMOHON
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
	    cetakSuratExt(idpermohonan, 'HTPajakanSuratJKPTGNegeri');
	}

	function simpanPemohon(){

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
			return;
		}
		document.${formName}.actionPajakan.value = "paparpemohon";
		document.${formName}.hitButton.value = "savePemohon";
		document.${formName}.mode.value = "view";
		//document.${formName}.submit();
		//
		doAjaxCall${formName}("");

	}

	function batalPemohon(){
		document.${formName}.actionPajakan.value = "paparpemohon";
		document.${formName}.mode.value = "view";
		//document.${formName}.submit();
		//
		doAjaxCall${formName}("");
	}
//kemaskini pemohon langkah3
	function kemaskiniPemohon(){
		document.${formName}.actionPajakan.value = "paparpemohon";
		document.${formName}.mode.value = "update";
		//document.${formName}.submit();
		//
		doAjaxCall${formName}("");

	}
	//LAIN2
	function doChangeNegeri(){
		doAjaxCall${formName}('doChangeNegeri');
	}

	//Skrin Pemohon. Butang [Seterusnya] sini yg betul
	function seterusnyaPemohon(){
		if ( !window.confirm("Adakah Anda Pasti ?") ){
			return;
		}
		document.${formName}.actionPajakan.value = "paparulasan";
		document.${formName}.hitButton.value = "seterusnyadaftar";
		document.${formName}.selectedTab.value = 0;
		//document.${formName}.submit();
		doAjaxCall${formName}("");

	}
</script>
