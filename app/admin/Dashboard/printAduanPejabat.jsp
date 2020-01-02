<div align="center"><strong>JUMLAH ADUAN MENGIKUT PEJABAT JKPTG BAGI TAHUN $TAHUN</strong></div>
<br /> 
<table width="100%" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" > 
	
<!--<tr><td colspan="14" align="right"><img width="20" height="20" src="../img/printer.png" onclick="doCetak('LogMasuk')"></td></tr>-->
<tr class="table_header" >
<td width="37%" align="center" valign="top"><strong>Negeri</strong></td>
<td width="63%" valign="top" align="center"><strong>Jum. Aduan</strong></td>
</tr>
#if ($statJKPTGPLA.size()>0)
#foreach ($stat in $statJKPTGPLA)
<tr>
<td width="37%" align="center" valign="top">$stat.NAMA_NEGERI</td>
<td width="63%" valign="center" align="center">$stat.JUMLAH</td>
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