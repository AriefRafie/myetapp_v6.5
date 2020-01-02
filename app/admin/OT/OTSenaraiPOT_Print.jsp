<!-- open ct -->
	
	#if($listPOT.size()>0)
	<div id="div_LaporanPOTforPrint" style="display:none" >
	
	<table width="100%"  
	style="border-collapse: collapse;"  cellspacing="1" cellpadding="2"  width="100%" > 	
	<tr>
	<td valign="top"  width="1%"></td><td valign="top"  width="28%"></td><td valign="top"  width="1%"></td><td valign="top"  width="70%"><!-- $viewPengguna --></td>
	</tr>
	<tr>
	<td valign="top" colspan="4"  style="border-bottom: 1px solid #000;font-size: 120%;" >
	<b>LAPORAN PERMOHONAN OT</b>
	</td>
	</tr>
	
	
	
	
	#if($POT_ID_NEGERI != "" && ($POT_ID_UNIT == "" || $POT_ID_UNIT == "ALL"))	
		<tr >
		<td valign="top"  >
		</td>
		<td valign="top" style="font-size: 100%;" >NEGERI</td>
		<td valign="top"  style="font-size: 100%;" >
		:
		</td>
		<td valign="top" style="font-size: 100%;">
		<span id="display_NEGERI"></span>
			<script>
			$jquery(document).ready(function () {
			returnDropDownSelectedText('POT_ID_NEGERI','display_NEGERI');
			});
			</script>
		</td>
		</tr>
	#end
	
	#if($POT_ID_UNIT != "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 100%;" >UNIT</td>
	<td valign="top"  style="font-size: 100%;" >
	:
	</td>
	<td valign="top" style="font-size: 100%;">
	<span id="display_Unit"></span>
	<script>
	$jquery(document).ready(function () {
	returnDropDownSelectedText('POT_ID_UNIT','display_Unit');
	});
	</script>
	</td>
	</tr>
	#end
	
	#if($POT_NO_OT != "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 100%;" >NO. OT</td>
	<td valign="top"  style="font-size: 100%;" >
	:
	</td>
	<td valign="top" style="font-size: 100%;">
	$POT_NO_OT
	</td>
	</tr>
	#end
	
	#if($POT_NAMA_PEMOHON != "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 100%;" >PEMOHON</td>
	<td valign="top"  style="font-size: 100%;" >
	:
	</td>
	<td valign="top" style="font-size: 100%;">
	$POT_NAMA_PEMOHON
	</td>
	</tr>
	#end
	
	#if($POT_NAMA_PELULUS != "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 100%;" >PEGAWAI PELULUS</td>
	<td valign="top"  style="font-size: 100%;" >
	:
	</td>
	<td valign="top" style="font-size: 100%;">
	$POT_NAMA_PELULUS
	</td>
	</tr>
	#end
	
	#if($POT_TARIKH_OT_MULA != "" || $POT_TARIKH_OT_AKHIR != "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 100%;" >TARIKH OT</td>
	<td valign="top"  style="font-size: 100%;" >
	:
	</td>
	<td valign="top" style="font-size: 100%;">
	#if($POT_TARIKH_OT_MULA !="")
	Dari $POT_TARIKH_OT_MULA
	#end
	
	#if($POT_TARIKH_OT_MULA != "" && $POT_TARIKH_OT_AKHIR != "")
	&nbsp;
	#end
	
	#if($POT_TARIKH_OT_AKHIR !="")
	Sehingga $POT_TARIKH_OT_AKHIR
	#end
	</td>
	</tr>
	#end
	
	#if($POT_ID_STATUS != "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 100%;" >STATUS</td>
	<td valign="top"  style="font-size: 100%;" >
	:
	</td>
	<td valign="top" style="font-size: 100%;">
	<span id="display_Status"></span>
	<script>
	$jquery(document).ready(function () {
	returnDropDownSelectedText('POT_ID_STATUS','display_Status');
	});
	</script>
	</td>
	</tr>
	#end
	
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 100%;" >JUMLAH MINIT OT</td>
	<td valign="top"  style="font-size: 100%;" >
	:
	</td>
	<td valign="top" style="font-size: 100%;">
	<span id="total_OT"></span>
	
	</td>
	</tr>
	
	</table>
	
	<table style="border-collapse:collapse;"  cellspacing="1" cellpadding="2"  width="100%" > 	
		
	#if($listLogOT_forPrint.size()>0)
	<script>
	var butoncetakCT =  document.getElementById('cmdCetakPOT');
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
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>NO. OT</b></th>
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>PEMOHON</b></th>
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>PELULUS</b></th>
		   <th style="border: 1px solid black;font-size:75%;"  align="center" valign="top"><b>TARIKH OT</b></th>		   
		   <th style="border: 1px solid black;font-size:75%;"  align="center" valign="top"><b>MINIT OT</b></th>
		   <th style="border: 1px solid black;font-size:75%;"  align="center" valign="top"><b>STATUS</b></th>
	</tr>
	</thead>
	
		#set($totalMinitOT = 0)
		#foreach($LOGPRINT in $listLogOT_forPrint)
		
		<tr class="page-break"  >
		   <td width="2%" style="border: 1px solid black;font-size:75%;"   align="center" valign="top" >$LOGPRINT.BIL </td>
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">
		    $LOGPRINT.NO_OT
		   </td>
		    <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">
		    $LOGPRINT.NAMA_PEMOHON
		   </td>
		    <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">
		    $LOGPRINT.NAMA_PELULUS
		   </td>
		   <td style="border: 1px solid black;font-size:75%;"  align="center" valign="top">
		    $LOGPRINT.TARIKH_MULA - $LOGPRINT.TARIKH_AKHIR
		   </td>
		   <td style="border: 1px solid black;font-size:75%;"  align="center" valign="top">
		   $LOGPRINT.OV_MOT		
		   #set($totalMinitOT = $totalMinitOT + $LOGPRINT.OV_MOT	)    
		   </td>
		   <td style="border: 1px solid black;font-size:75%;"  align="center" valign="top">
		    $LOGPRINT.STATUS
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
	
	<script>	
	document.getElementById('total_OT').innerHTML='$totalMinitOT';
	</script>
	
	</div>
	#end
	<!-- close ct -->
	

	
	<script type="text/javascript">
	printHideDiv('div_LaporanPOTforPrint')
	</script>