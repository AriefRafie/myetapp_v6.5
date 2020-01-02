<br/>
<fieldset>
  <legend><strong>Maklumat Penampalan Notis Awam Seksyen 4</strong></legend>
   
     
      #foreach ( $agensi in $SenaraiAgensi )
    	<input name="id_permohonan" type="hidden" value="$agensi.id_permohonan" />
      #end
     
     <table width="100%" cellspacing="2" cellpadding="1" border="0">
     #foreach ( $notis in $NotisAwamList )
     <input name="id_notisawam" type="hidden" value="$notis.id_notisawam">
     
 		 	<tr>
			  	<td width="25%">Tempat Tampal</td>
			  	<td width="1%">:&nbsp;</td>
  				<td width="74%"><select name="jenis_tempat_tampal"  style="width:auto" tabindex="3" class="disabled" disabled >
  				  <option value="1" $selectA>PTD / PTG</option>
  				  <option value="2" $selectB>Dalam Mukim</option>
  				  <option value="3" $selectC>Tempat Lain Di atas / Berhampiran Tanah</option>
                </select>
                <input name="jenis_tempat_tampal" type="hidden" value="$notis.jenis_tempat_tampal"></td>
 	   		</tr>
 		 	
 		 	<tr>
 		 	  <td valign="top">Tempat</td>
 		 	  <td valign="top">:&nbsp;</td>
 		 	  <td><textarea name="tempat" rows="4" cols="40%" class="disabled" readonly >$notis.tempat</textarea></td>
 	   		</tr>
 		 	
 		 	<tr>
 		 	  <td>Tarikh Tampal</td>
 		 	  <td>:&nbsp;</td>
 		 	  <td><input name="tarikh_tampal" type="text" size="11" value="$notis.tarikh_tampal" class="disabled" readonly "></td>
 	   </tr>
 	 #end 
 	</table>   
</fieldset>
 	 
 	<table align="center">   
 	   <tr>
 	   		<td>&nbsp;</td>
 	   		<td>&nbsp;</td>
 			<td>
 				#if($status!="52")
 				<input name="" type="button" value="Kemaskini" onClick="kemaskini('$id_notisawam')">
       			#end
       			<input name="" type="button" value="Kembali" onClick="kembali('$id_permohonan')"></td>
 		</tr>
        
	</table>
  
     	<input name="skrin2" type="hidden" value="">
       	<input name="skrin3" type="hidden" value="">
       	<input name="skrin4" type="hidden" value="">
       	<input name="skrin5" type="hidden" value="">


<script>
function kemaskini(id_notisawam) {
	document.${formName}.command.value = "Semak2";
	document.${formName}.skrin2.value = "Papar";
	document.${formName}.skrin3.value = "Kemaskini";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.UPTSek4NotisAwam";
	document.${formName}.submit();
}
function kembali(id_permohonan) {
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "Semak2";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.UPTSek4NotisAwam";
	document.${formName}.submit();
}
</script>
