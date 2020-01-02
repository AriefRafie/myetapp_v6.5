
#if($commandFrom != "list")
<script>
document.getElementById('span_LINK_CARIAN').style.display = "";
</script>
#end

    
#if($flag_reset_list == "yes")
<script>
$jquery(document).ready(function () {
             doDivAjaxCall3$formname('div_Senarai','showSenarai','OPENCLOSE_CARIAN=close&FLAG_CARIAN=N&FLAG_NOTIFIKASI=');			 	  
 });	
</script>
#end

#if($fromDashboard == "Y")
<script>
  $jquery(document).ready(function () {
	 doDivAjaxCall$formname('div_Senarai','showSenarai','FLAG_NOTIFIKASI=');			 	  
  });
</script>
#end



<script>
document.getElementById('divDisplayAlert_$view.ID_ADUANPUBLIC').innerHTML = "";
//alert('123');
</script>


<tr id="div_row$view.ID_ADUANPUBLIC" >
<td align="left" valign="top" colspan="14" >

<!--	
commandFrom : $commandFrom 
<br />
mode : $mode
<br />
$view.ID_STATUS
<br />
-->

<div id="printableArea_$view.ID_ADUANPUBLIC">
	<fieldset class="classFade">
	<legend>	
	#if($view.ID_ADUANPUBLIC != "")
	$legend_form_aduan_edit 
        #if($view.NO_ADUAN != "")
        : $view.NO_ADUAN
        #end
	#else
	$title_button_tambah_aduan
	#end
	</legend>
    
    
    
    
    
    
    
    <div id="divKronologi_$view.ID_ADUANPUBLIC">
    </div> 
    
    #if(($view.ID_STATUS == "16125" || $view.ID_STATUS	== "") && $mode == "edit")
	#set($mode = "edit")
    #else
        #set($mode = "view")
    #end
	
	<div id="printableArea_$view.ID_ADUANPUBLIC">
    #if($mode=="edit")
    	#parse("app/online/AduanPublic/MaklumatPengadu.jsp")
    #else
    	#parse("app/online/AduanPublic/MaklumatPengaduView.jsp")
    #end
    <fieldset> 
    <legend>$legend_form_aduan_edit</legend>
    
		<table width="100%" border="0">
			<tr>
			<td valign="top"  width="1%"></td>
			<td valign="top"  width="28%"></td>
			<td valign="top"  width="1%"></td>
			<td valign="top"  width="70%">
			</td>
			</tr>
            
            
            #if($view.ID_STATUS!="")
			<tr>
				<td valign="top" >
				</td>			
				<td valign="top" >
				$label_status_aduan</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                  #set($span8 = "span8list"+$view.BIL)	   
                  <span id="$span8">$view.STATUS</span>
                  #if($CR_ID_STATUS != "")
                  <script>			   
                  highlight_item(returnDropDownSelectedText_value('CR_ID_STATUS'),'$span8');
                  </script>
				  #end		
                  
                 #if($view.MESEJ_TINDAKAN != "")
                     <div class="font_tajuk_sub blink" align="left" >             
                     #if($view.MESEJ_TINDAKAN == "alert_delete_log_deraf")
                         <font  color="red">    
                            $alert_delete_log_deraf <br />[$view.TARIKH_KEMASKINI]
                         </font>
                     #end
                     </div>
              	 #end	    
				</td>
			</tr>
			#end
            <input type="hidden" id="ID_STATUS_$view.ID_ADUANPUBLIC" name="ID_STATUS_$view.ID_ADUANPUBLIC" value="$view.ID_STATUS" >	
            
            #if($view.TARIKH_ADUAN!="")
			<tr>
				<td valign="top" >
				</td>			
				<td valign="top" >
				$label_tarikh_aduan</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                  $view.TARIKH_ADUAN			    
				</td>
			</tr>
			#end
            <input type="hidden" id="TARIKH_ADUAN_$view.ID_ADUANPUBLIC" name="TARIKH_ADUAN_$view.ID_ADUANPUBLIC" value="$view.TARIKH_ADUAN" >	
            
            
            
            
            #if($view.TARIKH_ADUAN_SELESAI!="")
			<tr>
				<td valign="top" >
				</td>			
				<td valign="top" >
				$label_tarikh_aduan_selesai</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                  $view.TARIKH_ADUAN_SELESAI			    
				</td>
			</tr>
			#end
            <input type="hidden" id="TARIKH_ADUAN_SELESAI_$view.ID_ADUANPUBLIC" name="TARIKH_ADUAN_SELESAI_$view.ID_ADUANPUBLIC" value="$view.TARIKH_ADUAN_SELESAI" >	
            
            
			
			#if($view.NO_ADUAN!="")
			<tr>
				<td valign="top" >
				</td>			
				<td valign="top" >
				$label_no_aduan</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                #set($span1 = "span1list"+$view.BIL)
                <span id="$span1"> 
				$view.NO_ADUAN
                </span>			
			    <script>highlight_item('$CR_NO_ADUAN','$span1')</script>   
				
				</td>
			</tr>
			#end
            <input style="text-transform:uppercase;" size="50" type="hidden" id="NO_ADUAN_$view.ID_ADUANPUBLIC" name="NO_ADUAN_$view.ID_ADUANPUBLIC" value="$view.NO_ADUAN" onKeyUp="" >
            
            <tr>
				<td valign="top" >
                #if($mode=="edit")
                <font color="red" >*</font>	
                #end
				</td>			
				<td valign="top" >
				$label_jenis_aduan</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                #if($mode=="edit")
				<select id="ID_JENISADUAN_$view.ID_ADUANPUBLIC" name="ID_JENISADUAN_$view.ID_ADUANPUBLIC" 
				onChange = "doDivAjaxCall3$formname('showContentAduan_$view.ID_ADUANPUBLIC','showContentAduan','ID_ADUANPUBLIC=$view.ID_ADUANPUBLIC&mode=edit&ID_JENISADUAN='+this.value);"
				>	   
					   <option value = "" >$label_sila_pilih</option>
						#foreach ( $N in $listJenisAduan)		
							#set ( $selected_N = "" )
							#if($view.ID_JENISADUAN==$N.ID_JENISADUAN)
							#set ( $selected_N = "selected" )
							#end
							<option $selected_N value="$N.ID_JENISADUAN" >
							$N.JENIS_ADUAN
							</option>							
						#end
					</select>
                  #else
                  
                  #set($span3 = "span3list"+$view.BIL)	   
                  <span id="$span3">$view.JENIS_ADUAN</span>
                  #if($CR_ID_JENISADUAN != "")
                  <script>			   
                  highlight_item(returnDropDownSelectedText_value('CR_ID_JENISADUAN'),'$span3');
                  </script>
                  #end		
                  <input type="hidden" id="ID_JENISADUAN_$view.ID_ADUANPUBLIC" name="ID_JENISADUAN_$view.ID_ADUANPUBLIC" value="$view.ID_JENISADUAN"  />
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
				$label_hide_personal_info
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >	
                
                #if($mode=="edit")
                
				#if($view.FLAG_HIDE_INFO == "1")
                	#set($radio_hide1="checked")
					#set($radio_hide2="")
                #elseif($view.FLAG_HIDE_INFO == "2")
                	#set($radio_hide1="")
					#set($radio_hide2="checked")
                #else
                	#set($radio_hide1="")
					#set($radio_hide2="")
                #end
				
				
				<input type="radio" $radio_hide1 onClick="sendValueJenisPengguna(this,this.value,'FLAG_HIDE_INFO_$view.ID_ADUANPUBLIC')" id="radio_FLAG_HIDE_INFO_$view.ID_ADUANPUBLIC" name="radio_FLAG_HIDE_INFO_$view.ID_ADUANPUBLIC" value="1" >$label_yes
				&nbsp;&nbsp;
				<input type="radio" $radio_hide2 onClick="sendValueJenisPengguna(this,this.value,'FLAG_HIDE_INFO_$view.ID_ADUANPUBLIC')" id="radio_FLAG_HIDE_INFO_$view.ID_ADUANPUBLIC" name="radio_FLAG_HIDE_INFO_$view.ID_ADUANPUBLIC" value="2" >$label_no
                
                #else
                	#if($view.FLAG_HIDE_INFO == "1")
                		$label_yes
               		#elseif($view.FLAG_HIDE_INFO == "2")
						$label_no
                    #else
                    -
                    #end
                #end
								
				<input style="text-transform:uppercase;"  type="hidden" id="FLAG_HIDE_INFO_$view.ID_ADUANPUBLIC" 
				name="FLAG_HIDE_INFO_$view.ID_ADUANPUBLIC" 
				value="$view.FLAG_HIDE_INFO" >	
							
				</td>
			</tr>
	</table>
    <div id="showContentAduan_$view.ID_ADUANPUBLIC">
    #if($view.ID_ADUANPUBLIC!="")    
    	#if($view.ID_JENISADUAN=="16101")<!-- Untuk Aduan Sahaja -->
    		#parse("app/online/AduanPublic/ContentAduan.jsp")
        #else
        	#parse("app/online/AduanPublic/ContentCadangan.jsp")
        #end
    #end
	</div> 
    
       
       
       
    #set($docType = "PENGADU")  
    <div id="divLampiranPENGADU_$view.ID_ADUANPUBLIC">
    <!--
    #if($view.ID_ADUANPUBLIC!="")    	
    #end
    -->
    #parse("app/online/AduanPublic/SkrinLampiran.jsp")
    </div>  
    
    
   
    
    
      
    </fieldset>
    
    
     
    <div id="divSkrinUI_$view.ID_ADUANPUBLIC" style="display:none" >      
      #parse("app/online/AduanPublic/SkrinUI.jsp")        
    </div>
    
    
    
    <div id="divSkrinBahagian_$view.ID_ADUANPUBLIC" style="display:none" >      
      #parse("app/online/AduanPublic/SkrinBahagian.jsp")        
    </div>
    
    
    
    
    
    </div>
    
    
   <div id="divDisplayAlert_$view.ID_ADUANPUBLIC" class="font_tajuk_sub"></div>
    
   
    
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
                #set($showTipsResend = "N")	
                #if($mode=="edit" || $modeUI=="edit" || $modeBahagian=="edit")
                
                
                 <input type="button" id="BTNHANTAR$view.ID_ADUANPUBLIC" name="BTNHANTAR$view.ID_ADUANPUBLIC" 
                    onClick="if(valEdit('$view.ID_ADUANPUBLIC','hantar')==true){hantarLog('$view.ID_ADUANPUBLIC','$view.FLAG_SEMENTARA','view','$commandFrom','$BIL','$rowCss',this);}" value="$button_hantar_aduan" > 
              	   
                    
                 #if(($view.ID_PENGADU == $USER_ID_SYSTEM && $view.ID_STATUS == "16125") || ($view.ID_ADUANPUBLIC == "") 
                  || ($view.ID_PENGADU == "" && $USER_ROLE == "user_unit_intergriti" && ($view.ID_STATUS == "16125")))
                <input type="button" id="BTNSAVE$view.ID_ADUANPUBLIC" name="BTNSAVE$view.ID_ADUANPUBLIC" 
                onClick="if(valEdit('$view.ID_ADUANPUBLIC','deraf')==true){doDivAjaxCall3$formname('div_row$view.ID_ADUANPUBLIC','saveDeraf','ID_ADUANPUBLIC=$view.ID_ADUANPUBLIC&mode=view&commandFrom=$commandFrom&BIL=$BIL&rowCss=$rowCss');}" value="$button_simpan_deraf" > 
                 #end
                    
                    
                <input type="button" id="BTNBTL$view.ID_ADUANPUBLIC" name="BTNBTL$view.ID_ADUANPUBLIC" onClick="doDivAjaxCall3$formname('div_row$view.ID_ADUANPUBLIC','edit','ID_ADUANPUBLIC=$view.ID_ADUANPUBLIC&mode=edit&commandFrom=$commandFrom&BIL=$BIL&rowCss=$rowCss');" value="$button_batal" >               
                #else
                
               
                
                #if(($view.ID_PENGADU == $USER_ID_SYSTEM && $view.ID_STATUS == "16125") 
                || ($view.ID_PENGADU == "" && $USER_ROLE == "user_unit_intergriti" && ($view.ID_STATUS == "16125"))
                || ($USER_ROLE == "user_unit_intergriti" && ($view.ID_STATUS == "16122" || $view.ID_STATUS == "16122") && $view.ID_PEGAWAI_UI == $USER_ID_SYSTEM)
                )
                <input type="button" id="BTNBTL$view.ID_ADUANPUBLIC" name="BTNBTL$view.ID_ADUANPUBLIC" onClick="doDivAjaxCall3$formname('div_row$view.ID_ADUANPUBLIC','edit','ID_ADUANPUBLIC=$view.ID_ADUANPUBLIC&mode=edit&commandFrom=$commandFrom&BIL=$BIL&rowCss=$rowCss');" value="$button_kemaskini" >
                
                #elseif(($USER_ROLE == "user_unit_intergriti" && ($view.ID_STATUS == "16126" || $view.ID_STATUS == "16127" || $view.ID_STATUS == "16123") && $view.ID_PEGAWAI_UI == $USER_ID_SYSTEM)
                )
                
                 <input type="button" id="BTNRS$view.ID_ADUANPUBLIC" name="BTNRS$view.ID_ADUANPUBLIC" onClick="document.getElementById('div_row').style.display='';javascript:if(confirm('$label_adakah_pasti')){doDivAjaxCall3$formname('div_row','editRecreateLog','ID_ADUANPUBLIC=$view.ID_ADUANPUBLIC&mode=edit&commandFrom=form&BIL=$BIL&rowCss=$rowCss');}" value="$button_resend" >
                 #set($showTipsResend = "Y")
                 <!--
                 ::::::::::: $ID_ADUANPUBLICASAL
                -->
                #end
                
                
                
                <input type="button" id="BTNPRINT$view.ID_ADUANPUBLIC" name="BTNPRINT$view.ID_ADUANPUBLIC" 
                    onclick="printAduanForm('printableArea_$view.ID_ADUANPUBLIC','$view.ID_ADUANPUBLIC')" 
                    value="$button_cetak" />
                
                #end
                
                #set($divClose = "div_row")
                #if($commandFrom=="list")
                	#set($divClose = "div_row"+$view.ID_ADUANPUBLIC)
                #end
                
                
                
                <input type="button" id="BTNCLOSE$view.ID_ADUANPUBLIC" name="BTNCLOSE$view.ID_ADUANPUBLIC" onClick="doDivAjaxCall3$formname('$divClose','close','ID_ADUANPUBLIC=$view.ID_ADUANPUBLIC&commandFrom=$commandFrom&BIL=$BIL&rowCss=$rowCss');" value="$button_tutup" > 
                
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
            #if($showTipsResend == "Y")
            <font color="blue" class="blink" id="tips_$view.ID_ADUANPUBLIC">* $TipsResend</font>
            #end	
            </td>
            </tr>
	</table>
   
	</fieldset>
	</div>
	
	<br>
    
</td>		
</tr>

