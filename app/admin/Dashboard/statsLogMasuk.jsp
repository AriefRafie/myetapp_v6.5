
<div align="center"><strong>TREND LOG MASUK BAGI TAHUN $TAHUN</strong></div>
<br />

<table width="100%" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" > 
	
<!--<tr><td colspan="14" align="right"><img width="20" height="20" src="../img/printer.png" onclick="doCetak('LogMasuk')"></td></tr>-->
<tr class="table_header" >
<td width="27%" align="center" valign="top"><strong>Bulan</strong></td>
<td width="73%" valign="top" align="center"><strong>Log Pengguna</strong></td>
</tr>
#if ($statLogin.size()>0)
#foreach ($stat in $statLogin)
<tr>
<td width="27%" align="center" valign="top" class="$stat.rowCss">$stat.MONTH_DISPLAY</td>
<td width="73%" valign="top" align="center" class="$stat.rowCss">$stat.JUMLAH</td>
</tr>
#end
#end
</table>


<script>
$jquery(document).ready(function () {
		printHideDiv('printStats');
		
		var printStats =  document.getElementById('printStats');
		printStats.style.display = "none";
		
	});
</script>