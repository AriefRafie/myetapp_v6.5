
#set($modeUI = $mode)
#set($showSkrinUI = "none")
#if($USER_ROLE == "user_unit_intergriti" && ($view.ID_STATUS == "16122" || $view.ID_STATUS == "16121"))

	#if($view.ID_PEGAWAI_UI == $USER_ID_SYSTEM)
        #set($modeUI = "edit")
        #set($showSkrinUI = "")
    #else   		
    <script>
	//alert('1');
	var alert_mesej_ui = '$alert_action_taken_by_ui_officer $view.PEGAWAI_UI'+'!';
	alert(alert_mesej_ui.toUpperCase());
	document.getElementById('divDisplayAlert_$view.ID_ADUANPUBLIC').innerHTML = "<br><fieldset><div class='mq_css'><h3><font  style = 'color:red' ><b>"+alert_mesej_ui.toUpperCase()+"</b></font></h3></div></fieldset>";
	//alert('1a');
	</script>
   
    #end
    
#elseif(($USER_ROLE == "wakil_bahagian_aduan"  || $USER_ROLE == "user_unit_intergriti") && ($view.ID_STATUS == "16126" || $view.ID_STATUS == "16123" || $view.ID_STATUS == "16127"))
	#set($modeUI = "view")
    #set($showSkrinUI = "")
#elseif($view.ID_STATUS == "16123") 

	#if(($USER_ROLE == "online" || $USER_ROLE == "php-online-user" || $USER_ROLE == "ppk-online-user" || $USER_ROLE == "ppt-online-user") 
&& $ID_SUMBERTINDAKAN == "16101" )  
	#set($modeUI = "$mode")
    #set($showSkrinUI = "none")
#else
	#set($modeUI = "view")
    #set($showSkrinUI = "")
#end 

    
#end   


<script>
document.getElementById('divSkrinUI_$view.ID_ADUANPUBLIC').style.display = "$showSkrinUI";
</script>

