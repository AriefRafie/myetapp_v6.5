20200228/index.jsp
	<br>
	<fieldset>
		<legend>MUATNAIK DOKUMEN</legend>
		<table width="100%" border="0">
			<tr>
				<td>
				 	<table width="100%" border="0" cellspacing="2" cellpadding="2">
						<tr>
							<td valign="top" width="1%"></td>
							<td valign="top" width="28%">Dokumen</td>
							<td valign="top" width="1%">:</td>
							<td valign="top" width="70%">
								<input type="file" name="dokumen" >							
							</td>
			
				
				</td>
			</tr>
		</table>
	</fieldset>
	<INPUT TYPE="button" NAME="one" VALUE="Simpan" OnClick="simpanLampiran()">
	

<script>

function simpanLampiran() {
	var dok = document.${formName}.dokumen.value;
	if(dok==''){
		alert('Sila pilih fail terlebih dahulu');
		return;
		
	}else{
 		document.${formName}.enctype="multipart/form-data";
	   	document.${formName}.encoding="multipart/form-data";
	
	}
	//alert('simpan lampiran');
	//document.${formName}.command.value = "upload";
	//
	document.${formName}.action="?_portal_module=latihan.sip.FrmCukai&command=upload";
	document.${formName}.submit();
	
}

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