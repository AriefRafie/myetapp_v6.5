<!-- open ct -->
#if($CT_OPENCLOSE_CARIAN=="open")
<script>
	document.getElementById('div_CT_CARIAN_Online').style.display = "";
	document.getElementById('span_LINK_CT_CARIAN_Online').style.display = "none";
	//document.getElementById('div_CT_CARIAN_$OnlineernalType').className = "classFade";
</script>
#end
#if($CT_OPENCLOSE_CARIAN=="close")
<script>
	document.getElementById('div_CT_CARIAN_Online').style.display = "none";
	document.getElementById('span_LINK_CT_CARIAN_Online').style.display = "";
	//document.getElementById('div_CT_CARIAN_$OnlineernalType').className = "";
</script>
#end
<!-- close ct -->


<!-- open ct -->
	#if($listPenggunaOnline.size()>0)
	
	<div id="div_LaporanPenggunaforPrint_Online" style="display:none" >
	
	<table width="100%"  
	style="border-collapse: collapse;"  cellspacing="1" cellpadding="2"  width="100%" > 	
	<tr>
	<td valign="top"  width="1%"></td><td valign="top"  width="28%"></td><td valign="top"  width="1%"></td><td valign="top"  width="70%"><!-- $viewPengguna --></td>
	</tr>
	<tr>
	<td valign="top" colspan="4"  style="border-bottom: 1px solid #000;font-size: 120%;" >
	<b>LAPORAN PENGGUNA (ONLINE)</b>
	</td>
	</tr>
	
	
	
	#if($CT_ID_KATEGORI_PENGGUNA != "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 100%;" >KATEGORI</td>
	<td valign="top"  style="font-size: 100%;" >
	:
	</td>
	<td valign="top" style="font-size: 100%;">
	<span id="display_CT_ID_KATEGORI_PENGGUNA_Online"></span>
	<script>
	$jquery(document).ready(function () {
	returnDropDownSelectedText('CT_ID_KATEGORI_PENGGUNA_Online','display_CT_ID_KATEGORI_PENGGUNA_Online');
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
	<span id="display_CT_ID_NEGERI_Online"></span>
	<script>
	$jquery(document).ready(function () {
	returnDropDownSelectedText('CT_ID_NEGERI_Online','display_CT_ID_NEGERI_Online');
	});
	</script>
	</td>
	</tr>
	#end
	
	#if($CT_HAD_UMUR != "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 100%;" >HAD UMUR</td>
	<td valign="top"  style="font-size: 100%;" >
	:
	</td>
	<td valign="top" style="font-size: 100%;">
	<span id="display_CT_HAD_UMUR_Online"></span>
	<script>
	$jquery(document).ready(function () {
	returnDropDownSelectedText('CT_HAD_UMUR_Online','display_CT_HAD_UMUR_Online');
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
	<span id="display_CT_ROLE_MAIN_Online"></span>
	<script>
	$jquery(document).ready(function () {
	returnDropDownSelectedText('CT_ROLE_MAIN_Online','display_CT_ROLE_MAIN_Online');
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
	<span id="display_CT_FLAG_AKTIF_Online"></span>
	<script>
	$jquery(document).ready(function () {
	returnDropDownSelectedText('CT_FLAG_AKTIF_Online','display_CT_FLAG_AKTIF_Online');
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
	<span id="display_CT_STATUS_LOG_Online"></span>
	<script>
	$jquery(document).ready(function () {
	returnDropDownSelectedText('CT_STATUS_LOG_Online','display_CT_STATUS_LOG_Online');
	});
	</script>
	</td>
	</tr>
	#end
	</table>
	
	<table style="border-collapse:collapse;"  cellspacing="1" cellpadding="2"  width="100%" > 	
		
	#if($PrintlistPenggunaOnline.size()>0)
	<script>
	var butoncetakCT =  document.getElementById('cmdCetakPenggunaCT_Online');
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
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>NAMA / Username</b></th>
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>MyID / MyCOID</b></th>
		   <!-- 
		   #if($CT_JAWATAN == "")
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>JAWATAN</b></th>
		   #end
		   -->
		   #if($CT_ID_KATEGORI_PENGGUNA == "")
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>KATEGORI</b></th>
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
	
		#foreach($Pengguna_Online in $PrintlistPenggunaOnline)
		<tr class="page-break" >
		   <td width="2%" style="border: 1px solid black;font-size:75%;"   align="center" valign="top" >$Pengguna_Online.BIL </td>
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">
		   $Pengguna_Online.NAMA_PENUH 
		   <br>
		   [$Pengguna_Online.USER_LOGIN]
		   </td>
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">
		   		#if($Pengguna_Online.KATEGORI=="SYARIKAT")
					$Pengguna_Online.NO_PENGENALAN
				#elseif($Pengguna_Online.KATEGORI=="INDIVIDU")
					#if($Pengguna_Online.NO_PENGENALAN1!="")
					$Pengguna_Online.NO_PENGENALAN1 - $Pengguna_Online.NO_PENGENALAN2 - $Pengguna_Online.NO_PENGENALAN3
					#end
				#else
					#if($Pengguna_Online.NO_PENGENALAN1!="")
					$Pengguna_Online.NO_PENGENALAN1 - $Pengguna_Online.NO_PENGENALAN2 - $Pengguna_Online.NO_PENGENALAN3
					#end
				#end
		   </td>
		   <!-- 
		   #if($CT_JAWATAN == "")
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">$Pengguna_Online.JAWATAN</td>
		   #end	
		   -->	   
		   #if($CT_ID_KATEGORI_PENGGUNA == "")
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">$Pengguna_Online.KATEGORI</td>
		   #end		   
		   #if($CT_ROLE_MAIN == "")
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">$Pengguna_Online.NAMA_ROLE</td>
		   #end
		   #if($CT_FLAG_AKTIF == "")
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">$Pengguna_Online.KEAKTIFAN</td>
		   #end		  
		   #if($CT_LOGMASUK_MULA == "" && $CT_LOGMASUK_AKHIR == "")
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">$Pengguna_Online.WAKTU_AKHIR_LOGIN</td>
		   #end
		   #if($CT_STATUS_LOG == "")
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">$Pengguna_Online.DISPLAY_NOTE</td>
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
	printHideDiv('div_LaporanPenggunaforPrint_Online','Online','');
	</script>