<fieldset > 
<legend>$legend_form_tindakan_ui</legend>
<table width="100%" border="0"  >
			<tr><td colspan="4" ></td></tr>
			<tr>
			<td valign="top"  width="1%"></td>
			<td valign="top"  width="28%"></td>
			<td valign="top"  width="1%"></td>
			<td valign="top"  width="70%">
			</td>
			</tr>
            #if($modeUI == "view")
            #if($view.PEGAWAI_UI != "")
            <tr >
				<td valign="top" >
                </td>			
				<td valign="top" >
				Pegawai Unit Intergriti
                </td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                $view.PEGAWAI_UI
				</td>
			</tr>
            #end
            #if($view.EMEL_PEGAWAI_UI != "")
            <tr >
				<td valign="top" >
                </td>			
				<td valign="top" >
				Emel Pegawai Unit Intergriti
                </td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                $view.EMEL_PEGAWAI_UI
				</td>
			</tr>
            #end
            #if($view.NO_TEL_PEGAWAI_UI != "")
            <tr >
				<td valign="top" >
                </td>			
				<td valign="top" >
				No. Tel. Unit
                </td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                $view.NO_TEL_PEGAWAI_UI
				</td>
			</tr>
            #end           
            #end
            
            <tr>
				<td valign="top" >
                #if($modeUI=="edit")
                <font color="red" >*</font>	
                #end
				</td>			
				<td valign="top" >
				$label_jenis_tindakan_aduan
                </td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                #if($modeUI=="edit")                
                    #if($view.ID_JENISTINDAKAN=="" && ($view.ID_STATUS == "16121" || $view.ID_STATUS == "16122"))
                        #set($view.ID_JENISTINDAKAN = "16176")
                    #end
					<select id="ID_JENISTINDAKAN_$view.ID_ADUANPUBLIC" name="ID_JENISTINDAKAN_$view.ID_ADUANPUBLIC"       
                    onChange = "doDivAjaxCall3$formname('tdJenisTindakan$view.ID_ADUANPUBLIC','showBahagian','ID_ADUANPUBLIC=$view.ID_ADUANPUBLIC&mode=edit&ID_JENISTINDAKAN='+this.value);" >	   
					   <option value = "" >$label_sila_pilih</option>
						#foreach ( $N in $listJenisTindakan)		
							#set ( $selected_N = "" )
							#if($view.ID_JENISTINDAKAN==$N.ID_KATEGORITINDAKAN)
							#set ( $selected_N = "selected" )
							#end
							<option $selected_N value="$N.ID_KATEGORITINDAKAN" >
							$N.KATEGORITINDAKAN
							</option>							
						#end
					</select>
                 #else
                     #if($view.KATEGORITINDAKAN != "") 
                        #set($span11 = "span11list"+$view.BIL)
                        <span id="$span11">$view.KATEGORITINDAKAN</span>
                        #if($CR_ID_JENISTINDAKAN != "")
                        <script>			   
                        highlight_item(returnDropDownSelectedText_value('CR_ID_JENISTINDAKAN'),'$span11');
                        </script>
                        #end  
                     #else 
                        $label_blank 
                     #end
                     <input type="hidden" id="ID_JENISTINDAKAN_$view.ID_ADUANPUBLIC" name="ID_JENISTINDAKAN_$view.ID_ADUANPUBLIC" value="$view.ID_JENISTINDAKAN"  />
                 #end
				</td>
			</tr>
            
            
            
            
            
            <tr id="tr_ID_NEGERI_PT_$view.ID_ADUANPUBLIC" style="display:none"  >
				<td valign="top" >
                <!--
                #if($modeUI=="edit")
                <font color="red" >*</font>	
                #end
                -->
				</td>			
				<td valign="top" >
				$label_negeri_pt
                </td>
				<td valign="top" >
				:
				</td>
				<td valign="top" id="tdNegeriPT$view.ID_ADUANPUBLIC" >
                 #if($modeUI=="edit")                
                  	<select id="ID_NEGERI_PT_$view.ID_ADUANPUBLIC" name="ID_NEGERI_PT_$view.ID_ADUANPUBLIC" >	   
					   <option value = "" >$label_sila_pilih</option>
						#foreach ( $N in $listNegeriPT)		
							#set ( $selected_N = "" )
							#if($view.ID_NEGERI_PT==$N.ID_NEGERI)
							#set ( $selected_N = "selected" )
							#end
                            
                            <option $selected_N value="$N.ID_NEGERI" >
                            $N.NAMA_NEGERI
                            </option>	
                                                
						#end
					</select>	
                 #else
                     #if($view.ID_NEGERI_PT != "") 
                        $view.NEGERI_PT
                     #else 
                        $label_blank 
                     #end
                     <input type="hidden" id="ID_NEGERI_PT_$view.ID_ADUANPUBLIC" name="ID_NEGERI_PT_$view.ID_ADUANPUBLIC" value="$view.ID_NEGERI_PT"  />
                 #end
				</td>
			</tr>
            
            
            
            
            
            <tr id="tr_ID_DAERAH_PT_$view.ID_ADUANPUBLIC" style="display:none"  >
				<td valign="top" >
                <!--
                #if($modeUI=="edit")
                <font color="red" >*</font>	
                #end
                -->
				</td>			
				<td valign="top" >
				$label_daerah_pt
                </td>
				<td valign="top" >
				:
				</td>
				<td valign="top" id="tdDaerahPT$view.ID_ADUANPUBLIC" >
                 #if($modeUI=="edit")                
                  	<select id="ID_DAERAH_PT_$view.ID_ADUANPUBLIC" name="ID_DAERAH_PT_$view.ID_ADUANPUBLIC" >	   
					   <option value = "" >$label_sila_pilih</option>
						#foreach ( $N in $listDaerahPT)		
							#set ( $selected_N = "" )
							#if($view.ID_DAERAH_PT==$N.ID_DAERAH)
							#set ( $selected_N = "selected" )
							#end
                            
                            <option $selected_N value="$N.ID_DAERAH" >
                            $N.NAMA_DAERAH
                            </option>	
                                                
						#end
					</select>	
                 #else
                     #if($view.ID_DAERAH_PT != "") 
                        $view.DAERAH_PT
                     #else 
                        $label_blank 
                     #end
                     <input type="hidden" id="ID_DAERAH_PT_$view.ID_ADUANPUBLIC" name="ID_DAERAH_PT_$view.ID_ADUANPUBLIC" value="$view.ID_DAERAH_PT"  />
                 #end
				</td>
			</tr>
            
            
            
            <tr id="tr_ID_PEJABATTANAH_$view.ID_ADUANPUBLIC" style="display:none"  >
				<td valign="top" >
                <!--
                #if($modeUI=="edit")
                <font color="red" >*</font>	
                #end
                -->
				</td>			
				<td valign="top" >
				$label_pejabat_pt
                </td>
				<td valign="top" >
				:
				</td>
				<td valign="top" id="tdPejabatPT$view.ID_ADUANPUBLIC" >
                 #if($modeUI=="edit")                
                  	<select id="ID_PEJABATTANAH_$view.ID_ADUANPUBLIC" name="ID_PEJABATTANAH_$view.ID_ADUANPUBLIC" >	   
					   <option value = "" >$label_sila_pilih</option>
						#foreach ( $N in $listPejabatPT)		
							#set ( $selected_N = "" )
							#if($view.ID_PEJABATTANAH==$N.ID_PEJABAT)
							#set ( $selected_N = "selected" )
							#end
                            
                            <option $selected_N value="$N.ID_PEJABAT" >
                            $N.NAMA_PEJABAT
                            </option>	
                                                
						#end
					</select>	
                 #else
                     #if($view.ID_PEJABATTANAH != "") 
                        $view.PEJABAT_PT
                     #else 
                        $label_blank 
                     #end
                     <input type="hidden" id="ID_PEJABATTANAH_$view.ID_ADUANPUBLIC" name="ID_PEJABATTANAH_$view.ID_ADUANPUBLIC" value="$view.ID_PEJABATTANAH"  />
                 #end
				</td>
			</tr>
            
            
            
            
            #set($displayTrNegeriBahagian = "none")
            #set($displayTrEmelBahagian = "none")
            #set($displayAlertEmelBahagian = "none")
            #set($displayAlertSumberBahagian = "none")
            #set($displayTipsSumberBahagian = "none")
            #set($displayTrNegeriPT = "none")
            #set($displayTrPejabatTanah = "none")
            #set($displayTrDaerahPT = "none")
            
            #if($view.ID_JENISTINDAKAN == "16172" || $view.ID_JENISTINDAKAN == "16171")
            	#set($displayTrNegeriBahagian = "")
                #set($displayAlertSumberBahagian = "")
                #set($displayTipsSumberBahagian = "")
            #end
            
            #if($view.ID_JENISTINDAKAN != "16176" && $view.ID_JENISTINDAKAN != "")
            	#set($displayTrEmelBahagian = "")
            #end
            
            #if($view.ID_JENISTINDAKAN != "16172" && $view.ID_JENISTINDAKAN != "16171" && $view.ID_JENISTINDAKAN != "16176" && $view.ID_JENISTINDAKAN != "")
             	#set($displayAlertEmelBahagian = "")
            #end 
            
            
            
           #if($view.ID_JENISTINDAKAN == "16173" || $view.ID_JENISTINDAKAN == "16174")
                #set($displayTrNegeriPT = "")
                #set($displayTrPejabatTanah = "")
                
                #if($view.ID_JENISTINDAKAN == "16174")
                	#set($displayTrDaerahPT = "")
                #end
           #end
            
             
            <script>
			//alert('11111111111111');
			document.getElementById('tr_ID_NEGERI_BAHAGIAN_$view.ID_ADUANPUBLIC').style.display = "$displayTrNegeriBahagian";
			//alert('1');
			document.getElementById('tr_EMEL_BAHAGIAN_$view.ID_ADUANPUBLIC').style.display = "$displayTrEmelBahagian";
			//alert('2');
			document.getElementById('emel_bahagian_alert_$view.ID_ADUANPUBLIC').style.display = "$displayAlertEmelBahagian";
			//alert('3');
			document.getElementById('sumber_bahagian_alert_$view.ID_ADUANPUBLIC').style.display = "$displayAlertSumberBahagian";
			//alert('4');
			document.getElementById('sumber_bahagian_tips_$view.ID_ADUANPUBLIC').style.display = "$displayTipsSumberBahagian";
			//alert('5');
			document.getElementById('tr_ID_NEGERI_PT_$view.ID_ADUANPUBLIC').style.display = "$displayTrNegeriPT";
			//alert('6');
			document.getElementById('tr_ID_DAERAH_PT_$view.ID_ADUANPUBLIC').style.display = "$displayTrDaerahPT";
			//alert('7');
			document.getElementById('tr_ID_PEJABATTANAH_$view.ID_ADUANPUBLIC').style.display = "$displayTrPejabatTanah";
			//alert('8');	
			</script>
            
            
           
            
            
            <tr id="tr_ID_NEGERI_BAHAGIAN_$view.ID_ADUANPUBLIC" style="display:none"  >
				<td valign="top" >
                #if($modeUI=="edit")
                <font color="red" >*</font>	
                #end
				</td>			
				<td valign="top" >
				$label_negeri_bahagian
                </td>
				<td valign="top" >
				:
				</td>
				<td valign="top" id="tdNegeriBahagian$view.ID_ADUANPUBLIC" >
                
                 #if($modeUI=="edit")                
                  	<select id="ID_NEGERI_BAHAGIAN_$view.ID_ADUANPUBLIC" name="ID_NEGERI_BAHAGIAN_$view.ID_ADUANPUBLIC" 
                    
                    onChange = "doDivAjaxCall3$formname('tdSENARAI_PEGAWAIBAHAGIAN$view.ID_ADUANPUBLIC','showPegawaiBahagian','ID_ADUANPUBLIC=$view.ID_ADUANPUBLIC&mode=edit&ID_SUMBERTINDAKAN='+document.getElementById('ID_SUMBERTINDAKAN_$view.ID_ADUANPUBLIC').value+'&ID_NEGERI='+document.getElementById('ID_NEGERI_BAHAGIAN_$view.ID_ADUANPUBLIC').value+'&ID_BAHAGIAN='+document.getElementById('ID_BAHAGIAN_$view.ID_ADUANPUBLIC').value);"
                    
                    >	   
					   <option value = "" >$label_sila_pilih</option>
						#foreach ( $N in $listNegeriBahagian)		
							#set ( $selected_N = "" )
							#if($view.ID_NEGERI_BAHAGIAN==$N.ID_NEGERI)
							#set ( $selected_N = "selected" )
							#end
                            
                            <option $selected_N value="$N.ID_NEGERI" >
                            $N.NAMA_NEGERI
                            </option>	
                                                
						#end
					</select>	
                 #else
                     #if($view.ID_NEGERI_BAHAGIAN != "") 
                        #set($span13 = "span13list"+$view.BIL)
                        <span id="$span13">$view.NEGERI_BAHAGIAN</span>
                        #if($CR_ID_NEGERI_BAHAGIAN != "")
                        <script>			   
                        highlight_item(returnDropDownSelectedText_value('CR_ID_NEGERI_BAHAGIAN'),'$span13');
                        </script>
                        #end 
                     #else 
                        $label_blank 
                     #end
                     <input type="hidden" id="ID_NEGERI_BAHAGIAN_$view.ID_ADUANPUBLIC" name="ID_NEGERI_BAHAGIAN_$view.ID_ADUANPUBLIC" value="$view.ID_NEGERI_BAHAGIAN"  />
                 #end
				</td>
			</tr>

            
            
            
            
            #set($displayTrBahagian = "none")
            #if($view.ID_JENISTINDAKAN != "" && ($view.ID_JENISTINDAKAN == "16171" || $view.ID_JENISTINDAKAN == "16172"))
            	#set($displayTrBahagian = "")
            #end
            <script>
			document.getElementById('tr_ID_BAHAGIAN_$view.ID_ADUANPUBLIC').style.display = "$displayTrBahagian";
			</script>
            
            <tr id="tr_ID_BAHAGIAN_$view.ID_ADUANPUBLIC"  >
				<td valign="top" >
                #if($modeUI=="edit")
                <font color="red" >*</font>	
                #end
				</td>			
				<td valign="top" >
				$label_tindakan_bahagian
                </td>
				<td valign="top" >
				:
				</td>
				<td valign="top" id="tdJenisTindakan$view.ID_ADUANPUBLIC" >
                #if($modeUI=="edit")                
                   <select id="ID_BAHAGIAN_$view.ID_ADUANPUBLIC" name="ID_BAHAGIAN_$view.ID_ADUANPUBLIC" 
                   
                   onChange = "doDivAjaxCall3$formname('tdSENARAI_PEGAWAIBAHAGIAN$view.ID_ADUANPUBLIC','showPegawaiBahagian','ID_ADUANPUBLIC=$view.ID_ADUANPUBLIC&mode=edit&ID_SUMBERTINDAKAN='+document.getElementById('ID_SUMBERTINDAKAN_$view.ID_ADUANPUBLIC').value+'&ID_NEGERI='+document.getElementById('ID_NEGERI_BAHAGIAN_$view.ID_ADUANPUBLIC').value+'&ID_BAHAGIAN='+document.getElementById('ID_BAHAGIAN_$view.ID_ADUANPUBLIC').value);"
                   
                   >	   
					   <option value = "" >$label_sila_pilih</option>
						#foreach ( $N in $listBahagian)		
							#set ( $selected_N = "" )
							#if($view.ID_BAHAGIAN==$N.ID_SEKSYEN)
							#set ( $selected_N = "selected" )
							#end
							<option $selected_N value="$N.ID_SEKSYEN" >
							$N.NAMA_SEKSYEN
							</option>							
						#end
					</select>
                 #else
                     #if($view.ID_BAHAGIAN != "") 
                        #set($span12 = "span12list"+$view.BIL)
                        <span id="$span12">$view.NAMA_BAHAGIAN</span>
                        #if($CR_ID_BAHAGIAN != "")
                        <script>			   
                        highlight_item(returnDropDownSelectedText_value('CR_ID_BAHAGIAN'),'$span12');
                        </script>
                        #end                        
                     #else 
                        $label_blank 
                     #end
                     <input type="hidden" id="ID_BAHAGIAN_$view.ID_ADUANPUBLIC" name="ID_BAHAGIAN_$view.ID_ADUANPUBLIC" value="$view.ID_BAHAGIAN"  />
                 #end
				</td>
			</tr>
            
            
            


