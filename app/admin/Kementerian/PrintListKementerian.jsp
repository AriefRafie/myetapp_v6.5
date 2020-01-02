

<table width="100%%" align="center" border="0" cellpadding="0" cellspacing="0">
<tr class="table_header" >
<td width="2%" class="underline_td_main">
</td>
<td width="58%" class="underline_td_main">
<font size="4"><strong> Senarai Kementerian </strong></font>
</td>
<td width="38%" class="underline_td_main">
</td>
<td width="2%" class="underline_td_main">

</td>
</tr>
</table>

<table width="100%" align="center">
<tr>
<td>
<table width="100%" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" > 
	
<tr class="table_header" >
<td   align="center" valign="top"><strong>Bil.</strong></td>
<td   align="left" valign="top"><strong>Kod</strong></td>
<td   align="left" valign="top"><strong>Nama Kementerian</strong></td>
</tr>

#if($listKementerian.size()>0)
#foreach($list in $listKementerian)
<tr id="div_rowPejabatUrusan$gred.ID_GRED">
<td   align="center" valign="top" >$list.BIL.</td>
<td  align="center" valign="top">($list.KOD_KEMENTERIAN)</td>
<td  align="left" valign="top">$list.NAMA_KEMENTERIAN</td>
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
</table>

<script>
$jquery(document).ready(function () {
		printHideDiv('SenaraiForPrint');
		
		/*var divPrint =  document.getElementById('cmdCetakPejabat');
		divPrint.style.display = "none";*/
		
		var SenaraiForPrint =  document.getElementById('SenaraiForPrint');
		SenaraiForPrint.style.display = "none";
		
	});
</script>