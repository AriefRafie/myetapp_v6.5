




#set($displayByRole = "none")
<!--
#if($USER_ROLE == "(PDT) Pengguna Bahagian Pengurusan Perundangan Tanah")
#set($displayByRole = "block")
#end
-->


#if($FLAG_EDIT == "Y")
#set($displayByRole = "")
#end

<fieldset id="">

<legend>Senarai Mesyuarat &nbsp;&nbsp;
<div style="display:$displayByRole" >
<input type="button" id="cmdTambahTajukUtama" name="cmdTambahTajukUtama" value="Tambah" 
onClick="doDivAjaxCall$formname('div_rowFolderUtama','addTajukUtama','FlagCari=$FlagCari&ID_MESYUARATUTAMA=');" >
</div>
</legend>

<!--
SENARAI :::::::::::<br /> 
$FLAG_AFTERINSERT<br /> 
$TAJUK_MESYUARAT_AFTERINSERT<br /> 
$TAHUN_AFTERINSERT<br /> 
$BILANGAN_AFTERINSERT	<br /> 
-->

#if($FLAG_AFTERINSERT=="Y")
<script> 
    document.getElementById("carianMesyuarat").value="$TAJUK_MESYUARAT_AFTERINSERT";
	document.getElementById("carianTahun").value="$TAHUN_AFTERINSERT";
	document.getElementById("carianBilangan").value="$BILANGAN_AFTERINSERT";
		
		  $jquery(document).ready(function () {
			  doDivAjaxCall$formname('div_senaraiUtama','carianUtama','FlagCari=Y&FLAG_AFTERINSERT=&FLAG_AUTOLOADSUB=Y');	  
		  });
		  
</script>
#end

<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
<tr id="div_rowFolderUtama">
</tr>
</table>


