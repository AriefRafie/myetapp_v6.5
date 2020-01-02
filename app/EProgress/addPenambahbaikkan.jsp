       
       
	   <tr >
	   <td class="underline_td_item_content"  align="center" valign="top" >
	   </td>
	   <td class="underline_td_item_content"  align="center" valign="top" >	   
	   $BIL_TEMP
	   </td>
	   <td class="underline_td_item_content"  align="left" valign="top" >
	  
	   <textarea class="required" id="PENAMBAHBAIKAN_$ID_SUBMODUL" style="text-transform:uppercase;" name="PENAMBAHBAIKAN_$ID_SUBMODUL"  ></textarea>
	   </td>
	   
	   
	   <td class="underline_td_item_content"  align="left" valign="top" >
	   
	   
	   <select class="drop_pic required" id="ID_PIC_$ID_SUBMODUL"  name="ID_PIC_$ID_SUBMODUL" >	   
	   <option value = "" >SILA PILIH</option>
		#foreach ( $p in $listPIC )							
			<option  value="$p.ID_PIC" >
			$p.PIC
			</option>
		#end
	   </select>
	   </td>
	   
	   
	   
	   
	   <td class="underline_td_item_content"  align="left" valign="top" >
	   <input class="required" name="TARIKH_MULA_$ID_SUBMODUL" type="text" id="TARIKH_MULA_$ID_SUBMODUL" style="text-transform:uppercase;" value="" size="15" maxlength="15"  />
       <a href="javascript:displayDatePicker('TARIKH_MULA_$ID_SUBMODUL',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> 
       <br>
       to
       <br>
       <input class="required" name="TARIKH_TARGET_SIAP_$ID_SUBMODUL" type="text" id="TARIKH_TARGET_SIAP_$ID_SUBMODUL" style="text-transform:uppercase;" value="" size="15" maxlength="15"  />
       <a href="javascript:displayDatePicker('TARIKH_TARGET_SIAP_$ID_SUBMODUL',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> 
       
	   
	   
	   </td>
	   <td class="underline_td_item_content"  align="left" valign="top" >
	   
	   <table border="0" width="95%">
	   <tr>
	   <td width="10%" valign="top" align="center">
	   <input type="checkbox" id="FS_LOCAL_$ID_SUBMODUL" name="FS_LOCAL_$ID_SUBMODUL" value="1" >	   
	   </td>
	   <td width="90%" valign="top" align="left">
	   LOCAL DEPLOYMENT
	   </td>
	   </tr>
	   <tr>
	   <td valign="top" align="center">
	   <input type="checkbox" id="FS_STG_$ID_SUBMODUL" name="FS_STG_$ID_SUBMODUL" value="1" >	   
	   </td>
	   <td valign="top" align="left">
	   STG DEPLOYMENT
	   </td>
	   </tr>	
	   <tr>
	   <td valign="top" align="center">
	   <input type="checkbox" id="FS_DEV_$ID_SUBMODUL" name="FS_DEV_$ID_SUBMODUL" value="1" >	   
	   </td>
	   <td valign="top" align="left">
	   LIVE DEPLOYMENT
	   </td>
	   </tr>	      
	   </table>
	   
	   </td>
	   <td class="underline_td_item_content"  align="left" valign="top" >
	   
	   <table border="0" width="95%" >
	   <tr>
	   <td width="10%" valign="top" align="center">
	   <input type="checkbox" id="F_KIV_$ID_SUBMODUL" name="F_KIV_$ID_SUBMODUL" value="1" >	   
	   </td>
	   <td width="90%" valign="top" align="left">
	   STATUS KIV
	   </td>
	   </tr>
	   <tr>
	   <td valign="top" align="left" colspan="2">
	   <textarea  id="CATATAN_$ID_SUBMODUL" style="text-transform:uppercase;" name="CATATAN_$ID_SUBMODUL"  ></textarea>	   
	   </td>
	   </tr>		        
	   </table>
	   </td>	
	   
	      
	   <td class="underline_td_item_content"  align="center" valign="top" >
	   <input type="button" id="BTN_ADD_PENAMBAHBAIKAN_$ID_SUBMODUL" name="BTN_ADD_PENAMBAHBAIKAN_$ID_SUBMODUL" onClick="if(valAddPenambahbaikan('$ID_SUBMODUL')==true){doDivAjaxCall$formname('div_PENAMBAHBAIKKAN_$ID_SUBMODUL','add_item_penambahbaikkan','ID_SUBMODUL=$ID_SUBMODUL&BIL_TEMP=$BIL_TEMP');}" value="Tambah" > 
	   <input type="button" id="BTN_ADDBTL_PENAMBAHBAIKAN_$ID_SUBMODUL" name="BTN_ADDBTL_PENAMBAHBAIKAN_$ID_SUBMODUL" onClick="doDivAjaxCall$formname('div_PENAMBAHBAIKKAN_$ID_SUBMODUL','bataladd_item_penambahbaikkan','ID_SUBMODUL=$ID_SUBMODUL&BIL_TEMP=$BIL_TEMP');" value="Batal" > 
	   </td>	   
	   </tr>
	   
              