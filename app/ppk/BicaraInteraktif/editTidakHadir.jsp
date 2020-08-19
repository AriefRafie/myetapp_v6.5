
#if($scrolPosition!="")
 <script>
 setPageLocation('$scrolPosition');
 </script>
#end

<tr id="rowTidakHadir_$ID_BITIDAKHADIR"   >
			   <td class="$rowCss"  align="center" valign="top" >$BIL</td>
               <td class="$rowCss"  align="left" valign="top" ><input type="text" style="text-transform:uppercase" name="NAMA_TIDAKHADIR_$ID_BITIDAKHADIR" class="fullwidth_input" id="NAMA_TIDAKHADIR_$ID_BITIDAKHADIR" value = "$viewTidakHadir.NAMA"  placeholder="Sila Masukkan Nama..."   maxlength="500"/></td>
                <td class="$rowCss"  align="left" valign="top" ><input  type="text" style="text-transform:uppercase" name="PENGENALAN_TIDAKHADIR_$ID_BITIDAKHADIR" class="fullwidth_input"  id="PENGENALAN_TIDAKHADIR_$ID_BITIDAKHADIR" value = "$viewTidakhadir.PENGENALAN" maxlength="20" /></td>	               
               <td class="$rowCss"  align="left" valign="top" >
                $dataHubungan
               <input  type="text" style="text-transform:uppercase" name="HUBUNGAN_TIDAKHADIR_$ID_BITIDAKHADIR" class="fullwidth_input"  id="HUBUNGAN_TIDAKHADIR_$ID_BITIDAKHADIR" value = "$viewTuruthadir.HUBUNGAN" maxlength="100" list="dataHubungan" /></td>	
               <td class="$rowCss"  align="center" valign="top" ><input  type="text" style="text-transform:uppercase" name="UMUR_TIDAKHADIR_$ID_BITIDAKHADIR" class="fullwidth_input"  id="UMUR_TIDAKHADIR_$ID_BITIDAKHADIR" value = "$viewTidakhadir.UMUR" maxlength="3" onkeydown="validateNumber(event);" /></td>	
               <td class="$rowCss"  align="left" valign="top" >
               $dataStatusOB
               <input  type="text" style="text-transform:uppercase" name="STATUS_TIDAKHADIR_$ID_BITIDAKHADIR" class="fullwidth_input"  id="STATUS_TIDAKHADIR_$ID_BITIDAKHADIR" value = "$viewTidakhadir.STATUS" maxlength="100" list="dataStatusOB" /></td>	
               <td class="$rowCss"  align="center" valign="top" >
              <input type="button" id="cmdUpdateTidakHadir" name="cmdUpdateTidakHadir" value="Simpan" onClick="simpanTidakHadir('$ID_BITIDAKHADIR','$ID_PERBICARAAN','$ID_PERMOHONAN',getPageLocation(),'$rowCss','$BIL')" >    
              
              
               </td>	
</tr>	

<script>
	document.getElementById('rowAddTidakHadir').style.display = "none";
</script>