<table width="100%">
<tr>
<td>

	<table border="0" cellspacing="1" cellpadding="1" width="100%" > 
	#if($listMesyuaratUtama.size()>0)
	<tr >
		   <td  align="left" valign="top" colspan="14" >
		   #parse("app/pdt/mesyuarat/record_paging_pu.jsp")
		   </td>	     
	</tr>
	#end 
	<tr class="table_header" >
           <td   align="center" valign="top" width="3%" >No.</td> 
		   <td   align="left" valign="top" width="25%" >Tajuk Mesyuarat</td> 
           <td   align="center" valign="top" width="7%">Tahun</td>
           <td   align="center" valign="top" width="7%">Bilangan</td>
           <td   align="center" valign="top" width="10%">Status</td> 
           <td   align="left" valign="top" width="33%">PIC / Tel / Emel</td> 
           <td   align="center" valign="top" width="15%" style="display:$displayByRole">Tindakan</td> 
	</tr>
	#if($listMesyuaratUtama.size()>0)
	
		#foreach($LFU in $listMesyuaratUtama)
		<tr id="div_rowFolderUtama$LFU.ID_MESYUARATUTAMA">
        	   <td class="$LFU.rowCss"  align="center" valign="top" >$LFU.BIL</td> 
			   <td class="$LFU.rowCss"  align="left" valign="top">  	
               #set($span1 = "span1listMesyuaratUtama"+$LFU.BIL)
               
               #if($FLAG_AUTOLOADSUB=="Y")
               <script> 		
		  $jquery(document).ready(function () {
			 doDivAjaxCall$formname('div_viewFolderUtama$LFU.ID_MESYUARATUTAMA','showAllFolder','NAMA_FOLDER=$LFU.TAJUK_MESYUARAT&FLAG_OPENCLOSE='+$jquery('#HID_OPENCLOSE_$LFU.ID_MESYUARATUTAMA').val()+'&ID_MESYUARATUTAMA=$LFU.ID_MESYUARATUTAMA&LAYER=1&AUTOLOAD=Y&FlagCari=Y&MAX_LAYER=$LFU.MAX_LAYER');			 	  				});		  
               </script>
               #end
               
               		   
			   <span id="$span1" > 
			   <span onClick="doDivAjaxCall$formname('div_viewFolderUtama$LFU.ID_MESYUARATUTAMA','showAllFolder','NAMA_FOLDER=$LFU.TAJUK_MESYUARAT&FLAG_OPENCLOSE='+$jquery('#HID_OPENCLOSE_$LFU.ID_MESYUARATUTAMA').val()+'&ID_MESYUARATUTAMA=$LFU.ID_MESYUARATUTAMA&LAYER=1&AUTOLOAD=Y&FlagCari=Y&MAX_LAYER=$LFU.MAX_LAYER');" >
			   <span class="font_tajuk_utama" ><u><b>$LFU.TAJUK_MESYUARAT</b></u></span>
			   </span>
			   </span>
               #if(($AUTOLOAD=="Y" || $FlagCari=="Y"))	
			   <script>highlight_item('$carianMesyuarat','$span1')</script>
               #end
			   <input type="hidden" id="countFolder_$LFU.ID_MESYUARATUTAMA" name="countFolder_$LFU.ID_MESYUARATUTAMA" value = "0"  >
			   <input type="hidden" id="countActFolder_$LFU.ID_MESYUARATUTAMA" name="countActFolder_$LFU.ID_MESYUARATUTAMA" value = "$LFU.TOTAL_FOLDER"  >
			   <input type="hidden" id="countTindakan_$LFU.ID_MESYUARATUTAMA" name="countTindakan_$LFU.ID_MESYUARATUTAMA" value = "0"  >
			   <input type="hidden" id="countActLampiran_$LFU.ID_MESYUARATUTAMA" name="countActLampiran_$LFU.ID_MESYUARATUTAMA" value = "$LFU.TOTAL_TINDAKAN"  >
			   <input type="hidden" id="HID_OPENCLOSE_$LFU.ID_MESYUARATUTAMA" name="HID_OPENCLOSE_$LFU.ID_MESYUARATUTAMA" value = "CLOSE"  >
			   <input type="hidden" id="HID_MAXLAYER_$LFU.ID_MESYUARATUTAMA" name="HID_MAXLAYER_$LFU.ID_MESYUARATUTAMA" value = "$LFU.MAX_LAYER"  >
			  
               <span id="ShowDalamTindakanMain_$LFU.ID_MESYUARATUTAMA">
               #if($LFU.DALAM_TINDAKAN > 0)
               <br />
			   <font color='red' class="blink"><b>* $LFU.DALAM_TINDAKAN Menunggu Tindakan Bahagian</b></font> 
               
                <a href="javascript:if(confirm('Emel akan dihantar. Adakah Anda Pasti?')){ doDivAjaxCall$formname('div_viewFolderUtama$LFU.ID_MESYUARATUTAMA','emelTindakanMain','NAMA_FOLDER=$LFU.TAJUK_MESYUARAT&FLAG_OPENCLOSE=CLOSE&ID_MESYUARATUTAMA=$LFU.ID_MESYUARATUTAMA&LAYER=1&AUTOLOAD=Y&FlagCari=Y&MAX_LAYER=$LFU.MAX_LAYER');}"><img title="Emel"  src="../img/emel.gif" border="0"></a>
               
               #end
               </span>
			   </td>  
               
           <td  class="$LFU.rowCss" align="center" valign="top" >
           #set($span2 = "span2listMesyuaratUtama"+$LFU.BIL)
           <span id="$span2">$LFU.TAHUN</span>
           #if(($AUTOLOAD=="Y" || $FlagCari=="Y"))	
            <script>highlight_item('$carianTahun','$span2')</script>
            #end
            </td>
           <td  class="$LFU.rowCss" align="center" valign="top" >
           #set($span3 = "span3listMesyuaratUtama"+$LFU.BIL)
           <span id="$span3">$LFU.BILANGAN</span>
           #if(($AUTOLOAD=="Y" || $FlagCari=="Y"))	
           <script>highlight_item('$carianBilangan','$span3')</script>
           #end
           </td> 
           <td  class="$LFU.rowCss" align="center" valign="top" >
            #set($span4 = "span4listMesyuaratUtama"+$LFU.BIL)
           <span id="$span4">
           $LFU.STATUS_MESYUARAT
           </span>
           
           #if(($AUTOLOAD=="Y" || $FlagCari=="Y"))		
			<script>			
			var statcarianmesyuarat = "";
			if($jquery('#carianStatusMesyuarat').val()=="A")
			{
				statcarianmesyuarat = "AKTIF";
			}
			else if($jquery('#carianStatusMesyuarat').val()=="T")
			{
				statcarianmesyuarat = "TANGGUH";
			}
			highlight_item(statcarianmesyuarat,'$span4'); 
			</script>			
           #end
           
           </td> 
           <td class="$LFU.rowCss"  align="left" valign="top" >
           
           <table width="100%" cellpadding="0" cellspacing="0" >
            <tr>
            <td width="5%" align="left" valign="top">PIC</td>
            <td width="1%" align="center" valign="top">:</td>
            <td width="94%" align="left" valign="top">           
            #set($span9 = "span9listMesyuaratUtama"+$LFU.BIL)
            <span id="$span9"> $LFU.PIC</span>
            #if(($AUTOLOAD=="Y" || $FlagCari=="Y"))	
            <script>highlight_item('$carianPIC','$span9')</script>
            #end
            </td>
            </tr>
            #if($LFU.TEL_PIC != "")
            <tr>
            <td align="left" valign="top">Tel</td>
            <td  align="center" valign="top">:</td>
            <td  align="left" valign="top">
            $LFU.TEL_PIC
            </td>
            </tr>
            #end
             #if($LFU.EMEL_PIC != "")
            <tr>
            <td align="left" valign="top">Emel</td>
            <td  align="center" valign="top">:</td>
            <td  align="left" valign="top">
            <span id="listEMELPIC$LFU.ID_MESYUARATUTAMA"  style="width:100%" >   
                $LFU.EMEL_PIC              
           </span>
           <script>
		   	 hideByTag('listEMELPIC$LFU.ID_MESYUARATUTAMA','strong');
		   </script>
            </td>
            </tr>
            #end
            </table>
           
           
           
           
           </td>
           
           <td class="$LFU.rowCss"  align="center" valign="top" style="display:$displayByRole">
			<input type="hidden" id="BIL_MAIN_$LFU.ID_MESYUARATUTAMA" name="BIL_MAIN_$LFU.ID_MESYUARATUTAMA" value = "$LFU.BIL"  >
            <input type="hidden" id="rowCss_MAIN_$LFU.ID_MESYUARATUTAMA" name="rowCss_MAIN_$LFU.ID_MESYUARATUTAMA" value = "$LFU.rowCss"  >
			   <a href="javascript:doDivAjaxCall$formname('div_rowFolderUtama$LFU.ID_MESYUARATUTAMA','editMainDir','rowCss=$LFU.rowCss&ID_MESYUARATUTAMA=$LFU.ID_MESYUARATUTAMA&BIL=$LFU.BIL');"><img title="Kemaskini Nama Sub Direktori" src="../img/edit.gif" border="0"></a>
	    	   <a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('div_senaraiUtama','deleteFolderUtama','ID_MESYUARATUTAMA=$LFU.ID_MESYUARATUTAMA&FlagCari=N&carianTerperinci='+$jquery('#carianTerperinci').val());}"><img title="Hapus Direktori"  src="../img/hapus.gif" border="0"></a>
               
               
               #if($LFU.FLAG_AKTIF == "T" && $LFU.TOTALBILANGAN == $LFU.BILANGAN && $LFU.TOTALTAHUN == $LFU.TAHUN)
           
               
               <span class="font_tajuk_sub" style="cursor:pointer">
	<u onClick="if(confirm('Maklumat mesyuarat akan disalin untuk merekod maklumat mesyuarat bilangan seterusnya. Adakah Anda Pasti?')){ doDivAjaxCall$formname('div_senaraiUtama','salinRekod','ID_MESYUARATUTAMA=$LFU.ID_MESYUARATUTAMA&FlagCari=N&carianTerperinci='+$jquery('#carianTerperinci').val());}">[Salin]</u>
	</span>
	
	    		#end
              
               </td>
                
		</tr>
		<tr  >
        <td align="left" valign="top"  >	
		</td>	
		<td align="left" valign="top" colspan="10" >		
		<div id="div_viewFolderUtama$LFU.ID_MESYUARATUTAMA">		
		</div>			
		</td>			
		</tr>
		#end
	#else
	<tr >
		   <td  align="left" valign="top" colspan="10" >Tiada Rekod</td>
		     
	</tr>
	#end
	</table>

 
</td>
</tr>
</table>
</fieldset>


