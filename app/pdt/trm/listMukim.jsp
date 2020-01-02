<select id="ID_MUKIM_$JENISSUB$ID_WARTATRMINDUK$ID_WARTATRM" name="ID_MUKIM_$JENISSUB$ID_WARTATRMINDUK$ID_WARTATRM" >	   
					   <option value = "" >Sila Pilih</option>
                       #foreach ( $N in $listMukim)		
							#set ( $selected_N = "" )
							<option $selected_N value="$N.ID_MUKIM" >
							$N.NAMA_MUKIM
							</option>							
						#end
</select>