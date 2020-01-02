<fieldset>
<legend>Carian Pejabat </legend>
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
<input type="text" size="50" id="carianTerperinci" name="carianTerperinci" value="$carianTerperinci" onkeypress="if(event.keyCode == 13) { doDivAjaxCall$formname('div_senaraiUtama','carianUmum',''); return false; }" >

<input type="button" id="cmdCariPengguna" name="cmdCariPengguna" value="Cari" onClick="if(validateCarian()==true){doDivAjaxCall$formname('div_senaraiUtama','carianUmum','');}" >
<input type="button" id="cmdBatalCariPengguna" name="cmdBatalCariPengguna" value="Reset" onClick="$jquery('#carianTerperinci').val('');doDivAjaxCall$formname('div_senaraiUtama','carianUmum','FlagCari=');" >


</td>
</tr>
</table>
</fieldset>

