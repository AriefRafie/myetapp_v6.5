
#parse("app/ppt/frmLabelSet.jsp")

<div id="fileupload_progress"></div>
<div id="progressBar" style="display: none;">
<div id="progressBarBoxContent"></div>
</div>

#if ($completed)
<script>
parent.document.getElementById("fileupload_progress").innerHTML="<div class=\"success\">Fail berjaya dimuatnaik.</div>";
</script>
#end


<center>
	<fieldset style="width:80%">
	<legend><strong>Maklumat Dokumen</strong></legend>
<!--  kmie, 20100824 -->
#parse("app/ppt/LinkMacGDI.jsp")
<!-- end kmie -->
	
    	 <table width="100%" cellpadding="0" border="0">  
        	<tr><td>&nbsp;</td></tr>
        	<tr>
        	  <td width="5%">&nbsp;</td>
              <td width="22%"><font color="red">*</font>Nama Dokumen</td>
              <td width="1%">&nbsp;</td>
              <td width="72%"><input type="text" name="nama_dokumen" value="" id="nama_dokumen" size="43" /></td>
          </tr>
            
            <tr>
            	<td>&nbsp;</td>
            	<td valign="top">Keterangan</td>
            	<td valign="top">:&nbsp;</td>
                <td><textarea name="keterangan" id="keterangan" cols="40%" rows="5"></textarea></td>
            </tr>
            
            <tr>
            	<td>&nbsp;</td>
            	<td><font color="red">*</font>Dokumen Sokongan</td>
            	<td>&nbsp;</td>
  				<td><input id="fileupload" name="fileupload" type=file size=40 /></td><br/>
  			</tr>
            
        </table>
        
        <table width="100%" border="0">
        	<tr><td width="5%">&nbsp;</td><td width="95%">&nbsp;</td></tr>
        	<tr>
        		<td>&nbsp;</td>
        		<td>$!perhatian2</td>
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
</center>

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">
<input type="hidden" name="tabId">

<input type="hidden" name="id_permohonan" value="$id_permohonan">
<!-- <input type="hidden" name="command"> -->

<script>
function Upload(){	

	if(document.${formName}.nama_dokumen.value==""){
		alert("Sila masukkan \"Nama Dokumen\" terlebih dahulu.");
  		document.${formName}.nama_dokumen.focus(); 
		return;
	}else if(document.${formName}.fileupload.value==""){
		alert("Sila pilih \"Dokumen\" yang hendak dimuat naik terlebih dahulu.");
  		document.${formName}.fileupload.focus(); 
		return;
	}else{
	
		if ( !window.confirm("Adakah Anda Pasti?")) return;
		var id = document.${formName}.id_permohonan.value ;
		var name = document.${formName}.nama_dokumen.value ;
		var keterangan = document.${formName}.keterangan.value ;

		document.${formName}.enctype = "multipart/form-data";
		document.${formName}.encoding = "multipart/form-data";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4&command=uploadFile&id_permohonan="+id+"&nama_dokumen="+name+"&keterangan="+keterangan;
		document.${formName}.submit();	

	}	
}
function kembali(id_permohonan) {
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4&command=semak&id_permohonan="+id_permohonan;
	document.${formName}.tabId.value = "1";
	document.${formName}.submit();
}
</script>