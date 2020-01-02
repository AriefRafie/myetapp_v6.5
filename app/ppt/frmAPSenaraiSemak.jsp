<br/>
<center>

<table border="0" width="100%">
	<tr>
		<td align="left"><font color="red">*</font>Sila hantar permohonan <b>'Hardcopy'</b> anda mengikut senarai dibawah bagi tujuan proses permohonan.</td>
	</tr>
</table>

<br/>

	<fieldset>
		#if($IDsuburusan=='51')
		<legend><strong>Senarai Semak Seksyen 4</strong></legend>
		#else
		<legend><strong>Senarai Semak Seksyen 8</strong></legend>
		#end
		
			#if($IDsuburusan=='51')
			<table width="100%"  cellpadding="1" cellspacing="2" border="0">
				
				<tr class="table_header">
					<td width="3%" align="center"><b>No</b></td>
					<td width="82%"><b>Perkara</b></td>
					<td width="15%"><b>Syarat</b></td>
				</tr>
				
				<tr>
					<td valign="top" align="center">1</td>
					<td>Pelan Pengambilan Tanah yang lengkap.</td>
					<td>Mandatori</td>
				</tr>
				
				<tr>
					<td valign="top" align="center">2</td>
					<td>Pengesahan peruntukan yang mencukupi untuk membiayai kos pampasan 
                   		kerosakan akibat kerja-kerja ukur dan kajian tanah.</td>
					<td>Mandatori</td>
				</tr>
				
			</table>
			#end
			
			#if($IDsuburusan=='52')
			<table width="100%"  cellpadding="1" cellspacing="2" border="0">
				
				<tr class="table_header">
					<td width="3%" align="center"><b>No</b></td>
					<td width="82%"><b>Perkara</b></td>
					<td width="15%"><b>Syarat</b></td>
				</tr>
				
				<tr>
					<td valign="top" align="center">1</td>
					<td>Pelan Pengambilan Tanah yang lengkap.</td>
					<td>Mandatori</td>
				</tr>
				
				<tr>
					<td valign="top" align="center">2</td>
					<td>Sijil Carian Rasmi/ Persendirian yang terkini.</td>
					<td>Mandatori</td>
				</tr>
				
				<tr>
					<td valign="top" align="center">3</td>
					<td>Ulasan dari Jabatan-Jabatan Teknikal.</td>
					<td>Mandatori</td>
				</tr>
				
				<tr>
					<td valign="top" align="center">4</td>
					<td>Ulasan dari Jabatan Alam Sekitar.</td>
					<td>Mandatori</td>
				</tr>
				
				<tr>
					<td valign="top" align="center">5</td>
					<td>Persetujuan Jawatankuasa Pembangunan Daerah atau Jawatankuasa seumpamanya.</td>
					<td>Mandatori</td>
				</tr>
				
				<tr>
					<td valign="top" align="center">6</td>
					<td>Pengesahan peruntukan yang mencukupi termasuk kos penandaan dan lain-lain kos.</td>
					<td>Mandatori</td>
				</tr>
				
				<tr>
					<td valign="top" align="center">7</td>
					<td>Surat Perakuan segera (Borang I)</td>
					<td>Mandatori</td>
				</tr>
				
			</table>
			#end
			
	</fieldset>
	
			<table width=100%>
				<tr align="center">
					<td><input type="button" name="cmdOK" id="cmdOK" value="Seterusnya" onClick="ok()"></td>
				</tr>
			</table>
	
</center>

<script>
function ok() {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmAPDaftarSenarai";
	document.${formName}.command.value = "ok";
	document.${formName}.submit();
}
</script>