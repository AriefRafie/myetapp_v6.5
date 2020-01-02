<select id="CT_ID_AGENSI_$internalType"  name="CT_ID_AGENSI_$internalType" >	   
					    <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $list_TBLRUJAGENSI)		
							#set ( $selected_CT_ID_AGENSI = "" )
							#if($CT_ID_AGENSI==$ruj.ID)
							#set ( $selected_CT_ID_AGENSI = "selected" )
							#end	
							<option $selected_CT_ID_AGENSI value="$ruj.ID" >
							$ruj.KETERANGAN
							</option>							
						#end
</select>
			