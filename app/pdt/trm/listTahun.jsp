
#set($sizeTable = "50%")
#if($listTahun.size() > 15)
#set($sizeTable = "100%")
<div style="overflow-y: scroll; height:400px; width:50%" >
#end
<table border="0" cellspacing="1" cellpadding="1" width="$sizeTable" > 
<tr><td width="10%"></td><td width="90%"></td></tr>
<tr class="table_header" >
<td align="center" valign="top">
<input type="checkbox" onclick="doCheckMain('checkMAIN_TAHUN','checkSUB_TAHUN');" id="checkMAIN_TAHUN" name="checkMAIN_TAHUN" value="1" />                    
</td>
<td  valign="top" align="left" >
Pilih Kesemua Tahun
</td>
</tr>
#foreach($ln in $listTahun)
<tr  class="row2" >
<td align="center" valign="top">
<input type="checkbox" onclick="doCheckSub('checkMAIN_TAHUN','checkSUB_TAHUN');" #if($ln.FLAG_WUJUD == "Y") checked="checked"  #end id="checkSUB_TAHUN" name="checkSUB_TAHUN" value="$ln.TAHUN" />   </td>
<td align="left" valign="top">$ln.TAHUN</td>
</tr>
#end
</table>
#if($listTahun.size() > 15)
</div>
#end