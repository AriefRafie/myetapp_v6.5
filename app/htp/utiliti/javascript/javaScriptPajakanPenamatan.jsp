<script type="text/javascript" >
//TAB PENGHANTARAN/ SELESAI

	function simpanSelesai(){
		document.${formName}.actionPajakan.value = "penamatan";
		if(document.${formName}.txdTarikhTerima.value == ""){
			alert('Sila masukkan " Tarikh " terlebih dahulu.');
	  		document.${formName}.txdTarikhTerima.focus();
			return;
		}
		document.${formName}.hitButton.value = "selesaisimpan";
		document.${formName}.mode.value = "view";
		doAjaxCall${formName}("");

	}

	function kemaskiniSimpanSelesai(){
		document.${formName}.actionPajakan.value = "penamatan";
		document.${formName}.hitButton.value = "selesaikemaskinisimpan";
		document.${formName}.mode.value = "view";
		doAjaxCall${formName}("");

	}

	function kemaskiniSelesai(){
		document.${formName}.actionPajakan.value = "penamatan";
		document.${formName}.hitButton.value = "selesaikemaskini";
		document.${formName}.mode.value = "kemaskini";
		doAjaxCall${formName}("");

	}

	function cetakSuratKemungkiran(idpermohonan) {
	    var url = "../servlet/ekptg.report.htp.NoFailTajukFail?template=HTPPajakanNotisKemungkiran&idpermohonan="+idpermohonan;
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();

	}

</script>