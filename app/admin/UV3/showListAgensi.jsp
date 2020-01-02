<select id="ID_AGENSI_$internalType$USER_ID"  name="ID_AGENSI_$internalType$USER_ID"
					>	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $list_TBLRUJAGENSI)		
							#set ( $selected_ruj = "" )
							#if($viewPengguna.ID_AGENSI==$ruj.ID)
							#set ( $selected_ruj = "selected" )
							#end	
							<option $selected_ruj value="$ruj.ID" >
							$ruj.KETERANGAN
							</option>					
							
						#end
</select>


<script type="text/javascript">
				if('$ID_KEMENTERIAN'!="")
				{
					$jquery(document).ready(function () 
					{
						doDivAjaxCall$formname('div_ALAMAT_KEMENTERIAN$internalType$USER_ID','showAlamatKJP','internalType=$internalType&USER_ID=$USER_ID&ID_KEMENTERIAN=$ID_KEMENTERIAN');
					});
				}
</script>
			