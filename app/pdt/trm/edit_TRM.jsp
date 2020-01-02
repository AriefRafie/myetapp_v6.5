<!--
:::::: [JENISSUB : $JENISSUB]
[ID_WARTATRMINDUK : $ID_WARTATRMINDUK]
-->

#if(($ID_WARTATRMINDUK != "" && $JENISSUB != ""))
<script>
//alert('div_row$JENISSUB$ID_WARTATRMINDUK$ID_WARTATRM');
//alert('1');
if( $jquery('#'+'div_row$JENISSUB$ID_WARTATRMINDUK$ID_WARTATRM').length ) 
{
	//alert('2');
	window.scrollTo(0, $jquery('#'+'div_row$JENISSUB$ID_WARTATRMINDUK$ID_WARTATRM').offset().top-10);
}
else
{
	//alert('3');
	if( $jquery('#'+'div_row$ID_WARTATRMINDUK').length ) 
	{
		//alert('4');
	 	window.scrollTo(0, $jquery('#'+'div_row$ID_WARTATRMINDUK').offset().top-10);
	}
}
</script>
#else
<script>
	//alert('5');
	if( $jquery('#'+'div_row$view.ID_WARTATRM').length ) 
	{
		//alert('6');
	 	window.scrollTo(0, $jquery('#'+'div_row$view.ID_WARTATRM').offset().top-10);
	}
	else
	{
		if( $jquery('#'+'div_row').length ) 
		{
			//alert('8');
			window.scrollTo(0, $jquery('#'+'div_row').offset().top-10);
		}
	}
</script>
#end









#if($view.ID_WARTATRM == "")
    #if($ID_WARTATRMINDUK != "")
    	#if($setJENISWARTA=="B")
        	#set($view.FLAG_JENISWARTA = "B")
            #set($view.JENISWARTA = "PEMBATALAN") 
            #set($view.ID_NEGERI = $viewInduk.ID_NEGERI) 
            #set($view.ID_DAERAH = $viewInduk.ID_DAERAH)
            #set($view.ID_MUKIM = $viewInduk.ID_MUKIM)           
        #elseif($setJENISWARTA=="G")
        	#set($view.FLAG_JENISWARTA = "G")
            #set($view.JENISWARTA = "PENGGANTIAN") 
        #end  
    #else
    	#set($view.FLAG_JENISWARTA = "W")
        #set($view.JENISWARTA = "PEWUJUDAN")        
    #end    
#end

#set($displayTR = "")
    #if($view.FLAG_JENISWARTA == "B")
    #set($displayTR = "style='display:none;'")
#end


<!--(TEMPID_WARTATRM_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM)-->
<input type="hidden" placeholder="TEMPID_WARTATRM...." id="TEMPID_WARTATRM_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" name="TEMPID_WARTATRM_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" />
<input type="hidden" placeholder="ID_WARTATRM...." id="ID_WARTATRM_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" name="ID_WARTATRM_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" value="$view.ID_WARTATRM"  />

<input type="hidden" placeholder="ID_WARTATRMASAL...." id="ID_WARTATRMASAL_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" name="ID_WARTATRMASAL_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" value="#if($viewWartaAsal.size()>0) $viewWartaAsal.ID_WARTATRM #end"  />

