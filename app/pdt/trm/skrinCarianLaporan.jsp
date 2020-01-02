

#if($OPENCLOSE_CARIAN_LAPORAN=="open")
<script>
	//alert('1');
	document.getElementById('div_CARIANLaporan').style.display = "";
	document.getElementById('span_LINK_CARIANLaporan').style.display = "none";	
	//alert('2');
	if( $jquery('#div_CARIANLaporan').length )         // use this if you are using id to check
	{
		window.scrollTo(0, $jquery('#div_CARIANLaporan').offset().top-10);
	}
</script>
#elseif($OPENCLOSE_CARIAN_LAPORAN=="close")
<script>
	document.getElementById('div_CARIANLaporan').style.display = "none";
	document.getElementById('span_LINK_CARIANLaporan').style.display = "";
</script>
#end



<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" id="TABLE_CARIANLaporan" class="classFade">
<tr  >
<td  width="100%"  valign="top">
	
	
			
	<fieldset>
	<legend>Filter Laporan</legend>
	<table width="100%" align="center" border="0" cellpadding="0" cellspacing="2">	
	<tr>
			<td valign="top"  width="1%"></td><td valign="top"  width="28%"></td><td valign="top"  width="1%"></td><td valign="top"  width="70%"><!-- $viewPengguna --></td>
			</tr>
			
			
             <tr  >
				<td valign="top" >
               
                </td>			
				<td valign="top" >
				Tahun
                </td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                <input type="text" size="10" id="LAP_TAHUN_DARI" name="LAP_TAHUN_DARI" style="text-transform:uppercase;" 
value="$LAP_TAHUN_DARI" maxLength="4"
onKeyUp="numberOnlyTahun('LAP_TAHUN_DARI','LAP_TAHUN_HINGGA');if(valFilterLaporanTahun()==true){doDivAjaxCall$formname('list_tahun','JanaTahun','modeReport=&FLAG_CARIAN_LAPORAN=Y&scrolPosition='+getPageLocation('$command'));}"
>
<span id="span_CHECK_LAP_TAHUN_DARI">
<input type='hidden' id='CHECK_LAP_TAHUN_DARI' name='CHECK_LAP_TAHUN_DARI' value='true' >
</span>
-
<input type="text" size="10" id="LAP_TAHUN_HINGGA" name="LAP_TAHUN_HINGGA" style="text-transform:uppercase;" 
value="$LAP_TAHUN_HINGGA" maxLength="4"
onKeyUp="numberOnlyTahun('LAP_TAHUN_DARI','LAP_TAHUN_HINGGA');if(valFilterLaporanTahun()==true){doDivAjaxCall$formname('list_tahun','JanaTahun','modeReport=&FLAG_CARIAN_LAPORAN=Y&scrolPosition='+getPageLocation('$command'));}"
>
<span id="span_CHECK_LAP_TAHUN_HINGGA">
<input type='hidden' id='CHECK_LAP_TAHUN_HINGGA' name='CHECK_LAP_TAHUN_HINGGA' value='true' >
</span>
				<!--
                <input type="button" id="cmdCari" name="cmdCari" value="Jana Tahun" 
				onClick="if(valFilterLaporanTahun()==true){doDivAjaxCall$formname('list_tahun','JanaTahun','modeReport=&FLAG_CARIAN_LAPORAN=Y&scrolPosition='+getPageLocation('$command'));}" 
				>
                -->	
                </td>
			</tr>
            
            <tr  >
				<td valign="top" >
               
                </td>			
				<td valign="top" >
				
                </td>
				<td valign="top" >
			
				</td>
				<td valign="top" >
                <div id="list_tahun">
                
                </div>
                </td>
                </tr>
                
            
            <tr  >
				<td valign="top" >
               
                </td>			
				<td valign="top" >
				Negeri
                </td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                            <table border="0" cellspacing="1" cellpadding="1" width="50%" > 
                            <tr><td width="10%"></td><td width="90%"></td></tr>
                            <tr class="table_header" >
                            <td align="center" valign="top">
                            <input type="checkbox" onclick="doCheckMain('checkMAIN_NEGERI','checkSUB_NEGERI');" id="checkMAIN_NEGERI" name="checkMAIN_NEGERI" value="1" />                            
                            </td>
                            <td  valign="top" align="left" >
                            Pilih Kesemua Negeri
                            </td>
                            </tr>
                            #foreach($ln in $listNegeri)
                            <tr  class="row2" >
                            <td align="center" valign="top">
                            <input type="checkbox" onclick="doCheckSub('checkMAIN_NEGERI','checkSUB_NEGERI');" id="checkSUB_NEGERI" name="checkSUB_NEGERI" value="$ln.ID_NEGERI" />   </td>
                            <td align="left" valign="top">$ln.NAMA_NEGERI</td>
                            </tr>
                            #end
                            </table>
				</td>
			</tr>
            
			
			
            
            
		
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				</td>
				<td valign="top" >
				</td>
				<td valign="top" >
				<input type="button" id="cmdCari" name="cmdCari" value="Cari / Jana Laporan" 
				onClick="document.getElementById('SenaraiLaporanTahunNegeriForPrint').innerHTML='';if(valFilterLaporan()==true){doDivAjaxCall$formname('div_Laporan','JanaLaporan','modeReport=view&FLAG_CARIAN_LAPORAN=Y&scrolPosition='+getPageLocation('$command'));}" 
				>								
				<input type="button" id="cmdTutup" name="cmdTutup" value="Tutup" 
				onClick="document.getElementById('div_CARIANLaporan').innerHTML='';document.getElementById('div_Laporan').innerHTML='';document.getElementById('span_LINK_CARIANLaporan').style.display = '';	" >
				
				
				<input style="display:none;" type="button" id="cmdCetak" name="cmdCetak" value="Cetak" 
				onClick="doDivAjaxCall$formname('SenaraiLaporanTahunNegeriForPrint','showLaporani_Print','FLAG_CARIAN_LAPORAN=Y');" 
				>	
				
				</td>
			</tr>
	</table>
	</fieldset>
	
	<div id="SenaraiLaporanTahunNegeriForPrint">	
	</div>

<br>
</td>
</tr>
</table>

