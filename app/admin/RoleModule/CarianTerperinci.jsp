<fieldset>
<legend>Carian&nbsp;&nbsp; 
<input type="button" id="cmdTambahGroup" name="cmdTambahGroup" value="Tambah Role Group" 
onClick="doDivAjaxCall$formname('div_rowGroup','viewGroup','GROUPUNIK=&MODE=EDIT');" >
</legend>
<table width="100%">
<tr>
<td width="1%" valign="top" >
</td>
<td width="28%" valign="top" >
Carian (Role / Description / Group)
</td>
<td width="1%" valign="top" >
:
</td>
<td width="70%" valign="top" >
<input type="text" size="50" id="carianTerperinci" name="carianTerperinci" value="$carianTerperinci" onkeypress="if(event.keyCode == 13) { doDivAjaxCall$formname('div_senaraiUtama','carianUtama',''); return false; }" >

<input type="button" id="cmdCariModule" name="cmdCariModule" value="Cari" onClick="if(validateCarian()==true){doDivAjaxCall$formname('div_senaraiUtama','carianUtama','');}" >
<input type="button" id="cmdBatalCariModule" name="cmdBatalCariModule" value="Reset" onClick="$jquery('#carianTerperinci').val('');doDivAjaxCall$formname('div_senaraiUtama','carianUtama','carianTerperinci=');" >

<input type="button" id="cmdCetakRole" name="cmdCetakRole" value="Cetak Peranan" onClick="doDivAjaxCall$formname('div_PrintRole','printRole','');" >

</td>
</tr>

 <tr>
 <td></td>
 <td></td>
	<td></td>
    <td>Laporan Statistik Peranan Pengguna  <img src="../img/images_stat.png" alt="" width="20" height="20" onclick="statsRole('Role')" /></td>
</tr>  

 <tr>
 <td></td>
 <td colspan="14">
<div id="div_statsRole" style="display:none">

#parse("app/admin/RoleModule/StatsModule.jsp")

</div>
</td>
</tr>

</table>
</fieldset>


 <script>   
  $jquery(document).ready(function () {
	  doDivAjaxCall$formname('div_senaraiUtama','carianUtama','carianTerperinci=');	  
  });  
  </script>



<div id="div_viewModul"></div>

<div id="div_PrintRole" style="display:none"></div>