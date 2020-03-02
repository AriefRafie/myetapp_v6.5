
	<input name="command01" input="text">
	<INPUT TYPE="button" NAME="one"   
	VALUE="Submit" OnClick="hantar()">
	
	07/02/2020 ( $!nama1 )

<script>

function hantar() {
	//document.${formName}.command01.value = "testSubmit";
	document.${formName}.submit();
}

function hantarContextPutValue() {
	document.${formName}.command.value = "hantarContext";
	document.${formName}.submit();
}


function cubaSubmitButtonAjax() {
	doAjaxCall${formName}("testSubmit");
}

function hantarContextPutValueAjax() {
	doAjaxCall${formName}("hantarContext");
}

</script>