<select id="CR_ID_BAHAGIAN" name="CR_ID_BAHAGIAN" >	   
					   <option value = "" >$label_sila_pilih</option>
						#foreach ( $N in $listBahagian)		
							#set ( $selected_N = "" )
							#if($CR_ID_BAHAGIAN==$N.ID_SEKSYEN)
							#set ( $selected_N = "selected" )
							#end
							<option $selected_N value="$N.ID_SEKSYEN" >
							$N.NAMA_SEKSYEN
							</option>							
						#end
</select>

<script>
$jquery(document).ready(function () {
	doDivAjaxCall3$formname('tdNegeriBahagian','showNegeriBahagianCR','ID_JENISTINDAKAN=$ID_JENISTINDAKAN');;
});
</script>
