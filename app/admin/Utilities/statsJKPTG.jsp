<script>
$jquery(document).ready(function () {
document.getElementById('fieldJKPTG').style.display="";
});
</script>

<table width="100%%" align="center" border="0" cellpadding="0" cellspacing="0">
<tr class="table_header" >
<td width="2%" class="underline_td_main">
</td>
<td width="58%" class="underline_td_main">
<strong> Statistik Unit JKPTG mengikut Negeri </strong>
</td>
<td width="38%" class="underline_td_main">
</td>
<td width="2%" class="underline_td_main">
</td>
</tr>
</table>


<fieldset id="div_rowPejabatUrusan">
<table width="100%" id="div_viewPejabat" style="display:none">
<tr >
</tr>
</table>
<div id="statsJKPTGNegeri">
<table width="79%" align="center">
<tr  >
<th colspan="15" align="center" style="font-size:90%;">Statistik Unit JKPTG mengikut Negeri<br><br></th>
</tr>
<tr>
<td>


	<table border="0" cellspacing="1" cellpadding="1" width="100%" > 
	<tr class="table_header" >
		   <td width="40%"   align="left" valign="top">Negeri</td>
		   <td width="20%"   align="center" valign="top">Jumlah Unit JKPTG</td>
           <td width="20%"   align="center" valign="top">Jumlah Kakitangan</td>
           <!-- <td width="20%"   align="left" valign="top">Jumlah Unit JKPTG</td>-->
	</tr>
	#if($statsJKPTGNegeri.size()>0)
	#foreach($stats in $statsJKPTGNegeri)
			<tr>
			   <td   align="left" valign="top" class="$stats.rowCss">$stats.NAMA_NEGERI </td>
			   <td  align="center" valign="top" class="$stats.rowCss">$stats.JUMLAHUNIT </td>  
                <td  align="center" valign="top" class="$stats.rowCss">$stats.JUMLAHSTAFF </td>  
                 <!--<td  align="left" valign="top" class="$PejUrus.rowCss">$stats.JUMLAHSTAFF </td>  -->
		</tr>
	#end
	#else
	<tr >
	<td  align="left" valign="top" colspan="14" >Tiada Rekod</td>	     
	</tr>
	#end
	</table>

</td>
</tr>

<tr>
<td align="center">
<input type="button" value="Cetak" id="cetakJumlahStaff" name="cetakJumlahStaff" onclick="printLaporan('statsJKPTGNegeri','Statistik Unit JKPTG mengikut Negeri');"/>
</td>
</tr>
</table>
</div>
</fieldset>
