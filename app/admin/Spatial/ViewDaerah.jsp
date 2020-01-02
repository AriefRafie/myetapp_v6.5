<tr id="div_viewPejabat$viewPejabat.ID_PEJABAT">
<td align="left" valign="top" colspan="14">

<fieldset>
<legend>Maklumat Daerah </legend>
<table width="100%">
<tr>
<td width="20%"   align="left" valign="top" >Kod Daerah</td>
<td width="8%"   align="center" valign="top" >:</td>
<td width="60%"   align="left" valign="top" >$detailDaerah.KOD_DAERAH
</td>
</tr>
<tr>
<td colspan="10">
<div id="div_CHECK_KOD_$ID_DAERAH" align="center">
<input type="hidden" id="CHECK_KOD_$ID_DAERAH" name="CHECK_KOD_$ID_DAERAH" value="$checkKod" ></div>
</td>
</tr>

<tr>
<td width="20%"   align="left" valign="top" >Kod UPI Daerah</td>
<td width="8%"   align="center" valign="top" >:</td>
<td width="60%"   align="left" valign="top" >$detailDaerah.KOD_DAERAH_UPI
</td>
</tr>

<tr>
<td colspan="10">
<div id="div_CHECK_KODUPI_$ID_DAERAH" align="center">
<input type="hidden" id="CHECK_KODUPI_$ID_DAERAH" name="CHECK_KODUPI_$ID_DAERAH" value="$checkKodUPI" ></div>
</td>
</tr>

<tr>
<td width="20%"   align="left" valign="top" >Nama Daerah</td>
<td width="8%"   align="center" valign="top" >:</td>
<td width="60%"  align="left" valign="top">$detailDaerah.NAMA_DAERAH</td>
</tr>

<tr>
<td width="20%"   align="left" valign="top" >Status</td>
<td width="8%"   align="center" valign="top" >:</td>
<td width="60%"  align="left" valign="top">
#if($detailDaerah.FLAG_AKTIF=="Y" || $detailDaerah.FLAG_AKTIF=="")
AKTIF
#else
TIDAK AKTIF
#end
</td>
</tr>


<tr>
<td width="20%"   align="center" valign="top" ></td>
<td width="8%"   align="center" valign="top" ></td>
<td width="60%"  align="left" valign="top">

<input type="button" id="buttonSimpan" value="Kemaskini" onclick="doDivAjaxCall$formname('div_viewDaerah$ID_DAERAH','editDaerah','ID_NEGERI=$ID_NEGERI&ID_DAERAH=$ID_DAERAH');" >
<input type="button" id="buttonTutup" value="Tutup" onclick="doDivAjaxCall$formname('div_viewDaerah$ID_DAERAH','batalBahagian','');" >

</td>
</tr>
</table>
</fieldset>
</td>
</tr>
