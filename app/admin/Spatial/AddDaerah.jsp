<tr id="div_viewPejabat$viewPejabat.ID_PEJABAT">
<td align="left" valign="top" colspan="14">

<fieldset>
<legend>Maklumat Daerah </legend>
<table width="100%">
<tr>
<td width="20%"   align="left" valign="top" >Negeri</td>
<td width="8%"   align="center" valign="top" >:</td>
<td width="60%"   align="left" valign="top" >  
<select id="ID_NEGERI"  name="ID_NEGERI">	   
<option value = "" >SILA PILIH</option>
#foreach ( $ruj in $list_TBLRUJNEGERI )	
#set ( $selected_ruj = "" )
#if($ID_NEGERI==$ruj.ID)
#set ($selected_ruj = "selected" )
#end	
<option $selected_ruj value="$ruj.ID" >
$ruj.KETERANGAN
#end
</option>					
</select>
</td>
</tr>
<tr>
<td width="20%"   align="left" valign="top" >Kod Daerah</td>
<td width="8%"   align="center" valign="top" >:</td>
<td width="60%"   align="left" valign="top" >
<input name="KOD_DAERAH_$ID_DAERAH" id="KOD_DAERAH_$ID_DAERAH" type="text" size="2" maxlength="2" style="text-transform:uppercase;" 
onKeyUp="checkKodJenisPjbt(this.value,'$ID_NEGERI');" value="$detailDaerah.KOD_DAERAH">
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
<td width="60%"   align="left" valign="top" >
<input name="KOD_DAERAH_UPI_$ID_DAERAH" id="KOD_DAERAH_UPI_$ID_DAERAH" type="text" size="2" maxlength="2" style="text-transform:uppercase;" 
onKeyUp="checkKodUPI(this.value,'$ID_NEGERI');" value="$detailDaerah.KOD_DAERAH_UPI">
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
<td width="60%"  align="left" valign="top"><input size="50" maxlength="50" name="NAMA_DAERAH_$ID_DAERAH" id="NAMA_DAERAH_$ID_DAERAH" 
type="text" style="text-transform:uppercase;" value="$detailDaerah.NAMA_DAERAH"></td>
</tr>

<tr>
<td width="20%"   align="left" valign="top" >Status</td>
<td width="8%"   align="center" valign="top" >:</td>
<td width="60%"  align="left" valign="top">
<select id="FLAG_AKTIF_$ID_DAERAH" name="FLAG_AKTIF_$ID_DAERAH">
				#set ( $selected_aktif = "" )
				#set ( $selected_tidakaktif = "" )
							#if($detailDaerah.FLAG_AKTIF=="Y" || $detailDaerah.FLAG_AKTIF=="")
								#set ( $selected_aktif = "selected" )
							#else
								#set ( $selected_tidakaktif = "selected" )
							#end
				<option value="Y" $selected_aktif >AKTIF</option>
				<option value="N" $selected_tidakaktif >TIDAK AKTIF</option>
				</select>
                </td>
</tr>


<tr>
<td width="20%"   align="center" valign="top" ></td>
<td width="8%"   align="center" valign="top" ></td>
<td width="60%"  align="left" valign="top">

#if ($ID_DAERAH.equals(""))
<input type="button" id="buttonSimpan" value="Simpan" onclick="doDivAjaxCall$formname('div_viewDaerah$ID_DAERAH','saveDaerah','ID_NEGERI=$ID_NEGERI&ID_DAERAH=');" >
#else 
<input type="button" id="buttonSimpan" value="Simpan" onclick="doDivAjaxCall$formname('div_viewDaerah$ID_DAERAH','saveDaerah','ID_NEGERI=$ID_NEGERI&ID_DAERAH=$ID_DAERAH');" >
#end
<input type="button" id="buttonTutup" value="Tutup" onclick="doDivAjaxCall$formname('div_viewDaerah$ID_DAERAH','batalBahagian','');" >

</td>
</tr>
</table>
</fieldset>
</td>
</tr>
