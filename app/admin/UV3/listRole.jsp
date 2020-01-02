
<select id="ROLE_MAIN_$internalType$USER_ID"  name="ROLE_MAIN_$internalType$USER_ID" >	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $listRole )		
							#set ( $selected_ruj = "" )
							#if($current_role_utama==$ruj.ID && $ruj.ID !="")
							#set ( $selected_ruj = "selected" )
							#end		
							<option $selected_ruj value="$ruj.ID" >
							#if($ruj.ID == "" && $ruj.KOD!="")
								--------- $ruj.KOD --------- 
							#else
								$ruj.KOD - $ruj.KETERANGAN
							#end
							</option>
						#end
</select>