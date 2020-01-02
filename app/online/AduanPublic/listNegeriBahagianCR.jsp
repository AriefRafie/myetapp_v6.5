

<select id="CR_ID_NEGERI_BAHAGIAN" name="CR_ID_NEGERI_BAHAGIAN" >	   
					   <option value = "" >$label_sila_pilih</option>
						#foreach ( $N in $listNegeriBahagian)		
							#set ( $selected_N = "" )
                            
                            #if($ID_JENISTINDAKAN == "16171" &&  $N.ID_NEGERI == "16" )	
                            	#set ( $selected_N = "selected" )
                            #end						
                            <option $selected_N value="$N.ID_NEGERI" >
                            $N.NAMA_NEGERI
                            </option>	
                            
                                                
						#end
					</select>	