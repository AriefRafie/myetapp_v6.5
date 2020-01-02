<select id="POT_ID_UNIT"  name="POT_ID_UNIT">	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $U in $listUnit)		
							#set ( $selected_U = "" )
							#if($POT_ID_UNIT==$U.ID_UNIT)
							#set ( $selected_U = "selected" )
							#end
							<option $selected_U value="$U.ID_UNIT" >
							#if($U.ID_UNIT=="ALL")
								$U.NAMA_UNIT
							#else
								$U.NAMA_UNIT, $U.NAMA_NEGERI
							#end							
							</option>							
						#end
</select>