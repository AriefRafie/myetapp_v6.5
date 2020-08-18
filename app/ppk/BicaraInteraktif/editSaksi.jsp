
#if($scrolPosition!="")
 <script>
 setPageLocation('$scrolPosition');
 </script>
#end

<tr id="rowSaksi_$ID_BIKEHADIRAN"   >
			   <td class="$rowCss"  align="center" valign="top" >$BIL</td>
               <td class="$rowCss"  align="left" valign="top" ><input type="text" style="text-transform:uppercase" name="NAMA_SAKSI_$ID_BIKEHADIRAN" class="fullwidth_input" id="NAMA_SAKSI_$ID_BIKEHADIRAN" value = "$viewSaksi.NAMA"  placeholder="Sila Masukkan Nama..."   maxlength="500"/></td>
                <td class="$rowCss"  align="left" valign="top" ><input  type="text" style="text-transform:uppercase" name="PENGENALAN_SAKSI_$ID_BIKEHADIRAN" class="fullwidth_input"  id="PENGENALAN_SAKSI_$ID_BIKEHADIRAN" value = "$viewSaksi.PENGENALAN" maxlength="20" /></td>	               
               <td class="$rowCss"  align="left" valign="top" >
                $dataHubungan
               <input  type="text" style="text-transform:uppercase" name="HUBUNGAN_SAKSI_$ID_BIKEHADIRAN" class="fullwidth_input"  id="HUBUNGAN_SAKSI_$ID_BIKEHADIRAN" value = "$viewSaksi.HUBUNGAN" maxlength="100" list="dataHubungan" /></td>	
               <td class="$rowCss"  align="center" valign="top" ><input  type="text" style="text-transform:uppercase" name="UMUR_SAKSI_$ID_BIKEHADIRAN" class="fullwidth_input"  id="UMUR_SAKSI_$ID_BIKEHADIRAN" value = "$viewSaksi.UMUR" maxlength="3" onkeydown="validateNumber(event);" /></td>	
               <td class="$rowCss"  align="left" valign="top" >
               $dataStatusOB
               <input  type="text" style="text-transform:uppercase" name="STATUS_SAKSI_$ID_BIKEHADIRAN" class="fullwidth_input"  id="STATUS_SAKSI_$ID_BIKEHADIRAN" value = "$viewSaksi.STATUS" maxlength="100" list="dataStatusOB" /></td>	
               <td class="$rowCss"  align="center" valign="top" >
              <input type="button" id="cmdUpdateSaksi" name="cmdUpdateSaksi" value="Simpan" onClick="simpanSaksi('$ID_BIKEHADIRAN','$ID_PERBICARAAN','$ID_PERMOHONAN',getPageLocation(),'$rowCss','$BIL')" >    
              
              
               </td>	
</tr>	

<script>
	document.getElementById('rowAddSaksi').style.display = "none";
</script>