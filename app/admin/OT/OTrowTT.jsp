<tr >
			   <td class="$rowCss"  align="center" valign="top" >$BIL</td>
			   <td class="$rowCss"  align="left" valign="top">			   
			   #set($span1 = "span1listTT"+$BIL)
			   <span id="$span1"> 
			   $viewTT.NAMA_AKTIVITI
			   </span>			
			   <script>highlight_item('$TT_NAMA_AKTIVITI','$span1')</script>   
			   </td>
			   <td class="$rowCss"  align="left" valign="top">			   
			   #set($span2 = "span2listTT"+$BIL)
			   <span id="$span2"> 
			   $viewTT.NAMA_TABLE
			   </span>		
			   <script>highlight_item('$TT_NAMA_TABLE','$span2')</script>	   
			   </td>
			   <td class="$rowCss"  align="left" valign="top">	
			  	   
			   #set($span3 = "span3listTT"+$BIL)
			   <span id="$span3"> 
			   $viewTT.FIELD_CHECK
			   </span>			   
			   <script>highlight_item('$TT_NAMA_FIELD','$span3')</script>	
			   			   
			   </td>
			   <!--
			   <td class="$rowCss"  align="left" valign="top">			   
			   #set($span4 = "span4listTT"+$BIL)
			   <span id="$span4"> 
			   $viewTT.NAMA_SEKSYEN
			   </span>			   
			   </td>
			   -->
			   <td class="$rowCss"  align="center" valign="top">
			   <a href="javascript:doDivAjaxCall$formname('div_rowTT$viewTT.ID_TRANSAKSI','editTT','ID_TRANSAKSI=$viewTT.ID_TRANSAKSI&BIL=$BIL&rowCss=$rowCss');"><img title="Kemaskini" src="../img/edit.gif" border="0"></a>
	   		   <a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('div_SenaraiTT','deleteTT','ID_TRANSAKSI=$viewTT.ID_TRANSAKSI');}"><img title="Hapus"  src="../img/delete.gif" border="0"></a>
	   		  
			   </td>	   
		</tr>