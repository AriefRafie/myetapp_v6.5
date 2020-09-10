<script>

	document.${formName}.actionPajakan.value = "paparpajakan";
	//alert($!txtCatatan);
	//document.${formName}.txtcatatan.value = "$!txtCatatan";

	function paparPajakan(idPajakan){
		document.${formName}.idPajakan.value = idPajakan;
		document.${formName}.mode.value = "viewPajakan";
		//document.${formName}.actionPajakan.value = "papar";
		//document.${formName}.actionPajakan.value = "paparperjanjian";
		document.${formName}.submit();
		//doAjaxCall${formName}("");

	}
	function kemaskiniPajakan(){
		//document.${formName}.actionPajakan.value = "papar";
		//document.${formName}.actionPajakan.value = "paparperjanjian";
		document.${formName}.mode.value = "updatePajakan";
		document.${formName}.submit();
		//doAjaxCall${formName}("");

	}

</script>