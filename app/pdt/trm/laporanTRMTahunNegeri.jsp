
#if($command == "TarikDataByTahun")

<script>
setPageLocation('$scrolPosition');
</script>

#else

#if($FLAG_CARIAN_LAPORAN=="Y")
<script>
	document.getElementById('div_Laporan').style.display = "";
	if( $jquery('#div_Laporan').length )         // use this if you are using id to check
	{
		window.scrollTo(0, $jquery('#div_Laporan').offset().top-10);
	}
</script>
#else
<script>
	document.getElementById('div_Laporan').style.display = "none";
</script>
#end

#end



#set($header = "LAPORAN KESELURUHAN KELUASAN(HEKTAR) KAWASAN TANAH RIZAB MELAYU")
<fieldset>
<!--
<legend>
#if($modeReport=="edit")
<input type="button" id="BTNSAVELAPORAN" name="BTNSAVELAPORAN" 
onClick="if(confirm('Adakah anda pasti?')){doDivAjaxCall$formname('div_Laporan','SaveLaporan','FLAG_CARIAN_LAPORAN=$FLAG_CARIAN_LAPORAN&modeReport=view&FLAG_CARIAN_LAPORAN=Y&scrolPosition='+getPageLocation('$command'));}" value="Simpan" >               	
<input type="button" id="BTNSAVELAPORAN" name="BTNSAVELAPORAN" 
onClick="doDivAjaxCall$formname('div_Laporan','EditLaporan','FLAG_CARIAN_LAPORAN=$FLAG_CARIAN_LAPORAN&modeReport=edit&FLAG_CARIAN_LAPORAN=Y&scrolPosition='+getPageLocation('$command'));" value="Batal" > 
#else                
<input type="button" id="BTNSAVELAPORAN" name="BTNSAVELAPORAN" 
onClick="doDivAjaxCall$formname('div_Laporan','EditLaporan','FLAG_CARIAN_LAPORAN=$FLAG_CARIAN_LAPORAN&modeReport=edit&FLAG_CARIAN_LAPORAN=Y&scrolPosition='+getPageLocation('$command'));" value="Kemaskini" >   
<input type="button" id="BTNPRINTLAPORAN" name="BTNPRINTLAPORAN" 
                    onclick="printLaporan('printableArea_Laporan','$header')" 
                    value="Cetak" />
#end
</legend>
-->

<table border="0" width="95%" align="center" cellpadding="2" cellspacing="1">
<tr>
<td style="border-bottom:1px solid black;" >
#parse("app/pdt/trm/buttonCetak.jsp")
</td>
</tr>
</table>






<div id="printableArea_Laporan" >
#set($tot = $listREPORTNEGERI.size() + 1)
#set($tot = 95 / $tot)
<table style="border-collapse:collapse;" width="95%" align="center" cellpadding="2" cellspacing="1">
<thead >
<tr  >
<th colspan="15" align="center" style="font-size:90%;">$header<br><br></th>
</tr>
<tr   class="row2">
<th style="border:1px solid black; background-color:#A1EAD3;" valign="top" width="5%"></th>
#foreach($n in $listREPORTNEGERI)
<th style="border:1px solid black; font-size:65%; background-color:#A1EAD3;" valign="top" align="center" width="$tot%"> $n.NAMA_NEGERI</th>
#end
</tr>
</thead >

#set($tahun = "")
#foreach($rep in $listREPORTTAHUNNEGERI)

    #if($rep.TAHUN != $tahun)
    #set($tahun = $rep.TAHUN)
    #set($jumlah = 0.0)
    <tr  >
    	<td style="border :1px solid black; font-size:65%; background-color:#A1EAD3;"   class="row2"  valign="top" align="center">
        <b>$tahun </b>
        #if($modeReport=="edit")
        <a href="javascript:if(confirm('Data Akan Ditarik Daripada Kemasukan Maklumat Melalui MyeTaPP. Adakah anda Pasti?')){ doDivAjaxCall$formname('div_Laporan','TarikDataByTahun','REGE_TAHUN=$tahun&FLAG_CARIAN_LAPORAN=$FLAG_CARIAN_LAPORAN&modeReport=edit&FLAG_CARIAN_LAPORAN=Y&scrolPosition='+getPageLocation('$command'));}">
        <b><font color='blue'  title="Data Akan Ditarik Daripada Kemasukkan Malumat Warta Melalui MyeTaPP">>></font></b></a>
        #end
        </td>
        #foreach($repNegeri in $listREPORTTAHUNNEGERI)
            #if($repNegeri.TAHUN == $tahun)
            <td style="border:1px solid black; font-size:65%; " valign="top" align="right">
            #set($jumlah = $jumlah + $repNegeri.LUAS)
            <!-- $repNegeri.ID_TRMREPTAHUNNEGERI, $repNegeri.NAMA_NEGERI, $repNegeri.TAHUN -->
            #set($luas_type="text")
            #if($modeReport=="view" || ($modeReport!="view" && $repNegeri.ID_NEGERI == "999999"))
            
            $repNegeri.LUAS_DISPLAY <span id="spanProgress_$repNegeri.BIL">
           
			<script>
			checkProgresLuas('$repNegeri.LUAS_FIELD','$repNegeri.BIL','$listREPORTNEGERI.size()');
            </script>
            </span>
            #set($luas_type="hidden")
            #end
             <input style="text-transform:uppercase;"   class="autoSizing"  type="$luas_type" id="LUAS_$repNegeri.TAHUN$repNegeri.ID_NEGERI" 
				name="LUAS_$repNegeri.TAHUN$repNegeri.ID_NEGERI" value="$repNegeri.LUAS_FIELD" onkeypress="return isNumberKey(event,this.value,this)"
                 onblur="convertFourDec(this.value,this)"   >
                 
                 
             <input  type="hidden" id="ID_TRMREPTAHUNNEGERI_$repNegeri.TAHUN$repNegeri.ID_NEGERI" name="ID_TRMREPTAHUNNEGERI_$repNegeri.TAHUN$repNegeri.ID_NEGERI" value="$repNegeri.ID_TRMREPTAHUNNEGERI"  >	
              <input type="hidden" id="LUAS_ASAL_$repNegeri.TAHUN$repNegeri.ID_NEGERI" name="LUAS_ASAL_$repNegeri.TAHUN$repNegeri.ID_NEGERI" value="$repNegeri.LUAS"   >
              <input type="hidden" id="LUAS_ASAL_CAL_$repNegeri.BIL" name="LUAS_ASAL_CAL_$repNegeri.BIL" value="$repNegeri.LUAS"   >
             
            </td>
            #end
        #end
       
    <tr>
    #end
	<!--$rep.TAHUN, $rep.LUAS, $rep.NAMA_NEGERI-->
        
#end
</table>
</div>
<br />



<table border="0" width="95%" align="center" cellpadding="2" cellspacing="1">
<tr>
<td style="border-top:1px solid black;" >
#parse("app/pdt/trm/buttonCetak.jsp")
</td>
</tr>
</table>

</fieldset>
