
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
	
    	 <table width="100%" cellpadding="0" border="0">  
        	<tr>
        	  <td width="5%">&nbsp;</td>
              <td width="22%"><font color="red">*</font>Nama Dokumen</td>
              <td width="1%">:&nbsp;</td>
              <td width="72%"><input type="text" name="nama_dokumen" value="" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();"  id="nama_dokumen" size="43" /></td>
          </tr>
            
            <tr>
            	<td>&nbsp;</td>
            	<td valign="top">Keterangan</td>
            	<td valign="top">:&nbsp;</td>
                <td><textarea name="keterangan" id="keterangan" value="" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();"  cols="40%" rows="5"></textarea></td>
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
                <input name="cmdKembali" type="button" value="Kembali" onClick="kembali('$id_permohonan')" /></td>
        </tr>
    </table>

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<input name="id_permohonan" type="hidden" value="$id_permohonan" /> 

<script>

function Upload(){
	
		if ( !window.confirm("Adakah Anda Pasti?")) return;
		var id = document.${formName}.id_permohonan.value ;
		var dok = document.${formName}.nama_dokumen.value ;
		var ket = document.${formName}.keterangan.value ;
		document.${formName}.enctype = "multipart/form-data";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmAPDaftarSenarai&command=uploadFile&id_permohonan="+id+"&nama_dokumen="+dok+"&keterangan="+ket;
		document.${formName}.submit();	
}
function kembali(id_permohonan) {
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmAPDaftarSenarai&command=semak&id_permohonan="+id_permohonan;
	document.${formName}.submit();
}
</script>
