<select id="ID_DAERAH_$internalType$USER_ID"  name="ID_DAERAH_$internalType$USER_ID" >	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $list_TBLRUJDAERAH )		
							#set ( $selected_ruj = "" )								
							<option $selected_ruj value="$ruj.ID" >
							$ruj.KETERANGAN
							</option>
						#end
</select>
<script>
		  $jquery(document).ready(function () {
			  //document.getElementById("div_ALAMAT_PEJABATJKPTG$internalType$USER_ID").style.display="none";
			  doDivAjaxCall$formname('divListPejabat$internalType$USER_ID','selectPejabatByDaerahNegeri','ID_DAERAH='+$jquery('#ID_DAERAH_$internalType$USER_ID').val()+'&ID_JENISPEJABAT='+$jquery('#ID_JENISPEJABAT_$internalType$USER_ID').val()+'&ID_NEGERI='+$jquery('#ID_NEGERI_$internalType$USER_ID').val()+'&USER_ID=$USER_ID&internalType=$internalType');
		 });
</script>