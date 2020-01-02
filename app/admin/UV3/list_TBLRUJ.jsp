<td  align="left" valign="top" >
	   $tableRujukan
	   
	   <select id="$fieldName$USER_ID"  name="$fieldName$USER_ID" >	   
	   <option value = "" >SILA PILIH</option>
		#foreach ( $ruj in $list_TBLRUJ )		
			#set ( $selected_ruj = "" )
			#if($SELECTED_VALUE==$ruj.ID)
			#set ( $selected_ruj = "selected" )
			#end		
			<option $selected_ruj value="$ruj.ID" >
			$ruj.KETERANGAN
			</option>
		#end
	   </select>
	   
	   
	   </td>