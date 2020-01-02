


	   <tr  >
	   <td class="underline_td_main" width="2%" align="center" valign="top" ></td>
	   <td class="underline_td_main" width="83%" align="left" valign="top" >  
	   MAIN MODUL : <input class="required" type="text" id="MAINMODUL_" size="50" style="text-transform:uppercase;" name="MAINMODUL_" value="" >
	   TURUTAN : <input type="text" id="TURUTAN_" onKeyUp="javascript:numberOnly(this,this.value)" size="5" style="text-transform:uppercase;" name="TURUTAN_" value="" >
	   </td>
	   <td class="underline_td_main" width="15%" align="center" valign="top" >	   
	   
	   <input type="button" id="BTN_ADD_MAINMODUL_" name="BTN_ADD_MAINMODUL_" onClick="if(valAddMainmodul()==true){doDivAjaxCall$formname('div_by_modul','add_item_mainmodul','ID_PROJEK='+$jquery('#ID_PROJEK').val());}" value="Tambah" > 
	   <input type="button" id="BTN_ADDBTL_MAINMODUL_" name="BTN_ADDBTL_MAINMODUL_" onClick="doDivAjaxCall$formname('div_MAINMODUL_ADD_','bataladd_item_mainmodul','');" value="Batal" > 
	  
	   
	   </td>	   
	   </tr>


	   
              