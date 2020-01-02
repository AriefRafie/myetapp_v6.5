<select id="ID_GRED_$internalType$USER_ID"  name="ID_GRED_$internalType$USER_ID" >	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $list_TBLRUJGRED )		
							#set ( $selected_ruj = "" )								
							<option $selected_ruj value="$ruj.ID" >
							$ruj.KOD
							</option>
						#end
					</select>