#parse("app/ppt/frmLabelSet.jsp")

<div id="fileupload_progress"></div>
<div id="progressBar" style="display: none;">
<div id="progressBarBoxContent"></div>
</div>

#if ($completed)
<script>
parent.document.getElementById("fileupload_progress").innerHTML="<div class=\"success\">Dokumen berjaya dimuat naik.</div>";
</script>
#end

<center>
	<fieldset id="top">
	<legend><strong>Maklumat Dokumen</strong></legend>
	
    	 <table width="100%" cellpadding="0" border="0">  
        	
        	<tr>
        	  <td width="1%"><font color="red">*</font></td>
              <td width="20%">Nama Dokumen</td>
              <td width="1%">:</td>
              <td width="78%"><input type="text" name="txtNamaDokumen" value="" id="txtNamaDokumen" size="43" /></td>
          	</tr>
          	
          	<tr>
        	  <td width="1%"><font color="red">*</font></td>
              <td width="20%">Jenis Dokumen</td>
              <td width="1%">:</td>
              <!-- <td width="78%">
              <select name="txtJenisDokumen">
              <option value="">Sila Pilih</option>
              <option value="16171330">Surat Iringan</option>
              <option value="16171331">Pelan</option>
              <option value="1329">Lain-Lains</option>
              </select>
              </td> -->
              
              <td width="78%">
              <select id="txtJenisDokumen" name="txtJenisDokumen">
				<option value = "" >SILA PILIH</option>
				#foreach ( $neg in $jenis_dokumen )
				#set ( $selected_doc = "" )
				#if($txtJenisDokumen==$neg.ID_JENISDOKUMEN)
				#set ( $selected_doc = "selected" )
				#end
				<option $selected_doc value="$neg.ID_JENISDOKUMEN" >$neg.KETERANGAN</option>
				#end
				</select>
              </td>
          	</tr>
            
            <tr>
            	<td>&nbsp;</td>
            	<td valign="top">Keterangan</td>
            	<td valign="top">:</td>
                <td><textarea name="txtKeterangan" id="txtKeterangan" cols="40%" rows="3"></textarea></td>
            </tr>
            
            <tr>
            	<td valign="top"><font color="red">*</font></td>
            	<td valign="top">Lampiran Dokumen</td>
            	<td valign="top">:</td>
  				<td><input id="fileupload" name="fileupload" type=file size=40 /></td>
  			</tr>
  			
  			<tr>
  				<td valign="top"></td>
            	<td valign="top"></td>
            	<td valign="top"></td>
  				<td><input id="fileupload" name="fileupload" type=file size=40 /></td>
  			</tr>
  			
  			<tr>
  				<td valign="top"></td>
            	<td valign="top"></td>
            	<td valign="top"></td>
  				<td><input id="fileupload" name="fileupload" type=file size=40 /></td>
  			</tr>
  			
  			<tr>
  				<td valign="top"></td>
            	<td valign="top"></td>
            	<td valign="top"></td>
  				<td><input id="fileupload" name="fileupload" type=file size=40 /></td>
  			</tr>
  			
  			<tr>
  				<td valign="top"></td>
            	<td valign="top"></td>
            	<td valign="top"></td>
  				<td><input id="fileupload" name="fileupload" type=file size=40 /></td>
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
           		<td width="3%" align="center"><b>Bil</b></td>
                <td width="20%"><b>Nama Dokumen</b></td>
                <td width="34%"><b>Keterangan</b></td>
                <td width="15%"><b>Jenis Dokumen</b></td>
                <td width="30%"><b>Muat Turun</b></td>
                <td width="30%"><b>Tindakan</b></td>
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
                <td class="$row">$!listD.jenisdoc</td>
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
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTOnline&command=hapusDokumenII&id_dokumen="+iddokumen;
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
	/* if(document.${formName}.fileupload.value == ""){
		alert("Sila pilih \"Lampiran Dokumen\" yang hendak dimuat naik terlebih dahulu.");
  		document.${formName}.fileupload.focus(); 
		return;
	} */
	if(document.${formName}.txtJenisDokumen.value == ""){
		alert("Sila pilih \"Jenis Dokumen\" terlebih dahulu.");
  		document.${formName}.txtJenisDokumen.focus(); 
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
		var jenisDoc = document.${formName}.txtJenisDokumen.value ;

		var command = "&command=tambahDokumen&command2=uploadFile";
		var data = "&id_permohonan="+idpermohonan+"&nama_dokumen="+name+"&keterangan="+keterangan+"&id_jenisdokumen="+jenisDoc;

		var actionItem = (command+data+token);
		
		document.${formName}.enctype = "multipart/form-data";
		document.${formName}.encoding = "multipart/form-data";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTOnline"+actionItem;
		document.${formName}.submit();		
	}
}
function kembali(idpermohonan){
	document.${formName}.ScreenLocation.value = "middle";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "semakPendaftaran";
	document.${formName}.tabId.value = "1";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTOnline";
	document.${formName}.submit();
}
</script>