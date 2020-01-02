<br/>

<div id="fileupload_progress"></div>
<div id="progressBar" style="display: none;">
<div id="progressBarBoxContent"></div>
</div>

#if ($completed)
<script>
parent.document.getElementById("fileupload_progress").innerHTML="<div class=\"success\">File successfully uploaded.</div>";
</script>
#end
<center>
<fieldset style="width:80%">
<legend><strong>Maklumat Dokumen</strong></legend>

<table width="100%">
  <!--<tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>-->
  <tr>
    <td width="1%"><font color="red">*</font></td>
    <td width="28%">Nama Dokumen</td>
    <td width="1%">:</td>
    <td width="70%"><input type="text" name="nama_dokumen" value="" onkeyup="this.value=this.value.toUpperCase();" id="nama_dokumen" size="43px" /></td>
  </tr>
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%" valign="top">Keterangan</td>
    <td width="1%" valign="top">:</td>
    <td width="70%"><textarea name="keterangan" id="keterangan" onkeyup="this.value=this.value.toUpperCase();" cols="40%" rows="5"></textarea></td>
  </tr>
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%">Dokumen Sokongan</td>
    <td width="1%">:</td>
     #foreach( $i in [1..$num_files] )
  		<td width="70%"><input id="fileupload" name="fileupload" type="file" size="40" /></td><br/>
     #end
    
  </tr>
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
    <td width="70%"><label>
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onClick="Upload()" />
      <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="kembali('$id_permohonan')" >
    </label></td>
  </tr>
</table>
</fieldset>
<input type="hidden" name="id_permohonan" value="$id_permohonan">

</center>
<script>
function Upload(){
	
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	var id = document.${formName}.id_permohonan.value ;
	var dok = document.${formName}.nama_dokumen.value ;
	var ket = document.${formName}.keterangan.value ;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraSPT&action=uploadFile&id_permohonan="+id+"&nama_dokumen="+dok+"&keterangan="+ket;
	document.${formName}.method="post";
	document.${formName}.enctype="multipart/form-data";
	document.${formName}.submit();
}
function kembali(id_permohonan) {
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "semak";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraSPT&action=semak&id_permohonan="+id_permohonan;
	document.${formName}.submit();
}
</script>