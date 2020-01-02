<fieldset> 
    <legend>$legend_pengadu</legend>  
    
    #set($icon_mandatory = "")
    #if($USER_ROLE == "user_unit_intergriti" && ($view.ID_ADUANPUBLIC == "" || ($view.ID_ADUANPUBLIC != "" && $view.ID_PENGADU == "")))
    	#set($icon_mandatory = "*")      
    #end   
     
    <table width="100%" border="0">
			<tr>
			<td valign="top"  width="1%"></td>
			<td valign="top"  width="28%"></td>
			<td valign="top"  width="1%"></td>
			<td valign="top"  width="70%">
			</td>
			</tr>
            <tr>
            <td valign="top" >  
            <font color="red" >$icon_mandatory</font>	       
            </td>			
            <td valign="top" >
            $label_sumber_pengadu
            </td>
            <td valign="top" >
            :
            </td>
            <td valign="top" >
            #if($USER_ROLE == "user_unit_intergriti" && ($view.ID_ADUANPUBLIC == "" || ($view.ID_ADUANPUBLIC != "" && $view.ID_PENGADU == "")))
            
            <select id="ID_SUMBERADUAN_$view.ID_ADUANPUBLIC" name="ID_SUMBERADUAN_$view.ID_ADUANPUBLIC" >	   
					   <option value = "" >$label_sila_pilih</option>
						#foreach ( $N in $listJenisSumberPengadu)		
							#set ( $selected_N = "" )
							#if($view.ID_SUMBERADUAN==$N.ID_SUMBERADUAN)
							#set ( $selected_N = "selected" )
							#end
                            #if($N.ID_SUMBERADUAN != "16101")
                                <option $selected_N value="$N.ID_SUMBERADUAN" >
                                $N.NAMA_SUMBER
                                </option>	
                            #end						
						#end
			</select>
            
            #else
                $view.SUMBER_PENGADU            			
                <input style="text-transform:uppercase;"  type="hidden" id="ID_SUMBERADUAN_$view.ID_ADUANPUBLIC" 
                name="ID_SUMBERADUAN_$view.ID_ADUANPUBLIC" value="$view.ID_SUMBERADUAN" >   
            #end
            
            
            
            
                    
            </td>
			</tr>
            <tr>
            <td valign="top" >  
            <font color="red" >$icon_mandatory</font>	               
            </td>			
            <td valign="top" >
            $label_nama_pengadu
            </td>
            <td valign="top" >
            :
            </td>
            <td valign="top" >
            #set($setHiddenField = "hidden")
            #if($USER_ROLE == "user_unit_intergriti" && ($view.ID_ADUANPUBLIC == "" || ($view.ID_ADUANPUBLIC != "" && $view.ID_PENGADU == "")))
            	#set($setHiddenField = "text")
            #else
            	$view.NAMA_PENGADU 
            #end    
            <input style="text-transform:uppercase;" size="50" type="hidden" id="ID_PENGADU_$view.ID_ADUANPUBLIC" 
            name="ID_PENGADU_$view.ID_ADUANPUBLIC" value="$view.ID_PENGADU" >            
            <input style="text-transform:uppercase;" size="50" type="$setHiddenField" id="NAMA_PENGADU_$view.ID_ADUANPUBLIC" 
            name="NAMA_PENGADU_$view.ID_ADUANPUBLIC" value="$view.NAMA_PENGADU" >            
            </td>
			</tr>
            <tr>
            <td valign="top" > 
            <font color="red" >$icon_mandatory</font>	                
            </td>			
            <td valign="top" >
            $label_tel_pengadu
            </td>
            <td valign="top" >
            :
            </td>
            <td valign="top" >
            #set($setHiddenField = "hidden")
            #if($USER_ROLE == "user_unit_intergriti" && ($view.ID_ADUANPUBLIC == "" || ($view.ID_ADUANPUBLIC != "" && $view.ID_PENGADU == "")))
            	#set($setHiddenField = "text")
            #else
            	$view.TEL_PENGADU
            #end                         			
            <input style="text-transform:uppercase;"  onKeyUp = "RemoveNonNumeric_V3(this,this.value)" size="50" maxlength="20" type="$setHiddenField" id="TEL_PENGADU_$view.ID_ADUANPUBLIC" 
            name="TEL_PENGADU_$view.ID_ADUANPUBLIC" value="$view.TEL_PENGADU" >            
            </td>
			</tr>
            <tr>
            <td valign="top" > 
            <font color="red" >$icon_mandatory</font>	                
            </td>			
            <td valign="top" >
            $label_emel_pengadu
            </td>
            <td valign="top" >
            :
            </td>
            <td valign="top" >
            #set($setHiddenField = "hidden")
            #if($USER_ROLE == "user_unit_intergriti" && ($view.ID_ADUANPUBLIC == "" || ($view.ID_ADUANPUBLIC != "" && $view.ID_PENGADU == "")))
            	#set($setHiddenField = "text")
            #else
            	$view.EMEL_PENGADU
            #end                       			
            <input  type="$setHiddenField" id="EMEL_PENGADU_$view.ID_ADUANPUBLIC" 
            name="EMEL_PENGADU_$view.ID_ADUANPUBLIC" value="$view.EMEL_PENGADU" size="50" maxlength="100" >            
            </td>
			</tr>
            
    </table>
    </fieldset>