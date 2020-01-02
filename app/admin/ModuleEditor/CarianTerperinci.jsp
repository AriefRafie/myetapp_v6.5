<fieldset>
<legend>Carian &nbsp;&nbsp;
<input type="button" id="BTNEDIT$MODULE_GROUP$MODULE_ID" name="BTNEDIT$MODULE_GROUP$MODULE_ID"   
onClick="doDivAjaxCall$formname('div_viewModul','editModule','MODULE_ID=&MODULE_GROUP=');" value="Tambah Modul" > 
</legend>
<table width="100%">
<tr>
<td width="1%" valign="top" >
</td>
<td width="28%" valign="top" >
Carian (Kumpulan / ID / Nama / Keterangan Modul)
</td>
<td width="1%" valign="top" >
:
</td>
<td width="70%" valign="top" >
<input type="text" size="50" id="carianTerperinci" name="carianTerperinci" value="$carianTerperinci" onkeypress="if(event.keyCode == 13) { doDivAjaxCall$formname('div_senaraiUtama','carianUtama',''); return false; }" >

<input type="button" id="cmdCariModule" name="cmdCariModule" value="Cari" onClick="if(validateCarian()==true){doDivAjaxCall$formname('div_senaraiUtama','carianUtama','');}" >
<input type="button" id="cmdBatalCariModule" name="cmdBatalCariModule" value="Reset" onClick="$jquery('#carianTerperinci').val('');doDivAjaxCall$formname('div_senaraiUtama','carianUtama','carianTerperinci=');" >

</td>
</tr>
</table>
</fieldset>
<script>   
  $jquery(document).ready(function () {
	  doDivAjaxCall$formname('div_senaraiUtama','carianUtama','carianTerperinci=');	  
  });  
  </script>

<div id="div_rowModul"></div>
<div id="div_viewModul"></div>

