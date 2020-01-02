<tr >
			   <td class="$rowCss"  align="center" valign="top" >$BIL</td>
			   <td class="$rowCss"  align="left" valign="top">			   
			   #set($span1 = "span1lisPMTT"+$BIL)
			   <span id="$span1"> 
			   $viewPMT.NAMA_AKTIVITI
			   </span>			
			   <script>highlight_item('$PMT_NAMA_AKTIVITI','$span1')</script>   
			   </td>
			   <td class="$rowCss"  align="left" valign="top">		   
			   $viewPMT.MINIT_INSERT
			   </td>
			   <td class="$rowCss"  align="left" valign="top">
			   $viewPMT.MINIT_UPDATE
			   </td>
			   
			   <td class="$rowCss"  align="center" valign="top">
			   <a href="javascript:doDivAjaxCall$formname('div_rowPMT$viewPMT.ID_MINITTRANSAKSI','editPMT','ID_MINITTRANSAKSI=$viewPMT.ID_MINITTRANSAKSI&BIL=$BIL&rowCss=$rowCss');"><img title="Kemaskini" src="../img/edit.gif" border="0"></a>
	   		   <a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('div_SenaraiPMT','deletePMT','ID_MINITTRANSAKSI=$viewPMT.ID_MINITTRANSAKSI');}"><img title="Hapus"  src="../img/delete.gif" border="0"></a>
	   		  
			   </td>	   
		</tr>