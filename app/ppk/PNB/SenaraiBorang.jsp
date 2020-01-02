
#if($OPENCLOSE_CARIAN=="open")
<script>
	document.getElementById('div_CARIAN').style.display = "";
	document.getElementById('span_LINK_CARIAN').style.display = "none";	
	if( $jquery('#div_CARIAN').length )         // use this if you are using id to check
	{
		window.scrollTo(0, $jquery('#div_CARIAN').offset().top);
	}
</script>
#end
#if($OPENCLOSE_CARIAN=="close")
<script>
	document.getElementById('div_CARIAN').style.display = "none";
	document.getElementById('span_LINK_CARIAN').style.display = "";
	if( $jquery('#main_table').length )         // use this if you are using id to check
	{
		window.scrollTo(0, $jquery('#main_table').offset().top);
	}
</script>
#end


<fieldset>
<table width="100%" align="center">
<tr>
<td>
	<table border="0" cellspacing="1" cellpadding="1" width="100%" > 
	#if($listBorang.size()>0)
    
	<script>
	
	var butoncetak =  document.getElementById('cmdCetak');
	if (typeof(butoncetak) != 'undefined' && butoncetak != null)
    {
    	butoncetak.style.display = "";
    }
	
	</script>
	<tr >
		   <td  align="left" valign="top" colspan="14" >
		   #parse("app/ppk/PNB/record_paging.jsp")
		   </td>
		     
	</tr>
	
	
	#end 
	<tr class="table_header" >
		   <td   align="center" valign="top" width="3%" >Bil.</td>
		   <td   align="left" valign="top" >No. PNB & Tarikh Hantar Ke PNB</td>
		   <td   align="left" valign="top" >No. Fail & Unit PPK</td>
           <td   align="left" valign="top" >Jenis</td>
		   <td   align="left" valign="top" >Borang</td>
           <td   align="center" valign="top" >Muka Surat</td>
           <td   align="center" valign="top" width="5%" >Jumlah Penerima</td>	
           <td   align="center" valign="top" width="5%" >Jumlah Dihantar</td>		     
		   <td   align="center" valign="top" width="5%" >Jumlah Pemulangan</td>		 
           <td   align="left" valign="top" >Status</td> 
		  
	</tr>
	#if($listBorang.size()>0)
	
		#foreach($LB in $listBorang)
		
		<tr id="div_rowBorang$LB.ID_BORANGPNB" >
			   <td class="$LB.rowCss"  align="center" valign="top" >$LB.BIL
			   <input type="hidden" id="HID_OPENCLOSE_ROWBORANG$LB.ID_BORANGPNB" name="HID_OPENCLOSE_ROWBORANG$LB.ID_BORANGPNB" value = "CLOSE"  >
			   
			   </td>
			   <td class="$LB.rowCss"  align="left" valign="top">			   
			   #set($span1 = "span1listBorang"+$LB.BIL)
			   <span id="$span1"> 
               
               #if(($USER_ROLE == "user_pnb" && $LB.FLAG_DOWNLOAD == "Y") || ($USER_ROLE != "user_pnb"))
			   <a href="javascript:doDivAjaxCall$formname('div_rowBorangPenerima$LB.ID_BORANGPNB','showBorangPenerima','ID_BORANGPNB=$LB.ID_BORANGPNB&BIL=$LB.BIL&rowCss=$LB.rowCss&FLAG_PNB_CARIAN=$FLAG_PNB_CARIAN&HID_OPENCLOSE_ROWBORANG='+$jquery('#HID_OPENCLOSE_ROWBORANG$LB.ID_BORANGPNB').val());">	
               <font color='blue'><u>$LB.NO_PNB</u></font> 
			   </a>
               #else
               $LB.NO_PNB
               #end
               
               
			   </span>			
			   <script>highlight_item('$CARIAN_NO_PNB','$span1')</script>   
               <br />
               $LB.TARIKH_HANTARPNB
			   </td>			   
			   <td class="$LB.rowCss"  align="left" valign="top">	
               #set($span4 = "span4listBorang"+$LB.BIL)
			   <span id="$span4"> 
			   $LB.NO_FAIL 			  
			   </span>			
			   <script>highlight_item('$CARIAN_NO_FAIL','$span4')</script>  
               <br />		   
			   #set($span2 = "span2listBorang"+$LB.BIL)
			   <span id="$span2"> 
			   $LB.NAMAUNIT, 			  
			   </span>			
			   <script>highlight_item(returnDropDownSelectedText_value('CARIAN_UNIT'),'$span2')</script>                
               <br />
               #set($span3 = "span3listBorang"+$LB.BIL)
			   <span id="$span3"> 
			   $LB.NEGERIUNIT 			  
			   </span>			
			   <script>highlight_item(returnDropDownSelectedText_value('CARIAN_NEGERI'),'$span3')</script>                               
			   </td>
               
               <td class="$LB.rowCss"  align="left" valign="top">	
               
               #set($span6 = "span6listBorang"+$LB.BIL)
			   <span id="$span6"> 
			   $LB.JENIS_BORANG 			  
			   </span>			
			   <script>highlight_item('$CARIAN_JENIS_BORANG','$span6')</script>
              
			   </td>
               
               
			   <td class="$LB.rowCss"  align="left" valign="top">	
               
               
               #set($span5 = "span5listBorang"+$LB.BIL)
			   <span id="$span5"> 
               <a href="javascript:downloadBorangPNB('$LB.ID_BORANGPNB','$LB.ID_FAIL');if('$LB.FLAG_DOWNLOAD' != 'Y'){doDivAjaxCall$formname('div_rowBorang$LB.ID_BORANGPNB','downloadBorang','BIL=$LB.BIL&rowCss=$LB.rowCss&ID_BORANGPNB=$LB.ID_BORANGPNB&HID_OPENCLOSE_ROWBORANG='+$jquery('#HID_OPENCLOSE_ROWBORANG$LB.ID_BORANGPNB').val());}">	
               <font color='blue'><u>$LB.NAMA_BORANG</u></font> 
			   </a>
               
               	  
			   </span>			
			   <script>highlight_item('$CARIAN_NAMA_BORANG','$span5')</script>    
               
               #if($LB.FLAG_DOWNLOAD == "Y")
               <BR />
               [Diterima Pada $LB.TARIKH_DOWNLOAD]
               #else               
                   #if($USER_ROLE == "user_pnb")
                   <br />
                   <font color="red" class="blink">Sila pastikan lampiran ini dimuat turun!</font>
                   #end               
               #end
                
			   </td>
			  
              <td  class="$LB.rowCss"  align="center" valign="top">	
               $LB.COUNT_PAGES
			   </td>
			   
			   <td class="$LB.rowCss"  align="center" valign="top">	
               $LB.JUMLAH_PENERIMA 
			   </td>
               
               <td class="$LB.rowCss"  align="center" valign="top">	
               #if($LB.JUMLAH_PENERIMA != $LB.JUMLAH_DIHANTAR && $LB.JUMLAH_PENERIMA>0 )
               <font color='red'><b>$LB.JUMLAH_DIHANTAR</b></font> 
               #else
               $LB.JUMLAH_DIHANTAR 
               #end
			   </td>
               
               <td class="$LB.rowCss"  align="center" valign="top">	
               
               
               #set($span8 = "span8listBorang"+$LB.BIL)
			   <span id="$span8"> 
               $LB.JUMLAH_RETURN
			   </span>	
               
               #if($LB.JUMLAH_RETURN > 0)		
			   <script>
			   if(document.getElementById("CARIAN_STATUS").value=='5')
			   {
			   highlight_item('$LB.JUMLAH_RETURN','$span8')
			   }               
               </script>
               #end                  
               </td>
               
               <td class="$LB.rowCss"  align="left" valign="top">	
               
               
               #set($span7 = "span7listBorang"+$LB.BIL)
			   <span id="$span7">               
               #if($LB.STATUS_BORANG=="1")
               HANTAR KE PNB
               #elseif($LB.STATUS_BORANG=="2")
               DITERIMA PNB
               #elseif($LB.STATUS_BORANG=="3")
               DALAM PROSES
               #elseif($LB.STATUS_BORANG=="4")
               SELESAI
               #end	  
			   </span>			
			   <script>highlight_item(returnDropDownSelectedText_value('CARIAN_STATUS'),'$span7')</script> 
               
               
              
			   </td>
			   
                
		</tr>
        <tr id="div_rowBorangPenerima$LB.ID_BORANGPNB">
        
        </tr>
		
		#end
	#else
	<tr >
		   <td  align="left" valign="top" colspan="14" >Tiada Rekod</td>
		     
	</tr>
    <script>
	/*
	var butoncetakCT =  document.getElementById('cmdCetakBU');
	if (typeof(butoncetakCT) != 'undefined' && butoncetakCT != null)
    {
    	butoncetakCT.style.display = "none";
    }
	*/
	</script>
	
	#end
	</table>


</td>
</tr>
</table>
</fieldset>