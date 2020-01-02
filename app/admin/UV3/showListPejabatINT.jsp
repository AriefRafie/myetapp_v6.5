<select id="ID_PEJABAT_$internalType$USER_ID"  
					name="ID_PEJABAT_$internalType$USER_ID" 
					onChange="doDivAjaxCall$formname('div_ALAMAT_PEJABAT_$internalType$USER_ID','showAlamatPejabat','ID_PEJABAT='+$jquery('#ID_PEJABAT_$internalType$USER_ID').val()+'&USER_ID=$USER_ID&internalType=$internalType');" 
					
					>	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $listPejabat )		
							#set ( $selected_ruj = "" )									
							<option $selected_ruj value="$ruj.ID_PEJABAT" >
							$ruj.NAMA_PEJABAT
							</option>
						#end
					</select>
					
		<script>
		  $jquery(document).ready(function () {
			  document.getElementById("div_ALAMAT_PEJABAT_$internalType$USER_ID").style.display="none";
			  doDivAjaxCall$formname('div_ALAMAT_PEJABAT_$internalType$USER_ID','showAlamatPejabat','ID_PEJABAT=&USER_ID=$USER_ID&internalType=$internalType');			 	  
		  });
		</script>