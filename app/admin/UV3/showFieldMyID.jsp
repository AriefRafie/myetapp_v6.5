<tr id="div_NO_PENGENALAN$internalType$USER_ID">
				<td valign="top" >	
				<font color="red" >*</font>				
				</td>			
				<td valign="top" >
				
				ID Pengguna	(User Login)						
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				<input name="NO_PENGENALAN1_$internalType$USER_ID" type="text" id="NO_PENGENALAN1_$internalType$USER_ID" style="width:50px;" onKeyUp="javascript:validateIC_V3(event,this,this.value,'NO_PENGENALAN2_$internalType$USER_ID')" value="$NO_PENGENALAN1" size="7" maxlength="6"  onBlur="getAgeByIC_V3(this,this.value,'UMUR_$internalType$USER_ID');getDOBByIC_V3(this,this.value,'TARIKH_LAHIR_$internalType$USER_ID','span_NO_PENGENALAN1_$internalType$USER_ID');"  />
                <span id="span_NO_PENGENALAN1_$internalType$USER_ID">
                <input type="hidden" id="CHECK_NO_PENGENALAN1_$internalType$USER_ID" 
				name="CHECK_NO_PENGENALAN1_$internalType$USER_ID" value="true" >
                </span> 
                                   - 
                <input name="NO_PENGENALAN2_$internalType$USER_ID" type="text" id="NO_PENGENALAN2_$internalType$USER_ID" style="width:20px;" onKeyUp="javascript:validateIC_V3(event,this,this.value,'NO_PENGENALAN3_$internalType$USER_ID')" value="$NO_PENGENALAN2" size="1" maxlength="2"  />
                                     -
                <input name="NO_PENGENALAN3_$internalType$USER_ID" type="text" id="NO_PENGENALAN3_$internalType$USER_ID" style="width:40px;" onKeyUp="javascript:validateIC_V3(event,this,this.value,'NO_PENGENALAN3_$internalType$USER_ID')" onBlur="getJantinaByIC_V3(this,this.value,'JANTINA_$internalType$USER_ID');"  value="$NO_PENGENALAN3" size="5" maxlength="4" />     
                                
				</td>
			</tr>