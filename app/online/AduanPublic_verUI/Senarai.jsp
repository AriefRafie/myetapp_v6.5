 

#if($OPENCLOSE_CARIAN=="open")
<script>
	document.getElementById('div_CARIAN').style.display = "";
	document.getElementById('span_LINK_CARIAN').style.display = "none";	
</script>
#end
#if($OPENCLOSE_CARIAN=="close")
<script>
	document.getElementById('div_CARIAN').style.display = "none";
	document.getElementById('span_LINK_CARIAN').style.display = "";
</script>
#end


#if($FLAG_CARIAN=="Y")
	<script>
	if( $jquery('#div_CARIAN').length )         // use this if you are using id to check
	{
		window.scrollTo(0, $jquery('#div_CARIAN').offset().top);
	}
	</script>
	#else
	<script>
	if( $jquery('#div_Senarai').length )         // use this if you are using id to check
	{
		window.scrollTo(0, $jquery('#div_Senarai').offset().top);
	}
	</script>
#end






<fieldset id="">
<table width="100%" id="" >
<tr >
</tr>
</table>
<!-- ::::::::: $list -->
<table width="100%" align="center">
<tr>
<td>

<div id="div_SenaraiNotifikasi" >		
			<script>		
			  $jquery(document).ready(function () {
				 doDivAjaxCall3$formname('div_SenaraiNotifikasi','showListNotifikasi','FLAG_NOTIFIKASI=$FLAG_NOTIFIKASI');			 	  
			  });		
			</script>		
</div>	

	
	<table border="0" cellspacing="1" cellpadding="1" width="100%" > 
	#if($list.size()>0)
		<script>
        var butoncetak =  document.getElementById('cmdCetak');
        if (typeof(butoncetak) != 'undefined' && butoncetak != null)
        {
            butoncetak.style.display = "";
        }
        </script>
        <tr >
               <td  align="left" valign="top" colspan="14" >
               #parse("app/online/AduanPublic/record_paging.jsp")
               </td>		     
        </tr>
	#end 
	<tr class="table_header" >
		   <td   align="center" valign="top" width="3%" >$label_bil</td>
		   <td   align="left" valign="top" width="15%">$label_no_aduan</td>
		   <td   align="center" valign="top" width="15%">$label_tarikh_aduan</td>
           <td   align="left" valign="top" width="20%">$label_pengadu</td>
           <td   align="left" valign="top" width="20%">$label_jenis_aduan</td>
		   <td   align="left" valign="top" width="15%">$label_status_aduan</td>
		   <td   align="center" valign="top"width="12%">$label_tindakan</td>	   
	</tr>
	#if($list.size()>0)
	
   
    
		#foreach($view in $list)
       
		
		<tr id="div_row$view.ID_ADUANPUBLIC" >
			   <td class="$view.rowCss"  align="center" valign="top" >$view.BIL
			   
			   </td>
			   <td class="$view.rowCss"  align="left" valign="top">			   
			   #set($span1 = "span1list"+$view.BIL)
			   <span id="$span1"> 
			   <a href="javascript:document.getElementById('div_row').innerHTML ='';doDivAjaxCall$formname('div_row$view.ID_ADUANPUBLIC','view','ID_ADUANPUBLIC=$view.ID_ADUANPUBLIC&BIL=$view.BIL&rowCss=$view.rowCss&mode=view&commandFrom=list');">
			   <font color='blue' class="font_tajuk_sub" >	
               		
               #if($view.ID_STATUS == "16125")   
               [<u>$view.STATUS</u>]
               #else
			   <u>$view.NO_ADUAN</u>
               #end			   
			   </font>
			   </a>
			   </span>			
			   <script>highlight_item('$CR_NO_ADUAN','$span1')</script>   
               
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
               		   
			   <td class="$view.rowCss"  align="center" valign="top">			   
			   $view.TARIKH_ADUAN			  
			   </td>
               
               <td class="$view.rowCss"  align="left" valign="top">	
               	
                
               #if($view.FLAG_HIDE_INFO == "1" && $USER_ROLE != "user_unit_intergriti") 
               		
               	   [$label_maklumat_dilindungi]
               #else
               #set($span2 = "span2list"+$view.BIL)
               <span id="$span2">		   
                   $view.NAMA_PENGADU
               </span>
               <script>highlight_item('$CR_NAMA_PENGADU','$span2')</script>   
               #end			  
			   </td>
               
               <td class="$view.rowCss"  align="left" valign="top">			   
               #set($span3 = "span3list"+$view.BIL)	   
			   <span id="$span3">$view.JENIS_ADUAN</span>
               #if($CR_ID_JENISADUAN != "")
               <script>			   
			   highlight_item(returnDropDownSelectedText_value('CR_ID_JENISADUAN'),'$span3');
               </script>
               #end		  
			   </td>
               
               <td class="$view.rowCss"  align="left" valign="top">	
               
               #set($classSenaraiStatus="")               
               #if($view.MESEJ_TINDAKAN != "")                 
             	 #if($view.MESEJ_TINDAKAN == "alert_masih_didalam_tindakan_ui")
                 	#set($classSenaraiStatus="font_tajuk_sub blink")
                 #elseif($view.MESEJ_TINDAKAN == "alert_masih_didalam_tindakan_bahagian")
                 	#set($classSenaraiStatus="font_tajuk_sub blink")
                 #end                 
               #end   
			   	
               
               #set($span13 = "span13list"+$view.BIL)	   
			   <span id="$span13" class="$classSenaraiStatus">$view.STATUS</span>
               #if($CR_ID_STATUS != "")
               <script>			   
			   highlight_item(returnDropDownSelectedText_value('CR_ID_STATUS'),'$span13');
               </script>
               #end	
               
               		  
			   </td>
               
			   
			   
			   <td class="$view.rowCss"  align="center" valign="top">
               #if((($view.ID_PENGADU == $USER_ID_SYSTEM && $view.ID_STATUS == "16125") || ($view.ID_PENGADU == "" && $USER_ROLE == "user_unit_intergriti" && ($view.ID_STATUS == "16125"))) ||  ($view.FLAG_SEMENTARA == 'Y'))	
               
                 <a href="javascript:if(confirm('$label_adakah_pasti_delete')){ doDivAjaxCall$formname('div_Senarai','delete','ID_ADUANPUBLIC=$view.ID_ADUANPUBLIC');}"><img title="$label_hapus"  src="../img/hapus.gif" border="0"></a>
                #end
                		  
	   		 
               
              
              
              #if($view.MESEJ_TINDAKAN != "")
             	 <div class="font_tajuk_sub blink" align="center" ><font color="blue">                 
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
		
		#end
      
	#else
	<tr >
		   <td  align="left" valign="top" colspan="14" >$label_tiada_rekod</td>
		     
	</tr>
    <script>
	var butoncetakCT =  document.getElementById('cmdCetakBU');
	if (typeof(butoncetakCT) != 'undefined' && butoncetakCT != null)
    {
    	butoncetakCT.style.display = "none";
    }
	</script>
	#end
	</table>


</td>
</tr>
</table>
</fieldset>
<br>
