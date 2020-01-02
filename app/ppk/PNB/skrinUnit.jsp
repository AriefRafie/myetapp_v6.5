<select id="CARIAN_UNIT"  name="CARIAN_UNIT">	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $U in $listUnit)		
							#set ( $selected_U = "" )
							#if($CARIAN_UNIT==$U.ID_UNIT)
							#set ( $selected_U = "selected" )
							#end
							<option $selected_U value="$U.ID_UNIT" >
							$U.NAMA_UNIT														
							</option>							
						#end
</select>