<!-- open ct -->
	
    
    
	#if($list.size()>0)
	<div id="div_LaporanforPrint" style="display:none" >
	
	<table width="100%"  
	style="border-collapse: collapse;"  cellspacing="1" cellpadding="1"   > 	
	<tr>
	<td valign="top"  width="1%"></td><td valign="top"  width="28%"></td><td valign="top"  width="1%"></td><td valign="top"  width="70%"><!-- $viewPengguna --></td>
	</tr>
	<tr>
	<td valign="top" colspan="4"  style="border-bottom: 1px solid #000;font-size: 120%;" >
	<b>LAPORAN PEWARTAAN TANAH RIZAB MELAYU</b>
	</td>
	</tr>
	
    #if($CR_FLAG_JENISWARTA != "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 90%;" >JENIS WARTA</td>
	<td valign="top"  style="font-size: 90%;" >
	:
	</td>
	<td valign="top" style="font-size: 90%;">
	<span id="display_FLAG_JENISWARTA"></span>
	<script>
    $jquery(document).ready(function () {
    returnDropDownSelectedText('CR_FLAG_JENISWARTA','display_FLAG_JENISWARTA');
    });
    </script>    
	</td>
	</tr>
	#end
    
    
    #if($CR_NO_WARTA != "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 90%;" >NO. WARTA</td>
	<td valign="top"  style="font-size: 90%;" >
	:
	</td>
	<td valign="top" style="font-size: 90%;">
    $CR_NO_WARTA 
	</td>
	</tr>
	#end
    
    #if($CR_TARIKH_MULA != "" || $CR_TARIKH_AKHIR != "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 90%;" >TARIKH WARTA</td>
	<td valign="top"  style="font-size: 90%;" >
	:
	</td>
	<td valign="top" style="font-size: 90%;">
	#if($CR_TARIKH_MULA !="")
	DARI $CR_TARIKH_MULA
	#end
	
	#if($CR_TARIKH_AKHIR != "" && $CR_TARIKH_AKHIR != "")
	&nbsp;
	#end
	
	#if($CR_TARIKH_AKHIR !="")
	SEHINGGA $CR_TARIKH_AKHIR
	#end
	</td>
	</tr>
	#end
    
    #if($CR_ID_NEGERI != "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 90%;" >NEGERI</td>
	<td valign="top"  style="font-size: 90%;" >
	:
	</td>
	<td valign="top" style="font-size: 90%;">	
    <span id="display_ID_NEGERI"></span>
	<script>
    $jquery(document).ready(function () {
    returnDropDownSelectedText('CR_ID_NEGERI','display_ID_NEGERI');
    });
    </script>    
	</td>
	</tr>
	#end
    
    
    #if($CR_ID_DAERAH != "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 90%;" >DAERAH</td>
	<td valign="top"  style="font-size: 90%;" >
	:
	</td>
	<td valign="top" style="font-size: 90%;">	
    <span id="display_ID_DAERAH"></span>
	<script>
    $jquery(document).ready(function () {
    returnDropDownSelectedText('CR_ID_DAERAH','display_ID_DAERAH');
    });
    </script>    
	</td>
	</tr>
	#end
    
    
    #if($CR_ID_MUKIM != "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 90%;" >MUKIM</td>
	<td valign="top"  style="font-size: 90%;" >
	:
	</td>
	<td valign="top" style="font-size: 90%;">
	
    <span id="display_ID_MUKIM"></span>
	<script>
    $jquery(document).ready(function () {
    returnDropDownSelectedText('CR_ID_MUKIM','display_ID_MUKIM');
    });
    </script>    
	</td>
	</tr>
	#end
    
    
    
    #if($CR_KAWASAN != "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 90%;" >KAWASAN</td>
	<td valign="top"  style="font-size: 90%;" >
	:
	</td>
	<td valign="top" style="font-size: 90%;">
    $CR_KAWASAN
	</td>
	</tr>
	#end
    
    #if($CR_NO_PELAN != "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 90%;" >NO. PELAN</td>
	<td valign="top"  style="font-size: 90%;" >
	:
	</td>
	<td valign="top" style="font-size: 90%;">
    $CR_NO_PELAN
	</td>
	</tr>
	#end
    
    
    <tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 90%;" >LUAS ASAL WARTA</td>
	<td valign="top"  style="font-size: 90%;" >
	:
	</td>
	<td valign="top" style="font-size: 90%;">
	<span id="display_LUASSASALWARTA"></span>	
	</td>
	</tr>
    
    
    #if($CR_FLAG_JENISWARTA == "W" || $CR_FLAG_JENISWARTA == "")
    <tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 90%;" >LUAS WARTA</td>
	<td valign="top"  style="font-size: 90%;" >
	:
	</td>
	<td valign="top" style="font-size: 90%;">
	<span id="display_LUASSWARTA"></span>	
	</td>
	</tr>
    #end
    
    
    #if($CR_FLAG_JENISWARTA == "B" || $CR_FLAG_JENISWARTA == "")
    <tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 90%;" >LUAS PEMBATALAN</td>
	<td valign="top"  style="font-size: 90%;" >
	:
	</td>
	<td valign="top" style="font-size: 90%;">
	<span id="display_BATAL"></span>	
	</td>
	</tr>
    #end
    
    
    #if($CR_FLAG_JENISWARTA == "G" || $CR_FLAG_JENISWARTA == "")
    <tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 90%;" >LUAS PENGGANTIAN</td>
	<td valign="top"  style="font-size: 90%;" >
	:
	</td>
	<td valign="top" style="font-size: 90%;">
	<span id="display_GANTI"></span>	
	</td>
	</tr>
    #end
    
    
    
    
    
    <tr  id="tr_display_KOSONG">
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 90%;" >LUAS TIDAK DIGANTI</td>
	<td valign="top"  style="font-size: 90%;" >
	:
	</td>
	<td valign="top" style="font-size: 90%;">
	<span id="span_display_KOSONG"></span>	
	</td>
	</tr>
    
    
    
    
    
    
    #if($CR_FLAG_STATUSWARTA != "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 90%;" >STATUS WARTA</td>
	<td valign="top"  style="font-size: 90%;" >
	:
	</td>
	<td valign="top" style="font-size: 90%;">
	<span id="display_STATUSWARTA"></span>
	<script>
    $jquery(document).ready(function () {
    returnDropDownSelectedText('CR_FLAG_STATUSWARTA','display_STATUSWARTA');
    });
    </script>    
	</td>
	</tr>
	#end
    
    
    
	
	
	</table>
	
	<table style="border-collapse:collapse;"  cellspacing="1" cellpadding="2"  width="100%" > 	
		
	#if($listLog_forPrint.size()>0)
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
        
	<tr>
		   <th style="border: 1px solid black;font-size:75%;" align="center" valign="top"><b>BIL.</b></th>
           #if($CR_FLAG_JENISWARTA == "")
           <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>JENIS WARTA</b></th>
           #end
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>NO. WARTA</b></th>		   
		   <th style="border: 1px solid black;font-size:75%;"  align="center" valign="top"><b>TARIKH WARTA</b></th>
           #if($CR_ID_NEGERI == "")
           <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>NEGERI</b></th>
           #end
           #if($CR_ID_DAERAH == "")
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>DAERAH</b></th>
           #end
           #if($CR_ID_MUKIM == "")
           <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>MUKIM</b></th>
           #end
           
           #if($CR_FLAG_JENISWARTA == "W")
           <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>LUAS ASAL (HEKTAR)</b></th> 
           <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>LUAS (HEKTAR)</b></th> 
           #end
           
           #if($CR_FLAG_JENISWARTA == "B")
           <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>LUAS PEMBATALAN (HEKTAR)</b></th> 
           #end
           #if($CR_FLAG_JENISWARTA == "G")
           <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>LUAS PENGGANTIAN (HEKTAR)</b></th> 
           #end
           
           
           <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>KAWASAN</b></th> 
           <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>NO. PELAN</b></th>          	   
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>STATUS WARTA</b></th>
           <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>CATATAN</b></th>
		   
	</tr>
	</thead>
	
    
    	#set($totalLuas = 0.0)
        #set($totalLuasBatal = 0.0)
        #set($totalLuasGanti = 0.0)
        #set($totalLuasKosong = 0.0)
    
		#foreach($LOGPRINT in $listLog_forPrint)
        
               
            #if($LOGPRINT.FLAG_JENISWARTA == "W")
                #set($totalLuas = $totalLuas + $LOGPRINT.LUAS)
                #set($totalLuasKosong = $totalLuasKosong + $LOGPRINT.KOSONG)
            #end
            
            #if($CR_FLAG_JENISWARTA == "W" || $CR_FLAG_STATUSWARTA == "G") 
            	#set($totalLuasBatal = $totalLuasBatal + $LOGPRINT.LUAS_BATAL)
            	#set($totalLuasGanti = $totalLuasGanti + $LOGPRINT.LUAS_GANTI)
            #else       
        	            
                #if($LOGPRINT.FLAG_JENISWARTA == "B")
                    #set($totalLuasBatal = $totalLuasBatal + $LOGPRINT.LUAS)
                #end
                
                #if($LOGPRINT.FLAG_JENISWARTA == "G")
                    #set($totalLuasGanti = $totalLuasGanti + $LOGPRINT.LUAS)
                #end
       
        	#end
        
        <tr class="page-break"  >
		   <td width="2%" style="border: 1px solid black;font-size:65%;"   align="center" valign="top" >$LOGPRINT.BIL </td>
           #if($CR_FLAG_JENISWARTA == "")
           <td style="border: 1px solid black;font-size:65%;"  align="left" valign="top">
		    $LOGPRINT.JENISWARTA           	
		   </td>
           #end
		   <td style="border: 1px solid black;font-size:65%;"  align="left" valign="top">
		    $LOGPRINT.NO_WARTA           	
		   </td>
		    <td style="border: 1px solid black;font-size:65%;"  align="center" valign="top">
            $LOGPRINT.TARIKH_WARTA
		   </td>
           #if($CR_ID_NEGERI == "")
		   <td style="border: 1px solid black;font-size:65%;"  align="left" valign="top">
		   $LOGPRINT.NEGERI
		   </td>
           #end
           
           #if($CR_ID_DAERAH == "")
           <td style="border: 1px solid black;font-size:65%;"  align="left" valign="top">
		   $LOGPRINT.DAERAH
		   </td>
           #end
           
           #if($CR_ID_MUKIM == "")
           <td style="border: 1px solid black;font-size:65%;"  align="left" valign="top">
		   $LOGPRINT.MUKIM
		   </td>
           #end
           
           #if($CR_FLAG_JENISWARTA == "W")
           <td style="border: 1px solid black;font-size:65%;"  align="left" valign="top">
		   $LOGPRINT.LUAS_ASAL_DISPLAY
		   </td>           
           #end
           
           
          
           <td style="border: 1px solid black;font-size:65%;"  align="left" valign="top">
		   $LOGPRINT.LUAS_DISPLAY
		   </td>
           
           
           
           <td style="border: 1px solid black;font-size:65%;"  align="left" valign="top">
		   $LOGPRINT.KAWASAN
		   </td>
           <td style="border: 1px solid black;font-size:65%;"  align="left" valign="top">
		   $LOGPRINT.NO_PELAN
		   </td>
           <td style="border: 1px solid black;font-size:65%;"  align="left" valign="top">
		   $LOGPRINT.STATUSWARTA
		   </td>
           <td style="border: 1px solid black;font-size:65%;"  align="left" valign="top">
		   
           #if($LOGPRINT.NO_WARTA_ASAL != "")
               		#if($LOGPRINT.FLAG_JENISWARTA == "B")
               		$LOGPRINT.JENISWARTA : $LOGPRINT.NO_WARTA_ASAL<br />
                    #else
                    PENGGANTIAN : $LOGPRINT.NO_WARTA_ASAL<br />
                    #end
           #end
         
           #if($LOGPRINT.KOSONG>0)               
           <font color="red">$LOGPRINT.KOSONG_DISPLAY HEKTAR TIDAK DIGANTI</font><br />
           #end
           
           
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
	//alert(number_format(1234212121.56783, 4, '.', ','));
	
	
		var luas_asal = 0.0;
	    var luas = parseFloat('$totalLuas');
		var luas_batal = parseFloat('$totalLuasBatal');
		var luas_ganti = parseFloat('$totalLuasGanti');
		luas_asal = luas + luas_batal - luas_ganti;
		document.getElementById('display_LUASSASALWARTA').innerHTML = number_format(luas_asal, 4, '.', ',')+' HEKTAR';
	
	
	if('$CR_FLAG_JENISWARTA' == "W" || '$CR_FLAG_JENISWARTA' == "")
	{
		document.getElementById('display_LUASSWARTA').innerHTML = number_format('$totalLuas', 4, '.', ',')+' HEKTAR';
	}
	
	if('$CR_FLAG_JENISWARTA' == "B" || '$CR_FLAG_JENISWARTA' == "")
	{
		document.getElementById('display_BATAL').innerHTML = number_format('$totalLuasBatal', 4, '.', ',')+' HEKTAR';
	}
	
	if('$CR_FLAG_JENISWARTA' == "G" || '$CR_FLAG_JENISWARTA' == "")
	{
		document.getElementById('display_GANTI').innerHTML = number_format('$totalLuasGanti', 4, '.', ',')+' HEKTAR';
	}
	
	if('$totalLuasKosong'>0)
	{
		document.getElementById('tr_display_KOSONG').style.display="";
		document.getElementById('span_display_KOSONG').innerHTML = "<font color = 'red'>"+number_format('$totalLuasKosong', 4, '.', ',')+" HEKTAR</font>";
	}
	else
	{
		document.getElementById('tr_display_KOSONG').style.display="none";
		document.getElementById('span_display_KOSONG').innerHTML = "";
	}
	
	//alert('x');
	printHideDiv('div_LaporanforPrint')
	</script>