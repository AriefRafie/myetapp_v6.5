<table border="0" cellpadding="0" cellspacing="0" width="100%"> 
<tr>
    <td width="8" nowrap></td>
    <td>
<fieldset>
<legend>Carian Umum 
<input type="button" id="cmdAddBahagian" name="cmdAddBahagian"   
onClick="document.getElementById('div_TambahBahagianHQ').style.display='';doDivAjaxCall$formname('div_TambahBahagianHQ','editBahagian','ID_SEKSYEN=');" value="Tambah Bahagian" >
</legend>

<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" >
<tr>
<td width="1%" valign="top" >
</td>
<td width="28%" valign="top" >
Carian Umum<!-- (User Login / Nama / Bahagian / Unit / Negeri /Jawatan / Peranan Utama) --></td>
<td width="1%" valign="top" >
:
</td>
<td width="70%" valign="top" >
<input type="text" id="carianUmum" name="carianUmum" style="text-transform:uppercase;" size="50" value="$!carianUmum" 
	onkeypress="if(event.keyCode == 13) { doDivAjaxCall$formname('div_senaraiUtama','carianUmum','FlagCari=Y'); return false; }">	
<input type="hidden" id="cetakReport" name="cetakReport" value="$cetakReport" />

<input type="button" id="cmdCariPejabat" name="cmdCariPejabat" value="Cari" onClick="$jquery('#cetakReport').val('Y');doDivAjaxCall$formname('div_senaraiUtama','carianUmum','FlagCari=Y');" >
<input type="button" id="cmdBatalCariPejabat" name="cmdBatalCariPejabat" value="Reset" onClick="$jquery('#carianUmum').val('');" >

<input type="button" id="cmdCetak" name="cmdCetak" value="Cetak Laporan"
onClick="doDivAjaxCall$formname('SenaraiForPrint','printListBahagian','FlagCetak=Y');" style="display:none;" />

</td>


</tr>
</table>

<div id="div_TambahBahagianHQ" style="display:none"></div>

</fieldset>

</td>
</tr>

</table>

<script>   
$jquery(document).ready(function () 
{
doDivAjaxCall$formname('div_senaraiUtama','showSenaraiBahagian');	  
});  

</script>