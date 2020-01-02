<td id="div_ID_PEJABATJKPTG$internalType$USER_ID" >
	<select id="ID_PEJABATJKPTG_$internalType$USER_ID"  name="ID_PEJABATJKPTG_$internalType$USER_ID" 
	onChange = "doDivAjaxCall$formname('div_ALAMAT_PEJABATJKPTG$internalType$USER_ID','showDisplayPejabat','ID_PEJABATJKPTG='+$jquery('#ID_PEJABATJKPTG_$internalType$USER_ID').val()+'&internalType=$internalType&USER_ID=$USER_ID');"
	>	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $listPejabatJKPTG )		
							#set ( $selected_ruj = "" )
							#if($viewPengguna.ID_PEJABATJKPTG==$ruj.ID_PEJABATJKPTG)
							#set ( $selected_ruj = "selected" )
							#end		
							<option $selected_ruj value="$ruj.ID_PEJABATJKPTG" >
							$ruj.NAMA_UNIT
							</option>
						#end
	</select>
</td>

		<script>
		  $jquery(document).ready(function () {
			  document.getElementById("div_ALAMAT_PEJABATJKPTG$internalType$USER_ID").style.display="none";
			  doDivAjaxCall$formname('div_ALAMAT_PEJABATJKPTG$internalType$USER_ID','showDisplayPejabat','ID_PEJABATJKPTG=&internalType=$internalType&USER_ID=$USER_ID');			 	  
		  });
		</script>