<script>
	//javaScriptPajakanPerjanjian.jsp
	//document.${formName}.actionPajakan.value = "paparperjanjian";

	function seterusnyaSelesaiPerjanjian(){
		document.${formName}.actionPajakan.value = "BayaranPajakan";
		document.${formName}.hitButton.value = "seterusnyaperjanjian";
		doAjaxCall${formName}("");

	}
	//MOA javaScriptPerjanjian
	function kemaskiniMOA(){
		//
		document.${formName}.actionPajakan.value = "paparperjanjian";
		document.${formName}.mode.value = "update";
		doAjaxCall${formName}("");
	}
	function batalMOA(){
		//
		document.${formName}.actionPajakan.value = "paparperjanjian";
		document.${formName}.mode.value = "view";
		doAjaxCall${formName}("");
	}
	function simpanMOA(){
		/*
		if(document.${formName}.txdTarikhTerimaMOA.value == ""){
			alert('Sila masukkan Tarikh Terima MOA.');
	  		document.${formName}.txdTarikhTerimaMOA.focus();
			return;
		}
		if(document.${formName}.txdTarikhTandatanganPTP.value == ""){
			alert('Sila masukkan Tarikh Tandatangan PTP.');
	  		document.${formName}.txdTarikhTandatanganPTP.focus();
			return;
		}
		if(document.${formName}.txdTarikhKembaliMOA.value == ""){
			alert('Sila masukkan Tarikh MOA Dikembalikan');
	  		document.${formName}.txdTarikhKembaliMOA.focus();
			return;
		}
		if(document.${formName}.txdTarikhDaftarMOA.value == ""){
			alert('Sila masukkan Tarikh MOA didaftarkan .');
	  		document.${formName}.txdTarikhDaftarMOA.focus();
			return;
		}
		if(document.${formName}.txdTarikhBayaranMOA.value == ""){
			alert('Sila masukkan Tarikh Bayaran MOA.');
	  		document.${formName}.txdTarikhBayaranMOA.focus();
			return;
		}
		if(document.${formName}.txtNoPerjanjianMOA.value == ""){
			alert('Sila masukkan No Perjanjian MOA.');
	  		document.${formName}.txtNoPerjanjianMOA.focus();
			return;
		}
		*/

		if ( !window.confirm("Adakah Anda Pasti ?") ){
			//
			document.${formName}.actionPajakan.value = "paparperjanjian";
			return;
		}

		document.${formName}.hitButton.value = "saveMOA";
		document.${formName}.mode.value = "view";
		doAjaxCall${formName}("");

	}
	// MULA 15A
	function kemaskini15A(){
		//
		document.${formName}.actionPajakan.value = "paparperjanjian";
		document.${formName}.mode.value = "update";
		doAjaxCall${formName}("");

	}
	function batal15A(){
		//
		document.${formName}.actionPajakan.value = "paparperjanjian";
		document.${formName}.mode.value = "view";
		doAjaxCall${formName}("");

	}
	function simpan15A(){
		/*
		if(document.${formName}.txdTarikhTerimaPemohon.value == ""){
			alert('Sila masukkan Tarikh Terima Pemohon.');
	  		document.${formName}.txdTarikhTerimaPemohon.focus();
			return;
		}
		if(document.${formName}.txdTarikhTandatanganPTP15A.value == ""){
			alert('Sila masukkan Tarikh Tandatangan PTP.');
	  		document.${formName}.txdTarikhTandatanganPTP15A.focus();
			return;
		}
		if(document.${formName}.txdTarikhHantarPemohon.value == ""){
			alert('Sila masukkan Tarikh Hantar Pemohon');
	  		document.${formName}.txdTarikhHantarPemohon.focus();
			return;
		}
		if(document.${formName}.txdTarikhDaftarPajakan.value == ""){
			alert('Sila masukkan Tarikh Daftar Pajakan .');
	  		document.${formName}.txdTarikhDaftarPajakan.focus();
			return;
		}
		if(document.${formName}.txdtarikhTerimaHakmilik.value == ""){
			alert('Sila masukkan Tarikh Terima Hakmilik.');
	  		document.${formName}.txdtarikhTerimaHakmilik.focus();
			return;
		}
		if(document.${formName}.txdTarikhKemaskiniHakmilik.value == ""){
			alert('Sila masukkan Tarikh Kemaskini Hakmilik.');
	  		document.${formName}.txdTarikhKemaskiniHakmilik.focus();
			return;
		}
		*/

		if ( !window.confirm("Adakah Anda Pasti ?") ){
			//
			document.${formName}.actionPajakan.value = "paparperjanjian";
			return;
		}

		document.${formName}.hitButton.value = "save15A";
		document.${formName}.mode.value = "view";
		doAjaxCall${formName}("");

	}
	//MULA MAKLUMAT PAJAKAN
	function daftarBaruPajakan(){
		//
		document.${formName}.actionPajakan.value = "paparpajakan";
		document.${formName}.mode.value = "newPajakan";
		doAjaxCall${formName}("");

	}
	function simpanPajakan(){
		if(document.${formName}.txdTarikhTandatangan.value == ""){
			alert('Sila masukkan Tarikh Tandatangan.');
	  		document.${formName}.txdTarikhTandatangan.focus();
			return;
		}
	/*if(document.${formName}.txdTarikhMulaPajakan.value == ""){
		alert('Sila masukkan Tarikh Mula Pajakan.');
  		document.${formName}.txdTarikhMulaPajakan.focus();
		return;
	}
	if(document.${formName}.txdTarikhTamatPajakan.value == ""){
		alert('Sila masukkan Tarikh Tamat Pajakan');
  		document.${formName}.txdTarikhTamatPajakan.focus();
		return;
	}
	if(document.${formName}.txtTempoh.value == ""){
		alert('Sila masukkan Tempoh Pajakan.');
  		document.${formName}.txtTempoh.focus();
		return;
	}
	*/
		if(document.${formName}.socNotifikasiPeringatan.value != "" && document.${formName}.txdTarikhTamatPajakan.value == ""){
			alert('Sila pastikan Tarikh Tamat Pajakan dimasukkan sebelum memilih Papar Peringatan.');
	  		document.${formName}.txdTarikhTandatangan.focus();
			return;
		}
		if ( !window.confirm("Adakah Anda Pasti ?") ){
			//
			document.${formName}.actionPajakan.value = "paparpajakan";
			return;

		}

		document.${formName}.hitButton.value = "savePajakan";
		document.${formName}.mode.value = "view";
		doAjaxCall${formName}("");

	}
	function batalPajakan(){
		//
		document.${formName}.actionPajakan.value = "paparpajakan";
		document.${formName}.mode.value = "view";
		doAjaxCall${formName}("");

	}
	/* 2017/05/12*/
	function kemaskiniPajakan(){
		//
		document.${formName}.actionPajakan.value = "paparpajakan";
		document.${formName}.mode.value = "updatePajakan";
		doAjaxCall${formName}("");

	}

	function simpanUpdatePajakan(){
		if(document.${formName}.txdTarikhTandatangan.value == ""){
			alert('Sila masukkan Tarikh Tandatangan.');
	  		document.${formName}.txdTarikhTandatangan.focus();
			return;
		}
	/*	if(document.${formName}.txdTarikhMulaPajakan.value == ""){
			alert('Sila masukkan Tarikh Mula Pajakan.');
	  		document.${formName}.txdTarikhMulaPajakan.focus();
			return;
		}
		if(document.${formName}.txdTarikhTamatPajakan.value == ""){
			alert('Sila masukkan Tarikh Tamat Pajakan');
	  		document.${formName}.txdTarikhTamatPajakan.focus();
			return;
		}
		if(document.${formName}.txtTempoh.value == ""){
			alert('Sila masukkan Tempoh Pajakan.');
	  		document.${formName}.txtTempoh.focus();
			return;
		}
		*/
		if(document.${formName}.socNotifikasiPeringatan.value != "" && document.${formName}.txdTarikhTamatPajakan.value == ""){
			alert('Sila pastikan Tarikh Tamat Pajakan dimasukkan sebelum memilih Papar Peringatan.');
	  		document.${formName}.txdTarikhTandatangan.focus();
			return;
		}
		if ( !window.confirm("Adakah Anda Pasti ?") ){
			//
			document.${formName}.actionPajakan.value = "paparpajakan";
			return;
		}

		document.${formName}.hitButton.value = "saveUpdatePajakan";
		document.${formName}.mode.value = "viewPajakan";
		doAjaxCall${formName}("");

	}
	function batalUpdatePajakan(){
		//
		document.${formName}.actionPajakan.value = "paparpajakan";
		document.${formName}.mode.value = "viewPajakan";
		doAjaxCall${formName}("");

	}
	function paparPajakan(idPajakan){
		//
		document.${formName}.actionPajakan.value = "paparpajakan";
		document.${formName}.mode.value = "viewPajakan";
		document.${formName}.idPajakan.value = idPajakan;
		//
		doAjaxCall${formName}("");

	}
	function hapusPajakan(){
		if (window.confirm("Adakah Anda Pasti ?") ){
			//
			document.${formName}.actionPajakan.value = "paparpajakan";
			document.${formName}.mode.value = "view";
			document.${formName}.hitButton.value = "hapusPajakan";
			doAjaxCall${formName}("");

		}

	}

	function kiraCukai(){
		document.${formName}.txtKadarCukai.value = document.${formName}.txtKadarCukaitemp.value;
	}

	//PENAMBAHBAIKAN FASA 3. POPUP/NOTIFIKASI PERINGATAN SEMULA / LUPUT (SYAZ, 27112014)
	function doCheckTarikhTamat(){
		var date = document.${formName}.txdTarikhTamatPajakan.value;

		if(date=="" || date=="NaN"){
			alert('Sila masukkan Tarikh Tamat Pajakan terlebih dahulu.');
			document.${formName}.socNotifikasiPeringatan.value = "";
	  		document.${formName}.txdTarikhTamatPajakan.focus();
			return;
		}

	}
	function cal_tarikh_luput(){
		var tr = document.${formName}.txdTarikhMulaPajakan.value;
		var tempoh = document.${formName}.txtTempoh.value;
		var luput = "";
		if((tr!="" && tr!=null) && (tempoh!="" && tempoh!=null)){
			var temp_tr = tr.substring(0,6)
		  	var year_cur = tr.substring(6,10)
			luput = temp_tr+(parseInt(year_cur)+parseInt(tempoh));

		}
		document.${formName}.txdTarikhTamatPajakan.value = luput;

	}

	function validateCurrency(elmnt,content,content2) {
		//if it is character, then remove it..
		if (isNaN(content)) {
			elmnt.value = content2;
			return;
		}

		if(content != ""){
			var num = content * 1;
			elmnt.value = num.toFixed(2);
			return;
		}else {
			elmnt.value ="";
			return;
		}

	}
	//TAMAT MAKLUMAT PAJAKAN

	//MULA PERJANJIAN

	//Skrin Senarai |Maklumat Perjanjian , Button [Hapus]
	function hapusPerjanjianSenarai(idraf){
		document.${formName}.idDraf.value = idraf;
		hapusPerjanjian();
	}
	function hapusPerjanjian(){
		if ( !window.confirm("Adakah Anda Pasti ?") ){
			return;
		}

		document.${formName}.actionPajakan.value = "paparperjanjian";
		document.${formName}.mode.value = "view";
		document.${formName}.hitButton.value = "hapusdrafper";
		doAjaxCall${formName}("");

	}
	// MULA KEMASKINI
		// Skrin Maklumat Perjanjian, Button [Simpan] (semasa kemaskini)
	function simpanKemaskiniPerjanjian(){
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
			//
			document.${formName}.actionPajakan.value = "paparperjanjian";
			return;
		}

		document.${formName}.mode.value = "viewDraf";
		//document.${formName}.actionPajakan.value = "paparperjanjian";
		document.${formName}.hitButton.value = "saveupdatedrafper";
		doAjaxCall${formName}("");

	}
	// Skrin Maklumat Perjanjian, Button [Kemaskini]
	function kemaskiniPerjanjian(){
		document.${formName}.actionPajakan.value = "paparperjanjian";
		document.${formName}.mode.value = "updateDraf";
		doAjaxCall${formName}("");

	}
	// Skrin Maklumat Perjanjian, Button[Kembali]
	function kembaliPerjanjian(){
		document.${formName}.actionPajakan.value = "paparperjanjian";
		document.${formName}.mode.value = "viewDraf";
		doAjaxCall${formName}("");

	}
	// TAMAT KEMASKINI
	// MULA BAHARU
	// Skrin Maklumat Perjanjian, Button [Simpan]
	function simpanPerjanjian(){
		if(document.${formName}.txdTarikhHantarDraf.value == ""){
			alert('Sila masukkan Tarikh Hantar Draf.');
	  		document.${formName}.txdTarikhHantarDraf.focus();
			return;
		}
	/*
	if(document.${formName}.txdTarikhTerimaDraf.value == ""){
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
			document.${formName}.actionPajakan.value = "paparperjanjian";
			return;
		}

		document.${formName}.mode.value = "viewDraf";
		//document.${formName}.hitButton.value = "saveDraf";
		document.${formName}.hitButton.value = "savedrafper";
		doAjaxCall${formName}("");

	}
	// Skrin Maklumat Perjanjian, Button [Batal]
	function batalPerjanjian(){
		document.${formName}.actionPajakan.value = "paparperjanjian";
		document.${formName}.mode.value = "papar";
		doAjaxCall${formName}("");

	}
	// TAMAT BAHARU

	// SENARAI
	//Skrin Senarai, link [Tarikh Terima]
	function paparPerjanjian(idDraf){
		document.${formName}.actionPajakan.value = "paparperjanjian";
		document.${formName}.mode.value = "viewDraf";
		document.${formName}.idDraf.value = idDraf;
		doAjaxCall${formName}("");

	}
	//Skrin Senarai, Button [Daftar Baru]
	function daftarBaruPerjanjian(){
		//
		document.${formName}.actionPajakan.value = "paparperjanjian";
		document.${formName}.mode.value = "newDraf";
		doAjaxCall${formName}("");

	}

</script>