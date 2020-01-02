<fieldset>
<legend>Carian</legend>
<table width="100%">
<tr>
<td width="1%" valign="top" >
</td>
<td width="28%" valign="top" >
Carian Terperinci<!-- (User Login / Nama / Bahagian / Unit / Negeri /Jawatan / Peranan Utama) --> 
</td>
<td width="1%" valign="top" >
:
</td>
<td width="70%" valign="top" >
<input type="text" size="50" id="carianTerperinci" name="carianTerperinci" value="$!carianTerperinci" onkeypress="if(event.keyCode == 13) { doDivAjaxCall$formname('div_senaraiUtama','carianUtama','FlagCari=Y'); return false; }" >

<input type="button" id="cmdCariPengguna" name="cmdCariPengguna" value="Cari" onClick="if(validateCarian()==true){doDivAjaxCall$formname('div_senaraiUtama','carianUtama','FlagCari=Y');}" >
<input type="button" id="cmdBatalCariPengguna" name="cmdBatalCariPengguna" value="Reset" onClick="$jquery('#carianTerperinci').val('');doDivAjaxCall$formname('div_senaraiUtama','batalCarianUtama','FlagCari=Y');" >

</td>
</tr>
</table>
</fieldset>

