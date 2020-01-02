



<table class="classFade"  border="0" cellspacing="0" cellpadding="1" width="100%" >  
 #if($listMainModul.size()>0) 			         
	   #foreach($mm in $listMainModul)
	   <tr  id="div_ITEM_MAINMODUL_$mm.ID_MAINMODUL" >
	   <td class="underline_td_main" width="2%" align="center"></td>
	   <td style="cursor: pointer;" class="underline_td_main" width="83%" align="left" onClick="doDivAjaxCall$formname('div_SUBMODUL_'+'$mm.ID_MAINMODUL','show_by_submodul','ID_MAINMODUL=$mm.ID_MAINMODUL');" >$mm.MAINMODUL</td>
	   <td class="underline_td_main" width="15%" align="center">
	   
	   <a href="javascript:doDivAjaxCall$formname('div_ITEM_MAINMODUL_$mm.ID_MAINMODUL','edit_mainmodul','ID_MAINMODUL=$mm.ID_MAINMODUL');"><img title="Kemaskini" src="../img/edit.gif" border="0"></a>
	   <a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('div_by_modul','delete_mainmodul','ID_MAINMODUL=$mm.ID_MAINMODUL');}"><img title="Hapus" src="../img/delete.gif" border="0"></a>
	  
	   </td>	   
	   </tr>
	   <tr >
	   
	   <td colspan="3" align="right" >
	   <div id="div_SUBMODUL_$mm.ID_MAINMODUL">
	   <input type="hidden" id="openSectionSubmodul_$mm.ID_MAINMODUL" name="openSectionSubmodul_$mm.ID_MAINMODUL" value="$openSectionSubmodul" >
	   
	   </div>
	   </td>
	   </tr>
	   <!--
	   <tr>
	   <td  width="5%" align="center"></td>
	   <td  width="85%" align="left">&nbsp;</td>
	   <td  width="10%" align="center"></td>
	   </tr>
	   -->
	    <script>  
	    /*
		  $jquery(document).ready(function () {
			  doDivAjaxCall$formname('div_SUBMODUL_'+'$mm.ID_MAINMODUL','show_by_submodul','ID_MAINMODUL=$mm.ID_MAINMODUL');		
		  });
	    */
		</script>

	   #end
	   
	   <tr id="div_MAINMODUL_ADD_" >
	   <td class="underline_td_main" width="2%" align="center" valign="top" ></td>
	   <td class="underline_td_main" width="83%" align="center" valign="top" >&nbsp;</td>
	   <td class="underline_td_main" width="15%" align="center" valign="top" >
	   <a href="javascript:doDivAjaxCall$formname('div_MAINMODUL_ADD_','add_mainmodul','');"><img title="Tambah" src="../img/plus.gif" border="0"></a>
	   </td>	   
	   </tr>	
 #else
 	   <tr id="div_MAINMODUL_ADD_" >
	   <td class="underline_td_main" width="2%" align="center" valign="top" ></td>
	   <td class="underline_td_main" width="83%" align="left" valign="top" >
	   TIADA REKOD MODUL
	   </td>
	   <td class="underline_td_main" width="15%" align="center" valign="top" >
	   #if($ID_PROJEK!="")
	   <a href="javascript:doDivAjaxCall$formname('div_MAINMODUL_ADD_','add_mainmodul','');"><img title="Tambah" src="../img/plus.gif" border="0"></a>
	   #end
	   </td>	   
	   </tr>
 #end
</table>



              