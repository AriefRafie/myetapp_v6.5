
<table width="100%" border="0" >
<tr>
<td>
<fieldset>
<legend><b>Senarai Pengguna </b>&nbsp; 
<input name="PENGGUNA" id="PENGGUNA" value="Tambah Pengguna" type="button" onClick="javascript:tambahPENGGUNA()">
</legend>
<table  border="0" width="100%" bordercolor="green" align="center"> 
<tr class="table_header" >
<td width="5%" align="center"><strong>BIL</strong></td>
<td width="25%"><strong>NAMA</strong></td>
<td width="10%"><strong>KAD PENGENALAN</strong></td>
<td width="5%"><strong>UMUR</strong></td>
<td width="5%"><strong>JANTINA</strong></td>
<td width="20%" ><strong>ALAMAT</strong></td>
<td width="5%"><strong>POSKOD</strong></td>
<td width="20%"><strong>NEGERI</strong></td>
</tr>
          
          #set ($counter = 0)
          #foreach ($list in $listTRAIN)          
          #set($counter=$counter+1)
          #if($counter%2!=0)
          #set ($row = "row1")
          #else
          #set ($row = "row2")
          #end
          <tr>
          <td  class="$row" valign="top" align="center" >$list.BIL</td>           
          <td  class="$row" valign="top" ><a href="javascript:paparTRAIN('$list.ID')"><B><font color="blue">$list.NAMA</font></B></a></td>
          <td  class="$row" valign="top" >$list.IC &nbsp;</td>
          <td  class="$row" valign="top" >$list.UMUR &nbsp;</td>
          <td  class="$row" valign="top" >$list.JANTINA &nbsp;</td>
          <td  class="$row" valign="top" >$list.ALAMAT &nbsp;</td>
          <td  class="$row" valign="top" >$list.POSKOD &nbsp;</td>
          <td  class="$row"  valign="top" >$list.NEGERI &nbsp;</td>
          </tr>       
          #end
          
          #if($counter == 0)
          <tr><td  valign="top" colspan="10">Tiada rekod</td></tr>
          #end 
          
         
</table>
</fieldset>
</td>
</tr>
</table>
