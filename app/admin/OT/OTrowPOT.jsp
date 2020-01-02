<tr >
			   <td class="$rowCss"  align="center" valign="top" >$BIL
			   
			   </td>
			   <td class="$rowCss"  align="left" valign="top">			   
			   #set($span1 = "span1listPOT"+$BIL)
			   <a href="javascript:doDivAjaxCall$formname('div_rowPOT$viewPOT.ID_PERMOHONANOT','editPOT','ID_PERMOHONANOT=$viewPOT.ID_PERMOHONANOT&BIL=$BIL&rowCss=$rowCss');">
			   <font color='blue' >
			   <span id="$span1"> 
			   #if($viewPOT.ID_STATUS == "1" && $viewPOT.ID_PELULUS == $USER_ID_SYSTEM)
			   <u>$viewPOT.NO_OT</u> <br>
			   <span  class="blink" ><i><b>Tindakan Pegawai Pelulus!</b></i></span>
			   #else
			   <u>$viewPOT.NO_OT</u>
			   #end
			   </span>
			   </font>	
			   </a>		
			   <script>highlight_item('$viewPOT_NO_OT','$span1')</script>   
			   </td>
			   
			   
			   
			   
			   
			   
			   
			   			   
			   <td class="$rowCss"  align="left" valign="top">			   
			   #set($span2 = "span2listPOT"+$BIL)
			   <span id="$span2"> 
			   $viewPOT.NAMA_PEMOHON<br>
			   $viewPOT.NAMA_UNIT,<br> 
			   $viewPOT.NAMA_NEGERI
			   </span>			
			   <script>highlight_item('$viewPOT_NAMA_PEMOHON','$span2')</script>   
			   </td>
			   <td class="$rowCss"  align="left" valign="top">			   
			   #set($span3 = "span3listPOT"+$BIL)
			   <span id="$span3"> 
			   $viewPOT.NAMA_PELULUS
			   </span>			
			   <script>highlight_item('$viewPOT_NAMA_PELULUS','$span3')</script>   
			   </td>
			   <td class="$rowCss"  align="center" valign="top">		   
			   $viewPOT.TARIKH_MULA - $viewPOT.TARIKH_AKHIR
			   </td>
			   <!--  
			   <td class="$rowCss"  align="center" valign="top">		   
			   $viewPOT.TARIKH_AKHIR
			   </td>
			   -->
			   <td class="$rowCss"  align="center" valign="top">		   
			   $viewPOT.STATUS
			   </td>
			   
			    <td class="$rowCss"  align="center" valign="top">		   
			   <table width="100%" border="0" cellspacing="0" cellpadding="0" >
				   <tr>
				   	<td valign="top" width="70%">Jumlah Hari</td><td valign="top" width="1%">:</td><td valign="top" width="29%">$OVERALLMINIT.OV_DAYS</td>
				   </tr>
				   <tr>
				   	<td valign="top">Waktu Kerja</td><td valign="top">:</td><td valign="top">
				   	#if($OVERALLMINIT.OV_MWH < $OVERALLMINIT.OV_MWH_ALL)
				   		<font color="red">$OVERALLMINIT.OV_MWH</font>
				   	#else
				   		$OVERALLMINIT.OV_MWH
				   	#end
				   	</td>
				   </tr>
				   <tr>
				   	<td valign="top"><b>OT</b></td><td valign="top">:</td><td valign="top"><b>$OVERALLMINIT.OV_MOT</b></td>
				   </tr>
				   <tr>
				   	<td valign="top">Total</td><td valign="top">:</td><td valign="top">$OVERALLMINIT.OV_MPD</td>
				   <tr>
			   </table>
			   </td>
			   
			   <td class="$rowCss"  align="center" valign="top">
			   <a href="javascript:doDivAjaxCall$formname('div_rowPOT$viewPOT.ID_PERMOHONANOT','editPOT','ID_PERMOHONANOT=$viewPOT.ID_PERMOHONANOT&BIL=$BIL&rowCss=$rowCss');"><img title="Kemaskini" src="../img/edit.gif" border="0"></a>
	   		   <a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('div_SenaraiPOT','deletePOT','ID_PERMOHONANOT=$viewPOT.ID_PERMOHONANOT');}"><img title="Hapus"  src="../img/delete.gif" border="0"></a>
	   		  
			   </td>	   
		</tr>