<tr id="tr_ID_SUMBERTINDAKAN_$view.ID_ADUANPUBLIC" style="display:none"  >
				<td valign="top" >
                
                <font color="red" id="sumber_bahagian_alert_$view.ID_ADUANPUBLIC" >
                #if($modeUI=="edit")
                *	
                #end
                </font>
				</td>			
				<td valign="top" >
				$label_sumber_bahagian
                </td>
				<td valign="top" >
				:
				</td>
				<td valign="top" id="tdSumberTindakan$view.ID_ADUANPUBLIC" >
                 #if($modeUI=="edit")                
                  	<select id="ID_SUMBERTINDAKAN_$view.ID_ADUANPUBLIC" name="ID_SUMBERTINDAKAN_$view.ID_ADUANPUBLIC"
                    onChange = "doDivAjaxCall3$formname('tdSENARAI_PEGAWAIBAHAGIAN$view.ID_ADUANPUBLIC','showPegawaiBahagian','ID_ADUANPUBLIC=$view.ID_ADUANPUBLIC&mode=edit&ID_SUMBERTINDAKAN='+this.value+'&ID_NEGERI='+document.getElementById('ID_NEGERI_BAHAGIAN_$view.ID_ADUANPUBLIC').value+'&ID_BAHAGIAN='+document.getElementById('ID_BAHAGIAN_$view.ID_ADUANPUBLIC').value);" 
                    onBlur = "doDivAjaxCall3$formname('tdSENARAI_PEGAWAIBAHAGIAN$view.ID_ADUANPUBLIC','showPegawaiBahagian','ID_ADUANPUBLIC=$view.ID_ADUANPUBLIC&mode=edit&ID_SUMBERTINDAKAN='+this.value+'&ID_NEGERI='+document.getElementById('ID_NEGERI_BAHAGIAN_$view.ID_ADUANPUBLIC').value+'&ID_BAHAGIAN='+document.getElementById('ID_BAHAGIAN_$view.ID_ADUANPUBLIC').value);" 
                     >	   
					   <option value = "" >$label_sila_pilih</option>
						#foreach ( $N in $listJenisSumberBahagian)		
							#set ( $selected_N = "" )
							#if($view.ID_SUMBERTINDAKAN==$N.ID_SUMBERADUAN)
							#set ( $selected_N = "selected" )
							#end
                            
                            <option $selected_N value="$N.ID_SUMBERADUAN" >
                            $N.NAMA_SUMBER
                            </option>	
                                                
						#end
					</select>
                   
                 #else
                     #if($view.ID_SUMBERTINDAKAN != "") 
                        $view.SUMBER_BAHAGIAN
                     #else 
                        $label_blank 
                     #end
                     <input type="hidden" id="ID_SUMBERTINDAKAN_$view.ID_ADUANPUBLIC" name="ID_SUMBERTINDAKAN_$view.ID_ADUANPUBLIC" value="$view.ID_SUMBERTINDAKAN"  />
                 #end
                 <br />
                 <font color="blue" class="blink" id="sumber_bahagian_tips_$view.ID_ADUANPUBLIC" >#if($modeUI=="edit")* $TipsSumberBahagian#end</font>
				</td>
			</tr>


	<tr id="tr_SENARAI_PEGAWAIBAHAGIAN_$view.ID_ADUANPUBLIC"  style="display:none" >
				<td valign="top" >
                
				</td>			
				<td valign="top" >
				Senarai Pegawai<br />
                (Peranan 'Wakil Bahagian' didalam MyeTaPP)
                </td>
				<td valign="top" >
				:
				</td>
				<td valign="top" id="tdSENARAI_PEGAWAIBAHAGIAN$view.ID_ADUANPUBLIC" >&nbsp;</td>
			</tr>


			 
            <tr id="tr_EMEL_BAHAGIAN_$view.ID_ADUANPUBLIC" >
				<td valign="top" >
                <font color="red" id="emel_bahagian_alert_$view.ID_ADUANPUBLIC" >
                #if($modeUI=="edit")
                *	
                #end
                </font>
				</td>			
				<td valign="top" >
				$label_emel_bahagian
                </td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                #set($setBR = "<br />")
             	#set($typeF="text")
             	#if($modeUI=="edit")
				#set($typeF="text")
         		#else
                #set($typeF="hidden")
                #set($setBR = "")
                    #if($view.EMEL_BAHAGIAN_1 != "")                 
                    	<a href="mailto:$view.EMEL_BAHAGIAN_1" style="color:blue;" target="_top"><u>$view.EMEL_BAHAGIAN_1;</u></a>
                    #end
                    #if($view.EMEL_BAHAGIAN_2 != "")                 
                    	<a href="mailto:$view.EMEL_BAHAGIAN_2" style="color:blue;" target="_top"><u>$view.EMEL_BAHAGIAN_2;</u></a>
                    #end
                    #if($view.EMEL_BAHAGIAN_3 != "")                 
                    	<a href="mailto:$view.EMEL_BAHAGIAN_3" style="color:blue;" target="_top"><u>$view.EMEL_BAHAGIAN_3;</u></a>
                    #end
                    #if($view.EMEL_BAHAGIAN_4 != "")                 
                    	<a href="mailto:$view.EMEL_BAHAGIAN_4" style="color:blue;" target="_top"><u>$view.EMEL_BAHAGIAN_4;</u></a>
                    #end
                    
                    #if($view.EMEL_BAHAGIAN_1 == "" && $view.EMEL_BAHAGIAN_2 == "" && $view.EMEL_BAHAGIAN_3 == "" && $view.EMEL_BAHAGIAN_4 == "")  
                    	$label_blank 
                    #end
                #end	
                <input type="$typeF" placeholder="[Emel 1]" style="margin-bottom:3px" size="50" maxlength="100" onfocus="onfocusEmail('$view.ID_ADUANPUBLIC',this);" name="EMEL_BAHAGIAN_1_$view.ID_ADUANPUBLIC" id="EMEL_BAHAGIAN_1_$view.ID_ADUANPUBLIC"  value="$view.EMEL_BAHAGIAN_1" >$setBR 
                 <input type="$typeF" placeholder="[Emel 2]" style="margin-bottom:3px" size="50" maxlength="100" onfocus="onfocusEmail('$view.ID_ADUANPUBLIC',this);" name="EMEL_BAHAGIAN_2_$view.ID_ADUANPUBLIC" id="EMEL_BAHAGIAN_2_$view.ID_ADUANPUBLIC"  value="$view.EMEL_BAHAGIAN_2" >$setBR  
                  <input type="$typeF" placeholder="[Emel 3]" style="margin-bottom:3px" size="50" maxlength="100"  onfocus="onfocusEmail('$view.ID_ADUANPUBLIC',this);" name="EMEL_BAHAGIAN_3_$view.ID_ADUANPUBLIC" id="EMEL_BAHAGIAN_3_$view.ID_ADUANPUBLIC"  value="$view.EMEL_BAHAGIAN_3" >$setBR
                  <input type="$typeF" placeholder="[Emel 4]"  size="50" maxlength="100" onfocus="onfocusEmail('$view.ID_ADUANPUBLIC',this);" name="EMEL_BAHAGIAN_4_$view.ID_ADUANPUBLIC" id="EMEL_BAHAGIAN_4_$view.ID_ADUANPUBLIC"  value="$view.EMEL_BAHAGIAN_4" >$setBR                
             
				</td>
			</tr>
            


			



			#set($displayTrSumberBahagian = "none")
            #if($view.ID_JENISTINDAKAN != "" && ($view.ID_JENISTINDAKAN != "16176"))
            	#set($displayTrSumberBahagian = "")
            #end
            <script>
			
			document.getElementById('tr_ID_SUMBERTINDAKAN_$view.ID_ADUANPUBLIC').style.display = "$displayTrSumberBahagian";
			</script>
            
            
            
            
            
            


			<tr id="tr_FLAG_LUAR_TINDAKAN_$view.ID_ADUANPUBLIC" >
				<td valign="top" >
               
				</td>			
				<td valign="top" >
				$label_luar_tindakan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                #if($modeUI=="edit")
				<input type="checkbox" id="FLAG_LUAR_TINDAKAN_$view.ID_ADUANPUBLIC" name="FLAG_LUAR_TINDAKAN_$view.ID_ADUANPUBLIC"
                #if($view.FLAG_LUAR_TINDAKAN=="Y") checked="checked" #end value="Y"  />
         		#else
                    #if($view.FLAG_LUAR_TINDAKAN == "Y")                 
                    	$label_yes
                    #else 
                    	$label_no 
                    #end
                <input type="hidden" name="FLAG_LUAR_TINDAKAN_$view.ID_ADUANPUBLIC" id="FLAG_LUAR_TINDAKAN_$view.ID_ADUANPUBLIC" value="$view.FLAG_LUAR_TINDAKAN"  />
                #end			
				</td>
			</tr>

	
    		
			
            <tr>
				<td valign="top" >
                #if($modeUI=="edit")
                <font color="red" >*</font>	
                #end	
				</td>			
				<td valign="top" >
				$label_keterangan_ui
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                #if($modeUI=="edit")
				 <textarea style="text-transform:uppercase;" name="MAKLUMBALAS_UI_$view.ID_ADUANPUBLIC" 
				 id="MAKLUMBALAS_UI_$view.ID_ADUANPUBLIC" cols="50" rows="5" 
		 onKeyup="check_length(this,4000,'span_MAKLUMBALAS_UI_$view.ID_ADUANPUBLIC');" >$view.MAKLUMBALAS_UI</textarea>		 		
         <span id="span_MAKLUMBALAS_UI_$view.ID_ADUANPUBLIC"  ></span>
         		#else
                    #if($view.MAKLUMBALAS_UI != "")                 
                    	$view.MAKLUMBALAS_UI
                    #else 
                    	$label_blank 
                    #end
                <input type="hidden" name="MAKLUMBALAS_UI_$view.ID_ADUANPUBLIC" id="MAKLUMBALAS_UI_$view.ID_ADUANPUBLIC" value="$view.MAKLUMBALAS_UI"  />
                #end			
				</td>
			</tr>	
            
            
            <tr id="tr_ID_STATUS_UI_$view.ID_ADUANPUBLIC">
				<td valign="top"  >	
                #if($modeUI=="edit")
                <font color="red" >*</font>	
                #end			
				</td>			
				<td valign="top" >
				$label_status_aduan	
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" id="tdStatusAduanUI$view.ID_ADUANPUBLIC"  >	
                
               
                
                #if($modeUI=="edit")
                 <script>							
				  $jquery(document).ready(function () {
					 renameButtonHantar('BTNHANTAR$view.ID_ADUANPUBLIC','$view.ID_STATUS');	 	  
				  });	
				</script>			
				<select id="ID_STATUS_UI_$view.ID_ADUANPUBLIC" name="ID_STATUS_UI_$view.ID_ADUANPUBLIC" 
                onchange="renameButtonHantar('BTNHANTAR$view.ID_ADUANPUBLIC',this.value);" >	   
					   #foreach ( $N in $listStatus)		
							#set ( $selected_N = "" )
							#if($view.ID_STATUS==$N.ID_STATUS)
							#set ( $selected_N = "selected" )
							#end
							<option $selected_N value="$N.ID_STATUS" >
							$N.KETERANGAN
							</option>							
						#end
				</select>	
                #else
					<script>
					//alert('11111111');
                    document.getElementById('tr_ID_STATUS_UI_$view.ID_ADUANPUBLIC').style.display = "none";
					//alert('2');
                    </script>
                    <input type="hidden" name="ID_STATUS_UI_$view.ID_ADUANPUBLIC" id="ID_STATUS_UI_$view.ID_ADUANPUBLIC" value="$view.ID_STATUS"  />
                #end
				</td>
			</tr>
            
            
            </table>
            
           
    #set($docType = "UI")  
   <!--  --------- divLampiranUI_$view.ID_ADUANPUBLIC  -->
    <div id="divLampiranUI_$view.ID_ADUANPUBLIC">
    <!--
    #if($view.ID_ADUANPUBLIC!="")    	
    #end
    -->
    #parse("app/online/AduanPublic/SkrinLampiran.jsp")
    </div>  
    
    
</fieldset>
