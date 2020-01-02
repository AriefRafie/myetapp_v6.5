<style type="text/css">
<!--
.style1 {color: #0033FF}
.style2 {color: #0000FF}
.style5 {
	color: #0000FF;
	font-style: italic;
	font-size: 9px;
}
-->
</style>
<fieldset>
	<legend>Senarai Fail KIV</legend>
	<br />
	<table width="100%"  border="1" cellspacing="1" cellpadding="2"  > 
	
#if($SenaraiFail.size()>0)
	 <tr class="table_header">
		   <td  align="center" valign="top"><b>BIL.</b></td>
		   <td   align="left" valign="top"><b>NO FAIL</b></td>
		   <td   align="left" valign="top"><b>NAMA SIMATI</b></td>
		   <td   align="left" valign="top"><b>TARIKH MATANG</b></td>
		   <td   align="left" valign="top"><b>TARIKH PERINTAH</b></td>
		   #if($jawatanKpp==true)
		   <td   align="left" valign="top"><b>PEGAWAI PENGENDALI</b></td>
		   <td   align="left" valign="top"><b>CATATAN</b></td>
		   #end
	</tr>
	

	
	
		#foreach($listKIV in $SenaraiFail)
		<tr >
			   <td class="$row"  align="center" valign="top" >$listKIV.BIL</td>
			   <td class="$row"  align="left" valign="top">$listKIV.NO_FAIL</td>
			   <td class="$row"  align="left" valign="top">$listKIV.NAMA_SIMATI</td>
			   <td class="$row"  align="center" valign="top">$listKIV.DATE_KIV</td>
			   <td class="$row"  align="center" valign="top">$listKIV.tarikh_perintah</td>
			   #if($jawatanKpp==true)
			   <td class="$row"  align="left" valign="top">$listKIV.PEG_PENGENDALI</td>
			   <td class="$row"  align="left" valign="top">$listKIV.CATATAN_KIV</td>
			   #end
		</tr>
		
		
		
		#end
		#else
		
		
        
		<tr>
		<td colspan="8" style="display:none">
		Tiada Rekod		</td>
		</tr>
		#end	
</table>
	
</fieldset>
<script>
$jquery(document).ready(function () {
		printDiv('div_PrintKIV');
		
	});
</script>
