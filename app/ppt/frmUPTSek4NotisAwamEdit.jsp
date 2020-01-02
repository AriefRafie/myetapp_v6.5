<br/>
<fieldset>
  <legend><strong>Maklumat Warta Sekyen 4 Dan Notis Awam</strong></legend>
   
  	#parse("app/ppt/frmPPTHeader.jsp")

</fieldset>
  
<br/>     
     
<center>
   <fieldset>
     	<legend><strong>Maklumat Warta</strong></legend>
    
     <table width="100%" cellspacing="0">
     #foreach ( $warta in $WartaList )
     <input name="id_warta" type="hidden" value="$warta.id_warta">
     
 		 	<tr>
			  <td width="30%">Proses DiWarta Oleh</td>
  				<td width="70%">:&nbsp;<select name="proses_warta" class="mediumselect">
  				  <option value="1" $selectA>PTD</option>
  				  <option value="2" $selectB>PTG</option>
				  </select>
                  <input type="hidden" name="proses_warta" value="$warta.proses_warta"></td>
			</tr>
			
 		 	<tr>
 		 	  <td>No.Warta </td>
 		 	  <td>:&nbsp;<input type="text" size="23" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" name="no_warta" value="$warta.no_warta"></td> 
			</tr>
			
 		 	<tr>
 		 	  <td>Tarikh Warta</td>
 		 	  <td>:&nbsp;<input type="text" size="23" name="tarikh_warta" onblur="check_date(this)" value="$warta.tarikh_warta">
              <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('tarikh_warta',false,'dmy');" style="display:$Style2"></td>
			</tr>
			
#end
	 </table>
     </fieldset>
     </center>

<br/>
 
 	<table align="center">
 		<tr align="center"><td>
       <input name="cmdSimpan" type="button" value="Simpan" onclick="edit_Warta('$id_permohonan')">
       <input name="cmdBatal" type="button" value="Batal" onclick="batal('$id_permohonan')">
        </td></tr>
    </table>

       	<input name="skrin2" type="hidden" value="">
       	<input name="skrin3" type="hidden" value="">
       	<input name="skrin4" type="hidden" value="">
       	<input name="skrin5" type="hidden" value="">
  		<input type="hidden" name="id_permohonan" value="$id_permohonan">
  		
<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<script>
function edit_Warta(id_permohonan) {
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "Semak2";
	document.${formName}.skrin2.value = "Kemaskini";
	document.${formName}.skrin3.value = "Simpan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.UPTSek4NotisAwam";
	document.${formName}.submit();
}
function batal(id_permohonan) {
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "Semak2";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.UPTSek4NotisAwam";
	document.${formName}.submit();
}
</script>

<script type="text/javascript">
<!--

//-->
</script>
