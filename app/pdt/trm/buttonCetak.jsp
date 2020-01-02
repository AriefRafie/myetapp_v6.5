#if($modeReport=="edit")
<input type="button" id="BTNSAVELAPORAN" name="BTNSAVELAPORAN" 
onClick="if(confirm('Adakah anda pasti?')){doDivAjaxCall$formname('div_Laporan','SaveLaporan','FLAG_CARIAN_LAPORAN=$FLAG_CARIAN_LAPORAN&modeReport=view&FLAG_CARIAN_LAPORAN=Y&scrolPosition='+getPageLocation('$command'));}" value="Simpan" >               	
<input type="button" id="BTNSAVELAPORAN" name="BTNSAVELAPORAN" 
onClick="doDivAjaxCall$formname('div_Laporan','EditLaporan','FLAG_CARIAN_LAPORAN=$FLAG_CARIAN_LAPORAN&modeReport=edit&FLAG_CARIAN_LAPORAN=Y&scrolPosition='+getPageLocation('$command'));" value="Batal" > 
#else       
 <div style="display:$displayByRole" >         
<input type="button" id="BTNSAVELAPORAN" name="BTNSAVELAPORAN" 
onClick="doDivAjaxCall$formname('div_Laporan','EditLaporan','FLAG_CARIAN_LAPORAN=$FLAG_CARIAN_LAPORAN&modeReport=edit&FLAG_CARIAN_LAPORAN=Y&scrolPosition='+getPageLocation('$command'));" value="Kemaskini" > 
</div>  
<input type="button" id="BTNPRINTLAPORAN" name="BTNPRINTLAPORAN" 
                    onclick="printLaporan('printableArea_Laporan','$header')" 
                    value="Cetak" />
#end