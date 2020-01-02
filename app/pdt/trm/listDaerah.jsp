<select id="ID_DAERAH_$JENISSUB$ID_WARTATRMINDUK$ID_WARTATRM" name="ID_DAERAH_$JENISSUB$ID_WARTATRMINDUK$ID_WARTATRM"
 onChange = "doDivAjaxCall3$formname('tdMukim_$JENISSUB$ID_WARTATRMINDUK$ID_WARTATRM','showMukim','ID_WARTATRM=$ID_WARTATRM&ID_WARTATRMINDUK=$ID_WARTATRMINDUK&JENISSUB=$JENISSUB&ID_DAERAH='+this.value);" >	   
					   <option value = "" >Sila Pilih</option>
                       #foreach ( $N in $listDaerah)		
							#set ( $selected_N = "" )
							<option $selected_N value="$N.ID_DAERAH" >
							$N.NAMA_DAERAH
							</option>							
						#end
</select>

