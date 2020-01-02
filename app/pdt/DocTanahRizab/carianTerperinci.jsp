<table width="100%">
<tr>
	<td width="5"></td>
	<td align="left"><strong>Dokumen Berkaitan Tanah Rizab Melayu</strong></td>
</tr>
</table>
<fieldset>

<legend>Carian Umum</legend>
<table width="100%" align="center" border="0" cellpadding="2" cellspacing="2" >
<tr>
<td width="1%" valign="top" >
</td>
<td width="28%" valign="top" >
Jenis Dokumen</td>
<td width="1%" valign="top" >
:
</td>
<td width="70%" valign="top" >
<input type="text" size="50" id="carianTerperinci" name="carianTerperinci" style="text-transform:uppercase;" value="$carianTerperinci" onkeypress="if(event.keyCode == 13) { if(validateCarian()==true){doDivAjaxCall$formname('div_senaraiUtama','carianUtama','FlagCari=Y'); return false; }}" >

</td>
</tr>
<tr>
<td></td>
<td>Nama Lampiran/Dokumen</td>
<td>:</td>
<td>
<input type="text" size="50" id="carianTerperinciLampiran" name="carianTerperinciLampiran" style="text-transform:uppercase;" value="$carianTerperinciLampiran" onkeypress="if(event.keyCode == 13) { if(validateCarian()==true){doDivAjaxCall$formname('div_senaraiUtama','carianUtama','FlagCari=Y'); return false; }}" >

</td>
</tr>
<tr>
<td></td>
<td></td>
<td></td>
<td>
<input type="button" id="cmdCariFolder" name="cmdCariFolder" value="Cari" onClick="if(validateCarian()==true){doDivAjaxCall$formname('div_senaraiUtama','carianUtama','FlagCari=Y');}" >
<input type="button" id="cmdBatalCariFolder" name="cmdBatalCariFolder" value="Batal Carian" onClick="$jquery('#carianTerperinci').val('');$jquery('#carianTerperinciLampiran').val('');doDivAjaxCall$formname('div_senaraiUtama','batalCarianUtama','FlagCari=Y');" >
</td>
</tr>
</table>
</fieldset>

