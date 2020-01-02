
<div align="center"><strong>TREND PENDAFTARAN DAN NYAHAKTIF PENGGUNA BAGI TAHUN $TAHUN</strong></div>
<br />

<table width="100%" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" >
<tr class="table_header" >
<td width="37%" align="center" valign="top"><strong>Bulan</strong></td>
<td width="63%" valign="top" align="center"><strong>Daftar Pengguna</strong></td>
<!--<td width="73%" valign="top" align="center"><strong>Nyahaktif Pengguna</strong></td>-->
</tr>
#if ($statDaftar.size()>0)
#foreach ($stat in $statDaftar)
<tr>
<td width="37%" align="center" valign="top" class="$stat.rowCss">$stat.MONTH_DISPLAY</td>
<td width="63%" valign="center" align="center" class="$stat.rowCss">$stat.JUMLAH_DAFTAR</td>
<!--<td width="63%" valign="center" align="center" class="$stat.rowCss"></td>-->
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