<fieldset>
<legend><strong>SENARAI URUSAN</strong></legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr class="table_header">
    <td width="5%" scope="row" align="center"><strong><font color="white">Bil.</font></strong></td>
    <td><strong><font color="white">No. Perserahan</font></strong></td>
    <td ><strong><font color="white">Jenis Urusan</font></strong></td>
    <td ><strong><font color="white">Tarikh Daftar</font></strong></td>
  </tr>
  #set ($list = "")
  #set ($counter = 0)
  #if ($hakmilik.urusanList.size() > 0)
  #foreach ($list in $hakmilik.urusanList)
  #set( $counter = $counter + 1 )
  #if ($counter == '')
  #set( $row = "row1" )
  #elseif (($counter % 2) != 0)
  #set( $row = "row1" )
  #else 
  #set( $row = "row2" )
  #end
  <tr>
    <td class="$row" align="center"> $counter </td>
    <td class="$row">$!list.noPerserahan.toUpperCase()</td>
    <td class="$row">$!list.jenisUrusan.toUpperCase()</td>
    <td class="$row">$!list.tarikhDaftar</td>
  </tr>
  #end  
  #else
  <tr>
    <td colspan="4" class="row"> Tiada rekod </td>
  </tr>
  #end
</table>
</fieldset>
