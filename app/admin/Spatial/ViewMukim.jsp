<tr id="div_viewPejabat$viewPejabat.ID_PEJABAT">
<td align="left" valign="top" colspan="14">
<fieldset>
<legend>Maklumat Mukim </legend>
<table width="100%">
<tr>
<td width="20%"   align="left" valign="top" >Kod Mukim</td>
<td width="8%"   align="center" valign="top" >:</td>
<td width="60%"   align="left" valign="top" >$detailMukim.KOD_MUKIM
</td>
</tr>
<tr>
<td colspan="10">
<div id="div_CHECK_KOD_$ID_MUKIM" align="center">
<input type="hidden" id="CHECK_KOD_$ID_MUKIM" name="CHECK_KOD_$ID_MUKIM" value="$checkKod" ></div>
</td>
</tr>

<tr>
<td width="20%"   align="left" valign="top" >Kod UPI Mukim</td>
<td width="8%"   align="center" valign="top" >:</td>
<td width="60%"   align="left" valign="top" >$detailMukim.KOD_MUKIM_UPI
</td>
</tr>

<tr>
<td colspan="10">
<div id="div_CHECK_KODUPI_$ID_MUKIM" align="center">
<input type="hidden" id="CHECK_KODUPI_$ID_MUKIM" name="CHECK_KODUPI_$ID_MUKIM" value="$checkKodUPI" ></div>
</td>
</tr>

<tr>
<td width="20%"   align="left" valign="top" >Nama Mukim</td>
<td width="8%"   align="center" valign="top" >:</td>
<td width="60%"  align="left" valign="top">$detailMukim.NAMA_MUKIM</td>
</tr>

<tr>
<td width="20%"   align="left" valign="top" >Status</td>
<td width="8%"   align="center" valign="top" >:</td>
<td width="60%"  align="left" valign="top">
#if($detailMukim.FLAG_AKTIF=="Y" || $detailMukim.FLAG_AKTIF=="")
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

<input type="button" id="buttonSimpan" value="Kemaskini" onclick="doDivAjaxCall$formname('div_viewMukim$detailMukim.ID_MUKIM','editMukim','ID_NEGERI=$ID_NEGERI&ID_DAERAH=$ID_DAERAH&ID_MUKIM=$detailMukim.ID_MUKIM');" >
<input type="button" id="buttonTutup" value="Tutup" onclick="doDivAjaxCall$formname('div_viewMukim$detailMukim.ID_MUKIM','batalBahagian','');" >

</td>
</tr>
</table>
</fieldset>
</td>
</tr>
