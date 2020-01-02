<td id="div_DAERAH_ID$ID_PEJABAT">$ID_PEJABAT
					<select id="ID_DAERAH_$ID_PEJABAT"  
					name="ID_DAERAH_$ID_PEJABAT" 
					onChange="doDivAjaxCall$formname('div_BANDAR_ID$ID_PEJABAT','showListBandar','ID_PEJABAT=$ID_PEJABAT&ID_NEGERI='+$jquery('#ID_NEGERI_$ID_PEJABAT').val()+'&ID_DAERAH='+$jquery('#ID_DAERAH_$ID_PEJABAT').val());"
					>	  
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $list_TBLRUJDAERAH )		
							#set ( $selected_ruj = "" )
							#if($viewPejabat.ID_DAERAH==$ruj.ID)
							#set ( $selected_ruj = "selected" )
							#end		
							<option $selected_ruj value="$ruj.ID" >
							$ruj.KETERANGAN
							</option>
						#end
					</select>
				</td>