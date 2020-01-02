<input type="hidden" id="openSectionSubmodul_$ID_MAINMODUL" name="openSectionSubmodul_$ID_MAINMODUL" value="$openSectionSubmodul" >
	   
<table class="classFade"  border="0" cellspacing="0" cellpadding="1" width="100%" >  
#if($listSubModul.size()>0) 			         
	   #foreach($sm in $listSubModul)
	   <tr id="div_ITEM_SUBMODUL_$sm.ID_SUBMODUL" class="table_header"  >
	   <td class="underline_td_sub" width="5%" align="center"></td>
	   <td class="underline_td_sub" width="80%"  style="cursor: pointer;" align="left" onClick="doDivAjaxCall$formname('div_PENAMBAHBAIKKAN_$sm.ID_SUBMODUL','show_by_penambahbaikkan','ID_SUBMODUL=$sm.ID_SUBMODUL');" >$sm.SUBMODUL</td>
	   <td class="underline_td_sub" width="15%" align="center">
	   
	   <a href="javascript:doDivAjaxCall$formname('div_ITEM_SUBMODUL_$sm.ID_SUBMODUL','edit_submodul','ID_SUBMODUL=$sm.ID_SUBMODUL');"><img title="Kemaskini" src="../img/edit.gif" border="0"></a>
	   <a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('div_SUBMODUL_$sm.ID_MAINMODUL','delete_submodul','ID_SUBMODUL=$sm.ID_SUBMODUL&ID_MAINMODUL=$sm.ID_MAINMODUL');}"><img title="Hapus" src="../img/delete.gif" border="0"></a>
	  
	   </td>	   
	   </tr>
	   <tr >
	   <td colspan="3" align="right" >
	   <div id="div_PENAMBAHBAIKKAN_$sm.ID_SUBMODUL">
	   <input type="hidden" id="openSectionPenambahbaikan_$sm.ID_SUBMODUL" name="openSectionPenambahbaikan_$sm.ID_SUBMODUL" value="$openSectionPenambahbaikan" >
	   </div>
	   </td>
	   </tr>	 
	   
	   <script>  
	   /*
		  $jquery(document).ready(function () {
			  doDivAjaxCall$formname('div_PENAMBAHBAIKKAN_$sm.ID_SUBMODUL','show_by_penambahbaikkan','ID_SUBMODUL=$sm.ID_SUBMODUL');		
		  });  
	   */
		</script>
		
		  	 
	   #end
	   
	   <tr id="div_SUBMODUL_ADD_$ID_MAINMODUL" class="table_header">
	   <td class="underline_td_sub" width="5%" align="center" valign="top" ></td>
	   <td class="underline_td_sub" width="80%" align="center" valign="top" >&nbsp;</td>
	   <td class="underline_td_sub" width="15%" align="center" valign="top" >
	   <a href="javascript:doDivAjaxCall$formname('div_SUBMODUL_ADD_$ID_MAINMODUL','add_submodul','ID_MAINMODUL=$ID_MAINMODUL');"><img title="Tambah" src="../img/plus.gif" border="0"></a>
	   </td>	   
	   </tr>	
	   
 #else
 	   
	<tr id="div_SUBMODUL_ADD_$ID_MAINMODUL" class="table_header" >
	   <td  class="underline_td_sub" width="5%" align="center"></td>
	   <td  class="underline_td_sub" width="80%" align="left">
	   TIADA REKOD SUB MODUL / SKRIN
	   
	   </td>
	   <td  class="underline_td_sub" width="15%" align="center" valign="top">
	   <a href="javascript:doDivAjaxCall$formname('div_SUBMODUL_ADD_$ID_MAINMODUL','add_submodul','ID_MAINMODUL=$ID_MAINMODUL');"><img title="Tambah" src="../img/plus.gif" border="0"></a>
	   </td>
	</tr>
	   
 #end
</table>
              