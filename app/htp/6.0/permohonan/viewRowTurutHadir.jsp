
#if($scrolPosition!="")
 <script>
 setPageLocation('$scrolPosition');
 </script>
#end

<tr id="rowTurutHadir_$ID_BIKEHADIRAN"   >
			   <td class="$rowCss"  align="center" valign="top" >$BIL</td>
               <td class="$rowCss"  align="left" valign="top" >$viewTuruthadir.NAMA</td>
               <td class="$rowCss"  align="left" valign="top" >$viewTuruthadir.PENGENALAN</td>	
               <td class="$rowCss"  align="left" valign="top" >$viewTuruthadir.HUBUNGAN</td>
               <td class="$rowCss"  align="center" valign="top" >$viewTuruthadir.UMUR</td>	
               <td class="$rowCss"  align="left" valign="top" >$viewTuruthadir.STATUS</td>
               <td class="$rowCss"  align="center" valign="top" >
               <a href="javascript:doDivAjaxCall$formname('rowTurutHadir_$ID_BIKEHADIRAN','editTurutHadir','ID_BIKEHADIRAN=$ID_BIKEHADIRAN&ID_PERBICARAAN=$ID_PERBICARAAN&ID_PERMOHONAN=$ID_PERMOHONAN&rowCss=$rowCss&BIL=$BIL&scrolPosition='+getPageLocation())"><img title="Kemaskini" src="../img/edit.gif" border="0"></a>
               <a href="javascript:doDivAjaxCall$formname('view_turuthadir','delete_turuthadir','ID_BIKEHADIRAN=$ID_BIKEHADIRAN&ID_PERBICARAAN=$ID_PERBICARAAN&ID_PERMOHONAN=$ID_PERMOHONAN&scrolPosition='+getPageLocation())"><img title="Hapus" src="../img/delete.gif" border="0"></a> 
               
               
               <span id="spanIconKeteranganTurutHadir$ID_BIKEHADIRAN" style="display:none">
               <a href="javascript:doDivAjaxCall$formname('tdViewKeteranganTurutHadir$ID_BIKEHADIRAN','showKeteranganTurutHadir','NAMA=$viewTuruthadir.NAMA&ID_BIKEHADIRAN=$ID_BIKEHADIRAN&ID_PERMOHONANSIMATI=$ID_PERMOHONANSIMATI&ID_PERBICARAAN=$ID_PERBICARAAN&ID_PERMOHONAN=$ID_PERMOHONAN&scrolPosition='+getPageLocation())"><img title="Keterangan" src="../img/write.gif" border="0"></a>	   
               </span>
				
                <script type="text/javascript">
				if('$ID_BIKEHADIRAN' != "")
				{
					document.getElementById('spanIconKeteranganTurutHadir$ID_BIKEHADIRAN').style.display = "";
				}				
				</script>
               
                 
               </td>	
</tr>	
<script>
	document.getElementById('rowAddTurutHadir').style.display = "";
</script>