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
	<fieldset id="top">
	<legend><strong>Maklumat Dokumen</strong></legend>
<!--  kmie, 20100824 -->
#parse("app/ppt/LinkMacGDI.jsp")
<!-- end kmie -->
	
    	 <table width="100%" cellpadding="0" border="0">  
        	
        	<tr>
        	  <td width="1%"><font color="red">*</font></td>
              <td width="20%">Nama Dokumen</td>
              <td width="1%">:</td>
              <td width="78%"><input type="text" name="txtNamaDokumen" value="" id="txtNamaDokumen" size="43" /></td>
          	</tr>
            
            <tr>
            	<td>&nbsp;</td>
            	<td valign="top">Keterangan</td>
            	<td valign="top">:</td>
                <td><textarea name="txtKeterangan" id="txtKeterangan" cols="40%" rows="3"></textarea></td>
            </tr>
            
            <tr>
            	<td valign="top"><font color="red">*</font></td>
            	<td valign="top">Dokumen</td>
            	<td valign="top">:</td>
  				<td><input id="fileupload" name="fileupload" type=file size=40 /></td><br/>
  			</tr>
            
        </table>
        
        <table width="100%" border="0">
        	<tr><td>&nbsp;</td></tr>
        	<tr>
        		<td>$!perhatian2</td>
        	</tr>
        </table>
        
    </fieldset>

	<table align="left" width="75%"  cellpadding="0" border="0">
    	<tr align="center">
        	<td>
                <input type="button" value="Simpan" onClick="Upload('$!id_permohonan')">   
                <input name="cmdKembali" type="button" value="Kembali" onClick="kembali('$!id_permohonan')" />
            </td>
        </tr>
    </table>
    
    <br/>
    <br/>
    <br/>
     
    <fieldset>
    <legend><strong>&nbsp;Senarai Dokumen yang Disertakan</strong></legend>
    
       	<table width="100%"  cellpadding="0" cellspacing="2" border="0">   
        	<tr class="table_header">
           		<td width="3%" align="center"><b>No</b></td>
                <td width="27%"><b>Nama Dokumen</b></td>
                <td width="34%"><b>Keterangan</b></td>
                <td width="30%"><b>Dokumen Sokongan</b></td>
                #if($listD_size!=0)
                <td width="6" align="center"><b>&nbsp;</b></td>
                #end
            </tr>
              
         #if($listD_size!=0)
          
             #foreach($listD in $listDokumen)  
                   
                    #set( $i = $velocityCount )       	
         		#if ( ($i % 2) != 1 )
              		#set( $row = "row2" )
         		#else
               		#set( $row = "row1" )
         		#end
         		      
          	<tr>
                <td class="$row" align="center">$listD.bil</td>
                <td class="$row">$listD.tajuk</td>
                <td class="$row">$listD.keterangan</td>
                <td class="$row"><a href="javascript:papar_Lampiran('$!listD.id_Dokumen')"><font color="blue">$listD.nama_Fail</font></a></td>
                #if($listD_size!=0)
                <td class="$row" align="center"><input type="button" name="cmdHapusDoc" value ="Hapus" onClick="hapusDokumen('$!listD.id_Dokumen')"></td>	
                #end
            </tr>
             #end  
              		 
         #else
            <tr>
                <td colspan="4">Tiada rekod</td>
            </tr>
         #end
                    
       </table>        	
    </fieldset>	
            
</center>




<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">
<input type="hidden" name="tabId">

<script>
function hapusDokumen(iddokumen) {
	
	document.${formName}.ScreenLocation.value = "top";
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek8&command=hapusDokumenII&id_dokumen="+iddokumen;
	document.${formName}.submit();
}
function papar_Lampiran(id_dokumen) {
    var url = "../servlet/ekptg.view.ppt.DisplayBlob?id="+id_dokumen;
    var hWnd = window.open(url,'displayfile','width=800,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function Upload(idpermohonan){	

	if(document.${formName}.txtNamaDokumen.value == ""){
		alert("Sila masukkan \"Nama Dokumen\" terlebih dahulu.");
  		document.${formName}.txtNamaDokumen.focus(); 
		return;
	}
	if(document.${formName}.fileupload.value == ""){
		alert("Sila pilih \"Dokumen\" yang hendak dimuat naik terlebih dahulu.");
  		document.${formName}.fileupload.focus(); 
		return;
	}
	else{
		if ( !window.confirm("Adakah Anda Pasti?")) return;

		//token for dopost
		var ft = document.${formName}.form_token.value ;
		var token = "&form_token="+ft;

		//data
		var name = document.${formName}.txtNamaDokumen.value ;
		var keterangan = document.${formName}.txtKeterangan.value ;

		var command = "&command=tambahDokumen&command2=uploadFile";
		var data = "&id_permohonan="+idpermohonan+"&nama_dokumen="+name+"&keterangan="+keterangan;

		var actionItem = (command+data+token);
		
		document.${formName}.enctype = "multipart/form-data";
		document.${formName}.encoding = "multipart/form-data";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek8"+actionItem;
		document.${formName}.submit();		
	}
}
function kembali(idpermohonan){
	document.${formName}.ScreenLocation.value = "middle";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "semakPendaftaran";
	document.${formName}.tabId.value = "1";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek8";
	document.${formName}.submit();
}
</script>