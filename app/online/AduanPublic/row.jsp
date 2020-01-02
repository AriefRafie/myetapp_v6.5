<tr id="div_row$view.ID_ADUANPUBLIC" >
			   <td class="$rowCss"  align="center" valign="top" >$BIL
			   
			   </td>
			   <td class="$rowCss"  align="left" valign="top">			   
			   <a href="javascript:document.getElementById('div_row').innerHTML ='';doDivAjaxCall$formname('div_row$view.ID_ADUANPUBLIC','edit','ID_ADUANPUBLIC=$view.ID_ADUANPUBLIC&BIL=$BIL&rowCss=$rowCss&mode=view&commandFrom=list');">
			   <font color='blue' >	
               		
               #if($view.ID_STATUS == "16125")   
               [<u>$view.STATUS</u>]
               #else
			   <u>$view.NO_ADUAN</u>
               #end			   
			   </font>
			   </a>
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
               		   
			   <td class="$rowCss"  align="center" valign="top">			   
			   $view.TARIKH_ADUAN			  
			   </td>
               
               <td class="$rowCss"  align="left" valign="top">	
               #if($view.FLAG_HIDE_INFO == "1") 
               	   [$label_maklumat_dilindungi]
               #else		   
                   $view.NAMA_PENGADU
               #end			  
			   </td>
               
               <td class="$rowCss"  align="left" valign="top">			   
			   $view.JENIS_ADUAN			  
			   </td>
               
               <td class="$rowCss"  align="left" valign="top">	
               #set($classSenaraiStatus="")               
               #if($view.MESEJ_TINDAKAN != "")                 
             	 #if($view.MESEJ_TINDAKAN == "alert_masih_didalam_tindakan_ui")
                 	#set($classSenaraiStatus="font_tajuk_sub blink")
                 #elseif($view.MESEJ_TINDAKAN == "alert_masih_didalam_tindakan_bahagian")
                 	#set($classSenaraiStatus="font_tajuk_sub blink")
                 #end                 
               #end  
               <span class="$classSenaraiStatus">		   
			   $view.STATUS	
               </span>		  
			   </td>
               
			   
			   
			  <td class="$view.rowCss"  align="center" valign="top">			  
	   		   #if(($view.ID_PENGADU == $USER_ID_SYSTEM && $view.ID_STATUS == "16125") )			  
	   		   <a href="javascript:if(confirm('$label_adakah_pasti_delete')){ doDivAjaxCall$formname('div_Senarai','delete','ID_ADUANPUBLIC=$view.ID_ADUANPUBLIC');}"><img title="$label_hapus"  src="../img/hapus.gif" border="0"></a>
	   		  #end
              
              #if($view.MESEJ_TINDAKAN != "")
             	 <div class="font_tajuk_sub blink" align="left" ><font color="blue">                 
                 #if($view.MESEJ_TINDAKAN == "alert_perlu_tindakan_ui")
                 	$alert_perlu_tindakan_ui.toUpperCase()
                 #elseif($view.MESEJ_TINDAKAN == "alert_perlu_tindakan_bahagian")
                 	$alert_perlu_tindakan_bahagian.toUpperCase()
                 #end
                 </font>
                 </div>
              #end
			   </td>	   
		</tr>
        
       
        #if($viewBaru.ID_ADUANPUBLIC != "")
        <script>
		//fungsi ni akan diguna pakai untuk resend aduan
				 $jquery(document).ready(function () {
							 doDivAjaxCall3$formname('divSenaraiLampiran$viewBaru.ID_ADUANPUBLIC','showLampiran','ID_ADUANPUBLIC=$viewBaru.ID_ADUANPUBLIC&ID_STATUS=$viewBaru.ID_STATUS&ID_PENGADU=$viewBaru.ID_PENGADU&mode=$mode');			 	  
				 });	
		</script>
        #end