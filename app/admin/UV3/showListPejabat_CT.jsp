<td id="div_CT_ID_PEJABATJKPTG_$internalType" >
	<select id="CT_ID_PEJABATJKPTG_$internalType"  name="CT_ID_PEJABATJKPTG_$internalType" 
	>	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $listPejabatJKPTG )									
							<option $selected_ruj value="$ruj.ID_PEJABATJKPTG" >
							$ruj.NAMA_UNIT
							</option>
						#end
	</select>
</td>
