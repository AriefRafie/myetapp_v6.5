
<fieldset>
<legend><strong> Senarai 5 Agensi yang Paling Aktif</strong></legend>
<table width="100%%" align="center" border="0" cellpadding="0" cellspacing="0">
<tr  >
<td width="2%" >
</td>
<td width="58%" ></td>
<td width="38%" >
</td>
<td width="2%" >

</td>
</tr>
</table> 


<table border="0" cellpadding="2" cellspacing="2" align="center" width="98%">
<tr width="100%" >
<td colspan="14">
<table width="100%" align="center">
<tr><td colspan="14" align="right"><img width="20" height="20" src="../img/printer.png" onclick="doCetak('printableArea')"></td></tr>
<tr>
<td>

<div id="printableArea">
<table border="0" cellspacing="1" cellpadding="1" width="100%" > 	
<tr class="table_header" >
<td   align="center" valign="top">Bil.</td>
<td   align="left" valign="top">Nama Agensi</td>
<td   align="left" valign="top">Kekerapan Log Masuk Pengguna </td>
</tr>
#if($listAgensiAktif.size()>0)
#foreach($list in $listAgensiAktif)
<tr id="div_rowPejabatUrusan$gred.ID_GRED">
<td   align="center" valign="top" class="$list.rowCss">$list.BIL</td>
<td  align="left" valign="top" class="$list.rowCss">$list.NAMA_AGENSI</td>
<td  align="center" valign="top" class="$list.rowCss">$list.TOTALAGENSI</td>
</tr>
<tr  id="div_viewAgensi$list.ID_AGENSI">
<td align="left" valign="top" colspan="14">
</td>
</tr>
#end
#else
<tr >
<td  align="left" valign="top" colspan="14" >Tiada Rekod</td>
</tr>
#end
</table>

</div>
</td>
</tr>

</table>
</td>
</tr>

</table>
</fieldset>