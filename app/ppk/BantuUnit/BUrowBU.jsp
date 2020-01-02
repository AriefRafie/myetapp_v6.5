<tr  >
			   <td class="$rowCss"  align="center" valign="top" >$BIL
			   
			   </td>
			   <td class="$rowCss"  align="left" valign="top">			   
			   #set($span1 = "span1listBU"+$BIL)
			   <span id="$span1"> 
			   <a href="javascript:doDivAjaxCall$formname('div_rowBU$viewBU.ID_PERMOHONANBANTUUNIT','editBU','ID_PERMOHONANBANTUUNIT=$viewBU.ID_PERMOHONANBANTUUNIT&BIL=$BIL&rowCss=$rowCss');">
			   <font color='blue' >
			   #if($viewBU.ID_STATUS == "1" && $viewBU.ID_UNIT == $USER_UNIT && $USER_ROLE == "adminppk")
			   <u>$viewBU.NO_BANTUUNIT</u> <br>
			   <span  class="blink" ><i><b>Tindakan Pegawai Pelulus!<b></i></span>
			   #else
			   <u>$viewBU.NO_BANTUUNIT</u>
			   #end
			   </font>
			   </a>
			   </span>			
			   <script>highlight_item('$BU_NO_BANTUUNIT','$span1')</script>   
			   </td>			   
			   <td class="$rowCss"  align="left" valign="top">			   
			   #set($span2 = "span2listBU"+$BIL)
			   <span id="$span2"> 
			   $viewBU.NAMA_PEMOHON			  
			   </span>			
			   <script>highlight_item('$BU_NAMA_PEMOHON','$span2')</script>  
               
               <br />
               $viewBU.PEJABAT_PEMOHON, $viewBU.NEGERI_PEMOHON
			   
                
			   </td>
			   <td class="$rowCss"  align="left" valign="top">	
               #if($viewBU.ID_PELULUS != "")		   
                   #set($span3 = "span3listBU"+$BIL)
                   <span id="$span3"> 
                   $viewBU.NAMA_PELULUS
                   </span>			
                   <script>highlight_item('$BU_NAMA_PELULUS','$span3')</script>   
                    <br />
                   $viewBU.PEJABAT_PELULUS, $viewBU.NEGERI_PELULUS
               #end
			   </td>
			   <td class="$rowCss"  align="center" valign="top">		   
			   $viewBU.TARIKH_MULA <br />-<br /> $viewBU.TARIKH_AKHIR
			   </td>
			   
			   <td class="$rowCss"  align="left" valign="top">	
               #set($span4 = "span4listBU"+$BIL)	   
			   <span id="$span4">$viewBU.NAMA_NEGERI</span>,
               #if($BU_ID_NEGERI != "")
               <script>			   
			   highlight_item(returnDropDownSelectedText_value('BU_ID_NEGERI'),'$span4');
               </script>
               #end
               <br />
               #set($span5 = "span5listBU"+$BIL)	  
               <span id="$span5" > 
               $viewBU.NAMA_UNIT 
               </span>
               #if($BU_ID_NEGERI != "")
               <script>			   
			   highlight_item(returnDropDownSelectedText_value('BU_ID_UNIT'),'$span5');
               </script>
			   #end
			   </td>
			   
               <td class="$rowCss"  align="left" valign="top">		   
			   $viewBU.DAERAH_JAGAAN
			   </td>
			   <td class="$rowCss"  align="center" valign="top">		
               #set($span6 = "span6listBU"+$BIL)	
               <span id="$span6" >      
			   $viewBU.STATUS
               </span>
               #if($BU_ID_STATUS != "")
               <script>			   
			   highlight_item(returnDropDownSelectedText_value('BU_ID_STATUS'),'$span6');
               </script>
			   #end
			   </td>
			   
			   <td class="$rowCss"  align="center" valign="top">
			   <a href="javascript:doDivAjaxCall$formname('div_rowBU$viewBU.ID_PERMOHONANBANTUUNIT','editBU','ID_PERMOHONANBANTUUNIT=$viewBU.ID_PERMOHONANBANTUUNIT&BIL=$BIL&rowCss=$rowCss');"><img title="Kemaskini" src="../img/edit.gif" border="0"></a>
	   		   #if($viewBU.ID_STATUS == "1")
	   		   <a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('div_SenaraiBU','deleteBU','ID_PERMOHONANBANTUUNIT=$viewBU.ID_PERMOHONANBANTUUNIT');}"><img title="Hapusx"  src="../img/delete.gif" border="0"></a>
	   		   #end
			   </td>	   
		</tr>





