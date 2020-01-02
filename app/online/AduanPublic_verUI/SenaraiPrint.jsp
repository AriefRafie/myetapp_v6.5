<!-- open ct -->
	
	#if($list.size()>0)
	<div id="div_LaporanforPrint"  style="display:none"  >
   
	
	<table style="border-collapse: collapse;"  cellspacing="1" cellpadding="2"  width="100%" > 	
	<tr>
	<td valign="top"  width="1%"></td><td valign="top"  width="28%"></td><td valign="top"  width="1%"></td><td valign="top"  width="70%"><!-- $viewPengguna --></td>
	</tr>
	<tr>
	<td valign="top" colspan="4"  style="border-bottom: 1px solid #000;font-size: 120%;" >
	<b>$label_laporan_cetak</b>
	</td>
	</tr>
	#if($CR_NO_ADUAN != "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 100%;" >$label_no_aduan	</td>
	<td valign="top"  style="font-size: 100%;" >
	:
	</td>
	<td valign="top" style="font-size: 100%;">
	$CR_NO_ADUAN
	</td>
	</tr>
	#end
    #if($CR_NAMA_PENGADU != "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 100%;" >$label_pengadu</td>
	<td valign="top"  style="font-size: 100%;" >
	:
	</td>
	<td valign="top" style="font-size: 100%;">
	$CR_NAMA_PENGADU
	</td>
	</tr>
	#end
    #if($CR_ID_JENISADUAN != "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 100%;" >$label_jenis_aduan</td>
	<td valign="top"  style="font-size: 100%;" >
	:
	</td>
	<td valign="top" style="font-size: 100%;">
	<span id="display_ID_JENISADUAN"></span>
	<script>
    $jquery(document).ready(function () {
    returnDropDownSelectedText('CR_ID_JENISADUAN','display_ID_JENISADUAN');
    });
    </script>    
	</td>
	</tr>
	#end
    #if($CR_ID_KATEGORIADUAN != "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 100%;" >$label_kategori_aduan</td>
	<td valign="top"  style="font-size: 100%;" >
	:
	</td>
	<td valign="top" style="font-size: 100%;">
	<span id="display_ID_KATEGORIADUAN"></span>
	<script>
    $jquery(document).ready(function () {
    returnDropDownSelectedText('CR_ID_KATEGORIADUAN','display_ID_KATEGORIADUAN');
    });
    </script>    
	</td>
	</tr>
	#end
    #if($CR_ID_SUMBERADUAN != "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 100%;" >$label_sumber_pengadu</td>
	<td valign="top"  style="font-size: 100%;" >
	:
	</td>
	<td valign="top" style="font-size: 100%;">
	<span id="display_ID_SUMBERADUAN"></span>
	<script>
    $jquery(document).ready(function () {
    returnDropDownSelectedText('CR_ID_SUMBERADUAN','display_ID_SUMBERADUAN');
    });
    </script>    
	</td>
	</tr>
	#end
    
    
    #if($CR_ID_JENISTINDAKAN != "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 100%;" >$label_jenis_tindakan_aduan</td>
	<td valign="top"  style="font-size: 100%;" >
	:
	</td>
	<td valign="top" style="font-size: 100%;">
	<span id="display_ID_JENISTINDAKAN"></span>
	<script>
    $jquery(document).ready(function () {
    returnDropDownSelectedText('CR_ID_JENISTINDAKAN','display_ID_JENISTINDAKAN');
    });
    </script>    
	</td>
	</tr>
	#end
    
    
    #if($CR_ID_NEGERI_BAHAGIAN != "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 100%;" >$label_negeri_bahagian</td>
	<td valign="top"  style="font-size: 100%;" >
	:
	</td>
	<td valign="top" style="font-size: 100%;">
	<span id="display_ID_NEGERI_BAHAGIAN"></span>
	<script>
    $jquery(document).ready(function () {
    returnDropDownSelectedText('CR_ID_NEGERI_BAHAGIAN','display_ID_NEGERI_BAHAGIAN');
    });
    </script>    
	</td>
	</tr>
	#end
    
    
    #if($CR_ID_BAHAGIAN != "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 100%;" >$label_tindakan_bahagian</td>
	<td valign="top"  style="font-size: 100%;" >
	:
	</td>
	<td valign="top" style="font-size: 100%;">
	<span id="display_ID_BAHAGIAN"></span>
	<script>
    $jquery(document).ready(function () {
    returnDropDownSelectedText('CR_ID_BAHAGIAN','display_ID_BAHAGIAN');
    });
    </script>    
	</td>
	</tr>
	#end
    
    
    
    
    #if($CR_ID_NEGERI != "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 100%;" >$label_negeri</td>
	<td valign="top"  style="font-size: 100%;" >
	:
	</td>
	<td valign="top" style="font-size: 100%;">
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
	<td valign="top" style="font-size: 100%;" >$label_daerah</td>
	<td valign="top"  style="font-size: 100%;" >
	:
	</td>
	<td valign="top" style="font-size: 100%;">
	<span id="display_ID_DAERAH"></span>
	<script>
    $jquery(document).ready(function () {
    returnDropDownSelectedText('CR_ID_DAERAH','display_ID_DAERAH');
    });
    </script>    
	</td>
	</tr>
	#end
    #if($CR_ID_STATUS != "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 100%;" >$label_status_aduan</td>
	<td valign="top"  style="font-size: 100%;" >
	:
	</td>
	<td valign="top" style="font-size: 100%;">
	<span id="display_ID_STATUS"></span>
	<script>
    $jquery(document).ready(function () {
    returnDropDownSelectedText('CR_ID_STATUS','display_ID_STATUS');
    });
    </script>    
	</td>
	</tr>
	#end
    
    
    #if($CR_TARIKH_MULA != "" || $CR_TARIKH_AKHIR != "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 100%;" >$label_tarikh_aduan</td>
	<td valign="top"  style="font-size: 100%;" >
	:
	</td>
	<td valign="top" style="font-size: 100%;">
	#if($CR_TARIKH_MULA !="")
	$label_from $CR_TARIKH_MULA
	#end
	
	#if($CR_TARIKH_AKHIR != "" && $CR_TARIKH_AKHIR != "")
	&nbsp;
	#end
	
	#if($CR_TARIKH_AKHIR !="")
	$label_to $CR_TARIKH_AKHIR
	#end
	</td>
	</tr>
	#end
    
	
	#if($CR_DETAILS_ADUAN != "")
	<tr >
	<td valign="top"  >
	</td>
	<td valign="top" style="font-size: 100%;" >$label_keterangan_log	</td>
	<td valign="top"  style="font-size: 100%;" >
	:
	</td>
	<td valign="top" style="font-size: 100%;">
	$CR_DETAILS_ADUAN
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
    
    
	<tr   >
		   <th style="border: 1px solid black;font-size:85%;" align="center" valign="top"><b>$label_bil.toUpperCase()</b></th>
		   <th style="border: 1px solid black;font-size:85%;"  align="left" valign="top"><b>$label_no_aduan.toUpperCase()</b></th>		   
		   <th style="border: 1px solid black;font-size:85%;"  align="left" valign="top"><b>$label_pengadu.toUpperCase()</b></th>
           <th style="border: 1px solid black;font-size:85%;"  align="center" valign="top"><b>$label_tarikh_aduan.toUpperCase()</b></th>
		   <th style="border: 1px solid black;font-size:85%;"  align="left" valign="top"><b>$label_jenis_aduan.toUpperCase()</b></th>
           <th style="border: 1px solid black;font-size:85%;"  align="left" valign="top"><b>$label_jenis_tindakan_aduan.toUpperCase()</b></th>
           <th style="border: 1px solid black;font-size:85%;"  align="left" valign="top"><b>$label_keterangan_log.toUpperCase()</b></th>           	   
		   <th style="border: 1px solid black;font-size:85%;"  align="center" valign="top"><b>$label_status_aduan.toUpperCase()</b></th>
		   
	</tr>
	</thead>
	
		#foreach($LOGPRINT in $listLog_forPrint)
		
		<tr class="page-break"  >
		   <td width="2%" style="border: 1px solid black;font-size:65%;"   align="center" valign="top" >$LOGPRINT.BIL </td>
		   <td style="border: 1px solid black;font-size:65%;"  align="left" valign="top">
		    #if($LOGPRINT.ID_STATUS == "16125")   
               [<u>$LOGPRINT.STATUS</u>]
             #else
			   <u>$LOGPRINT.NO_ADUAN</u>
             #end	
		   </td>
		    <td style="border: 1px solid black;font-size:65%;"  align="left" valign="top">
            #if($LOGPRINT.FLAG_HIDE_INFO == "1")
            	[$label_maklumat_dilindungi]
            #else
                $LOGPRINT.NAMA_PENGADU
                #if($LOGPRINT.EMEL_PENGADU != "")
                <br />$LOGPRINT.EMEL_PENGADU
                #end
                #if($LOGPRINT.TEL_PENGADU != "")
                <br />$LOGPRINT.TEL_PENGADU
                #end
            #end
		   </td>
		   <td style="border: 1px solid black;font-size:65%;"  align="center" valign="top">
		   $LOGPRINT.TARIKH_ADUAN
		   </td>
		   <td style="border: 1px solid black;font-size:65%;"  align="left" valign="top">
		   $LOGPRINT.JENIS_ADUAN
           #if($LOGPRINT.ID_JENISADUAN == "16101")
            - $LOGPRINT.KATEGORIADUAN
           #end           
		   </td>
           
           
           <td style="border: 1px solid black;font-size:65%;"  align="left" valign="top">
          
           $LOGPRINT.KATEGORITINDAKAN
           #if($LOGPRINT.NAMA_BAHAGIAN != "")
            - $LOGPRINT.NAMA_BAHAGIAN
           #end 
           
           #if($LOGPRINT.ID_JENISTINDAKAN == "16173")
           #if($LOGPRINT.NEGERI_PT != "")
            - $LOGPRINT.NEGERI_PT
           #end 
           #end 
           
           #if($LOGPRINT.ID_JENISTINDAKAN == "16174")
           #if($LOGPRINT.DAERAH_PT != "")
            - $LOGPRINT.DAERAH_PT
           #end 
           #end 
           
           #if($LOGPRINT.NAMA_PEGAWAI_BAHAGIAN != "")
           <br />$LOGPRINT.NAMA_PEGAWAI_BAHAGIAN
           #end 
           #if($LOGPRINT.EMEL_PEGAWAI_BAHAGIAN != "")
           <br />$LOGPRINT.EMEL_PEGAWAI_BAHAGIAN
           #end   
           #if($LOGPRINT.TEL_PEGAWAI_BAHAGIAN != "")
           <br />$LOGPRINT.TEL_PEGAWAI_BAHAGIAN
           #end  
           
           
           #if($LOGPRINT.EMEL_BAHAGIAN_1 != "")
           <br />$LOGPRINT.EMEL_BAHAGIAN_1
           #end 
           #if($LOGPRINT.EMEL_BAHAGIAN_2 != "")
           <br />$LOGPRINT.EMEL_BAHAGIAN_2
           #end 
           #if($LOGPRINT.EMEL_BAHAGIAN_2 != "")
           <br />$LOGPRINT.EMEL_BAHAGIAN_2
           #end 
           #if($LOGPRINT.EMEL_BAHAGIAN_2 != "")
           <br />$LOGPRINT.EMEL_BAHAGIAN_2
           #end  
                 
		   </td>
           
           
		   <td style="border: 1px solid black;font-size:65%;"  align="left" valign="top">
           <!--
           #if($LOGPRINT.ID_JENISADUAN == "16101")
           [$LOGPRINT.SUBJEK_ADUAN] <br />  
           #end
           -->
		   $label_keterangan_log.toUpperCase() : <br />
           $LOGPRINT.KETERANGAN_ADUAN   
           
		   </td>
		   <td style="border: 1px solid black;font-size:65%;"  align="center" valign="top">
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
	printHideDiv('div_LaporanforPrint')
	</script>