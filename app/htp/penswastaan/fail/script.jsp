


<script>
	
function doHantarPengesahan() {
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionPenswastaan.value = "papar";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.actionPenswastaan.value = "papar";
	document.${formName}.hitButton.value = "hantarPengesahan";
	doAjaxCall${formName}("");
	
}

function doSahkan() {
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionPenswastaan.value = "papar";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.actionPenswastaan.value = "papar";
	document.${formName}.hitButton.value = "sahkanPermohonan";
	doAjaxCall${formName}("");
	
}

</script>