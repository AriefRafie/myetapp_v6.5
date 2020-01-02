<fieldset>
  <legend>Permohonan Seksyen 17 Online</legend>
<table width="100%" cellspacing="1" cellpadding="1" border="0">
	
  <tr class="table_header">
  	
  	<td width="12%"align="center">Tarikh Mohon</td>	
	<td width="14%" align="center">No Rujukan Online</td>
  	<td width="15%">Status Pergerakan Fail</td>
  	<td width="10%"></td>
  </tr>	
  #foreach ($senarai in $DATA )
  	
  	<td width="12%"align="center">$!senarai.tarikhMohon</td>	
	<td width="14%" align="center">$!senarai.noFail</td>
  	<td width="30%">$!senarai.status</td>
  	<td width="10%"><input type="button" value="Borang P" onclick="kemaskiniBorangP()"></td>
  	<input type="hidden" name="typez" value="online"/>
	<input type="hidden" name="nopermohonan" value="$!senarai.noFail"/>
  #end
</table>
</fieldset>
<p>
<font color=red>
<b>PERHATIAN:</b><br>

Permohonan anda belum disahkan. Sila klik butang Borang P untuk mengemaskini maklumat permohonan anda.
</font>
</p>
<input type="hidden" name="typez"/>
<input type="hidden" name="nopermohonan"/>
<script>

function kemaskiniBorangP(input){
	alert(input);
	document.${formname}.method="post";
	document.${formname}.nopermohonan.value=id;
	document.${formname}.typez.value="online";		
	//doAjaxCall${formName}("check_kp17","typez=online&nopermohonan="+id);
	doAjaxCall${formName}("check_kp17");
	document.${formname}.action="$EkptgUtil.getTabID('Menu','online')?_portal_module=ekptg.view.ppk.FrmPrmhnnStatusPengunaOnline";
	document.${formName}.submit();
}
</script>