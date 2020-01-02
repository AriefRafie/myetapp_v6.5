<tr>
<td colspan="10">
<table>
<tr>
<td width="20%"   align="left" valign="top" >Kod Negeri</td>
<td width="8%"   align="center" valign="top" >:</td>
<td width="60%"   align="left" valign="top" >
<input name="KOD_NEGERI_" id="KOD_NEGERI_" type="text" size="2" maxlength="2" style="text-transform:uppercase;" 
onKeyUp="checkKodJenisPjbt(this.value,'$ID_NEGERI');" value="">
</td>
</tr>
<tr>
<td colspan="10">
<div id="div_CHECK_KOD_$ID_NEGERI" align="center">
<input type="hidden" id="CHECK_KOD_$ID_NEGERI" name="CHECK_KOD_$ID_NEGERI" value="$checkKod" ></div>
</td>
</tr>

<!--<tr>
<td width="20%"   align="left" valign="top" >Kod UPI Negeri</td>
<td width="8%"   align="center" valign="top" >:</td>
<td width="60%"   align="left" valign="top" >
<input name="KOD_NEGERI_UPI_$ID_NEGERI" id="KOD_NEGERI_UPI_$ID_NEGERI" type="text" size="2" maxlength="2" style="text-transform:uppercase;" 
onKeyUp="checkKodUPI(this.value,'$ID_NEGERI');" value="">
</td>
</tr>

<tr>
<td colspan="10">
<div id="div_CHECK_KODUPI_$ID_NEGERI" align="center">
<input type="hidden" id="CHECK_KODUPI_$ID_NEGERI" name="CHECK_KODUPI_$ID_NEGERI" value="$checkKodUPI" ></div>
</td>
</tr>-->

<tr>
<td width="20%"   align="left" valign="top" >Nama Negeri</td>
<td width="8%"   align="center" valign="top" >:</td>
<td width="60%"  align="left" valign="top"><input size="50" maxlength="50" name="NAMA_NEGERI_" id="NAMA_NEGERI_" 
type="text" style="text-transform:uppercase;" value=""></td>
</tr>
<tr>
<td width="20%"   align="center" valign="top" ></td>
<td width="8%"   align="center" valign="top" ></td>
<td width="60%"  align="left" valign="top">

#if ($ID_NEGERI.equals(""))
<input type="button" id="buttonSimpan" value="Simpan" onclick="hideDiv('div_TambahNegeri');doDivAjaxCall$formname('div_viewNegeri','saveNegeri','ID_NEGERI=');" >
#else 
<input type="button" id="buttonSimpan" value="Simpan" onclick="hideDiv('div_TambahNegeri');doDivAjaxCall$formname('div_viewNegeri','saveNegeri','ID_NEGERI=$ID_NEGERI');" >
#end
<input type="button" id="buttonTutup" value="Tutup" onclick="doDivAjaxCall$formname('saveNegeri','batalBahagian','');" >

</td>
</tr>
</table>
</td>
</tr>
<br>
<br>