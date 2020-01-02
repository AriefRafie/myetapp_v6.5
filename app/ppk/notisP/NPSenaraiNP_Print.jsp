<!-- open ct -->
	
	#if($listNP.size()>0)
	<div id="div_LaporanBUforPrint" style="display:none" >
	
	<table width="100%"  
	style="border-collapse: collapse;"  cellspacing="1" cellpadding="2"  width="100%" > 	
	<tr>
	<td valign="top"  width="1%"></td><td valign="top"  width="28%"></td><td valign="top"  width="1%"></td><td valign="top"  width="70%"><!-- $viewPengguna --></td>
	</tr>
	<tr>
	<td valign="top" colspan="4"  style="border-bottom: 1px solid #000;font-size: 120%;" >
	<b>LAPORAN SENARAI NOTIS PERBICARAAN</b>
	</td>
	</tr>
	
	
	
	
	
	
	</table>
	
	<table style="border-collapse:collapse;"  cellspacing="1" cellpadding="2"  width="100%" > 	
		
	#if($listNP_forPrint.size()>0)
	<script>
	var butoncetakCT =  document.getElementById('cmdCetakBU');
    if (typeof(butoncetakCT) != 'undefined' && butoncetakCT != null)
    {
    	butoncetakCT.style.display = "";
    }
	</script>
	
	
	<thead >
	<tr>
		<th colspan="10"><br></th>
	</tr>
	<tr   >
		   <th width="2%" style="border: 1px solid black;font-size:75%;" align="center" valign="top"><b>BIL.</b></th>
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>NO FAIL</b></th>
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>NAMA SIMATI</b></th>
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>KEPUTUSAN PERMOHONAN</b></th>
		   <th style="border: 1px solid black;font-size:75%;"  align="center" valign="top"><b>TARIKH NOTIS</b></th>		   
	
	</tr>
	</thead>
	
		#foreach($LOGPRINT in $listNP_forPrint)
		
		<tr class="page-break"  >
		   <td width="2%" style="border: 1px solid black;font-size:75%;"   align="center" valign="top" >$LOGPRINT.BIL </td>
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">
		    $LOGPRINT.NO_FAIL
		   </td>
		    <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">
		    $LOGPRINT.NAMA_SIMATI
		   </td>
		    <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">
		    $LOGPRINT.KETERANGAN
            
		   </td>
		   <td style="border: 1px solid black;font-size:75%;"  align="center" valign="top">
		   $LOGPRINT.TARIKH_NOTIS 
		   </td>
		   
			   
			   
		</tr>
		
		#end
		#else		
		<tr>
		<td colspan="8">
		Tiada Rekod
		</td>
		</tr>
		#end	
	</table>
	
	
	
	</div>
	#end
	<!-- close ct -->
	

	
	<script type="text/javascript">
	printHideDiv('div_LaporanBUforPrint')
	</script>