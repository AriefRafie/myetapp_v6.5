
#if($scrolPosition!="")
 <script>
 setPageLocation('$scrolPosition');
 </script>
#end

<tr id="rowSaksi_$ID_BIKEHADIRAN"   >
			   <td class="$rowCss"  align="center" valign="top" >$BIL</td>
               <td class="$rowCss"  align="left" valign="top" >$viewSaksi.NAMA</td>
               <td class="$rowCss"  align="left" valign="top" >$viewSaksi.PENGENALAN</td>	
               <td class="$rowCss"  align="left" valign="top" >$viewSaksi.HUBUNGAN</td>
               <td class="$rowCss"  align="center" valign="top" >$viewSaksi.UMUR</td>	
               <td class="$rowCss"  align="left" valign="top" >$viewSaksi.STATUS</td>
               <td class="$rowCss"  align="center" valign="top" >
               <a href="javascript:doDivAjaxCall$formname('rowSaksi_$ID_BIKEHADIRAN','editSaksi','ID_BIKEHADIRAN=$ID_BIKEHADIRAN&ID_PERBICARAAN=$ID_PERBICARAAN&ID_PERMOHONAN=$ID_PERMOHONAN&rowCss=$rowCss&BIL=$BIL&scrolPosition='+getPageLocation())"><img title="Kemaskini" src="../img/edit.gif" border="0"></a>
               <a href="javascript:doDivAjaxCall$formname('view_saksi','delete_saksi','ID_BIKEHADIRAN=$ID_BIKEHADIRAN&ID_PERBICARAAN=$ID_PERBICARAAN&ID_PERMOHONAN=$ID_PERMOHONAN&scrolPosition='+getPageLocation())"><img title="Hapus" src="../img/delete.gif" border="0"></a> 
               
               
               <span id="spanIconKeteranganSaksi$ID_BIKEHADIRAN" style="display:none">
               <a href="javascript:doDivAjaxCall$formname('tdViewKeteranganSaksi$ID_BIKEHADIRAN','showKeteranganSaksi','NAMA=$viewSaksi.NAMA&ID_BIKEHADIRAN=$ID_BIKEHADIRAN&ID_PERMOHONANSIMATI=$ID_PERMOHONANSIMATI&ID_PERBICARAAN=$ID_PERBICARAAN&ID_PERMOHONAN=$ID_PERMOHONAN&scrolPosition='+getPageLocation())"><img title="Keterangan" src="../img/write.gif" border="0"></a>	   
               </span>
				
                <script type="text/javascript">
				if('$ID_BIKEHADIRAN' != "")
				{
					document.getElementById('spanIconKeteranganSaksi$ID_BIKEHADIRAN').style.display = "";
				}				
				</script>
               
                 
               </td>	
</tr>	
<script>
	document.getElementById('rowAddSaksi').style.display = "";
</script>