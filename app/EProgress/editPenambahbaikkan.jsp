
	   <tr >
	   <td class="underline_td_item_content"  align="center" valign="top" >
	   <input type="hidden" id="ID_PENAMBAHBAIKAN_$getItemPenambahbaikkan.ID_PENAMBAHBAIKAN" name="ID_PENAMBAHBAIKAN_$getItemPenambahbaikkan.ID_PENAMBAHBAIKAN" value="$getItemPenambahbaikkan.ID_PENAMBAHBAIKAN" >
	   </td>
	   <td class="underline_td_item_content"  align="center" valign="top" >	   
	   $BIL_TEMP
	   </td>
	   <td class="underline_td_item_content"  align="left" valign="top" >
	   
	   <textarea class="required" id="PENAMBAHBAIKAN_$getItemPenambahbaikkan.ID_PENAMBAHBAIKAN" style="text-transform:uppercase;" name="PENAMBAHBAIKAN_$getItemPenambahbaikkan.ID_PENAMBAHBAIKAN"  >$getItemPenambahbaikkan.PENAMBAHBAIKAN</textarea>
	   </td>
	   
	   
	   <td class="underline_td_item_content"  align="left" valign="top" >
	   
	   
	   <select class="drop_pic required" id="ID_PIC_$getItemPenambahbaikkan.ID_PENAMBAHBAIKAN"  name="ID_PIC_$getItemPenambahbaikkan.ID_PENAMBAHBAIKAN" >	   
	   <option value = "" >SILA PILIH</option>
		#foreach ( $p in $listPIC )		
			#set ( $selected_pic = "" )
			#if($getItemPenambahbaikkan.ID_PIC==$p.ID_PIC)
			#set ( $selected_pic = "selected" )
			#end		
			<option $selected_pic value="$p.ID_PIC" >
			$p.PIC
			</option>
		#end
	   </select>
	   
	   
	   </td>
	   
	   
	   
	   <td class="underline_td_item_content"  align="left" valign="top" >
	   <input class="required" name="TARIKH_MULA_$getItemPenambahbaikkan.ID_PENAMBAHBAIKAN" type="text" id="TARIKH_MULA_$getItemPenambahbaikkan.ID_PENAMBAHBAIKAN" style="text-transform:uppercase;" value="$getItemPenambahbaikkan.TARIKH_MULA" size="15" maxlength="15"  />
       <a href="javascript:displayDatePicker('TARIKH_MULA_$getItemPenambahbaikkan.ID_PENAMBAHBAIKAN',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> 
       <br>
       to
       <br>
       <input class="required" name="TARIKH_TARGET_SIAP_$getItemPenambahbaikkan.ID_PENAMBAHBAIKAN" type="text" id="TARIKH_TARGET_SIAP_$getItemPenambahbaikkan.ID_PENAMBAHBAIKAN" style="text-transform:uppercase;" value="$getItemPenambahbaikkan.TARIKH_TARGET_SIAP" size="15" maxlength="15"  />
       <a href="javascript:displayDatePicker('TARIKH_TARGET_SIAP_$getItemPenambahbaikkan.ID_PENAMBAHBAIKAN',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> 
       
	   
	   
	   </td>
	   <td class="underline_td_item_content"  align="left" valign="top" >
	   
	   <table border="0" width="95%">
	   <tr>
	   <td width="10%" valign="top" align="center">
	   
	   #set($check_FS_LOCAL = "" )
	   #if($getItemPenambahbaikkan.FS_LOCAL=="1")
	   #set($check_FS_LOCAL = "checked" )
	   #end
	   
	   <input type="checkbox" $check_FS_LOCAL id="FS_LOCAL_$getItemPenambahbaikkan.ID_PENAMBAHBAIKAN" name="FS_LOCAL_$getItemPenambahbaikkan.ID_PENAMBAHBAIKAN" value="1" >	   
	   </td>
	   <td width="90%" valign="top" align="left">
	   LOCAL DEPLOYMENT
	   </td>
	   </tr>
	   <tr>
	   <td valign="top" align="center">
	   #set($check_FS_STG = "" )
	   #if($getItemPenambahbaikkan.FS_STG=="1")
	   #set($check_FS_STG = "checked" )
	   #end
	   <input type="checkbox" $check_FS_STG id="FS_STG_$getItemPenambahbaikkan.ID_PENAMBAHBAIKAN" name="FS_STG_$getItemPenambahbaikkan.ID_PENAMBAHBAIKAN" value="1" >	   
	   </td>
	   <td valign="top" align="left">
	   STG DEPLOYMENT
	   </td>
	   </tr>	
	   <tr>
	   <td valign="top" align="center">
	   #set($check_FS_DEV = "" )
	   #if($getItemPenambahbaikkan.FS_DEV=="1")
	   #set($check_FS_DEV = "checked" )
	   #end
	   <input type="checkbox" $check_FS_DEV id="FS_DEV_$getItemPenambahbaikkan.ID_PENAMBAHBAIKAN" name="FS_DEV_$getItemPenambahbaikkan.ID_PENAMBAHBAIKAN" value="1" >	   
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
	   #set($check_F_KIV = "" )
	   #if($getItemPenambahbaikkan.F_KIV =="1")
	   #set($check_F_KIV  = "checked" )
	   #end
	   <input type="checkbox" $check_F_KIV id="F_KIV_$getItemPenambahbaikkan.ID_PENAMBAHBAIKAN" name="F_KIV_$getItemPenambahbaikkan.ID_PENAMBAHBAIKAN" value="1" >	   
	   </td>
	   <td width="90%" valign="top" align="left">
	   STATUS KIV
	   </td>
	   </tr>
	   <tr>
	   <td valign="top" align="left" colspan="2">
	   <textarea  id="CATATAN_$getItemPenambahbaikkan.ID_PENAMBAHBAIKAN" style="text-transform:uppercase;" name="CATATAN_$getItemPenambahbaikkan.ID_PENAMBAHBAIKAN"  >$getItemPenambahbaikkan.CATATAN</textarea>	   
	   </td>
	   </tr>	
	        
	   </table>
	   
	   
	   
	   </td>
	   
	   
	    
	   <td class="underline_td_item_content"  align="center" valign="top" >
	   <input type="button" id="BTN_SMP_PENAMBAHBAIKAN_$getItemPenambahbaikkan.ID_PENAMBAHBAIKAN" name="BTN_SMP_PENAMBAHBAIKAN_$getItemPenambahbaikkan.ID_PENAMBAHBAIKAN" onClick="if(valEditPenambahbaikan('$getItemPenambahbaikkan.ID_PENAMBAHBAIKAN')==true){doDivAjaxCall$formname('div_ITEM_PENAMBAHBAIKAN_'+'$getItemPenambahbaikkan.ID_PENAMBAHBAIKAN','save_item_penambahbaikkan','ID_SUBMODUL=$getItemPenambahbaikkan.ID_SUBMODUL&ID_PENAMBAHBAIKAN=$getItemPenambahbaikkan.ID_PENAMBAHBAIKAN&BIL_TEMP=$BIL_TEMP&fromStatististik=$fromStatististik');}" value="Simpan" > 
	   <input type="button" id="BTN_BTL_PENAMBAHBAIKAN_$getItemPenambahbaikkan.ID_PENAMBAHBAIKAN" name="BTN_BTL_PENAMBAHBAIKAN_$getItemPenambahbaikkan.ID_PENAMBAHBAIKAN" onClick="doDivAjaxCall$formname('div_ITEM_PENAMBAHBAIKAN_'+'$getItemPenambahbaikkan.ID_PENAMBAHBAIKAN','bataledit_item_penambahbaikkan','ID_SUBMODUL=$getItemPenambahbaikkan.ID_SUBMODUL&ID_PENAMBAHBAIKAN=$getItemPenambahbaikkan.ID_PENAMBAHBAIKAN&BIL_TEMP=$BIL_TEMP&fromStatististik=$fromStatististik');" value="Batal" > 
	   </td>	   
	   </tr>
	   
	   
              