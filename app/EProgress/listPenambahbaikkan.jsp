<input type="hidden" id="openSectionPenambahbaikan_$ID_SUBMODUL" name="openSectionPenambahbaikan_$ID_SUBMODUL" value="$openSectionPenambahbaikan" >
	   
<table class="classFade"  border="0" cellspacing="1" cellpadding="1" width="100%" >  
#set($BIL_LAST = 0)	
 
#if($listPenambahbaikkan.size()>0) 
	  
	   <tr class="row2" >
	   <td class="underline_td_item" width="5%" align="left"></td>
	   <td class="underline_td_item" width="5%" align="center">BIL</td>
	   <td class="underline_td_item" width="15%" align="left">PENAMBAHBAIKKAN</td>
	   <td class="underline_td_item" width="15%" align="left">PIC</td>
	   <td class="underline_td_item" width="15%" align="left">TARIKH</td>
	   <td class="underline_td_item" width="15%" align="left">STATUS SELESAI</td>
	   <td class="underline_td_item" width="15%" align="left">CATATAN</td>
	   <td class="underline_td_item" width="15%" align="center">TINDAKAN</td>	   
	   </tr> 
	   #foreach($sp in $listPenambahbaikkan)
	   #set($BIL_LAST = $BIL_LAST + 1)	
	   <tr id="div_ITEM_PENAMBAHBAIKAN_$sp.ID_PENAMBAHBAIKAN"  >
	   <td class="underline_td_item_content"  align="center" valign="top" ></td>
	   <td class="underline_td_item_content"  align="center" valign="top" >$sp.BIL</td>
	   <td class="underline_td_item_content"  align="left" valign="top" >$sp.PENAMBAHBAIKAN</td>
	   <td class="underline_td_item_content"  align="left" valign="top" >$sp.PIC</td>
	   
	   
	   <td class="underline_td_item_content"  align="left" valign="top" >
	   $sp.TARIKH_MULA
	   <br>
	   -
	   <br>
	   $sp.TARIKH_TARGET_SIAP
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
	    #if($sp.FS_LOCAL==1)
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
	   	#if($sp.FS_STG==1)
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
	   	#if($sp.FS_DEV==1)
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
	   
	   #if($sp.F_KIV==1)
	   <tr>
	   <td width="89%" valign="top" align="left">
	   STATUS KIV
	   </td>
	   <td width="1%" valign="top" align="center">
	   :
	   </td>
	   <td width="10%" valign="top" align="left">
	   	#if($sp.F_KIV==1)
	    <img title="Selesai"  src="../img/validyes.png" border="0">
	    #else
	    <img title="Belum Selesai"  src="../img/validno.png" border="0">
	    #end
	   </td>
	   </tr>
	   #end
	   <tr>
	   <td valign="top" align="left" colspan="3">
	   $sp.CATATAN	   
	   </td>
	   </tr>	
	   </table>
	   </td>	   
	   <td class="underline_td_item_content"  align="center" valign="top" >
	   <a href="javascript:doDivAjaxCall$formname('div_ITEM_PENAMBAHBAIKAN_$sp.ID_PENAMBAHBAIKAN','edit_penambahbaikkan','ID_PENAMBAHBAIKAN=$sp.ID_PENAMBAHBAIKAN&BIL_TEMP=$sp.BIL&fromStatististik=N');"><img title="Kemaskini" src="../img/edit.gif" border="0"></a>
	   <a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('div_PENAMBAHBAIKKAN_$ID_SUBMODUL','delete_penambahbaikkan','ID_PENAMBAHBAIKAN=$sp.ID_PENAMBAHBAIKAN&BIL_TEMP=$sp.BIL&ID_SUBMODUL=$sp.ID_SUBMODUL');} "><img title="Hapus"  src="../img/delete.gif" border="0"></a>
	   </td>	   
	   </tr>
	  	 
	   #end
	   <tr id="div_PENAMBAHBAIKAN_ADD_$ID_SUBMODUL" >
	   <td class="underline_td_item_content"  align="center" valign="top" ></td>
	   <td class="underline_td_item_content"  align="center" valign="top" >&nbsp;</td>
	   <td class="underline_td_item_content"  align="left" valign="top" ></td>
	   <td class="underline_td_item_content"  align="left" valign="top" ></td>
	   <td class="underline_td_item_content"  align="left" valign="top" ></td>
	   <td class="underline_td_item_content"  align="left" valign="top" ></td>	
	   <td class="underline_td_item_content"  align="left" valign="top" ></td>	   
	   <td class="underline_td_item_content"  align="center" valign="top" >
	   #set($BIL_LAST = $BIL_LAST + 1)
	   <a href="javascript:doDivAjaxCall$formname('div_PENAMBAHBAIKAN_ADD_$ID_SUBMODUL','add_penambahbaikkan','ID_SUBMODUL=$ID_SUBMODUL&BIL_TEMP=$BIL_LAST');"><img title="Tambah" src="../img/plus.gif" border="0"></a>
	   </td>	   
	   </tr>	
	   
 #else
 	#set($BIL_LAST = $BIL_LAST + 1) 
 	<tr class="row2" >
	   <td class="underline_td_item" width="5%" align="left"></td>
	   <td class="underline_td_item" width="5%" align="center">BIL</td>
	   <td class="underline_td_item" width="15%" align="left">PENAMBAHBAIKKAN</td>
	   <td class="underline_td_item" width="15%" align="left">PIC</td>
	   <td class="underline_td_item" width="15%" align="left">TARIKH</td>
	   <td class="underline_td_item" width="15%" align="left">STATUS SELESAI</td>
	   <td class="underline_td_item" width="15%" align="left">CATATAN</td>
	   <td class="underline_td_item" width="15%" align="center">TINDAKAN</td>	   
	   </tr> 
	<tr id="div_PENAMBAHBAIKAN_ADD_$ID_SUBMODUL" >
	   <td class="underline_td_item_content"  align="center"></td>
	   <td class="underline_td_item_content" colspan="6" align="left">
	   TIADA REKOD ITEM PENAMBAHBAIKKAN	   
	   </td>
	   <td class="underline_td_item_content"  align="center" valign="top">
	   <a href="javascript:doDivAjaxCall$formname('div_PENAMBAHBAIKAN_ADD_$ID_SUBMODUL','add_penambahbaikkan','ID_SUBMODUL=$ID_SUBMODUL&BIL_TEMP=$BIL_LAST');"><img title="Tambah" src="../img/plus.gif" border="0"></a>
	   </td>
	</tr>
	   
 #end
</table>
              