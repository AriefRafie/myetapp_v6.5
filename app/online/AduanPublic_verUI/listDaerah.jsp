<select id="ID_DAERAH_$ID_ADUANPUBLIC" name="ID_DAERAH_$ID_ADUANPUBLIC" >	   
					   <option value = "" >$label_sila_pilih</option>
                       #foreach ( $N in $listDaerah)		
							#set ( $selected_N = "" )
							<option $selected_N value="$N.ID_DAERAH" >
							$N.NAMA_DAERAH
							</option>							
						#end
					</select>