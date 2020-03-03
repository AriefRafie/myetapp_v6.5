<<<<<<< HEAD:app/tranningjava/202002/index.jsp

<table>
	<tr>
		<td>Upload your Fail: 
		<input name="uploadFail" type="file" value="upload" />
		<input name="submitButton" type="button" value="Submit" OnClick="simpanLampiran()"/>
		</td>
	</tr>

</table>


<script>
function simpanLampiran(){
	var upload = document.${formName}.uploadFail.value;
	if(upload = ''){
		alert('Sila upload dahulu');
	}else{
		ducument.${formName}.enctype="multipart/form-data";
		document.${formName}.encoding="multipart/form-data";
	}
	alert('Simpan lampiran');
	document.${formName}.action="?_portal_module=latihan.sip.latihanShiqa&command=upload";
	document.${formName}.submit();
}
</script>
=======

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
>>>>>>> 2be87460a9e07eb83846caaca2c361b40eb672a7:app/trainingjava/202002/index.jsp
