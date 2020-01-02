
	   <tr  >
	   <td class="underline_td_sub" width="5%" align="center" valign="top" ></td>
	   <td class="underline_td_sub" width="80%" align="left" valign="top" >  
	   SUB MODUL : <input class="required" type="text" id="SUBMODUL_$getItemSubmodul.ID_SUBMODUL" size="50" style="text-transform:uppercase;" name="SUBMODUL_$getItemSubmodul.ID_SUBMODUL" value="$getItemSubmodul.SUBMODUL" >
	   TURUTAN : <input type="text" id="TURUTAN_$getItemSubmodul.ID_SUBMODUL" onKeyUp="javascript:numberOnly(this,this.value)" size="5" style="text-transform:uppercase;" name="TURUTAN_$getItemSubmodul.ID_SUBMODUL" value="$getItemSubmodul.TURUTAN" >
	   </td>
	   <td class="underline_td_sub" width="15%" align="center" valign="top" >	   
	   <input type="button" id="BTN_SMP_SUBMODUL_$getItemSubmodul.ID_SUBMODUL" name="BTN_SMP_SUBMODUL_$getItemSubmodul.ID_SUBMODUL" onClick="if(valEditSubmodul('$getItemSubmodul.ID_SUBMODUL')==true){doDivAjaxCall$formname('div_SUBMODUL_$getItemSubmodul.ID_MAINMODUL','save_item_submodul','ID_SUBMODUL=$getItemSubmodul.ID_SUBMODUL&ID_MAINMODUL=$getItemSubmodul.ID_MAINMODUL');}" value="Simpan" > 
	   <input type="button" id="BTN_BTL_SUBMODUL_$getItemSubmodul.ID_SUBMODUL" name="BTN_BTL_SUBMODUL_$getItemSubmodul.ID_SUBMODUL" onClick="doDivAjaxCall$formname('div_ITEM_SUBMODUL_$getItemSubmodul.ID_SUBMODUL','bataledit_item_submodul','ID_SUBMODUL=$getItemSubmodul.ID_SUBMODUL&ID_MAINMODUL=$getItemSubmodul.ID_MAINMODUL');" value="Batal" > 
	   </td>	   
	   </tr>


              