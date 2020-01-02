
<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
<tr  >
<td  width="100%"  valign="top">
	
<fieldset>

<legend>Carian Pengurusan Perkhidmatan <!--<input type="button" id="cmdAddIntegDalaman" name="cmdAddIntegDalaman"   
onClick="doDivAjaxCall$formname('div_AddInteg','addInteg','ID_INTEGRASI=&Type=');" value="Tambah Agensi" >-->
</legend>

<table width="100%" align="center" border="0" cellpadding="0" cellspacing="2">	
<tr align="center">
<td width="1%" valign="top" ></td>			
<td width="29%" valign="top" align="left">Carian Umum </td>
<td width="1%" valign="top" >:</td>
<td width="69%" valign="top" align="left">
<input type="text" id="carianUmum" name="carianUmum" style="text-transform:uppercase;" size="50" value="$!carianUmum" 
onkeypress="if(event.keyCode == 13) { doDivAjaxCall$formname('div_senaraiUtama','carianUmum','FlagCari=Y&carianUmum='+$jquery('#carianUmum').val()); return false; }">	

<input type="button" id="cmdCariKementerian" name="cmdCariKementerian" value="Cari" onClick="$jquery('#cetakReport').val('Y');doDivAjaxCall$formname('div_senaraiUtama','carianUmum','FlagCari=Y&carianUmum='+$jquery('#carianUmum').val());" >
<input type="hidden" id="cetakReport" name="cetakReport" value="$cetakReport" >

<input type="button" id="cmdBatalCariKementerian" name="cmdBatalCariKementerian" value="Reset" onClick="$jquery('#carianUmum').val('');" >
<!--<input style="display:none" type="button" id="cmdCetakPejabat" name="cmdCetakPejabat" value="Cetak Laporan"
onClick="$jquery('#cetakReport').val('Y');doDivAjaxCall$formname('SenaraiForPrint','cetakListKementerian','FlagCetak=Y&ID_NEGERI='+$jquery('#Form_id_negeri').val());"  />-->

</td>
</tr>

<tr>
<td valign="top" >	
</td>			
</tr>
</table>

<!--<div id="div_AddInteg$Type">
</div>-->

</fieldset>

<br>
</td>
</tr>
</table>