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
	
    	 <table width="98%" cellpadding="0" border="0">  
       <tr><td>&nbsp;</td></tr>
        	<tr>
        		<td width="3%">&nbsp;</td>
              <td width="30%">Nama Dokumen</td>
              <td width="1%">:&nbsp;</td>
              <td width="66%"><input type="text" name="nama_dokumen" value="" onkeyup="this.value=this.value.toUpperCase();" id="nama_dokumen" size="48" /></td>
          </tr>
            
            <tr>
            	<td>&nbsp;</td>
            	<td valign="top">Keterangan</td>
                <td valign="top">:&nbsp;</td>
                <td valign="top"><textarea name="keterangan" id="keterangan" onkeyup="this.value=this.value.toUpperCase();" cols="45%" rows="5"></textarea></td>
            </tr>
            
            <tr>
            	<td>&nbsp;</td>
            	<td>Dokumen Sokongan</td>
                <td>&nbsp;</td>
                #foreach( $i in [1..$num_files] )
  				<td><input id="fileupload" name="fileupload" type=file size=40 /></td><br/>
  			    #end
  			</tr>
        </table>
    <br/>
    </fieldset>



	<table width="100%"  cellpadding="0" border="0">
    	<tr align="center">
        	<td>
        		<!-- <input name="cmdKemaskini" type="button" value="Kemaskini"> -->
                <input type="button" value="Simpan" onClick="Upload()">
                <!-- <input name="cmdBatal" type="button" value="Batal" onClick="kembali?()"> -->
                <input name="cmdView" type="button" value="Kembali" onclick="kembali('$id_fail','$id_permohonan')"/></td>
    	</tr>
    </table>
<center>

<input type="hidden" name="id_permohonan" value="$id_permohonan">
<input type="hidden" name="id_fail" value="$id_fail">

<script>
function doChanges() {
	doAjaxCall${formName}("doChanges");
}
function Upload(){
	
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	var id = document.${formName}.id_permohonan.value ;
	var dok = document.${formName}.nama_dokumen.value ;
	var ket = document.${formName}.keterangan.value ;
	document.${formName}.action = "?command=uploadFile&id_permohonan="+id+"&nama_dokumen="+dok+"&keterangan="+ket;
	document.${formName}.enctype = "multipart/form-data";
	document.${formName}.submit();

}
function kembali(id_fail,id_permohonan) {
	document.${formName}.command.value = "semak";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek4HakmilikSenarai&command=semak&id_fail="+id_fail+"&id_permohonan="+id_permohonan;
	document.${formName}.submit();
}
</script>