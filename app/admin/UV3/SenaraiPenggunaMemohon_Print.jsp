

<!-- open ct -->
#if($CT_OPENCLOSE_CARIAN=="open")
<script>
	document.getElementById('div_CT_CARIAN_MOHON').style.display = "";
	document.getElementById('span_LINK_CT_CARIAN_MOHON').style.display = "none";
	//document.getElementById('div_CT_CARIAN_$internalType').className = "classFade";
</script>
#end
#if($CT_OPENCLOSE_CARIAN=="close")
<script>
	document.getElementById('div_CT_CARIAN_MOHON').style.display = "none";
	document.getElementById('span_LINK_CT_CARIAN_MOHON').style.display = "";
	//document.getElementById('div_CT_CARIAN_$internalType').className = "";
</script>
#end
<!-- close ct -->


	<!-- open ct -->
	#if($listPenggunaMOHON.size()>0)
	<div id="div_LaporanPenggunaforPrint_MOHON" style="display:none" >
	
	<table width="100%"  
	style="border-collapse: collapse;"  cellspacing="1" cellpadding="2"  width="100%" > 	
	<tr>
	<td valign="top"  width="1%"></td><td valign="top"  width="28%"></td><td valign="top"  width="1%"></td><td valign="top"  width="70%"><!-- $viewPengguna --></td>
	</tr>
	<tr>
	<td valign="top" colspan="4"  style="border-bottom: 1px solid #000;font-size: 120%;" >
	<b>LAPORAN PERMOHONAN ID PENGGUNA MyeTaPP</b>
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
	<span id="display_CT_ID_SEKSYEN_MOHON"></span>
	<script>
	$jquery(document).ready(function () {
	returnDropDownSelectedText('CT_ID_SEKSYEN_MOHON','display_CT_ID_SEKSYEN_MOHON');
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
	<span id="display_CT_ID_NEGERI_MOHON"></span>
	<script>
	$jquery(document).ready(function () {
	returnDropDownSelectedText('CT_ID_NEGERI_MOHON','display_CT_ID_NEGERI_MOHON');
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
	<span id="display_CT_ID_PEJABATJKPTG_MOHON"></span>
	<script>
	$jquery(document).ready(function () {
	returnDropDownSelectedText('CT_ID_PEJABATJKPTG_MOHON','display_CT_ID_PEJABATJKPTG_MOHON');
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
	<span id="display_CT_ID_JAWATAN_MOHON"></span>
	<script>
	$jquery(document).ready(function () {
	returnDropDownSelectedText('CT_ID_JAWATAN_MOHON','display_CT_ID_JAWATAN_MOHON');
	});
	</script>
	</td>
	</tr>
	#end
	#if($CT_TARIKHPENDAFTARAN_MULA != "" || $CT_TARIKHPENDAFTARAN_AKHIR != "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 100%;" >TARIKH PENDAFTARAN</td>
	<td valign="top"  style="font-size: 100%;" >
	:
	</td>
	<td valign="top" style="font-size: 100%;">
	#if($CT_TARIKHPENDAFTARAN_MULA !="")
	Dari $CT_TARIKHPENDAFTARAN_MULA
	#end
	
	#if($CT_TARIKHPENDAFTARAN_MULA != "" && $CT_TARIKHPENDAFTARAN_AKHIR != "")
	&nbsp;&nbsp;
	#end
	
	#if($CT_TARIKHPENDAFTARAN_AKHIR !="")
	Sehingga $CT_TARIKHPENDAFTARAN_AKHIR
	#end
	</td>
	</tr>
	#end
	
	
	
	#if($CT_STATUSPENDAFTARAN != "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 100%;" >STATUS PENDAFTARAN</td>
	<td valign="top"  style="font-size: 100%;" >
	:
	</td>
	<td valign="top" style="font-size: 100%;">
	<span id="display_CT_STATUSPENDAFTARAN_MOHON"></span>
	<script>
	$jquery(document).ready(function () {
	returnDropDownSelectedText('CT_STATUSPENDAFTARAN_MOHON','display_CT_STATUSPENDAFTARAN_MOHON');
	});
	</script>
	</td>
	</tr>
	#end
	</table>
	
	<table style="border-collapse:collapse;"  cellspacing="1" cellpadding="2"  width="100%" > 	
		
	#if($PrintlistPenggunaMOHON.size()>0)
	<script>
	var butoncetakCT =  document.getElementById('cmdCetakPenggunaCT_MOHON');
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
		   #if($CT_ID_JAWATAN == "")
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>JAWATAN</b></th>
		   #end
		   #if($CT_TARIKHPENDAFTARAN_MULA == "" && $CT_TARIKHPENDAFTARAN_AKHIR == "")
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>TARIKH PENDAFTARAN</b></th>
		   #end
		   #if($CT_STATUSPENDAFTARAN == "")
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>STATUS PENDAFTARAN</b></th>
		   #end
	</tr>
	</thead>
	
		#foreach($Pengguna_MOHON in $PrintlistPenggunaMOHON)
		<tr class="page-break" >
		   <td width="2%" style="border: 1px solid black;font-size:75%;"   align="center" valign="top" >$Pengguna_MOHON.BIL </td>
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">
		   $Pengguna_MOHON.NAMA 
		   <br>
		   [$Pengguna_MOHON.NOKP]
		   
		   </td>
		   #if($CT_ID_SEKSYEN == "")
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">$Pengguna_MOHON.BAHAGIAN</td>
		   #end
		   #if($CT_ID_NEGERI == "")
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">$Pengguna_MOHON.NEGERI</td>
		   #end
		   #if($CT_ID_PEJABATJKPTG == "")
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">$Pengguna_MOHON.NAMA_UNIT</td>
		   #end
		   #if($CT_ID_JAWATAN == "")
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">$Pengguna_MOHON.JAWATAN</td>
		   #end
		   #if($CT_TARIKHPENDAFTARAN_MULA == "" && $CT_TARIKHPENDAFTARAN_AKHIR == "")
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">$Pengguna_MOHON.TARIKH_PENDAFTARAN</td>
		   #end
		   #if($CT_STATUSPENDAFTARAN == "")
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">$Pengguna_MOHON.STATUS</td>
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
	printHideDiv('div_LaporanPenggunaforPrint_MOHON','MOHON','');
	</script>
	
	