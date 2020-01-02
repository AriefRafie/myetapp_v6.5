<script type="text/javascript" >

	function simpanKemaskiniPantau(){
		document.${formName}.actionPajakan.value = "pemantauan";
		
		if(document.${formName}.txdtindakan.value == ""){
			alert('Sila masukkan " Tarikh " terlebih dahulu.');
	  		document.${formName}.txdtindakan.focus(); 
			return; 
		}
		if ( !window.confirm("Adakah Anda Pasti ?") ){
			return;
		}
			
		document.${formName}.hitButton.value = "tindakankemaskini";
		document.${formName}.mode.value = "papar";
		doAjaxCall${formName}("");
		
	}

	function paparPantau(id_){
		document.${formName}.actionPajakan.value = "pemantauan";
		document.${formName}.mode.value = "papar";
		document.${formName}.idsusulan.value = id_;
		doAjaxCall${formName}("");
	}
	function hapusPantau(){
		document.${formName}.actionPajakan.value = "pemantauan";
		document.${formName}.hitButton.value = "tindakanhapus";
		document.${formName}.mode.value = "";
		doAjaxCall${formName}("");
	}

	function batalKemaskiniPantau(){
		document.${formName}.actionPajakan.value = "pemantauan";
		document.${formName}.mode.value = "papar";
		doAjaxCall${formName}("");
	}
	function simpanPantau(){
		document.${formName}.actionPajakan.value = "pemantauan";
		if(document.${formName}.txdtindakan.value == ""){
			alert('Sila masukkan " Tarikh " terlebih dahulu.');
	  		document.${formName}.txdtindakan.focus(); 
			return; 
		}
		if ( !window.confirm("Adakah Anda Pasti ?") ){
			return;
		}
		document.${formName}.hitButton.value = "tindakansimpan";
		document.${formName}.mode.value = "papar";
		doAjaxCall${formName}("");
		
	}
	
	function kemaskiniPantau(){
		document.${formName}.actionPajakan.value = "pemantauan";
		document.${formName}.hitButton.value = "";
		document.${formName}.mode.value = "kemaskini";
		doAjaxCall${formName}("");
	}
	
	function daftarBaruPantau(){
		document.${formName}.actionPajakan.value = "pemantauan";
		document.${formName}.mode.value = "baharu";
		doAjaxCall${formName}("");
		
	}
	function batalPantau(){
		document.${formName}.actionPajakan.value = "pemantauan";
		document.${formName}.mode.value = "";
		doAjaxCall${formName}("");
		
	}
</script>