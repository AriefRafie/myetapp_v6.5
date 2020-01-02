<!-- open ct -->
	
	#if($listBU.size()>0)
	<div id="div_LaporanBUforPrint" style="display:none" >
	
	<table width="100%"  
	style="border-collapse: collapse;"  cellspacing="1" cellpadding="2"  width="100%" > 	
	<tr>
	<td valign="top"  width="1%"></td><td valign="top"  width="28%"></td><td valign="top"  width="1%"></td><td valign="top"  width="70%"><!-- $viewPengguna --></td>
	</tr>
	<tr>
	<td valign="top" colspan="4"  style="border-bottom: 1px solid #000;font-size: 120%;" >
	<b>LAPORAN PERMOHONAN BANTU UNIT</b>
	</td>
	</tr>
	
	
	
	
	#if($BU_ID_NEGERI != "" && ($BU_ID_UNIT == "" || $BU_ID_UNIT == "ALL"))	
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
			returnDropDownSelectedText('BU_ID_NEGERI','display_NEGERI');
			});
			</script>
		</td>
		</tr>
	#end
	
	#if($BU_ID_UNIT != "")
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
	returnDropDownSelectedText('BU_ID_UNIT','display_Unit');
	});
	</script>
	</td>
	</tr>
	#end
	
	#if($BU_NO_BANTUUNIT != "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 100%;" >NO. BANTU UNIT</td>
	<td valign="top"  style="font-size: 100%;" >
	:
	</td>
	<td valign="top" style="font-size: 100%;">
	$BU_NO_BANTUUNIT
	</td>
	</tr>
	#end
	
	#if($BU_NAMA_PEMOHON != "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 100%;" >PEMOHON</td>
	<td valign="top"  style="font-size: 100%;" >
	:
	</td>
	<td valign="top" style="font-size: 100%;">
	$BU_NAMA_PEMOHON
	</td>
	</tr>
	#end
	
	#if($BU_NAMA_PELULUS != "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 100%;" >PEGAWAI PELULUS</td>
	<td valign="top"  style="font-size: 100%;" >
	:
	</td>
	<td valign="top" style="font-size: 100%;">
	$BU_NAMA_PELULUS
	</td>
	</tr>
	#end
	
	#if($BU_TARIKH_BU_MULA != "" || $BU_TARIKH_BU_AKHIR != "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 100%;" >TARIKH BANTU UNIT</td>
	<td valign="top"  style="font-size: 100%;" >
	:
	</td>
	<td valign="top" style="font-size: 100%;">
	#if($BU_TARIKH_BU_MULA !="")
	Dari $BU_TARIKH_BU_MULA
	#end
	
	#if($BU_TARIKH_BU_MULA != "" && $BU_TARIKH_BU_AKHIR != "")
	&nbsp;
	#end
	
	#if($BU_TARIKH_BU_AKHIR !="")
	Sehingga $BU_TARIKH_BU_AKHIR
	#end
	</td>
	</tr>
	#end
	
	#if($BU_ID_STATUS != "")
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
	returnDropDownSelectedText('BU_ID_STATUS','display_Status');
	});
	</script>
	</td>
	</tr>
	#end
	
	
	
	</table>
	
	<table style="border-collapse:collapse;"  cellspacing="1" cellpadding="2"  width="100%" > 	
		
	#if($listLogOT_forPrint.size()>0)
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
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>NO. BANTU UNIT</b></th>
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>PEMOHON</b></th>
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>PELULUS</b></th>
		   <th style="border: 1px solid black;font-size:75%;"  align="center" valign="top"><b>TARIKH BANTUAN</b></th>		   
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>NEGERI & UNIT BANTUAN</b></th>
		   <th style="border: 1px solid black;font-size:75%;"  align="center" valign="top"><b>STATUS</b></th>
	</tr>
	</thead>
	
		#foreach($LOGPRINT in $listLogOT_forPrint)
		
		<tr class="page-break"  >
		   <td width="2%" style="border: 1px solid black;font-size:75%;"   align="center" valign="top" >$LOGPRINT.BIL </td>
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">
		    $LOGPRINT.NO_BANTUUNIT
		   </td>
		    <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">
		    $LOGPRINT.NAMA_PEMOHON
            <br />
            $LOGPRINT.PEJABAT_PEMOHON
		   </td>
		    <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">
		    $LOGPRINT.NAMA_PELULUS
            <br />
            $LOGPRINT.PEJABAT_PELULUS
		   </td>
		   <td style="border: 1px solid black;font-size:75%;"  align="center" valign="top">
		   $LOGPRINT.TARIKH_MULA <br />-<br /> $LOGPRINT.TARIKH_AKHIR
		   </td>
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">
		   $LOGPRINT.NAMA_UNIT   
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
	
	
	
	</div>
	#end
	<!-- close ct -->
	

	
	<script type="text/javascript">
	printHideDiv('div_LaporanBUforPrint')
	</script>