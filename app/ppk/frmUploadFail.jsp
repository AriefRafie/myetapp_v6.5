<!-- elly -->

<form name="f1" action="" enctype="multipart/form-data" method="post"
	 onsubmit="setTimeout('fileupload_ajax_query_upload_status()', 1000)">
     
<fieldset>
	<legend><strong>Maklumat Fail</strong></legend>	
    	 <table width="100%" cellpadding="0" border="0">  
        	<tr>
        	  <td width="5%">&nbsp;</td>
              <td width="22%"><font color="red">*</font>&nbsp;Nama Fail</td>
              <td width="1%">:&nbsp;</td>
              <td width="72%"><input type="text" name="nama_dokumen" id="nama_dokumen" value="" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" size="43px" /></td>
          </tr>
            <tr>
            	<td>&nbsp;</td>
            	<td valign="top"><font color="red">*</font>&nbsp;Keterangan</td>
            	<td valign="top">:&nbsp;</td>
                <td><textarea name="keterangan" id="keterangan" value="" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" cols="40%" rows="5"></textarea></td>
            </tr>            
            <tr>
            	<td>&nbsp;</td>
            	<td><font color="red">*</font>&nbsp;Fail Sokongan</td>
            	<td>&nbsp;</td>          	
               #foreach( $i in [1..$num_files] )
  				<td><input id="fileupload" name="fileupload" type=file size=40 /></td><br/>
  			   #end				
            </tr>
		<table width="100%"  cellpadding="0" border="0">
    		<tr align="center">
        		<td><input type="button" value="Simpan" onClick="Upload()">
                	<input type="button" name="cmdExit" value="kembali" onclick="self.close();return false;" />
            	</td>
        	</tr>
    	</table>  
    </table>
        <br/>

</fieldset>     

<input name="idPermohonan" type="hidden" value="$idPermohonan" /> 
<input name="idFail" type="hidden" value="$idFail" /> 
<input type="hidden" readonly="true" name="ekptg_user_id" size="5" value="${session.getAttribute("_ekptg_user_id")}" /> 
<input name="idDokumen" type="hidden" value="1" />
</form>

<div id="fileupload_progress"></div>
<div id="progressBar" style="display: none;">
<div id="progressBarBoxContent"></div>
</div>	

##List of files:<br> 
##foreach ( $fail in $SenaraiFail )
<!--<a href="#" onClick="javascript:doOpen($fail.id)">$fail.nama_fail</a><br>-->
##end	 
 
#if ($completed)
<script>
parent.document.getElementById("fileupload_progress").innerHTML="<div class=\"success\">File successfully uploaded.</div>";
</script>
#end

<script language="JavaScript">
function Upload(){
	if(document.f1.nama_dokumen.value == ""){
		alert("Sila masukkan \"Nama Fail\" terlebih dahulu.");
  		document.f1.nama_dokumen.focus(); 
		return;
	}
	if(document.f1.keterangan.value == ""){
		alert("Sila masukkan \"Keterangan\" terlebih dahulu.");
  		document.f1.keterangan.focus(); 
		return;
	}
	if(document.f1.fileupload.value == ""){
		alert("Sila masukkan \"Fail Sokongan\" terlebih dahulu.");
  		document.f1.fileupload.focus(); 
		return;
	}else{	
	if ( !window.confirm("Adakah Anda Pasti?")) return;	
	var id = document.f1.idPermohonan.value ;
	var idFail = document.f1.idFail.value ;
	var dok = document.f1.nama_dokumen.value ;
	var uid = document.f1.ekptg_user_id.value ;
	var ket = document.f1.keterangan.value ;
	
	document.f1.action = "?command2=uploadFile&command=uploadFile&idPermohonan="+id+"&idFail="+idFail+"&nama_dokumen="+dok+"&ekptg_user_id="+uid+"&keterangan="+ket;
	document.f1.submit();
	window.opener.refreshUploadPage();
	}
}
function doOpen(id) {
	    var url = "../servlet/ekptg.view.ppk.DisplayBlob?id="+id;
	    var hWnd = window.open(url,'displayfile','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
	    hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
}

</script>