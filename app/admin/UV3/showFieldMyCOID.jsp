<tr id="div_NO_PENGENALAN$internalType$USER_ID">
				<td valign="top" >	
				<font color="red" >*</font>				
				</td>			
				<td valign="top" >
				MyCOID (User Login)								
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				<input name="NO_PENGENALAN_$internalType$USER_ID" type="text" id="NO_PENGENALAN_$internalType$USER_ID" size="50"  value="$NO_PENGENALAN" onkeyup="copyValueIC(event,this,this.value,'USER_LOGIN_$internalType$USER_ID',$jquery('#KATEGORI_$internalType$USER_ID').val());" />
                
               #set($login_kp1="hidden")
					#set($login_kp2="hidden")
					#set($login_kp3="hidden")	
                
                <input name="NOKP1_$internalType$USER_ID" type="$login_kp1" 
				id="NOKP1_$internalType$USER_ID" style="width:50px;" value="x" size="7" maxlength="6"  
				onBlur="getAgeByIC_V3(this,this.value,'UMUR_$internalType$USER_ID');getDOBByIC_V3(this,this.value,'TARIKH_LAHIR_$internalType$USER_ID','span_NOKP1_$internalType$USER_ID');"  />
               
               	<span id="span_NOKP1_$internalType$USER_ID">
                <input type="hidden" id="CHECK_NOKP1_$internalType$USER_ID" 
				name="CHECK_NOKP1_$internalType$USER_ID" value="true" >
                </span> 
                                     &nbsp; 
                <input name="NOKP2_$internalType$USER_ID" type="$login_kp2" 
                id="NOKP2_$internalType$USER_ID" style="width:20px;" 
                value="x" size="1" maxlength="2"  />
                                     &nbsp; 
                <input name="NOKP3_$internalType$USER_ID" type="$login_kp3" 
                id="NOKP3_$internalType$USER_ID" style="width:40px;" onBlur="getJantinaByIC_V3(this,this.value,'JANTINA_$internalType$USER_ID');"  
                value="x" size="5" maxlength="4" />  
             </td>
</tr>