<script type="text/javascript" src="../../library/js/jquery-1.7.2.min.js" ></script>
<script type="text/javascript" src="../../library/js/ekptgTools.js"></script>
<script type="text/javascript" src="../../img"></script>
<script>var $jquery = jQuery.noConflict();</script>
<style type="text/css">
<!--
#parse("css/eTapp_PPK.css") 
.style1 {
color: #FF0000
}
-->
</style>
<!--
$listStatsJumlahBicara
-->
<br />

#if($flagPrint == "Y")
        <body onLoad="printSkrin();">   
          
    	<style>
		.onTT {display:block;}
		.infoCetak {display:none;}
    	@media { 
			table { page-break-inside:auto }
			tr    { page-break-inside:avoid; page-break-after:auto;  }
			.autoBreak { page-break-inside:avoid; }
			.onTT {display:none;}
			.infoCetak {display:block;}
		} 	
    	</style>
        
#end

<div class="onTT" >
<fieldset style="margin-top:5px" >
<legend>Carian</legend>
<table width='100%' align='center' border='0' cellspacing='1' cellpadding='2'   >
<tr><td ></td><td align='left' valign='top' >Negeri Pegawai</td><td align='center' valign='top' >:</td><td width='70%' align='left' valign='top' >
#set($namaNegeriDisplay = "")
<select id="statsBicaraID_NEGERI"  name="statsBicaraID_NEGERI" style="width:100%;">	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $listNegeri )		
							#set ( $selected_ruj = "" )
							#if($statsBicaraID_NEGERI==$ruj.ID_NEGERI)
							#set ( $selected_ruj = "selected" )
                            #set($namaNegeriDisplay = $ruj.NAMA_NEGERI)
							#end							
							<option $selected_ruj value="$ruj.ID_NEGERI" >
							$ruj.NAMA_NEGERI
							</option>					
							
						#end
					</select>
                    
                    
</td></tr>
<tr><td class="red" valign="top"  align="center"><font color="red">*</font></td><td align='left' valign='top' >Tarikh Mula Bicara</td><td align='center' valign='top' >:</td><td width='70%' align='left' valign='top' >
<input id="statsBicaraTARIKH_MULA" name="statsBicaraTARIKH_MULA" type="text" value="$statsBicaraTARIKH_MULA" size = '10' style="text-transform:uppercase"  />
 <a href="javascript:displayDatePicker('statsBicaraTARIKH_MULA',false,'dmy');"><img border="0" src="../../img/calendar.gif"/></a>
</td></tr>
<tr><td  class="red"  valign="top" align="center" ><font color="red">*</font></td><td align='left' valign='top' >Tarikh Akhir Bicara</td><td align='center' valign='top' >:</td><td width='70%' align='left' valign='top' >
<input id="statsBicaraTARIKH_AKHIR" name="statsBicaraTARIKH_AKHIR" type="text" value="$statsBicaraTARIKH_AKHIR" size = '10' style="text-transform:uppercase"  />
<a href="javascript:displayDatePicker('statsBicaraTARIKH_AKHIR',false,'dmy');"><img border="0" src="../../img/calendar.gif"/></a>
</td></tr>

<tr><td width='1%'></td><td width='28%'></td><td width='1%'></td><td width='70%'></td></tr>
<tr><td ></td><td align='left' valign='top' >Nama Pegawai</td><td align='center' valign='top' >:</td><td width='70%' align='left' valign='top' >
<input id="statsBicaraNAMA_PEGAWAI" name="statsBicaraNAMA_PEGAWAI" type="text" value="$statsBicaraNAMA_PEGAWAI" style="width:100%;text-transform:uppercase"  />
</td></tr>
</table>
</fieldset>

<table width='100%' align='center' border='0' cellpadding='1' cellspacing='1' style='margin-top:5px'   >
<tr>
<td align='center' valign='top' width='100%'>
<input type="button" id="cmdCariStatsBicara" name="cmdCariStatsBicara" value="Cari" onClick="cariStatsPegawai()" >
<input type="button" id="cmdResetCariStatsBicara" name="cmdResetCariStatsBicara" value="Reset" onClick="resetStatsPegawai();" >
<input type="button" id="cmdCariStatsBicara" name="cmdCariStatsBicara" value="Cetak" onClick="cetakStatsPegawai();" >
</td>
</tr></table>
</div>