<input type="hidden" id="FLAG_JENISWARTA_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" name="FLAG_JENISWARTA_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" value="$view.FLAG_JENISWARTA"  />
#if($view.ID_WARTATRM == "")
<fieldset >
<legend>	
WARTA $view.JENISWARTA    
</legend>
#end
<table width="100%" border="0">
			<tr>
			<td valign="top"  width="1%"></td>
			<td valign="top"  width="28%"></td>
			<td valign="top"  width="1%"></td>
			<td valign="top"  width="70%">
			</td>
			</tr>
            
            #if($view.ID_WARTATRM != "")
            <tr><td></td><td colspan="3" style="border-bottom:1px solid black; margin-bottom:10px"><strong>WARTA $view.JENISWARTA</strong></td></tr>
            #end
            
            #if($view.JENISWARTA != "")
             <tr  >
				<td valign="top" >
                </td>			
				<td valign="top" >
				Jenis Warta
                </td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
               $view.JENISWARTA
				</td>
			</tr>
            #end
            
           
            <tr $displayTR >
				<td valign="top" >
                #if($mode=="edit")
                <font color="red" >*</font>	
                #end
                </td>			
				<td valign="top" >
				Negeri
                </td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                #if($mode=="edit")
                <select id="ID_NEGERI_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" name="ID_NEGERI_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM"               
                onChange = "document.getElementById('ID_MUKIM_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM').value='';doDivAjaxCall3$formname('tdDaerah_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM','showDaerah','ID_WARTATRM=$view.ID_WARTATRM&ID_WARTATRMINDUK=$ID_WARTATRMINDUK&JENISSUB=$JENISSUB&ID_NEGERI='+this.value);"
				>	   
					   <option value = "" >Sila Pilih</option>
						#foreach ( $N in $listNegeri)		
							#set ( $selected_N = "" )
							#if($view.ID_NEGERI==$N.ID_NEGERI)
							#set ( $selected_N = "selected" )
							#end
							<option $selected_N value="$N.ID_NEGERI" >
							$N.NAMA_NEGERI
							</option>							
						#end
					</select>
                 #else
                 	#if($view.NEGERI != "")                      
                     	$view.NEGERI
                    #else - #end
                 	<input type="hidden" id="ID_NEGERI_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" name="ID_NEGERI_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" value="$view.ID_NEGERI"  />
                 #end
				</td>
			</tr>
         
            
            
            
          
            <tr $displayTR >
				<td valign="top" >
                #if($mode=="edit")
                <font color="red" >*</font>	
                #end
                </td>			
				<td valign="top" >
				Daerah
                </td>
				<td valign="top" >
				:
				</td>
				<td valign="top" id="tdDaerah_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" >
                #if($mode=="edit")
				<select id="ID_DAERAH_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" name="ID_DAERAH_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM"               
                onChange = "doDivAjaxCall3$formname('tdMukim_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM','showMukim','ID_WARTATRM=$view.ID_WARTATRM&ID_WARTATRMINDUK=$ID_WARTATRMINDUK&JENISSUB=$JENISSUB&ID_DAERAH='+this.value);"
				>	   
					   <option value = "" >Sila Pilih</option>
						#foreach ( $N in $listDaerah)		
							#set ( $selected_N = "" )
							#if($view.ID_DAERAH==$N.ID_DAERAH)
							#set ( $selected_N = "selected" )
							#end
							<option $selected_N value="$N.ID_DAERAH" >
							$N.NAMA_DAERAH
							</option>							
						#end
					</select>
                 #else
                 	#if($view.DAERAH != "")                      
                     	$view.DAERAH
                    #else - #end
                 	<input type="hidden" id="ID_DAERAH_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" name="ID_DAERAH_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" value="$view.ID_DAERAH"  />
                 #end
				</td>
			</tr>
            
            
            <tr $displayTR  >
				<td valign="top" >
                #if($mode=="edit")
                <font color="red" >*</font>	
                #end
                </td>			
				<td valign="top" >
				Mukim
                </td>
				<td valign="top" >
				:
				</td>
				<td valign="top" id="tdMukim_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" >
                #if($mode=="edit")
                <select id="ID_MUKIM_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" name="ID_MUKIM_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM"               
                >	   
					   <option value = "" >Sila Pilih</option>
						#foreach ( $N in $listMukim)		
							#set ( $selected_N = "" )
							#if($view.ID_MUKIM==$N.ID_MUKIM)
							#set ( $selected_N = "selected" )
							#end
							<option $selected_N value="$N.ID_MUKIM" >
							$N.NAMA_MUKIM
							</option>							
						#end
					</select>
                 #else
                 	#if($view.MUKIM != "")                      
                     	$view.MUKIM
                    #else - #end
                 	<input type="hidden" id="ID_MUKIM_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" name="ID_MUKIM_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" value="$view.ID_MUKIM"  />
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
				No. Warta
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                #set($type="text")
                #if($mode=="view")
                	#if($view.NO_WARTA != "") 
                    $view.NO_WARTA
                    #else - #end
                    #set($type="hidden")
                #end                
				    <input style="text-transform:uppercase;" size="50" type="$type" id="NO_WARTA_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" 
				name="NO_WARTA_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" 
				value="$view.NO_WARTA" onKeyUp="doDivAjaxCall3$formname('span_CHECK_NO_WARTA_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM','checkWartaDuplicate','ID_WARTATRM=$view.ID_WARTATRM&ID_WARTATRMINDUK=$ID_WARTATRMINDUK&JENISSUB=$JENISSUB');" >
                <span id="span_CHECK_NO_WARTA_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM">
                <input type="hidden" id="CHECK_NO_WARTA_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" name="CHECK_NO_WARTA_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" value="N" >
                </span>				
			  </td>
			</tr>
            
            <tr>
				<td valign="top" >
               	
				</td>			
				<td valign="top" >
				Muat Naik Warta
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                #if($mode=="view")
                
                	#if($view.NAMA_FAIL_WARTA!="")
                    <span class="font_tajuk_sub"  style="cursor:pointer" >
            		<u onClick="paparDoc($view.ID_WARTATRM,'WARTA');">$view.NAMA_FAIL_WARTA</u>
                    </span>
                    #else
                    -
                    #end
        		
                #else
                <span id="div_FAILWARTA_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM">    
                <span class="font_tajuk_sub"  style="cursor:pointer" >
            		<u onClick="paparDoc($view.ID_WARTATRM,'WARTA');">$view.NAMA_FAIL_WARTA</u>
        		</span>  
                
                </span> 
                 <input size="50" type="file" id="FAILWARTA_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" name="FAILWARTA_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM"  onChange="uploadTRM('div_FAILWARTA_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM','saveLampiran','$JENISSUB','$ID_WARTATRMINDUK','$view.ID_WARTATRM','$mode','WARTA','FAILWARTA_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM');" >     
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
				Tarikh Warta
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                #set($type="text")
                #if($mode=="view")
                    #if($view.TARIKH_WARTA != "") $view.TARIKH_WARTA #else - #end
                    #set($type="hidden")
                #end
				    <input name="TARIKH_WARTA_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" 
		onKeyUp="checkFormatDate_V4(this,this.value,'span_CHECK_TARIKH_WARTA_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM');"
        onBlur="checkFormatDate_V4(this,this.value,'span_CHECK_TARIKH_WARTA_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM');" 
        onFocus="checkFormatDate_V4(this,this.value,'span_CHECK_TARIKH_WARTA_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM');" 			
		type="$type" id="TARIKH_WARTA_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" style="text-transform:uppercase;" value="$view.TARIKH_WARTA" size="12" maxlength="10"  />
       #if($mode=="edit")
       <a href="javascript:displayDatePicker('TARIKH_WARTA_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> 
       #end
       <span id="span_CHECK_TARIKH_WARTA_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM">
       <input type='hidden' id='CHECK_TARIKH_WARTA_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM' name='CHECK_TARIKH_WARTA_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM' value='true' >
       </span>								
			  </td>
			</tr>
            
            
            <tr $displayTR >
				<td valign="top" >
               		
				</td>			
				<td valign="top" >
				Kawasan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                #set($type="text")
                #if($mode=="view")
                	#if($view.KAWASAN != "") 
                    $view.KAWASAN
                    #else - #end
                    #set($type="hidden")
                #end                
				    <input style="text-transform:uppercase;" size="50" type="$type" id="KAWASAN_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" 
				name="KAWASAN_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" 
				value="$view.KAWASAN" >				
			  </td>
			</tr>
          
            
            
            <tr>
				<td valign="top" >
                </td>			
				<td valign="top" >
				No. Pelan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                #set($type="text")
                #if($mode=="view")
                	#if($view.NO_PELAN != "") 
                    $view.NO_PELAN
                    #else - #end
                    #set($type="hidden")
                #end                
				    <input style="text-transform:uppercase;" size="50" type="$type" id="NO_PELAN_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" 
				name="NO_PELAN_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" 
				value="$view.NO_PELAN" >				
			  </td>
			</tr>
            
            
            
            
            <tr>
				<td valign="top" >
               	
				</td>			
				<td valign="top" >
				Muat Naik Pelan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                #if($mode=="view")
                
                	#if($view.NAMA_FAIL_PELAN!="")
                    <span class="font_tajuk_sub"  style="cursor:pointer" >
            		<u onClick="paparDoc($view.ID_WARTATRM,'PELAN');">$view.NAMA_FAIL_PELAN</u>
                    </span>
                    #else
                    -
                    #end
        		
                #else
                <span id="div_FAILPELAN_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM">    
                <span class="font_tajuk_sub"  style="cursor:pointer" >
            		<u onClick="paparDoc($view.ID_WARTATRM,'PELAN');">$view.NAMA_FAIL_PELAN</u>
        		</span>  
                </span> 
                 <input size="50" type="file" id="FAILPELAN_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" name="FAILPELAN_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM"  onChange="uploadTRM('div_FAILPELAN_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM','saveLampiran','$JENISSUB','$ID_WARTATRMINDUK','$view.ID_WARTATRM','$mode','PELAN','FAILPELAN_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM');" >     
               
                
                          
              
                
				</td>
			</tr>
            #end
            
            
            #if($view.FLAG_JENISWARTA == "W" && $view.ID_WARTATRM != "")
            <tr >
				<td valign="top" >
                </td>			
				<td valign="top" >
				Luas Asal (Hektar)
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >                
                   
                    <font color="blue">$view.LUAS_ASAL_DISPLAY</font>              			
			  </td>
			</tr>
            #end
            
            
            #if($view.LUAS_BATAL > 0) 
             <tr >
				<td valign="top" >
                </td>			
				<td valign="top" >
				Luas Pembatalan (Hektar)
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >                
                    $view.LUAS_BATAL_DISPLAY                			
			  </td>
			</tr>
            #end
            
           #if($view.LUAS_GANTI > 0)
           <tr >
				<td valign="top" >
                </td>			
				<td valign="top" >
				Luas Penggantian (Hektar)
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >                
                    $view.LUAS_GANTI_DISPLAY               			
			  </td>
			</tr>
            #end
            
           
            #if($view.KOSONG > 0)   
             <tr >
				<td valign="top" >
                </td>			
				<td valign="top" >
				Luas Tidak Diganti (Hektar)
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >  
                	           
                    <font color="red"><span class="blink"><b>$view.KOSONG_DISPLAY</b></span></font>
                                 			
			  </td>
			</tr>
         	#end
            
            
            
            <tr >
				<td valign="top" >
                #if($mode=="edit")
                <font color="red" >*</font>	
                #end
                </td>			
				<td valign="top" >
				Luas Semasa (Hektar)
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                #set($type="text")
                #if($mode=="view")
                	#if($view.LUAS != "") 
                    
                        #if($view.LUAS < 0)
                            <font color="red"><b>$view.LUAS_DISPLAY</b></font>
                        #else
                            $view.LUAS_DISPLAY
                        #end
                    	
                    #else - #end
                    #set($type="hidden")
                #end                
				
                <input style="text-transform:uppercase;"   type="$type" id="LUAS_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" 
				name="LUAS_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" value="$view.LUAS" onkeypress="return isNumberKey(event,this.value,this)"
                 onblur="convertFourDec(this.value,this)" onkeyup="checkLuas(this,'LUASASAL_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM','$view.FLAG_JENISWARTA','LUAS_BEFORE_EDIT_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM');"  >	
                 <span id="span_CHECK_LUAS_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" ></span>
                 <input type="hidden"  id="CHECK_LUAS_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" name="CHECK_LUAS_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" value="N"  />
                 
                 <input type="hidden" id="LUAS_BEFORE_EDIT_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" name="LUAS_BEFORE_EDIT_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" value="$view.LUAS"  />
                 #set($LUAS_ASAL="")
                 #if($viewInduk.size()>0)
                 	#set($LUAS_ASAL=$viewInduk.LUAS)
                 #end
                 
                 
                
                 <input style="text-transform:uppercase;"   type="hidden" id="LUASASAL_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" 
				name="LUASASAL_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" value="$LUAS_ASAL" onkeypress="return isNumberKey(event,this.value,this)"
                 onblur="convertFourDec(this.value,this)"  >				
			  </td>
			</tr>
            
            
            
            
            <tr  >
				<td valign="top" >
                #if($mode=="edit")
                <font color="red" >*</font>	
                #end
                </td>			
				<td valign="top" >
				Status
                </td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                #if($mode=="edit")
                <select id="FLAG_STATUSWARTA_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" name="FLAG_STATUSWARTA_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" >	   
					   <option value = "" >Sila Pilih</option>
                       <option value = "K" #if($view.FLAG_STATUSWARTA == 'K') selected="selected" #end >KUATKUASA</option>
                       <option value = "T" #if($view.FLAG_STATUSWARTA == 'T') selected="selected" #end >TIDAK KUATKUASA</option>
                       <option value = "X" #if($view.FLAG_STATUSWARTA == 'X') selected="selected" #end >TIDAK DIKETAHUI</option>						
				</select>
                 #else
                 	#if($view.FLAG_STATUSWARTA != "")                      
                     	$view.STATUSWARTA
                    #else - #end
                 	<input type="hidden" id="FLAG_STATUSWARTA_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" name="FLAG_STATUSWARTA_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" value="$view.FLAG_STATUSWARTA"  />
                 #end
				</td>
			</tr>            
