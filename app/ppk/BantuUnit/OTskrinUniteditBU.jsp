<select id="BU_ID_UNIT_$ID_PERMOHONANBANTUUNIT"  name="BU_ID_UNIT_$ID_PERMOHONANBANTUUNIT" onchange="doDivAjaxCall3$formname('div_BU_DUPLICATE_DATE_$ID_PERMOHONANBANTUUNIT','checkDateBU','ID_PERMOHONANBANTUUNIT=$ID_PERMOHONANBANTUUNIT');">	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $U in $listUnit)		
							#set ( $selected_U = "" )
							#if($BU_ID_UNIT==$U.ID_UNIT)
							#set ( $selected_U = "selected" )
							#end
							<option $selected_U value="$U.ID_UNIT" >
							#if($U.ID_UNIT=="ALL")
								$U.NAMA_UNIT
							#else
                            	#if($U.ID_JENISPEJABAT=="22")
								$U.NAMA_UNIT
                                #else
                                $U.NAMA_UNIT
                                #end
							#end							
							</option>							
						#end
</select>