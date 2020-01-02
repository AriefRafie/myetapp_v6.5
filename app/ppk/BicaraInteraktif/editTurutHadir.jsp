
#if($scrolPosition!="")
 <script>
 setPageLocation('$scrolPosition');
 </script>
#end

<tr id="rowTurutHadir_$ID_BIKEHADIRAN"   >
			   <td class="$rowCss"  align="center" valign="top" >$BIL</td>
               <td class="$rowCss"  align="left" valign="top" ><input type="text" style="text-transform:uppercase" name="NAMA_TURUTHADIR_$ID_BIKEHADIRAN" class="fullwidth_input" id="NAMA_TURUTHADIR_$ID_BIKEHADIRAN" value = "$viewTuruthadir.NAMA"  placeholder="Sila Masukkan Nama..."   maxlength="500"/></td>
                <td class="$rowCss"  align="left" valign="top" ><input  type="text" style="text-transform:uppercase" name="PENGENALAN_TURUTHADIR_$ID_BIKEHADIRAN" class="fullwidth_input"  id="PENGENALAN_TURUTHADIR_$ID_BIKEHADIRAN" value = "$viewTuruthadir.PENGENALAN" maxlength="20" /></td>	               
               <td class="$rowCss"  align="left" valign="top" >
                $dataHubungan
               <input  type="text" style="text-transform:uppercase" name="HUBUNGAN_TURUTHADIR_$ID_BIKEHADIRAN" class="fullwidth_input"  id="HUBUNGAN_TURUTHADIR_$ID_BIKEHADIRAN" value = "$viewTuruthadir.HUBUNGAN" maxlength="100" list="dataHubungan" /></td>	
               <td class="$rowCss"  align="center" valign="top" ><input  type="text" style="text-transform:uppercase" name="UMUR_TURUTHADIR_$ID_BIKEHADIRAN" class="fullwidth_input"  id="UMUR_TURUTHADIR_$ID_BIKEHADIRAN" value = "$viewTuruthadir.UMUR" maxlength="3" onkeydown="validateNumber(event);" /></td>	
               <td class="$rowCss"  align="left" valign="top" >
               $dataStatusOB
               <input  type="text" style="text-transform:uppercase" name="STATUS_TURUTHADIR_$ID_BIKEHADIRAN" class="fullwidth_input"  id="STATUS_TURUTHADIR_$ID_BIKEHADIRAN" value = "$viewTuruthadir.STATUS" maxlength="100" list="dataStatusOB" /></td>	
               <td class="$rowCss"  align="center" valign="top" >
              <input type="button" id="cmdUpdateTurutHadir" name="cmdUpdateTurutHadir" value="Simpan" onClick="simpanTurutHadir('$ID_BIKEHADIRAN','$ID_PERBICARAAN','$ID_PERMOHONAN',getPageLocation(),'$rowCss','$BIL')" >    
              
              
               </td>	
</tr>	

<script>
	document.getElementById('rowAddTurutHadir').style.display = "none";
</script>