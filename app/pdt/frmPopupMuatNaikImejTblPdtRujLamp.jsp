<script type="text/javascript" src="../../library/js/ekptgTools.js"></script>
<link rel="stylesheet" type="text/css" href="../../css/eTapp_PDT.css">
<p>
	<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
</p>
<fieldset>
	<legend>
		<strong>Perincian Dokumen</strong>
	</legend>
	<table width="100%">
		<tr>
			<td width="29%" align="right" scope="row">Nama Dokumen</td>
			<td width="1%" scope="row">:</td>
			<td width="70%"><input size="30" name="txtNamaDokumen" type="text"
				id="txtNamaDokumen" value="$!txtNamaDokumen" />
				<input id="fileupload" name="fileupload" type="file" size="40" />	
			</td>
		</tr>
		<tr>
			<td align="right" scope="row">&nbsp;</td>
			<td scope="row">&nbsp;</td>
			<td><input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpan()" /> 
				<input type="reset" name="cmdKosongkanAkta" value="Kosongkan"/></td>
		</tr>
	</table>
</fieldset>
<input name="hitButton" type="hidden" id="hitButton" value="$!hitButton"/>
<input name="id" type="hidden" id="id" value="$!id"/>
<script>

function simpan() {
	try {
		document.${formName}.method.value="post";
		document.${formName}.hitButton.value="addDoc";
		var x = create_request_string(document.${formName});
		document.${formName}.enctype="multipart/form-data";
		document.${formName}.encoding="multipart/form-data";
		document.${formName}.action = "?_portal_module=ekptg.view.pdt.FrmSenaraiDokumen&"+x;
		document.${formName}.submit();
	}catch (err) {
		alert(err);
	}
	if (window.opener && !window.opener.closed) {
		window.opener.tmbhSnriDok(document.${formName}.id.value);
		window.close();
	}
}

</script>