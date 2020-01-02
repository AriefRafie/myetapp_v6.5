<table border="0" cellpadding="0" cellspacing="0" width="100%"> 
<tr>
    <td width="8" nowrap></td>
    <td>
<fieldset>
<legend>Carian Umum Kod Spatial
<!-- <input type="button" id="cmdAddBahagian" name="cmdAddBahagian"   
onClick="doDivAjaxCall$formname('div_TambahBahagianHQ','editBahagian','ID_SEKSYEN=');" value="Tambah Bahagian" >  -->
</legend>
<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" >
<tr>
<td width="1%" valign="top" >
</td>
<td width="28%" valign="top" >
Carian <!-- (User Login / Nama / Bahagian / Unit / Negeri /Jawatan / Peranan Utama) --> 
</td>
<td width="1%" valign="top" >
:
</td>
<td width="70%" valign="top" ><input type="text" id="carianUmum" name="carianUmum" style="text-transform:uppercase;" size="50" value="$!carianUmum" onkeypress="if(event.keyCode == 13) { doDivAjaxCall$formname('div_senaraiUtama','showCarianUmum','FlagCari=Y&cetakReport='); return false; }">

<input type="button" id="cmdcarianUmum" name="cmdcarianUmum" value="Cari" onClick="$jquery('#cetakReport').val('Y'); doDivAjaxCall$formname('SenaraiNegeri','showCarianUmum','FlagCari=Y&cetakReport=Y');" >
<input type="hidden" id="cetakReport" name="cetakReport" value="$cetakReport" >

<input type="button" id="cmdCetakPejabat" name="cmdCetakPejabat" value="Cetak Laporan"
onClick="$jquery('#cetakReport').val('Y');$jquery('#buttonM').val('Y');doDivAjaxCall$formname('SenaraiForPrint','cetakListKod','FlagCetak=Y&ID_NEGERI=');"  />

</td>
</tr>

<!--<tr>
<td width="1%" valign="top" >
</td>
<td width="28%" valign="top" >
</td>
<td width="1%" valign="top" >
</td>
<td width="70%" valign="top" id="test" ><br /> <a href="#" onClick="showCarianTerperinci();document.getElementById('cmdcarianUmum').style.display='none';document.getElementById('test').style.display='none';"><font color="#000000"><u>Carian Terperinci>></u></font></a></td>
</tr>-->

</table>
</fieldset>

</td>
</tr>

<tr id="tableCT">
<td width="8" nowrap></td>
<td>
</td>
</tr>
</table>
