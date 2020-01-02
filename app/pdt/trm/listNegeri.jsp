<select id="CR_ID_NEGERI" name="CR_ID_NEGERI" 
onChange = "doDivAjaxCall3$formname('tdDaerah','showDaerahCR','ID_NEGERI='+this.value);"
 >	   
					   <option value = "" >Sila Pilih</option>
						#foreach ( $N in $listNegeri)		
							#set ( $selected_N = "" )
							#if($CR_ID_NEGERI==$N.ID_NEGERI)
							#set ( $selected_N = "selected" )
							#end
							<option $selected_N value="$N.ID_NEGERI" >
							$N.NAMA_NEGERI
							</option>							
						#end
					</select>	