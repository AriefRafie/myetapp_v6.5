
<script>
	if( $jquery('#div_rowBorang$viewBorang.ID_BORANGPNB').length )         // use this if you are using id to check
	{
		window.scrollTo(0, $jquery('#div_rowBorang$viewBorang.ID_BORANGPNB').offset().top);
	}
</script>
<tr id="div_rowBorang$viewBorang.ID_BORANGPNB" >
			   <td class="$rowCss"  align="center" valign="top" >$BIL
			    <input type="hidden" id="HID_OPENCLOSE_ROWBORANG$viewBorang.ID_BORANGPNB" name="HID_OPENCLOSE_ROWBORANG$viewBorang.ID_BORANGPNB" value = "$HID_OPENCLOSE_ROWBORANG"  >
			   </td>
			   <td class="$rowCss"  align="left" valign="top">			   
			   #set($span1 = "span1listBorang"+$BIL)
			   <span id="$span1"> 
			   <a href="javascript:doDivAjaxCall$formname('div_rowBorangPenerima$viewBorang.ID_BORANGPNB','showBorangPenerima','ID_BORANGPNB=$viewBorang.ID_BORANGPNB&BIL=$BIL&rowCss=$rowCss&FLAG_PNB_CARIAN=$FLAG_PNB_CARIAN&HID_OPENCLOSE_ROWBORANG='+$jquery('#HID_OPENCLOSE_ROWBORANG$viewBorang.ID_BORANGPNB').val());">	
               <font color='blue'><u>$viewBorang.NO_PNB</u></font> 
			   </a>
			   </span>			
			   <script>highlight_item('$CARIAN_NO_PNB','$span1')</script>   
               <br />
               $viewBorang.TARIKH_HANTARPNB
			   </td>			   
			   <td class="$rowCss"  align="left" valign="top">	
               #set($span4 = "span4listBorang"+$BIL)
			   <span id="$span4"> 
			   $viewBorang.NO_FAIL 			  
			   </span>			
			   <script>highlight_item('$CARIAN_NO_FAIL','$span4')</script>  
               <br />		   
			   #set($span2 = "span2listBorang"+$BIL)
			   <span id="$span2"> 
			   $viewBorang.NAMAUNIT, 			  
			   </span>			
			   <script>highlight_item(returnDropDownSelectedText_value('CARIAN_UNIT'),'$span2')</script>                
               <br />
               #set($span3 = "span3listBorang"+$BIL)
			   <span id="$span3"> 
			   $viewBorang.NEGERIUNIT 			  
			   </span>			
			   <script>highlight_item(returnDropDownSelectedText_value('CARIAN_NEGERI'),'$span3')</script>                               
			   </td>
               
               <td class="$rowCss"  align="left" valign="top">	
               
               #set($span6 = "span6listBorang"+$BIL)
			   <span id="$span6"> 
			   $viewBorang.JENIS_BORANG 			  
			   </span>			
			   <script>highlight_item('$CARIAN_JENIS_BORANG','$span6')</script>
              
			   </td>
               
			   <td class="$rowCss"  align="left" valign="top">	
               
               
               #set($span5 = "span5listBorang"+$BIL)
			   <span id="$span5"> 
               <a href="javascript:downloadBorangPNB('$viewBorang.ID_BORANGPNB','$viewBorang.ID_FAIL');if('$viewBorang.FLAG_DOWNLOAD' != 'Y'){doDivAjaxCall$formname('div_rowBorang$viewBorang.ID_BORANGPNB','downloadBorang','BIL=$BIL&rowCss=$rowCss&ID_BORANGPNB=$viewBorang.ID_BORANGPNB&HID_OPENCLOSE_ROWBORANG='+$jquery('#HID_OPENCLOSE_ROWBORANG$viewBorang.ID_BORANGPNB').val());}">	
               <font color='blue'><u>$viewBorang.NAMA_BORANG</u></font> 
			   </a>
               
               	  
			   </span>			
			   <script>highlight_item('$CARIAN_NAMA_BORANG','$span5')</script>  
               #if($viewBorang.FLAG_DOWNLOAD == "Y")
               <BR />
               [Diterima Pada $viewBorang.TARIKH_DOWNLOAD]
               #end   
			   </td>
               
               <td class="$rowCss"  align="center" valign="top">	
               $viewBorang.COUNT_PAGES 
			   </td>
			  
			   
			   <td class="$rowCss"  align="center" valign="top">	
               $viewBorang.JUMLAH_PENERIMA 
			   </td>
               
               <td class="$rowCss"  align="center" valign="top">	
               #if($viewBorang.JUMLAH_PENERIMA != $viewBorang.JUMLAH_DIHANTAR && $viewBorang.JUMLAH_PENERIMA>0 )
               <font color='red'><b>$viewBorang.JUMLAH_DIHANTAR</b></font> 
               #else
               $viewBorang.JUMLAH_DIHANTAR 
               #end
			   </td>
               
               <td class="$rowCss"  align="center" valign="top">	
               
               
               #set($span8 = "span8listBorang"+$BIL)
			   <span id="$span8"> 
               $viewBorang.JUMLAH_RETURN
			   </span>	
               #if($viewBorang.JUMLAH_RETURN > 0)		
			   <script>
			   if(document.getElementById("CARIAN_STATUS").value=='5')
			   {
			   highlight_item('$viewBorang.JUMLAH_RETURN','$span8')
			   }               
               </script>
               #end                
               </td>
               
               <td class="$rowCss"  align="left" valign="top">	
               
               
               #set($span7 = "span7listBorang"+$BIL)
			   <span id="$span7">               
               #if($viewBorang.STATUS_BORANG=="1")
               HANTAR KE PNB
               #elseif($viewBorang.STATUS_BORANG=="2")
               DITERIMA PNB
               #elseif($viewBorang.STATUS_BORANG=="3")
               DALAM PROSES
               #elseif($viewBorang.STATUS_BORANG=="4")
               SELESAI
               #end	  
			   </span>			
			   <script>
			  
			   highlight_item(returnDropDownSelectedText_value('CARIAN_STATUS'),'$span7')</script> 
               
               
              
			   </td>
			   
                
		</tr>