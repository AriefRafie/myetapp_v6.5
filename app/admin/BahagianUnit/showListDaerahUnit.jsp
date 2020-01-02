<td id="div_DAERAH_ID$ID_SEKSYEN$ID_PEJABATJKPTG">
					<select id="ID_DAERAH_$ID_SEKSYEN$ID_PEJABATJKPTG"  
					name="ID_DAERAH_$ID_SEKSYEN$ID_PEJABATJKPTG" 
					onChange="doDivAjaxCall$formname('div_BANDAR_ID$ID_SEKSYEN$ID_PEJABATJKPTG','showListBandarUnit','ID_PEJABATJKPTG=$ID_PEJABATJKPTG&ID_NEGERI=$ID_NEGERI&ID_DAERAH='+$jquery('#ID_DAERAH_$ID_SEKSYEN$ID_PEJABATJKPTG').val());"
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