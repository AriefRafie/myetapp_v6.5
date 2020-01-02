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

</script>