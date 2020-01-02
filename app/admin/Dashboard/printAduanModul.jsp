<div align="center"><strong>JUMLAH ADUAN MENGIKUT MODUL BAGI TAHUN $TAHUN</strong></div>
<br />
<table width="100%" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" > 
	
<!--<tr><td colspan="14" align="right"><img width="20" height="20" src="../img/printer.png" onclick="doCetak('LogMasuk')"></td></tr>-->
<tr class="table_header">
<td width="27%" align="center" valign="top"><strong>Modul</strong></td>
<td width="73%" valign="top" align="center"><strong>Jum. Aduan</strong></td>
</tr>
#if ($statModulePLA.size()>0)
#foreach ($stat in $statModulePLA)
<tr>
<td width="27%" align="center" valign="top">$stat.MODUL</td>
<td width="73%" valign="top" align="center">$stat.JUMLAH</td>
</tr>
#end
#else
<tr >
<td colspan="14">Tiada rekod aduan. </td>
</tr>
#end
</table>

<script>
$jquery(document).ready(function () {
		printHideDiv('printStats');
		
		var printStats =  document.getElementById('printStats');
		printStats.style.display = "none";
		
	});
</script>