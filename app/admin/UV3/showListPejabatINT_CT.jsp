<select id="CT_ID_PEJABAT_$internalType"  
					name="CT_ID_PEJABAT_$internalType" 
					>	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $listPejabat )		
							#set ( $selected_ruj = "" )									
							<option $selected_ruj value="$ruj.ID_PEJABAT" >
							$ruj.NAMA_PEJABAT
							</option>
						#end
					</select>
					
		