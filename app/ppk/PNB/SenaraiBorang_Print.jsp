<!-- open ct -->
	
  
	#if($listBorang.size()>0)
	<div id="div_LaporanBorangforPrint"   style="display:none" >
	
	<table width="100%"  
	style="border-collapse: collapse;"  cellspacing="1" cellpadding="2"  width="100%" > 	
	<tr>
	<td valign="top"  width="1%"></td><td valign="top"  width="28%"></td><td valign="top"  width="1%"></td><td valign="top"  width="70%"><!-- $viewPengguna --></td>
	</tr>
	<tr>
	<td valign="top" colspan="4"  style="border-bottom: 1px solid #000;font-size: 120%;" >
	<b>LAPORAN PNB</b>
	</td>
	</tr>
	
	
	
    #if($CARIAN_NO_PNB != "")
        <tr >
		<td valign="top"  >
		</td>
		<td valign="top" style="font-size: 100%;" >NO. PNB</td>
		<td valign="top"  style="font-size: 100%;" >
		:
		</td>
		<td valign="top" style="font-size: 100%;">
        $CARIAN_NO_PNB		
		</td>
		</tr>
    #end
    #if($CARIAN_NO_FAIL != "")
        <tr >
		<td valign="top"  >
		</td>
		<td valign="top" style="font-size: 100%;" >NO. FAIL PPK</td>
		<td valign="top"  style="font-size: 100%;" >
		:
		</td>
		<td valign="top" style="font-size: 100%;">
        $CARIAN_NO_FAIL		
		</td>
		</tr>
    #end
     #if($CARIAN_JENIS_BORANG != "")
        <tr >
		<td valign="top"  >
		</td>
		<td valign="top" style="font-size: 100%;" >JENIS BORANG</td>
		<td valign="top"  style="font-size: 100%;" >
		:
		</td>
		<td valign="top" style="font-size: 100%;">
        <span id="display_JENIS_BORANG"></span>
			<script>
			$jquery(document).ready(function () {
			returnDropDownSelectedText('CARIAN_JENIS_BORANG','display_JENIS_BORANG');
			});
			</script>	
		</td>
		</tr>
    #end
    #if($CARIAN_NAMA_BORANG != "")
        <tr >
		<td valign="top"  >
		</td>
		<td valign="top" style="font-size: 100%;" >NAMA BORANG</td>
		<td valign="top"  style="font-size: 100%;" >
		:
		</td>
		<td valign="top" style="font-size: 100%;">
        $CARIAN_NAMA_BORANG		
		</td>
		</tr>
    #end
    
    
    #if($CARIAN_TARIKH_MULA != "" || $CARIAN_TARIKH_AKHIR != "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 100%;" >TARIKH HANTAR KE PNB</td>
	<td valign="top"  style="font-size: 100%;" >
	:
	</td>
	<td valign="top" style="font-size: 100%;">
	#if($CARIAN_TARIKH_MULA !="")
	Dari $CARIAN_TARIKH_MULA
	#end
	
	#if($CARIAN_TARIKH_MULA != "" && $CARIAN_TARIKH_AKHIR != "")
	&nbsp;
	#end
	
	#if($CARIAN_TARIKH_AKHIR !="")
	Sehingga $CARIAN_TARIKH_AKHIR
	#end
	</td>
	</tr>
	#end
    
    
    #if($CARIAN_NEGERI != "")	
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
			returnDropDownSelectedText('CARIAN_NEGERI','display_NEGERI');
			});
			</script>
		</td>
		</tr>
	#end
    
    #if($CARIAN_UNIT != "")	
		<tr >
		<td valign="top"  >
		</td>
		<td valign="top" style="font-size: 100%;" >UNIT PPK</td>
		<td valign="top"  style="font-size: 100%;" >
		:
		</td>
		<td valign="top" style="font-size: 100%;">
		<span id="display_UNIT"></span>
			<script>
			$jquery(document).ready(function () {
			returnDropDownSelectedText('CARIAN_UNIT','display_UNIT');
			});
			</script>
		</td>
		</tr>
	#end
    
    
     #if($CARIAN_NAMA_PENERIMA != "")
        <tr >
		<td valign="top"  >
		</td>
		<td valign="top" style="font-size: 100%;" >NAMA PENERIMA</td>
		<td valign="top"  style="font-size: 100%;" >
		:
		</td>
		<td valign="top" style="font-size: 100%;">
        $CARIAN_NAMA_PENERIMA		
		</td>
		</tr>
    #end
    
    #if($CARIAN_ALAMAT_PENERIMA != "")
        <tr >
		<td valign="top"  >
		</td>
		<td valign="top" style="font-size: 100%;" >ALAMAT PENERIMA</td>
		<td valign="top"  style="font-size: 100%;" >
		:
		</td>
		<td valign="top" style="font-size: 100%;">
        $CARIAN_ALAMAT_PENERIMA		
		</td>
		</tr>
    #end
    
    
    #if($CARIAN_STATUS != "")	
		<tr >
		<td valign="top"  >
		</td>
		<td valign="top" style="font-size: 100%;" >STATUS</td>
		<td valign="top"  style="font-size: 100%;" >
		:
		</td>
		<td valign="top" style="font-size: 100%;">
		<span id="display_STATUS"></span>
			<script>
			$jquery(document).ready(function () {
			returnDropDownSelectedText('CARIAN_STATUS','display_STATUS');
			});
			</script>
		</td>
		</tr>
	#end
    
    
    
    <tr >
		<td valign="top"  >
		</td>
		<td valign="top" style="font-size: 100%;" >JUMLAH MUKA SURAT</td>
		<td valign="top"  style="font-size: 100%;" >
		:
		</td>
		<td valign="top" style="font-size: 100%;">
		<span id="display_MUKA_SURAT"></span>		
		</td>
		</tr>
    
    
    
    
    
    
	
	</table>
    
    
    #set($total_pages = 0)
	
	<table style="border-collapse:collapse;"  cellspacing="1" cellpadding="2"  width="100%" > 	
		
	#if($listBorang_forPrint.size()>0)
	<script>
	var butoncetak =  document.getElementById('cmdCetak');
    if (typeof(butoncetak) != 'undefined' && butoncetak != null)
    {
    	butoncetak.style.display = "";
    }
	</script>
	
	
	<thead >
	<tr>
		<th colspan="10"><br></th>
	</tr>
    
    
	<tr   >
		   <th width="3%" style="border: 1px solid black;font-size:75%;" align="center" valign="top"><b>BIL.</b></th>
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>NO. PNB & <br /> TARIKH HANTAR KE PNB</b></th>
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>NO. FAIL & UNIT PPK</b></th>
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>JENIS</b></th>
           <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>BORANG</b></th>
           <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>MUKA SURAT</b></th>
		   <th style="border: 1px solid black;font-size:75%;"  align="center" valign="top" width="5%"><b>JUMLAH PENERIMA</b></th>		   
		   <th style="border: 1px solid black;font-size:75%;"  align="center" valign="top" width="5%"><b>JUMLAH DIHANTAR</b></th>
		   <th style="border: 1px solid black;font-size:75%;"  align="center" valign="top" width="5%"><b>JUMLAH PEMULANGAN</b></th>
           <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>STATUS</b></th>
	</tr>
	</thead>
	
		#foreach($LOGPRINT in $listBorang_forPrint)
		
		<tr  class="page-break"  >
       
		   <td width="3%" style="border: 1px solid black;font-size:75%;"   align="center" valign="top" >$LOGPRINT.BIL </td>
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">
		    $LOGPRINT.NO_PNB<br />$LOGPRINT.TARIKH_HANTARPNB
		   </td>
		    <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">
		    $LOGPRINT.NO_FAIL 
            <br />
            $LOGPRINT.NAMAUNIT, 
            <br />
            $LOGPRINT.NEGERIUNIT 	
		   </td>
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">
		    $LOGPRINT.JENIS_BORANG 
		   </td>
           <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">
		    $LOGPRINT.NAMA_BORANG 
		   </td>
           <td style="border: 1px solid black;font-size:75%;"  align="center" valign="top" width="5%">
		    $LOGPRINT.COUNT_PAGES 
            
            #set($total_pages = $total_pages + $LOGPRINT.COUNT_PAGES)
		   </td>
           <td style="border: 1px solid black;font-size:75%;"  align="center" valign="top" width="5%">
		    $LOGPRINT.JUMLAH_PENERIMA 
		   </td>
           <td style="border: 1px solid black;font-size:75%;"  align="center" valign="top" width="5%">
		    $LOGPRINT.JUMLAH_DIHANTAR 
		   </td>
           <td style="border: 1px solid black;font-size:75%;"  align="center" valign="top" width="5%">
		    $LOGPRINT.JUMLAH_RETURN 
		   </td>
           <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">
		     #if($LOGPRINT.STATUS_BORANG=="1")
               HANTAR KE PNB
               #elseif($LOGPRINT.STATUS_BORANG=="2")
               DITERIMA PNB
               #elseif($LOGPRINT.STATUS_BORANG=="3")
               DALAM PROSES
               #elseif($LOGPRINT.STATUS_BORANG=="4")
               SELESAI
               #end	   
		   </td>
           
           
           
           
           
           
         
			   
			   
		</tr>
		
		#end
		#else		
		<tr>
		<td colspan="10">
		Tiada Rekod
		</td>
		</tr>
		#end	
	</table>
	
	
	
    
    #if($total_pages>0)
    <script>
	document.getElementById("display_MUKA_SURAT").innerHTML = '$total_pages';		
	</script>
    #end
    
	</div>
	#end
	<!-- close ct -->
	

	
	<script type="text/javascript">
	printHideDiv('div_LaporanBorangforPrint')
	</script>