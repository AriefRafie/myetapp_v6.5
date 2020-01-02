<tr id="div_viewPejabat$viewPejabat.ID_PEJABAT">
<td align="left" valign="top" colspan="14">
<fieldset>
<legend>Maklumat Seksyen </legend>
<table width="100%">

<!--<tr>
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
</tr>-->

<!--<tr>
<td width="20%"   align="left" valign="top" >Daerah</td>
<td width="8%"   align="center" valign="top" >:</td>
<td width="60%"   align="left" valign="top" id="divDropdownDaerah">  
<select id="ID_DAERAH"  name="ID_DAERAH" onChange = "doDivAjaxCall$formname('divDropdownMukim','showListDaerah','ID_NEGERI='+$jquery('#ID_NEGERI').val());">	   
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
</tr>-->

<tr>
<td width="20%"   align="left" valign="top" >Mukim</td>
<td width="8%"   align="center" valign="top" >:</td>
<td width="60%"   align="left" valign="top" id="divDropdownMukim">  
<select id="ID_MUKIM_$ID_SEKSYEN"  name="ID_MUKIM_$ID_SEKSYEN" >	   
<option value = "" >SILA PILIH</option>
#foreach ( $ruj in $list_TBLRUJMUKIM )	
#set ( $selected_ruj = "" )
#if($ID_MUKIM==$ruj.ID)
#set ($selected_ruj = "selected" )
#end	
<option $selected_ruj value="$ruj.ID" >
$ruj.KETERANGAN
#end
</option>					
</select>
</td>
</tr>

<!--<tr>
<td width="20%"   align="left" valign="top" >Kod Seksyen</td>
<td width="8%"   align="center" valign="top" >:</td>
<td width="60%"   align="left" valign="top" >
<input name="KOD_SEKSYEN_$ID_SEKSYEN" id="KOD_SEKSYEN_$ID_SEKSYEN" type="text" size="2" maxlength="2" style="text-transform:uppercase;" 
onKeyUp="checkKodSeksyen(this.value,'$ID_SEKSYEN');" value="$detailSeksyen.KOD_SEKSYEN">
</td>
</tr>-->
<tr>
<td colspan="10">
<div id="div_CHECK_KOD_$ID_SEKSYEN" align="center">
<input type="hidden" id="CHECK_KOD_$ID_SEKSYEN" name="CHECK_KOD_$ID_SEKSYEN" value="$checkKod" ></div>
</td>
</tr>

<tr>
<td width="20%"   align="left" valign="top" >Kod UPI Seksyen</td>
<td width="8%"   align="center" valign="top" >:</td>
<td width="60%"   align="left" valign="top" >
<input name="KOD_SEKSYEN_UPI_$ID_SEKSYEN" id="KOD_SEKSYEN_UPI_$ID_SEKSYEN" type="text" size="2" maxlength="2" style="text-transform:uppercase;" 
onKeyUp="checkKodUPI(this.value,'$ID_SEKSYEN');" value="$detailSeksyen.KOD_SEKSYEN">
</td>
</tr>

<tr>
<td colspan="10">
<div id="div_CHECK_KODUPI_$ID_SEKSYEN" align="center">
<input type="hidden" id="CHECK_KODUPI_$ID_SEKSYEN" name="CHECK_KODUPI_$ID_SEKSYEN" value="$checkKodUPI" ></div>
</td>
</tr>

<tr>
<td width="20%"   align="left" valign="top" >Nama Seksyen</td>
<td width="8%"   align="center" valign="top" >:</td>
<td width="60%"  align="left" valign="top"><input size="50" maxlength="50" name="NAMA_SEKSYEN_$ID_SEKSYEN" id="NAMA_SEKSYEN_$ID_SEKSYEN" type="text" style="text-transform:uppercase;" value="$detailSeksyen.NAMA_SEKSYEN"></td>
</tr>

<tr>
<td width="20%"   align="left" valign="top" >Status</td>
<td width="8%"   align="center" valign="top" >:</td>
<td width="60%"  align="left" valign="top">
<select id="FLAG_AKTIF_$ID_MUKIM" name="FLAG_AKTIF_$ID_MUKIM">
				#set ( $selected_aktif = "" )
				#set ( $selected_tidakaktif = "" )
							#if($detailSeksyen.FLAG_AKTIF=="Y" || $detailSeksyen.FLAG_AKTIF=="")
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

#if ($ID_SEKSYEN.equals(""))
<input type="button" id="buttonSimpan" value="Simpan" onclick="doDivAjaxCall$formname('div_viewSeksyen$ID_SEKSYEN','saveSeksyen','ID_MUKIM=$ID_MUKIM&ID_SEKSYEN=');" >
#else 
<input type="button" id="buttonSimpan" value="Simpan" onclick="doDivAjaxCall$formname('div_viewSeksyen$ID_SEKSYEN','saveSeksyen','ID_MUKIM=$detailSeksyen.ID_MUKIM&ID_SEKSYEN=$detailSeksyen.ID_SEKSYEN');" >
#end
<input type="button" id="buttonTutup" value="Tutup" onclick="doDivAjaxCall$formname('div_viewSeksyen$detailSeksyen.ID_SEKSYEN','batalBahagian','');" >

</td>
</tr>
</table>
</fieldset>
</td>
</tr>
