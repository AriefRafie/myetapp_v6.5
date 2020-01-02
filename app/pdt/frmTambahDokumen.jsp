<script type="text/javascript" src="../../library/js/ekptgTools.js"></script>
<link rel="stylesheet" type="text/css" href="../../css/eTapp_PDT.css">
<p>
	<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
</p>
<fieldset>
	<legend>
		<strong>Dokumen</strong>
	</legend>
	<table width="100%">
		<tr>
			<td width="29%" align="right" scope="row">Tajuk Dokumen</td>
			<td width="1%" scope="row">:</td>
			<td width="70%"><!--<textarea name="txtNamaDokumen" id="txtNamaDokumen" value="$!txtNamaDokumen" />--><label>
        <textarea name="txtNamaDokumen" cols="41" id="txtNamaDokumen">$!txtNamaDokumen</textarea>
      </label></td>
		</tr>
		<tr>
			<td align="right" scope="row">&nbsp;</td>
			<td scope="row">&nbsp;</td>
			<td>
				<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpan()" /> 
				<input type="reset" name="cmdKosongkanAkta" value="Kosongkan"/>
			</td>
		</tr>
	</table>
</fieldset>
<input name="hitButton" type="hidden" id="hitButton" value="$!hitButton"/>
<input name="id" type="hidden" id="id" value="$!id"/>
<input name="idTblPdtSenaraiDokumen" type="hidden" id="idTblPdtSenaraiDokumen" value="$!idTblPdtSenaraiDokumen"/>
<input name="step" type="hidden" id="step" value="$!step"/>
<script>

function simpan() {
	
	try {
		if(document.${formName}.step.value == "addInduk"){
			var x = "hitButton=saveInduk&txtNamaDokumen="+document.${formName}.txtNamaDokumen.value+"";
			document.${formName}.action = "?_portal_module=ekptg.view.pdt.FrmTambahDokumen&"+x;
			document.${formName}.submit();
		}else if(document.${formName}.step.value == "addChild"){
			var x = "hitButton=saveChild&txtNamaDokumen="+document.${formName}.txtNamaDokumen.value+"&id="+document.${formName}.id.value+"";
			document.${formName}.action = "?_portal_module=ekptg.view.pdt.FrmTambahDokumen&"+x;
			document.${formName}.submit();
		}else if(document.${formName}.step.value == "editInduk"){
			var x = "hitButton=saveEditInduk&txtNamaDokumen="+document.${formName}.txtNamaDokumen.value+"&id="+document.${formName}.idTblPdtSenaraiDokumen.value+"";
			document.${formName}.action = "?_portal_module=ekptg.view.pdt.FrmTambahDokumen&"+x;
			document.${formName}.submit();
		}
	}
	catch (err) {
	}
	
	if (window.opener && !window.opener.closed) {
		 window.opener.search();
		window.close();
	}
}

</script>