#if($flagPrint == "Y" && ($statsBicaraTARIKH_MULA != "" || $statsBicaraTARIKH_AKHIR != "" || $namaNegeriDisplay != "" || $statsBicaraNAMA_PEGAWAI != "")) 
<table align="center"  width="98%" border="0" cellspacing="1" cellpadding="2" class="infoCetak"  >
<tr>
<td colspan="5" valign="top"  style="border-bottom: 1px solid #000;font-size: 100%;">
<strong>LAPORAN STATISTIK PEGAWAI PERBICARAAN</strong>
</td>
</tr>
<tr>
<td width="1%"></td><td width="28%"></td><td width="1%"></td><td width="70%"></td>
</tr>
#if($statsBicaraTARIKH_MULA != "")
<tr>
<td valign="top" ></td><td valign="top">Tarikh Mula Bicara</td><td valign="top">:</td><td valign="top" >$statsBicaraTARIKH_MULA</td>
</tr>
#end
#if($statsBicaraTARIKH_AKHIR != "")
<tr>
<td valign="top" ></td><td valign="top">Tarikh Akhir Bicara</td><td valign="top">:</td><td valign="top" >$statsBicaraTARIKH_AKHIR</td>
</tr>
#end
#if($namaNegeriDisplay != "")
<tr>
<td valign="top" ></td><td valign="top">Negeri Pegawai</td><td valign="top">:</td><td valign="top" >$namaNegeriDisplay</td>
</tr>
#end
#if($statsBicaraNAMA_PEGAWAI != "")
<tr>
<td valign="top" ></td><td valign="top">Nama Pegawai</td><td valign="top">:</td><td valign="top" >$statsBicaraNAMA_PEGAWAI</td>
</tr>
#end
</table>
<br />
#end
<br />	
	#set($CountRekod = 0) 
    #if($listStatsJumlahBicara.size()>0)
    	#set($showDate = "")   
        #set($showNamaHari = "")   
        #set($showNegeri = "")  
        #set($tengahOpen = "")  
    	#foreach($pr in $listStatsJumlahBicara)   
        	  
            #if($pr.LAYER1=="1")
                #if($pr.BIL != "1")
                    #if($tengahOpen == "Y")
                        </table><br /><br />
                        </div>
                        #set($tengahOpen = "")  
                    #end
                #end
                
                #set($openTable = "Y")   
                #set($showDate = $pr.TARIKH_BICARA_DISPLAY) 
                #set($showNamaHari = $pr.NAMA_HARI)   
            #elseif($pr.LAYER1=="2")
            	 #set($showNegeri = $pr.NAMA_NEGERI)
            #elseif($pr.LAYER1=="3")   
            	#if($openTable == "Y")  
                	<div class="autoBreak"> 
                    <table style="border-collapse:collapse;"  cellspacing="1" cellpadding="1"  align="center"  width="98%">             	
                   
                    <tr >
                    <td        colspan="25" align="left">
                    #if($showDate != "")
                    <b>$showDate - $showNamaHari</b><br />
                    #end
                    #if($showNegeri != "")
                    <b>$showNegeri</b><br />
                    #end
                    </td>
                    </tr>
                   
                    <tr >
                           <td        rowspan="2"   align="left" valign="top" style="border: 1px solid black;font-size:60%;" width="25%" ><b>Nama Pegawai</b></td>
                           <td        rowspan="2"   align="center" valign="top" style="border: 1px solid black;font-size:60%;"  width="8.5%" ><b>Jumlah<br />Perbicaraan</b></td>
                           <td         align="center" valign="top" style="border: 1px solid black;font-size:60%;" colspan="19" width="66.5%"><b>Waktu Bicara</b></td>         
                    </tr> 
                    
                    <tr >
                           <td         align="center" valign="top" style="border: 1px solid black;font-size:50%;" width="3.5%">0830<br />0859</td>
                           <td         align="center" valign="top" style="border: 1px solid black;font-size:50%;" width="3.5%">0900<br />0929</td>
                           <td         align="center" valign="top" style="border: 1px solid black;font-size:50%;" width="3.5%">0930<br />0959</td>
                           <td         align="center" valign="top" style="border: 1px solid black;font-size:50%;" width="3.5%">1000<br />1029</td>
                           <td         align="center" valign="top" style="border: 1px solid black;font-size:50%;" width="3.5%">1030<br />1059</td>
                           <td         align="center" valign="top" style="border: 1px solid black;font-size:50%;" width="3.5%">1100<br />1129</td>
                           <td         align="center" valign="top" style="border: 1px solid black;font-size:50%;" width="3.5%">1130<br />1159</td>
                           <td         align="center" valign="top" style="border: 1px solid black;font-size:50%;" width="3.5%">1200<br />1229</td>
                           <td         align="center" valign="top" style="border: 1px solid black;font-size:50%;" width="3.5%">1230<br />1259</td>
                           <td         align="center" valign="top" style="border: 1px solid black;font-size:50%;" width="3.5%">0100<br />0129</td>
                           <td         align="center" valign="top" style="border: 1px solid black;font-size:50%;" width="3.5%">0130<br />0159</td>
                           <td         align="center" valign="top" style="border: 1px solid black;font-size:50%;" width="3.5%">0200<br />0229</td>
                           <td         align="center" valign="top" style="border: 1px solid black;font-size:50%;" width="3.5%">0230<br />0259</td>
                           <td         align="center" valign="top" style="border: 1px solid black;font-size:50%;" width="3.5%">0300<br />0329</td>
                           <td         align="center" valign="top" style="border: 1px solid black;font-size:50%;" width="3.5%">0330<br />0359</td>
                           <td         align="center" valign="top" style="border: 1px solid black;font-size:50%;" width="3.5%">0400<br />0429</td>
                           <td         align="center" valign="top" style="border: 1px solid black;font-size:50%;" width="3.5%">0430<br />0459</td>
                           <td         align="center" valign="top" style="border: 1px solid black;font-size:50%;" width="3.5%">0500<br />0529</td>
                           <td         align="center" valign="top" style="border: 1px solid black;font-size:50%;" width="3.5%">0530<br />0559</td>          
                    </tr> 
                     #set($CountRekod = $CountRekod + 1) 
                     #set($tengahOpen = "Y")
                     #set($openTable = "")
                #end       
                 <tr >
                       <td   align="left" valign="top" style="border: 1px solid black;font-size:50%;" >$pr.NAMA_PEGAWAI</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:50%;">$pr.JUMLAH_PERBICARAAN</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:50%;">$pr.T0830AM_0859AM</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:50%;">$pr.T0900AM_0929AM</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:50%;">$pr.T0930AM_0959AM</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:50%;">$pr.T1000AM_1029AM</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:50%;">$pr.T1030AM_1059AM</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:50%;">$pr.T1100AM_1129AM</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:50%;">$pr.T1130AM_1159AM</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:50%;">$pr.T1200PM_1229PM</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:50%;">$pr.T1230PM_1259PM</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:50%;">$pr.T0100PM_0129PM</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:50%;">$pr.T0130PM_0159PM</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:50%;">$pr.T0200PM_0229PM</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:50%;">$pr.T0230PM_0259PM</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:50%;">$pr.T0300PM_0329PM</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:50%;">$pr.T0330PM_0359PM</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:50%;">$pr.T0400PM_0429PM</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:50%;">$pr.T0430PM_0459PM</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:50%;">$pr.T0500PM_0529PM</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:50%;">$pr.T0530PM_0559PM</td>          
                    </tr>
                #set($tarikhDisplay = $pr.TARIKH_BICARA_DISPLAY)	
                #set($negeriDisplay = $pr.ID_NEGERI)                  
                #if($pr.BIL == $listStatsJumlahBicara.size())
                	#if($tengahOpen == "Y")
                        </table><br /><br />
                        </div>
                        #set($tengahOpen = "")  
                    #end                    
                #end               
            #end                  	
        #end
    #end

  

  #if($flagPrint == "Y")  
        </body>
 
  
  <script>
  //alert('masuk');
  window.print();
 
  </script>
   #end   
   
   <script>
   function cariStatsPegawai()
   {
	    if(document.${formName}.statsBicaraTARIKH_MULA.value==""){
   	   			alert('Sila Masukkan Tarikh Mula Perbicaraan');
				document.${formName}.statsBicaraTARIKH_MULA.focus();
	   	   		return;
   	   	}
		else if(document.${formName}.statsBicaraTARIKH_AKHIR.value==""){
   	   			alert('Sila Masukkan Tarikh Akhir Perbicaraan');
				document.${formName}.statsBicaraTARIKH_AKHIR.focus();
	   	   		return;
   	   	}
		else
		{
	   		doAjaxCall${formName}('showLaporanStatsPegawai','action=Cari');
		}
   }
   
   function cetakStatsPegawai()
   {
	    if(document.${formName}.statsBicaraTARIKH_MULA.value==""){
   	   			alert('Sila Masukkan Tarikh Mula Perbicaraan');
				document.${formName}.statsBicaraTARIKH_MULA.focus();
	   	   		return;
   	   	}
		else if(document.${formName}.statsBicaraTARIKH_AKHIR.value==""){
   	   			alert('Sila Masukkan Tarikh Akhir Perbicaraan');
				document.${formName}.statsBicaraTARIKH_AKHIR.focus();
	   	   		return;
   	   	}
		else
		{
	   		doAjaxCall${formName}('showLaporanStatsPegawai','action=Cetak');
		}
   }
   
   function resetStatsPegawai()
   {
	   document.${formName}.statsBicaraTARIKH_MULA.value = "";
	   document.${formName}.statsBicaraTARIKH_AKHIR.value = "";
	   document.${formName}.statsBicaraNAMA_PEGAWAI.value = "";
	   document.${formName}.statsBicaraID_NEGERI.value = "";
	   doAjaxCall${formName}('showLaporanStatsPegawai','action=Reset');
   }
   
   </script> 
