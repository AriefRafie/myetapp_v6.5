<select id="CT_ID_DAERAH_$internalType"  name="CT_ID_DAERAH_$internalType" >	   
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
			  doDivAjaxCall$formname('CT_divListPejabat$internalType','selectPejabatByDaerahNegeri_CT','ID_DAERAH='+$jquery('#CT_ID_DAERAH_$internalType').val()+'&ID_NEGERI='+$jquery('#CT_ID_NEGERI_$internalType').val()+'&ID_JENISPEJABAT='+$jquery('#CT_ID_JENISPEJABAT_$internalType').val()+'&internalType=$internalType&jenisPengguna=integrasi');
		 });
</script>