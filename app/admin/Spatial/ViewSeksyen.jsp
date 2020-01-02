<tr id="div_viewPejabat$viewPejabat.ID_PEJABAT">
<td align="left" valign="top" colspan="14">
<fieldset>
<legend>Maklumat Seksyen </legend>
<table width="100%">
<!--<tr>
<td width="20%"   align="left" valign="top" >Kod Seksyen</td>
<td width="8%"   align="center" valign="top" >:</td>
<td width="60%"   align="left" valign="top" >$detailMukim.KOD_MUKIM
</td>
</tr>-->
<tr>
<td colspan="10">
<div id="div_CHECK_KOD_$ID_MUKIM" align="center">
<input type="hidden" id="CHECK_KOD_$ID_MUKIM" name="CHECK_KOD_$ID_MUKIM" value="$checkKod" ></div>
</td>
</tr>

<tr>
<td width="20%"   align="left" valign="top" >Kod UPI Seksyen</td>
<td width="8%"   align="center" valign="top" >:</td>
<td width="60%"   align="left" valign="top" >$detailSeksyen.KOD_SEKSYEN
</td>
</tr>

<tr>
<td colspan="10">
<div id="div_CHECK_KODUPI_$ID_MUKIM" align="center">
<input type="hidden" id="CHECK_KODUPI_$ID_MUKIM" name="CHECK_KODUPI_$ID_MUKIM" value="$checkKodUPI" ></div>
</td>
</tr>

<tr>
<td width="20%"   align="left" valign="top" >Nama Seksyen</td>
<td width="8%"   align="center" valign="top" >:</td>
<td width="60%"  align="left" valign="top">$detailSeksyen.NAMA_SEKSYEN</td>
</tr>

<tr>
<td width="20%"   align="left" valign="top" >Status</td>
<td width="8%"   align="center" valign="top" >:</td>
<td width="60%"  align="left" valign="top">
#if($detailSeksyen.FLAG_AKTIF=="Y" || $detailSeksyen.FLAG_AKTIF=="")
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

<input type="button" id="buttonSimpan" value="Kemaskini" onclick="doDivAjaxCall$formname('div_viewSeksyen$detailSeksyen.ID_SEKSYEN','editSeksyen','ID_NEGERI=$ID_NEGERI&ID_DAERAH=$ID_DAERAH&ID_MUKIM=$detailSeksyen.ID_MUKIM&ID_SEKSYEN=$detailSeksyen.ID_SEKSYEN');" >
<input type="button" id="buttonTutup" value="Tutup" onclick="doDivAjaxCall$formname('div_viewSeksyen$detailSeksyen.ID_SEKSYEN','batalBahagian','');" >


</td>
</tr>
</table>
</fieldset>
</td>
</tr>
