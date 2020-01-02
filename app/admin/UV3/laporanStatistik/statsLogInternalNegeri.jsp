
<fieldset>
<legend>Statistik Penggunaan Sistem Dalaman mengikut Negeri</legend>
<div id="LogAgensi">
  <table width="100%%" align="center" border="0" cellpadding="0" cellspacing="0">
   <!-- <tr class="table_header" >
      <td width="2%" class="underline_td_main"></td>
      <td width="58%" class="underline_td_main"><font size="3"><strong> </strong></font></td>
      <td width="38%" class="underline_td_main"></td>
      <td width="2%" class="underline_td_main"></td>
    </tr>-->
  </table>
  <table border="0" cellpadding="2" cellspacing="2" align="center" width="98%">
    <tr width="100%" >
      <td colspan="14"><table width="100%" align="center">
        <tr>
          <td><table border="0" cellspacing="1" cellpadding="1" width="100%" > 
	
<tr class="table_header" >
<td   align="left" valign="top">Negeri</td>
<td   align="center" valign="top">Jumlah Pengguna </td>
</tr>
#if($statsInternalNegeri.size()>0)
#foreach($list in $statsInternalNegeri)
<tr id="div_rowPejabatUrusan$gred.ID_GRED">
<td  align="left" valign="top" class="$list.rowCss">$list.NAMA_NEGERI</td>
<td  align="center" valign="top" class="$list.rowCss">$list.TOTALUSERONLINE</td>
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
</table></td>
        </tr>
      </table></td>
    </tr>
  </table>
</div>
</fieldset>