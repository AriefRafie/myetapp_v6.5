
				
				<td id="div_BANDAR_ID$ID_SEKSYEN">
					<select id="ID_BANDAR_$ID_SEKSYEN"  
					name="ID_BANDAR_$ID_SEKSYEN" onChange = "doDivAjaxCall$formname('','','');" >	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $list_TBLRUJBANDAR )		
							#set ( $selected_ruj = "" )
							#if($viewPejabat.ID_BANDAR==$ruj.ID)
							#set ( $selected_ruj = "selected" )
							#end		
							<option $selected_ruj value="$ruj.ID" >
							$ruj.KETERANGAN
							</option>
						#end
					</select>
				</td>