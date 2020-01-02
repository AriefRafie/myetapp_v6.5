#set($classTable = "")
#if($command=="showContentAduan")
#set($classTable = "classFade")
#end

#if(($view.ID_STATUS	== "16125" || $view.ID_STATUS	== "") && $mode == "edit")
	#set($mode = "edit")
#else
	#set($mode = "view")
#end

<table width="100%" border="0"  class="$classTable">
			<tr><td colspan="4" style=" border-top:1px solid black;" ></td></tr>
			<tr>
			<td valign="top"  width="1%"></td>
			<td valign="top"  width="28%"></td>
			<td valign="top"  width="1%"></td>
			<td valign="top"  width="70%">
			</td>
			</tr>
            <tr>
				<td valign="top" >
                #if($mode=="edit")
                <font color="red" >*</font>	
                #end
				</td>			
				<td valign="top" >
				$label_kategori_aduan</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                #if($mode=="edit")
				<select id="ID_KATEGORIADUAN_$view.ID_ADUANPUBLIC" name="ID_KATEGORIADUAN_$view.ID_ADUANPUBLIC" >	   
					   <option value = "" >$label_sila_pilih</option>
						#foreach ( $N in $listKategoriAduan)		
							#set ( $selected_N = "" )
							#if($view.ID_KATEGORIADUAN==$N.ID_KATEGORIADUAN)
							#set ( $selected_N = "selected" )
							#end
							<option $selected_N value="$N.ID_KATEGORIADUAN" >
							$N.KATEGORIADUAN
							</option>							
						#end
					</select>
                 #else
                 #if($view.KATEGORIADUAN != "") 
                    #set($span4 = "span4list"+$view.BIL)
                    <span id="$span4">$view.KATEGORIADUAN</span>
                  	#if($CR_ID_KATEGORIADUAN != "")
                  	<script>			   
                  	highlight_item(returnDropDownSelectedText_value('CR_ID_KATEGORIADUAN'),'$span4');
                  	</script>
                  	#end		
                 #else $label_blank #end
                 <input type="hidden" id="ID_KATEGORIADUAN_$view.ID_ADUANPUBLIC" name="ID_KATEGORIADUAN_$view.ID_ADUANPUBLIC" value="$view.ID_KATEGORIADUAN"  />
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
				$label_subjek
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                #set($type="text")
                #if($mode=="view")
                    #if($view.SUBJEK_ADUAN != "") 
                    
                    #set($span9 = "span9list"+$view.BIL)
                    <span id="$span9"> 
                    $view.SUBJEK_ADUAN
                    </span>			
                    <script>highlight_item('$CR_DETAILS_ADUAN','$span9')</script>  
                    
                    
                    #else $label_blank #end
                    #set($type="hidden")
                #end                
				    <input style="text-transform:uppercase;" size="50" type="$type" id="SUBJEK_ADUAN_$view.ID_ADUANPUBLIC" 
				name="SUBJEK_ADUAN_$view.ID_ADUANPUBLIC" 
				value="$view.SUBJEK_ADUAN" >				
			  </td>
			</tr>
            
            <tr>
			  <td valign="top" >
				</td>			
				<td valign="top" >
                $label_tarikh_kejadian
                </td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                #set($type="text")
                #if($mode=="view")
                    #if($view.TARIKH_KEJADIAN != "") $view.TARIKH_KEJADIAN #else $label_blank #end
                    #set($type="hidden")
                #end
				    <input name="TARIKH_KEJADIAN_$view.ID_ADUANPUBLIC" 
		onKeyUp="checkFormatDate_V4(this,this.value,'span_CHECK_TARIKH_KEJADIAN_$view.ID_ADUANPUBLIC');"
        onBlur="checkFormatDate_V4(this,this.value,'span_CHECK_TARIKH_KEJADIAN_$view.ID_ADUANPUBLIC');" 
        onFocus="checkFormatDate_V4(this,this.value,'span_CHECK_TARIKH_KEJADIAN_$view.ID_ADUANPUBLIC');" 			
		type="$type" id="TARIKH_KEJADIAN_$view.ID_ADUANPUBLIC" style="text-transform:uppercase;" value="$view.TARIKH_KEJADIAN" size="12" maxlength="10"  />
       #if($mode=="edit")
       <a href="javascript:displayDatePicker('TARIKH_KEJADIAN_$view.ID_ADUANPUBLIC',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> 
       #end
       <span id="span_CHECK_TARIKH_KEJADIAN_$view.ID_ADUANPUBLIC">
       <input type='hidden' id='CHECK_TARIKH_KEJADIAN_$view.ID_ADUANPUBLIC' name='CHECK_TARIKH_KEJADIAN_$view.ID_ADUANPUBLIC' value='true' >
       </span>				
			  </td>
			</tr>
            
            <tr>
				<td valign="top" >
				</td>			
				<td valign="top" >
				$label_masa_kejadian
				</td>
				<td valign="top" >
				:
				</td>
			  <td valign="top" >
                #if($mode=="edit")
				<select id="JAM_$view.ID_ADUANPUBLIC" name="JAM_$view.ID_ADUANPUBLIC" class="smallselect">	   
					   <option value = "" >$label_sila_pilih_jam</option>
						#foreach ( $N in $listJam)		
							#set ( $selected_N = "" )
							#if($view.JAM==$N.JAM)
							#set ( $selected_N = "selected" )
							#end
							<option $selected_N value="$N.JAM" >
							$N.JAM
							</option>							
						#end
				</select>
                :
                <select id="MINIT_$view.ID_ADUANPUBLIC" name="MINIT_$view.ID_ADUANPUBLIC"  class="smallselect">	   
					   <option value = "" >$label_sila_pilih_minit</option>
						#foreach ( $N in $listMinit)		
							#set ( $selected_N = "" )
							#if($view.MINIT==$N.MINIT)
							#set ( $selected_N = "selected" )
							#end
							<option $selected_N value="$N.MINIT" >
							$N.MINIT
							</option>							
						#end
				</select>
                
                <select id="JENIS_WAKTU_$view.ID_ADUANPUBLIC" name="JENIS_WAKTU_$view.ID_ADUANPUBLIC"  class="smallselect">	   
					   <option value = "" >$label_sila_pilih_ampm</option>
                       
                       	#set($selectedAM = "")
                        #set($selectedPM = "")
                        
						#if($view.JENIS_WAKTU=="AM")
                        	#set($selectedAM = "selected")
                        #elseif($view.JENIS_WAKTU=="PM")
							#set($selectedPM = "selected")			
						#end
                        
                        <option $selectedAM value = "AM" >AM</option>	
                        <option $selectedPM value = "PM" >PM</option>	
				</select>
                #else
                    #if($view.JAM != "")
                    $view.JAM : $view.MINIT $view.JENIS_WAKTU
                    #else
                    $label_blank
                    #end
                  <input type="hidden" id="JAM_$view.ID_ADUANPUBLIC" name="JAM_$view.ID_ADUANPUBLIC" value="$view.JAM"  />
                <input type="hidden" id="MINIT_$view.ID_ADUANPUBLIC" name="MINIT_$view.ID_ADUANPUBLIC" value="$view.MINIT"  />
                    <input type="hidden" id="JENIS_WAKTU_$view.ID_ADUANPUBLIC" name="JENIS_WAKTU_$view.ID_ADUANPUBLIC" value="$view.JENIS_WAKTU"  />
                #end
				
				
			  </td>
			</tr>
            <tr>
				<td valign="top" >
				</td>			
				<td valign="top" >
				$label_lokasi_kejadian
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                #set($type="text")
                #if($mode=="view")
                    #if($view.LOKASI_KEJADIAN != "") $view.LOKASI_KEJADIAN #else $label_blank #end
                    #set($type="hidden")
                #end
				    <input style="text-transform:uppercase;" size="50" type="$type" id="LOKASI_KEJADIAN_$view.ID_ADUANPUBLIC" 
				name="LOKASI_KEJADIAN_$view.ID_ADUANPUBLIC" 
				value="$view.LOKASI_KEJADIAN" >				
			  </td>
			</tr>
            
            <tr>
				<td valign="top" >
                </td>			
				<td valign="top" >
				$label_negeri</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                #if($mode=="edit")
				<select id="ID_NEGERI_$view.ID_ADUANPUBLIC" name="ID_NEGERI_$view.ID_ADUANPUBLIC"               
                onChange = "doDivAjaxCall3$formname('tdDaerah_$view.ID_ADUANPUBLIC','showDaerah','ID_ADUANPUBLIC=$view.ID_ADUANPUBLIC&ID_NEGERI='+this.value);"
				>	   
					   <option value = "" >$label_sila_pilih</option>
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
                     	#set($span6 = "span6list"+$view.BIL)
                        <span id="$span6">$view.NEGERI</span>
                        #if($CR_ID_NEGERI != "")
                        <script>			   
                        highlight_item(returnDropDownSelectedText_value('CR_ID_NEGERI'),'$span6');
                        </script>
                        #end		
                    #else $label_blank #end
                 	<input type="hidden" id="ID_NEGERI_$view.ID_ADUANPUBLIC" name="ID_NEGERI_$view.ID_ADUANPUBLIC" value="$view.ID_NEGERI"  />
                 #end
				</td>
			</tr>
            <tr>
				<td valign="top" >
                </td>			
				<td valign="top" >
				$label_daerah</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" id="tdDaerah_$view.ID_ADUANPUBLIC" >
                #if($mode=="edit")
				<select id="ID_DAERAH_$view.ID_ADUANPUBLIC" name="ID_DAERAH_$view.ID_ADUANPUBLIC" >	   
					   <option value = "" >$label_sila_pilih</option>
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
                    
                    	#set($span7 = "span7list"+$view.BIL)
                        <span id="$span7">$view.DAERAH</span>
                        #if($CR_ID_DAERAH != "")
                        <script>			   
                        highlight_item(returnDropDownSelectedText_value('CR_ID_DAERAH'),'$span7');
                        </script>
                        #end
                    #else $label_blank #end
                	<input type="hidden" id="ID_DAERAH_$view.ID_ADUANPUBLIC" name="ID_DAERAH_$view.ID_ADUANPUBLIC" value="$view.ID_DAERAH"  />
                #end
				</td>
			</tr>
            <tr>
				<td valign="top" >
				</td>			
				<td valign="top" >
				$label_pihak_telibat
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                #set($type="text")
                #if($mode=="view")
                    #if($view.NAMA_PIHAK_TERLIBAT != "") $view.NAMA_PIHAK_TERLIBAT #else $label_blank #end
                    #set($type="hidden")
                #end
				    <input style="text-transform:uppercase;" size="50" type="$type" id="NAMA_PIHAK_TERLIBAT_$view.ID_ADUANPUBLIC" 
				name="NAMA_PIHAK_TERLIBAT_$view.ID_ADUANPUBLIC" 
				value="$view.NAMA_PIHAK_TERLIBAT" >				
			  </td>
			</tr>
            <tr>
				<td valign="top" >
                #if($mode=="edit")
                <font color="red" >*</font>	
                #end	
				</td>			
				<td valign="top" >
				$label_keterangan_aduan
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
                
                #set($span10 = "span10list"+$view.BIL)
                <span id="$span10"> 
                $view.KETERANGAN_ADUAN
                </span>			
                <script>highlight_item('$CR_DETAILS_ADUAN','$span10')</script>  
                
                
                 #else $label_blank #end
                <input type="hidden" name="KETERANGAN_ADUAN_$view.ID_ADUANPUBLIC" id="KETERANGAN_ADUAN_$view.ID_ADUANPUBLIC" value="$view.KETERANGAN_ADUAN"  />
                #end			
				</td>
			</tr>
            
</table>