</table>
	
#if($view.ID_WARTATRM == "")
</fieldset>
#end

<table width="100%" border="0">
			<tr>
			<td valign="top"  width="1%"></td>
			<td valign="top"  width="28%"></td>
			<td valign="top"  width="1%"></td>
			<td valign="top"  width="70%" >
			</td>
			</tr>
            
            
            
	<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >				
				</td>
				<td valign="top" >				
				</td>
				<td valign="top" >	
                
                <!-- ::::::::  $commandFrom -->
                
                #set($divClose = "div_row"+$JENISSUB+$ID_WARTATRMINDUK)
                #if($commandFrom=="list")
                	#set($divClose = "div_row"+$JENISSUB+$ID_WARTATRMINDUK+$view.ID_WARTATRM)
                #end  
                
              
                
                #if($mode=="edit")
                <span id="showButtonEdit$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM"  >
                 <input type="button" id="BTNSAVE$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" name="BTNSAVE$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" 
                    onClick="if(valEdit('$view.ID_WARTATRM','$JENISSUB','$ID_WARTATRMINDUK')==true){if(confirm('Adakah anda pasti?')){doDivAjaxCall$formname('div_row$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM','save','mode=view&commandFrom=$commandFrom&BIL=$BIL&rowCss=$rowCss&ID_WARTATRM=$view.ID_WARTATRM&ID_WARTATRMINDUK=$ID_WARTATRMINDUK&JENISSUB=$JENISSUB&TEMPID_WARTATRM='+document.getElementById('TEMPID_WARTATRM_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM').value);}}" value="Simpan" >               	
                <input type="button" id="BTNBTL$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" name="BTNBTL$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" onClick="doDivAjaxCall3$formname('div_row$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM','edit','mode=edit&commandFrom=$commandFrom&BIL=$BIL&rowCss=$rowCss&ID_WARTATRM=$view.ID_WARTATRM&ID_WARTATRMINDUK=$ID_WARTATRMINDUK&JENISSUB=$JENISSUB&setJENISWARTA=$setJENISWARTA');" value="Batal" >               </span>
                #else                
               <span id="showButtonView$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM"  >
                <input type="button" id="BTNUPD$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" name="BTNUPD$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" onClick="doDivAjaxCall3$formname('div_row$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM','edit','mode=edit&commandFrom=$commandFrom&BIL=$BIL&rowCss=$rowCss&ID_WARTATRM=$view.ID_WARTATRM&ID_WARTATRMINDUK=$ID_WARTATRMINDUK&JENISSUB=$JENISSUB');" value="Kemaskini" >
                <!--$divClose <br /> div_Senarai$JENISSUB$ID_WARTATRMINDUK-->
                <input type="button" id="BTNDEL$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" name="BTNDEL$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" onClick="if(confirm('Adakah Anda Pasti!')){doDivAjaxCall3$formname('div_Senarai$JENISSUB$ID_WARTATRMINDUK','delete','mode=edit&commandFrom=$commandFrom&BIL=$BIL&rowCss=$rowCss&ID_WARTATRM=$view.ID_WARTATRM&ID_WARTATRMINDUK=$ID_WARTATRMINDUK&JENISSUB=$JENISSUB');}" value="Hapus" >
               <!-- <input type="button" id="BTNPRINT$view.ID_WARTATRM" name="BTNPRINT$view.ID_WARTATRM" 
                    onclick="printWartaForm('printableArea_$view.ID_WARTATRM')" 
                    value="Cetak Maklumat Warta" /> -->
                    
                    
                    
                </span>                
                #end
                
                
               
               <!-- :::::::::::: $commandFrom  -->
                <input type="button" id="BTNCLOSE$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" name="BTNCLOSE$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" onClick="doDivAjaxCall3$formname('$divClose','close','commandFrom=$commandFrom&BIL=$BIL&rowCss=$rowCss&ID_WARTATRM=$view.ID_WARTATRM&ID_WARTATRMINDUK=$ID_WARTATRMINDUK&JENISSUB=$JENISSUB');" value="Tutup" > 
                
	   			</td>
			</tr>
	</table>
    
    #if($view.ID_WARTATRM!="")
        #if($command == "reloadTRM" || $command == "reloadTRMFromHistory")
        <script> 
        
        var LUASASALINDUK = document.getElementById("LUAS_$view.ID_WARTATRM").value;
        //alert('LUASASALINDUK : '+LUASASALINDUK);	
        if(LUASASALINDUK!="")
        {
            if(parseFloat(LUASASALINDUK)<=0)
            {
                document.getElementById("BTNADDB$view.ID_WARTATRM").style.display = "none";
            }
            else
            {
                document.getElementById("BTNADDB$view.ID_WARTATRM").style.display = "";
            }
        }	
            
        if('$command' == "reloadTRM")
        {
          $jquery(document).ready(function () {		  
              doDivAjaxCall$formname('div_ListHISTORY$view.ID_WARTATRM','displayUtamaHISTORY','&carianTerperinciHISTORY=&TARIKH_MULA_HISTORY=&TARIKH_AKHIR_HISTORY=&ID_WARTATRM=$view.ID_WARTATRM&FLAG_DEFAULT_HISTORY=Y&ID_WARTATRMINDUK=&JENISSUB=&commandFrom=$commandFrom&BIL=$BIL&rowCss=$rowCss');		 	  
          });
        }
        </script>	
        #elseif($mode == "view" && ($JENISSUB == "B" || $JENISSUB == "G") && $command == "save" && $ID_WARTATRMINDUK != "")
        <script>
        doDivAjaxCall$formname('div_editTRM$ID_WARTATRMINDUK','reloadTRM','ID_WARTATRM=$ID_WARTATRMINDUK&BIL=$BIL&rowCss=$rowCss&commandFrom=$commandFrom&BIL=$BIL&rowCss=$rowCss');
        </script>
        #end
    
    #else
    	#if($command == "add")
		<script>
        
        if('$ID_WARTATRMINDUK' == "")
        {
            doDivAjaxCall$formname('div_Senarai','showSenarai','OPENCLOSE_CARIAN=close&FLAG_CARIAN=N');
        }
		        
        </script>
        #end
           
    #end
   
   
   
    
    