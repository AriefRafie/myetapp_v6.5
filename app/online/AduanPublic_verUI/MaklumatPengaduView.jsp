<fieldset> 
    <legend>$legend_pengadu</legend>  
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
            </td>			
            <td valign="top" >
            $label_sumber_pengadu
            </td>
            <td valign="top" >
            :
            </td>
            <td valign="top" >
            #set($span5 = "span5list"+$view.BIL)	   
              <span id="$span5">$view.SUMBER_PENGADU</span>
            #if($CR_ID_SUMBERADUAN != "")
              <script>			   
              highlight_item(returnDropDownSelectedText_value('CR_ID_SUMBERADUAN'),'$span5');
              </script>
            #end	
            
                        			
            <input style="text-transform:uppercase;"  type="hidden" id="ID_SUMBERADUAN_$view.ID_ADUANPUBLIC" 
            name="ID_SUMBERADUAN_$view.ID_ADUANPUBLIC" value="$view.ID_SUMBERADUAN" >            
            </td>
			</tr>
            
           
            
            <tr>
            <td valign="top" >                
            </td>			
            <td valign="top" >
            $label_nama_pengadu
            </td>
            <td valign="top" >
            :
            </td>
            <td valign="top" >
            #if($view.FLAG_HIDE_INFO == "1" && $USER_ROLE != "user_unit_intergriti") 
               	   [$label_maklumat_dilindungi]
            #else
                #set($span2 = "span2list"+$view.BIL)
                <span id="$span2">	
                $view.NAMA_PENGADU 
                </span>
                <script>highlight_item('$CR_NAMA_PENGADU','$span2')</script>    
            #end       			
            <input style="text-transform:uppercase;"  type="hidden" id="ID_PENGADU_$view.ID_ADUANPUBLIC" 
            name="ID_PENGADU_$view.ID_ADUANPUBLIC" value="$view.ID_PENGADU" > 
            <input style="text-transform:uppercase;"  type="hidden" id="NAMA_PENGADU_$view.ID_ADUANPUBLIC" 
            name="NAMA_PENGADU_$view.ID_ADUANPUBLIC" value="$view.NAMA_PENGADU" >            
            </td>
			</tr>
            
            #if($view.TEL_PENGADU!="")
            <tr>
            <td valign="top" >                
            </td>			
            <td valign="top" >
            $label_tel_pengadu
            </td>
            <td valign="top" >
            :
            </td>
            <td valign="top" >
            #if($view.FLAG_HIDE_INFO == "1" && $USER_ROLE != "user_unit_intergriti") 
               	   [$label_maklumat_dilindungi]
            #else
            	$view.TEL_PENGADU
            #end
            </td>
			</tr>
            #end
            
            #if($view.EMEL_PENGADU!="")
            <tr>
            <td valign="top" >                
            </td>			
            <td valign="top" >
            $label_emel_pengadu
            </td>
            <td valign="top" >
            :
            </td>
            <td valign="top" >
            #if($view.FLAG_HIDE_INFO == "1" && $USER_ROLE != "user_unit_intergriti") 
               	   [$label_maklumat_dilindungi]
            #else
            	 
                <a href="mailto:$view.EMEL_PENGADU" style="color:blue;" target="_top"><u>$view.EMEL_PENGADU;</u></a>
            #end    
            </td>
			</tr>
            #end
            
             <input style="text-transform:uppercase;"  type="hidden" id="TEL_PENGADU_$view.ID_ADUANPUBLIC" 
            name="TEL_PENGADU_$view.ID_ADUANPUBLIC" value="$view.TEL_PENGADU" >  
             <input style="text-transform:uppercase;"  type="hidden" id="EMEL_PENGADU_$view.ID_ADUANPUBLIC" 
            name="EMEL_PENGADU_$view.ID_ADUANPUBLIC" value="$view.EMEL_PENGADU" >        
    </table>
    </fieldset>