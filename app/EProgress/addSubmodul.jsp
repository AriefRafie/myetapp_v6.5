


	   <tr  >
	   <td class="underline_td_sub" width="5%" align="center" valign="top" ></td>
	   <td class="underline_td_sub" width="80%" align="left" valign="top" >  
	   SUB MODUL : <input class="required" type="text" id="SUBMODUL_$ID_MAINMODUL" size="50" style="text-transform:uppercase;" name="SUBMODUL_$ID_MAINMODUL" value="" >
	   TURUTAN : <input type="text" id="TURUTAN_$ID_MAINMODUL" onKeyUp="javascript:numberOnly(this,this.value)" size="5" style="text-transform:uppercase;" name="TURUTAN_$ID_MAINMODUL" value="" >
	   </td>
	   <td class="underline_td_sub" width="15%" align="center" valign="top" >	   
	   
	   <input type="button" id="BTN_ADD_SUBMODUL_$ID_MAINMODUL" name="BTN_ADD_SUBMODUL_$ID_MAINMODUL" onClick="if(valAddSubmodul('$ID_MAINMODUL')==true){doDivAjaxCall$formname('div_SUBMODUL_$ID_MAINMODUL','add_item_submodul','ID_MAINMODUL=$ID_MAINMODUL');}" value="Tambah" > 
	   <input type="button" id="BTN_ADDBTL_SUBMODUL_$ID_MAINMODUL" name="BTN_ADDBTL_SUBMODUL_$ID_MAINMODUL" onClick="doDivAjaxCall$formname('div_SUBMODUL_$ID_MAINMODUL','bataladd_item_submodul','ID_MAINMODUL=$ID_MAINMODUL');" value="Batal" > 
	  
	   
	   </td>	   
	   </tr>


	   
              