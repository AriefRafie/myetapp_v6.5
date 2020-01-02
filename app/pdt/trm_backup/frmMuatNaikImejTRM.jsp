<p>
	<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
	<input type="hidden" name="hitButton">
	<input type="hidden" name="idWarta" value="$!idWarta">
	<input type="hidden" name="idWartaNew" value="$!idWartaNew">
	<input type="hidden" name="idPelanNew" value="$!idPelanNew">
	<input type="hidden" name="kawasan" value="$!kawasan">
	<input type="hidden" name="noWarta" value="$!noWarta">
	<input type="hidden" name="tarikhWarta" value="$!tarikhWarta">
	<input type="hidden" name="noPelan" value="$!noPelan">
	<input type="hidden" name="luas" value="$!luas">
	<input type="hidden" name="action" value="$!action">
	<input type="hidden" name="paramNegeri" value="$!paramNegeri">
	<input type="hidden" name="paramDaerah" value="$!paramDaerah">
	<input type="hidden" name="paramMukim" value="$!paramMukim">
	<input type="hidden" name="paramStatus" value="$!paramStatus">
	<input type="hidden" id="page" name="page" value="$!page">
	<input type="hidden" name="step" value="$!step">
	<input type="hidden" id="uploadMethod" name="uploadMethod" value="$!uploadMethod">
	<input type="hidden" name="idWartaGanti" value="$!idWartaGanti">
	<input type="hidden" name="idPelanGanti" value="$!idPelanGanti">
	<input type="hidden" name="idWartaWujud" value="$!idWartaWujud">
	<input type="hidden" name="idPelanWujud" value="$!idPelanWujud">
	<input type="hidden" name="idWartaBatal" value="$!idWartaBatal">
	<input type="hidden" name="idPelanBatal" value="$!idPelanBatal">
</p>
<fieldset>
	<legend>
		<strong>Perincian Imej</strong>
	</legend>
	<table width="100%">
		<tr>
			<td width="29%" align="left" scope="row"><a href="http://g4nrev2.mygeoportal.gov.my/" target="_blank" style="color: blue">[Laman WebMacGDi]</a></td>
		</tr>
		<tr>
			<td width="29%" align="right" scope="row">Direktori</td>
			<td width="1%" scope="row">:</td>
			<td width="70%">
<!-- 				<input name="paramKawasan" type="text" id="paramKawasan" value="$!paramKawasan" /> -->
				<input id="fileupload" name="fileupload" type="file" size="40" />
			</td>
		</tr>
		<tr>
			<td width="29%" align="right" scope="row">Catatan</td>
			<td width="1%" scope="row">:</td>
			<td width="70%">
				<textarea name="paramNoWartaWujud" rows="4" cols="50"></textarea>
			</td>
		</tr>
		<tr>
			<td align="right" scope="row">&nbsp;</td>
			<td scope="row">&nbsp;</td>
			<td>
				<input type="button" name="cmdSimpan" value="Simpan" onClick="javascript:simpanPendaftaran()" />
				<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="javascript:kembali()" />
			</td>
		</tr>
	</table>
</fieldset>
<div style="visibility: hidden;">
	<!-- 	<input type="hidden" name="mode" /> <input type="hidden" name="action" /> -->
</div>
<script type="text/javascript">
function simpanPendaftaran(){
	document.${formName}.method.value="post";
	document.${formName}.hitButton.value="saveUploadFile";
	var x = create_request_string(document.${formName});
	document.${formName}.enctype="multipart/form-data";
	document.${formName}.encoding="multipart/form-data";
	document.${formName}.action = "?_portal_module=ekptg.view.pdt.FrmCariMaklumatTRM&"+x;
	document.${formName}.submit();
}

function kemaskini(){
	document.${formName}.hitButton.value = "Kemaskini";
	doAjaxCall${formName}("doKemaskini");
}

function isisemula() {
	document.${formName}.reset();
	document.${formName}.paramNegeri.value = "";
	document.${formName}.paramDaerah.value = "";
	document.${formName}.paramMukim.value = "";

	document.${formName}.paramLuas.value = "";
	document.${formName}.paramStatus.value = "";
	document.${formName}.paramNoWartaWujud.value = "";
	document.${formName}.paramTarikhWujud.value = "";
	document.${formName}.paramNoPelanWujud.value = "";
	
	document.${formName}.paramNoWartaBatal.value = "";
	document.${formName}.paramTarikhBatal.value = "";
	document.${formName}.paramNoPelanBatal.value = "";
	document.${formName}.paramKawasan.value = "";
	document.${formName}.hitButton.value = "Isi Semula";
	doAjaxCall${formName}("doIsiSemula");
}


function kembali() {
	document.${formName}.hitButton.value = "kembali";
	document.${formName}.submit();
}

function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}
</script>

