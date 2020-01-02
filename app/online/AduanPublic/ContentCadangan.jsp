
#set($classTable = "")
#if($command=="showContentAduan")
#set($classTable = "classFade")
#end

#if(($view.ID_STATUS == "16125" || $view.ID_STATUS	== "") && $mode == "edit")
	#set($mode = "edit")
#else
	#set($mode = "view")
#end

<!--
:::::::: $view.ID_JENISADUAN - 
:::::::: $ID_JENISADUAN
-->
<table width="100%" border="0"  class="$classTable">
			<tr><td colspan="4" style=" border-top:1px solid black;" ></td></tr>
			<tr>
			<td valign="top"  width="1%"></td>
			<td valign="top"  width="28%"></td>
			<td valign="top"  width="1%"></td>
			<td valign="top"  width="70%">
			</td>
			</tr>
            
            
            
            #if($view.ID_JENISADUAN == "161021" || $ID_JENISADUAN == "161021")
            <script>
            document.getElementById("tr_ID_KATEGORIPERTANYAAN_"+'$view.ID_ADUANPUBLIC').style.display = "";			
            </script>  
            #else
            <script>
            document.getElementById("tr_ID_KATEGORIPERTANYAAN_"+'$view.ID_ADUANPUBLIC').style.display = "none";
			document.getElementById("ID_KATEGORIPERTANYAAN_"+'$view.ID_ADUANPUBLIC').value = "";
            </script>          
            #end
            
            
            <tr id="tr_ID_KATEGORIPERTANYAAN_$view.ID_ADUANPUBLIC">
				<td valign="top" >
                #if($mode=="edit")
                <font color="red" >*</font>	
                #end
				</td>			
				<td valign="top" >
				$label_kategori_pertanyaan</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                #if($mode=="edit")
				<select id="ID_KATEGORIPERTANYAAN_$view.ID_ADUANPUBLIC" name="ID_KATEGORIPERTANYAAN_$view.ID_ADUANPUBLIC" >	   
					   <option value = "" >$label_sila_pilih</option>
						#foreach ( $N in $listKATEGORIPERTANYAAN)		
							#set ( $selected_N = "" )
							#if($view.ID_KATEGORIPERTANYAAN==$N.ID_KATEGORIPERTANYAAN)
							#set ( $selected_N = "selected" )
							#end
							<option $selected_N value="$N.ID_KATEGORIPERTANYAAN" >
							$N.KATEGORIPERTANYAAN
							</option>							
						#end
					</select>
                 #else
                 #if($view.KATEGORIPERTANYAAN != "") 
                    #set($span14 = "span14list"+$view.BIL)
                    <span id="$span14">$view.KATEGORIPERTANYAAN</span>
                  	#if($CR_ID_KATEGORIPERTANYAAN != "")
                  	<script>			   
                  	highlight_item(returnDropDownSelectedText_value('CR_ID_KATEGORIPERTANYAAN'),'$span14');
                  	</script>
                  	#end		
                 #else $label_blank #end
                 <input type="hidden" id="ID_KATEGORIPERTANYAAN_$view.ID_ADUANPUBLIC" name="ID_KATEGORIPERTANYAAN_$view.ID_ADUANPUBLIC" value="$view.ID_KATEGORIPERTANYAAN"  />
                 #end
				</td>
			</tr>
            
            
            <tr>
				<td valign="top" >
                #if($mode=="edit")
                <font color="red" >*</font>	
                #end	
				</td>			
				<td valign="top" >
				$label_keterangan_cadangan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                #if($mode=="edit")
				 <textarea style="text-transform:uppercase;" name="KETERANGAN_ADUAN_$view.ID_ADUANPUBLIC" 
				 id="KETERANGAN_ADUAN_$view.ID_ADUANPUBLIC" cols="50" rows="5" 
		 onKeyup="check_length(this,4000,'span_KETERANGAN_ADUAN_$view.ID_ADUANPUBLIC');" >$view.KETERANGAN_ADUAN</textarea>		 		
         <span id="span_KETERANGAN_ADUAN_$view.ID_ADUANPUBLIC"  ></span>
         		#else
                #if($view.KETERANGAN_ADUAN != "") 
                
                #set($span11 = "span11list"+$view.BIL)
                <span id="$span11"> 
                $view.KETERANGAN_ADUAN
                </span>			
                <script>highlight_item('$CR_DETAILS_ADUAN','$span11')</script>  
                
                #else $label_blank #end
                <input type="hidden" name="KETERANGAN_ADUAN_$view.ID_ADUANPUBLIC" id="KETERANGAN_ADUAN_$view.ID_ADUANPUBLIC" value="$view.KETERANGAN_ADUAN"  />
                #end			
				</td>
			</tr>
</table>