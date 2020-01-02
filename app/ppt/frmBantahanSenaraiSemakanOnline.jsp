<p>&nbsp;</p>
<p>#parse("app/ppt/frmLabelSet.jsp")
  
</p>
<center>
	<fieldset id="top">

	#parse("app/ppt/frmPPTHeader.jsp")

	<br/>
	
	<fieldset>
	<legend>Senarai Semakan Yang Perlu Dibawa</legend>
	
		<table width="100%" border="0">
			<tr class="table_header">
				<td align="center" width="4%"><b>No</b></td>
				<td width="81%"><b>Perkara</b></td>
				<td width="15%" align="center"><b>Status</b></td>
			</tr>
			<tr>
				<td align="center">1</td>
				<td>Borang N.</td>
				<td align="center"><font color="red"><b>Mandatori</b></font></td>
			</tr>
			<tr>
				<td align="center" valign="top">2</td>
				<td valign="top">Deposit Bantahan.</td>
				<td valign="top" align="center"><font color="red"><b>Mandatori</b></font></td>
			</tr>
		</table>
	
	</fieldset>
	
	<table width="100%" border="0">
		<tr>
			<td>$!perhatian9</td>
		</tr>
	</table>
			
	<table width="100%" border="0">
		<tr align="center">
			<td>
				<!-- <input type="button" name="cmdPengesahanA" value="Cetak Pengesahan Permohonan" onClick="javascript:cetakPengesahan()"> -->
				<input type="button" name="cmdSah" id="cmdSah" value="Hantar Permohonan" onclick="javascript:hantar()" />
				<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:kembaliBantahan('$!id_fail','$!id_permohonan','$!id_status','$!id_hakmilik','1')">
			</td>
		</tr>
	</table>
	
	
	</fieldset>
</center>	



<input type="hidden" name="command2">
<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="id_fail" value="$!id_fail">
<input type="hidden" name="id_hakmilik" value="$!id_hakmilik">
<input type="hidden" name="id_bantahan" value="$!id_bantahan">
<input type="hidden" name="flag_bantahan" value="$!flag_bantahan">
<input type="hidden" name="id_agensi" id="id_agensi" value="$id_agensi" />
<input type="hidden" name="id_kementerian" id="id_kementerian" value="$id_kementerian" />


<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>


<script>
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});
function cetakPengesahan() {
    var url="../servlet/ekptg.report.ppt.PengesahanPermohonanOnline?idpermohonan="+document.${formName}.id_permohonan.value;
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
}
function hantar(){
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.command.value = "sahkan_permohonan";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanDaftarAPOnline";
	document.${formName}.submit();
}
function kembaliBantahan(id_fail,id_permohonan,id_status,id_hakmilik,flag_bantahan) {
	document.${formName}.id_fail.value = id_fail;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_status.value = id_status;
	document.${formName}.id_hakmilik.value = id_hakmilik;	
	document.${formName}.flag_bantahan.value = flag_bantahan;
	document.${formName}.command.value = "screenBorangN";	
	document.${formName}.method="POST";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanDaftarAPOnline";
	document.${formName}.submit();	
}
</script>
