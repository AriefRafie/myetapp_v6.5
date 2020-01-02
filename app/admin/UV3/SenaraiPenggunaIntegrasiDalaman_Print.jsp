<!-- open ct -->
#if($CT_OPENCLOSE_CARIAN=="open")
<script>
	document.getElementById('div_CT_CARIAN_INT').style.display = "";
	document.getElementById('span_LINK_CT_CARIAN_INT').style.display = "none";
	//document.getElementById('div_CT_CARIAN_$internalType').className = "classFade";
</script>
#end
#if($CT_OPENCLOSE_CARIAN=="close")
<script>
	document.getElementById('div_CT_CARIAN_INT').style.display = "none";
	document.getElementById('span_LINK_CT_CARIAN_INT').style.display = "";
	//document.getElementById('div_CT_CARIAN_$internalType').className = "";
</script>
#end
<!-- close ct -->


	<!-- open ct -->
	#if($listPenggunaINT.size()>0)
	<div id="div_LaporanPenggunaforPrint_INT" style="display:none" >
	
	<table width="100%"  
	style="border-collapse: collapse;"  cellspacing="1" cellpadding="2"  width="100%" > 	
	<tr>
	<td valign="top"  width="1%"></td><td valign="top"  width="28%"></td><td valign="top"  width="1%"></td><td valign="top"  width="70%"><!-- $viewPengguna --></td>
	</tr>
	<tr>
	<td valign="top" colspan="4"  style="border-bottom: 1px solid #000;font-size: 120%;" >
	<b>LAPORAN PENGGUNA (INTEGRASI)</b>
	</td>
	</tr>
	
	#if($CT_JAWATAN!= "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 100%;" >JAWATAN</td>
	<td valign="top"  style="font-size: 100%;" >
	:
	</td>
	<td valign="top" style="font-size: 100%;">
	$CT_JAWATAN	
	</td>
	</tr>
	#end
	
	#if($CT_ID_JENISPEJABAT != "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 100%;" >JENIS PEJABAT</td>
	<td valign="top"  style="font-size: 100%;" >
	:
	</td>
	<td valign="top" style="font-size: 100%;">
	<span id="display_CT_ID_JENISPEJABAT_INT"></span>
	<script>
	$jquery(document).ready(function () {
	returnDropDownSelectedText('CT_ID_JENISPEJABAT_INT','display_CT_ID_JENISPEJABAT_INT');
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
	<span id="display_CT_ID_NEGERI_INT"></span>
	<script>
	$jquery(document).ready(function () {
	returnDropDownSelectedText('CT_ID_NEGERI_INT','display_CT_ID_NEGERI_INT');
	});
	</script>
	</td>
	</tr>
	#end
	#if($CT_ID_DAERAH != "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 100%;" >DAERAH </td>
	<td valign="top"  style="font-size: 100%;" >
	:
	</td>
	<td valign="top" style="font-size: 100%;">
	<span id="display_CT_ID_DAERAH_INT"></span>
	<script>
	$jquery(document).ready(function () {
	returnDropDownSelectedText('CT_ID_DAERAH_INT','display_CT_ID_DAERAH_INT');
	});
	</script>
	</td>
	</tr>
	#end
	
	#if($CT_ID_PEJABAT != "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 100%;" >PEJABAT</td>
	<td valign="top"  style="font-size: 100%;" >
	:
	</td>
	<td valign="top" style="font-size: 100%;">
	<span id="display_CT_ID_PEJABAT_INT"></span>
	<script>
	$jquery(document).ready(function () {
	returnDropDownSelectedText('CT_ID_PEJABAT_INT','display_CT_ID_PEJABAT_INT');
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
	<span id="display_CT_ROLE_MAIN_INT"></span>
	<script>
	$jquery(document).ready(function () {
	returnDropDownSelectedText('CT_ROLE_MAIN_INT','display_CT_ROLE_MAIN_INT');
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
	<span id="display_CT_FLAG_AKTIF_INT"></span>
	<script>
	$jquery(document).ready(function () {
	returnDropDownSelectedText('CT_FLAG_AKTIF_INT','display_CT_FLAG_AKTIF_INT');
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
	<span id="display_CT_STATUS_LOG_INT"></span>
	<script>
	$jquery(document).ready(function () {
	returnDropDownSelectedText('CT_STATUS_LOG_INT','display_CT_STATUS_LOG_INT');
	});
	</script>
	</td>
	</tr>
	#end
	</table>
	
	<table style="border-collapse:collapse;"  cellspacing="1" cellpadding="2"  width="100%" > 	
		
	#if($PrintlistPenggunaINT.size()>0)
	<script>
	var butoncetakCT =  document.getElementById('cmdCetakPenggunaCT_INT');
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
		   <!-- 
		   #if($CT_JAWATAN == "")
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>JAWATAN</b></th>
		   #end
		   -->
		   #if($CT_ID_JENISPEJABAT == "")
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>JENIS PEJABAT</b></th>
		   #end
		   
		   #if($CT_ID_JENISPEJABAT=="16111")
			   #if($CT_ID_NEGERI == "")
			   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>NEGERI</b></th>
			   #end		   
			   #if($CT_ID_DAERAH == "")
			   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>DAERAH</b></th>
			   #end
			   #if($CT_ID_PEJABAT == "")
			   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>PEJABAT</b></th>
			   #end
		   #end
		   #if($CT_ROLE_MAIN == "")
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>PERANAN UTAMA</b></th>
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
	
		#foreach($Pengguna_INT in $PrintlistPenggunaINT)
		<tr class="page-break" >
		   <td width="2%" style="border: 1px solid black;font-size:75%;"   align="center" valign="top" >$Pengguna_INT.BIL </td>
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">
		   $Pengguna_INT.USER_NAME 
		   <br>
		   [$Pengguna_INT.USER_LOGIN]
		  
		   </td>
		   <!-- 
		   #if($CT_JAWATAN == "")
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">$Pengguna_INT.JAWATAN</td>
		   #end	
		   -->	   
		   #if($CT_ID_JENISPEJABAT == "")
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">$Pengguna_INT.JENIS_PEJABAT</td>
		   #end
		   #if($CT_ID_JENISPEJABAT=="16111")
			   #if($CT_ID_NEGERI == "")
			   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">$Pengguna_INT.NEGERI_UI</td>
			   #end
			   #if($CT_ID_DAERAH == "")
			   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">$Pengguna_INT.DAERAH_UI</td>
			   #end
			   #if($CT_ID_PEJABAT == "")
			   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">$Pengguna_INT.NAMA_PEJABAT</td>
			   #end
		   #end
		   #if($CT_ROLE_MAIN == "")
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">$Pengguna_INT.NAMA_ROLE</td>
		   #end
		   #if($CT_FLAG_AKTIF == "")
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">$Pengguna_INT.KEAKTIFAN</td>
		   #end		  
		   #if($CT_LOGMASUK_MULA == "" && $CT_LOGMASUK_AKHIR == "")
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">$Pengguna_INT.WAKTU_AKHIR_LOGIN</td>
		   #end
		   #if($CT_STATUS_LOG == "")
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">$Pengguna_INT.DISPLAY_NOTE</td>
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
	printHideDiv('div_LaporanPenggunaforPrint_INT','INT','');
	</script>
	