
 #if($scrolPosition!="")
 <script>
 setPageLocation('$scrolPosition');
 </script>
#end
<tr id="rowTidakHadir_$ID_BITIDAKHADIR"   >
			   <td class="$rowCss"  align="center" valign="top" >$BIL</td>
               <td class="$rowCss"  align="left" valign="top" >$viewTidakhadir.NAMA</td>
               <td class="$rowCss"  align="left" valign="top" >$viewTidakhadir.PENGENALAN</td>	
               <td class="$rowCss"  align="left" valign="top" >$viewTidakhadir.HUBUNGAN</td>
               <td class="$rowCss"  align="center" valign="top" >$viewTidakhadir.UMUR</td>	
               <td class="$rowCss"  align="left" valign="top" >$viewTidakhadir.STATUS</td>
               <td class="$rowCss"  align="center" valign="top" >
               <a href="javascript:doDivAjaxCall$formname('rowTidakHadir_$ID_BITIDAKHADIR','editTidakHadir','ID_BITIDAKHADIR=$ID_BIKTIDAK&ID_PERBICARAAN=$ID_PERBICARAAN&ID_PERMOHONAN=$ID_PERMOHONAN&rowCss=$rowCss&BIL=$BIL&scrolPosition='+getPageLocation())"><img title="Kemaskini" src="../img/edit.gif" border="0"></a>
               <a href="javascript:doDivAjaxCall$formname('view_tidakhadir','delete_tidakhadir','ID_BITIDAKHADIR=$ID_BIKTIDAKHADIR&ID_PERBICARAAN=$ID_PERBICARAAN&ID_PERMOHONAN=$ID_PERMOHONAN&scrolPosition='+getPageLocation())"><img title="Hapus" src="../img/delete.gif" border="0"></a> 
				
               </td>	
</tr>	
<script>
	document.getElementById('rowAddTidakHadir').style.display = "";
</script>