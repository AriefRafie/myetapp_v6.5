
	<div id="div_LisLogHide_$ID_PERMOHONANOT" style="display:none" >	
	<table border="0" width="100%" > 	
	<tr>
			<td valign="top"  width="1%"></td>
			<td valign="top"  width="28%"></td>
			<td valign="top"  width="1%"></td>
			<td valign="top"  width="70%" >
			</td>
			</tr>
			<tr >
			<td valign="top"  ></td>
			<td valign="top"  colspan="3" style="border-bottom: 2px solid #000;"><b>Log Overtime</b></td>
	</tr>
	<tr>
	<td valign="top"  ></td>
	<td colspan="3" align="center">
	
	<table style="border-collapse:collapse;"  cellspacing="1" cellpadding="1"  width="100%" > 	
		<thead >
			<tr   >
				   <th width="2%" style="border: 1px solid black;font-size:75%;" align="center" valign="top"><b>BIL.</b></th>
				   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>Nama Aktiviti</b></th>		  
				   <th style="border: 1px solid black;font-size:75%;"  align="center" valign="top"><b>Jenis Transaksi</b></th>
				   <th style="border: 1px solid black;font-size:75%;"  align="center" valign="top"><b>Minit</b></th>
				   <th style="border: 1px solid black;font-size:75%;"  align="center" valign="top"><b>Waktu Log</b></th>
				  
			</tr>
			</thead>
	#if($listLogOT_forPrint.size()>0)
	
	
		#foreach($OTPRINT in $listLogOT_forPrint)
		
			
			
			
		    #if($OTPRINT.LAYER == "1")
			<tr  class="page-break" >
				<td colspan="5" align="left" valign="top" style="border: 1px solid black;font-size:75%;" >				
				<table width="100%" border="0" cellspacing="0" cellpadding="0" >
				<tr >				
				<td width="25%"  style="font-size:75%;" ><b>
				$OTPRINT.TARIKH_LOG</b>
				</td>
				<td width="25%"style="font-size:75%;"   ><b>
				MINIT SEHARI : $OTPRINT.TM_PD</b>
				</td>
				<td width="25%" style="font-size:75%;" ><b>
				MINIT W. BEKERJA : 
				#if($OTPRINT.TM_WH < $OTPRINT.WH_ALL)
					<font color="red">$OTPRINT.TM_WH</font>
				#else
					$OTPRINT.TM_WH
				#end</b>
				</td>
				<td width="25%" style="font-size:75%;" >
				<b>MINIT OT : $OTPRINT.TM_OT</b>
				</td>
				</tr>
				</table>				
				</td>
			</tr>
			#else
			<tr class="page-break" >
			   <td width="2%" style="border: 1px solid black;font-size:75%;"   align="center" valign="top" >$OTPRINT.BIL </td>
			   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">$OTPRINT.NAMA_AKTIVITI</td>
			   <td style="border: 1px solid black;font-size:75%;"  align="center" valign="top">$OTPRINT.TRANSAKSI_TYPE</td>
			   <td style="border: 1px solid black;font-size:75%;"  align="center" valign="top">$OTPRINT.MINIT</td>
			   <td style="border: 1px solid black;font-size:75%;"  align="center" valign="top">$OTPRINT.HMS</td>		   
			</tr>
			#end
		
		#end
		#else		
		<tr>
		<td colspan="8">
		Tiada Rekod
		</td>
		</tr>
		#end	
	</table>
	</td>
	</tr>
	</table>
	</div>
	

	<script type="text/javascript">
	printOTForm('printableArea_$ID_PERMOHONANOT','$ID_PERMOHONANOT','$NO_OT','div_LisLogHide_$ID_PERMOHONANOT')
	</script>