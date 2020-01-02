<select id="CR_ID_KATEGORIADUAN" name="CR_ID_KATEGORIADUAN" >	   
					   <option value = "" >$label_sila_pilih</option>
						#foreach ( $N in $listKategoriAduan)		
							#set ( $selected_N = "" )
							#if($CR_ID_KATEGORIADUAN==$N.ID_KATEGORIADUAN)
							#set ( $selected_N = "selected" )
							#end
							<option $selected_N value="$N.ID_KATEGORIADUAN" >
							$N.KATEGORIADUAN
							</option>							
						#end
</select>	

<script>	
			
  $jquery(document).ready(function () {
	 doDivAjaxCall3$formname('tdNegeri','showNegeri','ID_JENISADUAN=$ID_JENISADUAN');			 	  
  });	

</script>	