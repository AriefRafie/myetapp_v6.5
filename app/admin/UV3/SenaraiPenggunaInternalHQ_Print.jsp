

<!-- open ct -->
#if($CT_OPENCLOSE_CARIAN=="open")
<script>
	document.getElementById('div_CT_CARIAN_HQ').style.display = "";
	document.getElementById('span_LINK_CT_CARIAN_HQ').style.display = "none";
	//document.getElementById('div_CT_CARIAN_$internalType').className = "classFade";
</script>
#end
#if($CT_OPENCLOSE_CARIAN=="close")
<script>
	document.getElementById('div_CT_CARIAN_HQ').style.display = "none";
	document.getElementById('span_LINK_CT_CARIAN_HQ').style.display = "";
	//document.getElementById('div_CT_CARIAN_$internalType').className = "";
</script>
#end
<!-- close ct -->


	<!-- open ct -->
	#if($listPenggunaInternalHQ.size()>0)
	<div id="div_LaporanPenggunaforPrint_HQ" style="display:none" >
	
	<table width="100%"  
	style="border-collapse: collapse;"  cellspacing="1" cellpadding="2"  width="100%" > 	
	<tr>
	<td valign="top"  width="1%"></td><td valign="top"  width="28%"></td><td valign="top"  width="1%"></td><td valign="top"  width="70%"><!-- $viewPengguna --></td>
	</tr>
	<tr>
	<td valign="top" colspan="4"  style="border-bottom: 1px solid #000;font-size: 120%;" >
	<b>LAPORAN PENGGUNA (IBU PEJABAT)</b>
	</td>
	</tr>
	
	#if($CT_ID_SEKSYEN != "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 100%;" >BAHAGIAN</td>
	<td valign="top"  style="font-size: 100%;" >
	:
	</td>
	<td valign="top" style="font-size: 100%;">
	<span id="display_CT_ID_SEKSYEN_HQ"></span>
	<script>
	$jquery(document).ready(function () {
	returnDropDownSelectedText('CT_ID_SEKSYEN_HQ','display_CT_ID_SEKSYEN_HQ');
	});
	</script>
	</td>
	</tr>
	#end
	
	#if($CT_ID_NEGERI != "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 100%;" >NEGERI</td>
	<td valign="top"  style="font-size: 100%;" >
	:
	</td>
	<td valign="top" style="font-size: 100%;">
	<span id="display_CT_ID_NEGERI_HQ"></span>
	<script>
	$jquery(document).ready(function () {
	returnDropDownSelectedText('CT_ID_NEGERI_HQ','display_CT_ID_NEGERI_HQ');
	});
	</script>
	</td>
	</tr>
	#end
	#if($CT_ID_PEJABATJKPTG != "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 100%;" >UNIT</td>
	<td valign="top"  style="font-size: 100%;" >
	:
	</td>
	<td valign="top" style="font-size: 100%;">
	<span id="display_CT_ID_PEJABATJKPTG_HQ"></span>
	<script>
	$jquery(document).ready(function () {
	returnDropDownSelectedText('CT_ID_PEJABATJKPTG_HQ','display_CT_ID_PEJABATJKPTG_HQ');
	});
	</script>
	</td>
	</tr>
	#end
	#if($CT_ROLE_MAIN != "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 100%;" >PERANAN PENGGUNA MyeTaPP</td>
	<td valign="top"  style="font-size: 100%;" >
	:
	</td>
	<td valign="top" style="font-size: 100%;">
	<span id="display_CT_ROLE_MAIN_HQ"></span>
	<script>
	$jquery(document).ready(function () {
	returnDropDownSelectedText('CT_ROLE_MAIN_HQ','display_CT_ROLE_MAIN_HQ');
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
	<span id="display_CT_FLAG_AKTIF_HQ"></span>
	<script>
	$jquery(document).ready(function () {
	returnDropDownSelectedText('CT_FLAG_AKTIF_HQ','display_CT_FLAG_AKTIF_HQ');
	});
	</script>
	</td>
	</tr>
	#end
	#if($CT_ID_JAWATAN != "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 100%;" >JAWATAN</td>
	<td valign="top"  style="font-size: 100%;" >
	:
	</td>
	<td valign="top" style="font-size: 100%;">
	<span id="display_CT_ID_JAWATAN_HQ"></span>
	<script>
	$jquery(document).ready(function () {
	returnDropDownSelectedText('CT_ID_JAWATAN_HQ','display_CT_ID_JAWATAN_HQ');
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
	<span id="display_CT_STATUS_LOG_HQ"></span>
	<script>
	$jquery(document).ready(function () {
	returnDropDownSelectedText('CT_STATUS_LOG_HQ','display_CT_STATUS_LOG_HQ');
	});
	</script>
	</td>
	</tr>
	#end
	</table>
	
	<table style="border-collapse:collapse;"  cellspacing="1" cellpadding="2"  width="100%" > 	
		
	#if($PrintlistPenggunaInternalHQ.size()>0)
	<script>
	var butoncetakCT =  document.getElementById('cmdCetakPenggunaCT_HQ');
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
		   #if($CT_ID_SEKSYEN == "")
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>BAHAGIAN</b></th>
		   #end
		   #if($CT_ID_NEGERI == "")
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>NEGERI</b></th>
		   #end
		   #if($CT_ID_PEJABATJKPTG == "")
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>UNIT</b></th>
		   #end
		   #if($CT_ROLE_MAIN == "")
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>PERANAN UTAMA</b></th>
		   #end
		   #if($CT_FLAG_AKTIF == "")
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>KEAKTIFAN</b></th>
		   #end
		   #if($CT_ID_JAWATAN == "")
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>JAWATAN</b></th>
		   #end
		   #if($CT_LOGMASUK_MULA == "" && $CT_LOGMASUK_AKHIR == "")
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>LOG MASUK TERAKHIR</b></th>
		   #end
		   #if($CT_STATUS_LOG == "")
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>STATUS LOG MASUK</b></th>
		   #end
	</tr>
	</thead>
	
		#foreach($Pengguna_HQ in $PrintlistPenggunaInternalHQ)
		<tr class="page-break" >
		   <td width="2%" style="border: 1px solid black;font-size:75%;"   align="center" valign="top" >$Pengguna_HQ.BIL </td>
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">
		   $Pengguna_HQ.FULLNAME 
		   <br>
		   [$Pengguna_HQ.USER_LOGIN]
		   </td>
		   #if($CT_ID_SEKSYEN == "")
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">$Pengguna_HQ.BAHAGIAN</td>
		   #end
		   #if($CT_ID_NEGERI == "")
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">$Pengguna_HQ.NEGERI_PEJABAT</td>
		   #end
		   #if($CT_ID_PEJABATJKPTG == "")
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">$Pengguna_HQ.NAMA_PEJABAT</td>
		   #end
		   #if($CT_ROLE_MAIN == "")
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">$Pengguna_HQ.ROLE_UTAMA</td>
		   #end
		   #if($CT_FLAG_AKTIF == "")
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">$Pengguna_HQ.KEAKTIFAN</td>
		   #end
		   #if($CT_ID_JAWATAN == "")
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">$Pengguna_HQ.JAWATAN</td>
		   #end
		   #if($CT_LOGMASUK_MULA == "" && $CT_LOGMASUK_AKHIR == "")
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">$Pengguna_HQ.WAKTU_AKHIR_LOGIN</td>
		   #end
		   #if($CT_STATUS_LOG == "")
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">$Pengguna_HQ.DISPLAY_NOTE</td>
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
	printHideDiv('div_LaporanPenggunaforPrint_HQ','HQ','');
	</script>
	
	