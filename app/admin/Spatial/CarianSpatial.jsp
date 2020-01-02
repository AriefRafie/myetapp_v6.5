<table border="0" cellpadding="0" cellspacing="0" width="100%"> 
<tr>
    <td width="8" nowrap></td>
    <td>
<fieldset>
<legend>Senarai Kod UPI&nbsp;&nbsp;
<!-- <input type="button" id="cmdAddBahagian" name="cmdAddBahagian"   
onClick="doDivAjaxCall$formname('div_TambahBahagianHQ','editBahagian','ID_SEKSYEN=');" value="Tambah Bahagian" >  -->
</legend>
<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" >
<tr>
<td width="1%" valign="top" >
</td>
<td width="28%" valign="top" >
Carian Negeri<!-- (User Login / Nama / Bahagian / Unit / Negeri /Jawatan / Peranan Utama) --> 
</td>
<td width="1%" valign="top" >
:
</td>
<td width="70%" valign="top" >$!selectNegeri </td>
</tr>

<tr>
<td width="1%" valign="top" >
</td>
<td width="28%" valign="top" >
Carian Daerah  <!-- (User Login / Nama / Bahagian / Unit / Negeri /Jawatan / Peranan Utama) --></td>
<td width="1%" valign="top" >
:
</td>
<td width="70%" valign="top" >
<input type="text" id="carianDaerah" name="carianDaerah" style="text-transform:uppercase;" size="50" value="$!carianDaerah" onkeypress="if(event.keyCode == 13) { doDivAjaxCall$formname('div_senaraiUtama','carianDaerah','FlagCari=Y'); return false; }">	
</td>
</tr>

<tr>
<td width="1%" valign="top" >
</td>
<td width="28%" valign="top" >
Carian Mukim  <!-- (User Login / Nama / Bahagian / Unit / Negeri /Jawatan / Peranan Utama) --></td>
<td width="1%" valign="top" >
:
</td>
<td width="70%" valign="top" >
<input type="text" id="carianMukim" name="carianMukim" style="text-transform:uppercase;" size="50" value="$!carianMukim" onkeypress="if(event.keyCode == 13) { doDivAjaxCall$formname('div_senaraiUtama','carianMukim','FlagCari=Y'); return false; }">	
</td>
</tr>

<tr>
<td width="1%" valign="top" >
</td>
<td width="28%" valign="top" ></td>
<td width="1%" valign="top" ></td>
<td width="70%" valign="top" >
<!--<input type="button" id="cmdCariNegeri" name="cmdCariNegeri" value="Cari" onClick="$jquery('#cetakReport').val('Y'); doDivAjaxCall$formname('div_SenaraiNegeri','showSenaraiNegeri','FlagCari=Y');" >-->
<input type="button" id="cmdcarianUmum" name="cmdcarianUmum" value="Cari" onClick="$jquery('#cetakReport').val('Y');doDivAjaxCall$formname('div_senaraiUtama','showSenaraiNegeri','FlagCari=Y&cetakReport=Y');">
<input type="hidden" id="cetakReport" name="cetakReport" value="$cetakReport" >

<input style="display:none" type="button" id="cmdCetakPejabat" name="cmdCetakPejabat" value="Cetak Laporan"
onClick="$jquery('#cetakReport').val('Y');$jquery('#buttonM').val('Y');doDivAjaxCall$formname('SenaraiForPrint','cetakListKod','FlagCetak=Y&ID_NEGERI='+$jquery('#Form_id_negeri').val());"  />

</td>
</tr>

</table>
</fieldset>
</td>
</tr>
</table>

