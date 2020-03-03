
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
