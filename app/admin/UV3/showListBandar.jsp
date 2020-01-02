
<td id="div_ID_BANDAR$internalType$USER_ID" >

	<select id="ID_BANDAR_$internalType$USER_ID"  name="ID_BANDAR_$internalType$USER_ID" 
	>	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $list_TBLRUJBANDAR )		
							#set ( $selected_ruj = "" )
							#if($viewPengguna.ID_BANDAR==$ruj.ID)
							#set ( $selected_ruj = "selected" )
							#end		
							<option $selected_ruj value="$ruj.ID" >
							$ruj.KETERANGAN
							</option>
						#end
	</select>
</td>
