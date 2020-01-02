#parse("app/ppt/frmLabelSet.jsp")

<center>
	<fieldset id="top">

	#parse("app/ppt/frmPPTHeader.jsp")

	<br/>
	
	<fieldset>
	<legend>Senarai Semakan Yang Perlu Dibawa</legend>
	
	#if($id_suburusan=="51")
		
		<table width="100%" border="0">
			<tr class="table_header">
				<td align="center" width="4%"><b>No</b></td>
				<td width="81%"><b>Perkara</b></td>
				<td width="15%" align="center"><b>Status</b></td>
			</tr>
			<tr>
				<td align="center">1</td>
				<td>Pelan Pengambilan Tanah yang lengkap.</td>
				<td align="center"><font color="red"><b>Mandatori</b></font></td>
			</tr>
			<tr>
				<td align="center" valign="top">2</td>
				<td valign="top">Pengesahan peruntukan yang mencukupi untuk membiayai kos pampasan kerosakan akibat kerja-kerja ukur dan kajian tanah.</td>
				<td valign="top" align="center"><font color="red"><b>Mandatori</b></font></td>
			</tr>
		</table>
		
	#else
	
		<table width="100%" border="0">
			<tr class="table_header">
				<td align="center" width="4%"><b>No</b></td>
				<td width="81%"><b>Perkara</b></td>
				<td width="15%" align="center"><b>Status</b></td>
			</tr>
			<tr>
				<td align="center">1</td>
				<td>Pelan Pengambilan Tanah yang lengkap.</td>
				<td align="center"><font color="red"><b>Mandatori</b></font></td>
			</tr>
			<tr>
				<td align="center">2</td>
				<td>Sijil Carian Rasmi/ Persendirian yang terkini.</td>
				<td align="center"><font color="red"><b>Mandatori</b></font></td>
			</tr>
			<tr>
				<td align="center">3</td>
				<td>Ulasan dari Jabatan-Jabatan Teknikal.</td>
				<td align="center"><b>-</b></td>
			</tr>
			<tr>
				<td align="center">4</td>
				<td>Ulasan dari Jabatan Alam Sekitar.</td>
				<td align="center"><b>-</b></td>
			</tr>
			<tr>
				<td align="center">5</td>
				<td>Persetujuan Jawatankuasa Pembangunan Daerah atau Jawatankuasa seumpamanya.</td>
				<td align="center"><b>-</b></td>
			</tr>
			<tr>
				<td align="center">6</td>
				<td>Pengesahan peruntukan yang mencukupi termasuk kos penandaan dan lain-lain kos.</td>
				<td align="center"><font color="red"><b>Mandatori</b></font></td>
			</tr>
			<tr>
				<td align="center">7</td>
				<td>Surat Perakuan segera (Borang I)</td>
				<td align="center"><b>-</b></td>
			</tr>
			<tr>
				<td align="center">8</td>
				<td>Surat Permohonan rasmi dari agensi</td>
				<td align="center"><b>-</b></td>
			</tr>
		</table>
		
	#end
	
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
				<input type="button" name="cmdHantar" value ="Hantar Permohonan" onClick="javascript:hantar('$!id_permohonan')">
				<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:kembaliPermohonan('$!id_permohonan')">
			</td>
		</tr>
	</table>
	
	
	</fieldset>
</center>	



<input type="hidden" name="command2">
<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="id_fail" value="$!id_fail">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>





<script>
window.onload = submitForm;
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});
function cetakPengesahan() {
    var url="../servlet/ekptg.report.ppt.PengesahanPermohonanOnline?idpermohonan="+document.${formName}.id_permohonan.value;
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
}
function hantar(id_permohonan) {
	if (!window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.ScreenLocation.value = "";
	document.${formName}.CursorPoint.value = "";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "semakPendaftaran";
	document.${formName}.command2.value = "hantar";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTOnline";
	document.${formName}.submit();
}
function kembaliPermohonan(id_permohonan) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.CursorPoint.value = "";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "semakPendaftaran";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTOnline";
	document.${formName}.submit();
}
function submitForm(){

	if('$ScreenLocation' != ""){
		window.location.hash='$ScreenLocation';
		goTo('$CursorPoint');
	}
}
</script>
