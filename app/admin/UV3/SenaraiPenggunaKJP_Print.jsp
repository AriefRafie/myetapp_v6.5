


<!-- open ct -->
#if($CT_OPENCLOSE_CARIAN=="open")
<script>
	document.getElementById('div_CT_CARIAN_KJP').style.display = "";
	document.getElementById('span_LINK_CT_CARIAN_KJP').style.display = "none";
	//document.getElementById('div_CT_CARIAN_$internalType').className = "classFade";
</script>
#end
#if($CT_OPENCLOSE_CARIAN=="close")
<script>
	document.getElementById('div_CT_CARIAN_KJP').style.display = "none";
	document.getElementById('span_LINK_CT_CARIAN_KJP').style.display = "";
	//document.getElementById('div_CT_CARIAN_$internalType').className = "";
</script>
#end
<!-- close ct -->


	<!-- open ct -->
	#if($listPenggunaKJP.size()>0)
	<div id="div_LaporanPenggunaforPrint_KJP" style="display:none" >
	
	<table width="100%"  
	style="border-collapse: collapse;"  cellspacing="1" cellpadding="2"  width="100%" > 	
	<tr>
	<td valign="top"  width="1%"></td><td valign="top"  width="28%"></td><td valign="top"  width="1%"></td><td valign="top"  width="70%"><!-- $viewPengguna --></td>
	</tr>
	<tr>
	<td valign="top" colspan="4"  style="border-bottom: 1px solid #000;font-size: 120%;" >
	<b>LAPORAN PENGGUNA (KJP)</b>
	</td>
	</tr>
	
	#if($CT_ID_KEMENTERIAN != "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 100%;" >KEMENTERIAN</td>
	<td valign="top"  style="font-size: 100%;" >
	:
	</td>
	<td valign="top" style="font-size: 100%;">
	<span id="display_CT_ID_KEMENTERIAN_KJP"></span>
	<script>
	$jquery(document).ready(function () {
	returnDropDownSelectedText('CT_ID_KEMENTERIAN_KJP','display_CT_ID_KEMENTERIAN_KJP');
	});
	</script>
	</td>
	</tr>
	#end
	
	#if($CT_ID_AGENSI != "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 100%;" >AGENSI</td>
	<td valign="top"  style="font-size: 100%;" >
	:
	</td>
	<td valign="top" style="font-size: 100%;">
	<span id="display_CT_ID_AGENSI_KJP"></span>
	<script>
	$jquery(document).ready(function () {
	returnDropDownSelectedText('CT_ID_AGENSI_KJP','display_CT_ID_AGENSI_KJP');
	});
	</script>
	</td>
	</tr>
	#end
	
	#if($CT_ID_TUGASAN != "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 100%;" >TUGASAN</td>
	<td valign="top"  style="font-size: 100%;" >
	:
	</td>
	<td valign="top" style="font-size: 100%;">
	<span id="display_CT_ID_TUGASAN_KJP"></span>
	<script>
	$jquery(document).ready(function () {
	returnDropDownSelectedText('CT_ID_TUGASAN_KJP','display_CT_ID_TUGASAN_KJP');
	});
	</script>
	</td>
	</tr>
	#end
	
	#if($CT_FLAG_AKTIF != "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 100%;" >STATUS KEAKTIFAN</td>
	<td valign="top"  style="font-size: 100%;" >
	:
	</td>
	<td valign="top" style="font-size: 100%;">
	<span id="display_CT_FLAG_AKTIF_KJP"></span>
	<script>
	$jquery(document).ready(function () {
	returnDropDownSelectedText('CT_FLAG_AKTIF_KJP','display_CT_FLAG_AKTIF_KJP');
	});
	</script>
	</td>
	</tr>
	#end
	
	#if($CT_LOGMASUK_MULA != "" || $CT_LOGMASUK_AKHIR != "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 100%;" >LOG MASUK TERAKHIR</td>
	<td valign="top"  style="font-size: 100%;" >
	:
	</td>
	<td valign="top" style="font-size: 100%;">
	#if($CT_LOGMASUK_MULA !="")
	Dari $CT_LOGMASUK_MULA
	#end
	
	#if($CT_LOGMASUK_MULA != "" && $CT_LOGMASUK_AKHIR != "")
	&nbsp;&nbsp;
	#end
	
	#if($CT_LOGMASUK_AKHIR !="")
	Sehingga $CT_LOGMASUK_AKHIR
	#end
	</td>
	</tr>
	#end
	
	
	
	#if($CT_STATUS_LOG != "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 100%;" >STATUS LOG MASUK</td>
	<td valign="top"  style="font-size: 100%;" >
	:
	</td>
	<td valign="top" style="font-size: 100%;">
	<span id="display_CT_STATUS_LOG_KJP"></span>
	<script>
	$jquery(document).ready(function () {
	returnDropDownSelectedText('CT_STATUS_LOG_KJP','display_CT_STATUS_LOG_KJP');
	});
	</script>
	</td>
	</tr>
	#end
	</table>
	
	<table style="border-collapse:collapse;"  cellspacing="1" cellpadding="2"  width="100%" > 	
		
	#if($PrintlistPenggunaKJP.size()>0)
	<script>
	var butoncetakCT =  document.getElementById('cmdCetakPenggunaCT_KJP');
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
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>NAMA</b></th>
		   #if($CT_ID_KEMENTERIAN == "")
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>KEMENTERIAN</b></th>
		   #end
		   #if($CT_ID_AGENSI == "")
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>AGENSI</b></th>
		   #end
		   #if($CT_ID_TUGASAN == "")
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>TUGASAN</b></th>
		   #end
		   
		   #if($CT_FLAG_AKTIF == "")
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>KEAKTIFAN</b></th>
		   #end
		   
		   #if($CT_LOGMASUK_MULA == "" && $CT_LOGMASUK_AKHIR == "")
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>LOG MASUK TERAKHIR</b></th>
		   #end
		   #if($CT_STATUS_LOG == "")
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>STATUS LOG MASUK</b></th>
		   #end
	</tr>
	</thead>
	
		#foreach($Pengguna_KJP in $PrintlistPenggunaKJP)
		<tr class="page-break" >
		   <td width="2%" style="border: 1px solid black;font-size:75%;"   align="center" valign="top" >$Pengguna_KJP.BIL </td>
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">
		   $Pengguna_KJP.NAMA_PENUH 
		   <br>
		   [$Pengguna_KJP.USER_LOGIN]
		   </td>
		   #if($CT_ID_KEMENTERIAN == "")
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">$Pengguna_KJP.NAMA_KEMENTERIAN</td>
		   #end
		   #if($CT_ID_AGENSI == "")
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">$Pengguna_KJP.NAMA_AGENSI</td>
		   #end
		   #if($CT_ID_TUGASAN == "")
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">$Pengguna_KJP.NAMA_JAWATAN</td>
		   #end
		   
		   #if($CT_FLAG_AKTIF == "")
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">$Pengguna_KJP.KEAKTIFAN</td>
		   #end
		  
		   #if($CT_LOGMASUK_MULA == "" && $CT_LOGMASUK_AKHIR == "")
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">$Pengguna_KJP.WAKTU_AKHIR_LOGIN</td>
		   #end
		   #if($CT_STATUS_LOG == "")
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">$Pengguna_KJP.DISPLAY_NOTE</td>
		   #end
			   
			   
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
	printHideDiv('div_LaporanPenggunaforPrint_KJP','KJP','');
	</script>