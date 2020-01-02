
	   <tr id="div_ITEM_PENAMBAHBAIKAN_$getItemPenambahbaikkan.ID_PENAMBAHBAIKAN" >
	   <td class="underline_td_item_content"  align="center" valign="top" >
	   
	   </td>
	   <td class="underline_td_item_content"  align="center" valign="top" >
	   $BIL_TEMP
	    </td>
	   <td class="underline_td_item_content"  align="left" valign="top" >
	   $getItemPenambahbaikkan.PENAMBAHBAIKAN
	   </td>
	   
	   
	   <td class="underline_td_item_content"  align="left" valign="top" >
	   $getItemPenambahbaikkan.PIC
	   
	   
	   </td>
	   
	   
	   
	   <td class="underline_td_item_content"  align="left" valign="top" >
	   $getItemPenambahbaikkan.TARIKH_MULA
	   <br>
	   -
	   <br>
	   $getItemPenambahbaikkan.TARIKH_TARGET_SIAP
	   </td>
	   <td class="underline_td_item_content"  align="left" valign="top" >
	   
	    <table border="0" width="95%">
	   <tr>
	   <td width="89%" valign="top" align="left">
	   LOCAL DEVELOPMENT	   
	   </td>
	   <td width="1%" valign="top" align="center">
	   :
	   </td>
	   <td width="10%" valign="top" align="left">
	    #if($getItemPenambahbaikkan.FS_LOCAL==1)
	    <img title="Selesai"  src="../img/validyes.png" border="0">
	    #else
	    <img title="Belum Selesai"  src="../img/validno.png" border="0">
	    #end
	   
	   </td>
	   </tr>
	   <tr>
	   <td valign="top" align="left">
	   STG DEPLOYMENT	   
	   </td>
	   <td valign="top" align="center">
	   :
	   </td>
	   <td valign="top" align="left">
	   	#if($getItemPenambahbaikkan.FS_STG==1)
	    <img title="Selesai"  src="../img/validyes.png" border="0">
	    #else
	    <img title="Belum Selesai"  src="../img/validno.png" border="0">
	    #end
	   </td>
	   </tr>	
	   <tr>
	   <td valign="top" align="left">
	   LIVE DEPLOYMENT	   
	   </td>
	   <td valign="top" align="center">
	   :
	   </td>
	   <td valign="top" align="left">
	   	#if($getItemPenambahbaikkan.FS_DEV==1)
	    <img title="Selesai"  src="../img/validyes.png" border="0">
	    #else
	    <img title="Belum Selesai"  src="../img/validno.png" border="0">
	    #end
	   </td>
	   </tr>	      
	   </table>
	   </td>	
	   <td class="underline_td_item_content"  align="left" valign="top" >
	   
	   <table border="0" width="95%" >
	   
	   #if($getItemPenambahbaikkan.F_KIV==1)
	   <tr>
	   <td width="89%" valign="top" align="left">
	   STATUS KIV
	   </td>
	   <td width="1%" valign="top" align="center">
	   :
	   </td>
	   <td width="10%" valign="top" align="left">
	   	#if($getItemPenambahbaikkan.F_KIV==1)
	    <img title="Selesai"  src="../img/validyes.png" border="0">
	    #else
	    <img title="Belum Selesai"  src="../img/validno.png" border="0">
	    #end
	   </td>
	   </tr>
	   #end
	   <tr>
	   <td valign="top" align="left" colspan="3">
	   $getItemPenambahbaikkan.CATATAN	   
	   </td>
	   </tr>	
	   </table>
	   </td>	
	   
	      
	   <td class="underline_td_item_content"  align="center" valign="top" >
	   <a href="javascript:doDivAjaxCall$formname('div_ITEM_PENAMBAHBAIKAN_$getItemPenambahbaikkan.ID_PENAMBAHBAIKAN','edit_penambahbaikkan','ID_PENAMBAHBAIKAN=$getItemPenambahbaikkan.ID_PENAMBAHBAIKAN&BIL_TEMP=$BIL_TEMP&fromStatististik=$fromStatististik');"><img title="Kemaskini" src="../img/edit.gif" border="0"></a>
	   #if($fromStatististik=="N")
	   <a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('div_PENAMBAHBAIKKAN_$getItemPenambahbaikkan.ID_SUBMODUL','delete_penambahbaikkan','ID_PENAMBAHBAIKAN=$getItemPenambahbaikkan.ID_PENAMBAHBAIKAN&BIL_TEMP=$BIL_TEMP&ID_SUBMODUL=$getItemPenambahbaikkan.ID_SUBMODUL');}"><img title="Hapus" src="../img/delete.gif" border="0"></a>
	   #end
	   </td>	   
	   </tr>
	   
	 
	   
              