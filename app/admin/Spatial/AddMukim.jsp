<tr id="div_viewPejabat$viewPejabat.ID_PEJABAT">
<td align="left" valign="top" colspan="14">
<fieldset>
<legend>Maklumat Mukim </legend>
<table width="100%">

<tr>
<td width="20%"   align="left" valign="top" >Negeri</td>
<td width="8%"   align="center" valign="top" >:</td>
<td width="60%"   align="left" valign="top" >  
<select id="ID_NEGERI"  name="ID_NEGERI" onChange = "doDivAjaxCall$formname('divDropdownDaerah','showListDaerah','ID_NEGERI='+$jquery('#ID_NEGERI').val());">	   
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
<td width="20%"   align="left" valign="top" >Daerah</td>
<td width="8%"   align="center" valign="top" >:</td>
<td width="60%"   align="left" valign="top" id="divDropdownDaerah">  
<select id="ID_DAERAH"  name="ID_DAERAH" onChange = "doDivAjaxCall$formname('divDropdownMukim','showListMukim','ID_DAERAH='+$jquery('#ID_DAERAH').val());"> 
<option value = "" >SILA PILIH</option>
#foreach ( $ruj in $list_TBLRUJDAERAH )	
#set ( $selected_ruj = "" )
#if($ID_DAERAH==$ruj.ID)
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
<td width="20%"   align="left" valign="top" >Kod Mukim</td>
<td width="8%"   align="center" valign="top" >:</td>
<td width="60%"   align="left" valign="top" >
<input name="KOD_MUKIM_$ID_MUKIM" id="KOD_MUKIM_$ID_MUKIM" type="text" size="2" maxlength="2" style="text-transform:uppercase;" 
onKeyUp="checkKodMukim(this.value,'$ID_MUKIM');" value="$detailMukim.KOD_MUKIM">
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
<td width="60%"   align="left" valign="top" >
<input name="KOD_MUKIM_UPI_$ID_MUKIM" id="KOD_MUKIM_UPI_$ID_MUKIM" type="text" size="2" maxlength="2" style="text-transform:uppercase;" 
onKeyUp="checkKodUPI(this.value,'$ID_MUKIM');" value="$detailMukim.KOD_MUKIM_UPI">
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
<td width="60%"  align="left" valign="top"><input size="50" maxlength="50" name="NAMA_MUKIM_$ID_MUKIM" id="NAMA_MUKIM_$ID_MUKIM" type="text" style="text-transform:uppercase;" value="$detailMukim.NAMA_MUKIM"></td>
</tr>

<tr>
<td width="20%"   align="left" valign="top" >Status</td>
<td width="8%"   align="center" valign="top" >:</td>
<td width="60%"  align="left" valign="top">
<select id="FLAG_AKTIF_$ID_MUKIM" name="FLAG_AKTIF_$ID_MUKIM">
				#set ( $selected_aktif = "" )
				#set ( $selected_tidakaktif = "" )
							#if($detailMukim.FLAG_AKTIF=="Y" || $detailMukim.FLAG_AKTIF=="")
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

#if ($ID_MUKIM.equals(""))
<input type="button" id="buttonSimpan" value="Simpan" onclick="doDivAjaxCall$formname('div_viewMukim$ID_DAERAH','saveMukim','ID_DAERAH=$ID_DAERAH&ID_MUKIM=');" >
#else 
<input type="button" id="buttonSimpan" value="Simpan" onclick="doDivAjaxCall$formname('div_viewMukim$ID_DAERAH','saveMukim','ID_NEGERI=$ID_NEGERI&ID_DAERAH=$ID_DAERAH&ID_MUKIM=$detailMukim.ID_MUKIM');" >
#end
<input type="button" id="buttonTutup" value="Tutup" onclick="doDivAjaxCall$formname('div_viewMukim$detailMukim.ID_MUKIM','batalBahagian','');" >

</td>
</tr>
</table>
</fieldset>
</td>
</tr>
