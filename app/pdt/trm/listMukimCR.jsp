<select id="CR_ID_MUKIM" name="CR_ID_MUKIM" >	   
					   <option value = "" >Sila Pilih</option>
                       #foreach ( $N in $listMukim)		
							#set ( $selected_N = "" )
							<option $selected_N value="$N.ID_MUKIM" >
							$N.NAMA_MUKIM
							</option>							
						#end
</select>