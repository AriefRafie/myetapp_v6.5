<td id="div_DROPDOWNJENISPEJABAT_$viewPejabat.ID_PEJABAT">
		<select id="ID_JENISPEJABAT_$viewPejabat.ID_PEJABAT"  
		name="ID_JENISPEJABAT_$viewPejabat.ID_PEJABAT" >
	
		   <option value = "" >SILA PILIH</option>
			#foreach ( $ruj in $list_TBLRUJJENISPEJABAT )		
				#set ( $selected_ruj = "" )
				#if($viewPejabat.ID_JENISPEJABAT==$ruj.ID)
				#set ( $selected_ruj = "selected" )
				#end		
				<option $selected_ruj value="$ruj.ID" >
				$ruj.KETERANGAN
				</option>
			#end
		</select><input type="button" id="addJenisPejabat" name="addJenisPejabat" value="Tambah Jenis Pejabat" 
		onClick="doDivAjaxCall$formname('div_TambahJenisPejabat$viewPejabat.ID_PEJABAT','tambahJenisPejabat','ID_JENISPEJABAT=');" >
	
		</td>