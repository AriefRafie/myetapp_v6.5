
	   <tr  >
	   <td class="underline_td_main" width="2%" align="center" valign="top" ></td>
	   <td class="underline_td_main" width="83%" align="left" valign="top" >  
	   MAIN MODUL : <input class="required" type="text" id="MAINMODUL_$getItemMainmodul.ID_MAINMODUL" size="50" style="text-transform:uppercase;" name="MAINMODUL_$getItemMainmodul.ID_MAINMODUL" value="$getItemMainmodul.MAINMODUL" >
	   TURUTAN : <input type="text" id="TURUTAN_$getItemMainmodul.ID_MAINMODUL" onKeyUp="javascript:numberOnly(this,this.value)" size="5" style="text-transform:uppercase;" name="TURUTAN_$getItemMainmodul.ID_MAINMODUL" value="$getItemMainmodul.TURUTAN" >
	   </td>
	   <td class="underline_td_main" width="15%" align="center" valign="top" >	   
	   <input type="button" id="BTN_SMP_MAINMODUL_$getItemMainmodul.ID_MAINMODUL" name="BTN_SMP_MAINMODUL_$getItemMainmodul.ID_MAINMODUL" onClick="if(valEditMainmodul('$getItemMainmodul.ID_MAINMODUL')==true){doDivAjaxCall$formname('div_ITEM_MAINMODUL_$getItemMainmodul.ID_MAINMODUL','save_item_mainmodul','ID_MAINMODUL=$getItemMainmodul.ID_MAINMODUL');}" value="Simpan" > 
	   <input type="button" id="BTN_BTL_MAINMODUL_$getItemMainmodul.ID_MAINMODUL" name="BTN_BTL_MAINMODUL_$getItemMainmodul.ID_MAINMODUL" onClick="doDivAjaxCall$formname('div_ITEM_MAINMODUL_$getItemMainmodul.ID_MAINMODUL','bataledit_item_mainmodul','ID_MAINMODUL=$getItemMainmodul.ID_MAINMODUL');" value="Batal" > 
	   </td>	   
	   </tr>


              