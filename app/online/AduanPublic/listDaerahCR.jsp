<select id="CR_ID_DAERAH" name="CR_ID_DAERAH" >	   
					   <option value = "" >$label_sila_pilih</option>
						#foreach ( $N in $listDaerah)		
							#set ( $selected_N = "" )
							#if($CR_ID_DAERAH==$N.ID_DAERAH)
							#set ( $selected_N = "selected" )
							#end
							<option $selected_N value="$N.ID_DAERAH" >
							$N.NAMA_DAERAH
							</option>							
						#end
					</select>	