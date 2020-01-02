<table width="100%" border="0" cellpadding="0" cellspacing="2">
			<tr>
			<td valign="top"  width="1%"></td><td valign="top"  width="28%"></td><td valign="top"  width="1%"></td><td valign="top"  width="70%"><!-- $viewPengguna --></td>
			</tr>
			<tr>
				<td valign="top" >		
								
				</td>			
				<td valign="top" >
				Negeri
				</td>
				<td valign="top" >
				:
				</td>
				<td >
					<select id="CT_ID_NEGERI_$internalType"  name="CT_ID_NEGERI_$internalType"
					onChange="doDivAjaxCall$formname('CT_divListDaerah$internalType','selectDaerahByNegeri_CT','ID_NEGERI='+$jquery('#CT_ID_NEGERI_$internalType').val()+'&internalType=$internalType');">	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $list_TBLRUJNEGERI )		
							#set ( $selected_ruj = "" )									
							<option $selected_ruj value="$ruj.ID" >
							$ruj.KETERANGAN
							</option>
						#end
					</select>
				</td>
			</tr>
					<tr>
				<td valign="top" >		
								
				</td>			
				<td valign="top" >
				Daerah
				</td>
				<td valign="top" >
				:
				</td>
				<td >
				
				<div id="CT_divListDaerah$internalType">
					<select id="CT_ID_DAERAH_$internalType"  name="CT_ID_DAERAH_$internalType" >	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $list_TBLRUJDAERAH )		
							#set ( $selected_ruj = "" )									
							<option $selected_ruj value="$ruj.ID" >
							$ruj.KETERANGAN
							</option>
						#end
					</select>
				</div>
				</td>
			</tr>
			<tr>
				<td valign="top" >	
						
				</td>			
				<td valign="top" >
				Pejabat
				</td>
				<td valign="top" >
				:
				</td>
				<td >
				<div id="CT_divListPejabat$internalType">
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
				</div>
				</td>
			</tr>
			
			
		
			</table>
            
        