<br/>
<fieldset>
  <legend><strong>Maklumat Penampalan Notis Awam Seksyen 4</strong></legend>
   
     
     #foreach ( $agensi in $SenaraiAgensi )
    	<input name="id_permohonan" type="hidden" value="$agensi.id_permohonan" />
   	 #end
     
     #foreach ( $notis in $NotisAwamList )
     
     <table width="100%" cellspacing="0">
 		 
 		 <tr>
			<td width="25%">Tempat Tampal</td>
			<td width="1%">:&nbsp;</td>
  			<td width="74%"><select name="jenis_tempat_tampal" style="width:auto" id="select2" tabindex="3">
  				  <option value="1" $selectA>PTD / PTG</option>
  				  <option value="2" $selectB>Dalam Mukim</option>
  				  <option value="3" $selectC>Tempat Lain Di atas / Berhampiran Tanah</option>
                </select>
                <input name="jenis_tempat_tampal" type="hidden" value="$notis.jenis_tempat_tampal">
            </td>
 	   	 </tr>
 	   	
 		 <tr>
 		 	 <td valign="top">Tempat</td>
 		 	 <td valign="top">:&nbsp;</td>
 		 	 <td><textarea name="tempat" rows="4" cols="40%" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();">$notis.tempat</textarea>              </td>
 	   	 </tr>
 	   	 
 		 <tr>
 		 	  <td>Tarikh Tampal</td>
 		 	  <td>:&nbsp;</td>
 		 	  <td><input name="tarikh_tampal" type="text" size="11" onblur="check_date(this)" value="$notis.tarikh_tampal">
              <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('tarikh_tampal',false,'dmy');" style="display:$Style2"></td>
 	   	 </tr>
 	   </table> 	 
</fieldset>

 	 <table align="center">
 	   
 	   	 <tr align="center">
 	   		
 			<td><input name="" type="button" value="Simpan" onclick="edit_Notis()">
       			<input name="" type="button" value="Batal" onclick="batal('$notis.id_notisawam')"></td>
 		</tr>
 		
	 </table>
	 
     <input name="id_notisawam" type="hidden" value="$notis.id_notisawam">
     #end

       	<input name="skrin2" type="hidden" value="">
       	<input name="skrin3" type="hidden" value="">
       	<input name="skrin4" type="hidden" value="">
       	<input name="skrin5" type="hidden" value="">


<script>
function edit_Notis() {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.command.value = "Semak2";
	document.${formName}.skrin2.value = "Papar";
	document.${formName}.skrin3.value = "Kemaskini";
	document.${formName}.skrin4.value = "Simpan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.UPTSek4NotisAwam";
	document.${formName}.submit();
}
function batal(id_notisawam) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_notisawam.value = id_notisawam;
	document.${formName}.command.value = "Semak2";
	document.${formName}.skrin2.value = "Papar";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.UPTSek4NotisAwam";
	document.${formName}.submit();
}
</script>
