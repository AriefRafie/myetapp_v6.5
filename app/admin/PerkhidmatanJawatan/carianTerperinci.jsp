<fieldset>
<legend>Carian Umum</legend>
<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" >
<tr>
<td width="1%" valign="top" >
</td>
<td width="28%" valign="top" >
Carian Umum<!-- (User Login / Nama / Bahagian / Unit / Negeri /Jawatan / Peranan Utama) --> 
</td>
<td width="1%" valign="top" >
:
</td>
<td width="70%" valign="top" >
<input type="text" size="50" id="carianTerperinci" name="carianTerperinci" value="$carianTerperinci" onkeypress="if(event.keyCode == 13) { doDivAjaxCall$formname('div_senaraiUtama','carianUtama','FlagCari=Y'); return false; }" >

<input type="button" id="cmdCari" name="cmdCari" value="Cari" onClick="if(validateCarian()==true){doDivAjaxCall$formname('div_senaraiUtama','carianUtama','FlagCari=Y');}" >
<input type="button" id="cmdBatalCari" name="cmdBatalCari" value="Reset" onClick="$jquery('#carianTerperinci').val('');doDivAjaxCall$formname('div_senaraiUtama','batalCarianUtama','FlagCari=Y');" >

</td>
</tr>
</table>
</fieldset>

